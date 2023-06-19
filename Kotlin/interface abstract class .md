# interface / abstract class

<aside>
💡 집을 복제해야된다고 치면,
abstract Class 로 집전체를 복제하고
interface 를 참고해서, 각각의 집에 문을 다는것

가장 큰 차이는, 
**Interface : 다중상속 가능, property 초기화 불가
abstract class : 다중상속 불가능, property 초기화 가능**

두가지 이다!!

</aside>

# Abstract Class (추상클래스)

### 특징

- Class 의 종류중 하나!!
- 다중상속 불가능
- 생성자 가질 수 있다 (초기화 가능)
- **추상 메소드, 변수는 자식이 반드시 오버라이드 해줘야함!**
- **직접 객체 만들지 못하고, 하위 클래스에서 상속받아 구현해야함!**

### 선언

- abstract Class 키워드 사용 (Class 이다!!)

- property 선언 → abstract 키워드 붙이기
- property 정의(초기화) → 그냥 정의하기
- 메서드 선언 → abstract 키워드 붙이기
- 메서드 구현 → 그냥 구현하기

```kotlin
abstract class Animal {

		abstract var name : String 
		var color : String = "red"

    abstract fun sound()
    fun eat() {
        println("Animal is eating")
    }
}

class Cat : Animal() {
		override var name = "cat"
    override fun sound() {
        println("Meow!")
    }
}

fun main() {
    val cat = Cat()
    cat.sound()
    cat.eat()
}
```

### 왜 사용?

- 대략적인 설계의 명세 / 공통의 기능을 구현

# Interface

### 특징

- 특별한 종류의 추상 클래스
- 모든 메서드가 추상 메서드로 선언됨 (abstract 키워드 자동으로 붙음. 암시적으로)
- 다중상속 가능
- 생성자 가질 수 없다 (초기화 불가능)

- **직접 객체 만들지 못하고, 하위 클래스에서 상속받아 구현해야함!**
- **추상 메소드, 변수는 자식이 반드시 오버라이드 해줘야함!**

### 선언

- interface 키워드 사용

- property 선언 → 그냥 선언 (abstract 키워드 자동 붙음)
- property 정의(초기화) → 불가능
- 메서드 선언 → 그냥 선언 (abstract 키워드 자동 붙음)
- 메서드 구현 → 그냥 구현하기

```kotlin

// 프로퍼티 선언 가능
// 프로퍼티 초기화 불가능
interface Runner {
    fun run()
		// var food : String = "egg" -> 에러
		var food : String 
}

interface Eater {
    fun eat() {
    	println ("음식을 먹습니다")
    }
}

// run, food 는 오버라이드 필수
// eat 는 오버라이드 안해도 됨

class Dog : Runner, Eater {
		override var food = "rice"
    override fun run() {
    	println ("뜁니다")
    }
}
```

- 다중상속시, 메소드 이름 겹치면, super<인터페이스명> 키워드로 구분해줘야함

```kotlin
interface Dog {
    fun howling():Unit = println("war")
}

interface Cat {
    fun howling():Unit = println("nya")
}

class DogCat: Dog, Cat {
    override fun howling() {
        super<Dog>.howling()
				super<Cat>.howling()
    }
}

fun main() {
    var dog = DogCat()
    dog.howling()
}
```

### 왜 사용?

- 다중상속을 지원하기 위해
- 공통된 기능을 추상화하여, 코드 재사용성 높이기 위해