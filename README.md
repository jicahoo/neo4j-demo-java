# neo4j-demo-java
Use Java to access neo4j

## How to run
1. Check the README in Python version demo: https://github.com/jicahoo/neo4j-demo which contains more useful info.
2. Import this project to IntelliJ IDEA.
3. In IntelliJ, just run the main method in Main.java

## ETL
* For large data, please use `import` command of `neo4j-admin`: http://neo4j.com/docs/operations-manual/current/tools/import/
* https://neo4j.com/developer/guide-importing-data-and-etl/
* https://neo4j.com/blog/rdbms-neo4j-etl-tool/ (Not mature enough)
* https://neo4j.com/blog/rdbms-graph-database-deployment-strategies/ 


## Work flow
1. Design the CSV file format which can be loaded into neo4j
2. Write Java code based on JDBC to dump the RDBMS data to CSV file.
3. Use neo4j tool: `neo4j-admin import` to load the CSV file to your neo4j database.
4. Change the demo code in this repo to query the data that customer want.
