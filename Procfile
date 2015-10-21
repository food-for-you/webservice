#db:     heroku pg:psql < db/food.sql
#jdbc:   cp src/main/resources/jdbc_heroku.properties src/main/resources/jdbc.properties
web:    java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port 8080 target/*.war
