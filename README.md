# 나만의 체크포인트

---

- [x]  시간 입력후 요일/시작시간/종료시간 parsing 하기
- [x]  2차원 배열에 시간표 데이터 저장하기
- [x]  2차원 배열 출력함수 구현하기 (표형태)
- [x]  예외처리1 : 입력범위(2 ~ 4) 벗어난 데이터 미반영
- [x]  예외처리2 : 19시 넘긴 데이터 미반영
- [x]  예외처리3 : 시간표 겹쳤을때 하단 별도출력
- [x]  코드 리팩토링 (프로그래밍 요구사항 체크)

<br><br>

# 문제해결 과정 / 학습메모


### 배열 입력받기

스트링으로 배열형태의 input이 들어왔을때, 직접 parsing 하여 배열형태로 바꿔주는 함수를 구현하였다.

사용자가 원소 하나하나 입력하게끔 구현할 수도 있었으나, 문자열 parsing을 연습한다고 생각하고 직접 해보았다.

```kotlin 

// main 함수
// 앞뒤 괄호 제거
input = input.substring(1,input.length - 1)
val datas = stringToArray(input)


// 입력값 ArrayList로 변환해주는 함수
fun stringToArray(s : String) : ArrayList<String>{

    val arr = s.split(",")
    val datas = ArrayList<String>()

    for(i in arr){
        // 앞뒤 공백 제거
        var temp = i.trim()
        // 앞뒤 따옴표 제거
        temp = temp.substring(1,temp.length - 1)
        datas.add(temp)
    }

    return datas
}


```
<br>

### 데이터 Parsing 하기
<br>

데이터 Parsing 은 생각보다 간단하지 않았다..
필연적으로 여러 함수를 거쳐서 Parsing을 구현해야 했다

우선, 입력배열을 받아서, 한쌍의 데이터끼리 처리하는 for문을 구현하였다

```kotlin

// 입력받은 데이터를 시간,이름 짝지어서 가공하는 함수
fun parsingDatas(datas : ArrayList<String>){

    for(i in 0 until datas.size step 2){

        // arr[0] : col / arr[1] : srow / arr[2] : erow
        val arr = timeParsing(datas[i])
        val name = datas[i+1]

        if(arr[0] == -1){ continue }
        // 예외처리3 : 시간표 겹쳤을때 하단에 별도출력
        if(checkDupli(arr,name)){
            continue
        }
        inputMainDatas(arr,name)
    }
}


// 데이터중 시간데이터를, 배열포지션에 맞게 Parsing 하는 함수
fun timeParsing(s : String) : IntArray {

    val time = s
    val srow = time.substring(1,3).toInt() - 9
    val erow = time.substring(3,5).toInt() - 10  // 끝나는시간 전칸까지만 표시하므로 10뺴주기

    // 예외처리1 & 예외처리 2
    // 입력범위 벗어난 데이터 미반영 & 19시 넘긴데이터 미반영
    if(erow - srow < 1 || erow - srow > 3 || time.substring(3,5).toInt() > 19){
        return intArrayOf(-1,-1,-1)
    }

    var col = 0
    when(time[0]){
        'M' -> {col = 1}
        'T' -> {col = 2}
        'W' -> {col = 3}
        'H' -> {col = 4}
        'F' -> {col = 5}
    }
    return intArrayOf(col, srow, erow)
}

```

문자열에서 배열로 바꾼 data를 인자로 넘겨주면,
2차원에 배열에 삽입할수있는 좌표값과, 삽입할 이름 데이터로 바꾸어 주었다

좌표값은 시간을 index로 바꾸어서 반환하였고, 요일을 나타내는 문자는 곧 2차원 배열에서 어떤 Column에 올지를 나타내므로, when절을 통해 col 변수에 담아 반환하였다

ex) M0912 -> col = 1 / srow = 0 / erow = 2 
   이렇게 되면 2차원 배열에 삽입할때 answer[srow][col] ~ answer[erow][col] 이런식으로 삽입할 수 있다



<br><br>

### 데이터 2차원 배열에 insert 하기

<br><br>

처음에 row 와 col 이 2차원배열에서 어떻게 적용되는지 헷갈려서 오류가 출력되었다..

2차원 배열에서 첫번째 index는 row 데이터로, 두번째 index는 col 데이터로 삽입을 해야한다.


삽입함수는 아래에 나오는 겹치는 스케쥴을 고려하여 리팩토링 했으므로 아래에서 다루겠다

