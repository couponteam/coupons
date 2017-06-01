#! /bin/bash
mvn clean package -DskipTests
scp -r /Users/Paul/Documents/workspace/linghuiquan/coupon/target/coupon.war root@119.23.242.204:/opt/8080/webapps


