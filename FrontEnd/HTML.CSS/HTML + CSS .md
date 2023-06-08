# HTML + CSS

# <HTML>

---

## 중요 태그들

**// 닫아야됨**

### title

→ 웹페이지 맨위의 제목

### head

→ 눈으로 보이지 않는 영역의 코드

- css / js 추가할때 이쪽에

### body

→ 눈에 보이는 영역의 코드

### h1 ~ h6  (블록 태그)

- 제목들. 크기가 바뀜. 문단이 됨
- h1은 무조건 하나만 존재해야함!
- 위에서부터 순서대로 존재해야함!
- title 태그로 마우스 커서 올렸을때 툴팁 설정 가능

### p (블록 태그)

- paragraph
- 문단을 나누어줌

### div (블록 태그)

- 공간을 분할해주는 태그 (제네릭 컨테이너)
- block임
- 그룹화하여 css 적용할때 편함

### ul / ol / dl (블록 태그)

- ul : 순서없는 목록, 자동 들여쓰기 됨
- ol : 순서있는 목록 → type 속성으로 앞에올 문자 정할수 있음
- 두개 모두 li 로 각행 추가해줄수 있음 ( </li> 생략 가능)

- dl : 정의리스트
    
    → dt 로 용어 정의 (굵은채)
    
    → dd 로 설명 정의 (얇은채)
    

### pre

- 개발자 포맷 그대로 적용
- visual studio에 나와있는 그대로 적용됨

### span

- 띄어져있는 inline컨탠츠를 그루핑해주는 태그 (제네릭 컨테이너)
- inline임

### table

- caption : 표제목
- thead : 주로 th들을 thead 에 삽입
- tbody : 테이블의 주된 행들
- tfoot : 테이블의 마지막 행

- th : 테이블의 헤더를 정의해줌 (각열의 이름)
- tr : 테이블의 한 행을 구분해줌
- td : 단일 셀

→ tr 안에 td or th 가 들어있고, 그것이 한 행을 이룸!

→ td 속성 : rowspan → 해당 셀을, 세로로 몇칸 차지하게 할건지

→ td 속성 : colspan → 해당 셀을, 가로로 몇칸 차지하게 할건지

![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598.jpg)

### a

- 하이퍼링크를 뜻함
- <a href=”이동할 주소”>네이버</a> 이런식으로 사용
    - 주소에 html 파일 넣어도 해당 페이지로 이동됨
    - 주소에 #id명 넣으면,  해당 id를 가지고있는 태그로 이동됨!! (같은페이지)
    - href 다음에 download 붙이면, 클릭시 href에 정의된 파일이 다운로드됨
- target=”_blank” 를 넣으면, 새로운창에 해당 페이지 뜸
    - _self 넣으면 현재윈도우
    - _parent 넣으면 부모 윈도우
    - _top 넣으면 브라우저 윈도우

### 토글버튼 구현

- details 태그와 summary 태그로 구현
- details 태그로 크게 감싼뒤, summary 태그에 이름적고, 그 하단에 토글 내려왔을때 보여질 문구 넣으면 됨

```html
<details>
    <summary>웹의 기본 목적</summary>
    <br/>
    웹의 기본 목적은 한 컴퓨터에서 만든 문서(document)를 다른 컴퓨터에서 
    쉽게 볼 수 있도록 하는 것이다
</details>
```

### button

- 버튼을 생성
- <button type=”button”>Click</button> 으로 사용
- 클릭후 동작에 대해서는, javascript로 구현
- onclick=”javascript:alert(’click button!!’);”

### sup / sub

- sup : 윗첨자  (제곱표시)
- sub : 아래첨자 (참조표시 등)

**// 닫지 않아도 됨**

### img

- 이미지주소를 받아서 이미지를 띄움
- <img src=”이미지주소”>
- src 에 지정가능한 이미지 종류 : GIF / PNG / JPG / BMP
- **alt 속성으로, 이미지 못불러왔을경우에 나오는 이미지 설명 넣을수있음**
- width / height 또한 기본 속성으로, 적용 가능

### br

- 줄바꿈 태그

### hr

- 수평선 긋기

## 기타 태그들

// +) 기타 태그들

