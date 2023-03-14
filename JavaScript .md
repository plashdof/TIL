# JavaScript

# HTML 과의 연결

- 보통 body 의 맨끝에 작성
- <script src=”파일명”></script>

# 기초문법

- **변수선언 let**
    - let num = 5;
    - num++;
    - num—;

- **const, var**
    - const : 고정하는 기능. 똑같다
    - var : let과 동일한 기능. but let이 더 최신기능    
    

- **boolean 형**
    - 참 : 1, true
    - 거짓: 0, false

- **문자열**
    - “” 로 선언
    - ‘’ 도 가능하나, ‘’는, “” 안에 넣을때 주로 사용
    
    - 문자열 합치기
        - + 로 더해주면 된다.
        - 숫자 + 문자열은 자바스크립트에서 문자열이 된다
    
    - 메소드
        - .toUpperCase()
        - .length
        - .slice()
        - .replace()
        - .indexOf()
        - .repeat()
    
    - 문자열 템플렛
        - `` 사용하여 문자열 선언, 그안에 ${} 으로 수식 넣을수 있다!!
    
- **null & undifined**
    - null : 아무것도 없는 상태
    - undifined : 아무것도 없다

- **Math 객체**
    - 수많은 수학동작이 Math.~() 로 시작한다
    - ex) Math.random()

- **변수이름 규칙**
    - 숫자 앞에오면 안됨
    - 특수문자는 $, _ 만 사용 가능

# 입출력

- prompt()  :  입력. input 과 비슷함
- alert() : 팝업창 출력
- console.log() : 콘솔에 출력

# 조건문

문자끼리의 비교 → Unicode 표 참조!

- **if / else if / else : 다른언어와 동일**

- **이중등호, 삼중등호**
    - == : type을 신경쓰지 않는다
        - ex) 1 == ‘1’  : true
    - === : type까지 같아야한다
        - ex) 1 === ‘1’  : false

- **truthy & falsy**
    - falsy values:   ( if문 조건에 단일로 들어갈시 false 로 취급 )
        - false
        - 0
        - “” (empty string)
        - null
        - undefined
        - NaN
    
    - falsy values 제외하고 모두 truthy values
        
        (if 문 조건에 단일로 들어갈시 true로 취급)
        

- **AND, OR, NOT**
    - &&  || !  :  다른언어와 동일
    - AND 이 OR 보다 먼저실행

- **switch case 문**
    - switch(cond){ case :  break; default : }

# 배열

- let 과 [] 로 선언.
- 다른종류의 자료형을 한곳에 넣어도 된다!!! (javascript 만의 차별성)

- **배열길이 & 배열인덱싱**
    - 문자열과 동일.

- **배열원소 수정**
    - 문자열에서는 replace 로만 가능했으나, 배열에서는 인덱싱으로 가능
    
    - arr[1] = ‘red’ : 이러면 배열의 2번쨰원소가 red로 바뀜
    
    - 없는 index에 추가하기도 가능.
        - 크기 3짜리 배열 arr 에 arr[7] = ‘purple’ 하면,
        - arr은 크기 8짜리 배열이 된다!!!!
    
- **pop / push & shift / unshift  (핵심 메소드)**
    - push : 배열끝에 원소 추가
        - arr.push(”oliver”);
        - 여러개 한번에 추가 가능
    - pop : 배열끝의 원소 제거
        - arr.pop();
    
    - shift : 배열맨앞에 원소 제거
        - arr.shift();
    - unshift : 배열 맨앞에 원소 추가
        - arr.unshift(”oliver”);

