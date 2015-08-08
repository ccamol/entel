//
//  ARC4.m
//  enteldemo
//
//  Created by Alex Campayo on 6/27/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "ARC4.h"
#import <CommonCrypto/CommonCryptor.h>

@implementation ARC4

+ (NSString*) decrypt:(NSString*)cipherText {
    return [self encryptDecrypt:cipherText withKey:[self hexKey] operation:kCCDecrypt];
}

+ (NSString*) encrypt:(NSString*)cipherText {
    NSString *txt = [self hexadecimalString:[cipherText dataUsingEncoding:NSUTF8StringEncoding]];
    return [self encryptDecrypt:txt withKey:[self hexKey] operation:kCCEncrypt];
}

+ (NSMutableString *)hexKey {
    const char rawKey[] = "mzzo";
    
    NSMutableString *key = [NSMutableString stringWithCapacity:(sizeof(rawKey) * 2)];
    for (size_t i = 0; i < sizeof(rawKey) - 1; ++i) {
        [key appendFormat:@"%02X", (unsigned char)rawKey[i]];
    }
    
    return key;
}

static NSData *hexStringToBytes(NSString *string) {
    NSMutableData *data = [NSMutableData data];
    for (int i = 0; i + 2 <= string.length; i += 2) {
        uint8_t value = (uint8_t)strtol([[string substringWithRange:NSMakeRange(i, 2)] UTF8String], 0, 16);
        [data appendBytes:&value length:1];
    }
    return data;
}

+ (NSString *)encryptDecrypt:(NSString *)string withKey:(NSString *)key operation:(CCOperation)operation {
    NSData *inBytes = hexStringToBytes(string);
    NSData *keyBytes = hexStringToBytes(key);
    
    NSMutableData *outBytes = [NSMutableData dataWithLength:[inBytes length]];
    size_t dataOutMoved = 0;
    
    CCCryptorStatus ccStatus = CCCrypt(operation,
                                       kCCAlgorithmRC4,
                                       0,
                                       [keyBytes bytes],
                                       [keyBytes length],
                                       NULL, // iv
                                       [inBytes bytes],
                                       [inBytes length],
                                       [outBytes mutableBytes],
                                       [outBytes length],
                                       &dataOutMoved);
    
    if (ccStatus == kCCSuccess) {
        if (operation == kCCEncrypt) {
            return [self hexadecimalString:outBytes];
        } else {
            return [[NSString alloc] initWithData:outBytes encoding:NSUTF8StringEncoding];
        }
    }
    
    return string;
}

+ (NSString *)hexadecimalString:(NSData*)data {
    const unsigned char *dataBuffer = (const unsigned char *)[data bytes];
    
    if (!dataBuffer)
        return [NSString string];
    
    NSUInteger          dataLength  = [data length];
    NSMutableString     *hexString  = [NSMutableString stringWithCapacity:(dataLength * 2)];
    
    for (int i = 0; i < dataLength; ++i)
        [hexString appendString:[NSString stringWithFormat:@"%02lx", (unsigned long)dataBuffer[i]]];
    
    return [NSString stringWithString:hexString];
}

@end
