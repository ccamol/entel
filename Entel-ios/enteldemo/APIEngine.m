//
//  APIEngine.m
//  enteldemo
//
//  Created by Alex Campayo on 6/27/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "APIEngine.h"
#define STATUS_SUCCESS 0
#define STATUS_REGISTER 2
#define STATUS_UNKNOWN_ERROR 99

@interface APIEngine ()
@property(nonatomic) MKNetworkEngine *engine;
@property(nonatomic) NSURLCredential *credential;
@end

@implementation APIEngine

+ (APIEngine *)shared {
    static APIEngine *shared = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        shared = [[APIEngine alloc] init];
    });
    
    if (!shared.credential) {
        shared.credential = [NSURLCredential credentialWithUser:CREDENTIAL_USER
                                                 password:CREDENTIAL_PSW
                                              persistence:NSURLCredentialPersistenceForSession];
    }
    
    return shared;
}

-(MKNetworkEngine *)engine {
    //lazy create engine
    if (_engine==nil) {
        _engine = [[MKNetworkEngine alloc] initWithHostName:ENTEL_DOMAIN apiPath:ENTEL_API customHeaderFields:@{}];
    }
    
    return _engine;
}

-(NSError *)errorForOperation:(MKNetworkOperation *)completedOperation errorMessage:(NSString*)errorMessage{
    NSError *newError = [NSError errorWithDomain:ENTEL_DOMAIN code:completedOperation.readonlyResponse.statusCode userInfo:@{NSLocalizedDescriptionKey:errorMessage}];
    return newError;
}

-(void)getToken:(NSString *)key successBlock:(TokenSuccessBlock)successBlock errorBlock:(ErrorBlock)errorBlock registerBlock:(RegisterBlock)registerBlock {
    MKNetworkOperation *op = [self.engine operationWithPath:@"signin.action"
                                                     params:@{@"key":key}
                                                 httpMethod:@"POST"
                                                        ssl:NO];
    
    op.postDataEncoding = MKNKPostDataEncodingTypeJSON;
    op.authHandler = ^(NSURLAuthenticationChallenge *challenge) {
        [challenge.sender useCredential:self.credential forAuthenticationChallenge:challenge];
    };
    
    [op addCompletionHandler:^(MKNetworkOperation *completedOperation) {
        NSDictionary *response = (NSDictionary*)[completedOperation responseJSON];
        NSInteger status = [response[@"estado"] integerValue];
        
        if (status == STATUS_SUCCESS) {
            successBlock(response[@"token"]);
        } else if (status == STATUS_REGISTER) {
            registerBlock();
        } else if (status == STATUS_UNKNOWN_ERROR) {
            errorBlock([self errorForOperation:completedOperation errorMessage:_(@"unknown_error")]);
        } else {
            errorBlock([self errorForOperation:completedOperation errorMessage:_(@"login_error")]);
        }
    } errorHandler:^(MKNetworkOperation *completedOperation, NSError *error) {
        errorBlock(error);
    }];
    
    [self.engine enqueueOperation:op];
}

-(void)login:(NSString *)token successBlock:(LoginSuccessBlock)successBlock errorBlock:(ErrorBlock)errorBlock {
    MKNetworkOperation *op = [self.engine operationWithPath:@"signin.action"
                                                     params:@{@"login":token}
                                                 httpMethod:@"POST"
                                                        ssl:NO];
    
    op.postDataEncoding = MKNKPostDataEncodingTypeJSON;
    op.authHandler = ^(NSURLAuthenticationChallenge *challenge) {
        [challenge.sender useCredential:self.credential forAuthenticationChallenge:challenge];
    };
    
    [op addCompletionHandler:^(MKNetworkOperation *completedOperation) {
        NSDictionary *response = (NSDictionary*)[completedOperation responseJSON];
        NSInteger status = [response[@"estado"] integerValue];
        
        if (status == STATUS_SUCCESS) {
            
            NSHTTPCookie *sessionCookie = [self cookieByName:@"JSESSIONID"];
            NSHTTPCookie *userCookie = [self cookieByName:@"esaCookie"];
            
            NSMutableDictionary *cookieProperties = [NSMutableDictionary dictionary];
            [cookieProperties setObject:sessionCookie.name forKey:NSHTTPCookieName];
            [cookieProperties setObject:sessionCookie.value forKey:NSHTTPCookieValue];
            [cookieProperties setObject:sessionCookie.domain forKey:NSHTTPCookieDomain];    // Without http://
            [cookieProperties setObject:sessionCookie.domain forKey:NSHTTPCookieOriginURL]; // Without http://
            [cookieProperties setObject:sessionCookie.path forKey:NSHTTPCookiePath];
            [cookieProperties setObject:[NSNumber numberWithInt:sessionCookie.version] forKey:NSHTTPCookieVersion];
            [cookieProperties setObject:userCookie.expiresDate forKey:NSHTTPCookieExpires];
            
            NSHTTPCookie *cookie = [NSHTTPCookie cookieWithProperties:cookieProperties];
            [[NSHTTPCookieStorage sharedHTTPCookieStorage] setCookie:cookie];
            
            NSError *error = nil;
            User *user = [MTLJSONAdapter modelOfClass:User.class fromJSONDictionary:response error:&error];
            successBlock(user);
        } else if (status == STATUS_UNKNOWN_ERROR) {
            errorBlock([self errorForOperation:completedOperation errorMessage:_(@"unknown_error")]);
        } else {
            errorBlock([self errorForOperation:completedOperation errorMessage:_(@"login_error")]);
        }
    } errorHandler:^(MKNetworkOperation *completedOperation, NSError *error) {
        errorBlock(error);
    }];
    
    [self.engine enqueueOperation:op];
}

-(NSHTTPCookie *)cookieByName:(NSString *)name {
    NSHTTPCookie *cookie;
    NSHTTPCookieStorage *storage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    
    for (cookie in [storage cookies]) {
        if ([cookie.name isEqualToString:name]) {
            return cookie;
        }
    }
    
    return nil;
}

-(void)logoutSuccessBlock:(SuccessBlock)successBlock errorBlock:(ErrorBlock)errorBlock {
    MKNetworkOperation *op = [self.engine operationWithPath:@"signout.action"
                                                     params:@{}
                                                 httpMethod:@"POST"
                                                        ssl:NO];
    
    op.postDataEncoding = MKNKPostDataEncodingTypeJSON;
    op.authHandler = ^(NSURLAuthenticationChallenge *challenge) {
        [challenge.sender useCredential:self.credential forAuthenticationChallenge:challenge];
    };
    
    [op addCompletionHandler:^(MKNetworkOperation *completedOperation) {
        successBlock();
    } errorHandler:^(MKNetworkOperation *completedOperation, NSError *error) {
        errorBlock(error);
    }];
    
    [self.engine enqueueOperation:op];
}

@end
