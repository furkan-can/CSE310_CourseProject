import RPi.GPIO as GPIO
import time
import subprocess
import smtplib
import datetime
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart

pir_sensor = 8 #sensor pin number
led_pin = 10 #led pin nunmber
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)

GPIO.setup(pir_sensor,GPIO.IN)
GPIO.setup(led_pin,GPIO.OUT)


    

def foo(smtpUser,smtpPass,toAdd,subject,body,file):
        msg = MIMEMultipart()
        msg['Subject'] = subject
        msg['From'] = smtpUser
        msg['To'] = toAdd
        text = MIMEText(body)
        msg.attach(text)
        image = MIMEImage(open("/home/pi/"+str(file),"rb").read())
        msg.attach(image)
    
        fromAdd = smtpUser
        header = "To: " + toAdd + "\n" + "From: " + fromAdd + "\n" + "Subject: " + subject
        print (header  + "\n" + body)
        s = smtplib.SMTP("smtp.gmail.com", 587)
        s.ehlo()
        s.starttls()
        s.ehlo()
        s.login(smtpUser,smtpPass)
        s.sendmail(fromAdd,toAdd,msg.as_string())
        s.quit()

while(True):
    current_state = GPIO.input(pir_sensor)
    if current_state:
        GPIO.output(led_pin, 0)
        print("Motion Detected")
        subprocess.call(["/home/pi/webcam.sh"])
        an = datetime.datetime.now()
        month=""
        day=""
        hour=""
        minute=""
        if(len(str(an.month))==1):
            month="0"+str(an.month)
        else:
            month=str(an.month)
            
        if(len(str(an.day))==1):
            day="0"+str(an.day)
        else:
            day=str(an.day)
        if(len(str(an.hour))==1):
            hour="0"+str(an.hour)
        else:
            hour=str(an.hour)
        if(len(str(an.minute))==1):
            minute="0"+str(an.minute)
        else:
            minute=str(an.minute)
        
        file=str(an.year)+"-"+month+"-"+day+"_"+hour+":"+minute+".jpg"
        
        print(file)
        
        smtpUser = "thiefdetectorproject@gmail.com"
        smtpPass = "Raspberrypi.1"
        toAdd = "ibrahimmasmanaci@gmail.com"
        subject = "Furkan"
        body = "Motion Detected"
        
        foo(smtpUser,smtpPass,toAdd,subject,body,file)
        foo(smtpUser,smtpPass,"furkancantavukcu98@gmail.com",subject,body,file)
        
        time.sleep(5)
    else:
        GPIO.output(led_pin, 1)
        print("No Motion Detected")
        time.sleep(0.2)
        

