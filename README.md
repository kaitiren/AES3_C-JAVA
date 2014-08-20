
AES3 C++/JAVA Common Encryption Algorithm
=========================================

This is a generic description AES encryption and decryption algorithms between C ++ and JAVA.

* Add the following to your **project** :
    
    [head "AES.h"]
    [cpp "AES.cpp"]
    

* Then call methods :
    ```
    char mingwen[] = "%80%E6%BA%90%20a";//encode
    char miwen_hex[1024];
    unsigned char key[] = "kaitiren-20120101";
    CBm53AES aes(key);
	aes.CipherStr(mingwen,miwen_hex);
    printf("%s\n",miwen_hex);
    ```

    ```
    char result[1024];
    aes.InvCipherStr(miwen_hex, result);//decode
    printf("%s\n",result);
    ```
    
Lincense
========
Copyright (c) 2012 kaitiren. All rights reserved.

    

