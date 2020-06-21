#!/bin/sh

mysql -u user -ppass -h127.0.0.1 -P10400 < database/migration/V1_0_0_1__create_meta_information.sql
mysql -u user -ppass -h127.0.0.1 -P10400 < database/migration/V1_0_0_2__create_auth.sql
mysql -u user -ppass -h127.0.0.1 -P10400 < database/migration/V1_0_0_3__create_user.sql
