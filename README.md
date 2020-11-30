# ddcw-technical-test

## About

Technical Test backend service.

## Service Definitions

The port for this service is 1235

## Run the application locally

To run the application execute
```
sbt run
```

## GET Homepage
```
/home
```


## Assumptions
User passes in json to textfield in format 
```json
{ "operationType" : "deposit", "amount" : 15}
```
where operationType can either be; deposit, balance or withdraw
OperationType is always entered as lowercase and the amount should not be a negative number.
WHen operationType is balance, amount should be null or it is ignored 

##Improvements/Further Implementation
* Create separate controller for account operations (unable to do this now as front end can not change)
* Return different error codes for different failures (front end sees everything that is a non 200 response as an error so no point)
* Implement a database so we dont have to use a var 