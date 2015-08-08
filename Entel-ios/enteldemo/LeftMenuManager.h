//
//  LeftMenuManager.h
//  enteldemo
//
//  Created by Alex Campayo on 7/1/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum : NSUInteger
{
    LeftMenuModeNoLogin = 1,
    LeftMenuModeSS = 2,
    LeftMenuModeCC = 3,
    LeftMenuModePP = 4,
} LeftMenuMode;

@interface LeftMenuManager : NSObject
@property(nonatomic)NSMutableArray *currentMenuOptions;
+ (LeftMenuManager *)shared;
- (NSArray*)getMenuOptions:(LeftMenuMode)menuMode;
- (LeftMenuMode)getMenuMode:(NSString*)key;
@end
