REM to list all the Available topics
REM %KAFKA_HOME%\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --list
REM This command will give you the list of the active brokers between brackets: (not working this cmd)
%KAFKA_HOME%\bin\windows\zookeeper-shell.bat localhost:2181 ls /brokers/ids