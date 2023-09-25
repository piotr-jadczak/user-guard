#!/bin/bash
source variables.sh

echo -e "\nDrop database: $dbname"
psql -c "drop database $dbname with (FORCE)" "user=$user password=$password"

echo -e "\nCreate database $dbname"
psql -c "create database $dbname owner $user" "user=$user password=$password"