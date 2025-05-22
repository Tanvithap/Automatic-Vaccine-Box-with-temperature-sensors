#include <DHT.h>

#define DHTPIN 6
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);
#define IN1 4
#define IN2 5
#define ENA 3

#define PELTIER_COOL_PIN 7
#define PELTIER_HEAT_PIN 8

String ins = "";

void setup() {
  Serial.begin(9600);

  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENA, OUTPUT);
  stopMotor();

  pinMode(PELTIER_COOL_PIN, OUTPUT);
  pinMode(PELTIER_HEAT_PIN, OUTPUT);
  digitalWrite(PELTIER_COOL_PIN, LOW);
  digitalWrite(PELTIER_HEAT_PIN, LOW);

  dht.begin();

  
}

void loop() {
  if (Serial.available()) {
    char input = Serial.read();
    ins = String(input);
    Serial.println("Received ins "+ins);
    if (ins == "1") {
      rotateClockwise(200);
      delay(200);
      stopMotor();
    }
  }
  float temperature = dht.readTemperature();

  if (isnan(temperature)) {

  } else {
    Serial.println(temperature);

    if (temperature > 30) {  
      digitalWrite(PELTIER_COOL_PIN, LOW);
        digitalWrite(PELTIER_HEAT_PIN, HIGH);
        Serial.println("Cooling..");

    } else if (temperature < 15) {

      digitalWrite(PELTIER_HEAT_PIN, LOW);
      digitalWrite(PELTIER_COOL_PIN, HIGH);
      Serial.println("heating...");
      
    } 
    else{
  digitalWrite(PELTIER_HEAT_PIN, HIGH);
      digitalWrite(PELTIER_COOL_PIN, HIGH);
      Serial.println("normal");

    }
  }

  delay(1000); 
  ins="";
}

void rotateClockwise(int speed) {
  digitalWrite(IN1, HIGH);
  digitalWrite(IN2, LOW);
  analogWrite(ENA, speed);
}

void stopMotor() {
  digitalWrite(IN1, LOW);
  digitalWrite(IN2, LOW);
  analogWrite(ENA, 0);
}
