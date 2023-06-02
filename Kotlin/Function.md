# Function

## **함수 선언**

### → **기본 선언**

- fun 키워드 사용
- 함수명 (인자1 : 타입, 인자2 : 타입) : 반환타입 { }
- 타입들은 생략 가능!
- 반환값이 존재한다면, 반환타입은 생략 불가

```kotlin
fun sum(a: Int, b: Int): Int{
	var sum = a + b

	return sum
}
```

### → **매개변수 디폴트값 설정**

- 파라미터를 생략해서 호출하면, 디폴트값이 들어감

```kotlin
fun sum(a: Int = 0, b: Int): Int{
	var sum = a + b

	return sum
}
```

### → **등호 선언**

- { } 가 아닌 등호로 함수 body 선언 가능

```kotlin
fun sum(a: Int, b: Int) = a + b;
```

## 람다함수

<aside>
💡 ‘익명함수’. 이름이 없는 함수를 의미한다
보통 한번 사용되고 재사용 되지 않는 함수를 만들때 사용함!
코드가독성 높아지고, 함수형 프로그래밍에서 주로 사용됨

</aside>

### → 표현법

- 이름이 없는 함수 형태
- 변수 : 람다함수  가능
- 변수 = 람다함수 가능

```kotlin
// 생략 안됨
val multi : (int, int) → int = {x:int, y:int → x * y}

// 선언 자료형 생략
val multi = {x:int, y:int → x * y}

// 매개변수 자료형 생략
val multi : (int, int) → int = {x, y → x * y}

// 매개변수 생략
val customPrint : () -> String = {"Hello World"}

```

![캡처](https://github.com/plashdof/TIL/assets/86242930/820fc474-2e74-48e3-9181-6ef1738ab76d)


### → 함수인자가 람다함수

- 위에서 다룬 표현법을 함수 인자에 선언한다
- 함수 인자에 넣을때는, 표현법 생략한 {매개변수 → 리턴값} 형태로만 집어넣는다
- **인자로 전달된 람다함수는, 값으로 처리되어, 그 즉시 수행된 후 값을 전달함**

```kotlin
fun main(){
	var result : Int
	result = highOrder({x,y -> x+y}, 10, 20)
}

fun highOrder(calc: (int,int) -> int, a: int, b: int) : int{
	return calc(a,b)
}
```

### → trailing 람다 표현식

<aside>
💡 함수의 마지막 매개변수가 함수라면, 해당 인수로 전달되는 람다식을 **괄호 밖에 넣을 수** 있다

또한, 람다가 함수의 유일한 인수라면, **괄호를 완전히 생략**할 수 있다!!

</aside>

- 매개변수 두개일때 예시
    - operateOnNumbers 의 마지막 매개변수는 람다식이다.
    - 이럴때, operateOnNumbers 호출시, 마지막 매개변수인 람다식을, 괄호 밖에 {} 로 선언할 수 있다

```kotlin
fun operateOnNumbers(a: Int, b: Int, op: (Int, Int) -> Int): Int {

    return op(a, b)
}

var result = operateOnNumbers(10, 10, // trailing comma here
) { input1, input2 -> input1 + input2 }
```

- 매개변수 한개일때 예시
    - operateOnNumbers 는 단 하나의 람다식 매개변수 갖는다
    - 이럴때, operateOnNumbers 호출시, () 를 생략하고 {} 로만 매개변수 넣을 수 있다

```kotlin
fun operateOnNumbers(op: (Int, Int) -> Int): Int {

    return op(10, 20)
}

var result = operateOnNumbers{ input1, input2 -> input1 + input2 }
```

## 고차함수

???