# Java-Multithreaded-Server

This app is a simple multithreaded server made using Swing. 

It works by connecting to a localhost MySQL database which holds 2 tables - Users and Students.
To access this information, the user must first login via their UserID which must exist in the Users table. If it does, open the main GUI which
gives reads and displays the students from the Students table.

Features:
- Multithreaded Server using Sockets
- Login validation
- Swing GUI to present Student table information
