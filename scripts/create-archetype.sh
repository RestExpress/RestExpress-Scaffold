#! /bin/bash
#
rm -rf .settings/
find . -name bin | xargs rm -rf 
mvn clean eclipse:clean archetype:create-from-project -Darchetype.properties=./archetype.properties

sed -i .orig 's/org.restexpress.scaffold.cassandra/${package}/g' ./target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
sed -i .orig 's/org.restexpress.scaffold.minimal/${package}/g' ./target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
sed -i .orig 's/org.restexpress.scaffold.mongodb/${package}/g' ./target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
