//
//  ViewController.m
//  enteldemo
//
//  Created by Matthew Mayer on 26/02/2014.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "MainViewController.h"
#import "Constants.h"
#import <PKRevealController/PKRevealController.h>
#import <SVProgressHUD/SVProgressHUD.h>
#import "APIEngine.h"
#import <CommonCrypto/CommonCryptor.h>
#import "ARC4.h"
#import "LeftViewController.h"
#import "DataManager.h"
#import "Accessories.h"
#import "LeftMenuManager.h"
#import "OnBoardingViewController.h"
#import "AppDelegate.h"

@interface MainViewController ()
@property (weak, nonatomic) IBOutlet UIButton *hamburgerButton;
@property (weak, nonatomic) IBOutlet UIButton *backButton;
@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UIImageView *headerLogo;
@property (weak, nonatomic) IBOutlet UIImageView *loginLogo;
@property (strong, nonatomic) NSString *nextUrl, *registerPhone, *registerRut, *registerKey;
@property (nonatomic)BOOL isUserEdited;
@property(nonatomic, weak) IBOutlet UIButton *loginBtn;
@property(nonatomic, weak) IBOutlet UITextField *txtPhoneNumber;
@property(nonatomic, weak) IBOutlet UITextField *txtRut;
@property(nonatomic, weak) IBOutlet UITextField *txtPin;
@property(nonatomic, weak) IBOutlet UITextView *txtMiEntel;
@property(nonatomic, weak) IBOutlet UIView *loginBgView;
@property(nonatomic, weak) IBOutlet UIScrollView *loginView;
@property(nonatomic, weak) IBOutlet UIButton *btnRegister, *btnRecover;
@property(nonatomic, weak) IBOutlet UIView *bgPattern;
@property(nonatomic) LeftViewController *leftView;
@property(nonatomic) BOOL isLoadOpenURL;
@property (weak, nonatomic) UITextField *txtActive;
@property (weak, nonatomic) IBOutlet UIButton *infoBtn;
@end

@implementation MainViewController

-(id)initWithCoder:(NSCoder *)aDecoder {
    if (self = [super initWithCoder:aDecoder])  {
        self.menuMode = LeftMenuModeNoLogin;
    }
    return self;
}

- (IBAction)onHambuger:(id)sender {
    [self.revealController showViewController:self.revealController.leftViewController animated:YES completion:nil];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self setUpLoadingView];
    User *user = (User *)[[DataManager shared] unarchiveObjectforKey:KEY_USER];
    
    if (user != nil) {
        // If the property "aaa" has the default value "-1", means we have to log in the user again to capture this data from the backend
        if (user.aaa != -1) {
            [[DataManager shared] incrementNumExecutions];
            self.menuMode = [[LeftMenuManager shared] getMenuMode:user.market];
        }
    }
    
    [self checkSurvey];
    
    self.leftView = (LeftViewController *)self.revealController.leftViewController;
    [self.leftView reloadMenu:self.menuMode];
    [self loadURL:self.url ? self.url : [self.leftView.leftMenuItems objectAtIndex:0][@"url"]];
    self.loginView.scrollEnabled = [[UIScreen mainScreen] bounds].size.height < 568;
    
    if (self.menuMode != LeftMenuModeNoLogin && !self.txtTitle) {
        [self setTitle:_([self.leftView.leftMenuItems objectAtIndex:0][@"title"])];
    } else if (self.txtTitle) {
        [self setTitle:self.txtTitle];
    }
    
    // Set input accessory views
    self.txtPhoneNumber.inputAccessoryView = [Accessories forTextFieldInputFormWithTarget:self backAction:nil nextAct:@selector(onNextAction) doneAction:nil];
    self.txtRut.inputAccessoryView = [Accessories forTextFieldInputFormWithTarget:self backAction:@selector(onBackAction) nextAct:@selector(onNextAction) doneAction:nil];
    self.txtPin.inputAccessoryView = [Accessories forTextFieldInputFormWithTarget:self backAction:@selector(onBackAction) nextAct:nil doneAction:@selector(onDoneAction)];
}


