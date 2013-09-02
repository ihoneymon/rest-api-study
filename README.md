REST API
===================

*****

* SLiPP.net Wiki : [8주차 REST API 설계 및 구현](http://slipp.net/wiki/pages/viewpage.action?pageId=12878219)
* 프로젝트 설명
	* [01.project-architecture](readme/01.project-architecture.md)
	* [02.java-source](readme/02.java-source.md)
	* [03.rest-api-example](readme/03.rest-api-example.md)

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
	- [네이버 개발자센터](http://dev.naver.com/)
	- [다음](http://dna.daum.net/)
	- [Google Developer](https://developers.google.com/?hl=ko)
	- [Twitter Developer](https://dev.twitter.com/)
	- 새주소 REST API 제공
* REST API 정보제공 방식

*****

### REST API 설계
	* URI 경로path는 언제 복수로 써야하는가?
	* 리소스의 상태를 업데이트하려면, 어떤 메소드를 사용해야 하는가?
	* CRUD 가 아닌 연산을 어떻게 URL에 매핑하는가?
	* 특정한 시나니로에 가장 적합한 HTTP응답은 무엇인가?
	* 리소스 상태 표현의 버전은 어떻게 관리할 수 있는가?
	* JSON에 포함된 하이퍼링크는 어떻게 구조화 하는가?

### URI(Uniform Resource Identifier) 식별자 설계
> 식별자라고 할 수 있는 유일한 일은 대상을 나타내는 것이다. 역참조를 할 때가 아니라면 다른 정보를 얻기 위해서 URI의 내용을 들여다보지 말아야 한다.
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

### 요청메소드(GET/POST/PUT/DELETE)
* 참고 : [Using HTTP Methods for RESTful Services](http://www.restapitutorial.com/lessons/httpmethods.html)
* 메소드별 용도
	* GET : 리소스 상태의 표현을 얻을 때 사용
	* POST : 컬렉션에 새로운 리소스를 만들거나 컨트롤러를 실행할 때 사용
	* PUT : 새로운 리소스를 스토어에 추가하거나 기존 리소스를 갱신할 때 사용
	* DELETE : 리소스 제거
	* HEAD
	* OPTIONS
* 팁
	* jQuery를 통해서 ajax통신을 할때, PUT이나 DELETE 메소드로 요청을 날려도 POST로 처리되는 경우
		1. web.xml hiddenMethodFilter 추가
		2. ```<form></form>``` 내에 ```<input type="hidden" name="_method" value="PUT/DELETE"/>``` 추가
			- Spring taglib를 사용하여 폼 생성시에는 ```<form:form method="PUT"/>```을 사용시 자동으로 추가해줌
		3. json 형태로 요청시에는 form 객체 내에 '_method': 'put/delete' 추가 하면 됨

### 응답상태코드
* 응답상태코드
	* 1xx : 전송 프로토콜 수준의 정보 교환
	* 2xx : 클라어인트 요청이 성공적으로 수행됨
	* 3xx : 클라이언트는 요청을 완료하기 위해 추가적인 행동을 취해야 함
	* 4xx : 클라이언트의 잘못된 요청
	* 5xx : 서버쪽 오류로 인한 상태코드
***** 

### OAuth2(할 수 있을까...?)
* OAuth란 무엇인가?
* OAuth2 프로세스
* OAuth2 예제

*****

### 참고사항
* Gradle : [http://www.gradle.org/](http://www.gradle.org/)
	* Groovy : [http://groovy.codehaus.org/](http://groovy.codehaus.org/) [KO](http://groovy.codehaus.org/Korean+Beginners+Tutorial)
* Java Config : [http://www.springsource.org/javaconfig](http://www.springsource.org/javaconfig)
* REST API
    * [REST](http://ko.wikipedia.org/wiki/REST) - Wikipedia
    * [Web API Design : 개발자에게 사랑받는 API 만들기](http://www.mimul.com/pebble/default/2012/08/07/1344315512542.html) - Mimuls
* Sitemesh : [https://github.com/sitemesh](https://github.com/sitemesh)
    > 화면구성 중 일부만 변경하여 사용하는 경우에 적합한 템플릿엔진
* [Learn REST: A RESTful Tutorial](http://www.restapitutorial.com/)