- sub : 아래첨자
- sup : 위첨자
- b  /  strong : 진하게
- mark : 하이라이팅
- blockquote : 인용문

- fieldset / legend
    - 박스모양 설정 가능
    - legend 가 박스의 제목 (Login 적혀있는 부분)
    
    ![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%201.jpg)
    

## 문서 구조화

→ 기능은 같지만, 문서의 구조 의미를 전달하는 태그를 사용하고, 문서를 구조화시킴

→ 직접 CSS를 통해 스타일은 지정해야함

### 틀

---

### header

- 페이지나 섹션의 머리말 표현
- 페이지 제목, 페이지를 소개하는 간단한 설명

### nav

- 하이퍼링크들을 모아 놓은 특별한 섹션
- 페이지 내 목차를 만드는 용도

### section

- 문서의 장(chapter, section) 혹은 절을 구성하는 역할
- 일반 문서에 여러 장이 있듯이 웹 페이지에 여러 <section>가능
- 헤딩 태그(<h1>~<h6>)를 사용하여 절 혹은 섹션의 주제 기입

### article

- 본문과 연관 있지만, 독립적인 콘텐트를 담는 영역
- 혹은 보조 기사, 블로그 포스트, 댓글 등 기타 독립적인 내용
- <article>에 담는 내용이 많은 경우 여러 <section> 둘 수 있음

### aside

- 본문에서 약간 벗어난 노트나 팁
- 신문, 잡지에서 주요 기사 옆 관련 기사, 삽입 어구로 표시된 논평 등
- 페이지의 오른쪽이나 왼쪽에 주로 배치

### footer

- 꼬리말 영역, 주로 저자나 저작권 정보

![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%202.jpg)

### 요소

---

### figure / figcaption

→ 사진을 넣을때, figure로 감싸서 넣고, 그 아래 figcaption 으로 사진에 대한 설명을 넣는다

```html
<figure>
    <img src="vetoven.webp" width="250" height="300"/>
    <figcaption>요제프 카를 슈틸러에 의한 베토벤의 초상화 (1820년), 장엄 미사를 작곡하고 있다.</figcaption>
</figure>
```

### mark / time / meter / progress

- mark : 중요한 텍스트임을 표시
- time : 텍스트의 내용이 시간임을 표시
- meter : 주어진 범위나 %의 데이터 량 표시
- progress : 작업의 진행 정도 표시

## Form

→ form 은 하나의 컨테이너이다.

- name 으로 이름지정
- method 으로 전송방식 지정 : GET / POST
- enctype : 데이터의 인 코딩 타입
- action=”” 으로 어디로 보낼것인지 선언
    - type=submit 버튼이 눌리면 전송됨
- 데이터를 어디론가 보내는 역할
- http 요청이 전송된다

### Input

- textfield 를  뜻함
- <input type=”” /> 로 사용
- type에 뭘 넣느냐에따라, 다양한 입력을 받을수 있음
- ex) password, text, number, color 등등

![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%203.jpg)

- placeholder 이라는 attribute 도 존재
    - 입력전 설명을 적는 란

- name 이라는 attribute 존재
    - 입력된 값을 대표하는 변수를 지정
    - 데이터를 서버로 전송할때 사용
- maxlength / size 로 입력제한 가능

- type=”submit”
    - 버튼처럼 쓸 수 있음.
    - 클릭시 form 제출됨

- type=”radio”
    - 하나만 선택할수있는 선택지
    - type=”radio” 하면되고
    - 여러 input의 name을 통일시켜야 하나만 선택되게 할 수 있다
    - 그리고 선택된 값을 전달하려면, value 라는 attribute 추가해야함.
    
          제출시 name=value 로 form이 전달된다 
    

- type=”range”
    - 게이지바 생성하여, 게이지 조절 입력 기능
    - 최소 최댓값 min, max로 attribute 부여 가능
    - 얼만큼 이동할지 step 으로 attribute 부여 가능
    - 최초값 설정 value로 가능
    

### button

- form 안에 있으면, form을 제출하는 기능이 디폴트
    - type=”submit” 지정해주기도 함
    - input 에 submit type 주어서 버튼으로 쓰기도 함
- form 안에 있으면서 form 제출안하게 하려면, type=”button” 속성 부여해야 한다
- form 밖에있으면, form제출 안함