- (void) setUpLoadingView {
    UIImage *imgLoading = [UIImage animatedImageNamed:@"preloader" duration:1.3f];
    UIImageView *loadingImageView = [[UIImageView alloc] initWithImage:imgLoading];
    loadingImageView.frame = CGRectMake(0, 0, 30, 30);
    loadingImageView.center = CGPointMake(CGRectGetWidth(self.loadingView.frame)/2, CGRectGetHeight(self.loadingView.frame)/2);
    
    [self.loadingView setColor:[UIColor clearColor]];
    [self.loadingView addSubview:loadingImageView];
    
    UIImageView *refreshImageView = [[UIImageView alloc] initWithImage:imgLoading];
    refreshImageView.frame = CGRectMake(0, 0, 20, 20);
    refreshImageView.center = CGPointMake(CGRectGetWidth(self.refreshControl.frame)/2, CGRectGetHeight(self.refreshControl.frame)/2);
    refreshImageView.backgroundColor = [UIColor clearColor];
    
    self.refreshControl.tintColor = [UIColor clearColor];
    [self.refreshControl addSubview:refreshImageView];
}

-(void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(showSurveyAlert:) name:SURVEY_ALERTBOX_NOTIF object:nil];
    
    if (DataManager.shared.loadOpenUrl) {
        self.isLoadOpenURL = YES;
        self.menuMode = [[LeftMenuManager shared] getMenuMode:[DataManager.shared getUser].market];
        [self loadURL:DataManager.shared.loadOpenUrl];
        [self setupLoginView:false];
        self.titleLabel.text = DataManager.shared.loadOpenUrlTitle;
        DataManager.shared.loadOpenUrl = nil;
    }
    
    if (self.menuMode == LeftMenuModeNoLogin) {
        [self setupLoginView:self.leftView.selectedOpt == 0];
        if (self.leftView.selectedOpt == 0) {
            [self setupLoginForm];
        }
    } else {
        [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
        self.loginView.hidden = YES;
    }
    
    [self slideMenuVisibility];
    
    UITapGestureRecognizer *singleTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard:)];
    [singleTap setNumberOfTapsRequired:1];
    [self.loginBgView addGestureRecognizer:singleTap];
    
    UIColor *patternColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"bg-pattern"]];
    self.bgPattern.backgroundColor = patternColor;
    
    self.txtPhoneNumber.delegate = self;
    self.txtPin.delegate = self;
    self.txtRut.delegate = self;
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:SURVEY_ALERTBOX_NOTIF object:nil];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)slideMenuVisibility {

    
    if (self.menuMode == LeftMenuModeNoLogin) {
        self.revealController.recognizesPanningOnFrontView = NO;
        self.hamburgerButton.hidden = YES;
        self.backButton.hidden = YES;
    } else {
        BOOL shouldShowMenu = [self.url length] > 0;
        self.revealController.recognizesPanningOnFrontView = !shouldShowMenu;
        self.hamburgerButton.hidden = shouldShowMenu;
        self.backButton.hidden = !shouldShowMenu;
    }
}

