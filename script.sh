#!/bin/bash

echo "Starting build process..."
mvn package
echo "Build done!"
echo "Building javadoc..."
mvn javadoc:javadoc
echo "Javadoc completed!"
echo "Running program..."
java -jar target/Project_grupp6-1.0-SNAPSHOT.jar