- button & input
    
    
    ![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%204.jpg)
    

### label

- label 클릭시 연결된 태그로 이동!
    - label 로 다른 태그 감싸기
    - id 로 다른 태그와 연결
    - <label for=”연결할id명”>문자</label> 로 사용

### textarea

- 여러줄 입력할 수 있다
- rows=”” 로 입력할 행수 조절 가능
- cols=”” 로 입력할 열수 조절 가능

### datalist

- 목록 리스트 작성 태그
- option 태그로 항목 하나 표현
- input 태그의 list 와 id를 같게하여, input 에 들어갈 항목 고르게 할 수 있음
    
    
    ![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%205.jpg)
    

### select

- 눌렀을때, 드롭다운 리스트 나오며 선택할수 있게 만듬.
- <select> </select> 로 구현
    - 여기에 통일된 name attribute 부여
    - size 로 개수 지정
    - multiple 속성 부여로, 다수선택 가능
- 사이에 <option></option> 으로 선택지를 넣는다!
    - 여기에 value attribute 부여

### form 의 유효성 검사

- 제출하기전, 양식을 정해 맞는지 체크할수 있다
- 여러 attribute가 있지만 대표적으로 required (아무것도 입력 안했을때 반려)

## HTML entity code

- 기호를 쓰고싶을때 행하는 코드
    - &로시작 ;로 종료
    - 각 기호마다 entity code가 존재 (아스키코드처럼)
    - 구글링해서 찾아서 사용해야한다

## HTML 의미적 markup

- div 대신, div와 똑같은 기능의 tag
    - main / section / articile / nav 등등
    - 코드를 보고, 어떤기능인지 알 수있다!!
    - 코드를 보고 웹페이지를 분석할때, 아주 유용하고 중요하다

- html 에는, 기능이 같고 이름이 다른 태그가 무수히 많다

# <CSS>

---

## HTML 적용 / 배경 / 색 / 텍스트

## HTML 과의 연결

1. style 태그 사이에 지정
    - head 사이에 style태그 만들어서 지정
    - 여러번 지정해도 됨
2. .css 파일로 만들어서 linking
    - <link rel=”stylesheet” href=”css파일 위치”>
    - link 도 head 사이에 적용해야함
3. 인라인 지정
    - 태그 안에 style = “” 로 지정
    - “” 안에 해당 태그에 적용시킬 스타일들 작성

- css에서 각 특성 뒤에 세미콜론은 필수다!

## 색, 배경 속성

**색 선언하는 법**

- rgb로 색 선택
    - 직접 색이름 입력하지 않고, rgb(0, 0, 0) 으로 입력 가능!
    

- rgba로 색 선택
    - rgba(0, 0, 0, 0) 으로 입력 가능!
    - 맨 마지막 요소가 추가됐는데, 그건 투명도이다!! 0 ~ 1 로 설정 가능
    
- 16진법으로 색 선택
    - XXXXXX  두글자씩 rgb의 요소이다!
    - #XXXXXX 로 선언

- 색 이름으로 색 선택
    - CSS3 표준에 있는 색 이름으로 선택
    - blue / red 등등

**글자색깔**

- color: 색명

**배경색**

- background-color: 색명
- background: 색명
    - 이것도 가능하지만, background은 더 많은 동작도 가능!
    - google 에 color picker 검색후, 원하는색 보고 선택 가능

**배경 이미지 지정**

- background-image : url(””)
- background-position : center center
    - 배경 이미지의 위치
    - 첫번째 : x축 / 두번째 : y축
    
- background-repeat : repeat-y
    - 배경 이미지 반복 출력
    - repeat-y : 위에서 아래로 이미지 반복출력
    - repeat-x : 왼쪾에서 오른쪽으로 이미지 반복출력

- background-size: cover;
    - 박스크기에 맞게 이미지 최대한 맞춰서 나타남

**테두리색**

- border-color : 색명

속기법

- background 속기법은, 순서 상관없지만, 위치다음 / 크기를 입력해야함!
    - background: url() no-repeat center/cover;
    - background: url() center/20%;

## 텍스트 속성

