# InternalDSL
This Internal DSL is used to read a CSV file and print the values of it to JSON as a list of objects. These objects are represented as a type constructed by a Builder using Entities, Attributes, and EntityLists. 

* Entities can have Attributes, Entities, and EntityLists.
* Attributes are data read from a CSV file that supports strings and numbers.
* EntityLists can contain multiple Entities.

Currently, two Builder implementations have been made. The PersonCarBuilder constructs a metamodel instance as a Person with various attributes and entities for cars. This metamodel supports two CSV files carData1.csv and carData2.csv using the load method.

The PersonPetBuilder constructs a metamodel instance as a Person with various attributes and entities for pets. This metamodel supports two CSV files petData1.csv and petData1.csv using the load method. 

Running one of the builders will read the CSV file based on the DSL program and print it to JSON. It is recommended to use https://jsonformatter.org/json-pretty-print to look through the JSON output. Additional CSV files with mock data can be created using https://mockaroo.com/
