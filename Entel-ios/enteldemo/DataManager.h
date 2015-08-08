//
//  DataManager.h
//  enteldemo
//
//  Created by Alex Campayo on 7/14/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "User.h"
#import "LeftViewController.h"

@interface DataManager : NSObject
@property(nonatomic) User *user;
@property(nonatomic) BOOL surveyAlertShowed;
@property(nonatomic) NSString *cKey, *loadOpenUrl, *loadOpenUrlTitle;
@property(nonatomic) NSTimer *timer;

+ (DataManager *)shared;
- (User*)getUser;
- (id)unarchiveObjectforKey:(NSString *)key;
- (void)archiveObject:(id)anObject forKey:(NSString *)key;
- (void)deleteArchiveForKey:(NSString *)key;
- (void)login:(User *)user;
- (void)logout;
- (NSInteger) numExecutions;
- (void) incrementNumExecutions;
- (void) surveyTimer;
- (BOOL) surveyShouldLoad;

@end