![252230385-21e190fc-3c6a-4499-9b6f-d1843a0c45ff](https://user-images.githubusercontent.com/86242930/252276069-dcfb8bfa-2cfc-4ed5-baa4-587f87bfd8e7.jpeg)

<br><br>

### 예외처리1,2 입력범위/19시 벗어난 데이터 미반영

간단히 if문 하나로 처리하였다

timePrasing 함수에서

```kotlin
// 입력범위 벗어난 데이터 미반영 & 19시 넘긴데이터 미반영
if(erow - srow < 1 || erow - srow > 3 || time.substring(3,5).toInt() > 19){
    return intArrayOf(-1,-1,-1)
}

```

정상적인 값이면 col,srow,erow 값을 담은 intArray를 반환하지만,  구간이 1 미만 3초과 했을시 (시간으로는 2 ~ 4 사이가 아닐시) -1로만 구성된 intArray를 반환한다.

또한 스케쥴이 끝나는 시간이 19를 초과하면, 똑같이 -1로 구성된 intArray를 반환한다.

반환된 int Array는 parsingDatas 함수에서 예외처리 해준다

```kotlin

fun parsingDatas(datas : ArrayList<String>){

    for(i in 0 until datas.size step 2){

        // arr[0] : col / arr[1] : srow / arr[2] : erow
        val arr = timeParsing(datas[i])
        val name = datas[i+1]

        if(arr[0] == -1){ continue }

        ...
    }
}

```


![캡처3](https://user-images.githubusercontent.com/86242930/252275341-89c57cbe-59ab-40a9-b620-1d22605fc769.JPG)

<br><br>

### 예외처리3 시간표 겹쳤을때 하단 별도출력

![캡처2](https://user-images.githubusercontent.com/86242930/252275329-324dab42-2b64-4679-a29f-47203e40e73d.JPG)

삽입함수는 총 두가지가 필요했다.

메인 테이블 출력함수, 그리고 겹치는 스케쥴을 추가로 출력하는, 추가테이블 출력함수.

초기에는 이 두가지 함수를 각각 나누고, 아예 다른 로직으로 각각 삽입하였는데, 코드가 길어져서 리팩토링이 시급해 보였다.

해서, 최대한 통일시키고자, 함수하나를 더 추가시키고, 한개의 함수로 두가지 테이블을 다 출력할수 있게 리팩토링하였다.

추가한 함수는 맨앞의 시간정보를 주어진 이차원배열에 삽입해주는 함수이다

```kotlin
// 테이블 맨앞 시간 삽입하는 함수

fun inputTimeData(table:Array<Array<String>>, startTime : Int){
    var time = startTime
    for(i in table){
        if(time == 9){ i[0] = "| 09 " }
        else{ i[0] = "| $time " }
        time++
    }
}


```
이차원 배열과, 시작시간을 넣어주면, Array 사이즈만큼 For문을 돌면서 맨앞 시간을 삽입해준다.

나머지 삽입함수은 다음과 같다

```kotlin

// 행 6 열 11 짜리 2차원배열 초기화
val answer = Array(11) { Array(6) { "    " } }

// 시간표 겹치는 스케쥴 저장하는 추가배열 선언
val extras = ArrayList<Array<Array<String>>>()


// 가공한 데이터를 이차원배열에 삽입하는 함수
fun inputMainDatas(arr : IntArray, name : String){
    var index = 0
    for(i in arr[1] until arr[2]+1 ){

        if(i == arr[2] || index == 4){
            answer[i][arr[0]] = "----"
            break
        }
        answer[i][arr[0]] = name.substring(index,index+2)
        index+=2
    }
    inputTimeData(answer,9)
}

// 중복데이터 따로 배열 만드는 함수
fun inputExtraDatas(arr : IntArray, name : String){
    val startTime = arr[1] + 9
    val size = arr[2] - arr[1] + 1
    val extra = Array(size) { Array(6) { "    " } }
    var index = 0
    for(i in 0 until size){
        if(i == size-1){
            extra[i][arr[0]] = "----"
            break
        }
        extra[i][arr[0]] = name.substring(index,index+2)
        index+=2
    }
    inputTimeData(extra,startTime)
    extras.add(extra)
}


```


두가지 모두 같은 데이터 형식을 인자로 받으면, 각각 데이터를 알맞은 이차원 배열에 삽입해준다.

extra 데이터 같은 경우는, 여러가지가 있을수 있으므로, extras 라는 전역 3차원배열을 선언하여, 필요할때마다 삽입해준뒤 한번에 출력하는 방향으로 잡았다.

위 3가지 함수를 통해, 데이터삽입은 완료되었다.




<br><br>

### 출력함수 만들기

처음에 한글이 다른문자의 2칸을 정확히 차지하지 않아서,
칸배열이 미세하게 안맞는 문제 발생하였다. 아무리 고정폭 글꼴을 적용시켜도 적용이 되지 않아, 정말 난감했다.. 이문제를 구글링 하는데만 꽤 시간을 쏟은것 같다..

Slack 에서 나와 같은 문제를 겪고있는 캠퍼분들이 나눈 스레드를 보았는데, d2coding 글꼴을 적용시키면 잘 된다고 하셨다!! 

바로 적용시켰더니, 거짓말처럼 예쁘게 잘 출력되었다.
고정폭 글꼴이란 개념을 처음 알게 되었다.

![캡처](https://user-images.githubusercontent.com/86242930/252275831-a72e5c87-83ea-453a-8148-021d793fc221.JPG)


d2coding 글꼴 적용. 칸배열 맞춤 문제 해결
![캡처](https://user-images.githubusercontent.com/86242930/252275287-f3c475bc-0186-403a-83d2-14dfbd7a6238.JPG)
<br><br>



출력함수도 삽입함수와 같이, 초기에는 두가지 함수로 이루어져 있었으나, 리팩토링을 통하여 한개의 함수로 통일하였다.

```kotlin
// 테이블을 출력하는 함수
fun printTable(table: Array<Array<String>>){
    for(i in table){
        for(j in i){
            print("$j|")
        }
        println()
    }
    println("-------------------------------")
}


```

아주 간단하게, table을 받으면, 그 table을 행렬 형식에 맞춰 출력해준다.  삽입함수 형식을 얼추 통일시키고, 리팩토링 한 덕분에 출력함수가 엄청나게 줄어들었다. 위의 함수를 호출하여 올바른 출력을 하는 main 함수 구문은 다음과 같다


```kotlin
fun main(){

    ...

    println("|시간| 월 | 화 | 수 | 목 | 금 |")
    println("-------------------------------")
    printTable(answer)
    for(extra in extras){
        printTable(extra)
    }
}


```

answer은 메인테이블이고, extras는 추가테이블들이 저장된 3차원 배열이다. 이렇게 같은 출력함수를 여러번 호출하여 올바른 출력을 도출할 수 있었다.





<br>





