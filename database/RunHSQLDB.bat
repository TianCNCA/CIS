@echo off

set CLASSPATH=.;..\lib\hsqldb.jar;%CLASSPATH%

@echo on

java org.hsqldb.util.DatabaseManagerSwing -url "jdbc:hsqldb:ClientSystem"

pause