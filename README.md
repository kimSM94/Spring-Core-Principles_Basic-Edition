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

8. 구현체로 바로 이동
- CTRL + ALT + B

9. 하나로 합치는거
- CRTRL + ALT + N

```
  int count = prototypebean.getCount();
            return count;
하면

 return prototypebean.getCount();
으로 변경                



```
  

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

### @Autowired 필드 명, @Quilifier, @Primary

1. @Autowired 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
   
@Autowired 매칭 정리
1) 타입 매칭
2) 타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름 매칭

@Qualifier 사용
@Qualifier 는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것
은 아니다.

생성자 자동 주입 예시 

``` 
@Autowired
public OrderServiceImpl(MemberRepository memberRepository,
 @Qualifier("mainDiscountPolicy") DiscountPolicy
discountPolicy) {
 this.memberRepository = memberRepository;
 this.discountPolicy = discountPolicy;
}
```

수정자 자동 주입 예시 

``` 
@Autowired
public DiscountPolicy setDiscountPolicy(@Qualifier("mainDiscountPolicy") 
DiscountPolicy discountPolicy) {
 this.discountPolicy = discountPolicy
}
```

@Qualifier 로 주입할 때 @Qualifier("mainDiscountPolicy") 를 못찾으면 어떻게 될까? 그러면 
mainDiscountPolicy라는 이름의 스프링 빈을 추가로 찾는다. 하지만 경험상 @Qualifier 는 @Qualifier 를 찾
는 용도로만 사용하는게 명확하고 좋다

@Qualifier 정리
1. @Qualifier끼리 매칭
2. 빈 이름 매칭
3. NoSuchBeanDefinitionException 예외 발생


@Primary 사용
@Primary 는 우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 @Primary 가 우선권을 가진다

```
@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {}
```

Primary, Qualifier 비교
메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 @Primary 를 적용해서 조회하는 곳에서 @Qualifier 지정 없이 편리하게 조회하고, 서브 데이터베 이스 커넥션 빈을 획득할 때는 @Qualifier 를 지정해서 명시적으로 획득 하는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있다. 

물론 이때 메인 데이터베이스의 스프링 빈을 등록할 때 @Qualifier 를 지정해주는 것은 상관없다.

우선순위는 @Primary 는 기본값 처럼 동작하는 것이고, @Qualifier 는 매우 상세하게 동작한다. 

스프링은 자동보다는 수동이, 넒은 범위의 선택권 보다는 좁은 범위의 선택권이 우선 순위가 높기 @Qualifier 가 우선권이 높다


### 애노테이션 직접 만들기

@Annotation에는 상속이라는 개념이 없다. 이렇게 여러 애노테이션을 모아서 사용하는 기능은 스프링이 지원해주는 기능이다.
@Qualifier 뿐만 아니라 다른 애노테이션들도 함께 조합해서 사용할 수 있다.
단적으로 @Autowired 도 재정의 할 수 있다. 물론 스프링이 제공하는 기능을 뚜렷한 목적 없이 무분별하게 재정의 하는 것은 유지보수에 더 혼란만 가중할 수 있다


### 자동, 수동의 올바른 실무 운영 기준

1. 편리한 자동 기능을 기본으로 사용하자
 스프링이 나오고 시간이 갈 수록 점점 자동을 선호하는 추세다. 스프링은 @Component 뿐만 아니라 @Controller , @Service , @Repository 처럼 계층에 맞추어 일반적인 애플리케이션 로직을 자동으로 스캔할 수 있도록 지원하며, 최근 스프링 부트는 컴포넌트 스캔을 기본으로 사용하고, 스프링 부트의 다양한 스프링 빈들도 조건이 맞으면 자동으로 등록하도록 설계했다.
 -> 자동 빈 등록을 사용해도 OCP, DIP를 지킬 수 있다. <br>

2. 수동 빈 등록은 언제 사용하면 좋을까? <br>
 애플리케이션은 크게 업무 로직과 기술 지원 로직으로 나눌 수 있다. <br>

