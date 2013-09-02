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
        <li>SLiPP.net Wiki : <a href="http://slipp.net/wiki/pages/viewpage.action?pageId=12878219">8주차 REST API 설계 및
            구현</a></li>
        <li>프로젝트 설명
            <ul>
                <li><a href="readme/01.project-architecture.md">01.project-architecture</a></li>
                <li><a href="readme/02.java-source.md">02.java-source</a></li>
                <li><a href="readme/03.rest-api-example.md">03.rest-api-example</a></li>
            </ul>
        </li>
    </ul>
    <hr>
    <h3>REST API 란 무엇인가? (15~20분)</h3>
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
}
</code></pre>
                            <blockquote>
                                <p>데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 한다.</p>
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
                <li><a href="http://dev.naver.com/">네이버 개발자센터</a></li>
                <li><a href="http://dna.daum.net/">다음</a></li>
                <li><a href="https://developers.google.com/?hl=ko">Google Developer</a></li>
                <li><a href="https://dev.twitter.com/">Twitter Developer</a></li>
                <li>새주소 REST API 제공</li>
            </ul>
        </li>
        <li>REST API 정보제공 방식</li>
    </ul>
    <hr>
    <h3>REST API 설계</h3>
<pre><code>* URI 경로path는 언제 복수로 써야하는가?
    * 리소스의 상태를 업데이트하려면, 어떤 메소드를 사용해야 하는가?
    * CRUD 가 아닌 연산을 어떻게 URL에 매핑하는가?
    * 특정한 시나니로에 가장 적합한 HTTP응답은 무엇인가?
    * 리소스 상태 표현의 버전은 어떻게 관리할 수 있는가?
    * JSON에 포함된 하이퍼링크는 어떻게 구조화 하는가?
</code></pre>
    <h3>URI(Uniform Resource Identifier) 식별자 설계</h3>
    <blockquote>
        <p>식별자라고 할 수 있는 유일한 일은 대상을 나타내는 것이다. 역참조를 할 때가 아니라면 다른 정보를 얻기 위해서 URI의 내용을 들여다보지 말아야 한다.
            * URI를 만들때부터 REST API 리소스 모델을 클라이언트 모델에 전달할 수 있어야 한다.</p>
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
    <h3>요청메소드(GET/POST/PUT/DELETE)</h3>
    <ul>
        <li>참고 : <a href="http://www.restapitutorial.com/lessons/httpmethods.html">Using HTTP Methods for RESTful
            Services</a></li>
        <li>메소드별 용도
            <ul>
                <li>GET : 리소스 상태의 표현을 얻을 때 사용</li>
                <li>POST : 컬렉션에 새로운 리소스를 만들거나 컨트롤러를 실행할 때 사용</li>
                <li>PUT : 새로운 리소스를 스토어에 추가하거나 기존 리소스를 갱신할 때 사용</li>
                <li>DELETE : 리소스 제거</li>
                <li>HEAD</li>
                <li>OPTIONS</li>
            </ul>
        </li>
        <li>팁
            <ul>
                <li>jQuery를 통해서 ajax통신을 할때, PUT이나 DELETE 메소드로 요청을 날려도 POST로 처리되는 경우
                    <ol>
                        <li>web.xml hiddenMethodFilter 추가</li>
                        <li><code>&lt;form&gt;&lt;/form&gt;</code> 내에 <code>&lt;input type=&quot;hidden&quot; name=&quot;_method&quot;
                            value=&quot;PUT/DELETE&quot;/&gt;</code> 추가
                            <ul>
                                <li>Spring taglib를 사용하여 폼 생성시에는 <code>&lt;form:form method=&quot;PUT&quot;/&gt;</code>을
                                    사용시 자동으로 추가해줌
                                </li>
                            </ul>
                        </li>
                        <li>json 형태로 요청시에는 form 객체 내에 &#39;_method‘: ’put/delete&#39; 추가 하면 됨</li>
                    </ol>
                </li>
            </ul>
        </li>
    </ul>
    <h3>응답상태코드</h3>
    <ul>
        <li>응답상태코드
            <ul>
                <li>1xx : 전송 프로토콜 수준의 정보 교환</li>
                <li>2xx : 클라어인트 요청이 성공적으로 수행됨</li>
                <li>3xx : 클라이언트는 요청을 완료하기 위해 추가적인 행동을 취해야 함</li>
                <li>4xx : 클라이언트의 잘못된 요청</li>
                <li>5xx : 서버쪽 오류로 인한 상태코드</li>
            </ul>
        </li>
    </ul>
    <hr>
    <h3>OAuth2(할 수 있을까…?)</h3>
    <ul>
        <li>OAuth란 무엇인가?</li>
        <li>OAuth2 프로세스</li>
        <li>OAuth2 예제</li>
    </ul>
    <hr>
    <h3>참고사항</h3>
    <ul>
        <li>Gradle : <a href="http://www.gradle.org/"><a href="http://www.gradle.org/">http://www.gradle.org/</a></a>
            <ul>
                <li>Groovy : <a href="http://groovy.codehaus.org/"><a href="http://groovy.codehaus.org/">http://groovy.codehaus.org/</a></a>
                    <a href="http://groovy.codehaus.org/Korean+Beginners+Tutorial">KO</a></li>
            </ul>
        </li>
        <li>Java Config : <a href="http://www.springsource.org/javaconfig"><a
                href="http://www.springsource.org/javaconfig">http://www.springsource.org/javaconfig</a></a></li>
        <li>REST API
            <ul>
                <li><a href="http://ko.wikipedia.org/wiki/REST">REST</a> - Wikipedia</li>
                <li><a href="http://www.mimul.com/pebble/default/2012/08/07/1344315512542.html">Web API Design - 개발자에게
                    사랑받는 API 만들기</a> - Mimuls
                </li>
            </ul>
        </li>
        <li>Sitemesh : <a href="https://github.com/sitemesh"><a href="https://github.com/sitemesh">https://github.com/sitemesh</a></a>
            <blockquote>
                <p>화면구성 중 일부만 변경하여 사용하는 경우에 적합한 템플릿엔진</p>
            </blockquote>
        </li>
        <li><a href="http://www.restapitutorial.com/">Learn REST- A RESTful Tutorial</a></li>
    </ul>
</section>
</body>
</html>