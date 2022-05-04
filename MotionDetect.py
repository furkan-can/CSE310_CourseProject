import RPi.GPIO as GPIO
import time

pir_sensor = 8 #sensor pin number
led_pin = 10 #led pin nunmber
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)

GPIO.setup(pir_sensor,GPIO.IN)
GPIO.setup(led_pin,GPIO.OUT)

while(True):
    current_state = GPIO.input(pir_sensor)
    if current_state:
        GPIO.output(led_pin, 0)
        print("Motion Detected")
        time.sleep(5)
    else:
        GPIO.output(led_pin, 1)
        print("No Motion Detected")
        time.sleep(0.2)
