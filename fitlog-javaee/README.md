TODO:
- Just create an exercise without assigning it anywhere
- Fix DB (random workout index 1 tada 33???)
- CDI / @Inject ???



### Datasource
- A server-side database connection configuration.
- It is not the data itself, but a named connection that the application uses to access the database.

### Where is your data actually stored?
- In an H2 file database configured through WildFly datasource FitLogDS, stored under WildFly’s standalone/data directory.

## MyBatis
- in pom.xml added mybatis library