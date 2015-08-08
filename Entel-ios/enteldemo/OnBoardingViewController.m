//
// Created by Tuo on 6/18/15.
// Copyright (c) 2015 ReignDesign. All rights reserved.
//

#import "OnBoardingViewController.h"
#import "AppDelegate.h"

@interface OnBoardingViewController () <UIScrollViewDelegate>
@property (weak, nonatomic) IBOutlet UIScrollView *scrollView;
@property (weak, nonatomic) IBOutlet UIPageControl *pageControl;
@property (weak, nonatomic) IBOutlet UIView *statusView;

@property(nonatomic) UIStatusBarStyle oldStatusBarStyle;
@end

#define kSlidePageCount 4

@implementation OnBoardingViewController {

}

- (void)viewDidLoad {
    [super viewDidLoad];



    for (int i = 0; i < kSlidePageCount; i++) {
        CGRect frame;
        frame.origin.x = self.scrollView.frame.size.width * i;
        frame.origin.y = 0;
        frame.size = self.scrollView.frame.size;

        UIImageView *imageView = [[UIImageView alloc] initWithFrame:frame];
        NSString *imgName = [NSString stringWithFormat:@"onboarding%d", (i + 1)];
        imageView.image = [UIImage imageNamed:imgName];
        [self.scrollView addSubview:imageView];
    }


    self.scrollView.contentSize = CGSizeMake(self.scrollView.frame.size.width * kSlidePageCount+ 1.0, self.scrollView.frame.size.height);
    self.pageControl.numberOfPages = (kSlidePageCount + 1);
    self.pageControl.currentPageIndicatorTintColor = [UIColor entelOrange];
    self.pageControl.pageIndicatorTintColor = [UIColor whiteColor];
    
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    self.oldStatusBarStyle = [UIApplication sharedApplication].statusBarStyle;
    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    [[UIApplication sharedApplication] setStatusBarStyle:self.oldStatusBarStyle];
}


- (void)scrollViewDidScroll:(UIScrollView *)sender {
    // Update the page when more than 50% of the previous/next page is visible
    CGFloat pageWidth = self.scrollView.frame.size.width;
    CGFloat offsetX = self.scrollView.contentOffset.x;
    int page = floor((offsetX - pageWidth / 2) / pageWidth) + 1;
    NSLog(@"page: %d, self.scrollView.contentOffset.x: %.2f", page, offsetX);
    if(offsetX > (kSlidePageCount - 1) * pageWidth){
        //dismiss
        [self onClose:nil];
    }
    self.pageControl.currentPage = page;
}

- (IBAction)onClose:(id)sender {
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    [userDefaults setBool:YES forKey:@"OnBoardingDidPlay"];
    [userDefaults synchronize];

    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
    if(appDelegate.window.rootViewController == appDelegate.revealController){
        [self dismissViewControllerAnimated:YES completion:nil];
    }else{
        appDelegate.revealController.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;
        [self presentViewController:appDelegate.revealController animated:YES completion:^{
            appDelegate.window.rootViewController = appDelegate.revealController;
        }];
    }
}

@end