- text-align: 텍스트 축 위치 (center, left, right)
- font-weight: 글씨 굵기 (bold, normal, 숫자도 가능 (보통 400이 normal) )
- text-decoration: 텍스트 꾸미기 (underline, overline 등등 여러가지 존재)
- line-height: 줄간격 (숫자, 퍼센트, px, normal 등 다양하게 입력 가능)
- letter-spacing: 글자간격 (px)

- font-size: 글자크기 (px)
- font-family: 글씨체 설정 (css font stack 사이트 이용)
    - 연달아서 백업 폰트를 넣어두면, 적용안되는 UI 에서는 백업폰트로 나타남
    - 글씨체들의 집합이 있다. (fantasy, sans-serif)
        - 이거로 지정하면, 집합중 임의로 하나의 글씨체 시스템이 선택해줌
- text-transform: 글자형태(대소문자 등)
- text-shadow
    
    ![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%206.jpg)
    

## CSS Selector

**전체선택자**

- *로 시작
- 모든 요소들에 스타일 적용시킨다
- *{}

**element 선택자**

- tag 명으로 시작
- 해당 tag들에게 스타일 적용시킨다
- tag명{}

**ID 선택자**

- tag 에 id attribute를 부여
- #ID명{} 으로 선언
- 해당 ID가 부여된 요소들에 스타일 적용시킨다
- button 에도 ID 부여 가능

**클래스 선택자**

- 클래스명을 임의로 지음
- .class명{}
- 적용할때는, 태그뒤에 class=”” 로 적용시킬 클래스명 입력
- ID선택자와 작용 비슷하다!

**자손선택자**

- 앞에오는 tag에 속한 모든 뒤에오는 tag에 적용
- li a{}   (li 에 속한 모든 a 에 적용)
- 앞에오는 tag와 뒤어오는 tag는 다른형태의 선택자가 되어도 된다

**다중 선택자**

- 쉼표를 사용해, 여러 선택자를 한번에 적용 가능
    
    ex) h1, h2{}
    

**인접 선택자**

- h1 + p{}
- + 로 적용할 선택자 연결
- h1 바로다음에 오는 p 에만 적용! (h1에는 적용되는것이 아님)

**직계자손 선택자**

- div > li{}
- div 바로 밑에 있는 li 에만 적용
- 자손선택자는, div 에 속해만 있으면 모두 적용이었었다!!

**속성 선택자**

- input[type=”text”] {}
    - 선택자중 type=”text” 에만 적용

- a[href*=”example”]
    - a 선택자중, href 에 example 이 ‘포함된’ 것에만 적용

가상 **클래스** 

- 선택자:가상클래스명{} 으로 적용
    - 특정상황에만 적용!
- button:hover{}
    - button 에 마우스커서 올렸을때만, 적용!!
- button:active{}
    - 마우스 누르고있는 상황에 적용
- button:nth-child(even){}
    - 짝수번째 button에만 아래 스타일 적용
- button:nth-of-type(3){}
    - 3번째 button에만 적용!
    - 3n 을 쓰면, 3번째 버튼마다 button에 적용
- input[type=text]:focus{}
    - 포커스 받을때 스타일 적용
    - input 텍스트상자 클릭했을경우!

![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%207.jpg)

**유사 요소 (선택한 요소의 부분에 적용)**

- 선택자::유사요소명{} 으로 적용
    - 특정상황에만 적용!
- ::selection
    - 드래그하는것에 대해 적용
- ::first-letter
    - 첫글자에만 적용
- ::first-line
    - 첫줄에만 적용

## CSS 우선순위

**// 선택자가 가리키는 대상 겹칠때**

**같은 종류의 선택자**

- css는 위에서 아래로 흐르는 폭포구조이다
- 동일한 선택자는, 밑에서 작성한 선택자가 최종 적용됨

**다른 종류의 선택자 but 대상이 같음 (feat. 특이도)**

- button:hover{color: red}
- .post button:hover{color: blue}
    
    
- 이런경우 두개가 충돌하게 된다
- 충돌시, 더 구체적인 선택자를 우선 적용함!! (시스템이 알아서)

- ID > class > element  순서로 우선순위 가짐

**!important**

- 해당 선택자의 우선순위를 최우선으로 만듬

**// 요소가 충돌하여, 한요소가 다른요소 가릴때**

**요소의 우선순위를 나타내는 속성**

