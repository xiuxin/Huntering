@echo off
echo [INFO]  init schema/data.
echo [INFO] confirm has created db.
cd %~dp0
cd ../web
echo [INFO] init schema.
call mvn db:schema -e -X

echo [INFO] init data.
call mvn db:data -e -X

cd ../bin
pause