- **concat / includes / indexOf / reverse / slice / splice / sort (서브 메소드)**
    
    
    - concat : 배열 두개 합치기
        - arr1.concat(arr2) : arr1 + arr2 값 반환
    
    - includes : 배열안에 특정원소 있는지 검사
        - arr.includes(’blue’) : blue 가 arr안에 있으면 true 반환 없으면 false 반환
    
    - indexOf : 해당 원소의 index값을 반환
        - arr.indexOf(’a’) : a가 위치한 인덱스를 반환
        
    - reverse : 배열원소 거꾸로 바꿈
        - arr.reverse() : arr의 원소를 거꾸로 변환
        
    
    - slice : 배열의 일부 뭉탱이를 잘라서 반환
        - arr.slice(n1, n2) : 배열의 인덱스 n1 ~ n2-1 까지 반환
    
    - splice : 배열의 일부 뭉탱이를 삭제 OR 배열의 사이에 삽입
        - arr.splice(n1, n2) : index n1 부터 n2 개 제거
        - arr.splice(1, 0, ‘red’) : index 1 자리에 red 원소 삽입
    
    - sort : 배열을 정렬
        - arr.sort() : 오름차순으로 정렬

- **배열의 참조**
    - [1,2,3] === [1,2,3]  :  결과는 거짓이다. 배열의 주솟값으로 비교하기 때문.
    - arr = [1,2,3]  /  arrCopy = arr
        - 이렇게되면, arr값에 변화가 일어난것이 arrCopy 에도 변화가 일어남!!
        - arrCopy가 arr를 가리키고 있기 때문

- **배열과 const**
    - 배열에 const 씌운다는것은, 배열의 참조주소가 바뀌지 않아야 한다는 뜻!!
    - 고로, 배열안의 원소들은 바뀌어도 된다!!!
    - ex) const num = [1,2,3,4]
        - num = [1,2,3]  : error!
        - num.push(5) : 정상!

# 객체

- **객체 리터럴**
    - const person = {firstName: ‘Park’, lastName: ‘JinSung’};
    - 파이썬의 dictionary 처럼 생성 가능
    - 순서가 없다!!
    - 각 내부요소들 comma로 구분
    
- **객체 데이터 호출**
    - person[”key”] : value를 호출함
        - 대괄호를 쓰게되면, 검사를 해준다!!!
        - Key 라고해도, 맞게 출력됨
    - person.key : value를 호출함

- **객체 데이터 수정 & 추가**
    - person.key = new_value;  // 데이터 수정
    - person.new_key = new_value;  //  데이터 추가

- 배열안의 객체 & 객체안의 배열 있는경우 가능

- **this 키워드**
    - 객체 자기자신을 가리킴

# 루프

- **for / while**
    - 자바와 똑같음
    - break, continue 도 같음

- **for of**
    - 자바의 for each 구문과 동일
    - ex) for(let num of numbers){}
        - numbers 라는 배열 혹은 문자열 에서 원소 하나씩 꺼내서 num 에 집어넣음

- **객체 루프**
    - for in 루프 이용
        - 객체의 각 key에 접근하는 것임.
    - for of 루프 + Object.values(객체명) 이용
        - 객체의 각 value를 배열로 만든후, for of 루프

# 함수

- **선언**
    - function 함수명(인자명1, 인자명2){ return 반환값 }

- **var 과 let 차이**
    - var은 블록지정이 안되는 변수
    - let은 블록지정이 되는 변수

- **렉시컬 함수 (중첩함수)**
    - 중첩함수에서는, 바깥함수만 호출하면, 안에함수는 호출이 안된다
    - 따라서, 안에서 안에함수 호출문도 넣어야한다

- **함수 표현식 (변수에 저장하는 이름없는 함수)**
    - 변수에 함수를 저장하면서, 함수이름을 생략할 수 있다
    - 변수명() 으로 호출한다!!

- 고차함수 (함수를 인자로 받는 함수)
- 함수를 인자로 받는 함수의 구조!!
- 함수의 인자에, 함수명만 집어넣게되는 구조!

- **반환 함수 (반환되는 이름없는 함수)**
    - 함수의 반환값에, 함수를 정의할 수 있다!!
    - 함수표현식과 동일하게, 반환함수는 이름이 없다!

- **메소드 함수 (객체 안의 함수)**
    - 선언 → 함수명: function(인자명){ return 반환값 };
    - 사용 → 객체명.함수명(인자명);
    - 속기법 → 함수명(인자명){ return 반환값 }
        - 그냥 함수처럼 쓴다!!

# 배열의 메소드들

### 특징 : 모두다 함수를 인자로 받는다!

