//
//  ViewController.h
//  enteldemo
//
//  Created by Matthew Mayer on 26/02/2014.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>
#import "WebViewController.h"
#import "LeftMenuManager.h"

@interface MainViewController : WebViewController<UITextFieldDelegate, UIAlertViewDelegate>
@property (nonatomic) NSString *url, *txtTitle;
@property (nonatomic, assign) NSInteger option;
@property (nonatomic) LeftMenuMode menuMode;
@property (nonatomic) BOOL isResgister, isRecover, isNotRegisteredUser;
- (void) setTitle:(NSString *) title;
-(void)logout;
@end
