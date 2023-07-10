

// 행 6 열 11 짜리 2차원배열 초기화
val answer = Array(11) { Array(5) { "    " } }

// 추가배열 선언
val extras = ArrayList<Array<Array<String>>>()

fun main(){
    var input = readln()

    // 앞뒤 괄호 제거
    input = input.substring(1,input.length - 1)
    val datas = stringToArray(input)
    parsingDatas(datas)
    printMainTable()
    printExtraTable()
}

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

// extra 테이블을 출력하는 함수
fun printExtraTable(){
    for(extra in extras){
        for(i in extra){
            for(j in i){
                print(j)
            }
            println()
        }
        println("-------------------------------")
    }
}

// 메인 테이블을 출력하는 함수
fun printMainTable(){
    println("|시간| 월 | 화 | 수 | 목 | 금 |")
    println("-------------------------------")
    var time = 9
    for(i in 0 until 11){
        if(time == 9){
            print("| 09 |")
        }else{
            print("| $time |")
        }
        time++
        for(j in 0 until 5){
            print(answer[i][j] + "|")
        }
        println()
    }
    println("-------------------------------")
}




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
        'M' -> {col = 0}
        'T' -> {col = 1}
        'W' -> {col = 2}
        'H' -> {col = 3}
        'F' -> {col = 4}
    }
    return intArrayOf(col, srow, erow)
}


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
}


// 중복데이터인지 체크하는 함수
fun checkDupli(arr : IntArray, name : String) : Boolean {
    for(i in arr[1] until arr[2]+1 ) {
        if (answer[i][arr[0]].isNotBlank()) {
            inputExtraDatas(arr,name)
            return true
        }
    }
    return false
}

// 중복데이터 따로 배열 만드는 함수
fun inputExtraDatas(arr : IntArray, name : String){
    var time = arr[1] + 9
    val size = arr[2] - arr[1] + 1
    val extra = Array(size) { Array(6) { "    |" } }
    var index = 0
    for(i in 0 until size){

        if(time == 9){ extra[i][0] = "| 09 |" }
        else{ extra[i][0] = "| $time |" }

        if(i == size-1){
            extra[i][arr[0]+1] = "----|"
            break
        }
        extra[i][arr[0]+1] = name.substring(index,index+2)  + "|"
        index+=2
        time+=1
    }
    extras.add(extra)
}


