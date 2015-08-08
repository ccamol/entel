//
//  Constants.h
//  enteldemo
//
//  Created by Matthew Mayer on 26/02/2014.
//  Copyright (c) 2014 ReignDesign. All rights reserved.
//

/* IS_CLASSIC is true on pre-iOS7 devices*/
#define IS_CLASSIC ([[[[[UIDevice currentDevice] systemVersion] componentsSeparatedByString:@"."] objectAtIndex:0] intValue] < 7)

//Credentials
#define CREDENTIAL_USER @"webuser"
#define CREDENTIAL_PSW @"entel123"

#define KEY_USER @"user"
#define USER_AGENT @"_mientelApp"

#define _(str) (NSLocalizedString(str,@""))
#define dynamicMenuOptUrl(str) ([NSString stringWithFormat:@"%@%@/%@/%@", ENTEL_PROTOCOL, ENTEL_DOMAIN, ENTEL_API, str])

#ifndef enteldemo_Constants_h
#define enteldemo_Constants_h

//#define ENTEL_DOMAIN @"testing.mzzo.cl:7010"
//#define ENTEL_DOMAIN @"appswlsdesa.entel.cl"
//#define ENTEL_DOMAIN @"appswlstest.entel.cl"
#define ENTEL_DOMAIN @"appswlspre.entel.cl"
//#define ENTEL_DOMAIN @"appswls.entel.cl"

#define VERSION_CODE @"v1.18"

#define ENTEL_API @"mientel"
#define ENTEL_PROTOCOL @"http://"

#define URL_REGISTER [NSString stringWithFormat:@"%@%@/%@/registro.action?app=1", ENTEL_PROTOCOL, ENTEL_DOMAIN, ENTEL_API]
#define URL_RECOVER [NSString stringWithFormat:@"%@%@/%@/passwd.action?app=1", ENTEL_PROTOCOL, ENTEL_DOMAIN, ENTEL_API]
#define URL_LOGIN [NSString stringWithFormat:@"%@%@/%@/login", ENTEL_PROTOCOL, ENTEL_DOMAIN, ENTEL_API]
#define URL_BOLSAS_CONFIRMAR [NSString stringWithFormat:@"/%@/bolsas/confirmar", ENTEL_API]

#define URL_SALDO [NSString stringWithFormat:@"%@%@/%@/menu.action", ENTEL_PROTOCOL, ENTEL_DOMAIN, ENTEL_API]
#define URL_TRAFICO [NSString stringWithFormat:@"%@%@/%@/trafico.action", ENTEL_PROTOCOL, ENTEL_DOMAIN, ENTEL_API]

//Survey
#define URL_SURVEY @"https://entel.qualtrics.com/jfe/form/SV_42UROmAY8PNv9it?Mercado=&Movil"
#define RULE_1 5
#define SURVEY_DAYS 90
#define SURVEY_TIMER_SECONDS 30
#define NUM_EXECUTIONS_PREF @"NUM_EXECUTIONS_PREF"
#define RULE_1_PREF @"RULE_1_PREF"
#define SURVEY_LAST_UPDATE_PREF @"SURVEY_LAST_UPDATE_PREF"
#define SURVEY_ALERTBOX_NOTIF @"SURVEY_ALERTBOX_NOTIF"

#endif
