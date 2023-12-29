# todo-application

## 개발 일지

23.12.29 API 명세서 작성

## API 명세서 (Todo)

|기능|Method|URL|Request|Response
|:---:|---:|:---:|:---:|:---:|
|TODO 등록|POST|/api/todo|{"todoTitle": "할일제목", "todoContents": "할일내용", "todoDate": "수행일"}|등록한 Todo 정보|
|TODO 전체 조회|GET|/api/todos| |모든 Todo 정보|
|Todo 조회|GET|/api/todo/{id}| |특정 Todo 정보|
|Todo 수정|PUT|/api/todo/{id}|{"todoTitle": "할일제목", "todoContents": "할일내용", "todoDate": "수행일"}|수정/등록 성공 여부|
|Todo 삭제|DELETE|/api/todo/{id}| |삭제 성공 여부
