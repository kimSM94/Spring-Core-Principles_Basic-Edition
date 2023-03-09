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

#Annotation
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
