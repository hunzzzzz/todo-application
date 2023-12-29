# todo-application

## 개발 일지

23.12.29 API 명세서 작성

## API 명세서 (Todo)

|기능|Method|URL|Request|Response
|:---:|:---:|:---:|:---:|:---:|
|TODO 등록|POST|/api/todo|Json|등록한 Todo 정보|
|TODO 전체 조회|GET|/api/todos| |모든 Todo 정보|
|TODO 조회|GET|/api/todo/{id}| |특정 Todo 정보|
|TODO 수정|PUT|/api/todo/{id}|Json|수정/등록 성공 여부|
|TODO 삭제|DELETE|/api/todo/{id}| |삭제 성공 여부
* Request의 Json 형식 = {"todoTitle": "할일제목", "todoContents": "할일내용", "todoDate": "수행일"}
