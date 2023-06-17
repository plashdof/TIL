# inner Class / Nested Class

- 우선 두개 다, this 키워드 사용하여 property 호출시
    - 라밸링 이용하여, 어떤 클래스를 가리키는지 명시 해줘야함.
    - this@클래스명

## **Nested class (중첩 클래스)**

- 클래스 안에 클래스 선언하는 것. (별도 키워드 없음)
- Nested class 는, outer class 의 인자에 접근 불가

```kotlin
class Outer {
    private val outer = "Outer"
    class Nested {
        init {
            print(outer)  // 오류!!
        }
    }
}
```

```kotlin

class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

val demo = Outer.Nested().foo() // == 2
```

## **inner class (내부 클래스)**

- inner class 키워드로, Class 내부에 선언 가능
- outer class 의 인자에 접근 가능
- **묵시적으로 outer class 객체를 가리키고 있다!! → 해서 되도록 사용하지 않는것이 좋음**

```kotlin
class Outer {
    private val outer = "Outer"
    inner class Inner {
        init {
            print(outer)  // 성공!!
        }
    }
}
```

```kotlin
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

val demo = Outer().Inner().foo() // == 1
```

<aside>
💡  왜 기본 키워드가 Nested 로 작동하게 했을까?

JAVA는 기본 Class 가 outer Class 참조가 가능하고
static 키워드를 붙여야 outer Class 참조를 막을 수 있다

이를 Kotlin 에서는 반대로, inner 키워드를 붙여야 참조가 가능하지게끔 설계함!

이는 JAVA 의 innerClass 에 문제가 있었기 때문에, 기본값을 outer 참조 불가로 변경했음을 알 수 있다!!

</aside>

<aside>
💡 문제점 3가지

1. Inner classes를 사용할 경우 직렬화에 문제가 있다고 한다.
2. Inner classes 내부에 숨겨진 Outer class 정보를 보관하게 되고, 결국 참조를 해지하지 못하는 경우가 생기면 메모리 누수가 생길 수 있고, 코드를 분석하더라도 이를 찾기 쉽지 않다고 한다.
3. Inner classes를 허용하는 자바는 Outer를 참조하지 않아도 기본 inner classes이기 때문에(위에서 확인했다.) 불필요한 메모리 낭비와 성능 이슈를 야기한다고 한다.

</aside>

<aside>
💡 RecyclerView Adapter 의 ViewHolder에 대한 고찰

→ inner Class 보단, Nested Class 를 사용하는것 이 좋다.
→ ViewHolder를 여러 Adapter 에서 사용 할 수 도 있기 때문에, 묵시적으로 Outer Class 를 참조하게 되면 여러 문제가 생길 수 있기 때문

</aside>