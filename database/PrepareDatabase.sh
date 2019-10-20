#!/bin/sh

mysql -u user -ppass -h127.0.0.1 -P10301 < database/DDL/CreateDatabase.sql
mysql -u user -ppass -h127.0.0.1 -P10301 < database/DDL/CreateDatabaseAuth.sql
mysql -u user -ppass -h127.0.0.1 -P10301 < database/DDL/CreateDatabaseUser.sql
