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

### Current State

The NM Medical Cannabis app currently has a login page, a patient card info page, transaction page, and a transaction dialog. Below are the current state of each view. 

#### Login Page
   * The login page button does login to the app but the email and password functions are not at this time. 
   
#### Patient Card Info Page
  (Current State)
  
   * The patient ID card page is working. You are able to type in the number of your medical card, the issue and expiration date, and add units available.
   * The issue date is a pop up date picker to enter the issue date
   * The expiration date will automatically be generated based on the issue date, which is one year after issue date.
   
  (Unimplemented)
  
   * Need to create a notification alert to alert you when you are 30 days from your card expiration date. 
   * Need to add an edit button to edit the issue and expiration date, and units available.
   * Need to remove the add button on the page. 
   
#### Transaction Dialog

   (Current State)
   
    * All fields are working in the dialog.
    * The purchase date is a date picker.
    * Used spinner to create types of cannabis purchased
    * Grams are converted into units using the multiplier for each type.
    * Total grams and total units are totaled on the bottom of the card for the current transaction. 
   
   (Unimplemented)
   
    * Need to reformat the font sizes.

#### Transaction Page
  (Current State)
  
   * Cards are being created as you enter transactions in the transaction dialog. 
   * Add button is working and pop up the dialog fragment.
   * Units available are at the top of the page and updates according the the type and amount of cannabis purchased.
   * Cards hold the dispensary name, purchase date, type of cannabis purchased, strain name, and grams purchased.
   * At the bottom of the card are the total grams and total units used for that specific transaction.
  
  (Unimplemented)
  
   * Need to change the TextView fields at the bottom of the cards to read "Total Grams" and "Total Units"
   * Need to fix the card view to wrap content.
   * Need to add a delete option so the user can delete the card. 
   * Need to add a on click listener for the card so when it is clicked you are able to edit the card. 
   

### Useful

NM Medical Cannabis app is useful because, as of this writing, patients do not have any way of tracking their units or keep track of purchase dates. The platform NM Medical Cannabis program uses is BioTrack and also do not track dates. This is a problem because patients do not know when or if they are recieving the correct amount of units. Patients are trusting the BioTrack platform.

### Hardware

Currently, the NM Medical Cannabis App developed on the Android OS platform version 5.0 – 5.1.1, is using the Lollipop API level 21. 

It is tested using the Nexus 5X API 22 emulator and has no orientation restrictions of which I am aware. 

### Cosmetics

   * Would like to change the color scheme
   * Would like to change the font style
   * Would like to reformat and clean up
   
### Stretch Goals
   * Create profiles for dispensaries and growers
   * Add strain database 
   * Get Login Page functioning
  
### Javadocs

[Javadocs](docs)

 
