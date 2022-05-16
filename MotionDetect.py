import RPi.GPIO as GPIO
import time
import subprocess
import smtplib

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
        subprocess.call(["/home/pi/webcam.sh"])
        smtpUser = "thiefdetectorproject@gmail.com"
        smtpPass = "Raspberrypi.1"
        toAdd = "ibrahimmasmanaci@gmail.com"
        fromAdd = smtpUser
        subject = "Python Test"
        header = "To: " + toAdd + "\n" + "From: " + fromAdd + "\n" + "Subject: " + subject
        body = "Hello Humeyraaaaas"
        print (header  + "\n" + body)
        s = smtplib.SMTP("smtp.gmail.com", 587)
        s.ehlo()
        s.starttls()
        s.ehlo()
        s.login(smtpUser,smtpPass)
        s.sendmail(fromAdd,toAdd,header + "\n\n" + body)
        s.quit()
        
        
        time.sleep(5)
    else:
        GPIO.output(led_pin, 1)
        print("No Motion Detected")
        time.sleep(0.2)

