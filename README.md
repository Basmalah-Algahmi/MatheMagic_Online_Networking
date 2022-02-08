# MatheMagic_Online_Networking
Project#1, CIS 427.

Basmalah Algahmi

Noe Sancen


Commands:
* Login
* Solve
* List
* ShutDown
* Logout

In order to run the program, the user is REQUIRED to start the server first BEFORE starting the client, otherwise the program will crash.
Once the server is started, run the client and insert the following commands. The first command, login, is required before any other command is called upon.
After user passes through the login requirement, then any of the other four commands can be called in any order.

There has been five commands implemented within the program. 
	The first command,  login, has the server take the username/password inputs from the user and checks the approved username/password list to check if it matches. The server side will open 
        the "logins.txt" file which contains all authorized usernames and passwords and read these data to a hashmap. 
        For validation:
		the client needs to type three strings with a space between them " login username password", if that did not happen, the program
		won't crash and will just display an error message. 
If approved, the server lets the username in before any other input is taken, otherwise the server denies the user from logging in and tells the user to re-enter their login information.
	The second command, Solve, takes the circle and rectangle values. The radius of the circle using the multiplication by pi formula to find the area, then the circumference is found using "c = 2πr" formula.
The rectangle sides are inputted by the user and the sizes are used through formulas to find the perimeter and areas. 
	The third command, LIST, takes the user's inputs and displays them in the client while also creating a text file of past inputs from the user.
	For validation:
		The program will not crash if the user entered the command without a shape, did not enter any side, or exceeded the number of sides.
If the login is root, the user will be able to use the "-all" function and list all of the inputs every login user has made. 
Otherwise, the server will send an error with a message telling the user they are not the root user. To be able to send multiple lines to the client side, 
the string concatenation process was used. 
	The fourth command, Shutdown, closes both the server and client sockets.
	The fifth command, logout, terminates the login user. When the user logouts, the server requires the user to enter a new login or re-enter their information to log back in.   	






The following text below is a sample of the output of the client side:

Note: a lot of invalid data was entered to test the program. 



-----------------------------------------------------------------
run:
You need to login in order to connect to the server by typing LOGIN follow by user name and password
Send command to server:	list
Server says: You need to sign in first
Send command to server:	login
Server says: FAILURE: Please provide correct username and password. Try again.
Send command to server:	login root
Server says: FAILURE: Please provide correct username and password. Try again.
Send command to server:	login root root ro
Server says: FAILURE: Please provide correct username and password. Try again.
Send command to server:	login ajs aj
Server says: Your login info is incorrect
Send command to server:	login root root22
Server says: SUCCESS
Send command to server:	solve -r 4
Server says: Side 4.0 4.0: Rectangle’s perimeter is 16.00 and area is 16.00

Send command to server:	solve -c 3
Server says: radius 3.0: Circle’s circumference is 18.85 and area is 28.27

Send command to server:	solve -r 12
Server says: Side 12.0 12.0: Rectangle’s perimeter is 48.00 and area is 144.00

Send command to server:	solve -r
Server says: Error: No sides found/exceed accabtable number of sides

Send command to server:	solve -c
Server says: Error: No sides found/exceed accabtable number of sides

Send command to server:	solve
Server says: Error, no shape found

Send command to server:	solve -c 7 7
Server says: Error: No sides found/exceed accabtable number of sides

Send command to server:	solve -c 25
Server says: radius 25.0: Circle’s circumference is 157.08 and area is 1963.50

Send command to server:	solve -r 2
Server says: Side 2.0 2.0: Rectangle’s perimeter is 8.00 and area is 4.00

Send command to server:	solve -r 3 4
Server says: Side 3.0 4.0: Rectangle’s perimeter is 14.00 and area is 12.00

Send command to server:	list 
Server says: root
Side 4.0 4.0: Rectangle’s perimeter is 16.00 and area is 16.00
radius 3.0: Circle’s circumference is 18.85 and area is 28.27
Side 12.0 12.0: Rectangle’s perimeter is 48.00 and area is 144.00
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
radius 25.0: Circle’s circumference is 157.08 and area is 1963.50
Side 2.0 2.0: Rectangle’s perimeter is 8.00 and area is 4.00
Side 3.0 4.0: Rectangle’s perimeter is 14.00 and area is 12.00

Send command to server:	logout
Server says: 200 OK
Send command to server:	list
Server says: You need to sign in first
Send command to server:	sally sally22
Server says: You need to sign in first
Send command to server:	login sally sally22
Server says: SUCCESS
Send command to server:	list
Server says: sally
No interactions yet

Send command to server:	solve -c 34
Server says: radius 34.0: Circle’s circumference is 213.63 and area is 3631.68

Send command to server:	solve -r 9
Server says: Side 9.0 9.0: Rectangle’s perimeter is 36.00 and area is 81.00

Send command to server:	solve -r 5 7
Server says: Side 5.0 7.0: Rectangle’s perimeter is 24.00 and area is 35.00

Send command to server:	solve -r 9 8
Server says: Side 9.0 8.0: Rectangle’s perimeter is 34.00 and area is 72.00

Send command to server:	list
Server says: sally
radius 34.0: Circle’s circumference is 213.63 and area is 3631.68
Side 9.0 9.0: Rectangle’s perimeter is 36.00 and area is 81.00
Side 5.0 7.0: Rectangle’s perimeter is 24.00 and area is 35.00
Side 9.0 8.0: Rectangle’s perimeter is 34.00 and area is 72.00

Send command to server:	list -kk
Server says: 300 invalid command
Send command to server:	list -all
Server says: Error: you are not the root user

Send command to server:	logout
Server says: 200 OK
Send command to server:	login john john22
Server says: SUCCESS
Send command to server:	list
Server says: john
No interactions yet

Send command to server:	list -all
Server says: Error: you are not the root user

Send command to server:	logout
Server says: 200 OK
Send command to server:	login root root22
Server says: SUCCESS
Send command to server:	list -all
Server says: root
Side 4.0 4.0: Rectangle’s perimeter is 16.00 and area is 16.00
radius 3.0: Circle’s circumference is 18.85 and area is 28.27
Side 12.0 12.0: Rectangle’s perimeter is 48.00 and area is 144.00
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
radius 25.0: Circle’s circumference is 157.08 and area is 1963.50
Side 2.0 2.0: Rectangle’s perimeter is 8.00 and area is 4.00
Side 3.0 4.0: Rectangle’s perimeter is 14.00 and area is 12.00
sally
radius 34.0: Circle’s circumference is 213.63 and area is 3631.68
Side 9.0 9.0: Rectangle’s perimeter is 36.00 and area is 81.00
Side 5.0 7.0: Rectangle’s perimeter is 24.00 and area is 35.00
Side 9.0 8.0: Rectangle’s perimeter is 34.00 and area is 72.00
john
No interactions yet
qiang
No interactions yet

Send command to server:	hdj
Server says: 300 invalid command
Send command to server:	shutdown
Server says: 
200 OK...
BUILD SUCCESSFUL (total time: 8 minutes 39 seconds)


------------------------------------------------------------------------

Server side:

Shutting down server...
BUILD SUCCESSFUL (total time: 8 minutes 43 seconds)

-------------------------------------------------------------------------

Possible issues when run the program:
> The words "Rectangle’s" and "Circle’s" were displayed in a weird way in one of the machines that were used to test the program. 

 
