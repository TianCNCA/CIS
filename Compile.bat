REM @echo off

REM
REM This script requires that the "javac" command be on the system command path.
REM This can be accomplished by modifying the path statement below
REM to include "C:\Program Files\Java\jdk1.6.0_03\bin" (or whatever your path is).
REM Alternatively, you could add the path to "javac" to the PATH Environment variable: 
REM   Settings-->Control Panel-->System-->Advanced-->Environment Variables-->Path
REM


REM clean all .class files out of the bin directory

cd bin
erase /S /Q *.class
cd ..


call setClasspath


REM @echo on

javac -d bin\ -cp %classpath%  src\buisness\*.java src\db\*.java src\cis\presentation\*.java

REM javac -d bin\ -cp %classpath% src\srsys\objects\*.java src\srsys\persistence\*.java src\srsys\application\*.java src\srsys\business\*.java src\srsys\presentation\*.java

REM javac -d bin\ -cp %classpath% src\tests\objectTests\*.java
REM javac -d bin\ -cp %classpath% src\tests\businessTests\*.java
javac -d bin\ -cp %classpath% src\tests\C*.java

pause

