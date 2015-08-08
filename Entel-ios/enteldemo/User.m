//
//  User.m
//  enteldemo
//
//  Created by Alex Campayo on 7/14/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "User.h"

@implementation User

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"estado":@"estado",
             @"market":@"mercado",
             @"owner":@"titular",
             @"name":@"nombre",
             @"aaa":@"aaa"
    };
}

- (id) initWithCoder:(NSCoder *)coder {
    
    self = [super initWithCoder:coder];
    
    if (![coder containsValueForKey:@"aaa"]) {
        self.aaa = -1; // Unassigned default value
    }
    
    return self;
}

@end