### 그리고, 메서드를 연달아서 동시사용할 수 있다!

- **forEach 메서드**
    - 배열의 인자를 하나씩 꺼내서, 그 인자로 동작한다
    - for of 문이 나오기 전에 많이쓰이던 것. 요즘은 거의 안쓰임
    - ex) nums.forEach(function (num) { console.log(num) } )

- **Map 메서드**
    - 배열의 인자를 하나씩 꺼내서, 그 인자로 동작한다
    - 배열의 인자를 변화시켜서, 새로운 인자를 반환. 그 반환값을 통해서 새로운 배열을 mapping 한다
    - ex) const new_arr = arr.map(function (m) { return m.toUpperCase(); } )

- **화살표함수**
    - (인자) ⇒ { return 반환값 }  으로 선언
        - 단독으로 사용 불가.
    - 암시적 반환 ( return 생략 가능 )
        - 함수 본문이 무조건 한줄일때 사용 가능!!!
        - {} 대신 () 사용하면서 return 구문 생략 가능
        - () 안쓰고 그냥 한줄에 써도 된다!!
        - ex) const add = num ⇒ num + 1
    
    ### 중요!!! 화살표함수에서는 this 가 다르게 동작한다!
    
    ### 객체의 메소드로 화살표함수 사용시, this 사용 주의
    

- **Filter 메소드**
    - 배열의 인자를 하나씩 꺼내서, 그 인자로 동작한다
    - 조건을 씌워서, 조건 만족하는 배열 원소로만, 새로운 배열 만듬
    - ex) numbers.filter(n ⇒ { return n < 10 })
    
- **some / every 메소드**
    - every : 배열의 인자 하나라도, 조건 만족 못하면 false 반환
    - some : 배열의 인자 하나라도, 조건 만족하면 true 반환
    
    - ex) exams.some( score ⇒ score > 70 );
        - 하나 이상의 학생 성적이 70 초과면 true
    - ex) exams.every( score ⇒ score > 70 );
        - 모든 학생 성적이 70 초과여야지만 true

- **reduce 메소드**
    - 어떤 동작을하며, 원소 하나만 남기는 메소드!
    - 두가지 인자를 받는다. 남겨질 인자, 연산시 이용할 인자
        - reduce( (accumulator, currentValue) ⇒ {} )
        
    - ex) evens.reduce( (sum, num) ⇒ sum + num );
    - ex) evens.reduce( (sum, num) ⇒ sum + num, 100);
        - sum 의 시작값을 100으로 한다
        

- **setTimeout()**
    - 자바의 sleep() 과 비슷함!!
    - 두 인자를 받음. 시간끝나면 실행할 함수 & 기다릴 시간
    - ex) setTimeout( () ⇒ {console.log(”Hello!”)} , 3000)

- **setInterval()**
    - 두인자를 받음. 반복해서 실행할 함수 & 사이 기다릴 시간
    - 일정 간격을 두고, 반복해서 함수를 실행함
    
    - id 라는 변수에 setInterval() 반환값 저장후, clearInterval(id) 를 하면 중단된다!!

# JS의 최신 기능들

- **함수의 디폴트 매개변수**
    - 매개변수가 존재하지만, 아무것도 입력 안했을경우, default 값 설정 가능
    - ex) function rollDie(num = 6) {}
        - rollDie() 이렇게 호출시, num 에 6이 들어간다!
    
    - 디폴트 매개변수는, 반드시 뒤쪽에 위치해야한다!!
        - ex) function rollDie(num1, num2 = 6){}
        - rollDie(5) 하게되면, num1 에 5 들어가고 num2는 6이된다
    

