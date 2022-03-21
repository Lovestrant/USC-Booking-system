The USC Booking System Documentation

![alt text](https://github.com/Lovestrant/Amba-Digital-HSApp-pics/blob/main/USC system.png)
 
USAGE
On Login
The above diagram shows the overall system overview of what a user can see as they navigate through the system. At first a student will have to input their registration number in the activity main where the system will check whether that particular registration number is registered or not. If not registered then the system will register it by adding it in the Authentication table. However if the student is registered then a session variable will be set and then direct them to dashboard activity.
In order for a user to create the timetable with the create timetable functionality, they have to login as admin. To do so a user will have to enter registration number/username as Admin in the main activity. Any other registration number won’t be able to see the create timetable functionality.
After successful Login
After successful Login a user can now access the dashboard features which include 
•	Check timetable
•	See bookings
•	Rate sessions
•	Print report
A user can select any of the above functionality depending on what they want to do in the system. 
	If they select check timetable
If a user selects this functionality, the timetable that had been created by the admin will get displayed with all the activities that the exercise team will perform. A user can then select which activity they want to book and which date. If the student has already booked the activity they can never book it again unless it is the same activity on a different day.  The students can however book as many activities as possible as long as they don’t collide in time. 
An activity can get booked by 4 users at most.
	If they select see booking
With this functionality a user can see the sessions that he or she has booked. They then have a option of unbooking them. If a user unbooks an activity then it gets available for booking again by the rest of the students.
	If they select Print report
A user can print report of what has been going on and the number of bookings that have occurred per game. The do so by searching on the print screen activity by game.
Users also have the option of displaying the report via the console.

DATABASE (SQLITE)
All the sql commands and methods that are executed in the database will be located in the DatabaseHelper.java file in the code src folder.This include all all methods to create the databases and also to call records.
The database used in this project is inbuilt and its name is sqlite database. This makes the records removable whenever the user uninstalls the application.
