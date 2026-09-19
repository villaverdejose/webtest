sudo apt install postgresql
sudo apt install tomcat11
sudo apt install apache2
sudo apt install maven

#CREAR la base de dades i afegir dades
sudo -u postgres psql -U postgres -f ./db/startup.sql

# COMPILACIO CODI
cd ./backend/
mvn clean package

#COPIAR .WAR a tomcat11
sudo cp ./target/backend.war /var/lib/tomcat11/webapps/.

#REINICI tomcat11
sudo systemctl stop tomcat11
sudo systemctl start tomcat11

#COPIAR index.html a l'apache
cd ..
sudo cp ./frontend/index.html /var/www/html/.