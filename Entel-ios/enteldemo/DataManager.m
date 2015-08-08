//
//  DataManager.m
//  enteldemo
//
//  Created by Alex Campayo on 7/14/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "DataManager.h"
#import "Constants.h"
#import "User.h"

//if you increment this, all previous data will be lost
#define DATASTORE_VERSION @"1"

@implementation DataManager

+ (DataManager *)shared {
    static DataManager *shared = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        shared = [[DataManager alloc] init];
        shared.user = [shared unarchiveObjectforKey:KEY_USER];
    });
    return shared;
}

- (User*)getUser {
    self.user = [self unarchiveObjectforKey:KEY_USER];
    return self.user;
}

- (void)login:(User *)user {
    self.user = user;
    [self archiveObject:user forKey:KEY_USER];
}

- (void)logout {
    [self.timer invalidate];
    self.timer = nil;
    [[NSUserDefaults standardUserDefaults] removeObjectForKey:@"NUM_EXECUTIONS_PREF"];
    [[NSUserDefaults standardUserDefaults] removeObjectForKey:@"RULE_1_PREF"];
    [[NSUserDefaults standardUserDefaults] removeObjectForKey:@"SURVEY_LAST_UPDATE_PREF"];
    [self deleteArchiveForKey:KEY_USER];
}

- (NSInteger) numExecutions {
    return [[NSUserDefaults standardUserDefaults] integerForKey:NUM_EXECUTIONS_PREF];
}

- (void) incrementNumExecutions {
    int numExecutions = [self numExecutions];
    
    if (numExecutions <= RULE_1) {
        [[NSUserDefaults standardUserDefaults] setInteger:[self numExecutions] + 1 forKey:NUM_EXECUTIONS_PREF];
        [[NSUserDefaults standardUserDefaults] synchronize];
    }
    
    NSLog(@"NUM: %ld", (long)[self numExecutions]);
}

- (void) surveyTimer {
    self.timer = [NSTimer scheduledTimerWithTimeInterval:SURVEY_TIMER_SECONDS
                                     target:self
                                   selector:@selector(finishSurveyTimer)
                                   userInfo:nil
                                    repeats:NO];
}

- (BOOL) surveyShouldLoad {
    NSDate *lastUpdate = (NSDate*)[[NSUserDefaults standardUserDefaults] objectForKey:SURVEY_LAST_UPDATE_PREF];
    long days = 0;

    if (lastUpdate != nil) {
        NSCalendar *gregorianCalendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];
        NSDateComponents *components = [gregorianCalendar components:NSDayCalendarUnit
                                                            fromDate:lastUpdate
                                                              toDate:[[NSDate alloc]init]
                                                             options:0];
        
        days = [components day];
    }
    
    return days >= SURVEY_DAYS;
}

- (void) finishSurveyTimer {
    [self.timer invalidate];
    self.timer = nil;
    [[NSUserDefaults standardUserDefaults] setBool:YES forKey:RULE_1_PREF];
    [[NSUserDefaults standardUserDefaults] synchronize];
    [[NSNotificationCenter defaultCenter] postNotificationName:SURVEY_ALERTBOX_NOTIF object:nil];
}

- (id)unarchiveObjectforKey:(NSString *)key {
	NSArray *path = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	NSString *documentsDirectory = path[0];
    NSString *versionedKey = [key stringByAppendingString:DATASTORE_VERSION];
	NSString *file = [documentsDirectory stringByAppendingPathComponent:versionedKey];
	id anObject = nil;
	if ([[NSFileManager defaultManager] fileExistsAtPath:file]) {
		NSData *data = [[NSData alloc] initWithContentsOfFile:file];
		NSKeyedUnarchiver *unarchiver = [[NSKeyedUnarchiver alloc] initForReadingWithData:data];
		anObject = [unarchiver decodeObjectForKey:versionedKey];
		[unarchiver finishDecoding];
	}
	return anObject;
}

- (void)archiveObject:(id)anObject forKey:(NSString *)key {
	NSArray *path = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	NSString *documentsDirectory = path[0];
    NSString *versionedKey = [key stringByAppendingString:DATASTORE_VERSION];
	NSString *file = [documentsDirectory stringByAppendingPathComponent:versionedKey];
	NSMutableData *data = [[NSMutableData alloc] init];
	NSKeyedArchiver *archiver = [[NSKeyedArchiver alloc] initForWritingWithMutableData:data];
	[archiver encodeObject:anObject forKey:versionedKey];
	[archiver finishEncoding];
	[data writeToFile:file atomically:YES];
}

-(void)deleteArchiveForKey:(NSString *)key {
    NSArray *path = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	NSString *documentsDirectory = path[0];
    NSString *versionedKey = [key stringByAppendingString:DATASTORE_VERSION];
	NSString *file = [documentsDirectory stringByAppendingPathComponent:versionedKey];
	[[NSFileManager defaultManager] removeItemAtPath:file error:nil];
}

@end
