# springboot-rabbitmq
Spring Boot rabbitmq example

Driver application

first request to 127.0.0.1:8080/sendMessage?message= (default message=hi) and this will send a message to quick-message.
second request to 127.0.0.1:8080/sendMessage will show the response from quick-message. 

These operations will run synchronously.
