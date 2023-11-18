스프링핵심 기본원리

<h3> 단축키 정리 </h3>

1. 생성자 만들기
- ALT + INSERT

2. 변수명으로 프린트
- soutv

3. ENUM값과 비교할 때, String 이어도 == 을 이용한다.
- equal -> X

4. CTTRL + SHIFT +T 
- 테스트 클래스 만들기 (자동으로)

5. 리펙토링
- CTRL + ALT + M(Extract Method)

6. iter
- 배열이 있으면 for문을 자동으로 만들어줌
7. isSameAs
- 인스턴스 비교할 
# Annotation
1. @BeforeEach 
- 테스트 실행 전 무조건 실

# Appconfig
1. 애플리케이션 환경 구성은 다 여기서(객체의 생성과 연결)
2. AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
3. AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해준다.
<p></p>


![image](https://user-images.githubusercontent.com/82505269/224055911-9cb8daa2-4e8d-4a6d-ae5a-58d0fce4bff4.png)


- DIP의 완성 'MemberServiceImpl'은 "MemberRepository'인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 됨.
- '관심사의 분리': 객체를 생하고 연결하는 역할과 실행하는 역할이 명확히 분리

![image](https://user-images.githubusercontent.com/82505269/224056902-256d4b41-d172-4349-8532-2a3f531b77e0.png)

- 'appConfig'객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 'mebmerServiceImpl'을 생성하면서 생성자로 전달.
- 클라이언트인 'mebmerServiceImpl' 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection : 의존관계 주입 또는 의존성 주입)라고 한다.

# 좋은 객체 지향 설계의 5가지 원칙의 적용

### SRP 단일 책임 원칙
#### 한 클래스는 하나의 책임만 가져야 한다.
1. 클라이언트 객체는 직접 구현 객체를 생성하고, 연결하고, 실행하는 다양한 책임을 가지고 있음.
2. SRP 단일 책임 원칙을 따르면서 관심사를 분리함
3. 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당
4. 클라이언트 객체는 실행하는 책임만 담당

### DIP 의존관계 역전 원칙
#### "프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.

### OCP
#### 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
1. 다형성 사용하고 클라이언트가 DIP를 지킴
2. 애플리케이션을 사용 영역과 구성 영역으로 나눔
3. 클라이언트 코드는 변경 되지 않아도 됨
4. <b>소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있다.</b>


# IoC, DI, 그리고 컨테이너
### 제어의 역전 IoC(Inversion of Control)
- 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했다.
- 반면에 AppConfig가 등장한 이후에 구현 객체는 자신의 로직을 실행하는 역할만 담당한다. 프로그램의 제어 흐름은 이제 AppConfig가 모두 담당
-> 이렇듯 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.

### 프레임워크 vs 라이브러리
1. 프레임워크 - 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크(JUnit)
2. 라이브러리 - 내가 작성한 코드가 직접 제어의 흐름들 담당

### 의존 관계 주입 DI(Dependency Injection)
- 의존관계는 <b>"정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계</b> 둘을 분리해서 생각해야함

#### 정적인 클래스 의존관계
- 클래스가 사용하는 import 코드만 보고 의존관계를 쉽게 판단할 수 잇따.

![image](https://user-images.githubusercontent.com/82505269/225334777-80e09a20-d409-4726-ba7e-a259948f207d.png)

- 이러한 클래스 의존관계 만으로는 실제 어떤 객체가 'OrderSerivceImpl'에 주입 될지 알 수 없다.

### 동적인 객체 인스턴스 의존 관계
- 애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계

![image](https://user-images.githubusercontent.com/82505269/225335439-b36537a9-f6ac-4e54-a28b-6d61fd938373.png)

1. 애플리케이션 <b>"실행 시점(런타임)"</b>에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존관계 주입.
2. 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결된다.
3. 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다.
4. <b>의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관꼐를 쉽게 변경할 수 있다.</b>

### IoC 컨테이너, DI 컨테이너
1. AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결 해주는 것을 IoC 컨테이너 또는 DI 컨테이너라 한다.
2. 의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 한다.
3. 또는 어샘블러, 오브젝트 팩토리 등으로 불리기도 한다.

### 스프링 컨테이너
1. ApplicationContext는 인터페이스이자 스프링 컨테이너라 한다.
2. XML 기반으로 만들 수 있고, 어노테이션 기반의 자바 설정 클래스로 만들 수 있다.
3. 직전에 AppConfig를 사용했던 방식이 어노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든 것이다. 
   * 참고로 스프링 컨테이너는 'BeanFactory', 'AppliactionContext'로 구분해서 이야기한다. 'BeanFactory'를 사용하는 경우는 거의 없으므로 일반적으로 'APpliactionContext'를 스프링 컨테이너라 한다.
  
1. 생성
2) 1) new AnnotaionConfigApplicationContext(Appconfig.class)
   2) 스프링 컨테이너를 생성할 떄는 구성 정보를 지정해주어야 한다.
   3) 여기서는 'Appconfig.class'를 구성 정보로 지정

