#!/bin/bash
# Author: Ishag Alexanian
# Deploying the war 

TOMCAT=/home/ec2-user/apache-tomcat-7.0.40/webapps

rm -rf $TOMCAT/wikipages
mkdir $TOMCAT/wikipages

cp WebContent/target/wikipages.war $TOMCAT/wikipages/
unzip wikipages.war

