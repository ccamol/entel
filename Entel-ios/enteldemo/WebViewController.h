//
//  WebViewController.h
//  Entel
//
//  Created by Alex Campayo on 4/21/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Reachability.h"

@interface WebViewController : UIViewController<UIWebViewDelegate, NSURLConnectionDelegate, NSURLConnectionDataDelegate>
@property (weak, nonatomic) IBOutlet UIWebView *webView;
@property (nonatomic) UIRefreshControl *refreshControl;
@property(nonatomic) NSString *currentURL, *previousURL;
@property(nonatomic)IBOutlet UIView *navigationBar;
@property(nonatomic) UIActivityIndicatorView *loadingView;
@property(nonatomic)IBOutlet UIView *errorConnectionView;
@property(nonatomic)IBOutlet UIButton *btnReload;
-(void)handleRefresh:(UIRefreshControl *)refresh;
-(void)loadURL:(NSString *)url;
-(void)setUserAgent:(NSString *)url request:(NSMutableURLRequest*)request;
-(void)showErrorView;
@end
