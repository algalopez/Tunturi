docker rm -f tunturi-db
docker rm -f tunturi-app
docker rm -f tunturi-graphite
docker rm -f tunturi-grafana
REM Deploying app, database, graphite and grafana
docker-compose -f docker-compose.yml up -d
