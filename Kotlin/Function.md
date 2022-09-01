# Function

## **함수**

**→ 함수 선언**

- fun A(s: String?): Boolean{ }   ( fun 키워드 사용)
    - String 이 매개변수타입
    - Boolean 이 반환타입
    - {} 안에 함수 본문

```kotlin
fun sum(a: Int, b: Int): Int{
	var sum = a + b

	return sum
}
```

- 매개변수 디폴트값 설정
    - 파라미터를 생략해서 호출하면, 디폴트값이 들어감

```kotlin
fun sum(a: Int = 0, b: Int): Int{
	var sum = a + b

	return sum
}
```

**→ 등호 선언**

- { } 가 아닌 등호로 함수 body 선언 가능
- 

```kotlin
fun sum(a: Int, b: Int) = a + b;
```

**→ 메인 함수**

- 메인함수는 클래스 안에 없어도 됨

```kotlin
fun main(args: Array<String>){

}
```

**→ 프로퍼티 오버라이딩**

- 부모클래스의 var를 자식클래스의 val로 override 불가
- 부코믈래스의 val를 자식클래스의 var로 override 가능
    - 단순하게, val은 readonly 이므로, subclass 에서 val로 재정의 불가

```kotlin
// var 를 val로 override 불가

open class Shape {
	open var vertexCount: Int = 0
}

class Rectangle: Shape() {
	override val vertexCount: Int = 4
}

// val 를 var로 override 가능

open class Shape {
	open val vertexCount: Int = 0
}

class Rectangle: Shape() {
	override var vertexCount: Int = 4
}
```