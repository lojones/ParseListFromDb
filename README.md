Given a table of results, how do you extract the data into java objects (pojo's) if one object consists of data from multiple rows?

In this example the data from the db looks something like this

| ID | City |  
| -- | ---- |  
| 1 | Toronto |  
| 1 | London |  
| 1 | NY |  
| 2 | LA |  
| 2 | Tokyo |  

This gets converted to a list of pojos that looks like this

User
id:1
cities:Toronto,London,NY

User
id:2
cities:LA,Tokyo
