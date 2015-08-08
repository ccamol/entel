//
//  LeftMenuManager.m
//  enteldemo
//
//  Created by Alex Campayo on 7/1/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "LeftMenuManager.h"
#import "DataManager.h"

@interface LeftMenuManager()
@property(nonatomic)NSArray *options;
@property(nonatomic)NSArray *data;
@end

#define kCC @"cc"
#define kSS @"ss"
#define kPP @"pp"

@implementation LeftMenuManager

+ (LeftMenuManager *)shared {
    static LeftMenuManager *shared = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        shared = [[LeftMenuManager alloc] init];
    });
    
    if (!shared.options) {
        [shared populateData];
    }
    
    return shared;
}

- (void)populateData {
    NSString *plistPath = [[NSBundle  mainBundle] pathForResource:@"leftMenu" ofType:@"plist"];
    self.data = [NSArray arrayWithContentsOfFile:plistPath];
    self.options = [self.data objectAtIndex:0];
}

- (NSArray*)getMenuOptions:(LeftMenuMode)menuMode {
    NSArray *optionIds = [self.data objectAtIndex:menuMode];
    self.currentMenuOptions = [NSMutableArray array];
    
    for (NSNumber *i in optionIds) {
        for (int x = 0; x < self.options.count; x++) {
            NSMutableDictionary *opt = [[self.options objectAtIndex:x] mutableCopy];
            if (opt[@"id"] == i) {
                if (opt[@"isDynamicUrl"]) {
                    [opt setObject:dynamicMenuOptUrl(opt[@"url"]) forKey:@"url"];
                }
                
                if ((menuMode == LeftMenuModeSS || menuMode == LeftMenuModeCC) && [opt[@"id"] intValue] == 2) {
                    if (DataManager.shared.user.aaa == 3) {
                        [self.currentMenuOptions addObject:opt];
                    }
                } else {
                    [self.currentMenuOptions addObject:opt];
                }
            }
        }
    }
    
    return self.currentMenuOptions;
}

- (LeftMenuMode)getMenuMode:(NSString*)key {
    if ([[key lowercaseString] isEqualToString:kCC]) {
        return LeftMenuModeCC;
    } else if ([[key lowercaseString] isEqualToString:kSS]) {
        return LeftMenuModeSS;
    } else if ([[key lowercaseString] isEqualToString:kPP]) {
        return LeftMenuModePP;
    }
    return LeftMenuModeNoLogin;
}

@end
