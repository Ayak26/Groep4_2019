#include <Servo.h>

#define M1 4
#define E1 5

Servo s1;
Servo s2;


const int CLOSED = 90, OPEN = 135;
const byte num_chars = 32;

char received_chars[num_chars];   // an array to store the received data
int index_servo1 = 0, index_servo2 = 0;
int interval_servo1 = 600, interval_servo2 = 1200, interval_close = 400;
unsigned long timing_servo1[5], timing_servo2[5];
unsigned long current_time, close_servo1, close_servo2;

bool on = false, new_data = false, servo1 = false, servo2 = false;

void setup() {
    s1.attach(8);
    s1.write(CLOSED);
    s2.attach(9);
    s2.write(CLOSED);
    pinMode(E1, OUTPUT);
    pinMode(M1, OUTPUT);
    for (int i = 10; i <= 12 ; ++i) {
        pinMode(i, OUTPUT);
    }
    Serial.begin(9600);
}

void loop() {
    current_time = millis();
    serialInput();
    if (on) {
        motor(255);
        checkServo1();
        checkServo2();
    } else {
        motor(0);
    }
}

void motor(int PWM) {
    analogWrite(E1, PWM);
    digitalWrite(M1, HIGH);
}

void serialInput() {
    static byte ndx = 0;
    char end_marker = '\n';
    char rc;

    while (Serial.available() > 0 && !new_data) {
        rc = Serial.read();

        if (rc != end_marker) {
            received_chars[ndx] = rc;
            ndx++;
            if (ndx >= num_chars) {
                ndx = num_chars - 1;
            }
        }
        else {
            received_chars[ndx] = '\0'; // terminate the string
            ndx = 0;
            new_data = true;
        }
    }
    readCommand();
}

void readCommand() {
    if (new_data) {
        if (strcmp(received_chars, "I") == 0) {
            on = true;
        } else if (strcmp(received_chars, "O") == 0) {
            on = false;
        } else if (strcmp(received_chars, "S1") == 0) {
            timing_servo1[index_servo1] = current_time;
            if (index_servo1 == 4) {
                index_servo1 = 0;
            } else {
                index_servo1 += 1;
            }
        } else if (strcmp(received_chars, "S2") == 0) {
            timing_servo2[index_servo2] = current_time;
            if (index_servo2 == 4) {
                index_servo2 = 0;
            } else {
                index_servo2 += 1;
            }
        } else {
            Serial.println("Command unknown");
        }
        new_data = false;
    }
}

void checkServo1 () {
    if (servo1) {
        if ((unsigned long)(current_time - close_servo1) >= interval_close) {
            s1.write(CLOSED);
            servo1 = false;
        }
    } else {
        for (int i = 0; i < 5; i++) {
            if ((unsigned long)(current_time - timing_servo1[i]) >= interval_servo1 && timing_servo1[i] != 0) {
                timing_servo1[i] = 0;
                close_servo1 = current_time;
                s1.write(OPEN);
                servo1 = true;
            }
        }
    }
}

void checkServo2 () {
    if (servo2) {
        if ((unsigned long)(current_time - close_servo2) >= interval_close) {
            s2.write(CLOSED);
            servo2 = false;
        }
    } else {
        for (int i = 0; i < 5; i++) {
            if ((unsigned long)(current_time - timing_servo2[i]) >= interval_servo2 && timing_servo2[i] != 0) {
                timing_servo2[i] = 0;
                close_servo2 = current_time;
                s2.write(OPEN);
                servo2 = true;
            }
        }
    }
}
