//
//  Accessories.m
//  enteldemo
//
//  Created by Juan-Manuel Fluxá on 8/18/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "Accessories.h"

@implementation Accessories

+ (UIView*) forTextFieldInputFormWithTarget:(id)target backAction:(SEL)backSel nextAct:(SEL)nextSel doneAction:(SEL)doneSel {
    UIView *inputAccView = [[UIView alloc] initWithFrame:CGRectMake(0.0, 0, 320.0, 44.0)];
    [inputAccView setBackgroundColor:[UIColor colorWithRed:241.0f/255.0f green:241.0f/255.0f blue:241.0f/255.0f alpha:1.0f]];
    
    UIButton *btBack = [[UIButton alloc] initWithFrame:CGRectMake(0.0f, 0.0f, 44.0f, 44.0f)];
    [btBack setImage:[UIImage imageNamed:@"tf_bt_back_a"] forState:UIControlStateNormal];
    [btBack setImage:[UIImage imageNamed:@"tf_bt_back_b"] forState:UIControlStateHighlighted];
    [btBack setEnabled:NO];
    if (backSel != nil) {
        [btBack addTarget:target action:backSel forControlEvents:UIControlEventTouchUpInside];
        [btBack setEnabled:YES];
    }
    
    
    UIButton *btNext = [[UIButton alloc] initWithFrame:CGRectMake(44.0f, 0.0f, 44.0f, 44.0f)];
    [btNext setImage:[UIImage imageNamed:@"tf_bt_next_a"] forState:UIControlStateNormal];
    [btNext setImage:[UIImage imageNamed:@"tf_bt_next_b"] forState:UIControlStateHighlighted];
    [btNext setEnabled:NO];
    if (nextSel != nil) {
        [btNext addTarget:target action:nextSel forControlEvents:UIControlEventTouchUpInside];
        [btNext setEnabled:YES];
    }
    
    UIButton *btDone = [[UIButton alloc] initWithFrame:CGRectMake(320.0f-80.0f, 0, 44.0f, 44.0f)];
    [btDone setTitle:@"Done" forState:UIControlStateNormal];
    [btDone setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [btDone setTitleColor:[UIColor grayColor] forState:UIControlStateHighlighted];
    [btDone setTitleColor:[UIColor grayColor] forState:UIControlStateDisabled];
    [btDone setEnabled:NO];
    if (doneSel != nil) {
        [btDone addTarget:target action:doneSel forControlEvents:UIControlEventTouchUpInside];
        [btDone setEnabled:YES];
    }
    
    [inputAccView addSubview:btBack];
    [inputAccView addSubview:btNext];
    [inputAccView addSubview:btDone];
    
    return inputAccView;
}

@end
