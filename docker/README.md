# Useful Docker commands

## Building a dockerfile

Build database container

```docker build -f docker/db/Dockerfile -t tunturi-db .```

Build app container

```docker build -f docker/app/Dockerfile -t tunturi-app .```

## Running containers

Run db container created from dockerfile

```
docker run --name db-docker -d -p 10400:3306 tunturi-db 
    -e MARIADB_ROOT_PASSWORD=pass 
    -e MARIADB_DATABASE=tunturi 
    -e MARIADB_USER=user 
    -e MARIADB_PASSWORD=pass 
    -e MARIADB_INITDB_SKIP_TZINFO=1 
    tunturi-db
```

Run app container created from dockerfile

``` docker run --env-file docker/app/application.env -p 10300:10300 --name tunturi-app -d tunturi-app```

## Execute containers interactively

Execute commands

```docker exec -it tunturi-db /bin/bash```

```docker exec -it tunturi-db mysql -uroot -ppass -h127.0.0.1 -P10400 show databases;```

## Other docker commands

```docker ps -a```

```docker stop tunturi-db```

```docker rm tunturi-db```

```docker rmi -f tunturi-```

```docker images -f dangling=true | awk '{print $3}' | xargs docker rmi```

```docker images```

## Command arguments

* -it: Interactive
* -h: host (ex: 127.0.0.1)
* -d: daemon
* -p: port (ex: 1234:4567)
* -e: environment variable
* -f: file
