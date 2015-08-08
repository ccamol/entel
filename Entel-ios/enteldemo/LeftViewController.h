//
//  LeftViewController.h
//  enteldemo
//
//  Created by Matthew Mayer on 26/02/2014.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "LeftMenuManager.h"

@interface LeftViewController : UIViewController<UITableViewDataSource,UITableViewDelegate>
@property(nonatomic)NSArray *leftMenuItems;
@property(nonatomic) NSInteger selectedOpt;
- (void)loadUser;
- (IBAction)onLogout:(id)sender;
- (void)reloadMenu:(LeftMenuMode)menuMode;
- (void) closeMenu;
@end
