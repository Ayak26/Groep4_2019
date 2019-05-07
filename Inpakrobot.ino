#include <Servo.h>

#define M1 4
#define E1 5

// ------ Servo objects ------
Servo s1;
Servo s2;

// ------ Constants ------
const int CLOSED = 90, OPEN = 135, INTERVAL_SERVO1 = 600, INTERVAL_SERVO2 = 1200, INTERVAL_CLOSE = 400;
const byte num_chars = 32;

// ------ variables ------
char received_chars[num_chars];   // an array to store the received data
int index_servo1 = 0, index_servo2 = 0;
unsigned long timing_servo1[5], timing_servo2[5];
unsigned long current_time, close_servo1, close_servo2;

// ------ Flags ------
bool on = false,
    new_data = false,   //Communication flag
    servo1 = false,     //Servo open or not
    servo2 = false;     //false is closed, true is open

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

/*
 Controls the speed of the DC motor

 @param PWM the pwm to use as speed
 */
void motor(int PWM) {
    analogWrite(E1, PWM);
    digitalWrite(M1, HIGH);
}

/*
 Reads the serial input when it is available
 If there was new data it runs readCommand
 */
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
    if(new_data) {
        readCommand();
        new_data = false;
    }
}

/*
 Reads the command received in serialInput and acts on it
 */
void readCommand() {
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
}

/*
 Looks if servo1 needs to perform an action
 */
void checkServo1 () {
    if (servo1) {
        if ((unsigned long)(current_time - close_servo1) >= INTERVAL_CLOSE) {
            s1.write(CLOSED);
            servo1 = false;
        }
    } else {
        for (int i = 0; i < 5; i++) {
            if ((unsigned long)(current_time - timing_servo1[i]) >= INTERVAL_SERVO1 && timing_servo1[i] != 0) {
                timing_servo1[i] = 0;
                close_servo1 = current_time;
                s1.write(OPEN);
                servo1 = true;
            }
        }
    }
}

/*
 Looks if servo2 needs to perform an action
 */
void checkServo2 () {
    if (servo2) {
        if ((unsigned long)(current_time - close_servo2) >= INTERVAL_CLOSE) {
            s2.write(CLOSED);
            servo2 = false;
        }
    } else {
        for (int i = 0; i < 5; i++) {
            if ((unsigned long)(current_time - timing_servo2[i]) >= INTERVAL_SERVO2 && timing_servo2[i] != 0) {
                timing_servo2[i] = 0;
                close_servo2 = current_time;
                s2.write(OPEN);
                servo2 = true;
            }
        }
    }
}
