## NM Medical Cannabis App

### Definition

*NM Medical Cannabis* app is for New Mexico *medical cannabis* patients to track allocated units in a 90 day rolling period. 
The patient will add the medical card ID number, issued date, and expiration date, into the app which will notify 
the patient of the renewal date 30 days before the expiration date.

Patients will know the allocated units available at any given point in time by adding the type of cannabis and number of grams 
purchased. 

Patients are given 230 units in a rolling 90 day period, and the type of cannabis purchased will determine the units used. 
The app will keep track of the date purchased to establish when receiving the units back.

Units are determined as followed:
  * Cannabis Flower: 1 gram = 1 unit
  * Cannabis Edibles: 1 edible = 1 unit
  * Cannabis Concentrates: 1 gram = 3 units 
  
  ### Action items
  
  1. Patient Card ID Page
     * Need to add item to correct database via ORM helper.
  2. New Transaction Page
     * Need to connect items to database via ORM helper.
     * Need to add a save button to screen which will add fields to database.
     * When *saved button* is pressed, the Transaction History page appears. 
  3. Transaction History Page
     * Finish creating card layout to display each transaction made.
     * Each card view display dispensary name, cannabis type, strain name, and grams purchased.
  4. Stretch goal
     *Transaction History page can show views base on category chosen. (i.e. strain name, dispensary name, etc)