2. 스프링 빈 등
1. Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
2. Role ROLE_INFRASTRUCTURE : 스프링이 내붕에서 사용하는 빈
3. ac.getBeanDefinition : 스프링에 등록된 모든 빈 이름을 조회
4. ac.getBean() : 빈이름으로 빈 객체(인스턴스)를 조회
- ac.getBean(빈이름,타입)
- ac.getBean(타입)
  > 조회 대상 스프링 빈이 없으면 예외 발생 'NoSuchBeanDefinitionException: No bean named 'xxx' available
-   assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    ac.getBean("xxxx", MemberService.class)) 가 없으면 NoSuchBeanDefinitionException 예외처리..
-> org.junit.jupiter.api.assertThrows 입니다.

### BeanFactory와 ApplicationContext

![image](https://github.com/kimSM94/Spring/assets/82505269/4c48998b-323b-42e4-9f62-73398dd44a54)

1. BeanFactory
   - 스프링 컨테이너의 최상위 인터페이스
   - 스프링 빈을 관리하고 조회
   - getBean()을 제공
     
2. ApplicationContext
   - BeanFactory 기능을 모두 상속받아서 제공
  
     
     ![image](https://github.com/kimSM94/Spring/assets/82505269/e192b5cf-7b0c-4baa-b1fb-3bb8675bc49d)

1) 메시지소스를 활용한 국제화 기능
- EX) 한국이면 한국어로, 외국이면 외국어로

2) 환경변수
- 로컬, 개발, 운영 등을 구분해서 관리

3) 애플리케이션 이벤트
- 이벤트를 발행하고 구독하는 모델을 편리하게 지원

4) 편리한 리소스 조회
 - File, ClassPath, 외부 등에서 리소스를 편리하게 조회

정리
- ApplicationContext는 BeanFactory의 기능을 상속
- ApplicationContext는 빈 관리기능 + 편리한 부가기능 제공
- BeanFactory를 직접 사용할일이 없기에 ApplicationContext를 사용
- BeanFactory나 ApplicationContext를 스프링 컨테이너라 한다.
  
### 다양한 설정 혁식 지우너 - 자바 코드, XML

