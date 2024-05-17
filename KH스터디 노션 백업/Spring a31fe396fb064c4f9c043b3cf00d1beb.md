# Spring

# Spring Framwork(Spring)?

자바 플랫폼을 위한 오픈 소스 애플리케이션 프레임 워크이다.

[Spring Framework 종류](Spring%20Framework%20%E1%84%8C%E1%85%A9%E1%86%BC%E1%84%85%E1%85%B2%20ee25fbd0d037434a8413187251784b01.csv)

스프링은 IOC를 통해 구동 시 필요한 객체의 생성부터 생명 주기까지 해당 객체에 대한 관리를 직접 수행하는데, 여기서 IOC는 무엇인지 알아보자 !

## IOC(Inversion Of Control, 제어반전)?

프로그램을 구동하는데 필요한 객체에 대한 생성, 변경 등의 관리를 개발자가 아닌 **프로그램을 구동하는 컨테이너에서 직접 관리**하는 것이다.

즉, 개발자가 아닌 서버에서 관리를 한다는 말 이다!!!

그래서 JAVA에서 객체는 개발자가 생성하고 관리를 했다면 Spring에서는 IOC에서 생성한 **Bean(빈)**이라는 객체로 제어를 담당하고 있다!

- Bean?
    
    스프링이 만들고 관리하는 객체
    

그렇다면? 서버 실행 시 작성하는 어노테이션에 대해서 알아볼까요?

## IOC 관련 어노테이션

- @Component
    
    객체(컴포넌트)를 나타내는 일반적인 타입으로 <bean>태그와 동일한 역할
    
- @Repository
    
    페시스턴스 레이어, 영속성을 가지는 속성(파일, 데이터베이스)를 가진 클래스
    
- @Service
    
    서비스 레이어, 비즈니스 로직을 가진 클래스
    
- @Controller
    
    프리젠테이션 레이어, 웹 애플리케이션에서 View에서 전달된 웹 요청과 응답을 처리하는 클래스
    

## DI 관련 어노테이션

- @Autowired
    
    정밀한 의존 관계 주입이 필요한 경우에 사용하며 주로  Type을 이용함
    
- @Qualifier
    
    @ Autowired와 함께 쓰이고 의존성을 주입하고자하는 객체가 여러개 있을 경우  주입할 때 사용함
    

그렇다면? 요청 주소 매핑하는 방법에 대해서 한번 알아봅시다!

### @RequestMapping("주소")

RequestMapping은 GET/POST  가리지 않고  Mapping하는 방법이다!

그럼 속성을 통해서 지정하는 Mapping 방법은 무엇이 있을까?

### @GetMapping("주소")

GET (조회) 방식 요청 매핑

### @PostMapping("주소")

POST (삽입) 방식 요청 매핑

### @PutMapping("주소")

PUT (수정) 방식 요청 매핑

### @DeleteMapping("주소")

DELETE (삭제) 방식 요청 매핑

여기서 잠깐?

Server와 다르게 Spring Boot Controller에서 특수한 경우를 제외하고는 매핑 주소 제일 앞에 "/"를 작성 안한다 잊지말자😋

```java
@Controller
@RequestMapping("param") 
@Slf4j
public class ParameterController {

	@GetMapping("main") 
	public String paramMain() {
		
		// classpath: src/main/resource
		// 접두사 : classpath:/templates/
		// 접미사 : .html
		// -> src/main/resource/templates/param/param-main.html
		return "param/param-main";
	}
```

메서드에서 반환되는 문자열이 forward할 html파일의 경로가 되기 때문에 반환형은 무조건 String이라는 사실 !

## 파라미터로 제출하는 방법

- **HttpServletRequest.getParameter("key")** 이용
    
    ```java
    @PostMapping("test1")
    	public String paramTest1(HttpServletRequest req) {
    		
    		String inputName = req.getParameter("inputName");
    		String inputAddress = req.getParameter("inputAddress");
    		int inputAge = Integer.parseInt(req.getParameter("inputAge"));
    		
    		log.debug("inputName : " + inputName);
    		log.debug("inputAddress : " + inputAddress);
    		log.debug("inputAge : " + inputAge);
    		
    		return "redirect:/param/main";
    	}
    ```
    
- **@RequestParam** 어노테이션을 이용
    
    ```java
    @PostMapping("test2")
    	public String paramTest2(@RequestParam("title") String title,
    							@RequestParam("writer") String writer,
    							@RequestParam("price") int price,
    							@RequestParam(value="publisher", required=false, defaultValue="ABC출판사") String publisher
    						) {
    		log.debug("title : " + title);
    		log.debug("writer : " + writer);
    		log.debug("price : " + price);
    		log.debug("publisher : " + publisher);
    		
    		
    		return "redirect:/param/main";
    	}
    ```
    

위의 방법은 name값과 불러오는 값의 이름이 같아야 하며 하나씩 객체를 생성해야한다. 따라서 많은 양의 객체를 불러오는 과정에서는 번거롭다는 단점이 있어서 …! @ ModelAttribute를 사용하면 좋다 😊

- **@ ModelAttribute**
    
    ```java
    @PostMapping("test3")
    	public String paramTest3(@**ModelAttribute Student student){** 
    		
    req.setAttribute("stdName", student.getStdName());
    
    req.setAttribute("stdAge", student.getStdAge());
    
    req.setAttribute("stdAddress", student.getStdAddress());
    		
    		
    		return "redirect:/param/main";
    	}
    ```
    
    위 req부분은 값을 불러오는 경우에 작성하면 되고 Student라는 메서드에서 설정한 값과 html에 작성한 태그??의 name의 값이 같은 경우 자동으로 연결하여 객체를 셋팅해주는 것이다. 따라서 코드의 길이도 감소할 수 있고 간편하여 사용하기 좋다!
    

## Thymeleaf(타임리프)?

HTML 파일에서 thymeleaf 속성을 이용해  컨트롤러로부터 전달 받은 데이터를 통해 동적페이지를 만드는 것이다. 

즉, JSP 대신 사용하는 템플릿 엔진이다.

thymeleaf를 설정하려면 html에 xmlns:th="[http://www.thymeleaf.org](http://www.thymeleaf.org/)"를 붙여 넣어 줘야한다.

![Untitled](Untitled%202.png)

타임리프의 다양한 예제

- 변수, Model 등을 이용해 세팅한 값 출력하기
    
    
    ```html
    <h4 th:text="${test1}">test1 값</h4>
    ```