sudo mysql -u root -e "CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost';
FLUSH PRIVILEGES;"

sudo mysql -u xmeme -pxmememysql -e "DROP DATABASE xmeme;"

sudo mysql -u xmeme -pxmememysql -e  "CREATE DATABASE xmeme DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;use xmeme;"


cd xmeme-backend

mvn spring-boot:run
