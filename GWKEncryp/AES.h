
//
//  AES.h
//  AES
//
//  Created by kaitiren on 12-8-27.
//  Copyright (c) 2012 kaitiren. All rights reserved.
//


// CBm53AES.h: interface for the CBm53AES class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_AES_H__24984881_87F5_447A_838A_57873C600813__INCLUDED_)
#define AFX_AES_H__24984881_87F5_447A_838A_57873C600813__INCLUDED_

#include <string.h>

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class CBm53AES
{
public:
	CBm53AES(unsigned char* key);	
	virtual ~CBm53AES();
	//º”√‹’˚∂ŒŒƒ◊÷
	void CipherStr(const char *input,char *output);	
	//Ω‚√‹
	void InvCipherStr(char *inut, char *output);
	void InvCipher(char *inut, char *output);
	void Cipher(char *input, char *output);
private:	
	unsigned char* Cipher(unsigned char* input);
	
	unsigned char* InvCipher(unsigned char* input);
	void* Cipher(void* input, int length=0);
	void* InvCipher(void* input, int length);	
		
	unsigned char Sbox[256];	
	unsigned char InvSbox[256];	
	unsigned char w[11][4][4];	
	void KeyExpansion(unsigned char* key, unsigned char w[][4][4]);	
	unsigned char FFmul(unsigned char a, unsigned char b);	
	void SubBytes(unsigned char state[][4]);
	void ShiftRows(unsigned char state[][4]);	
	void MixColumns(unsigned char state[][4]);	
	void AddRoundKey(unsigned char state[][4], unsigned char k[][4]);	
	void InvSubBytes(unsigned char state[][4]);	
	void InvShiftRows(unsigned char state[][4]);	
	void InvMixColumns(unsigned char state[][4]);
	int strToHex(const char *ch, char *hex);
	int hexToStr(const char *hex, char *ch);
	int ascillToValue(const char ch);
	char valueToHexCh(const int value);
	int getUCharLen(const unsigned char *uch);
	int strToUChar(const char *ch, unsigned char *uch);
	int ucharToStr(const unsigned char *uch, char *ch,int nLen);
	int ucharToHex(const unsigned char *uch, char *hex,int nLen);
	int hexToUChar(const char *hex, unsigned char *uch);
};


#endif // !defined(AFX_AES_H__24984881_87F5_447A_838A_57873C600813__INCLUDED_)
