#!/bin/sh

# docker-compose.ymlで追加したMYSQL_USERに、MySQLの全権限を付与する
echo "GRANT ALL ON *.* TO '"$MYSQL_USER"'@'%' ;" | "${mysql[@]}"
echo 'FLUSH PRIVILEGES ;' | "${mysql[@]}"