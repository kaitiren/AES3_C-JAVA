//
//  MD5.h
//  MD5
//
//  Created by kaitiren on 12-8-27.
//  Copyright (c) 2012年 kaitiren. All rights reserved.
//

#ifndef _MD5_H
#define _MD5_H 
  
//#pragma warning(disable:4786) 
  
#include <string> 
  
using namespace std; 
  
/*! 
* Manage MD5. 
*/
class CBm53MD5 
{ 
private: 
#define uint8 unsigned char 
#define uint32 unsigned long int 
  
struct md5_context 
{ 
   uint32 total[2]; 
   uint32 state[4]; 
   uint8 buffer[64]; 
}; 
  
void md5_starts( struct md5_context *ctx ); 
void md5_process( struct md5_context *ctx, uint8 data[64] ); 
void md5_update( struct md5_context *ctx, uint8 *input, uint32 length ); 
void md5_finish( struct md5_context *ctx, uint8 digest[16] ); 
  
public: 
//! construct a CBm53MD5 from any buffer 
void GenerateMD5(unsigned char* buffer,int bufferlen); 
  
//! construct a CBm53MD5 
CBm53MD5(); 
  
//! construct a md5src from char * 
CBm53MD5(const char * md5src); 
  
//! construct a CBm53MD5 from a 16 bytes md5 
CBm53MD5(unsigned long* md5src); 
  
//! add a other md5 
CBm53MD5 operator +(CBm53MD5 adder); 
  
//! just if equal 
bool operator ==(CBm53MD5 cmper); 
  
//! give the value from equer 
// void operator =(CBm53MD5 equer); 
  
//! to a string 
string ToString(); 
  
unsigned long m_data[4]; 
}; 
#endif /* md5.h */
