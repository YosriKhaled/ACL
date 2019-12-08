# gucBooks
This application allows guc students to view the books in the guc library and check their availability. To run the application run the nodejs application on your machine or on docker and then run the android app.

## Install Dependencies and running node on your machine 

```
Download and open the project using your favorite ide for node
open the terminal and the write the following commands:
npm init //to install the dependencies
node index.js //to run the project
```

## Config

```
make sure to setup your config files first before running by setting up your config directory
and the keys.js file and feeding them the database base name and password also the key for mockaroo api. An example for the config/keys.js is given below
module.exports = {
  mongoURI: "mongodb+srv://<YOUR DATABASE CLUSTER NAME>:<YOUR DATABASE CLUSTER PASSWORD>@cluster0-ws7fn.mongodb.net/test?retryWrites=true",
  apiKey:'MOCKAROO KEY'
}
```
## Docker
```
to run the node application on docker open your docker terminal at your project directory
and write the following commands:
docker build -t <your username>/node-mobile-app .
docker run -p 3000:3000 -d <your username>/node-mobile-app
# Get container ID
$ docker ps
# Print app output
$ docker logs <container id>
```

## Docker-compose
```
to run docker-compose.yml file open docker terminal at your project directory
and write the following command:
docker-compose up
```












