
//
//  GWKEncryp.cpp
//  AES TEST
//
//  Created by kaitiren on 12-8-27.
//  Copyright (c) 2012 kaitiren. All rights reserved.
//

#include <stdio.h>
#include "AES.h"
#include "MD5.h"

int main(int argc, char *argv[])
{	
    char mingwen[] = "%80%E6%BA%90%20a";
    char miwen_hex[1024];
	
    char result[1024];

    unsigned char key[] = "kaitiren-20120101";
    CBm53AES aes(key);
	aes.CipherStr(mingwen,miwen_hex);
    printf("%s\n",miwen_hex);
    aes.InvCipherStr(miwen_hex, result);	
    printf("%s\n",result);

	CBm53MD5 cMd5;
	cMd5.GenerateMD5((unsigned char*)mingwen,strlen(mingwen));
	printf("%s\n",cMd5.ToString().c_str());
    getchar();
	
    return 0;
}