REST API
===================

*****

* 발표관련 문서 : [REST API 설계 및 구현](https://docs.google.com/file/d/0Bz1yn-FO3HI7VXdtQ3k2NmZMU0E/edit?usp=sharing)

*****

### REST API 란 무엇인가? (15~20분)
* API(**A**pplication **P**rogramming **I**nterface)란 무엇인가?
	* API 어렵지 않아요.
		- 우리는 이미 API를 사용하고 있다.
* RESTful 정의
	* 관련 자료조사 : 박상도(2013/08/10, 토요일)
* RESTful API 구현 사례
	* 참조할만한 API 구현사례
		- 네이버
		- 다음
		- Google
		- Twitter
		- 새주소 REST API 제공
* REST API 정보제공 방식

*****

### 프로젝트 구성(15~20분)
* [gradle 을 이용한 프로젝트 구성](https://github.com/ihoneymon/rest-api-study/blob/master/build.gradle)  -> 완료
* ORM(DDD!) : [Hibernate](http://www.hibernate.org/) + [Spring Data JPA](http://www.springsource.org/spring-data/jpa) + [QueryDSL](http://www.querydsl.com/) -> 완료
* web.xml -> Java config 이용 -> 미진행
* [Thymeleaf](http://www.thymeleaf.org/) 템플릿 엔진 적용 : JSP -> HTML -> 미진행(2013/08/10, 토요일까지 완료예정)

### 도메인Domain 설계
* 모델
	* [기업Company](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/Company.java)
		- id
		- name
		- address
		- tel
		- rootDepartment
    * [부서Department](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/Department.java)
        - id
        - name
        - description
        - parent
        - subDepartments
	* [직원Employee](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/Employee.java)
		- id
		- email
		- name
		- nickname
		- company
		- department
		- createdDate
	* [사용자User](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/User.java)
	    - id
	    - email
	    - password
	    - employee
	    - roleGrantedAuthorities
	* [권한RoleAuthority](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/RoleAuthority.java)
	    - role
	    - description
	    - createdDate

*****

### 프로젝트에서 구현한 REST API 소개 및 구현예제(15~20분)
> 직원 관리 기능 구현(UI 포함) 후 해당 기능을 보여주면서 설명

* [기업CompanyController](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/CompanyController.java)
	* GET /companies : 목록
	* GET /companies/{company} : 기업상세
	* POST /companies : 등록
	* PUT /companies/{company} : 수정
	* DEL /companies/{company} : 삭제
* [부서DepartmentController](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/DepartmentController.java)
	* GET /departments
	* GET /departments/{department}
	* POST /departments
	* PUT /departments/{department}
	* DEL /departments/{department}
* [직원EmployeeController](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/EmployeeController.java)
	* GET /employees
	* GET /employees/{employee}
	* POST /employees
	* PUT /employees/{employee}
	* DEL /employees/{employee}
* [사용자UserController](https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/UserController.java)
	* GET /companies/{company}/users : 사용자 목록
	* POST /companies/{company}/users : 사용자 추가
	* GET /companies/{company}/users/{user} : 사용자 상세
	* PUT /companies/{company}/users/{user} : 사용자 수정
	* DEL /companies/{company}/users/{user} : 사용자 삭제

*****

### 논의
* REST API 설계시 고려사항
	- ??? : XML vs JSON : 선호하는 형태?!
	- Resource

* REST API 작명법
	* 복수형 vs 단수형
	* CRUD 유사 작명법
		- 기존 사용방식(/add, /remove, /edit(or modify), /list)
			- GET, POST(화면단에서 폼에 입력된 값을 submit으로 처리하는 경우)

* REST API Method별 고려사항
	- Form 으로 처리
	- ajax를 이용한 처리

***** 

### OAuth2(할 수 있을까...?)
* OAuth란 무엇인가?
* OAuth2 프로세스
* OAuth2 예제

*****

### 사용모듈
* [build.gradle](https://github.com/ihoneymon/rest-api-study/blob/master/build.gradle) 참조 

*****

### 참고사항
* Thymeleaf : [http://www.thymeleaf.org/](http://www.thymeleaf.org/)
* Gradle : [http://www.gradle.org/](http://www.gradle.org/)
* Java Config : [http://www.springsource.org/javaconfig](http://www.springsource.org/javaconfig)
* REST API
    * [REST](http://ko.wikipedia.org/wiki/REST) - Wikipedia
    * [Web API Design : 개발자에게 사랑받는 API 만들기](http://www.mimul.com/pebble/default/2012/08/07/1344315512542.html) - Mimuls