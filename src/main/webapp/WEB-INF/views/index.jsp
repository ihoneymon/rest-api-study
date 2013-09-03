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
        <li>SLiPP.net Wiki : <a href="http://slipp.net/wiki/pages/viewpage.action?pageId=12878219">8주차 REST API 설계 및 구현</a></li>
        <li>프로젝트 설명<ul>
            <li><a href="readme/01.project-architecture.md">01.project-architecture</a></li>
            <li><a href="readme/02.java-source.md">02.java-source</a></li>
            <li><a href="readme/03.rest-api-example.md">03.rest-api-example</a></li>
        </ul>
        </li>
    </ul>
    <hr>
    <h3>왜 REST에 대한 관심을 가지게 되었을까?</h3>
    <ul>
        <li>프론트엔드Front-End와 백엔드Back-End가 분리되기 시작<ul>
            <li>새로운 서비스 개발을 위해 개발작업 진행<ul>
                <li>JSON 형태로 데이터를 제공하는 API를 제공하자고 동의</li>
                <li>RequestMethod(HTTP: GET, POST, PUT, DELETE)와 URL을 이용한 정의</li>
                <li>View 영역이 포함되지 않은 서버사이드Server-side 개발을 진행</li>
            </ul>
            </li>
        </ul>
        </li>
        <li>멀티플랫폼에 대한 지원을 위해 서비스 자원에 대한 아키텍처를 세우고 이용하는 방법을 모색<ul>
            <li>웹 + 모바일 웹</li>
            <li>아이폰, 안드로이드</li>
            <li>그 외(뭐가 있을까?)</li>
        </ul>
        </li>
        <li>OPEN API를 제공하고 싶다.<ul>
            <li>웹 2.0 시대 이후 다양한 매시업Mashup 서비스들이 출현할 수 있었던 밑바탕에는 깔끔하게 정리된 REST API 덕분이 아닐까?</li>
            <li>내가 만든 서비스에서도 OPEN API를 제공하고자 한다.</li>
        </ul>
        </li>
    </ul>
    <h3>REST API 란 무엇인가?</h3>
    <ul>
        <li><p>API(<strong>A</strong>pplication <strong>P</strong>rogramming <strong>I</strong>nterface)란 무엇인가?</p>
            <ul>
                <li><p>API 어렵지 않아요.</p>
                    <ul>
                        <li>우리는 이미 API를 사용하고 있다.</li>
                        <li><p>Java 다른 클래스의 메소드를 사용하는 것도 API를 이용하는 것이다.</p>
    <pre><code>public class Calculator {
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
}</code></pre><blockquote>
                                <p>데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 한다.</p>
                            </blockquote>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
        <li><p>REST</p>
            <blockquote>
                <p>2000년 로이 필딩(Roy Fielding)이 박사학위 청구 논문에서 REST(Representational State Transfer)를 소프트웨어 아키텍처 스타일로 제안한 후 OPEN API를 개발하는 기본으로 급속도로 확산되고 있다.<p/>
                REST는 SOAP이 서비스 지향 구조인 것과 달리 자원지향구조(ROA: Resource Oriented Architecture)로 웹 사이트의 컨텐츠(Text, 이미지, 동영상), DB의 내용 등을 전부 하나의 자원으로 파악하여 각 자원의 고유한 URI(Uniform Resource Identifier)를 부여하고, 해당 자원에 대한 CRUD(Create, Read, Update, Delete) 작업을 HTTP의 기본 명령어인 POST, GET, PUT, DELETE를 통해서 처리한다.</p>
            </blockquote>
        </li>
        <li><p>메소드, CRUD, SQL 비교</p>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Method</th>
                    <th>CRUD</th>
                    <th>SQL</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>POST</td>
                    <td>Create</td>
                    <td>INSERT</td>
                </tr>
                <tr>
                    <td>GET</td>
                    <td>Read</td>
                    <td>SELECT</td>
                </tr>
                <tr>
                    <td>PUT</td>
                    <td>Update</td>
                    <td>UPDATE</td>
                </tr>
                <tr>
                    <td>DELETE</td>
                    <td>Delete</td>
                    <td>DELETE</td>
                </tr>
                </tbody>
            </table>
        </li>
        <li><p>REST API</p>
            <ul>
                <li>REST 구조 스타일에 적합한 Web API를 REST API라고 한다.</li>
                <li>‘REST API’를 제공하는 웹 서비스를 ‘RESTful’하다고 할 수 있다.<ul>
                    <li>RESTful 하다는 것은 뭘까?</li>
                </ul>
                </li>
            </ul>
        </li>
        <li><p>REST API 구현 사례</p>
            <ul>
                <li><a href="http://dev.naver.com/">네이버 개발자센터</a></li>
                <li><a href="http://dna.daum.net/">다음</a></li>
                <li><a href="https://developers.google.com/?hl=ko">Google Developer</a></li>
                <li><a href="https://dev.twitter.com/">Twitter Developer</a></li>
                <li>새주소 REST API 제공</li>
            </ul>
        </li>
        <li><p>REST API 정보제공 방식</p>
            <ul>
                <li>XML</li>
                <li>JSON</li>
                <li>RSS</li>
            </ul>
        </li>
    </ul>
    <hr>
    <h3>REST API 설계 전</h3>
    <ul>
        <li>제공하려고 하는 <strong>자원(리소스, Resource)</strong>은 무엇인가?</li>
        <li>지원할 Content-type은 어떤 것들이 있는가?<ul>
            <li>XML</li>
            <li>JSON</li>
            <li>RSS</li>
        </ul>
        </li>
        <li>구현한 REST API 이용방법을 어떻게 설명할 것인가?<ul>
            <li>Javadoc?</li>
            <li>별도로 REST API를 사용하는 방법을 기술한 메뉴얼?</li>
            <li>둘다!</li>
        </ul>
        </li>
    </ul>
    <h3>REST API 설계</h3>
    <ul>
        <li>REST API를 통해 제공하려고 하는 <strong>자원(리소스, Resource)</strong>는 무엇인가?</li>
        <li><strong>URI 경로path</strong>는 언제 복수로 써야하는가?</li>
        <li>리소스의 상태를 업데이트하려면, 어떤 메소드를 사용해야 하는가?</li>
        <li>CRUD 가 아닌 연산을 어떻게 URL에 매핑하는가?</li>
        <li>특정한 시나리오에 가장 적합한 HTTP응답은 무엇인가?</li>
        <li>리소스 상태 표현의 버전은 어떻게 관리할 수 있는가?</li>
        <li>JSON에 포함된 하이퍼링크는 어떻게 구조화 하는가?</li>
    </ul>
    <h3>URI(Uniform Resource Identifier) 식별자 설계</h3>
    <blockquote>
        <p>식별자라고 할 수 있는 유일한 일은 대상을 나타내는 것이다. 역참조를 할 때가 아니라면 다른 정보를 얻기 위해서 URI의 내용을 들여다보지 말아야 한다.</p>
        <ul>
            <li>URI를 만들때부터 REST API 리소스 모델을 클라이언트 모델에 전달할 수 있어야 한다.</li>
        </ul>
    </blockquote>
    <ul>
        <li>URI 형태<ul>
            <li>규칙 : 슬러시 구분자(/)는 계층관계를 나타내는 데 사용한다.</li>
            <li>규칙 : URI 마지막 문자로 슬래시(/)를 포함하지 않는다.</li>
            <li>규칙 : 하이픈(-)은 URI 가독성을 높이는 데 사용한다.</li>
            <li>규칙 : 밑줄(_)은 URI에 사용하지 않는다.</li>
            <li>규칙 : URI 경로는 소문자가 적합하다.</li>
            <li>규칙 : 파일 확장자(ex: .json, .xml)는 URI에 포함시키지 않는다.</li>
        </ul>
        </li>
        <li>리소스 모델링<blockquote>
            <p>웹서비스의 기반이 되는 URI는 REST API의 자원(리소스, Resource)가 된다.</p>
        </blockquote>
        </li>
        <li>리소스 원형    <ul>
            <li>도큐먼트 : 객체 인스턴스나 데이터베이스 레코드와 유사한 개념</li>
            <li>컬렉션 : 서버에서 관리하는 디렉터리라는 리소스</li>
            <li>스토어 : 클라이언트에서 관리하는 리소스 저장소</li>
        </ul>
        </li>
        <li>URI 경로 디자인<ul>
            <li>규칙 : 도큐먼트 이름으로는 단수 명사를 사용해야 한다.</li>
            <li>규칙 : 컬렉션 이름으로는 복수 명사를 사용해야 한다.</li>
            <li>규칙 : 스토어 이름으로는 복수 명사를 사용해야 한다.</li>
            <li>규칙 : 경로 부분 중 변하는 부분은 유일한 값으로 대체한다.</li>
            <li>규칙 : CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.</li>
        </ul>
        </li>
    </ul>
    <h3>요청메소드(GET/POST/PUT/DELETE)</h3>
    <ul>
        <li>참고 : <a href="http://www.restapitutorial.com/lessons/httpmethods.html">Using HTTP Methods for RESTful Services</a></li>
        <li>메소드별 용도<ul>
            <li>GET : 리소스 상태의 표현을 얻을 때 사용</li>
            <li>POST : 컬렉션에 새로운 리소스를 만들거나 컨트롤러를 실행할 때 사용</li>
            <li>PUT : 새로운 리소스를 스토어에 추가하거나 기존 리소스를 갱신할 때 사용</li>
            <li>DELETE : 리소스 제거</li>
            <li>HEAD</li>
            <li>OPTIONS</li>
        </ul>
        </li>
        <li>REST의 문제점<ul>
            <li>사용할 수 있는 메소드가 4가지 밖에 없다!<ul>
                <li>Send mail 이라던가, Log Writer와 같은 기능(Function or method)은 어떻게 표현해야할까?</li>
            </ul>
            </li>
            <li>이 문제를 해결할 것인가?<ul>
                <li>‘메일을 보낸다send mail’라는 행위의 의미를 바꾼다.<ul>
                    <li>‘POST /employees/1/send-mail’ : 직원 1에게 메일을 보낸다!</li>
                </ul>
                </li>
                <li>문맥상으로 의미 변환이 불가능한 경우<ul>
                    <li>‘PUT’과 URI를 사용하여 제어control의 의미를 부여</li>
                    <li>‘PUT /employees/1/grade’ : 직원 1의 등급을 변경한다.<blockquote>
                        <p>REST 기반의 아키텍처를 설계하려면 가장 어려운 것이 이 URI를 어떻게 정의하는 것이다. REST의 장점 중 하나는 이 URI와 HTTP Method만으로도 쉽게 의미를 파악할 수 있다는 것이기 때문에, URI 정의에 많은 노력을 기울이는 것이 좋다. </p>
                    </blockquote>
                    </li>
                </ul>
                </li>
            </ul>
            </li>
        </ul>
        </li>
    </ul>
    <h3>응답상태코드</h3>
    <ul>
        <li>응답상태코드<ul>
            <li>1xx : 전송 프로토콜 수준의 정보 교환</li>
            <li>2xx : 클라어인트 요청이 성공적으로 수행됨</li>
            <li>3xx : 클라이언트는 요청을 완료하기 위해 추가적인 행동을 취해야 함</li>
            <li>4xx : 클라이언트의 잘못된 요청</li>
            <li>5xx : 서버쪽 오류로 인한 상태코드</li>
        </ul>
        </li>
    </ul>
    <h3>REST 장점/단점</h3>
    <ul>
        <li>장점<ul>
            <li>기존 웹 인프라를 그대로 이용할 있다.</li>
            <li>쉽다.</li>
        </ul>
        </li>
        <li>단점<ul>
            <li>표준이 없어 관리가 어렵다.</li>
        </ul>
        </li>
    </ul>
    <hr>
    <h3><del>OAuth2(할 수 있을까…?)</del></h3>
    <blockquote>
        <p>아쉽지만 다음 기회에!!</p>
        <ul>
            <li>OAuth란 무엇인가?</li>
            <li>OAuth2 프로세스</li>
            <li>OAuth2 예제</li>
        </ul>
    </blockquote>
    <hr>
    <ul>
        <li>팁<ul>
            <li>jQuery를 통해서 ajax통신을 할때, PUT이나 DELETE 메소드로 요청을 날려도 POST로 처리되는 경우<ol>
                <li>web.xml hiddenMethodFilter 추가</li>
                <li><code>&lt;form&gt;&lt;/form&gt;</code> 내에 <code>&lt;input type=&quot;hidden&quot; name=&quot;_method&quot; value=&quot;PUT/DELETE&quot;/&gt;</code> 추가<ul>
                    <li>Spring taglib를 사용하여 폼 생성시에는 <code>&lt;form:form method=&quot;PUT&quot;/&gt;</code>을 사용시 자동으로 추가해줌</li>
                </ul>
                </li>
                <li>json 형태로 요청시에는 form 객체 내에 ‘_method’: ‘put/delete’ 추가 하면 됨</li>
            </ol>
            </li>
        </ul>
        </li>
    </ul>
    <h3>참고사항</h3>
    <ul>
        <li>Gradle : <a href="http://www.gradle.org/"><a href="http://www.gradle.org/">http://www.gradle.org/</a></a><ul>
            <li>Groovy : <a href="http://groovy.codehaus.org/"><a href="http://groovy.codehaus.org/">http://groovy.codehaus.org/</a></a> <a href="http://groovy.codehaus.org/Korean+Beginners+Tutorial">KO</a></li>
        </ul>
        </li>
        <li>Java Config : <a href="http://www.springsource.org/javaconfig"><a href="http://www.springsource.org/javaconfig">http://www.springsource.org/javaconfig</a></a></li>
        <li>REST API<ul>
            <li><a href="http://ko.wikipedia.org/wiki/REST">REST</a> - Wikipedia</li>
            <li><a href="http://www.mimul.com/pebble/default/2012/08/07/1344315512542.html">Web API Design : 개발자에게 사랑받는 API 만들기</a> - Mimuls</li>
            <li><a href="http://plus.url.google.com/url?sa=z&amp;n=1378166758956&amp;url=http%3A%2F%2Fm.blog.naver.com%2FPostView.nhn%3FblogId%3Dminsool%26logNo%3D130116896843&amp;usg=e-6yBM3nb6mn3htDPHVGcr6ZYSI.">RESTful 하게 OPEN API 서비스를</a></li>
            <li><a href="http://bcho.tistory.com/321">REST 연재-1회 REST 아키텍쳐의 기본</a> - 조대협</li>
            <li><a href="http://bcho.tistory.com/344">REST 연재-2회 Advanced REST</a> - 조대협</li>
        </ul>
        </li>
        <li>Sitemesh : <a href="https://github.com/sitemesh"><a href="https://github.com/sitemesh">https://github.com/sitemesh</a></a><blockquote>
            <p>화면구성 중 일부만 변경하여 사용하는 경우에 적합한 템플릿엔진</p>
        </blockquote>
        </li>
        <li><a href="http://www.restapitutorial.com/">Learn REST: A RESTful Tutorial</a></li>
    </ul>

</section>
</body>
</html>