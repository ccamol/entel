//
//  LeftViewController.m
//  enteldemo
//
//  Created by Matthew Mayer on 26/02/2014.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "LeftViewController.h"
#import <PKRevealController/PKRevealController.h>
#import "LeftMenuCell.h"
#import "Constants.h"
#import "MainViewController.h"
#import "User.h"
#import "DataManager.h"
#import "APIEngine.h"
#import <SVProgressHUD/SVProgressHUD.h>

@interface LeftViewController ()
@property(nonatomic) IBOutlet UIView *userInfoBar;
@property(nonatomic) IBOutlet UITableView *menu;
@property(nonatomic) LeftMenuMode currentMode;
@property(nonatomic, weak) IBOutlet UIView *userView;
@property(nonatomic, weak) IBOutlet UIView *titleView;
@property(nonatomic) NSInteger infoBarHeight, menuHeight;
@property(nonatomic, weak) IBOutlet UILabel *name;
@property(nonatomic, weak) IBOutlet UILabel *owner;
@property(nonatomic, weak) IBOutlet UILabel *ownerLabel;
@property(nonatomic, weak) IBOutlet UILabel *openingBrace;
@property(nonatomic, weak) IBOutlet UIButton *btnLogout;

@end

@implementation LeftViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.selectedOpt = 0;
    
    if (!IS_CLASSIC) {
        [self setupUserInfoBar];
    }
    
    self.infoBarHeight = self.userInfoBar.frame.size.height;
    self.menuHeight = self.menu.frame.size.height;
    [self.revealController setMinimumWidth:260.0 maximumWidth:260.0 forViewController:self];
}

-(void)loadUser {
    User *user = [[DataManager shared] unarchiveObjectforKey:KEY_USER];
    self.owner.text = user.owner;
    self.name.text = user.name;
    self.ownerLabel.text = _(@"ownerLabel");
    
    BOOL empty = [user.owner stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]].length == 0
                    && [user.name stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]].length == 0;
    
    self.owner.hidden = empty;
    self.name.hidden = empty;
    self.ownerLabel.hidden = user.owner.length == 0;
    self.openingBrace.hidden = empty;
    
    CGRect frame = self.userInfoBar.frame;
    CGRect menuFrame = self.menu.frame;
    
    if (empty && !self.userView.hidden) {
        NSInteger emptySpace = 45;
        frame.size.height = self.infoBarHeight - emptySpace;
        menuFrame.size.height = self.menuHeight + emptySpace;
    }
    
    self.userInfoBar.frame = frame;
    menuFrame.origin.y = CGRectGetMaxY(self.userInfoBar.frame);
    self.menu.frame = menuFrame;
}

- (void)setupUserInfoBar {
    CGFloat h = [UIApplication sharedApplication].statusBarFrame.size.height;
    CGRect frame = self.userInfoBar.frame;
    frame.size.height += h;
    self.userInfoBar.frame = frame;
    
    frame = self.titleView.frame;
    frame.size.height += h;
    self.titleView.frame = frame;
    
    frame = self.menu.frame;
    frame.size.height -= h;
    self.menu.frame = frame;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.leftMenuItems.count;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    LeftMenuCell *cell = [tableView dequeueReusableCellWithIdentifier:@"LeftMenuCell" forIndexPath:indexPath];
    cell.label.text = _(self.leftMenuItems[indexPath.row][@"title"]);
    
    UIImage *icon = [UIImage imageNamed:[self.leftMenuItems objectAtIndex:indexPath.row][@"icon"]];
    if (icon ) {
        cell.img.image = icon;
    } else {
        cell.img.image = [UIImage imageNamed:@"ic1"];
    }
    
    if (self.selectedOpt == indexPath.row) {
        [tableView selectRowAtIndexPath:indexPath animated:YES scrollPosition:UITableViewScrollPositionNone];
    }
    
    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    UINavigationController *nc = (UINavigationController *)self.revealController.frontViewController;
    
    if (self.selectedOpt == indexPath.row) {
        return;
    }
    
    NSString *url = [self.leftMenuItems objectAtIndex:indexPath.row][@"url"];
    
    if ([url rangeOfString:@"recarga=WebPay"].location != NSNotFound) {
        [tableView selectRowAtIndexPath:[NSIndexPath indexPathForRow:self.selectedOpt inSection:0] animated:NO scrollPosition:UITableViewScrollPositionNone];
        [tableView deselectRowAtIndexPath:indexPath animated:NO];
        NSURL *urlObj = [NSURL URLWithString:url];
        [[UIApplication sharedApplication] openURL:urlObj];
        return;
    }
    
    self.selectedOpt = indexPath.row;
    BOOL isLogin = self.currentMode == LeftMenuModeNoLogin;
    NSInteger i = isLogin ? 0 : nc.viewControllers.count - 1;
    [(MainViewController *)[nc.viewControllers objectAtIndex:i] loadURL:url];
    [(MainViewController *)[nc.viewControllers objectAtIndex:i] setOption:indexPath.row];

    [self.revealController showViewController:self.revealController.frontViewController animated:YES completion:^(BOOL finished) {}];
    
    //Navigation bar title
    NSString *title = isLogin && self.selectedOpt == 0 ? @"" : _([self.leftMenuItems objectAtIndex:indexPath.row][@"title"]);
    [(MainViewController *)[nc.viewControllers objectAtIndex:i] setTitle:title];
}

- (void) closeMenu {
    [self.revealController showViewController:self.revealController.frontViewController animated:YES completion:^(BOOL finished) {}];
}

- (IBAction)onLogout:(id)sender {
    [SVProgressHUD showWithMaskType:SVProgressHUDMaskTypeBlack];
    [[APIEngine shared] logoutSuccessBlock:^(void) {
        UINavigationController *nc = (UINavigationController *)self.revealController.frontViewController;
        [(MainViewController *)[nc.viewControllers objectAtIndex:0] logout];
        [self.revealController showViewController:self.revealController.frontViewController animated:YES completion:^(BOOL finished) {
            [self reloadMenu:LeftMenuModeNoLogin];
            self.selectedOpt = 0;
            NSIndexPath *auxIndexPath = [NSIndexPath indexPathForRow:0 inSection:0];
            [self.menu selectRowAtIndexPath:auxIndexPath animated:YES scrollPosition:UITableViewScrollPositionNone];
        }];
        [SVProgressHUD dismiss];
    } errorBlock:^(NSError *error) {
        [SVProgressHUD dismiss];
        [SVProgressHUD showErrorWithStatus:[error localizedDescription]];
    }];
}

- (void)reloadMenu:(LeftMenuMode)menuMode {
    self.currentMode = menuMode;
    self.leftMenuItems = [[LeftMenuManager shared] getMenuOptions:menuMode];
    [self userViewHidden:menuMode == LeftMenuModeNoLogin];
    [self.menu reloadData];
    [self loadUser];
}

- (void) userViewHidden:(BOOL)hidden {

    self.userView.hidden = hidden;
    CGRect frame = self.userInfoBar.frame;
    CGRect menuFrame = self.menu.frame;
    
    if (hidden) {
        frame.size.height = self.infoBarHeight - self.userView.frame.size.height;
        menuFrame.size.height = self.menuHeight + self.userView.frame.size.height;
    } else {
        frame.size.height = self.infoBarHeight;
        menuFrame.size.height = self.menuHeight;
    }
    
    self.userInfoBar.frame = frame;
    menuFrame.origin.y = CGRectGetMaxY(self.userInfoBar.frame);
    self.menu.frame = menuFrame;
}

@end
