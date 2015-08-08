//
//  WebViewController.m
//  Entel
//
//  Created by Alex Campayo on 4/21/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "WebViewController.h"

#define CallHistoryBack @"entel://historyBack"

@interface WebViewController ()
@property(nonatomic, strong) Reachability *wifiReachability;
@property(nonatomic) UILabel *offlineBanner;
@property(nonatomic) NSTimer *timer;
@property(nonatomic) BOOL isPullToRefresh, authed, hasAuth, isTimeout;
@property(nonatomic, assign) NSInteger reloadAttempts;
@property(nonatomic, copy) NSString *realoadLastURL;
@end

@implementation WebViewController

- (id)initWithCoder:(NSCoder *)coder {
    self = [super initWithCoder:coder];
    if (self) {
        self.wifiReachability = [Reachability reachabilityForInternetConnection];
        [self.wifiReachability startNotifier];
    }
    
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.errorConnectionView.hidden = YES;
    self.authed = NO;
    self.reloadAttempts = 0;
    
    if (!IS_CLASSIC) {
        [self setupNavigationBar];
    }
    
    if ([self.wifiReachability currentReachabilityStatus] == NotReachable) {
        [self showOfflineBanner];
    } else {
        self.offlineBanner.hidden = YES;
    }
    
    self.webView.delegate = self;
    self.webView.hidden = YES;
    self.webView.scrollView.scrollEnabled = TRUE;
    
    self.loadingView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhiteLarge];
    self.loadingView.center = self.view.center;
    [self.loadingView setColor:[UIColor blackColor]];
    [self.view addSubview:self.loadingView];
    
    self.refreshControl = [[UIRefreshControl alloc] init];
    [self.refreshControl addTarget:self action:@selector(handleRefresh:) forControlEvents:UIControlEventValueChanged];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reachabilityChanged:) name:kReachabilityChangedNotification object:nil];
}

- (void) reachabilityChanged:(NSNotification *)note {
    __weak typeof (self) weakSelf = self;
    Reachability* curReach = [note object];
    NSParameterAssert([curReach isKindOfClass:[Reachability class]]);
    if (curReach == self.wifiReachability){
        double delayInSeconds = 1.0;
        dispatch_time_t popTime = dispatch_time(DISPATCH_TIME_NOW, (int64_t)(delayInSeconds * NSEC_PER_SEC));
        dispatch_after(popTime, dispatch_get_main_queue(), ^(void){
            [weakSelf checkLocalWifiConnection];
        });
    }
}

- (void)checkLocalWifiConnection {
    NetworkStatus netStatus = [self.wifiReachability currentReachabilityStatus];
    if (netStatus!=NotReachable) {
        self.offlineBanner.hidden = YES;
        self.errorConnectionView.hidden = YES;
        self.loadingView.hidden = NO;
        [self loadURL:self.currentURL];
    } else {
        [self showOfflineBanner];
    }
}

- (void)showOfflineBanner {
    [self showErrorView];
    if (!self.offlineBanner) {
        NSInteger h = 50;
        self.offlineBanner = [[UILabel alloc] initWithFrame:CGRectMake(0, CGRectGetHeight(self.view.frame) - h, CGRectGetWidth(self.view.frame), h)];
        self.offlineBanner.backgroundColor = [UIColor colorWithWhite:0.8 alpha:0.8];
        self.offlineBanner.text = _(@"no_internet_conection");
        self.offlineBanner.textAlignment = NSTextAlignmentCenter;
        [self.view addSubview:self.offlineBanner];
    }
    self.offlineBanner.hidden = NO;
}

-(void)showErrorView {
    if ([self.loadingView isAnimating]) {
        self.errorConnectionView.hidden = NO;
        self.loadingView.hidden = YES;
    } else {
        self.errorConnectionView.hidden = YES;
    }
}

