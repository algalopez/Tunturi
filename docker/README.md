# Useful Docker commands

## Building a dockerfile

Build database container

```docker build -f docker/db/Dockerfile -t db-docker .```

Build app container

```docker build -f docker/app/Dockerfile -t app-docker .```

## Running containers

Run container created from dockerfile

```docker run --name db-docker -d -p 10301:3306 db-docker```

Run container created directly from images

```docker run -d --name db -p 10301:3306 -e MARIADB_ROOT_PASSWORD=pass -e MARIADB_DATABASE=prueba -e MARIADB_USER=user -e MARIADB_PASSWORD=pass mariadb/server:10.1```

```docker run -d --name db -p 10301:3306 -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=prueba mysql:5.7```

## Orchestrating containers

Services up

```docker-compose -f docker/docker-compose.yml up -d```

Services down

```docker-compose -f docker/docker-compose.yml down```

## Execute container interactively

Execute bash console

```docker exec -it db /bin/bash```

Execute mysql

```docker exec -it db mysql -uroot -ppass -h127.0.0.1 -P10301```

## Other docker commands

```docker pull mariadb:latest```

```docker ps -a```

```docker stop db```

```docker rm db```

```docker rmi db-docker```

```docker images```

## Command arguments

* -it: Interactive
* -h: host (ex: 127.0.0.1)
* -d: daemon
* -p: port (ex: 1234:4567)
* -e: environment variable
* -f: file