* 업무 로직 빈 <br>
- 웹을 지원하는 컨트롤러, 핵심 비즈니스 로직이 있는 서비스, 데이터 계층의 로직을 처리하는 리포지토리등이 모두 업무 로직이다. <br>
- 보통 비즈니스 요구사항을 개발할 때 추가되거나 변경 <br>

* 기술 지원 빈
- 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용된다.(데이터베이스 연결이나, 공통 로그처리 처럼 업무 로직을 지원하기 위한 하부 기술이나 공통 기술) <br>
- 업무 로직은 숫자도 매우 많고, 한번 개발해야 하면 컨트롤러, 서비스, 리포지토리 처럼 어느정도 유사한 패턴일때, 자동 기능을 적극 사용하는 것이 좋다. <br>
-> 그러면,보통 문제가 발생해도 어떤 곳에서 문제가 발생했는지 명확하게 파악하기 쉽다. <br>
- 기술 지원 로직은 업무 로직과 비교해서 그 수가 매우 적고, 보통 애플리케이션 전반에 걸쳐서 광범위하게 영향을 미치고 적용이 잘 되고 있는지 아닌지 조차 파악하기 어려운 경우가 많아 <b>가급적 수동 빈 등록</b>을 사용해서 명확하게 드러내는 것이 좋다. <br>
- 그리고 애플리케이션에 광범위하게 영향을 미치는 기술 지원 객체는 수동 빈으로 등록해 설정 정보에 바로 나타나게 하는것이 유지보수 하기 좋다. <br>
비즈니스 로직 중에서 다형성을 적극 활용할 때 의존관계 자동 주입 - 조회한 빈이 모두 필요할 때, List, Map을 다시 보자. <br>

```java
@Configuration
public class DiscountPolicyConfig {
 
 @Bean
 public DiscountPolicy rateDiscountPolicy() {
 return new RateDiscountPolicy();
 }
 @Bean
 public DiscountPolicy fixDiscountPolicy() {
 return new FixDiscountPolicy();
 }
}
```
 설정 정보만 봐도 한눈에 빈의 이름은 물론이고, 어떤 빈들이 주입될지 파악할 수 있다. 그래도 빈 자동 등록을 사용하고 싶으면 파악하기 좋게 DiscountPolicy 의 구현 빈들만 따로 모아서 특정 패키지에 모아두자. <br>
 
※ 참고로 스프링과 스프링 부트가 자동으로 등록하는 수 많은 빈들은 예외이며, 이런 부분들은 스프링 자체를 잘 이해하고 스프링의 의도대로 잘 사용하는게 중요하다. <br>
 스프링 부트의 경우 DataSource 같은 데이터베이스 연결에 사용하는 기술 지원 로직까지 내부에서 자동으로 등록하는데, 이런 부분은 메뉴얼을 잘 참고해서 스프링 부트가 의도한 대로 편리하게 사용하면 된다. 반면에 스프링 부트가 아니라 내가 직접 기술 지원 객체를 스프링 빈으로 등록한다면 수동으로 등록해서 명확하게 드러내는 것이 좋다. <br>

<h3>정리</h3>
1. 편리한 자동 기능을 기본으로 사용하자 <br>
2. 직접 등록하는 기술 지원 객체는 수동 등록 <br>
3. 다형성을 적극 활용하는 비즈니스 로직은 수동 등록을 고민하기 <br>


### 빈 생명주기 콜백

1. 빈 생명주기 콜백 시작
데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 객체의 초기화와 종료 작업이 필요하다.<br>
NetworkClient 는 애플리케이션 시작 시점에 connect() 를 호출해서 연결을 맺어두어야 하고, 애플리케이션이 종료되면 disConnect() 를 호출해서 연결을 끊어야 한다


```
package hello.core.lifecycle;
public class NetworkClient {
 private String url;
 public NetworkClient() {
 System.out.println("생성자 호출, url = " + url);
 connect();
 call("초기화 연결 메시지");
 }
 public void setUrl(String url) {
 this.url = url;
 }
 //서비스 시작시 호출
 public void connect() {
 System.out.println("connect: " + url);
 }
 public void call(String message) {
 System.out.println("call: " + url + " message = " + message);
 }
 //서비스 종료시 호출
 public void disconnect() {
 System.out.println("close: " + url);
 }
}
```