-(BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType {
    [webView stringByEvaluatingJavaScriptFromString:@"var iframes = document.getElementsByTagName('iframe');for (var i = 0; i < iframes.length; i++) { iframes[i].parentNode.removeChild(iframes[i]);}"];
    [super webView:webView shouldStartLoadWithRequest:request navigationType:navigationType];
    self.nextUrl = [request.URL absoluteString];
    
    if ([self.nextUrl hasPrefix:@"http://error"]) {
        NSURL *url = [NSURL URLWithString:self.nextUrl];
        NSArray* foo = [[url query] componentsSeparatedByString: @"msg="];
        NSString* msg = [foo objectAtIndex: 1];
        msg = [msg stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        [self showAlert:msg];
        return NO;
    }
    
    if (self.isResgister) {
        [self getRegisterData];
        
        if (self.nextUrl.length > 0 && ([self.nextUrl rangeOfString:URL_SALDO].location != NSNotFound ||
                                        [self.nextUrl rangeOfString:URL_TRAFICO].location != NSNotFound)) {
            self.leftView.selectedOpt = 0;
            [self onLogin:nil];
            return NO;
        }
    }
    
    if ([self.nextUrl rangeOfString:URL_LOGIN].location != NSNotFound) {
        if (self.isRecover) {
            self.leftView.selectedOpt = 0;
            [self.navigationController popViewControllerAnimated:YES];
            return NO;
        }
        
        [self cookiesLog];
        [self.leftView onLogout:nil];
        return NO;
    }
    
    if ([self.nextUrl rangeOfString:URL_BOLSAS_CONFIRMAR].location != NSNotFound && !self.isLoadOpenURL) {
        [self openURLWithMenu:self.nextUrl];
        return NO;
    } else {
        self.isLoadOpenURL = NO;
    }
    
    if (navigationType == UIWebViewNavigationTypeLinkClicked && [self.nextUrl hasPrefix:@"http"]) {
        [self pushNewController:self.nextUrl menuMode:self.menuMode];
        return NO;
    } else {
        if (self.menuMode == LeftMenuModeNoLogin) {
            [self setupLoginView:self.leftView.selectedOpt == 0];
            BOOL hidden = self.option != 0 || self.url;
            self.loginView.hidden = hidden;
            return hidden;
        }
        return YES;
    }
}

-(void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
    if (self.isResgister) {
        self.errorConnectionView.hidden = YES;
        self.loadingView.hidden = YES;
    }
}

-(void)cookiesLog {
    NSHTTPCookie *cookie;
    NSHTTPCookieStorage *storage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    
    for (cookie in [storage cookies]) {
        NSLog(@"ENTEL COOKIE: %@", cookie);
    }
}

-(void)setupLoginView:(BOOL)isLogin {
    [self.loadingView stopAnimating];
    [[UIApplication sharedApplication] setStatusBarStyle:isLogin ? UIStatusBarStyleDefault : UIStatusBarStyleLightContent];
    self.navigationBar.backgroundColor = isLogin ? [UIColor clearColor] : [UIColor entelBlue];
    UIImage *img = isLogin ? [UIImage imageNamed:@"icon-menu-color"] : [UIImage imageNamed:@"hamburger"];
    [self.hamburgerButton setImage:img forState:UIControlStateNormal];
    self.headerLogo.hidden = isLogin;
    self.loginLogo.hidden = !isLogin;
    self.infoBtn.hidden = !isLogin;
}

-(void)webViewDidFinishLoad:(UIWebView *)webView {
    [super webViewDidFinishLoad:webView];
    [self slideMenuVisibility];
    
    if (![[LeftMenuManager shared].currentMenuOptions objectAtIndex:self.leftView.selectedOpt][@"isPullToRefresh"]) {
        [self.refreshControl removeFromSuperview];
    }
}

- (IBAction)backAction:(id)sender {
    self.webView.delegate = nil;
    if ([self.currentURL isEqualToString:URL_RECOVER] || [self.currentURL isEqualToString:URL_REGISTER]) {
        self.leftView.selectedOpt = 0;
    }
    
    [self.navigationController popViewControllerAnimated:YES];
}

-(void)logout {
    [self.navigationController popToRootViewControllerAnimated:NO];
    self.loginView.hidden = NO;
    self.leftView.selectedOpt = 0;
    self.menuMode = LeftMenuModeNoLogin;
    [self setTitle:@""];
    [[DataManager shared] logout];
    [self viewWillAppear:NO];
}

-(void)openURLWithMenu:(NSString *)url {
    DataManager.shared.loadOpenUrl = url;
    DataManager.shared.loadOpenUrlTitle = self.titleLabel.text;
    [self.navigationController popToRootViewControllerAnimated:NO];
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)pushNewController:(NSString *)url menuMode:(LeftMenuMode)menuMode {
    [self pushNewController:url menuMode:menuMode title:nil isRegister:NO isRecover:NO isNotRegisteredUser:NO];
}

- (void)pushNewController:(NSString *)url menuMode:(LeftMenuMode)menuMode title:(NSString *) title isRegister:(BOOL)isRegister isRecover:(BOOL)isRecover {
    [self pushNewController:url menuMode:menuMode title:title isRegister:isRegister isRecover:isRecover isNotRegisteredUser:NO];
}

- (void)pushNewController:(NSString *)url menuMode:(LeftMenuMode)menuMode title:(NSString *) title isRegister:(BOOL)isRegister isRecover:(BOOL)isRecover isNotRegisteredUser:(BOOL)isNotRegisteredUser {
    UIStoryboard *mainStoryboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    MainViewController *nextController = [mainStoryboard instantiateViewControllerWithIdentifier:@"right"];
    nextController.url = url;
    nextController.menuMode = menuMode;
    nextController.isResgister = isRegister;
    nextController.isRecover = isRecover;
    nextController.isNotRegisteredUser = isNotRegisteredUser;
    nextController.previousURL = self.currentURL;
    
    if (url) {
        nextController.txtTitle = title ? title : [self.titleLabel.text substringToIndex:[self.titleLabel.text length]-1];
    }

    [self.navigationController pushViewController:nextController animated:YES];
}

- (void) setTitle:(NSString *) title {
    self.titleLabel.text = title.length > 0 ? [NSString stringWithFormat:@"%@_", title] : @"";
}



#pragma mark Login

- (void) setupLoginForm {
    [self.loginBtn setTitle:_(@"login_btn") forState:UIControlStateNormal];
    
    NSAttributedString *str = [[NSAttributedString alloc] initWithString:_(@"txtPhone") attributes:@{ NSForegroundColorAttributeName : [UIColor entelPlaceholder] }];
    self.txtPhoneNumber.attributedPlaceholder = str;
    
    str = [[NSAttributedString alloc] initWithString:_(@"txtRut") attributes:@{ NSForegroundColorAttributeName : [UIColor entelPlaceholder] }];
    self.txtRut.attributedPlaceholder = str;
    
    str = [[NSAttributedString alloc] initWithString:_(@"txtPin") attributes:@{ NSForegroundColorAttributeName : [UIColor entelPlaceholder] }];
    self.txtPin.attributedPlaceholder = str;
    
    self.loginView.hidden = [self.url length] > 0;
    self.txtMiEntel.text = _(@"mi_entel");
    self.txtMiEntel.textColor = [UIColor entelDarkGray];
    
    self.loginView.contentSize = CGSizeMake(320, 540);
    [self.loginView setAutoresizesSubviews:NO];
    
    [self setupLoginBtn:self.btnRecover title:_(@"btn_recover")];
    [self setupLoginBtn:self.btnRegister title:_(@"btn_register")];
    
    if (!IS_CLASSIC) {
        CGRect frame = self.loginView.frame;
        frame.size.height -= [UIApplication sharedApplication].statusBarFrame.size.height;
    }
}

-(void)setupLoginBtn:(UIButton*)btn title:(NSString*)title {
    [btn setTitle:title forState:UIControlStateNormal];
    [btn setTitleColor:[UIColor entelOrange] forState:UIControlStateNormal];
    [btn setTitleColor:[UIColor entelDarkOrange] forState:UIControlStateHighlighted];
}

- (IBAction)onLogin:(id)sender {
    if (self.isNotRegisteredUser) {
        [SVProgressHUD showWithMaskType:SVProgressHUDMaskTypeBlack];
        [self loginWithCriptedKey:[DataManager shared].cKey];
        return;
    }
    
    NSString *phone = !self.isResgister ? self.txtPhoneNumber.text : self.registerPhone;
    NSString *rut = !self.isResgister ? self.txtRut.text : self.registerRut;
    NSString *pin = !self.isResgister ? self.txtPin.text : self.registerKey;
    
    rut = [rut stringByReplacingOccurrencesOfString:@"." withString:@""];
    rut = [rut stringByReplacingOccurrencesOfString:@"-" withString:@""];
    
    if (!self.isResgister) {
        if (phone.length == 0) {
            [self.txtPhoneNumber becomeFirstResponder];
            [self showAlert:_(@"login_error_phone")];
            return;
        } else if (phone.length < 8 || phone.length > 8) {
            [self.txtPhoneNumber becomeFirstResponder];
            [self showAlert:_(@"login_error_phone_size")];
            return;
        } else if (rut.length == 0) {
            [self.txtRut becomeFirstResponder];
            [self showAlert:_(@"login_error_rut")];
            return;
        } else if (![self validRUT:rut]) {
            [self.txtRut becomeFirstResponder];
            [self showAlert:_(@"login_incorrect_rut_error")];
            return;
        } else if (pin.length == 0) {
            [self.txtPin becomeFirstResponder];
            [self showAlert:_(@"login_error_pin")];
            return;
        }
    }
    
    [self hideKeyboard:nil];
    
//    NSString *deviceModel = [[UIDevice currentDevice] model];
//    NSString *os = [[UIDevice currentDevice] systemVersion];
//    NSString *catBag = [NSString stringWithFormat:@"%@|%@|iOS %@", VERSION_CODE, deviceModel, os];
//    NSString *cipherTxt = [NSString stringWithFormat:@"%@,%@,%@,%@", phone , rut, pin, catBag];
    
    NSString *cipherTxt = [NSString stringWithFormat:@"%@,%@,%@", phone , rut, pin];
    
    [SVProgressHUD showWithMaskType:SVProgressHUDMaskTypeBlack];
    NSString *criptedKey = [ARC4 encrypt:cipherTxt];
    [DataManager shared].cKey = criptedKey;
    [self loginWithCriptedKey:criptedKey];
}

- (void)loginWithCriptedKey:(NSString*)criptedKey {
    [[APIEngine shared] getToken:criptedKey successBlock:^(NSString *token) {
        [self login:token];
    } errorBlock:^(NSError *error) {
        [SVProgressHUD dismiss];
        [self showAlert:[error localizedDescription]];
        
        if (self.isNotRegisteredUser) {
            self.leftView.selectedOpt = 1;
        }
        
        if (self.isResgister) {
            self.leftView.selectedOpt = 1;
            [self showErrorView];
        }
    } registerBlock:^{
        [self onUserNotRegistered];
        [SVProgressHUD dismiss];
    }];
}

- (BOOL)validRUT:(NSString*)rutdv {
    int rut = [[rutdv substringToIndex:rutdv.length-1] intValue];
    char dv = [rutdv characterAtIndex:rutdv.length-1];
    
    //to accept 'k' lowercase to avoid issues with "K" clients
    dv = (dv == 'k')?'K':dv;
    
    int m = 0, s = 1;
    for (; rut != 0; rut /= 10) {
        s = (s + rut % 10 * (9 - m++ % 6)) % 11;
    }
    
    //generate DV to check
    char dvcheck = (char) (s != 0 ? s + 47 : 75);
    
    return dv == dvcheck;
}

- (void) login:(NSString *)token {
    [SVProgressHUD showWithMaskType:SVProgressHUDMaskTypeBlack];
    [[APIEngine shared] login:token successBlock:^(User* user){
        if (self.isNotRegisteredUser) {
            self.isNotRegisteredUser = NO;
        }
        
        if (self.isResgister) {
            self.isResgister = NO;
        }
        
        [SVProgressHUD dismiss];
        self.txtPhoneNumber.text = @"";
        self.txtPin.text = @"";
        self.txtRut.text = @"";
        [self pushNewController:nil menuMode:LeftMenuModeSS];
        [[DataManager shared] login:user];
    } errorBlock:^(NSError *error) {
        [SVProgressHUD dismiss];
        [self showAlert:[error localizedDescription]];
        
        if (self.isNotRegisteredUser) {
            self.leftView.selectedOpt = 1;
        }
        
        if (self.isResgister) {
            self.leftView.selectedOpt = 1;
            [self showErrorView];
        }
    }];
}

-(void)hideKeyboard:(id)sender {
    [self.txtPhoneNumber resignFirstResponder];
    [self.txtRut resignFirstResponder];
    [self.txtPin resignFirstResponder];
}

-(IBAction)onRecover:(id)sender {
    [self hideKeyboard:nil];
    self.leftView.selectedOpt = 1;
    NSString *url = URL_RECOVER;
    [self pushNewController:url menuMode:self.menuMode title:_(@"recover_psw_title") isRegister:NO isRecover:YES];
}

-(IBAction)onRegister:(id)sender {
    [self hideKeyboard:nil];
    self.leftView.selectedOpt = 1;
    NSString *url = URL_REGISTER;
    [self pushNewController:url menuMode:self.menuMode title:_(@"register_user_title") isRegister:YES isRecover:NO];
}

-(void)onUserNotRegistered {
    [self hideKeyboard:nil];
    self.leftView.selectedOpt = 1;
    NSString *url = URL_REGISTER;
    [self pushNewController:url menuMode:self.menuMode title:_(@"register_user_title") isRegister:YES isRecover:NO isNotRegisteredUser:YES];
}

-(void)getRegisterData {
    self.registerPhone = self.registerPhone.length == 0 ? [self.webView stringByEvaluatingJavaScriptFromString:@"document.getElementById('msisdn').value"] : self.registerPhone;
    self.registerRut = self.registerRut.length == 0 ? [self.webView stringByEvaluatingJavaScriptFromString:@"document.getElementById('rut').value"] : self.registerRut;
    self.registerKey = self.registerKey.length == 0 ? [self.webView stringByEvaluatingJavaScriptFromString:@"document.getElementById('clave').value"] : self.self.registerKey;
}

#pragma mark UITextFieldDelegate

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    NSInteger nextTag = textField.tag + 1;
    UIResponder* nextResponder = [textField.superview viewWithTag:nextTag];
    
    if (nextResponder) {
        [nextResponder becomeFirstResponder];
    } else {
        [textField resignFirstResponder];
    }
    
    return NO;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSUInteger newLength = [textField.text length] + [string length] - range.length;
    NSUInteger maxLength = 0;
    
    if (textField.tag == 0) {
        maxLength = 8;
    } else if (textField.tag == 1) {
        maxLength = 10;
    } else if (textField.tag == 2) {
        maxLength = 4;
    }

    return (newLength > maxLength) ? NO : YES;
}

- (void)textFieldDidBeginEditing:(UITextField *)textField
{
    self.txtActive = textField;
    [self animateTextField: textField up: YES];
}


- (void)textFieldDidEndEditing:(UITextField *)textField
{
    [self animateTextField: textField up: NO];
}

- (void) animateTextField: (UITextField*) textField up: (BOOL) up
{
    const int movementDistance = 170; // tweak as needed
    const float movementDuration = 0.3f; // tweak as needed
    
    int movement = (up ? -movementDistance : movementDistance);
    
    [UIView beginAnimations: @"anim" context: nil];
    [UIView setAnimationBeginsFromCurrentState: YES];
    [UIView setAnimationDuration: movementDuration];
    self.view.frame = CGRectOffset(self.view.frame, 0, movement);
    [UIView commitAnimations];
}

#pragma mark Accessories
- (void) onBackAction {
    if (self.txtActive == self.txtPin) {
        [self.txtRut becomeFirstResponder];
    } else if (self.txtActive == self.txtRut) {
        [self.txtPhoneNumber becomeFirstResponder];
    }
}

- (void) onNextAction {
    if (self.txtActive == self.txtPhoneNumber) {
        [self.txtRut becomeFirstResponder];
    } else if (self.txtActive == self.txtRut) {
        [self.txtPin becomeFirstResponder];
    }
}

- (void) onDoneAction {
    [self onLogin:nil];
}

- (void) checkSurvey {
    if ([DataManager shared].surveyAlertShowed) {
        return;
    }
    
    if ([DataManager shared].surveyShouldLoad) {
        [self showSurveyAlert:nil];
    } else if ([DataManager shared].user &&
            [DataManager shared].numExecutions >= RULE_1 &&
            ![[NSUserDefaults standardUserDefaults] boolForKey:RULE_1_PREF] &&
            [DataManager shared].timer == nil) {
        [[DataManager shared] surveyTimer];
    }
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    
    switch (buttonIndex) {
        case 0:
            [self.leftView closeMenu];
            [self pushNewController:URL_SURVEY menuMode:self.menuMode title:_(@"survey_header") isRegister:NO isRecover:NO];
            break;
            
        case 1:
            //cancel survey
            break;
            
        default:
            break;
    }
}

- (void) showSurveyAlert:(id)sender {
    if(![DataManager shared].user)return;
    [DataManager shared].surveyAlertShowed = YES;
    [[NSUserDefaults standardUserDefaults] setObject:[[NSDate alloc] init] forKey:SURVEY_LAST_UPDATE_PREF];
    [[NSUserDefaults standardUserDefaults] synchronize];
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:nil
                                                    message:_(@"alertbox_text")
                                                   delegate:self
                                          cancelButtonTitle: nil
                                          otherButtonTitles:_(@"alertbox_ok_button"), _(@"alertbox_cancel_button"), nil];
    [alert show];
}

- (void) showAlert:(NSString*) msg {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:nil
                                                    message:msg
                                                   delegate:nil
                                          cancelButtonTitle:@"OK"
                                          otherButtonTitles:nil];
    [alert show];
}

- (IBAction)onInfo:(id)sender {
    UIStoryboard *mainStoryboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    OnBoardingViewController *onboardingVC = [mainStoryboard instantiateViewControllerWithIdentifier:@"OnBoardingViewController"];
    onboardingVC.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;
    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
    [self presentViewController:onboardingVC animated:YES completion:^{
//        appDelegate.window.rootViewController = onboardingVC;
    }];
}


@end