-(void)setupNavigationBar {
    CGFloat h = [UIApplication sharedApplication].statusBarFrame.size.height;
    CGRect frame = self.navigationBar.frame;
    frame.size.height += h;
    self.navigationBar.frame = frame;
    
    CGRect WVFrame = self.webView.frame;
    WVFrame.size.height -= h;
    self.webView.frame = WVFrame;
    
    for (UIView *v in [self.view subviews]) {
        if (v != self.navigationBar) {
            v.transform = CGAffineTransformMakeTranslation(0, [UIApplication sharedApplication].statusBarFrame.size.height);
        }
    }
}

-(void)loadURL:(NSString *)url {
    self.currentURL = url;
    NSMutableURLRequest *request;
    
    if (self.wifiReachability.isReachable) {
        request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:url]];
    }
    else
    {
        request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:url]
                                   cachePolicy:NSURLRequestReturnCacheDataElseLoad
                               timeoutInterval:60];
    }
    
    [request addValue:@"" forHTTPHeaderField:@"If-None-Match"];
    [self setUserAgent:@"" request:request];
    
    if ([url isEqualToString:URL_SURVEY]) {
        [request addValue:self.previousURL forHTTPHeaderField:@"Referer"];
    }
    
    [self.webView loadRequest:request];
}

-(void)handleRefresh:(UIRefreshControl *)refresh {
    self.isPullToRefresh = YES;
    [self.webView reload];
}

-(IBAction)reload:(id)sender {
    [self loadURL:self.currentURL];
}

- (void)setUserAgent:(NSString*)url request:(NSMutableURLRequest*)request {
    //This method should be overrided in the subclass.
}

-(void)startTimer {
    self.isTimeout = NO;
    self.errorConnectionView.hidden = YES;
    self.loadingView.hidden = self.isPullToRefresh ? YES : NO;
    self.timer = [NSTimer scheduledTimerWithTimeInterval:25.0
                                                  target:self
                                                selector:@selector(cancelWeb)
                                                userInfo:nil
                                                 repeats:NO];
}

- (void)cancelWeb {
    [self.timer invalidate];
    [self.webView stopLoading];
    self.isTimeout = YES;
    [self showErrorView];
}

-(void)webViewDidStartLoad:(UIWebView *)webView {
    [self.timer invalidate];
    if (![self.loadingView isAnimating] && !self.isPullToRefresh) {
        [self.loadingView startAnimating];
    }
    
    NetworkStatus netStatus = [self.wifiReachability currentReachabilityStatus];
    if (netStatus==NotReachable) {
        [self showErrorView];
        [self.webView stopLoading];
    } else {
        [self startTimer];
        if (self.isPullToRefresh) {
            self.webView.hidden = NO;
        } else {
            self.webView.hidden = YES;
        }
    }
}

-(BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest  *)request navigationType:(UIWebViewNavigationType)navigationType {
    NSString *requestString = [[request URL] absoluteString];
    NSLog(@"Did start loading: %@ auth:%d", requestString, self.authed);
    
    // Deal with custom calls first
    if ([requestString isEqualToString:CallHistoryBack]) {
        [self.navigationController popViewControllerAnimated:YES];
        return NO;
    }
    
//    NSDictionary *d = [[NSURLCredentialStorage sharedCredentialStorage] allCredentials];
//    
//    //Check if webuser credentials exist
//    for (NSString* key in d) {
//        
//        NSURLProtectionSpace *ps = (NSURLProtectionSpace *)key;
//        
//        if ([ENTEL_DOMAIN hasPrefix:ps.host]) {
//            if ([[d objectForKey:key] objectForKey:CREDENTIAL_USER]) {
//                self.authed = YES;
//            }
//        }
//    }
//    
//    if (!self.authed) {
//        NSURLConnection *connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
//        [connection start];
//        return NO;
//    } else {
//        return YES;
//    }
//    
//    return NO;
    
    return YES;
}