<h3>스프링 빈 라이프사이클</h3>
1. 객체 생성 의존관계 주입
스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비된 다음에 호출해야 한다. <br>
<br>
Q) 그런데 개발자가 의존관계 주입이 모두 완료된 시점을 어떻게 알 수 있을까?<br>

1. 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공 한다. <br>
2. 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다. 따라서 안전하게 종료 작업을 진행할 수 있다.

<h3>스프링 빈의 이벤트 라이프사이클</h3>
- 스프링 컨테이너 생성 스프링 빈 생성 의존관계 주입 초기화 콜백 사용 소멸전 콜백 스프링 종료
※ 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
※ 소멸전 콜백: 빈이 소멸되기 직전에 호출

- 스프링 생명주기 콜백주기
1. 인터페이스(InitializingBean, DisposableBean)
2. 설정 정보에 초기화 메서드, 종료 메서드 지정
3. @PostConstruct, @PreDestroy 애노테이션 지원

※ 참고: 객체의 생성과 초기화를 분리하자.
생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면에 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는등 무거운 동작을 수행한다.
따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화 하는 부분을 명확하게 나누는 것이 유지보수 관점에서 좋다.
그러나 물론 초기화 작업이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서 한번에 다 처리하는게 더 나을 수 있다.

※ 참고: 싱글톤 빈들은 스프링 컨테이너가 종료될 때 싱글톤 빈들도 함께 종료되기 때문에 스프링 컨테이너가 종료되기 직전에 소멸전 콜백이 일어난다. 싱글톤 처럼 컨테이너의 시작과 종료까지 생존하는 빈도 있지만, 생명주기가 짧은 빈들도 있는데 이 빈들은 컨테이너와 무관하게 해당 빈이 종료되기 직전에 소멸전 콜백이 일어난다

※ 초기화, 소멸 인터페이스 단점
- 이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
초기화, 소멸 메서드의 이름을 변경할 수 없다.

```
@Override
 public void afterPropertiesSet() throws Exception {
   connect();
   call("초기화 연결 메시지");
 }

 @Override
   public void destroy() throws Exception {
   disConnect();
 }

````

내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다

<b>참고: 인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법들이고, 지금은 다음의 더 나은 방법들이 있어서 거의 사용하지 않는다.</b>


빈 등록 초기화, 소멸 메서드 지정
설정 정보에 @Bean(initMethod = "init", destroyMethod = "close") 처럼 초기화, 소멸 메서드를 지
정할 수 있다.
설정 정보를 사용하도록 변경

```
package hello.core.lifecycle;
public class NetworkClient {
 private String url;
 public NetworkClient() {
 System.out.println("생성자 호출, url = " + url);
 }
 public void setUrl(String url) {
 this.url = url;
 }
 //서비스 시작시 호출
 public void connect() {
 System.out.println("connect: " + url);
 }
 public void call(String message) {
 System.out.println("call: " + url + " message = " + message);
 }
 //서비스 종료시 호출
 public void disConnect() {
 System.out.println("close + " + url);
 }
 public void init() {
 System.out.println("NetworkClient.init");
 connect();
 call("초기화 연결 메시지");
 }
 public void close() {
 System.out.println("NetworkClient.close");
 disConnect();
 }
}
```

설정 정보에 초기화 소멸 메서드 지정

※ 설정 정보 사용 특징
1. 메서드 이름을 자유롭게 줄 수 있다.
2. 스프링 빈이 스프링 코드에 의존하지 않는다.
3. 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.

※ 종료 메서드 추론
1. @Bean의 destroyMethod 속성에는 아주 특별한 기능이 있다.
2. 라이브러리는 대부분 close , shutdown 이라는 이름의 종료 메서드를 사용한다. 
3. @Bean의 destroyMethod 는 기본값이 (inferred) (추론)으로 등록되어 있다.
이 추론 기능은 close , shutdown 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다.

따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다. 추론 기능을 사용하기 싫으면 destroyMethod="" 처럼 빈 공백을 지정하면 된다.


<h2>결론은 <b color = "red">애노테이션 @PostConstruct, @PreDestroy</b>을 사용하면 된다.</h2> 

```
@PostConstruct
 public void init() {
   System.out.println("NetworkClient.init");
   connect();
   call("초기화 연결 메시지");
 }

 @PreDestroy
 public void close() {
   System.out.println("NetworkClient.close");
   disConnect();
 }
