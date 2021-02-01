# Spring Boot

본 프로젝트는 Spring Boot 기반의 어플리케이션이고, Elastic Search를 사용하요
 기본적으로 http 프로토콜로 접근이 가능한 REST API를 통해 데이터 조작을 지원한다.

> 필요하거나 하고싶은 기술을 제의할 수 있다.
  

## 1. 우리집 강아지 시루다.
![price](./images/siru.jpg)
혀가 귀엽다.

## 2. 하고싶은 리스트

#### 1. 엘라스틱 서치
#### 2. 레디스로 커넥
#### 3. JPA
#### 4. 리액트 or Vue
#### 5. 도커나 쿠버 

테스트해볼 테스트 코드는 아래와 같습니다.

```java
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ParameterStorePropertiesTest {

    @Autowired
    private ParameterStoreProperties properties;

    @Test
    void local_파라미터를_가져온다()   {
        assertThat(properties.getEncryptKey()).isEqualTo(dabin_local");
    }
}
```

## 2. 주의사항
* 프로젝트는 테스트 실행시 active profile을 ```local```로 두었는데, 다른 profile로 active 가능하다.
* 본 프로젝트는 2021년 상반기에 끝낼 목적으로 시작하고, 중간탈락은 없다.