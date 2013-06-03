REST API 스터디 계획
===================

### 프로젝트 구성
	* gradle 을 이용한 프로젝트 구성
	* web.xml -> Java config 이용
	* ORM : Hibernate + Spring Data JPA + QueryDSL
	* Thymeleaf 템플릿 엔진 적용 : JSP -> HTML 

### REST API 설계
	* 모델
		* 기업Company
			- id
			- name
			- address
			- tel
			- users
		* 직원Employee
			- id
			- email
			- name
			- nickname
			- departName
			- createdDate
	* REST API
		* 기업
			- GET /companies : 목록
			- POST /companies : 등록
			- GET /companies/{company} : 기업상세
			- PUT /companies/{company} : 수정
			- DEL /companies/{company} : 삭제
		* 사용자
			- GET /companies/{company}/users : 사용자 목록
			- POST /companies/{company}/users : 사용자 추가
			- GET /companies/{company}/users/{user} : 사용자 상세
			- PUT /companies/{company}/users/{user} : 사용자 수정
			- DEL /companies/{company}/users/{user} : 사용자 삭제
	* 논의
		* REST API 설계시 고려사항
			- 복수형 vs 단수형
			- RequestMethod 구별방법
		* REST API 작명법
		* REST API Method별 고려사항
			- Form 으로 처리
			- ajax를 이용한 처리

### OAuth2
	* OAuth란 무엇인가?
	* OAuth2 프로세스
	* OAuth2 예제

### 참고사항
	* Thymeleaf : http://www.thymeleaf.org/
	* Gradle
	* Java Config
	* REST API
		* 