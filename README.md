# SE2_Project
Summary.
This is a sprint project for our course in software engineering 2, Spring 2020.
The project members / workers.
  Mounir sabry
  Mostafa Badr
  Mina Faried
  Mina Botros
The project uses the agile techniques and has 5 sprints.
The project purpose is to practise web services on e-commerce.
The used programming language: JAVA

How to use the program.
You can run the project in your IDE or run through docker, either ways the requirements are constants.
Since we use maven, you should have no worry about spring boot and other staff.
However, since maven will not handle the installation of MySQL Server (or the DBMS if you choice to change it) into your device.
You will have to install it, either directly in case you used the IDE or jar file or through MySQL image in docker.

Once you have the project and installed Mysql, all you have to do is to make sure that mysql server is running, then run the 
application, the application will start the spring boot server to accept your requests, you case send command to the server using
browser or postman via the url "localhost:8080" followed the name of the service you want to use.
You can find all the available api functions in API_Functions class in se2_project package.

Detailed.
  On this project we build web services to enable e-commerce application for customers to buy and sell goods online.
The project uses maven for handling dependenices and uses docker for simplifing the installation and testing process.

The implemented Features.
Three types of users.
the admin user which has access to all the data and privilegios on the application.
The shop owner user which can add shop(s) to the application either physical(on-site) or virtual(online) to the application.
Using these shops, the shop owner can add goods to sell online.
The customer or normal user which can buy these goods from the shop owner.

MySQL.
We have implemented MySQL as our database which can accesses locally, however we doesn't restrict the DBMS used.
You can implement any DBMS you want either online or local as long as you keep the same interface.
All you have to do is to extend the AccountStorage abstract class with  you class of choice and then modify the created instances in 
account_system package's interfaces.

What might be added later.
Add and manage shops to the application.
We will add the ability to add and manage the shops in the application as a shop owner.

Purchase items from the application.
After you login to the application as a normal user, you can buy goods and items and buy using any available paying methods.

Statistics.
You can have some statistics about the informatinon in the application, like how many items you purchase or sell.
What is the number of customers and shop owners in the application, and other information.
Please note that what data or statistics will be availabe has not decided yet.






