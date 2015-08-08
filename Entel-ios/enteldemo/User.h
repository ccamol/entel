//
//  User.h
//  enteldemo
//
//  Created by Alex Campayo on 7/14/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <Mantle/Mantle.h>

@interface User : MTLModel<MTLJSONSerializing>
@property(nonatomic) NSInteger estado;
@property(nonatomic) NSInteger aaa;
@property(nonatomic) NSInteger bbb;
@property(nonatomic, copy) NSString *market;
@property(nonatomic, copy) NSString *owner;
@property(nonatomic, copy) NSString *name;

+ (NSDictionary *)JSONKeyPathsByPropertyKey;

@end
