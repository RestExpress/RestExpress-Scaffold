RestExpress-Scaffold
====================

Scaffolding projects that are used to generate Maven archetypes for creating new RestExpress projects quickly.

The following project archetypes exist:
* restexpress-minimal - a minimal RestExpress server with no persistence back-end. Useful for compute-only services or service aggregation.
* restexpress-mongodb - a full-up project with persistence in MongoDB and asynchronous cascade-delete support via Domain Eventing.

Please note that these projects often utilize a Maven snapshot repository, so please include the following in your ~/.m2/settings.xml file:
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
```bash
cd minimal
../scripts/create-archetype.sh
```
```bash
cd mongodb
../scripts/create-archetype.sh
```
