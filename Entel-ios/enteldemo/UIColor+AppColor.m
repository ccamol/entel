//
//  UIColor+AppColor.m
//  enteldemo
//
//  Created by Alex Campayo on 7/11/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "UIColor+AppColor.h"

@implementation UIColor (AppColor)

+ (UIColor*)entelLightGray {
    return [UIColor colorWithRed:204/255.0 green:204/255.0 blue:204/255.0 alpha:1];
}

+ (UIColor*)entelOrange {
    return [UIColor colorWithRed:255/255.0 green:103/255.0 blue:2/255.0 alpha:1];
}

+ (UIColor*)entelBlue {
    return [UIColor colorWithRed:1/255.0 green:84/255.0 blue:160/255.0 alpha:1];
}

+ (UIColor*)entelDarkGray {
    return [UIColor colorWithRed:85/255.0 green:85/255.0 blue:85/255.0 alpha:1];
}

+ (UIColor*)entelDarkOrange {
    return [UIColor colorWithRed:190/255.0 green:61/255.0 blue:7/255.0 alpha:1];
}

+ (UIColor*)entelPlaceholder {
    return [UIColor colorWithRed:99/255.0 green:100/255.0 blue:102/255.0 alpha:1];
}

@end