```

※ @PostConstruct, @PreDestroy 애노테이션 특징
1. 최신 스프링에서 가장 권장하는 방법이며, 애노테이션 하나만 붙이면 되므로 매우 편리하다.
2. 패키지를 잘 보면 javax.annotation.PostConstruct 이다. 스프링에 종속적인 기술이 아니라 JSR-250
라는 자바 표준이다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
3. 컴포넌트 스캔과 잘 어울린다.
4. 유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 initMethod , destroyMethod기능을 사용하자.

<h2>빈 스코프란</h2>
지금까지 우리는 스프링 빈이 스프링 컨테이너의 시작과 함께 생성되어서 스프링 컨테이너가 종료될 때 까지 유지된다
고 학습했다. 이것은 스프링 빈이 기본적으로 싱글톤 스코프로 생성되기 때문이다. 스코프는 번역 그대로 빈이 존재할
수 있는 범위를 뜻한다.

- 스프링은 다음과 같은 다양한 스코프를 지원한다.
1. 싱글톤: 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프이다.
2. 프로토타입: 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우
짧은 범위의 스코프이다.

※ 웹 관련 스코프
1. request: 웹 요청이 들어오고 나갈때 까지 유지되는 스코프이다.<br>
2. session: 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프이다.  <br>
3. application: 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프이다. <br>

빈 스코프는 다음과 같이 지정할 수 있다.

컴포넌트 스캔 자동 등록 
```
@Scope("prototype")
@Component
public class HelloBean {}
```

수동 등록 
```
@Scope("prototype")
@Bean
PrototypeBean HelloBean() {
 return new HelloBean();
}
```
지금까지 싱글톤 스코프를 계속 사용해보았으니, 프로토타입 스코프부터 확인해보자

프로토타입 스코프
싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환한다. 반면에 프로토타입 스
코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환한다.

싱글톤 빈 요청

1. 싱글톤 스코프의 빈을 스프링 컨테이너에 요청한다.
2. 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환한다.
3. 이후에 스프링 컨테이너에 같은 요청이 와도 같은 객체 인스턴스의 스프링 빈을 반환한다.

프로토타입 빈 요청

1. 프로토타입 스코프의 빈을 스프링 컨테이너에 요청한다.
2. 스프링 컨테이너는 이 시점에 프로토타입 빈을 생성하고, 필요한 의존관계를 주입한다.
3. 스프링 컨테이너는 생성한 프로토타입 빈을 클라이언트에 반환한다.
4. 이후에 스프링 컨테이너에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성해서 반환한다

※ 정리
여기서 핵심은 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다는 것이다. 클라이
언트에 빈을 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않는다. 프로토타입 빈을 관리할 책임
은 프로토타입 빈을 받은 클라이언트에 있다. 그래서 @PreDestroy 같은 종료 메서드가 호출되지 않는다.

- 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행 되지만, 프로토타입 스코프의 빈은 스프링 컨테
이너에서 빈을 조회할 때 생성되고, 초기화 메서드도 실행된다.
- 프로토타입 빈을 2번 조회했으므로 완전히 다른 스프링 빈이 생성되고, 초기화도 2번 실행된 것을 확인할 수 있
다.
싱글톤 빈은 스프링 컨테이너가 관리하기 때문에 스프링 컨테이너가 종료될 때 빈의 종료 메서드가 실행되지만, 
프로토타입 빈은 스프링 컨테이너가 생성과 의존관계 주입 그리고 초기화 까지만 관여하고, 더는 관리하지 않는
다.
 따라서 프로토타입 빈은 스프링 컨테이너가 종료될 때 @PreDestroy 같은 종료 메서드가 전혀 실행되지 않
는다.

※ 프로토타입 빈의 특징 정리
1. 스프링 컨테이너에 요청할 때 마다 새로 생성된다.
2. 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
3. 종료 메서드가 호출되지 않는다.
-> 그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다. 종료 메서드에 대한 호출도 클라이
언트가 직접 해야한다.


<h2>프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점</h2>
스프링 컨테이너에 프로토타입 스코프의 빈을 요청하면 항상 새로운 객체 인스턴스를 생성해서 반환한다. 하지만 싱글
톤 빈과 함께 사용할 때는 의도한 대로 잘 동작하지 않으므로 주의해야 한다.

프로토타입 빈 직접 요청
스프링 컨테이너에 프로토타입 빈 직접 요청1
1. 클라이언트A는 스프링 컨테이너에 프로토타입 빈을 요청한다.
2. 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환(x01)한다. 해당 빈의 count 필드 값은 0이다.
3. 클라이언트는 조회한 프로토타입 빈에 addCount() 를 호출하면서 count 필드를 +1 한다.
결과적으로 프로토타입 빈(x01)의 count는 1이 된다.

스프링 컨테이너에 프로토타입 빈 직접 요청2
1. 클라이언트B는 스프링 컨테이너에 프로토타입 빈을 요청한다.
2. 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환(x02)한다. 해당 빈의 count 필드 값은 0이다.
3. 클라이언트는 조회한 프로토타입 빈에 addCount() 를 호출하면서 count 필드를 +1 한다.
결과적으로 프로토타입 빈(x02)의 count는 1이 된다.

코드로 확인 
```
public class SingletonWithPrototypeTest1 {
 @Test
 void prototypeFind() {
   AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
   PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
   prototypeBean1.addCount();
  
   assertThat(prototypeBean1.getCount()).isEqualTo(1);
   PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
   prototypeBean2.addCount();
  
   assertThat(prototypeBean2.getCount()).isEqualTo(1);
  
 }

