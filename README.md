# Spring
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
2. 너무 깊게 이해하기 보다, BeanDefintion으로 추상화해서 사용하는 것 정도로만 이
