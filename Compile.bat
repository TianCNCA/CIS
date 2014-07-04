
REM clean all .class files out of the bin directory

cd bin
erase /S /Q *.class
cd ..


call setClasspath

javac -d bin\ -cp %classpath% src\app\*.java src\cis\buisness\*.java src\cis\persistance\*.java src\cis\presentation\*.java
javac -d bin\ -cp %classpath% src\tests\objectTests\*.java
javac -d bin\ -cp %classpath% src\tests\buisnessTests\*.java
javac -d bin\ -cp %classpath% src\tests\*.java


pause