-(void)webViewDidFinishLoad:(UIWebView *)webView {
    [self.timer invalidate];
    NSCachedURLResponse *resp = [[NSURLCache sharedURLCache] cachedResponseForRequest:webView.request];
    NSLog(@"HEADERS: %@",[(NSHTTPURLResponse*)resp.response allHeaderFields]);
    
    // WARNING: Comment out this line ONLY for testing.
//    [self fakeMZZOErrorTag:webView];
    
    if ([self didManageMZZOErrorTag:webView]) {
        NSLog(@"MZZO tag found, attempting to reload!");
        return;
    }

    [webView.scrollView insertSubview:self.refreshControl atIndex:0];
    if(self.currentURL && [URL_SURVEY isEqualToString:self.currentURL]){
        [self.refreshControl removeFromSuperview];
    }
    [webView stringByEvaluatingJavaScriptFromString:@"document.getElementById('mnsb').remove();document.getElementById('mngb').remove();"];
    
    // re-write history.back()
    // for now it will get button with class "btn btn-default" but it would be better to ask MZZO to add an id for this button.
    NSString *historyBackRewriteString = [NSString stringWithFormat:@"var elems=document.getElementsByClassName('btn btn-default');for(var i=0;i<elems.length;i++){var elem = elems[i];if(elem && elem.getAttribute('href').indexOf('history.back')>=0){elem.setAttribute('href','%@')};};",CallHistoryBack];
    [webView stringByEvaluatingJavaScriptFromString:historyBackRewriteString];
    
    double delayInSeconds = 0.1;
    dispatch_time_t popTime = dispatch_time(DISPATCH_TIME_NOW, delayInSeconds * NSEC_PER_SEC);
    __weak typeof (self) weakSelf = self;
    dispatch_after(popTime, dispatch_get_main_queue(), ^(void){
        [weakSelf.loadingView stopAnimating];
        [weakSelf.refreshControl endRefreshing];
        
        if (!self.isPullToRefresh) {
            webView.hidden = NO;
            webView.alpha = 0.0f;
            [UIView animateWithDuration:0.2 animations:^{webView.alpha = 1.0;}];
        }
        
        weakSelf.isPullToRefresh = NO;
        weakSelf.authed = NO;
    });
}

- (void) fakeMZZOErrorTag:(UIWebView*)webview {
    [webview stringByEvaluatingJavaScriptFromString:@"var ele = document.createElement('MZZO');ele.setAttribute('attempt',1);document.body.insertBefore(ele, document.body.childNodes[0])"];
}

- (BOOL) didManageMZZOErrorTag:(UIWebView*)webview {
    
    // reset attemps
    if (![self.currentURL isEqualToString:self.realoadLastURL]) {
        self.reloadAttempts = 0;
    }
    
    // check for MZZO error
    NSString *attempt = [webview stringByEvaluatingJavaScriptFromString:@"document.getElementsByTagName('MZZO')[0].getAttribute('attempt');"];
    if (![attempt isEqualToString:@""]) {
        NSInteger attempts = [attempt integerValue];
        if (self.reloadAttempts < attempts) {
            self.reloadAttempts ++;
            self.realoadLastURL = self.currentURL;
            [webview reload];
            return true;
        }
    }
    return false;
}


-(void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
    [self.timer invalidate];
    if (!self.isTimeout) {
        [self showErrorView];
    }
}

-(void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:kReachabilityChangedNotification object:nil];
}

#pragma mark NSURLConnectionDelegate - Challenge

//- (void)connection:(NSURLConnection *)connection didReceiveAuthenticationChallenge:(NSURLAuthenticationChallenge *)challenge {
//    NSLog(@"Has Challenge");
//    self.authed = YES;
//    self.hasAuth = YES;
//    [[challenge sender] useCredential:[NSURLCredential credentialWithUser:CREDENTIAL_USER
//                                                                password:CREDENTIAL_PSW
//                                                             persistence:NSURLCredentialPersistencePermanent]
//           forAuthenticationChallenge:challenge];
//}
//
//- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response {
//    if (self.hasAuth) {
//        self.hasAuth = NO;
//        [self loadURL:self.currentURL];
//    }
//}
//
//- (BOOL)connectionShouldUseCredentialStorage:(NSURLConnection *)connection {
//    return YES;
//}

@end