 @Scope("prototype")
 static class PrototypeBean {
   private int count = 0;
   public void addCount() {
   count++;
 }

 public int getCount() {
   return count;
 }

 @PostConstruct
 public void init() {
   System.out.println("PrototypeBean.init " + this);
 }

@PreDestroy
 public void destroy() {
   System.out.println("PrototypeBean.destroy");
 }
 }
}
```

싱글톤 빈에서 프로토타입 빈 사용
이번에는 clientBean 이라는 싱글톤 빈이 의존관계 주입을 통해서 프로토타입 빈을 주입받아서 사용하는 예를 보자.

- 싱글톤에서 프로토타입 빈 사용1
clientBean 은 싱글톤이므로, 보통 스프링 컨테이너 생성 시점에 함께 생성되고, 의존관계 주입도 발생한다.
1. clientBean 은 의존관계 자동 주입을 사용한다. 주입 시점에 스프링 컨테이너에 프로토타입 빈을 요청한다.
2. 스프링 컨테이너는 프로토타입 빈을 생성해서 clientBean 에 반환한다. 프로토타입 빈의 count 필드 값은 0이다.
이제 clientBean 은 프로토타입 빈을 내부 필드에 보관한다. (정확히는 참조값을 보관한다.)

- 싱글톤에서 프로토타입 빈 사용2
클라이언트 A는 clientBean 을 스프링 컨테이너에 요청해서 받는다.싱글톤이므로 항상 같은 clientBean이 반환된다.
3. 클라이언트 A는 clientBean.logic() 을 호출한다.
4. clientBean 은 prototypeBean의 addCount() 를 호출해서 프로토타입 빈의 count를 증가한다. count값이 1이 된다.

싱글톤에서 프로토타입 빈 사용3
클라이언트 B는 clientBean 을 스프링 컨테이너에 요청해서 받는다.싱글톤이므로 항상 같은 clientBean이 반환된다.
여기서 중요한 점이 있는데, clientBean이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이다. 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성이 된 것이지, 사용 할 때마다 새로 생성되
는 것이 아니다!
5. 클라이언트 B는 clientBean.logic() 을 호출한다.
6. clientBean 은 prototypeBean의 addCount() 를 호출해서 프로토타입 빈의 count를 증가한다. 원래 count 값이 1이었으므로 2가 된다.

스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다. 그런데 싱글톤 빈은 생성
시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과 함께 계속 유지되는 것
이 문제다.
 아마 원하는 것이 이런 것은 아닐 것이다. 프로토타입 빈을 주입 시점에만 새로 생성하는게 아니라, 사용할 때 마다 새로생성해서 사용하는 것은 다음 <b>Provider</b>로 문제를 해결할 수 있따.

<h2>프로토타입 스코프 - 싱글톤 빈과 함께 사용시 Provider로 문제 해결</h2>

<h3>ObjectFactory, ObjectProvider</h3>

```
@Autowired
private ObjectProvider<PrototypeBean> prototypeBeanProvider;
public int logic() {
 PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
 prototypeBean.addCount();
 int count = prototypeBean.getCount();
 return count;
}
```

실행해보면 prototypeBeanProvider.getObject() 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.<br>
ObjectProvider 의 getObject() 를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다. (DL)
스프링이 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬
쉬워진다.<br>
 ObjectProvider 는 지금 딱 필요한 DL 정도의 기능만 제공한다. <br>

특징
1. ObjectFactory: 기능이 단순, 별도의 라이브러리 필요 없음, 스프링에 의존
2. ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요 없음, 
스프링에 의존

※ DL : 의존관계를 외부에서 주입(DI) 받는게 아니라 이렇게 직접 필요한 의존관계를 찾는 것을 Dependency Lookup 
(DL) 의존관계 조회(탐색)

<h2>JSR-330 Provider</h2>
- 마지막 방법은 javax.inject.Provider 라는 JSR-330 자바 표준을 사용하는 방법이다.
- 스프링 부트 3.0은 jakarta.inject.Provider 사용한다.
-> 이 방법을 사용하려면 다음 라이브러리를 gradle에 추가해야 한다.

※ 스프링부트 3.0 미만
javax.inject:javax.inject:1 라이브러리를 gradle에 추가해야 한다.

※ 스프링부트 3.0 이상
jakarta.inject:jakarta.inject-api:2.0.1 라이브러리를 gradle에 추가해야 한다.
javax.inject.Provider 참고용 코드 - 스프링부트 3.0 미만 
```java
package javax.inject;
public interface Provider<T> {
 T get();
}
```

스프링 부트 3.0은 jakarta.inject.Provider 사용
```java
@Autowired
private Provider<PrototypeBean> provider;
public int logic() {
 PrototypeBean prototypeBean = provider.get();
 prototypeBean.addCount();
 int count = prototypeBean.getCount();
 return count;
}
```

실행해보면 provider.get() 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
provider 의 get() 을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다. (DL)
자바 표준이고, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬워진다.
Provider 는 지금 딱 필요한 DL 정도의 기능만 제공한다.
특징
1. get() 메서드 하나로 기능이 매우 단순하다.
2. 별도의 라이브러리가 필요하다.
3. 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다.
정리
그러면 프로토타입 빈을 언제 사용할까? 매번 사용할 때 마다 의존관계 주입이 완료된 새로운 객체가 필요하면 사
용하면 된다.<br>

그런데 실무에서 웹 애플리케이션을 개발해보면, 싱글톤 빈으로 대부분의 문제를 해결할 수 있기 때문에 프로토타입 빈을 직접적으로 사용하는 일은 매우 드물다.

- ObjectProvider , JSR330 Provider 등은 프로토타입 뿐만 아니라 DL이 필요한 경우는 언제든지 사용할수 있다.

※  참고: 스프링이 제공하는 메서드에 @Lookup 애노테이션을 사용하는 방법도 있지만, 이전 방법들로 충분하고, 
고려해야할 내용도 많아서 생략하겠다.

※ 참고: 실무에서 자바 표준인 JSR-330 Provider를 사용할 것인지, 아니면 스프링이 제공하는 ObjectProvider
를 사용할 것인지 고민이 될 것이다. 
 ObjectProvider는 DL을 위한 편의 기능을 많이 제공해주고 스프링 외에 별도의 의존관계 추가가 필요 없기 때문에 편리하다. 만약(정말 그럴일은 거의 없겠지만) 코드를 스프링이 아닌 다른 컨테이너에서도 사용할 수 있어야 한다면 JSR-330 Provider를 사용해야한다. <br>
스프링을 사용하다 보면 이 기능 뿐만 아니라 다른 기능들도 자바 표준과 스프링이 제공하는 기능이 겹칠때가 많
이 있다. 대부분 스프링이 더 다양하고 편리한 기능을 제공해주기 때문에, 특별히 다른 컨테이너를 사용할 일이 없
다면, 스프링이 제공하는 기능을 사용하면 된다

<h3>웹 스코프</h3>
지금까지 싱글톤과 프로토타입 스코프를 학습했다. 싱글톤은 스프링 컨테이너의 시작과 끝까지 함께하는 매우 긴 스코
프이고, 프로토타입은 생성과 의존관계 주입, 그리고 초기화까지만 진행하는 특별한 스코프이다.

1. 웹 스코프의 특징
- 웹 스코프는 웹 환경에서만 동작한다.
- 웹 스코프는 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리한다. 따라서 종료 메서드가 호출된
다.

2. 웹 스코프 종류
1) request: HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴
스가 생성되고, 관리된다.
2) session: HTTP Session과 동일한 생명주기를 가지는 스코프
3) application: 서블릿 컨텍스트( ServletContext )와 동일한 생명주기를 가지는 스코프
4) websocket: 웹 소켓과 동일한 생명주기를 가지는 스코프

<h3>스코프와 Provider</h3>
1. ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의생성을 지연할 수 있다.
2. ObjectProvider.getObject() 를 호출하시는 시점에는 HTTP 요청이 진행중이므로 request scope 빈의 생성이 정상 처리된다.
3. ObjectProvider.getObject() 를 LogDemoController , LogDemoService 에서 각각 한번씩 따로 호출해도 같은 HTTP 요청이면 같은 스프링 빈이 반환된다!

<h2>웹 스코프와 프록시 동작 원리</h2>
먼저 주입된 myLogger를 확인해보자. 

```java
 System.out.println("myLogger = " + myLogger.getClass());

 출력 결과
 myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$b68b726d
