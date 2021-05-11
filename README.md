PROJECT IS DEVELOPED USING SPRING BOOT , MAVEN

H2 CONSOLE IS USED AS IN MEMORY DATABASE FOR TESTING PURPOSE

SWAGGER2 IS USED FOR DOCUMENTATION OF API


STEP 0: Clone the project from github

STEP 1: Install Maven

https: //maven.apache.org/install.html

STEP 2: Open command line

STEP 3: Open the base directory of the project

Step 4: Run project 

	mvn spring-boot:run

and Press enter

OPEN POSTMAN

URLS:

/////////////////////////////////////////////////////////////////////////////////

POST : Signup User:

   http: //localhost:8080/signup
   
{ 

  "firstName": "Hamza", "lastName": "Fareed",
   
  "email": "hamzakfared@gmail.com",

  "password": "Test#1233",

  "userRole": "ROLE_USER"

}

/////////////////////////////////////////////////////////////////////////////////

POST: Signin User:

   http: //localhost:8080/signin { "email": "hamzakfared@gmail.com", "password": "Test#1233"
   
}

/////////////////////////////////////////////////////////////////////////////////


PUT: Update firstName and lastName of user

-> Click on Headers Tab 

HeaderName: Authorization

value: Bearer {tokenId}

exapmle: 

Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE2MjEzNDgxMjYsImlhdCI6MTYyMDc0MzMyNn0.n88AbXg9wuyc9WQcalTtOEYRwpiksLWKDI_aNJq_DDb9vVKGMJMfwpAj7A4NhUqmz-2e_5R6ixxgeayCjuvybQ

{

"firstName": "Hamza",

"lastName": "change"

}

/////////////////////////////////////////////////////////////////////////////////

POST: Create Support Ticket:

-> Click on Headers Tab 



HeaderName: Authorization

value: Bearer {tokenId

}

exapmle: 

Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE2MjEzNDgxMjYsImlhdCI6MTYyMDc0MzMyNn0.n88AbXg9wuyc9WQcalTtOEYRwpiksLWKDI_aNJq_DDb9vVKGMJMfwpAj7A4NhUqmz-2e_5R6ixxgeayCjuvybQ

POST:  http: //localhost:8080/users/1/tickets
{

"message": "this is for testing"

}

/////////////////////////////////////////////////////////////////////////////////

Admin SignUp



{

"firstName": "Admin",

"lastName": "Admin",

"email": "admin@gmail.com",

"password": "Test#1233",

"userRole": "ROLE_ADMIN"

}

/////////////////////////////////////////////////////////////////////////////////

Admin SignIn

{

"email": "admin@gmail.com",

"password": "Test#1233"

}

/////////////////////////////////////////////////////////////////////////////////

GET : http: //localhost:8080/tickets

-> Click on Headers Tab 

HeaderName: Authorization

value: Bearer {tokenId

}

exapmle: 

Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE2MjEzNDgxMjYsImlhdCI6MTYyMDc0MzMyNn0.n88AbXg9wuyc9WQcalTtOEYRwpiksLWKDI_aNJq_DDb9vVKGMJMfwpAj7A4NhUqmz-2e_5R6ixxgeayCjuvybQ



POST:  http: //localhost:8080/users/1/tickets { "message": "this is for testing"

}







NOTE: {tokenId} IS RETURN WITH SIGNIN OF USER 

TO VIEW THE DATABASE

TYPE http: //localhost:8080/h2-console IN BROWSER



Saved Settings: Generic H2 (Embedded)

Driver Class: org.h2.Driver

JDBC URL : jdbc:h2:mem:testdb

USERNAME: sa

PASSWORD:

Press connect


DOCUMENTATION URL

http: //localhost:8080/v2/api-docs



NOTE: DELETE USER , DELETE TICKET , GET TICKET BY USER ID ETC ARE NOT CREATED BECAUSE ITS NOT IN MENTIONED ON THE REQUIRMENT.

