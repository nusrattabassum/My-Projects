#include <SpeechSynthesis.h>
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
}
 byte ssr[500];//define a character string

// the loop routine runs over and over again forever:
void loop() {
  // read the input on analog pin 0,1,2,3:
  int s1 = analogRead(A0);
   int s2 = analogRead(A1);
 int s3 = analogRead(A2);
  int s4 = analogRead(A3);
  
  int va=560;
  int vb=560;
  int vc=520;
  int vd=560;
  // print out the value you read:
  Serial.println("flex1");
  Serial.println(s1);
  
   Serial.println("flex2");
  Serial.println(s2);
  
   Serial.println("flex3");
  Serial.println(s3);
  
   Serial.println("flex4");
  Serial.println(s4);
   
  delay(1000);        // delay in between reads for stability

if ( s1 > va && s2 > vb && s3 > vc && s4 >vd )
{
 
  
}

else if ( s1 < va && s2 > vb && s3 > vc && s4 >vd )
{
  SpeechSynthesis.buf_init(ssr);//initialize the buff
SpeechSynthesis.English(ssr,4,"10");//volume in grade 10
SpeechSynthesis.English(ssr,6,"bizeron ");//"6" means synthesis in English; "cooki"is the content
SpeechSynthesis.Espeaking(0,19,4,ssr);//Executive commands above, "0" is synthesis command; "19" select speaker; "4" speech function  

  
}


else if ( s1 > va && s2 < vb && s3 < vc && s4 <vd )
{
  SpeechSynthesis.buf_init(ssr);//initialize the buff
SpeechSynthesis.English(ssr,4,"10");//volume in grade 10
SpeechSynthesis.English(ssr,6," it's time for medicine ");//"6" means synthesis in English; "cooki"is the content
SpeechSynthesis.Espeaking(0,19,4,ssr);//Executive commands above, "0" is synthesis command; "19" select speaker; "4" speech function  

  
}


else if ( s1 > va && s2 > vb && s3 < vc && s4 <vd )
{
  SpeechSynthesis.buf_init(ssr);//initialize the buff
SpeechSynthesis.English(ssr,4,"10");//volume in grade 10
SpeechSynthesis.English(ssr,6," Napa  ");//"6" means synthesis in English; "cooki"is the content
SpeechSynthesis.Espeaking(0,19,4,ssr);//Executive commands above, "0" is synthesis command; "19" select speaker; "4" speech function  

  
}



else if ( s1 > va && s2 < vb && s3 < vc && s4 >vd )
{
  SpeechSynthesis.buf_init(ssr);//initialize the buff
SpeechSynthesis.English(ssr,4,"5");//volume in grade 5
SpeechSynthesis.English(ssr,6,"Seclo");//"6" means synthesis in English; "cooki"is the content
SpeechSynthesis.Espeaking(0,19,4,ssr);//Executive commands above, "0" is synthesis command; "19" select speaker; "4" speech function  

  
}

else if ( s1 < va && s2 < vb && s3 < vc && s4 >vd )
{
  SpeechSynthesis.buf_init(ssr);//initialize the buff
SpeechSynthesis.English(ssr,4,"5");//volume in grade 5
SpeechSynthesis.English(ssr,6,"Alactrol");//"6" means synthesis in English; "cooki"is the content
SpeechSynthesis.Espeaking(0,19,4,ssr);//Executive commands above, "0" is synthesis command; "19" select speaker; "4" speech function  

  
}

}
