//
//  AppDelegate.h
//  enteldemo
//
//  Created by Matthew Mayer on 26/02/2014.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <PKRevealController/PKRevealController.h>
@interface AppDelegate : UIResponder <UIApplicationDelegate,PKRevealing>

@property (strong, nonatomic) UIWindow *window;
@property (strong, nonatomic) UIViewController *revealController;
@end
