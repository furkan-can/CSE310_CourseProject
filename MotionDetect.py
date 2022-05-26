import RPi.GPIO as GPIO
import time
import subprocess
import smtplib
import firebase_admin
from firebase_admin import credentials, initialize_app, storage
from firebase_admin import credentials
import pyrebase
from random import randrange

import datetime
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
firebase = 0
value = (randrange(10000))
    
    

#takes an image path and send it to the firebase storage



pir_sensor = 8 #sensor pin number
led_pin = 10 #led pin nunmber
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)

GPIO.setup(pir_sensor,GPIO.IN)
GPIO.setup(led_pin,GPIO.OUT)


config = {                                                              #define a dictionary named config with several key-value pairs that configure the connection to the database.
  "apiKey": "hiSYf1AiN63jwo1cZvY3H0ulFFFzcx2FFxcImGwb",
  "authDomain": "the-thief-detector.firebaseapp.com",
  "databaseURL": "https://the-thief-detector-default-rtdb.firebaseio.com/",
  "storageBucket": "the-thief-detector.appspot.com"
}

cred = credentials.Certificate("/home/pi/Desktop/the-thief-detector-firebase-adminsdk-1wfg7-e46c1f4787.json")
firebase_admin.initialize_app(cred, {
    'storageBucket': 'the-thief-detector.appspot.com'
})
firebase = pyrebase.initialize_app(config)


 

def sendEmail(smtpUser,smtpPass,toAdd,subject,body,file):
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
        image_name=file 
        print(file)
        
        smtpUser = "thiefdetectorproject@gmail.com"
        smtpPass = "Raspberrypi.1"
        toAdd = "polathumeyraa@gmail.com"
        subject = "Furkan"
        body = "Motion Detected"
        
        sendEmail(smtpUser,smtpPass,toAdd,subject,body,file)
        
        
        bucket = storage.bucket()
        blob = bucket.blob(file)
        blob.upload_from_filename("/home/pi/"+str(file))
        # Opt : if you want to make public access from the URL
        blob.make_public()
        #image_url = blob.public_url

        print("uploaded file to the storage", blob.public_url)
        
        
        ref = firebase.database()
        
        data = {

            "time" : str(datetime.datetime.now()),
            "imagePath" : str (blob.public_url)
            
        }
        firebase.database().child("records").child(str((randrange(10000)))).set(data)
      
        time.sleep(5)
    else:
        GPIO.output(led_pin, 1)
        print("No Motion Detected")
        time.sleep(0.2)





