 
--------------------------------------
**This is a Spring Boot Application**

Mock Data Setup is already present at start of Application

There are two APIs in this service

**One to Persist Customer and Address info (Address belongs to customer)**

**Curl**

`curl --location --request POST 'localhost:8080/customer/save' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "Fort",
"lastName" : "Tch",
"addressesList" : [
{
"address1" : "example2",
"type" : "HOME",
"city" : "GAME CITY",
"state": "West ",
"zipcode" : "713301"
},
{
"address1" : "example2",
"type" : "OFFICE",
"city" : "gAME URBAN",
"state" : "Mysore",
"zipcode" : "465645"
}
]
}'
`

**_Another to get Customers List(No Filter/ Filter by one param/ filter by multiple param)
i.e., name, city and state_**

**Curl**

Import Curl on PostMan

`curl --location --request GET 'localhost:8080/customer/list?name=PM&city=Ba&state=New'`