- z-index: 숫자;
- 겹치는 요소중 우선순위를 매길수 있다!!

## CSS 박스모델

**가로세로**

- width: px;
- height: px;

**테두리 (border)**

- border-width: 테두리의 굵기 (px)
- border-color: 테두리의 색깔
- border-style: 테두리의 선 종류
    - solid → 실선
    - dotted → 점선

- 상하좌우 테두리속성 각각 변경 가능!
    - ex) border-left-color: 왼쪽테두리 색깔
    - ex) border-bottom-style: 아래테두리 선 종류

- 속기법
    - border: width | style | color
    - 속기법으로 한줄에 테두리 특성 작성 가능
        - ex) border: 5px solid white

- 모깎기 (border-radius)
    - border-radius: px or %(테두리 모서리를 깎는다)
    
    ![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%208.jpg)
    

- 표 테두리 제어 (border-collapse)
    - 표는 각 요소마다 border가 적용될시에, border가 두줄로 나타남
    - table 태그에 border-collapse:collapse 적용해주면 한줄로 바뀜

- 테두리 이미지 삽입
    - border-image : url(””) 30 round
    - 순서대로 이미지URL / 이미지에서 몇 px 만큼 자를건지 / 배치속성
    - 속성종류 : round / repeat / stretch

![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%209.jpg)

**padding(테두리와 콘텐츠 사이 공간)**

- 버튼내부 글씨는 그대로하고싶고, 버튼크기 키우고싶을때 → padding을 키우면 된다~!
- padding: 2px; (상하좌우 패딩 한번에 생성)
- padding: 2px 5px; (첫번째값 상하, 두번째값 좌우)
- padding: 1px 2px 3px; (첫번째값 상 두번째값 좌우 세번째값 하)
- padding: 1px 2px 3px 4px; (상 우 하 좌 (시계방향))

**margin(여백. 박스 바깥공간을 뜻함)**

- padding 과 속기법 똑같음!
- margin: 1px 2px 3px 4px;
- margin: 0 auto
    - 이렇게하면 가운데로 오게됨! 좌우여백 같게하기

- body 에도 default margin 이 존재!

**display**

- block 요소 : 한줄을 모두 사용
    - 항상 새로운 라인에서 시작
    - display: block (block으로 바꿔줌)
    - div / table / ul / li / form / p / h 등이 있다
    - width, height 적용
    - padding 적용, 다른요소 밀어냄
    - margin 적용, 다른요소 밀어냄
    
- inline 요소 : 한줄에 다른요소와 함께 사용
    - display: inline  (inline으로 바꿔줌)
    - a / span / img / select / button / input / label 등이 있다
    - width, height 적용되지 않는다
    - padding은 적용된다, but 나머지내용에 영향주진 않음(밀어내지 않음)
    - margin은, 가로에만 영향을 끼친다

- inline - block 요소 : inline 요소 이지만, margin, width, height 이 정상작동됨!
    - display: inline-block
    - ex) block요소를 나란히 가로로 정렬하고싶을때,
    
          block 성질때문에 세로로 정렬된다. inline으로 바꾸면, 너비높이여백이 적용이 안된다.!
    
     따라서, inline-block을 사용한다!!!!
    

- display: none
    - 요소를 숨긴다. javascript할때 요긴하게 쓰임
    
    ![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%2010.jpg)
    

**box-shadow**

![캡처.JPG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%25EC%25BA%25A1%25EC%25B2%2598%2011.jpg)

## CSS 박스모델 배치

→ normal flow : 웹 페이지에 나타난 순서대로 HTML 태그 배치

**위치속성 (position: ~ 으로 선언)**   

→ position 선언했으면 top,bottom,left,right 속성 선언 가능

- static: 디폴트값
    - top, bottom, left, right 가 무시된다
    
- relative : 상대배치
    - 원래있던곳의 상대적인 위치를 뜻함 (normal flow 에 대한 상대위치임)
    - 
    - top, bottom, left, right 으로 변화를 줄수 있다
    
- absolute: 절대배치
    - 가장 가까이 있는 조상을 기준으로, 상대적인 위치로 이동
    - 움직이고자 하는 방향의 반대뜻을 적어야한다!
    - 문서흐름에서 제거한다
    - 보통 absolute 적용 요소의 상위요소는 relative으로 적용하는것이 관례
    - top, bottom, left, right 으로 변화를 줄수 있다
    
