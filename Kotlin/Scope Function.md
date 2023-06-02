# Scope Function

### Scope Function 이란??

- Kotlin 만의 특징!!!
- 객체 생성시 사용하는 inline 함수들.
- 자체적인 scope를 갖는 코드블럭을 사용함!!
- 5가지 존재
    - let / run / with / apply / also
    - 사실 5개가 큰차이 있는것 아니지만, 상황마다 편한 scope function이 존재함!!

### with)

```kotlin
inline fun <T, R> with(receiver: T, block: T.() -> R): R
```

- **이미 생성된 객체에 여러작업을 일괄적으로 해야할때 사용**
- **인자없음. this로 참조**
- **람다함수 리턴값 있음. 마지막줄이 리턴됨**
- 앞에 긴 dot donation 을 with의 인자로 묶어, 코드를 줄일수있다
- with 인자안에 객체로 명시해준뒤, scope 안에 객체생략한 부분 코드 작성하면 됨!

```kotlin
webView.settings.javaScriptEnabled = true
webView.settings.domStorageEnabled = true
webView.settings.userAgentString = “mobile_app_webview”

with(webView.settings){
    javaScriptEnabled = true
    domStorageEnabled = true
    userAgentString = “mobile_app_webview”
    webview // 리턴됨
}
```

### let)

```kotlin
inline fun <T, R> T.let(block: (T) -> R): R
```

- **null check를 간편하게 하기위해 사용**
- **인자 있음. it 이나 변수지정해서 참조 가능**
- **람다함수 리턴값 있음. 마지막줄이 리턴됨**
- null 체크후 실행을 let을 이용해 대체할 수 있다
- if(text ≠ null){} 과 같은 효과!!

```kotlin
val len = text?.let {
    println("get length of $it")
    it.length
} ?: 0

// text 가 null 이면 let블럭 실행 안됨
```

- with 처럼도 사용 가능함!

```kotlin
webView.settings.let { setting ->
    setting.javaScriptEnabled = true
    setting.domStorageEnabled = true
    setting.userAgentString = “mobile_app_webview”

    webview
}
```

### apply)

```kotlin
inline fun <T> T.apply(block: T.() -> Unit): T
```

- **객체의 초기화에 주로 사용! (객체 생성과 동시에 가능)**
- **인자없음. this로 참조**
- **람다함수 리턴값 없음. context 객체 T 반환됨**
- 객체를 생성 할당하기 이전에, 사용할수 있음

```kotlin
val adam = Person("Adam").apply {
    age = 32
    city = "London"        
}
println(adam)
```

### also)

```kotlin
inline fun <T> T.also(block: (T) -> Unit): T
```

- **명시적으로 객체를 참조하는 작업이 필요할 때 사용! (apply 는 this 참조만 가능하므로)**
- **인자 있음. it 이나 변수지정해서 참조 가능**
- **람다함수 리턴값 없음. context 객체 T 반환됨**

```kotlin
fun getRandomInt(): Int {
    return Random.nextInt(100).also {
        writeToLog("getRandomInt() generated value $it")
    }
}

val i = getRandomInt()
```

```kotlin
val numbers = mutableListOf("one", "two", "three")
numbers
    .also { println("The list elements before adding new one: $it") }
    .add("four")
```

### run)

```kotlin
inline fun <R> run(block: () -> R): R
inline fun <T, R> T.run(block: T.() -> R): R
```

- **빌더 패턴 구현시 사용!**
- **인자없음. this로 참조**
- **람다함수 리턴값 있음. 마지막줄이 리턴됨**

- Context Object 없는버전

```kotlin
val hexNumberRegex = run {
    val digits = "0-9"
    val hexDigits = "A-Fa-f"
    val sign = "+-"

    Regex("[$sign]?[$digits$hexDigits]+")
}

for (match in hexNumberRegex.findAll("+123 -FFFF !%*& 88 XYZ")) {
    println(match.value)
}
```

- Context Object 있는버전

```kotlin
val service = MultiportService("https://example.kotlinlang.org", 80)

val result = service.run {
    port = 8080
    query(prepareRequest() + " to port $port")
}
```

```kotlin
val password: Password = PasswordGenerator().run {
   seed = "someString"
   hash = {s -> someHash(s)}
   hashRepetitions = 1000

   generate()
}
```

### 정리!!!

- **null 체크할 때**
    - **let**

- **객체를 생성하며 초기화할 때**
    - **apply**

- **생성된 객체의 메소드 여러개를 한번에 사용하거나, 속성을 한번에 설정할 때**
    - **with, 다른걸 사용할 수도 있지만 with가 가독성이 좋다고 생각된다.**

- **객체의 생성 시점이나, 생성한 객체를 사용할 때, 명시적으로 객체를 참조하는 작업이 필요할 때**
    - **also, 람다함수 인자로 ‘it’을 사용하거나 명시적인 다른 이름을 사용할 수 있기 때문에 this를 사용하는 경우보다 가독성이 좋다.**

- **빌더 패턴**
    - **run**