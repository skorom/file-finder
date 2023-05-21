export USERNAME_1 :=instance1
export USERNAME_2 :=instance2
export DIRECTORY_1 :=/etc/systemd
export DIRECTORY_2 :=/usr/systemd

build:
	mvn clean package
build-docker:
	docker build -t file-finder .
run: build-docker
	docker-compose up
stop:
	docker-compose down

default: build