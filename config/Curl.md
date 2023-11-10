### Requests for meal

#### get
`curl -X GET "http://localhost:8080/topjava/rest/profile/meals/100009"`

#### getAll
`curl -X GET "http://localhost:8080/topjava/rest/profile/meals"`

#### getBetween
`curl -s "http://localhost:8080/topjava/rest/profile/meals/filter??startDate=2020-01-30&startTime=11:00&endDate=2020-01-30&endTime=20:01"`

#### create
`curl -X POST "http://localhost:8080/topjava/rest/profile/meals" -H "Content-Type: application/json" -d '{"dateTime": "2023-11-10T15:30:00", "description": "Новая еда", "calories": 1000}'`

#### update
`curl -X PUT "http://localhost:8080/topjava/rest/profile/meals/100009" -H "Content-Type: application/json" -d '{"dateTime": "2023-11-10T12:30:00", "description": "Обновленный ужин", "calories": 600}'`

#### delete
`curl -X DELETE "http://localhost:8080/topjava/rest/profile/meals/100009"`
