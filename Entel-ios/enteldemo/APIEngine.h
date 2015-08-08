//
//  APIEngine.h
//  enteldemo
//
//  Created by Alex Campayo on 6/27/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import "MKNetworkEngine.h"
#import "User.h"

typedef void(^ErrorBlock)(NSError *error);
typedef void (^LoginSuccessBlock)(User*);
typedef void (^SuccessBlock)(void);
typedef void (^TokenSuccessBlock)(NSString*);
typedef void (^RegisterBlock)(void);

@interface APIEngine : MKNetworkEngine
+ (APIEngine *)shared;
-(void)login:(NSString *)token successBlock:(LoginSuccessBlock)successBlock errorBlock:(ErrorBlock)errorBlock;
-(void)getToken:(NSString *)key successBlock:(TokenSuccessBlock)successBlock errorBlock:(ErrorBlock)errorBlock registerBlock:(RegisterBlock)registerBlock;
-(void)logoutSuccessBlock:(SuccessBlock)successBlock errorBlock:(ErrorBlock)errorBlock;
@end
