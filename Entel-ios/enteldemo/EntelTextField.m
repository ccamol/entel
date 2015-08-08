//
//  EntelTextField.m
//  enteldemo
//
//  Created by Alex Campayo on 7/11/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import "EntelTextField.h"

@implementation EntelTextField

-(id)initWithCoder:(NSCoder *)aDecoder {
    self = [super initWithCoder:aDecoder];
    if (self) {
        CGRect frameRect = self.frame;
        frameRect.size.height = 40;
        self.frame = frameRect;
    }
    return self;
}

-(CGRect)textRectForBounds:(CGRect)bounds {
    return CGRectInset(bounds, 13, 0);
}

-(CGRect)editingRectForBounds:(CGRect)bounds {
    return CGRectInset(bounds, 13, 0);
}

@end
