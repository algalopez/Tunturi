#!/bin/sh

mysql -u user -ppass < DDL/CreateDatabase.sql
mysql -u user -ppass < DML/PopulateMinimumData.sql
