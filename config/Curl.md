### Requests for meal

#### Get meal by id (id=100009)

`curl -X GET "http://localhost:8080/topjava/rest/profile/meals/100009"`

#### Get all meals

`curl -X GET "http://localhost:8080/topjava/rest/profile/meals"`

#### Get meals filtered by date and time

`curl -X GET "http://localhost:8080/topjava/rest/profile/meals/filter??startDate=2020-01-30&startTime=00:00&endDate=2020-01-31&endTime=13:00"`

#### Get meals filtered without params

`curl -X GET "http://localhost:8080/topjava/rest/profile/meals/filter??startDate=&endTime="`

#### Create new meal

`curl -X POST "http://localhost:8080/topjava/rest/profile/meals" -H "Content-Type: application/json" -d '{"dateTime": "2023-11-10T15:30:00", "description": "Новая еда", "calories": 1000}'`

#### Update meal by id (id=100009)

`curl -X PUT "http://localhost:8080/topjava/rest/profile/meals/100009" -H "Content-Type: application/json" -d '{"dateTime": "2023-11-10T12:30:00", "description": "Обновленный ужин", "calories": 600}'`

#### Delete meal by id (id=100009)

`curl -X DELETE "http://localhost:8080/topjava/rest/profile/meals/100009"`
