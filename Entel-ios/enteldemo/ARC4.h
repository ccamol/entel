//
//  ARC4.h
//  enteldemo
//
//  Created by Alex Campayo on 6/27/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ARC4 : NSObject
+ (NSString*) decrypt:(NSString*)cipherText;
+ (NSString*) encrypt:(NSString*)cipherText;
@end
