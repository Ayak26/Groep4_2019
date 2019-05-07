void setup() {
  for(int i = 2; i < 7 ; ++i) {
    pinMode(i, OUTPUT);
  }
  Serial.begin(9600);
  while(!Serial){
    ;
  }
}

void loop() {
  if (Serial.available() > 0) {    
    byte incomingByte = 0;
    incomingByte = Serial.read(); // read the incoming byte:
    if (incomingByte != -1) { // -1 means no data is available
      if (incomingByte == 2) {
        digitalWrite(2, HIGH);
      } else if (incomingByte == 3) {
        digitalWrite(3, HIGH);
      } else if (incomingByte == 4) {
        digitalWrite(4, HIGH);
      } else if (incomingByte == 5) {
        digitalWrite(5, HIGH);
      } else if (incomingByte == 6) {
        digitalWrite(6, HIGH);
      }
    }
  }

}
