A Spring boot app which has APIs related to Bowling game

Build and Run
------------------
Clone the repo and run ‘mvn clean package’ command under the root directory.
Once build is completed to run ‘java -jar target\BowlingGame-1.0-SNAPSHOT.jar’ command and spring boot app will start.

Import API collection - https://www.getpostman.com/collections/8a60859ee62e9e09f01c

Game details:
A player will have has 10 sets, each set has 2 opportunities so total 20 rolls. if on 10th a Strike or Spare happen the player will get one extra roll to play so total 21 rolls available in game.

Maximum of 3 players can play in single lane and create or update bowler for lane will be allocated based on availability (if occupancy is less than 3)
Score submission and getting Score card for player is based on the ID allocated while creation of player and ID acts as Primary key for User in this game.  

Score calculation:
If Strike --> 10 points + 10 strike bonus points = 20 points
If Spare --> 10 points + 5 Spare bonus points = 15 points
Other rolls eg : 3 on first roll and 4 on second roll = 7 points

