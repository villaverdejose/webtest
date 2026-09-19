#INSTAL·LACIÓ programari bàsic
sudo apt install -y postgresql
sudo apt install -y tomcat11
sudo apt install -y apache2
sudo apt install -y maven

if ! command -v java &> /dev/null || ! command -v javac &> /dev/null; then
    echo "Java o Javac no s'han trobat. Instal·lant Java 25..."
    sudo apt update && sudo apt install -y openjdk-25-jdk
else
    echo "Java ja està instal·lat:"
    java -version
fi

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