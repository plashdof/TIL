


fun main(){
    var input = readln()

    // 앞뒤 괄호 제거
    input = input.substring(1,input.length - 1)
    val datas = stringToArray(input)
    print(datas)

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