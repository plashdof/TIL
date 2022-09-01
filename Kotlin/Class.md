# Class

**→ class 선언**

---

- 파스칼케이스 (첫문자만 대문자)
- 생성자는 하나의 primary constructor 와 다수의 secondary constructor 로 나뉨

**→ primary constructor** 

---

- constructor키워드 사용 (키워드 생략 가능)
- class명 우측에 구현
- primary constructor 에는 어떤 실행문도 올 수 없음. **실행문은 init 블록에 담기**

- 인자선언, 그인자를 property에 초기화하여 property 선언.  이과정을 그냥 한줄에도 가능!
- 초기값 설정 가능
- 예시
    
    ```kotlin
    class InitOrderDemo(name: String) {
        val firstProperty = "First property: $name".also(::println)
     
        init {
            println("First initializer block that prints ${name}")
        }
     
        val secondProperty = "Second property: ${name.length}".also(::println)
     
        init {
            println("Second initializer block that prints ${name.length}")
        }
    }
     
    fun main(args: Array<String>) {
        val person = InitOrderDemo("Ready Kim")
    }
    
    // 결과
    
    First property: Ready Kim
    First initializer block that prints Ready Kim
    Second property: 9
    Second initializer block that prints 9
    
    // 인자, property 선언 두가지를 한줄에 할수도 있다!!
    
    class Person(val firstName: String, val lastName: String, var age: Int) { /*...*/ }
    ```
    

**→ secondary constructor**

---

- 똑같이 constructor 키워드 사용 (생략 불가)
- body 에 구현
- 반드시 primary constructor 와 함께 사용되어야함. 부생성자는 주생성자를 호출함
- : this() 를 통해 주생성자의 인자를 전달해줘야함!
- 예시
    
    ```kotlin
    class Animal(val nLeg:Int, val color:String) {
        var name:String = "Tom"
        init {
            println("=== Primary Init block ===")
            println("nLeg: $nLeg, color: $color, name: $name")
            // Do something!!
        }
     
        constructor(_nLeg:Int, _color:String, _name:String) : this(_nLeg, _color) {
            println("=== Secondary constructor start ===")
            this.name = _name
            println("=== Secondary constructor end ===")
        }
     
        fun eat(something:String) {
            println("Eat $something")
        }
     
        fun cry() {
            println("Cry!!!")
        }
    }
    
    fun main(args: Array<String>) {
        val myAnimal = Animal(4, 'yellow', 'Smith')
    }
    
    // 결과
    
    === Primary Init block ===
    nLeg: 4, color: yellow, name: Tom
    === Secondary constructor start ===
    === Secondary constructor end ===
    ```
    

**→ Property**

---

![캡처.PNG](https://user-images.githubusercontent.com/86242930/187903934-85c05239-f686-4111-8a56-90069644ba6d.png)

**→ 인스턴스 생성**

---

- val product = Product(”패션”, “겨울패딩”)
    - 자바랑 달리 new 안써도 됨
    - 해당 클래스 생성자에 따라서, 매개변수 유무 달라짐

**→ 상속**

---

- 가장 최상위 클래스 Any
    - equals / hashCode() / toString() 만 존재
    - 자바의 java.lang.Object와는 다름
    - 모든 클래스는 명시적 또는 암시적으로 Any를 상속함
    
    ```kotlin
    class Person // 암시적 Any 상속
    class Person : Any // 명시적 Any 상속
    ```
    

- 다중상속 불가 (동시에 여러클래스 상속)

- open 한정자가 붙어야 자식클래스가 참조해 사용 가능
    - 클래스의 기본값인 final은, 상속을 허용하지 않음!
    
- override 키워드로 메소드 오버라이딩 가능
    
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
            /**
             * 느낌표 두 개(!!)는 Null 값 보증 연산자이다.
             * monster 변수는 절대 Null 값일 수 없다는 뜻 이다. Null 값이 들어오면 에러가 발생한다.
             */
            super.attack(monster!!)
        }
    
    }
    ```
    

**→ 메소드 오버라이딩**

---

- open 키워드 추가된 함수만, 오버라이딩 가능
- subclass 에서는, override 키워드 붙여야함

- 오버라이딩 못하게 하려면, final 키워드 넣으면 됨
- superclass 의 var 을, 오버라이딩하여 val 로 재정의 불가
- superclass 의 val 을, 오버라이딩하여 var 로 재정의 가능

**→ 추상클래스**

---

- 클래스 앞에 abstract 키워드 추가
- 객체생성 불가
- open 키워드 없어도, subclass 가 추상클래스를 상속 받을수 있다
- abstract  함수의 구현은, 반드시 subclass 에서 override로 구현

```kotlin
open class Base {    
		open fun f() {}
}

abstract class Derived : Base() {    
		override abstract fun f()
}

```

**→ interface**

---

![캡처.PNG](https://user-images.githubusercontent.com/86242930/187903944-883d4b6a-5fbc-4a05-9328-bafabbb261c9.png)

**→ super/this/override 키워드**

---

- super 키워드
    - 자식클래스에서, 부모클래스의 메소드,프로퍼티, 생성자를 참조

- override 키워드
    - 자식클래스에서, 부모클래스의 메소드 오버라이딩

- this 키워드
    - 현재클래스에서, 현재클래스의 메소드, 프로퍼트, 생성자를 참조

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

- subclass 객체를 생성하여, overriding 된 메소드를 호출하게되면
    1. 부모클래스 초기화 진행(init)
    2. 자식클래스 초기화 진행(init)
    3. 부모클래스 메소드 실행 (super.introduce())
    4. 자식클래스 메소드 실행

- this.text로, 자기자신의 프로퍼티 참조 가능