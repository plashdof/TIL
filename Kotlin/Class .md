# Class

# Class 선언

- 클래스명 : 파스칼케이스 (첫문자만 대문자)

### 생성자

→ 생성자는 하나의 primary constructor 와 다수의 secondary constructor 로 나뉨

**→ primary constructor** **(주생성자)**

---

- constructor 키워드 사용 (생략 가능)
- 함수 인자처럼, class명 우측에 구현

- primary constructor 에는 어떤 실행문도 올 수 없음. **실행문은 init 블록에 담기**
- **인자를 받아서 초기화 or 인자 받으면서 초기화 (보통 후자로 많이 함!! val 키워드 사용)**

```kotlin
// 1. 인자를 받아서 property 초기화

class InitOrderDemo(name: String) {
 
    init {
        println("First initializer block that prints ${name}")
    }
 
    val _name = name 
 
    init {
        println("Second initializer block that prints ${name.length}")
    }
}
 
fun main(args: Array<String>) {
    val person = InitOrderDemo("Ready Kim")
}

// 결과 (위에서 아래로 실행됨 알수있다)

First property: Ready Kim
First initializer block that prints Ready Kim
Second property: 9
Second initializer block that prints 9

// 2. 인자받음과 동시에 property 초기화

class InitOrderDemo(val name: String) {
 
    
}
 
fun main(args: Array<String>) {
    val person = InitOrderDemo("Ready Kim")
}
```

**→ secondary constructor (부생성자)**

---

- constructor 키워드 사용 (생략 불가)
- class body 에 구현
- **하나의 클래스에 두가지 이상 생성자 선언 가능!!**