- **스프레드 구문 (expanded)**
    
    
    - 배열, 문자열, 객체 를 풀어서 요소 하나하나로 만든다!
    - … 을 앞에 붙이기!
    
    1. 함수에 사용하는 스프레드
        - ex) Math.max(arr) → 배열을 인자로 못받음! 풀어서 줘야함
        - ex) Math.max(…arr) → 이러면 정상 작동!
        - ex) console.log(”hello”) → hello 라는 문자열 하나 출력
        - ex) console.log(…”hello”) → h e l l o 라는 문자들 나열된것 출력
    
    1. 배열에 사용하는 스프레드 (concat 보다 간편)
        - num1 = [1,2,3] / num2 = [4,5,6]
        - allnum = […num1, …num2]
            - 이런식으로 매우 쉽게 배열을 합칠 수 있다
        
        - […”hello”]
            - 이런식으로 문자열을 풀어서 배열에 저장 가능
        
    2. 객체에 사용하는 스프레드
        - 배열과 마찬가지로, 객체를 합칠때 주로 사용
            - const cat = { legs: 4, family: “A”}
            - const dog = { isFurry: true, family:”B” }
            - const catdog = {…cat, …dog}
                - 이렇게되면 family key 가 곂치므로, 나중에 온 객체로 덮어씌기 된다. 따라서 family: “B” 가 된다. 순서가 중요!!
        
        - 객체에 배열이나 문자열을 spread 하게되면, index가 key 가 된다
        
- **Rest 매개변수**
    
    
    - 함수에, 정해지지 않은 크기의 인자를 받고싶을때!!
    - spread 처리된 인자를 매개변수 자리에 넣는다.
        
        
        - ex) function sum (…nums) {}
        - 이러면, 매개변수에 몇개의 원소를 넣던, 그 원소들로 이루어진 배열이 되어서 nums 에 저장된다
        
    - 일반 매개변수와 섞어 쓸 수 있다!!
        
        
        - ex) function sum (num1, num2, …num){}
        - 이러면, sum(1,2,3,4,5,6) 했을때, 1 과 2는 num1 과 num2에 저장,    나머지 3,4,5,6은 배열로서 num에 저장된다

- **분해 (Destructuring)**
    
    
    1. 배열분해 (변수로 지정)
        - arr = [1,2,3,4,5] 있을때
            - const [a, b, c, …all] = arr;  하게되면
            - a = arr[0] / b = arr[1] / c = arr[2] 가 된다!!
            - 그리고 all = [4, 5] 가 된다!
    
    1. 객체분해 (변수로 지정)
        - const user = {} 있을때
            - const { key1, key2, key3 = ‘default’ } = user;
                - 객체의 value를 변수에 저장 가능!!
                - 없는 key일경우 default 값 지정 가능
            - const { key1: new_key1 } = user;
                - 객체의 key 명 변경 가능!!!
    
    1. 매개변수 분해
        - 함수의 매개변수가 배열이나 객체라면, 매개변수에서 구조 분해 가능!
            - function num({num1, num2}) { return num1 + num2 };
            - arr = [1,2,3,4,5]
            - num(arr) 하게되면
                - 반환값 : 3 이 된다

# DOM

- 요소선택 (selecting)
    - document.getElementById(’’) 메서드
        - 해당 아이디를 가진 요소를 선택하여 HTMLcollection 자료형으로 반환한다
        - HTMLcollection은 배열이 아니다! HTML 객체들의 집합임
        - indexing은 가능 but 배열 메소드 사용 불가
    
    - document.getElementsByTagName(’’) 메서드
        - elements 복수형이다!
        - 해당 tag를 가진 요소를 선택하여 HTMLcollection 자료형으로 반환한다
        
    - document.getElementsByClassName(’’) 메서드
        - elements 복수형이다!
        - 해당 class명을 가진 요소를 선택하여 HTMLcollection 자료형으로 반환한다
        
    - **document.querySelector(’’)**
        - 일치하는 첫번째 요소 반환
        - ID, tag, class 등 모든속성으로 선택 가능 (선택후 변경도 가능)
            - ID 선택 → #ID명
            - tag선택 → tag
            - 자손선택자, 인접선택자 등 모두 사용 가능!!
            - tag명[attribute 조건] 으로도 가능!
            
        - :nth-of-type 으로 첫번째 요소 이외의 요소 선택 가능
    
    - **document.querySelectorAll(’’)**
        - 일치하는 요소 모두 반환 (집합으로)
        