```

- CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다. @Scope 의 proxyMode = ScopedProxyMode.TARGET_CLASS) 를 설정하면 스프링 컨테이너는 CGLIB 라는 바이트코드를 조작하는 라이브러리를 사용해서, MyLogger를 상속받은 가짜 프록시 객체를 생성한다.
- 결과를 확인해보면 우리가 등록한 순수한 MyLogger 클래스가 아니라 MyLogger$ $EnhancerBySpringCGLIB 이라는 클래스로 만들어진 객체가 대신 등록된 것을 확인할 수 있다.
- 그리고 스프링 컨테이너에 "myLogger"라는 이름으로 진짜 대신에 이 가짜 프록시 객체를 등록한다. ac.getBean("myLogger", MyLogger.class) 로 조회해도 프록시 객체가 조회되는 것을 확인할 수 있다.
-> 그래서 의존관계 주입도 이 가짜 프록시 객체가 주입된다.

※ 동작 정리
CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다. 이 가짜 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있다.

※ 특징 정리
- 프록시 객체 덕분에 클라이언트는 마치 싱글톤 빈을 사용하듯이 편리하게 request scope를 사용할 수 있다. 사실 Provider를 사용하든, 프록시를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리한다는 점이다.
- 단지 애노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있다. 이것이 바로 다형성과 DI 컨테이너 가 가진 큰 강점이다.
- 꼭 웹 스코프가 아니어도 프록시는 사용할 수 있다.

※  주의점
마치 싱글톤을 사용하는 것 같지만 다르게 동작하기 때문에 결국 주의해서 사용하고 특별한 scope는 꼭 필요한 곳에만 최소화해서 사용하자, 무분별하게 사용하면 유지보수하기 어려워진다.