![image](https://github.com/kimSM94/Spring/assets/82505269/0210c01b-3372-4a60-b272-6ced7d6406a8)

1. 어노테이션 기반 자바 코드 설정 사용
- new AnnotationConfigAppliactionContext(AppConfig.class)
- AnnotationConfigAppliactionContext 클래스를 사용하면서 자바 코드로된 설정 정보를 넘기면 된다.

2. XMl 설정 사용
- 최근에 스프링 부트를 사용하면서 XM기반의 설정은 잘 사용하지 않으나, 아직 많은 레거시 프로젝트들이 XML로 되어 있고, 또 XML을 사용하면 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점도 있음.
- 'GenericXmlAppliactionContext'를 사용하면서 'xml' 설정 파일을 넘기면 된다.
- xml기반의 appConfig.xml 스프링 설정 정보와 자바 코드로 된 'AppConfig.java' 설정 정보를 비교하면 거의 비슷


### 스프링 빈 설정 메타 정보 - BeanDefinition
- 다양한 설정 형식 지원 -> BeanDefinition
  -> 쉽게 말해 '역할과 구현을 개념적으로 나눈 것'
  - Xml을 읽어서 BeanDefinition을 만들면 된다.
  - 잡마 코드를 읽어서 BeanDefinition를 만들면 된다.
  - 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알며 된다.
- 'BeanDefinition'을 빈 설정 메타정보라 한다.
@Bean, <bean> 당 각각 하나씩 메타 정보가 생성
- 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.

![image](https://github.com/kimSM94/Spring/assets/82505269/e56c4abd-6033-4511-8794-c4cc40a711d7)

![image](https://github.com/kimSM94/Spring/assets/82505269/7518eb22-cf2e-4740-8e31-80426be259c5)

- 'AnnotaionConfigAppliactionContext'는 'AnnotationBeanDefinitionReader'를 사용해 'AppConfig.class'를 읽고 'BeanDefiniton'을 생성
- 'GenericXmlAppliactionContext'는 'XmlBeanDefinitonReader'를 사용해서 'appConfig.xml' 설정 정보를 읽고 'BeanDefinition'을 생성
- 새로운 형식의 설정 정보가 추가되면,XxxBeanDefinitionReader를 만들어서 'BeanDefinition'을 생성하면 된다.

BeanDefiniton 정보
1. BeanClassName : 생성할 빈의 클래스명
2. factoryBeanName : 팩토리 역할의 빈을 사용할 경우, 예)appConfig
3. factoryMethodName : 빈을 생성할 팩토리 메서드 지정, 예) memberService
4. Scope : 싱글톤(기본값)
5. lazyInit : 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 떄까지 최대한 생성을 지연처리 하는 여부
6. InitMethodName : 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
7. DestoryMethodName : 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
8. Constructor arguements, Properties : 의존관계 주입해서 사용한다.(자바 설정처럼 팩토리 역할의 빈을 사용하면 없음)


** 정리
1. BeanDefinition을 직접 생성해서 스프링 컨테이너에 등록할 수도 있다. 하지만 실무에서 거의 사용 X
2. 너무 깊게 이해하기 보다, BeanDefintion으로 추상화해서 사용하는 것 정도로만 이해

### 싱글톤 패턴
- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막야함.(private생성자 이용)

- 싱글톤 패턴을 구현하는 방법은 여러가지임..
- 싱글톤 패턴을 적용하면 고객의 요청이 올 때마다 객체를 생성하는 것이 아닌, 이미 만들어진 객체를 공유해 효율적으로 사용..

#### 싱글톤 패턴 문제점
1. 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
2. 의존관계상 클라이언트 구체 클래스에 의존 -> DIP 위반.
3. 클라이언트가 구체 클래스에 의존해 OCP 원칙을 위반할 가능성이 높다.
4. 테스트하기 어렵다.
5. 내부 속성을 변경하거나 초기화 하기 어렵다.
6. private 생성자로 자식 클래스를 만들기 어렵다.
7. 유연성이 떨어진다.
8. 안티패턴으로 불리기도 한다.

### 싱글톤 컨테이너
1. 스프링 컨테이너는 싱글턴 패턴을 적용X -> 객체 인스턴스를 싱글톤으로 관리
2. 스프링 컨테이너는 싱글톤 컨테이너 역할 -> 이렇게 관리하는 기능을 싱글톤 레지스트리라 곻함.
3. 스프링 컨테이너의 이런 기능덕분에 싱글턴 패턴의 모든 단점이 해결
   -> DIP,OCP,테스트,private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.


### 싱글톤 방식의 주의점
- 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지하게 설계(stateful)하면 안된다.
- 무상태(stateless)로 설계해야 한다!
1. 특정 클라이언트에 의존적인 필드가 있으면 안된다.
2. 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
3. 가급적 읽기만 가능해야 한다.
4. 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터 , ThreadLocal 등을 사용해야 한다.
- 스프링 빈의 필드에 공유 값을 설정하면 큰 장애가 발생할 수 있다!
- 
=> 실무에 이런 경우 종종 보이는데, 진짜 해결하기 어려움(몇년에 한번씩은 꼭일어남..)

bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$f165934
   내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이버르리를 사용해 AppConfig 클래스를 상속받은 임이의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등
   => 이런 임의의 다른 클래스가 바로 싱글톤이 보장되도록 한다.

Q) @Configuration 을 적용하지 않고 @Bean만 적용하면 ?
Configuration을 붙이면, 바이트코드를 조작하는 CGLIB 기술을 사용해 싱글톤을 보장하지만, 싱글톤을 보장 하지 않음(직접 호출할때)
-> 고민하지 말고 스프링 설정 정보에는 항상 넣자 ~_~


