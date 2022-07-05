# Network_project_description
Multiple clients can connect to one FTP server.
FTP uses TCP connections for communication.
FTP server will have 2 files, the first named ‘Credentials’ to save the authorized Clients with their username & password, and the second named ‘Directories’ will save the directories that are allowed to each client to access.
We should have at minimum 4 clients and for each client 3 directories and in each directory 3 files.
The process: a. Client sends its username.
Server will search in the first file if this username exists.
If it exists, it will request the password. b. Client sends its password.
Server will compare this password with the password that belongs to the username.
If they both match, it will send “Login Successfully”.
Note: if the username or password is wrong, the server will reply with “Login Failed” and will ask for re-login. c. Client will enter the command “show directories” to list all the available dirs.
Server will retrieve list all the available dirs. d. Client will send the wanted dir. to the server.
Server will reply with the available files. e. Client sends a request which includes the name of the targeted file (download file).
Server responses with the file content. f. Client will receive the previous data & write it into a file with the same name in a download folder
in the client’s side. g. Client can enter the command “QUIT” to close the connection or request another file. Example of downloading new
file “Joker” that exists in “Movies dir.”. The username is ‘user1’ and password is ‘1234’: Server: Please choose ‘LOGIN’ or ‘QUIT’ Client: LOGIN
Server: Please enter your username Client: user1 Server: Username OK, Please enter your password Client: 1234Server: Login Successfully
Server: Please choose: ‘SHOW DIRECTORIES’ or ‘SHOW DIRECTORY’ or ‘DOWNLOAD FILE’ or ‘QUIT’ Client: SHOW DIRECTORIES Server: The
available directories are: Movies, Music, Docs Server: Please choose: ‘SHOW DIRECTORIES’ or ‘SHOW DIRECTORY’ or ‘DOWNLOAD FILE’ or 
‘QUIT’ Client: SHOW DIRECTORY Server: Please enter the desired directory Client: Movies Server: The available files are: Joker, WAR, Free
Fire Server: Please choose: ‘SHOW DIRECTORIES’ or ‘SHOW DIRECTORY’ or ‘DOWNLOAD FILE’ or ‘QUIT’ Client: DOWNLOAD FILE Server:
Please choose the desired file to download Client: Joker Server: reply with the file content Server: Please choose: ‘SHOW DIRECTORIES’ or
‘SHOW DIRECTORY’ or ‘DOWNLOAD FILE’ or ‘QUIT’ Client: QUIT Server: Connection is terminated. You are logged out.
