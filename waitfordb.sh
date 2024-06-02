#!/bin/bash

# waitfordb.sh
# script to exit if any subsequent commands fail.
set -e

host="$1"
shift
cmd="$@"

until mysql -h"$host" -u"sakila" -p"p_ssW0rd" -e 'SELECT 1'; do
  >&2 echo "MySQL is unavailable - sleeping"
  sleep 1
done

>&2 echo "MySQL is up - executing command"
exec $cmd