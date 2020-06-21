docker build -f "flyway/Dockerfile" -t "tunturi-flyway" ./flyway
docker run --rm --name 'tunturi-flyway' --net=host tunturi-flyway clean migrate

