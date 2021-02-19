

# Setting up the database
sudo mysql -e "SET PASSWORD FOR root@localhost = PASSWORD('root');FLUSH PRIVILEGES;"
sudo mysql -e "DELETE FROM mysql.user WHERE User='';"
sudo mysql -e "DELETE FROM mysql.user WHERE User='root' AND Host NOT IN ('localhost', '127.0.0.1', '::1');"
#sudo mysql -u root -proot -e "CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';FLUSH PRIVILEGES;"
sudo mysql -e "Alter user 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Current-Root-Password';FLush privileges;"
sudo mysql -uroot -proot -e "Alter user 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Current-Root-Password';FLush privileges;"
sudo mysql -uroot -proot -e 'create database IF NOT EXISTS xmeme;'
service mysql restart





















