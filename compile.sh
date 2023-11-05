javac -d . *.java

jar cfe Asteroids.jar Main *.class

rm *.class

java -jar Asteroids.jar