- 요소조작 (manipluating)
    - innerText
        - 선택한 요소의 text 요소만 가져온다 (숨겨진 텍스트 제외 실제 보이는 text만)
    - textContent
        - 선택한 요소의 모든 text를 가져온다 (숨겨진 텍스트 포함)
    - innerHTML
        - 선택한 요소의 HTML 이나 XML을 가져온다
        - 이걸로 js 에서 HTML 코드 자체를 바꿀 수 있다!
        
    - attribute 변경
        - 선택한후 .attribute명  하게되면, 해당 attribute가 선택된다. 그후 변경할 수 있다
        - setAttribute 메소드 이용하여, 속성을 추가할 수 있다
            - .setAttribute(’속성명’, ‘들어갈 요소’)
        - getAttribute 메소드 이용하여, 속성을 선택할 수 도 있다
    
    - style 변경
        - 선택한후 .style.스타일명 = ‘바꿀요소’ 하게되면, 해당 style을 변경할 수 있다
        - 스타일명은 camel case로 설정되어 있다!!
    
    - classList (html 태그 속성중, 클래스 조작법)
        - class 여러개를 추가할때 용이! (클래스 조작하는경우 많다!)
        - 선택한후 .classList.add(’’) : 해당 클래스 추가
        - 선택한후 .classList.remove(’’) : 해당 클래스 제거
        - 선택한후 .classList.contains(’’) : 해당클래스 포함되어 있는지 참거짓 반환
        - 선택한후 .classList.toggle(’’) : 해당클래스를 onoff 처리 할수 있음!
    
    - 계층 이동
        - .parentElement : 부모요소로 반환
        - .children : 자식요소들의 집합 반환
            - 인덱싱 가능! .children[1]
        - previousElementSibling / nextElementSibling
            - 앞 뒤 형제 요소 반환
    
    - 새로운 DOM 생성 & 삽입 (새로운 요소 생성)
        - const newEl = document.createElement(’img’);   // 하나의 노드 생성
            - 새로운 요소 생성!!
            - img 태그의 새로운 요소를생성하여, newEl 변수에 저장
        
        - document.body.appendChild(newEl);
            - 새로운 요소인 newEl를, body 의 아래에 추가!
            - 아니면 요소변수.appendChild(newEI) 해도 됨 (document 생략)
        
        - const p = document.querySelector(’p’);
        - p.append(”솰라솰라”);      //  요소 뒤에 추가
            - 이런식으로 append 라는 메소드를 이용하여, 기존 존재하는 요소 뒤에 추가할 수 있다
        - p.prepend(”솰라솰라”);    // 요소 앞에 추가
        
    - DOM 제거
        - 요소변수.parentElement.removeChild()
            - 제거하려는 요소의 부모요소에 메소드 적용해야됨
        - 요소변수.remove()
            - 제거하려는 요소 바로 제거됨!! 최신기능
    

# DOM 이벤트 처리

- **console.dir 로 해당 요소의 하위요소들을 볼 수 있다**

- onclick, onmouseenter 메소드
    - button 요소를 선택한후, .onclick 적용후 동작할 내용 오른쪽에 적는다
    - ex) btn.onclick = function() { console.log(”clicked”) }

