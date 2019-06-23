# lif-db-exporter

This tool can be used to export the files 'objects_types.xml', 'recipe.xml' and 'recipe_requirements.xml' from a running lif:yo database.

# How to build

Make sure you have a java sdk and gradle installed.

Tested versions:

|Tool |Version |
|-----|--------|
|Java |openjdk 11.0.3 2019-04-16|
|Gradle |5.2.1 |

Clone repository

```bash
git clone git@github.com:modmew8/lif-db-exporter.git

#or

git clone https://github.com/modmew8/lif-db-exporter.git
```

Navigate to cloned repository

```bash
cd lif-db-exporter
```

Compile jar file with gradle

```bash
gradle dist
```

Navigate to `/build/libs`

```bash
cd build/libs
```

Launch

```bash
lif-db-exporter/build/libs$ java -jar lif-db-exporter.jar 
Exporter v0.1.0 started.
Run with: java -jar lif-db-exporter.jar <server> <username> <password> <dbname>
```

Exported xml files will be within same directory.

# Notes

Make sure the lif:yo server is running, so the database is patched entirely.
