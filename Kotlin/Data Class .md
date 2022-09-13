# Data Class

## **데이터 클래스**

**→ 데이터 담는 클래스에는, 다음 3가지 오버라이딩 필수**

- equals()
    - 두 객체의 동등성 비교 가능!
    - 해당 객체의 ‘동등’ 의 기준을 정의할수있다!
- hashCode()
- toString()
    - 객체를 바로 출력해도, 원하는 정보가 나옴!

**→ 이 모든 과정을 대신해주는 class 있다!!!!!!**

- class 앞에 data 키워드 추가하면, 자동으로   3가지 오버라이딩 됨!

- toString()은, 데이터클래스의 생성자에 정의된 프로퍼티만 출력!

- hashCode()는, class객체일땐 다른값 리턴, dataclass 객체일때는 같은값 리턴

- equals()
    - 값이 동일한지 체크 == (java 에서의 equals)
    - 메모리상 동일한 객체인지 체크 === (java 에서의 ==)
    
    - 클래스는, 프로퍼티가 같아도, == 는 true, ===는 false가 나온다
    - 데이터클래스는, 프로퍼티가 같으면 , == === 모두  true가 나온다

**→ copy()**

- 하나의 데이터를, 단순 필드의 값만 변경해서 추가적으로 사용하고싶을때, dataclass의 copy()를 사용!

```kotlin
data class User(
    val name: String,
    val profileImg: String,
    val age: Int
)

fun UserProcess() {
    val user1 = User("Kenneth", "https://store.image/profile_1", 30)
    val user2 = user1.copy(name = "scarlet")
    // D/user1: User(name=Kenneth, profileImg=https://store.image/profile_1, age=30)
    // D/user2: User(name=scarlet, profileImg=https://store.image/profile_1, age=30)
}

// copy()를 이용해, dataclass의 name만 변경하여 쉽게 객체 복사함!
```

**→ 데이터클래스의 특징**

- 데이터 클래스의 생성자(primary constructor)는 1개 이상의 프로퍼티를 선언되어야 합니다.
- 데이터 클래스의 생성자 프로퍼티는 val 또는 var으로 선언해야 합니다.
- 데이터 클래스에 abstract, open, sealed, inner 를 붙일 수 없습니다.
- 클래스에서 toString(), hashCode(), equals(), copy()를 override하면, 그 함수는 직접 구현된 코드를 사용합니다.
- 데이터 클래스는 상속받을 수 없습니다.

```kotlin
data class Site(val url: String, val title: String) {
    val description = ""
}

data class User(
    val name: String,
    val profileImg: String,
    val age: Int
)
```