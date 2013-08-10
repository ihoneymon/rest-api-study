REST API
===================

*****

* SLiPP.net Wiki : 8주차 REST API 설계 및 구현 
* 발표관련 문서 : [REST API 설계 및 구현](https://docs.google.com/file/d/0Bz1yn-FO3HI7VXdtQ3k2NmZMU0E/edit?usp=sharing)

*****

### REST API 란 무엇인가? (15~20분)
* API(**A**pplication **P**rogramming **I**nterface)란 무엇인가?
	* API 어렵지 않아요.
		- 우리는 이미 API를 사용하고 있다.
		- Java 다른 클래스의 메소드를 사용하는 것도 API를 이용하는 것이다.
		```
		public class Calculator {
			public int add(int a, int b) {
				return a + b;
			}
		}

		public class TestCalculator {

			private Calculator calculator;

			@Befor
			public void setUp() {
				calculator = new Calculator();
			}

			@Test
			public voic testAdd() {
				int a = 5;
				int b = 10;
				assertThat(calculator.add(a, b), is(5));
			}
		}
		```
	> 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 한다.

* REST
	* REST(Representational State Transfer) 웹의 구조적 스타일에 대한 설명
	* 관련 자료조사 : 박상도(2013/08/10, 토요일)
* REST API
	* REST 구조 스타일에 적합한 Web API를 REST API라고 한다.
	* 'REST API'를 제공하는 웹 서비스를 'RESTful'하다고 할 수 있다.
		- RESTful 한게 뭔디?

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
* REST API 설계
	* URI 경로path는 언제 복수로 써야하는가?
	* 리소스의 상태를 업데이트하려면, 어떤 메소드를 사용해야 하는가?
	* CRUD 가 아닌 연산을 어떻게 URL에 매핑하는가?
	* 특정한 시나니로에 가장 적합한 HTTP응답은 무엇인가?
	* 리소스 상태 표현의 버전은 어떻게 관리할 수 있는가?
	* JSON에 포함된 하이퍼링크는 어떻게 구조화 하는가?

### URI(Uniform Resource Identifier) 식별자 설계
> 식발져라고 할 수 있는 유일한 일은 대상을 나타내는 것이다. 역참조를 할 때가 아니라면 다른 정보를 얻기 위해서 URI의 내용을 들여다보지 말아야 한다.
* URI를 만들때부터 REST API 리소스 모델을 클라이언트 모델에 전달할 수 있어야 한다.

* URI 형태
	* 규칙 : 슬러시 구분자(/)는 계층관계를 나타내는 데 사용한다.
	* 규칙 : URI 마지막 문자로 슬래시(/)를 포함하지 않는다.
	* 규칙 : 하이픈(-)은 URI 가독성을 높이는 데 사용한다.
	* 규칙 : 밑줄(_)은 URI에 사용하지 않는다.
	* 규칙 : URI 경로는 소문자가 적합하다.
	* 규칙 : 파일 확장자(ex: .json, .xml)는 URI에 포함시키지 않는다.
* 리소스 모델링
> 웹서비스의 기반이 되는 URI는 REST API의 자원(리소스, Resource)가 된다.
* 리소스 원형	
	* 도큐먼트 : 객체 인스턴스나 데이터베이스 레코드와 유사한 개념
	* 컬렉션 : 서버에서 관리하는 디렉터리라는 리소스
	* 스토어 : 클라이언트에서 관리하는 리소스 저장소
* URI 경로 디자인
	* 규칙 : 도큐먼트 이름으로는 단수 명사를 사용해야 한다.
	* 규칙 : 컬렉션 이름으로는 복수 명사를 사용해야 한다.
	* 규칙 : 스토어 이름으로는 복수 명사를 사용해야 한다.
	* 규칙 : 경로 부분 중 변하는 부분은 유일한 값으로 대체한다.
	* 규칙 : CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.

### 요청메서드(GET/POST/PUT/DELETE)

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