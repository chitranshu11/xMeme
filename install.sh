sudo apt-get update -y && sudo apt-get dist-upgrade -y
sudo apt-get install -y openjdk-8-jdk
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin

sudo apt update

sudo apt-get install -y maven

echo $JAVA_HOME
export PATH=/opt/apache-maven-3.6.3/bin:$PATH


# Installing MySQL
sudo apt-get install -q -y -o Dpkg::Options::="--force-confdef" mysql-server;

# Setting up the database
sudo mysql -e "SET PASSWORD FOR root@localhost = PASSWORD('root');FLUSH PRIVILEGES;"
sudo mysql -e "DELETE FROM mysql.user WHERE User='';"
sudo mysql -e "DELETE FROM mysql.user WHERE User='root' AND Host NOT IN ('localhost', '127.0.0.1', '::1');"
sudo mysql -u root -proot -e "CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';FLUSH PRIVILEGES;"
sudo mysql -uroot -proot -e 'create database IF NOT EXISTS xmeme;'
service mysql restart





















