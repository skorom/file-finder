# Overview
A Java + Spring Boot based project to demonstrate some important and famous modern technologies
in the Java microservice world.
The following technologies are used and demonstrated:
 - [Java](https://openjdk.org/projects/jdk/17/)
 - [Spring Boot](https://spring.io/projects/spring-boot)
 - [Maven](https://maven.apache.org/)
 - [PostgreSQL](https://www.postgresql.org/)
 - [Git](https://git-scm.com/)
 - [Docker (Docker Compose)](https://www.docker.com/)
 - [Make](https://www.gnu.org/software/make/)

# Business logic
The microservice itself is capable of 2 high level features
 - Find files in a directory (recursively) by file extension
 - Archive and expose the executed queries (and their results)

 The service has a REST and a graphical user interface ([Swagger UI](https://swagger.io/tools/swagger-ui/))

There will be <b>2 instances</b> from the microservice (On port `8081` and on port `8082`).
The default values can be seen in the following table:

| Description | Instance 1 | Instance 2 |
|-------------| ---------- | ---------- |
| Root URI | http://localhost:8081/ | http://localhost:8082/ |
| Username | instance1 | instance2 |
| Directory | /etc/systemd | /usr/systemd |

# Check your environment
In the following paragraphs, I would like to show you a step-by-step tutorial, how to make sure
that your environment is ready to start the whole project.
If something is not working, visit their official website, and follow their installation guide.

Open a terminal window!
## Make is installed
Check if the `make` command line tool is installed. If you execute `make --version`, you should see something like this:
```shell
$ make --version
> GNU Make 4.2.1
> Built for x86_64-pc-linux-gnu
> Copyright (C) 1988-2016 Free Software Foundation, Inc.
> License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>
> This is free software: you are free to change and redistribute it.
> There is NO WARRANTY, to the extent permitted by law.
```
## Docker is installed (and daemon is running)
Check the docker daemon with the `docker` command:
```shell
$ docker -v
> Docker version 23.0.5, build bc4487a
```
## Git is installed
Check `git` command:
```shell
$ git --version
> git version 2.25.1
```

# Clone the project
Go to your favourite directory through a command line and execute the following command:
## Linux:
`git clone https://github.com/skorom/file-finder.git && cd file-finder`
## Windows:
`git clone https://github.com/skorom/file-finder.git; cd file-finder`
# Start the service
Make sure that you are in the project's directory and start the service by the following command:

`make run`

In the background, 2 instances will be started by the service. The first one will be exposed to the `8081` port,
the second one will be exposed to the `8082` port.

# Interfaces

| Link                                          | Description                                                                                                                  |
|-----------------------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| http://localhost:8081/swagger-ui/index.html   | The main page of the Swagger UI                                                                                              |
| http://localhost:8081/findfile/{filextension} | REST GET endpoint to find the files by a file extension.<br/>Example: http://localhost:8081/findfile/txt                     |
 | http://localhost:8081/history/{username} | REST GET endpoint to get the executed queries (and results) for a user.<br/>Example: http://localhost:8081/history/instance1 |

## Configuration possibilities
In the `Makefile` you can see the configurable variables (described in the [beginning](#business-logic))
Feel free to edit them (before you start up)
```shell
export USERNAME_1 :=instance1
export USERNAME_2 :=instance2
export DIRECTORY_1 :=/etc/systemd
export DIRECTORY_2 :=/usr/systemd
```


# file-finder
A JAVA + Spring Boot microservice to find files by extension. A great end-to-end project from cloud technologies perspective (Java + Maven + Spring Boot + PostgreSQL + Docker/Podman + Make)

