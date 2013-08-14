<%@ page language="java" isELIgnored="false"
         contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
    <title>REST API STUDY Page</title>
    <meta name="decorator" content="common">
    <!-- stylesheet -->

    <!-- javascript -->
    <script src="<spring:url value="/resources/js/index.js" />"></script>
</head>

<body>
<section>
<h1>REST API</h1>
<hr>
<ul>
    <li>SLiPP.net Wiki : 8주차 REST API 설계 및 구현</li>
    <li>발표관련 문서 : <a
            href="https://docs.google.com/file/d/0Bz1yn-FO3HI7VXdtQ3k2NmZMU0E/edit?usp=sharing">REST
        API 설계 및 구현</a></li>
</ul>
<hr>
<h3>REST API 란 무엇인가? (15~20분)</h3>
<ul>
    <li><p>
        API(<strong>A</strong>pplication <strong>P</strong>rogramming <strong>I</strong>nterface)란
        무엇인가?
    </p>
        <ul>
            <li><p>API 어렵지 않아요.</p>
                <ul>
                    <li>우리는 이미 API를 사용하고 있다.</li>
                    <li><p>Java 다른 클래스의 메소드를 사용하는 것도 API를 이용하는 것이다.</p>
                        <pre><code>
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
                        </code></pre>
                        <blockquote>
                            <p>데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록
                                한다.</p>
                        </blockquote>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    <li><p>REST</p>
        <ul>
            <li>REST(Representational State Transfer) 웹의 구조적 스타일에 대한 설명</li>
            <li>관련 자료조사 : 박상도(2013/08/10, 토요일)</li>
        </ul>
    </li>
    <li><p>REST API</p>
        <ul>
            <li>REST 구조 스타일에 적합한 Web API를 REST API라고 한다.</li>
            <li>‘REST API’를 제공하는 웹 서비스를 ‘RESTful’하다고 할 수 있다.
                <ul>
                    <li>RESTful 한게 뭔디?</li>
                </ul>
            </li>
        </ul>
    </li>
    <li><p>RESTful API 구현 사례</p>
        <ul>
            <li>참조할만한 API 구현사례
                <ul>
                    <li>네이버</li>
                    <li>다음</li>
                    <li>Google</li>
                    <li>Twitter</li>
                    <li>새주소 REST API 제공</li>
                </ul>
            </li>
        </ul>
    </li>
    <li>REST API 정보제공 방식</li>
</ul>
<hr>
<h3>프로젝트 구성(15~20분)</h3>
<ul>
    <li><a
            href="https://github.com/ihoneymon/rest-api-study/blob/master/build.gradle">gradle
        을 이용한 프로젝트 구성</a> -&gt; 완료
    </li>
    <li>ORM(DDD!) : <a href="http://www.hibernate.org/">Hibernate</a>
        + <a href="http://www.springsource.org/spring-data/jpa">Spring
            Data JPA</a> + <a href="http://www.querydsl.com/">QueryDSL</a> -&gt; 완료
    </li>
    <li>web.xml -&gt; Java config 이용 -&gt; 미진행</li>
    <li>
        <del>
            <a href="http://www.thymeleaf.org/">Thymeleaf</a> 템플릿 엔진 적용 : JSP
            -&gt; HTML -&gt; 미진행(2013/08/10, 토요일까지 완료예정)
        </del>
        <ul>
            <li>TAGLIB를 사용할 수 없는 불편함이 있어서 JSP로 변경함</li>
            <li>SITE-MESH로 변경</li>
        </ul>
    </li>
</ul>
<h3>도메인Domain 설계</h3>
<ul>
    <li>모델
        <ul>
            <li><a
                    href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/Company.java">기업Company</a>
                <ul>
                    <li>id</li>
                    <li>name</li>
                    <li>address</li>
                    <li>tel</li>
                    <li>rootDepartment</li>
                </ul>
            </li>
            <li><a
                    href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/Department.java">부서Department</a>
                <ul>
                    <li>id</li>
                    <li>name</li>
                    <li>description</li>
                    <li>parent</li>
                    <li>subDepartments</li>
                </ul>
            </li>
            <li><a
                    href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/Employee.java">직원Employee</a>
                <ul>
                    <li>id</li>
                    <li>email</li>
                    <li>name</li>
                    <li>nickname</li>
                    <li>company</li>
                    <li>department</li>
                    <li>createdDate</li>
                </ul>
            </li>
            <li><a
                    href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/User.java">사용자User</a>
                <ul>
                    <li>id</li>
                    <li>email</li>
                    <li>password</li>
                    <li>employee</li>
                    <li>roleGrantedAuthorities</li>
                </ul>
            </li>
            <li><a
                    href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/domain/RoleAuthority.java">권한RoleAuthority</a>
                <ul>
                    <li>role</li>
                    <li>description</li>
                    <li>createdDate</li>
                </ul>
            </li>
        </ul>
    </li>
</ul>
<hr>
<h3>프로젝트에서 구현한 REST API 소개 및 구현예제(15~20분)</h3>
<blockquote>
    <p>직원 관리 기능 구현(UI 포함) 후 해당 기능을 보여주면서 설명</p>
