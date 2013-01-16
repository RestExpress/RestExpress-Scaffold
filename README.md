RestExpress-Scaffold
====================

Maven archetypes for creating new RestExpress projects quickly.

The following project archetypes exist:
* restexpress-minimal - a minimal RestExpress server with no persistence back-end. Useful for compute-only services or service aggregation.
* restexpress-mongodb - a full-up project with persistence in MongoDB and asynchronous cascade-delete support via Domain Eventing.

In addition to the above archetypes, the scripts/ directory contains shell scripts to invoke them so you don't have to remember the entire set of groupId, archetypeId, etc.
The scripts are:
* RestExpress-minimal.sh
* RestExpress-mongodb.sh

Please note that these projects often utilize a Maven snapshot repository, so please include the following in your .m2/settings.xml file:
```xml
<profiles>
  <profile>
     <id>allow-snapshots</id>
        <activation><activeByDefault>true</activeByDefault></activation>
     <repositories>
       <repository>
         <id>snapshots-repo</id>
         <url>https://oss.sonatype.org/content/repositories/snapshots</url>
         <releases><enabled>false</enabled></releases>
         <snapshots><enabled>true</enabled></snapshots>
       </repository>
     </repositories>
   </profile>
</profiles>
```

Generating Archetypes
=====================
To generate each archetype, use the ./scripts/create-archetype.sh shell script.  For example:
```
cd minimal
../scripts/create-archetype.sh
```