- addEventListener 메소드 (최선의 메소드)
    - 감지할수있는 어떤 이벤트던 적용가능
        - 왜 이 메소드가 최선일까?
        - 동작감지시, 수행하고싶은 동작이 2개이상일경우, 다른 메소드는 덮어씌워지는데, 이 메소드는 두개다 실행됨
        - 적용할수 있는 동작이 더 많다!
        
    - btn.addEventListener(’click’, function() {} );
        - 첫번째 인자 : 감지할 동작이름
        - 두번쨰 인자 : 감지시 동작할 함수
        
        - **function을 따로 구현하여, 함수이름을 넣으려면, function 구현시, 동작 하나하나를 this. 으로 해야한다!!!**
        - ex) btn.addEventListener(’click’, func);
        - function func(){ this.style.backgroundColor = ‘red’; }
    
    - 대표적 동작이름
        - click
        - keyDown, keyUp
            - 키보드 누르면 keyDown, 떼면 keyUp
            - 키보드 눌렀다 떼면 keyDown, keyUp
        
        - change, input
            - change : 입력하다가 다른곳 클릭하면 이벤트감지
                - select 태그 같은것에서 활용 가능
            - input : 입력을 계속 할때마다 이벤트 감지
                - input.value 로 어떤값이 적혀있는지 불러올수있다
        
    - 이벤트 객체
        - addEventListener 의 두번째 인자 함수에, 매개변수 e를 넣는다.  원래 항상 전달되는 이벤트객체에 이름을 붙인것!!
        - 이 이벤트 객체로, keydown 시 어떤 key 눌렸는지 알 수 있다!
        - e.key / e.code 로 확인 가능
    
    - form제출과 preventDefault()
        - form 을 제출하게되면, 기본값으로 페이지를 새로고침하거나, action 에 할당된 페이지로 옮겨진다
        - 현제페이지에 머물고 싶으면, form에대한 ‘submit’ 이벤트객체를 만들고, e.preventDefault() 메소드 적용시키면 됨
        - **e.preventDefault() 는 맨 위에 써야함!!**
        
        - form 요소에 .elements 하게되면, form객체의 모든 정보를 담은 집합이 선택된다.
    
- 이벤트 버블링
    - 하위 요소의 이벤트 동작감지만 필요한데, 상위요소의 이벤트 동작감지까지 딸려오는 경우
    - e.stopPropagation() 처리를 하면 됨!
        - 해당 이벤트의 최종 도달점을 표시!

- 이벤트 위임
    - 미래에 추가될 요소에 대한 이벤트처리!!
    - **기존에 존재하던 요소나 id 에 대해 이미 이벤트처리가 존재해도, 새로 추가된 것에대해서는 동작 안함!!**
    - [e.target](http://e.target) 으로 선택되므로, e.target 뒤에 수행할 이벤트후 동작 기입
        - e.target.nodeName 하게되면, 어떤 요소가 해당 이벤트객체의 target 인지 알 수 있음

# JS의 비동기처리

- JavsScript는, 특정 로직의 실행이 끝날때까지 기다려주지 않고, 바로 나머지 코드를 실행시켜버리는, 비동기처리를 행한다
    - 예시1) 다음 코드를 보자
    
    // #1
    console.log('Hello');
    // #2
    setTimeout(function() {
    console.log('Bye');
    }, 3000);
    // #3
    console.log('Hello Again');
    
    - Hello → Hello Again → (3초후) Bye 순으로 출력된다!!!
    
    - 예시2)
    
    function getData() {
    var tableData;
    $.get('[https://domain.com/products/1](https://domain.com/products/1)', function(response) {
    tableData = response;
    });
    return tableData;
    }
    
    console.log(getData()); // undefined
    
    - 서버에서 받아온 response 를 화면에 띄워야하지만, 비동기처리이기 때문에, 받아오기 전에 아무것도 없는 tableData를 띄워버린다!! 문제발생!

- 해결책
    1. 콜백함수 이용
        - 특정 로직이 끝났을때, 원하는 동작 수행 가능
        
        function getData(callbackFunc) {
        $.get('[https://domain.com/products/1](https://domain.com/products/1)', function(response) {
        callbackFunc(response); 
        
        // 서버에서 받은 데이터 response를 callbackFunc() 함수에 넘겨줌
        });
        }
        
        getData(function(tableData) {
        console.log(tableData); 
        
        // $.get()의 response 값이 tableData에 전달됨
        });
        
        - 단점 : 계속 중첩이 일어나서, 가독성이 심하게 떨어진다!! (콜백지옥)
    
    1. Promise API 이용
    
    ![Untitled](JavaScript%20ae6b7a2e108040e1a3fcbf287a312de7/Untitled.png)
    
    - new Promise(function(resolve, reject){}); 으로 객체 선언.
        - 본문내용
        - resolve(성공시 반환할 데이터)
        - reject(실패시 반환할 데이터)
    - .then 과 .catch
        - .then 으로 resolve일떄의 결과값을 받을수있고
        - .catch 로 reject일때의 결과값을 받을수 있다.
        - then이 무수히 많아도, 중간에 reject된다면, 바로 catch로 넘어간다
        - 다음 then에는, 이전 then의 return값이 전달된다
    - return 값 활용
        - then ( 임의의변수 ⇒ {  }  ) / catch ( 임의의변수 ⇒ {} );
            - 임의의 변수에 return값이 자동으로 들어가게된다
    
    1. Async Function 이용
        - async 키워드
            - 함수 앞에 async 키워드 붙여주면, 자동으로 Promise 객체로 반환해줌!!! (편리해진 신기능)
            - reject() 가 없으므로, throw 구문으로 대신한다!
        
        - await 키워드
            - promise를 반환하는 함수나, promise 함수 앞에 await붙이게 되면, 순서대로 실행이 됨!!
            - promise가 해결될때 까지 실행을 멈춘다!
            - 순서대로 처리가 된다! (비동기적 처리)
            
        - async & await 에서의 예외처리
            - try-catch 구문 이용
            - .catch 대신 try-catch 구문 이용한다고 생각하면됨.
    