![캡처 JPG](https://github.com/plashdof/TIL/assets/86242930/8050a79f-91a1-4189-ad2a-c45533a58497)

- 반드시 primary constructor 와 함께 사용되어야함. 부생성자는 주생성자를 호출함
- this.프로퍼티명  을 통해 초기화 진행 가능
- : this() 를 통해 주생성자의 인자를 전달해줘야함!

```kotlin
class Animal(_nLeg:Int, _color:String) {

    var name : String = ""
    var nLeg = _nLeg
    var color = _color

    init {
        println("=== Primary Init block ===")
        println("nLeg: $nLeg, color: $color, name: $name")
        // Do something!!
    }

    constructor(_nLeg:Int, _color:String, _name:String) : this(_nLeg, _color) {
        println("=== Secondary constructor start ===")
        this.name = _name
        println("nLeg: $nLeg, color: $color, name: $name")
        println("=== Secondary constructor end ===")
    }
}

fun main(){
    val myAnimal = Animal(4, "yellow", "Smith")
}

// 결과

=== Primary Init block ===
nLeg: 4, color: yellow, name: 
=== Secondary constructor start ===
nLeg: 4, color: yellow, name: Smith
=== Secondary constructor end ===
```

### 객체(인스턴스) 생성

- val product = Product(”패션”, “겨울패딩”)
    - 자바랑 달리 new 안써도 됨
    - 해당 클래스 생성자에 따라서, 매개변수 유무 달라짐

- 기본값 설정 가능

- 생성자 인자 변수 순서 바꿔도, 네이밍 통해서 대입 가능

```kotlin
class Person (var name:String = "개발자", var age:Int = "20")

val p0 = Person() // name="개발자", age=20
val p1 = Person("김철수") // name="김철수", age=20
val p2 = Person("김영희", 25) // name="김영희", age=25
val p3 = Person(age=30, name="도라에몽") // name="도라에몽", age=30
```

# 상속

### 상속 규칙

→ **모든 클래스는 명시적 또는 암시적으로 Any를 상속함**

---

- 가장 최상위 클래스 Any
    - equals / hashCode() / toString() 만 존재
    - 자바의 java.lang.Object와는 다름
    
    ```kotlin
    class Person // 암시적 Any 상속
    class Person : Any // 명시적 Any 상속
    ```
    

- 다중상속 불가 (동시에 여러클래스 상속)

**→ open 키워드 vs final 키워드**

---

- final 키워드 추가된 클래스 → 상속 불가능
    - final 키워드가 기본값. final 키워드 생략 가능
- open 키워드 추가된 클래스 → 상속 가능

```kotlin

// 상속 가능

open class Beginner(...) {
    ...
}

class Soldier (...) : Beginner(...){
		...
}

// 상속 불가능

class Beginner(...) {
    ...
}

class Soldier (...) : Beginner(...){
		...
}
```

→ **상속 인자**

---

- 자식은 부모의 인자를 자식의 인자에 전부 받아야 한다
    - 부모의 인자를 자식의 인자에서 생략할경우, 값을 넣어서 상속해야한다
- 자식은 부모에 없는 인자를 받을 수 있다.

```kotlin
open class Animal(var name:String, var age:Int, var type:String){
    fun introduce() {
        println("저는 ${type} ${name}이고, ${age}살 입니다.")
    }
}

class Dog (name:String, age:Int, sex :String) : Animal(name, age, "개"){ // var, val을 쓰지않고 Animal 클래스 생성자에 직접 넘겨준다.
    fun bark(){ // Dog만 가능하다.
        println("멍멍")
    }
}

class Cat(name:String, age:Int,color : String) : Animal(name, age, "고양이"){
    fun meow(){
        println("야옹")
    }
}
```

### 메소드 오버라이딩

- override 키워드로, 부모클래스의 메소드 오버라이딩 가능
- 오버라이드 하게되면, 호출시 부모클래스의 메소드는 호출되지 않음
    - 부모클래스 메소드또한 호출하고 싶으면, super.메소드명 사용

```kotlin
// 초보자 클래스

open class Beginner(
    var name: String, //이름 
    var hp: Int, //체력
    var mp: Int, //마나
    var experience: Int //경험치
) {
    //일반 공격
    open fun attack(monster: Monster) {
        monster.hp -= 10 // 몬스터에게 10에 데미지를 입힌다.
    }
}

// 전사 클래스

open class Soldier (name: String, hp: Int, mp: Int, experience: Int) : Beginner(name, hp, mp, experience){

    // 전사 일반 공격
    override fun attack(monster: Monster) {
        super.attack(monster!!)
    }

}
```

**→ open 키워드 vs final 키워드**

- final 키워드 추가된 함수 → 오버라이딩 불가능
    - final 키워드가 기본값. final 키워드 생략 가능
- open 키워드 추가된 함수 → 오버라이딩 가능

```kotlin
// 오버라이드 가능

open class Beginner(...) {
    open fun attack(){}
}

class Soldier (...) : Beginner(...){
		override fun attack(){}
}

// 오버라이드 불가능

open class Beginner(...) {
    fun attack(){}
}

class Soldier (...) : Beginner(...){
		override fun attack(){}
}
```

- superclass 의 var 을, 오버라이딩하여 val 로 재정의 불가
- superclass 의 val 을, 오버라이딩하여 var 로 재정의 가능

### 상속클래스 진행순서 + **super/this 키워드**

- super 키워드
    - 자식클래스에서, 부모클래스의 메소드,프로퍼티, 생성자를 참조

- this 키워드
    - 현재클래스에서, 현재클래스의 메소드, 프로퍼트, 생성자를 참조
    

- 상속받은 클래스 실행 순서
    1. 부모클래스 초기화 진행(init)
    2. 자식클래스 초기화 진행(init)
    3. 부모클래스 메소드 실행 (super.introduce())
    4. 자식클래스 메소드 실행

```kotlin

// 부모클래스
// open 키워드를 붙여야 상속 가능

open class Fruit(val name: String){
	init{
		println("Suplerclass 초기화")
	}

	open fun introduce(){
		println('나는 과일이야!')
	}

}

// 자식클래스

class Apple(name: String) : Fruit(name){

	val text="name"

	init{
		println("subclass 초기화"
	}

	override fun introduce() {
		super.introduce()
		println("그 중에 나는 $this.text야")
	}
}

// 메인함수

fun main(){
	val apple = Apple('red apple')
	apple.introduce()
}

// 실행결과

Superclass 초기화
subclass 초기화
나는 과일이야!
그 중에 나는 사과야
```