- fixed: 고정배치
    - 브라우저 기준에서의 위치설정 가능
    - 브라우저의 어디부분에서 얼만큼 떨어지게 고정할건지 설정
    - 화면을 고정한다
    - 브라우저 기준에서의 위치설정 가능
    - 브라우저 창 사이즈가 아무리 바뀌어도, 브라우저 창 기준 해당 위치에 고정됨!!

- sticky: 스크롤시에만, 화면이 고정됨
    - 아예 고정시키는 fixed와 다르게, 스크롤시에만 고정됨!
    
- float : 유동배치
    - position 으로 선언하는게 아니라,
    - float : left  /  float : right 둘중 하나로 선언
    - 브라우저의 왼편 혹은 오른편에 항상 위치함
    

**위치속성 (position: ~ 으로 선언)**

## CSS 단위

- px
    - 화면에서의 절대적인 값.
- percent (%)
    - 부모 요소에 비례하여 퍼센테이지를 나타냄
    - 주로 width , height 에 많이쓰임

- em
    - 부모요소의 몇배의 크기를 갖는지를 n em 으로 작성
        - 글꼴크기의 em
            - h2{ font-size: 2em }
            - h2의 font-size는 부모요소보다 2배큰 크기를 가진다
            - 부모요소에 배수를 나타낸다
        - margin, padding, border의 em
            - 해당 요소의
            - 글꼴크기를 기준으로 변한다
            - 1em이 글꼴크기
- rem (root em)
    - 최상위 부모요소의 몇배의 크기를 갖는지를 n rem으로 작성
    - em을 사용하면, 점점 하위태그로 갈수록, 배수가 중첩된다는 단점이 있음!
        - 이것을 획일화 하는것이 rem.

- vh, vw (vertical height & vertical width)
    - 뷰포트 (브라우저 창)에 맞게 높이 너비 설정 가능!!!
    - vh, vw 는 각각 창의 1/100을 뜻함
    - 부모요소와 독립적으로 설정 가능!!
    
    - 폰트사이즈도 설정 가능!

## CSS 기타 속성들

**z-index / visibility / overflow**

- z-index : 숫자 높을수록 앞에옴 (보임)
- visibility
    - hidden : 공간은 차지하지만 안보임
    - visible : 보임
- overflow
    - hidden : 박스 넘어가는 내용 안보임
    - visible : 박스 넘어가도 내용보임
    - scroll : 박스 넘어가면 스크롤생김
    

**리스트 꾸미기**

- list-style-type : 아이템 마커 타입 지정
- list-style-image : 아이템 마커 이미지 지정
- list-style-position : 아이템 마커 출력위치 지정
- list-style : 속기법

**불투명도 & 알파채널**

- 알파채널 : rgba(0, 0, 0, 0.7)
    - rgb에서 마지막 매개변수가 불투명도 결정
    - 해당 요소의 하위요소에는 적용 안됨
    - 16진법으로도 사용 가능. 맨뒤에 00 ~ FF 사이 숫자 추가하면 됨

- 불투명도 : opacity: 0.5
    - 해당요소의 하위요소까지 적용됨
    - ex) button, text 등

**transition(변환)**

- hover 같은 변화에, 있는 attribute가, 얼마의 시간을 거쳐 변화할것인지 설정!!!
- transition: 1s
- transition: 1s 1s (1초 기다렸다가 1초만에 변환)
- transition: background-color 1s (해당 선택요소만 1초만에 변환)

- timing function
    - transition-timing-function : 함수이름
    - ease-in → 느렸다 빨라짐
    - ease-out→ 빨랐다 느려짐
    - cubic-bezier→  임의로 설정
    
    - timing function은, 개별적으로 주는게 좋음
    

**transform(변형) : 하위요소에 모두 적용된다**

- transform-origin: top right  → 변형 기준점 바꾸기

- rotate(45deg) : 기준점을 축으로 회전시키기
- scale(0.5) : 기준점을 축으로 크기를 증가 또는 감소
- translateX(200px) : X좌표 양의방향으로 200px 이동
- skey(30deg) : 기울이기