</blockquote>
<ul>
    <li><a
            href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/CompanyController.java">기업CompanyController</a>
        <ul>
            <li>GET /companies : 목록</li>
            <li>GET /companies/{company} : 기업상세</li>
            <li>POST /companies : 등록</li>
            <li>PUT /companies/{company} : 수정</li>
            <li>DEL /companies/{company} : 삭제</li>
        </ul>
    </li>
    <li><a
            href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/DepartmentController.java">부서DepartmentController</a>
        <ul>
            <li>GET /departments</li>
            <li>GET /departments/{department}</li>
            <li>POST /departments</li>
            <li>PUT /departments/{department}</li>
            <li>DEL /departments/{department}</li>
        </ul>
    </li>
    <li><a
            href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/EmployeeController.java">직원EmployeeController</a>
        <ul>
            <li>GET /employees</li>
            <li>GET /employees/{employee}</li>
            <li>POST /employees</li>
            <li>PUT /employees/{employee}</li>
            <li>DEL /employees/{employee}</li>
        </ul>
    </li>
    <li><a
            href="https://github.com/ihoneymon/rest-api-study/blob/master/src/main/java/net/slipp/rest/controller/UserController.java">사용자UserController</a>
        <ul>
            <li>GET /companies/{company}/users : 사용자 목록</li>
            <li>POST /companies/{company}/users : 사용자 추가</li>
            <li>GET /companies/{company}/users/{user} : 사용자 상세</li>
            <li>PUT /companies/{company}/users/{user} : 사용자 수정</li>
            <li>DEL /companies/{company}/users/{user} : 사용자 삭제</li>
        </ul>
    </li>
</ul>
<hr>
<h3>논의</h3>
<ul>
    <li>REST API 설계
        <ul>
            <li>URI 경로path는 언제 복수로 써야하는가?</li>
            <li>리소스의 상태를 업데이트하려면, 어떤 메소드를 사용해야 하는가?</li>
            <li>CRUD 가 아닌 연산을 어떻게 URL에 매핑하는가?</li>
            <li>특정한 시나니로에 가장 적합한 HTTP응답은 무엇인가?</li>
            <li>리소스 상태 표현의 버전은 어떻게 관리할 수 있는가?</li>
            <li>JSON에 포함된 하이퍼링크는 어떻게 구조화 하는가?</li>
        </ul>
    </li>
</ul>
<h3>URI(Uniform Resource Identifier) 식별자 설계</h3>
<blockquote>
    <p>식발져라고 할 수 있는 유일한 일은 대상을 나타내는 것이다. 역참조를 할 때가 아니라면 다른 정보를 얻기 위해서
        URI의 내용을 들여다보지 말아야 한다. * URI를 만들때부터 REST API 리소스 모델을 클라이언트 모델에 전달할 수
        있어야 한다.</p>
</blockquote>
<ul>
    <li>URI 형태
        <ul>
            <li>규칙 : 슬러시 구분자(/)는 계층관계를 나타내는 데 사용한다.</li>
            <li>규칙 : URI 마지막 문자로 슬래시(/)를 포함하지 않는다.</li>
            <li>규칙 : 하이픈(-)은 URI 가독성을 높이는 데 사용한다.</li>
            <li>규칙 : 밑줄(_)은 URI에 사용하지 않는다.</li>
            <li>규칙 : URI 경로는 소문자가 적합하다.</li>
            <li>규칙 : 파일 확장자(ex: .json, .xml)는 URI에 포함시키지 않는다.</li>
        </ul>
    </li>
    <li>리소스 모델링
        <blockquote>
            <p>웹서비스의 기반이 되는 URI는 REST API의 자원(리소스, Resource)가 된다.</p>
        </blockquote>
    </li>
    <li>리소스 원형
        <ul>
            <li>도큐먼트 : 객체 인스턴스나 데이터베이스 레코드와 유사한 개념</li>
            <li>컬렉션 : 서버에서 관리하는 디렉터리라는 리소스</li>
            <li>스토어 : 클라이언트에서 관리하는 리소스 저장소</li>
        </ul>
    </li>
    <li>URI 경로 디자인
        <ul>
            <li>규칙 : 도큐먼트 이름으로는 단수 명사를 사용해야 한다.</li>
            <li>규칙 : 컬렉션 이름으로는 복수 명사를 사용해야 한다.</li>
            <li>규칙 : 스토어 이름으로는 복수 명사를 사용해야 한다.</li>
            <li>규칙 : 경로 부분 중 변하는 부분은 유일한 값으로 대체한다.</li>
            <li>규칙 : CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.</li>
        </ul>
    </li>
</ul>
<h3>요청메서드(GET/POST/PUT/DELETE)</h3>
<hr>
<h3>OAuth2(할 수 있을까…?)</h3>
<ul>
    <li>OAuth란 무엇인가?</li>
    <li>OAuth2 프로세스</li>
    <li>OAuth2 예제</li>
</ul>
<hr>
<h3>사용모듈</h3>
<ul>
    <li><a
            href="https://github.com/ihoneymon/rest-api-study/blob/master/build.gradle">build.gradle</a>
        참조
    </li>
</ul>
<hr>
<h3>참고사항</h3>
<ul>
    <li>Thymeleaf : <a href="http://www.thymeleaf.org/"><a
            href="http://www.thymeleaf.org/">http://www.thymeleaf.org/</a></a></li>
    <li>Gradle : <a href="http://www.gradle.org/"><a
            href="http://www.gradle.org/">http://www.gradle.org/</a></a></li>
    <li>Java Config : <a
            href="http://www.springsource.org/javaconfig"><a
            href="http://www.springsource.org/javaconfig">http://www.springsource.org/javaconfig</a></a></li>
    <li>REST API
        <ul>
            <li><a href="http://ko.wikipedia.org/wiki/REST">REST</a> -
                Wikipedia
            </li>
            <li><p>[Web API Design : 개발자에게 사랑받는 API 만들기]</p>(<a
                    href="http://www.mimul.com/pebble/default/2012/08/07/1344315512542.html">http://www.mimul.com/pebble/default/2012/08/07/1344315512542.html</a>)
                - Mimuls
            </li>
        </ul>
    </li>
</ul>

</section>
</body>
</html>