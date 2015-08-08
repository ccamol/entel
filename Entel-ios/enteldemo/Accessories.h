//
//  Accessories.h
//  enteldemo
//
//  Created by Juan-Manuel Fluxá on 8/18/14.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Accessories : NSObject

+ (UIView*) forTextFieldInputFormWithTarget:(id)target backAction:(SEL)backSel nextAct:(SEL)nextSel doneAction:(SEL)doneSel;
@end
