docker-compose -f docker-compose.yml down --remove-orphans
#docker rm tunturi-db
#docker rm tunturi-app
#docker rm tunturi-graphite
#docker rm tunturi-grafana

docker stop tunturi-flyway
docker rm -f tunturi-flyway
