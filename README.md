## NM Medical Cannabis App

### Definition

*NM Medical Cannabis* app is for *New Mexico medical cannabis patients* to track allocated units in a 90 day rolling period. 
Patients add the medical card ID number issued to them, the date the card was issued, and expiration date of the card, into the app. Patients will recieve a notification 30 days before card expired, which must be renewed. 

Additionally, patients can track the allocated units available at any given time. If they are a new patient they will start with 230 units, if not a new patient, the patient will have to call a dispensary to know the exact amount of units available at that time.

Patients can them add the *type* of cannabis and number of grams purchased into the app and it will automatically convert the grams purchased into units and subtract from the available units.  

New patients are given 230 units in a rolling 90 day period. The *type* of cannabis purchased will determine the units used. 
Since the app will keep track of the day of purchase, you will always know when you will recieve units back.

Units are determined as followed:
  * Cannabis Flower: 1 gram = 1 unit
  * Cannabis Edibles: 1 edible = 1 unit
  * Cannabis Concentrates: 1 gram = 3 units 
  
### Motivation 

The motivation behind the NM Medical Cannabis app is the lack of information NM medical cannabis patients have about the medical cannabis program in New Mexico, in particular, tracking units. The app intends to give patients a way to track the allocated units provided in a 90 day rolling period and dates of purchases. 


### Useful

NM Medical Cannabis app is useful because, as of this writing, patients do not have any way of tracking their units or keep track of purchase dates. The platform NM Medical Cannabis program uses is BioTrack and also do not track dates. This is a problem because patients do not know when or if they are recieving the correct amount of units. Patients are trusting the BioTrack platform.

###Current State

The NM Medical Cannabis app currently has a login page, a patient card info page, and a transaction page. Below is the state of each view in the app. 

The login page, as of now, is a way to enter the app. The patient card info page is set up to add the number of the patient card, enter the issue and expiration of the card, and enter the patient's current units.

For the app to be in a state of readiness the app needs the following:
- Patient Card Info Page 
    -needs to an edit button to be able to change the issue date of your card. 
    -the units need to update as soon as a transaction has been logged.
- Transaction Page 
    -Needs to finish the calculation of units depending on the type of cannabis was bought.
    -Needs to be able to scroll through the history of transactions. 
    -Needs to add units back to available units  
  
###Javadocs
[Javadocs](docs)

  ### Action items
  
  Patient Card ID Page
     * Need to add fields to correct database via ORM helper.
  New Transaction Page
     * Need to connect fields to database via ORM helper.
     * Need to add save button to screen which will add fields to database.
     * When *saved button* is pressed, the Transaction History page appears. 
  Transaction History Page
     * Finish creating card layout to display each transaction made.
     * Each card view display fields entered from *New Transaction* page. (dispensary name, cannabis type, strain name, and grams purchased)
  Clean up code
     * Need to add comments to code 
     * Need to reformat code
  Stretch goal
     * Transaction History page will show views base on category chosen. (i.e. strain name, dispensary name, etc)

 




Currently, the NM Medical Cannabis app has a login page, a patient card info page, and a transaction page. The login page, as of now, is a way to enter the app. The patient card info page is set up to add the number of the patient card, enter the issue and expiration of the card, and enter the patient's current units.

For the app to be in a state of readiness the app needs the following:
- Patient Card Info Page 
    -needs to an edit button to be able to change the issue date of your card. 
    -the units need to update as soon as a transaction has been logged.
- Transaction Page 
    -Needs to finish the calculation of units depending on the type of cannabis was bought.
    -Needs to be able to scroll through the history of transactions. 
    -Needs to add units back to available units  

Currently, the NM Medical Cannabis App developed on the Android OS platform version 5.0 – 5.1.1, is using the Lollipop API level 21. 

It is tested using the Nexus 5X API 22 emulator and has no orientation restrictions of which I am aware. 

The aesthetics of NM Medical Cannabis app can improve with the following:
-A color scheme using no more than three colors. 
-Change fonts to a clear modern typeface and one accent font. 

Below is a list of stretch goals sorted by what would add the most utility at the top.

-A link to the NM health department to learn about the laws and regulations of New Mexico regarding NM Medical Cannabis.
-A link to the diagnosis you can use to apply.
-A download link to the application which is needed to apply to be considered as a patient. 
-A list of medical cannabis doctors who are certified to approve your application.
-A list of all dispensaries in the state of New Mexico.
-A profile page for doctors, dispensaries, and growers so patients can rate as they see fit. 
