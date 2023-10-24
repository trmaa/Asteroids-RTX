javac -d . *.java

jar cfe Asteroids.jar Main *.class

del *.class

java -jar Asteroids.jar