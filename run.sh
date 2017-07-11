#!/usr/bin/env bash

wget -nc -O jetty-runner.jar http://central.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.4.3.v20170317/jetty-runner-9.4.3.v20170317.jar

echo http://localhost:8080/hello?target=world
echo http://localhost:8080/length?target=world
java -jar jetty-runner.jar target/maintainable-code-2-1.0.war
