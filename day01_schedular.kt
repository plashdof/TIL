
// 행 6 열 11 짜리 2차원배열 초기화
val answer = Array(11) { Array(5) { "    " } }

fun main(){
    var input = readln()

    // 앞뒤 괄호 제거
    input = input.substring(1,input.length - 1)
    val datas = stringToArray(input)
    insertData(datas)

    printTable()
}

fun printTable(){
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

// 입력받은 데이터를 2차원배열에 삽입하는 함수
fun insertData(datas : ArrayList<String>){

    for(i in 0 until datas.size step 2){

        // arr[0] : col / arr[1] : srow / arr[2] : erow
        val arr = timeParsing(datas[i])
        val name = datas[i+1]

        if(arr[0] == -1){ continue }

        var index = 0
        for(i in arr[1] until arr[2]+1 ){
            if(i == arr[2]){
                answer[i][arr[0]] = "----"
                break
            }
            answer[i][arr[0]] = name.substring(index,index+2)
            index+=2
        }
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
