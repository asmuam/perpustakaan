template request
URL = http://localhost:8080/return
{
    "memberID": "222",
    "bookID": 5,
    "returnDate": "2023-10-02"
}

URL = http://localhost:8080/borrow
{
    "memberID": "222",
    "bookID": 55,
    "startDate": "2023-09-15",
    "dueDate": "2023-09-30"
}

URL = http://localhost:8080/jsonrpc
getBooks
{
    "jsonrpc": "2.0",
    "method": "getBooks",
    "params": {},
    "id": "2"
}
searchBooks (author or title)
{
    "jsonrpc": "2.0",
    "method": "searchBooks",
    "params": {
        "keyword": "will"
    },
    "id": "1"
}
createBook
{
    "jsonrpc": "2.0",
    "method": "createBook",
    "params": {
        "title": "Belajar Web Service",
        "author": "Budi",
        "description":"Ini adalah
buku web service terbaik"}, "id": "1"
    }
