# building-app
a simple web app built with spring boot to represent a building

This application uses the gradle wrapper to build.  Ensure that you have internet, then run 'gradlew build' to build the application.  The gradle wrapper with pull all of the necessary resources and build the application into an Uber Jar which will be located in the build/libs directory.

The db.sql file located in the root directory contains all of the neccessary SQL to create the database that the application uses.  Database connection configuration and application configuration can found in the src/main/resources/application.properties file.  Currently, the app is designed to connect to a postgres database, but that can be changed by editing the jdbc library in the gradle.build file and editing the datasource and jpa dialect settings in the application.properties file.
