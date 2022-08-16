# Variables & Types

## Variables 변수

---

→ 두가지 선언 가능 

- Type 지정
- Type 생략

→ var 과 val

- val :  변경 불가능한 변수 (immutable)
    - 한번 선언된 값을 변경하지 못함
    - 값의 읽기만 혀용
    - java의 final 특성과 같음
    
    - **val이 가리키는 객체의 내부는 변경 가능하다**
    
- var : 변경 가능한 변수 (mutable)
    - 한번 선언된 값을 변경할 수 있음
    - 값의 읽기와 쓰기 모두 허용
    
    - **하지만, 선언시 사용한 타입으로만 변경 가능**

→ val 과 const

- val: 값이 런타임 시에 결정되는 상수. 불완전한 불변성
- const는, var에쓸수 없고, val에만 쓸수 있다
- const val : 값이 컴파일 시에 결정되는 상수
    - **함수, 클래스 생성자로 할당 불가!!**

→ 변수이름 지정 : Camel case 

- 소문자, 이어지는부분에만 대문자
- 명사, 명사구로 지정

## Types

---

### Non-null type. null 값 할당 불가

→ 기본적으로 kotlin은, null값을 읽지 못한다!

→ Kotlin default type

- 정수 : int
    - long 은 따로지정 (L붙이기)
- 실수 : double
    - float 는 따로지정 (f 붙이기)

→ Type 들의 Size

- Byte : 8bit
- Short : 16bit
- Int : 32bit
- Long : 64bit

- Float : 32bit
- Double : 64bit

→ Boolean type

- true, false 값 가짐.

→ Char type

- “” 안되고 ‘’ 로만 선언

→ String type

- ‘’ 안되고 “” 로만 입력
- $ 로 변수값 문자열로 저장 가능.
- ${변수.substring(0,6)} : 문자열 잘라오기 가능

- “”” “”” : 여러줄에 걸쳐 문자열 저장

→ Array type

- intArray, ShortArray, FloatArray 등 저장되는 자료형별로 따로존재
- 선언 3가지
    - val numbers = intArrayof(2,5,8)
    - val intArray = IntArray(size:3)
    - val intArray = intArray()
    

### Nullable types. Null값 할당 가능

- Type 뒤에 ? 를 붙여서, nullable 임을 선언해야한다.
    
    
    ```kotlin
    // ex)
    var userName: String? = 'ivy'
    userName = null
    ```
    
- NullPointerException 에러
    - 어떠한 메소드나 함수를 실행시킬때, null값이 들어가게되면 Exception 발생됨.

- 변수에 .isEmpty() 쓰는법
    
    ![캡처.PNG](https://user-images.githubusercontent.com/86242930/184863960-59b066c8-7c94-433d-9c9b-858026cc9070.png)
    
    - null을 넣은 변수에 isEmpty() 하게되면, 빨간줄 그어짐
    
    - Solution
        - .isNullOrEmpty() 로 사용
        - ?.isEmpty() == true 로 사용

### Null 관련 Operators

### (Kotlin 사용시, NPE (NullPointerException) 이 줄어드는 이유!!)

1. **Safe calls Operator**
    
    
- ?.   로 사용
    - null 일경우 실행시키지 않음

![캡처.PNG](https://user-images.githubusercontent.com/86242930/184863965-da829080-cd10-4950-9f3f-71fa12e27891.png)

- 빈 문자열 예외처리시 사용할 operator들
    - ?.isEmpty()  :  null값일경우 Boolean? 으로 반환되므로, true와 동등성 안됨
    - .isNullOrEmpty()  :  화이트스페이스 예외처리 못함
    - .isNullOrBlank()  :  모든 공란에 대한 예외처리 가능

![캡처.PNG](https://user-images.githubusercontent.com/86242930/184863972-0db57cfb-7bc7-495b-abdd-4d9584eca69d.png)

1. **Elvis Operator**

- ?:  로 사용
    - if 식 대신에 사용 가능
    - readLine() ?: “” 하게되면
    
           → 입력받은값 있을때, 반환
    
            → 입력받은값 없을때 (null 일때) “” 반환
    

- null 인경우 연산자 이후의 코드를 반환시킴!!

![캡처.PNG](https://user-images.githubusercontent.com/86242930/184863979-2c05e72a-df3c-4b5c-a856-4ebd10f7e6d6.png)

1. **Not null assertion Operator**

- !! 로 사용
    - null 이 아니라고 가정하는 operator.
    - 컴파일은 잘 되지만, Runtime에 NullPointerException 발생할 수 있다.

- 정리
    - ?. : null 이면 실행 안시킴
    - ?:  : if 식으로 null 체크 가능
    - !! : null 존재해도 compile 가능.