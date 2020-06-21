#!/bin/bash

trie=0
numberOfTries=40
secondsBetweenTries=1

#Default values
user=user
pass=pass
url=127.0.0.1
port=10400

# Named arguments
for ARGUMENT in "$@"; do
    KEY=$(echo $ARGUMENT | cut -f1 -d=)
    VALUE=$(echo $ARGUMENT | cut -f2 -d=)
    case "$KEY" in
    url) url=${VALUE} ;;
    port) port=${VALUE} ;;
    user) user=${VALUE} ;;
    pass) pass=${VALUE} ;;
    *) ;;
    esac
done

while [ $((++trie)) -le $numberOfTries ]; do
    status=$(echo "show databases;" | mysql -u $user -p$pass -h$url -P$port 2>/dev/null | grep tunturi)
    if [[ $status == "tunturi" ]]; then
        echo 'Database is ready'
        exit 0
    else
        echo 'Waiting for database' $trie
        sleep $secondsBetweenTries
    fi
done

exit 1
