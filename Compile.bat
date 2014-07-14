
REM clean all .class files out of the bin directory

cd bin
erase /S /Q *.class
cd ..


call setClasspath

javac -d bin\ -cp %classpath% src\cis\persistance\*.java src\app\*.java src\cis\objects\*.java src\cis\business\*.java src\cis\presentation\*.java 
javac -d bin\ -cp %classpath% src\tests\objectTests\*.java
javac -d bin\ -cp %classpath% src\tests\persistanceTests\*.java
javac -d bin\ -cp %classpath% src\tests\integration\*.java
javac -d bin\ -cp %classpath% src\tests\*.java

pause

