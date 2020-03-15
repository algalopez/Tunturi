#!/bin/bash
trie=0
numberOfTries=30
secondsBetweenTries=1

user=$(docker exec tunturi-db bash -c 'echo "$MARIADB_USER"' 2> /dev/null)
pass=$(docker exec tunturi-db bash -c 'echo "$MARIADB_PASSWORD"' 2> /dev/null)
url=$(docker exec tunturi-db bash -c 'echo "$DATABASE_URL"' 2> /dev/null)
port=$(docker exec tunturi-db bash -c 'echo "$DATABASE_PORT"' 2> /dev/null)

while [ $((++trie)) -le $numberOfTries ]
do

  status=$(echo "show databases;" | mysql -u $user -p$pass -h$url -P$port 2> /dev/null | grep tunturi)

  if [[ $status == "tunturi" ]]
  then
    echo Database is ready
    exit 0
  else
    echo 'Waiting for database' $trie
    sleep $secondsBetweenTries
  fi

done

exit 1