- 여러개 한줄에 띄어서 적용할수도 있다
- 어떤 도형의 동작변화 hover 넣을때, margin 건드리는것보다 transform 으로하는것이 났다

## CSS FlexBox

**// flex container 에 적용**

- display: flex;
    - width, height 모두 내용물만큼만 공간 할당됨!
    - 본축의 기본방향 : 왼→오

- flex-direction:  (주축설정)
    - row → 가로 왼오  (default)
    - row-reverse → 가로 오왼
    - column → 세로 위 아래
    - column-reverse → 세로 아래 위

- justify-content:  (주축기준 위치 설정)
    - flex-start → 주축의 앞에 위치 (순서그대로)
        
        ![캡처.PNG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/%EC%BA%A1%EC%B2%98.png)
        
    - flex-end → 주축의 뒤에 위치 (순서그대로)
        
        ![end.PNG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/end.png)
        
    - center → 주축의 가운데에 위치 (순서그대로)
        
        ![ce.PNG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/ce.png)
        
    - space-between → 첫요소 마지막요소 block에 끝에위치시키고, 공백 일정하게 위치
        
        ![sb.PNG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/sb.png)
        
    - space-around → 모든요소와 부모블록 사이까지 일정한 여백으로 위치
        
        ![sa.PNG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/sa.png)
        
    - space-evenly → 모든 요소와 block사이 요소까지 일정하게 여백
        
        ![se.PNG](HTML%20+%20CSS%2068d7ae49aca94d329217b0c990032c4b/se.png)
        
    
- flex-wrap (요소가 화면에 꽉찼을때 설정)
    - wrap:
        - 상위블록에 의해 하위블록이 사이즈가 줄지 않고, 설정한 주축대로 빈공간 찾아서 정렬된다
    
    - wrap-reverse:
        - 빈공간 찾아서 정렬될때, 축의 반대방향으로 정렬된다
        - 주의점! 정렬순서는 flex-direction로 설정된 main axis를 따른다!
        - reverse 되는것은 cross axis 이다

- align-items:  (cross축 기준 위치설정)
    - flex-end → cross axis 기준 마지막에 위치시키기
    - flex-start → cross axis 기준 처음에 위치시키기
    - center → cross axis 기준 가운데에 위치시키기
    - baseline → text의 기준선에 맞춘다! (각 글자를 잇는 밑줄에 맞춘다고 생각!)

- align-content:
    - 주축기준 여러행이나 여러열이 존재한다면 (상위박스 크기 꽉찰때), 그떄의 열이나 행끼리의 정렬을 컨트롤한다
    - justify-content 와 동작자체는 똑같다
    - flex-start / flex-end / center / space-between / space-around / space-evenly

**// flex-item 요소에 적용**

- align-self:
    - flex 컨테이너에서, 한요소만 옮길때 설정!
    - align-content와 동작자체는 똑같다

- flex-grow: / flex-shrink: / flex-basis:
    - flex-basis → 요소 배치되기 전, 최초크기 설정
        - 기존 너비 무시된다!
        - flex-basis는 주축에 걸쳐있기 때문에, 기존 width와 별개로 설정한다!
        - 주축에 따라, 너비도되고 높이도 된다!
    - flex-grow → flex 컨테이너에 남는 공간이 있을때, 요소가 그 공간을 얼마나 차지할지 결정
        - 단위를 적지 않는다
        - 1로하면, 남는공간 다차지! 기준이 된다 (첫 grow 값은 1로하던 2로하던 상관없다. 다른 grow 값에대한 기준이 될뿐)
        - 다른요소를 2로하면, 남는공간을 1:2로 채운다
        
    - flex-shrink → flex컨테이너에 공간이 부족할때, 요소가 그 공간에서 얼마나 줄어들지 결정
        - 나머지것들은 flex-grow와 같다
        
    - min-width / max-width
        - 각 요소가 줄어들고 늘어나는 최대최솟값 설정!
    
- flex 속기법 (grow / shrink / basis)
    - flex: 한줄에 3가지 속성 작성 가능

## CSS Media Query

- @media (max-width: 400px){}
    - 화면크기 400px 이하부터는, 이렇게 바꾼다
- @media (min-width: 400px{}
    - 화면크기 400px 이상부터는, 이렇게 바꾼다