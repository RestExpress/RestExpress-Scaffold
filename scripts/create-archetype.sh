#! /bin/bash
#
rm -rf .settings/
mvn clean eclipse:clean archetype:create-from-project -Darchetype.properties=./archetype.properties
mvn eclipse:eclipse

sed -i .orig 's/com.strategicgains.restexpress.scaffold.minimal/${package}/g' ./target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
sed -i .orig 's/com.strategicgains.restexpress.scaffold.mongodb/${package}/g' ./target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
