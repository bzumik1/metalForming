# MetalForming
This is the code for the backend of the Industrial Edge application aimed at monitoring the metal forming process. 
The basis is the comparison of the measured curve with the reference curve, which is created based on the number of 
cycles selected by the user.
## How to run it locally as JAR
To run the application locally as JAR it is necessary to build the source code with Maven. It can be done with IDE
or with command `mvn -P dev install` where parameter after `-P` determinate the used profile.
It is important for the application to run that the database is running. This can be achieved with command 
`docker run --name DevDatabase -e POSTGRES_PASSWORD=edge -e POSTGRES_DB=MetalForming --restart unless-stopped 
-p 5432:5432 -d postgres` 
which will start the database as Docker container. Then just run command `java -jar metalForming.jar` from
folder where the JAR file is located. 
## Docker images creation
To create docker images for Industrial Edge platform run following command.
`COMPOSE_DOCKER_CLI_BUILD=1 SSH_PRIVATE_KEY="$(cat ~/.ssh/id_rsa)" docker-compose build`
It will create two images one for the database and one for combination of backend and frontend. You can 
add `--no-cache` at the end of previous command when you have trouble to get the latest version of frontend or backend 
because they are cashed.

To be able to run these images locally (not on Edge device) you can edit `RUN ng build --prod` to `RUN ng build` 
in Dockerfile. Then just execute `docker-compose up`.
