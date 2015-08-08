//
//  AppDelegate.m
//  enteldemo
//
//  Created by Matthew Mayer on 26/02/2014.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "AppDelegate.h"
#import "MyRevealViewController.h"
#import "DataManager.h"
#import "OnBoardingViewController.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    [self setupURLCache];
    
    UIWebView *webView = [[UIWebView alloc]initWithFrame:CGRectZero];
    NSString *currentUA = [webView stringByEvaluatingJavaScriptFromString:@"navigator.userAgent"];
    NSString *entelUA = [NSString stringWithFormat:@"%@%@", currentUA, USER_AGENT];
    
    NSDictionary *dictionary = [NSDictionary dictionaryWithObjectsAndKeys:entelUA, @"UserAgent", nil];
    [[NSUserDefaults standardUserDefaults] registerDefaults:dictionary];
    
    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
    // Override point for customization after application launch.
    self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
    UIStoryboard *mainStoryboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    UINavigationController *frontNavigationController = [mainStoryboard instantiateViewControllerWithIdentifier:@"MainNavigation"];
    [frontNavigationController setNavigationBarHidden:YES animated:NO];
    UIViewController *left = [mainStoryboard instantiateViewControllerWithIdentifier:@"left"];
    MyRevealViewController *revealController = [MyRevealViewController revealControllerWithFrontViewController:frontNavigationController leftViewController:left];
    revealController.delegate = self;
    self.revealController = revealController;

    BOOL onBoardingDidPlay = [[NSUserDefaults standardUserDefaults] boolForKey:@"OnBoardingDidPlay"];
    if(onBoardingDidPlay){
        self.window.rootViewController = revealController;
    }else{
        OnBoardingViewController *onboardingVC = [mainStoryboard instantiateViewControllerWithIdentifier:@"OnBoardingViewController"];
        self.window.rootViewController = onboardingVC;
    }

    [self.window makeKeyAndVisible];
    self.window.backgroundColor = [UIColor blackColor];
    return YES;
}

- (void)setupURLCache {
    NSURLCache *URLCache = [[NSURLCache alloc] initWithMemoryCapacity:4 * 1024 * 1024
                                                         diskCapacity:20 * 1024 * 1024
                                                             diskPath:nil];
    [NSURLCache setSharedURLCache:URLCache];
}
							
- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
    [self.window endEditing:YES];
}

- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
