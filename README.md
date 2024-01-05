# todo-application

## 개발 일지

[23.12.29] API 명세서 작성

[23.12.29] TODO 등록/전체조회/조회/삭제 기능 구현 및 API 테스트 완료 (수정 기능 미구현, 추후 반영 예정)

[24.01.02] 패키지 리팩토링 및 dto 세분화 (멘토님 피드백 반영)

[24.01.03] 코드 리팩토링, TODO 수정(update) 기능 구현, 예외 처리

[24.01.04] Todo 테이블에 수행 여부(is-completed) 추가, Comment 관련 API 구현 완료, 예외 처리 케이스 추가

## 개발 환경 및 사용 도구
- JAVA 17 (JDK 17.0.9)
- IDE: IntelliJ Ultimate
- API 테스트: Swagger 라이브러리
- ERD 작성: ERD Cloud

## API 명세서 (Todo)

|기능|Method|URL|Request|Response
|:---:|:---:|:---:|:---:|:---:|
|등록|POST|/api/todos|Json|등록된 Todo 정보|
|전체 조회|GET|/api/todos| |모든 Todo 정보|
|조회|GET|/api/todos/{id}| |특정 Todo 정보|
|정렬 조회|GET|/api/todos/sorted|?sort=ASC/DESC|정렬된 모든 TODO 정보|
|수정|PUT|/api/todos/{id}|Json|수정/등록된 Todo 정보|
|수행여부 전환|PATCH|/api/todos/{id}| |전환 성공 여부|
|삭제|DELETE|/api/todos/{id}| |삭제 성공 여부|
* Request Json 형식 = {"title": "할일제목", "contents": "할일내용", "date": "수행일"}

## API 명세서 (Comment)

|기능|Method|URL|Request|Response
|:---:|:---:|:---:|:---:|:---:|
|등록|POST|/api/todos/{todoId}/comments|Json|등록한 Comment 정보|
|수정|PUT|/api/todos/{todoId}/comments/{commentId}|Json|수정된 Comment 정보|
|삭제|DELETE|/api/todos/{todoId}/comments/{commentId}|Json|삭제 성공 여부|
* Request Json 형식 (등록, 수정) = {"comment": "댓글내용", "password": "비밀번호"}
* Request Json 형식 (삭제) = {"password": "비밀번호"} -> 비밀번호 일치 시 삭제
