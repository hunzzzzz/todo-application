# todo-application

## 개발 일지

23.12.29 API 명세서 작성

23.12.29 TODO 등록/전체조회/조회/삭제 기능 구현 및 API 테스트 완료 (수정 기능 미구현, 추후 반영 예정)

24.01.02 패키지 리팩토링 및 dto 세분화 (멘토님 피드백 반영)

24.01.03 코드 리팩토링, TODO 수정(update) 기능 구현, 예외 처리

## API 명세서 (Todo)

|기능|Method|URL|Request|Response
|:---:|:---:|:---:|:---:|:---:|
|TODO 등록|POST|/api/todos|Json|등록한 Todo 정보|
|TODO 전체 조회|GET|/api/todos| |모든 Todo 정보|
|TODO 조회|GET|/api/todos/{id}| |특정 Todo 정보|
|TODO 수정|PUT|/api/todos/{id}|Json|수정/등록 성공 여부|
|TODO 삭제|DELETE|/api/todos/{id}| |삭제 성공 여부
* Request의 Json 형식 = {"todoTitle": "할일제목", "todoContents": "할일내용", "todoDate": "수행일"}
* API 테스트 도구 = Swagger 라이브러리
