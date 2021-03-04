#BUILDER
FROM alpine AS builder

WORKDIR /src

# installing packages
RUN apk update && apk upgrade \
&& apk add \
      git \
      maven \
      nodejs \
      nodejs-npm \
      openjdk11 \
      openssh \
&& npm install -g @angular/cli

# add credentials on build
ARG SSH_PRIVATE_KEY
RUN mkdir /root/.ssh \
&& echo "${SSH_PRIVATE_KEY}" > /root/.ssh/id_rsa \
&& chmod 600 /root/.ssh/id_rsa \
&& ssh-keyscan github.com >> /root/.ssh/known_hosts \
&& ssh-keyscan code.siemens.com >> /root/.ssh/known_hosts

#cloning repositories
RUN git clone git@code.siemens.com:apcprague/edge/metal-forming-fe.git
RUN git clone git@github.com:bzumik1/metalForming.git

# builds front-end
WORKDIR /src/metal-forming-fe
RUN npm ci
RUN ng build

# builds back-end with front-end in static folder
WORKDIR /src/metalForming
RUN cp -a /src/metal-forming-fe/dist/metal-forming/. /src/metalForming/src/main/resources/static \
&& mvn install -DskipTests=true





#PRODUCTION CONTAINER
FROM adoptopenjdk/openjdk11:debian-slim
LABEL maintainer="jakub.znamenacek@siemens.com"
RUN mkdir app
RUN ["chmod", "+rwx", "/app"]
WORKDIR /app
COPY --from=builder /src/metalForming/target/metal_forming-0.0.1-SNAPSHOT.jar .
EXPOSE 4200
RUN java -version
CMD java -jar metal_forming-0.0.1-SNAPSHOT.jar