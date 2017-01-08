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

'SearchQuery {
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
}'