# JS의 서버요청

- AJAX (Asynchronous javascript and xml)
    - 오늘날은 사실 XML 보단 JSON 을 의미함!
    - XML 거의 안쓰임

- API
    - 서버요청후, 코드로된 데이터를 받아오는것이 아니다! 따라서 API가 필요
    
    - 모든 소프트웨어의 API는 endpoint가 존재!!
        - endpoint URL 에 콜론(:) 이 있으면, 변수라는뜻!
        - 사용자가 입력해야되는 변수!
    
    - API가 필요로 한다면, request 에 header 포함시킬수 있다
        - API 구성 : 쿼리문자열(URL) & Header
    
    ![캡처.PNG](JavaScript%20ae6b7a2e108040e1a3fcbf287a312de7/%EC%BA%A1%EC%B2%98.png)
    

![캡처.PNG](JavaScript%20ae6b7a2e108040e1a3fcbf287a312de7/%EC%BA%A1%EC%B2%98%201.png)

- JSON (Java Script Object Notation)
    - 다른코드나 컴퓨터에서 쓸수있는 데이터 포맷(오늘날 대부분 API는 JSON 포맷 사용)
        - 자바스크립트 객체 구문을 기반으로 함!
        - key-value 쌍으로 이루어짐!
    
    - JSON 데이터를, 자바스크립트 객체로 변환하는법
        - JSON.parse(data)
    
    - 자바스크립트 데이터를 , JSON 데이터로 변환하는법
        - JSON.stringify(value)

- HTTP 서버 요청 (by Postman)
    - HTTP 요청 메소드
        - get
    - HTTP 응답 상태코드
        - 404 : Not Foound
        - 405 : Not Allowed
        - 2 로 시작하는건 대부분 성공했다는 뜻
        - 5 로 시작하는건 서버에 문제 있다는 뜻

- 서버 요청
    1. XHR
        - 옛날에 쓰던것. 요즘 하나도 안씀
    2. Fetch API
        - Promise 객체 사용하여서, 데이터 송수신
        - fetch(’url’) 로 시작. Promise 객체를 반환한다!
            - then, catch문 활용
            - 첫번째 매개변수 : URL / 두번쨰 매개변수 : option
        
        - 데이터타입 변환이 필요 : response.json()
        
    3. AXIOS (A Library for making HTTP Requests)
        - Promise 객체 사용하여서, 데이터 송수신
        - axios.get(’url’, header) 로 시작.
            - 변수 같이전달해야 할때, url q:${} 하는방법이 있고,
            - header부분에 전달할 변수 넣어주는 방법이 있다
                - **Param 객체 설정하여 넣어준다**
                - 쿼리문자열에 들어갈 정보 많은경우 있으므로, Param 객체 적극이용
            
             
            
        - 기본 데이터타입을 json으로 설정 가능
            - axios.get(url, {responseType : “json”})
        
        - response 에 .data : 반환된 json파일 전부를 선택
        - 그후, postman 참고해서, .자유롭게 요소 선택 가능
    

# JS 프로토타입, 클래스

- prototype : 특정 자료형의 메서드가 저장되는 장소
    -