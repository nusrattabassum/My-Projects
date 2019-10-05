#include <SoftwareSerial.h>
SoftwareSerial ms(10,11);

#include<Wire.h>
const int MPU=0x68;  // I2C address of the MPU-6050
int AcX,AcY,AcZ,Tmp,GyX,GyY,GyZ;
void setup(){
  Serial1.begin(38400);
  
  Wire.begin();
  Wire.beginTransmission(MPU);
  Wire.write(0x6B);  // PWR_MGMT_1 register
  Wire.write(0);     // set to zero (wakes up the MPU-6050)
  Wire.endTransmission(true);
  Serial.begin(38400);
}
void loop(){
//  Serial1.print('h');
  Wire.beginTransmission(MPU);
  Wire.write(0x3B);  // starting with register 0x3B (ACCEL_XOUT_H)
  Wire.endTransmission(false);
  Wire.requestFrom(MPU,14,true);  // request a total of 14 registers
  AcX=Wire.read()<<8|Wire.read();  // 0x3B (ACCEL_XOUT_H) & 0x3C (ACCEL_XOUT_L)     
  AcY=Wire.read()<<8|Wire.read();  // 0x3D (ACCEL_YOUT_H) & 0x3E (ACCEL_YOUT_L)
  AcZ=Wire.read()<<8|Wire.read();  // 0x3F (ACCEL_ZOUT_H) & 0x40 (ACCEL_ZOUT_L)
  Tmp=Wire.read()<<8|Wire.read();  // 0x41 (TEMP_OUT_H) & 0x42 (TEMP_OUT_L)
  GyX=Wire.read()<<8|Wire.read();  // 0x43 (GYRO_XOUT_H) & 0x44 (GYRO_XOUT_L)
  GyY=Wire.read()<<8|Wire.read();  // 0x45 (GYRO_YOUT_H) & 0x46 (GYRO_YOUT_L)
  GyZ=Wire.read()<<8|Wire.read();  // 0x47 (GYRO_ZOUT_H) & 0x48 (GYRO_ZOUT_L)
// Serial.print("AcX = "); Serial.print(AcX);
  //Serial.print("\n");
 //Serial.print(" | AcY = "); Serial.print(AcY);
   //Serial.print("\n");
//  Serial.print(" | AcZ = "); Serial.print(AcZ);

//  Serial.print(" | GyX = "); Serial.print(GyX);
//   Serial.print("\n");
 // Serial.print(" | GyY = "); Serial.print(GyY);
//   Serial.print("\n");
 // Serial.print(" | GyZ = "); Serial.println(GyZ);
//delay(1000);

if( AcX > 7000 ) 
{ 
 // forward();
  Serial1.print('f');
  //Serial.print('f');
 // delay(2000);
}
else if( AcX < -7000 ) 
{ 
 // backward();
   Serial1.print('b');
   // delay(2000);
}
if( AcY > 7000 ) 
{ 
//  Leftward();
    Serial1.print('r');
    // delay(2000);
}
if( AcY < -7000 ) 
{ 
 // Rightward();
    Serial1.print('l');
   //  delay(2000);
}
if( AcX < 7000 && AcX > -7000 && AcY <7000 && AcY > -7000 ) 
{ 
 // stop();
   Serial1.print('s');
  //  delay(2000);
}
 // else
 // {
 //  Serial1.print('s');
//}

}