### 컴포넌트 스캔과 의존관계 자동 주입 시작하기.
- 지금까지는 @bean을 통해 직접 등록했찌만, 스프링에서 제공하는 자동으로 스프링 빈을 등록하는 컴포너는 스캔을 기능을 이용하자.
- AutoAppConfig.java
![image](https://github.com/kimSM94/Spring/assets/82505269/0daa6541-aea1-48b3-b702-a363ec8e1a60)

참고 : 컴포넌트 스캔을 사용하연 @Configuration이 붙은 설정정보도 자동으로 등록되니, excludeFilters를 이용해 제외시켰음(보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다.)

- 컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등
- @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록하는데 스프링 빈의 기본 이름은 클래스명을 사용하되, 맨 앞글자만 소문자로 사용 MemberServiceImpl -> memberServiceImpl
-> 즉 직접 등록해주고 싶음 @Component("memberServiceImpl");


### 탐색 위치와 기본 스캔 대상
- 모든 자바 클래스를 다 컴포넌트로 스캔하면 시간이 오래걸리기에 시작 위치를 지정 할 수 이씅ㅁ
1. basePackages : 탐색할 패키지의 시작 위치를 지정
2. basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위로 지정 (만약 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨.

![image](https://github.com/kimSM94/Spring/assets/82505269/b8a728cb-cc94-4e4e-a190-96dc3f37c755)


## 권장하는 방법
- 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것(프로젝트 시작 루트 위치에 두는 것)
- 참고로 스프링 부트를 사용하면, 스프링 부트의 대표 시장 정보인 @SpringBootAppliaction를 이 프로젝트 시작 루트 위치에 두는 것이 관례

![image](https://github.com/kimSM94/Spring/assets/82505269/a5b8b44a-ce78-454e-a9d2-f9423bdfaf86)

## 컴포넌트 스캔 기본 대상
1. @Component : 컴포넌트 스캔에서 사용
2. @Controller : 스프링 MVC 컨트롤러에서 사용
3. @Service : 스프링 비즈니스 로직에서 사용
4. @Repository : 스프링 데이터 접근 계층에서 사용
5. @Configuration : 스프링 설정 정보에서 사용

- 참고 : 사실 애노테이션에는 상속관계라는 것이 없음.(스프링이 지원하는기능)

### 필터
1. includeFilters : 컴포넌트 스캔 대상을 추가로 지정
2. excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정

![image](https://github.com/kimSM94/Spring/assets/82505269/6163bf0b-d408-4f31-893a-d45d1688b416)

BeanA는 등록됐지만, BeanB는 등록되지 않음.

## FilterType 옵션
1. Annotatiton : 기본값,애노테이션을 인식해 동작

   ![image](https://github.com/kimSM94/Spring/assets/82505269/db7c81b2-c797-46f2-a2bb-a9e15b4a4040)
-> 이런식으로 생략 가능
   
2. ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해 동작
3. ASPECTJ : Aspectj 패턴 사용 ex) 'org.example..*<Service+'
4. REGEX : 정규 표현식
5. CUSTOM : 'TypeFilter'이라는 인터페이스를 구현해서 처리

### 중복 등록과 충돌

1. 자동 빈 등록 vs 자동 빈 등록
- 이름이 같은 경우 스프링은 오류 발생(ConflictingBeanDefinitionException이 발생)

2. 수동 빈 등록 vs 자동 빈 등록
- 수동 빈 등록이 우선권을 가져 자동빈을 오버라이딩해버림

![image](https://github.com/kimSM94/Spring/assets/82505269/031284b0-a30d-41c8-8e8a-667554f444d4)

- 스프링 부트인 'CoreAppliaction'을 실행해보면 오류를 볼 수 있음..


### 다양한 의존관계 주입 방법
1. 생성자 주입
- 생성자 호출시점에 딱 1번만 호출되는 것이 보장
- '불변,필수' 의존관계에 사용
- 
- ![image](https://github.com/kimSM94/Spring/assets/82505269/851702ed-7489-4edb-a2b2-249e5f25ce74)

※ 스피링 빈에서, 중요 생성자가 딱 1개만 있으면 @AutoWwired를 생략해도 자동 두입 된다.

   ![image](https://github.com/kimSM94/Spring/assets/82505269/e4378d4d-d840-4928-82ee-4fddb502bad4)

2. 수정자 주입(setter 주입)
- '선택,변경' 가능성이 있는 의존 관계에 사용
- 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.

![image](https://github.com/kimSM94/Spring/assets/82505269/8eb55bc0-58b3-4250-a25e-cad22114f63d)

3. 필드주입
- 코드가 간결하지만 외부에서 변경이 불가능해 테스트하기 어려운 치명적인 단점
- DI 프레임워크가 없으면 아무것도 할수 없다.
- 사용하지말자...
   - 애플리케이션의 실제 코드와 관계 없는 테스트 코드
   - 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용
     
   ![image](https://github.com/kimSM94/Spring/assets/82505269/b54ee4d1-1685-41f6-914d-79c5eb1894ed)

4. 일반메서드 주입
- 아무메서드에다가 AutoWired를 적용해 사용 ... 그러나 사용하지 않음..
![image](https://github.com/kimSM94/Spring/assets/82505269/a4105e8c-9f05-48fd-92ee-519c7c3b1f4d)

- 이처럼 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다. 스프링 빈이 아닌 Member 같은 클래스에서 @Autowired 코드를 적용해도 아무기능이 동작하지 않는다.



### 생성자 주입을 선택해라

- 최근에는 스프링을 포함한 DI 프레임워크 대부붑ㄴ이 생성자 주입을 권장한다.

<h4>불변</h3>
1. 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부분 의존관계는 애플리케이션 종료 전까지 불변해야함
2. 수정자 주입을 사용하면,setXxx 메서드를 <b>public</b>으로 열어두어야 한다.
3. 누군가 실수롭 ㅕㄴ경할 수도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
4. 생성자 주입은 객체를 생성할 때 딱 1번만 호출

<h4>누락</h4>
- 프레임워크 없이 순수한 자바 코드를 단위 테스트 하는 경우에 다음과 같이 수정자 의존관계 인 경우

<h4>final키워드</h4>
- 생성자 주입을 사용하면 필드에 <b>final</b>키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.


* 컴파일 오류가 세상에서 가장 빠르고, 좋은 오류임.!

##### 정리
1. 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법
2. 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식으로 옵션으로 부여하면 된다. 생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
3. 항상 생성자 주입을 선택해라! 그리고 가금 옵션이 필요하면 수정자 주입을 선택해라. 필드 주입은 사용하지 않는게 좋다.
