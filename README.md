# json-memory-nosql

json-memory-nosql manage nosql database in memory

## add new document into a repository

> POST /{repository}

> Parameters
> 
> repository	required	repository name

> body		required	document in json format

## Launch a search query into a repository

> POST /

> Parameters
>
> body	required	SeachQuery contains repository name in repositoryName field

```java
SearchQuery {
	repositoryName (string, optional),
	uniqueValue (UniqueValue, optional),
	filterQuery (FilterQuery, optional)
}
UniqueValue {
	fieldPathName (string, optional)
}
FilterQuery {
	fieldPathName (string, optional),
	value (string, optional)
}
```

## build

> mvn clean install

## buil with integrated test
> mvn clean install -P IntegrationTest

## run
java -jar json-memory-nosql-1.0-SNAPSHOT-swarm.jar

## deploy in docker container

> Dockerfile
> FROM java:openjdk-8-jdk

> ADD json-memory-nosql-1.0-SNAPSHOT.jar /opt/json-memory-nosql.jar
> ADD json-memory-nosql-1.0-SNAPSHOT-swarm.jar /opt/json-memory-nosql-swarm.jar

> ENTRYPOINT ["java", "-jar", "/opt/json-memory-nosql-swarm.jar"]

> docker-compose.yml
> json-memory-nosql:
>    container_name: json-memory-nosql
>    hostname: json-memory-nosql
>    domainname: {domainname}
>    build:  .

>
> docker-compose build
> docker-compose up &

