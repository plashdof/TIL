# Array vs List

## Array

**→ 선언(생성자)**

```kotlin
var arr = arrayOf(1,2,3)
// 기본 선언

var arr = Array(size){0}
// 0이 size만큼 반복되어 array 생성됨

val arr = arrayOfNulls<Int>(3)
// null 3개 들어간 Int 배열을 생성

val arr = emptyArray<Int>()
// 비어있는 Int 배열을 생성

val arr = Array(5){index->index}
// 0 ~ 4 연속된 숫자 생성
// 왼쪽이 인자 (index)  오른쪽이 반환값 (index)
// index -> index+1 하면 1 ~ 5 연속된 숫자 생성됨

val arr = (0..10).toList().toTypedArray()
// IntRange 를 이용해 생성
// IntRange는 List로만 변환 가능. 따라서 두번 변환해준다
// 0 ~ 10 연속된 숫자 생성
```

**→ 삽입 / 삭제 / 수정**

- 삽입 : plus

```kotlin
array = array.plus(4)   
array.plus(5)  // 이러면 반영 안됨
```

- 수정 : 인덱싱

```kotlin
array = arrayOf(1,2,3,4)
array[2] = 5

// print(array) -> 1,2,5,4
```

- 삭제 : 메소드 없음
    - 주로 조건을 filtering 하여, 새로운 array 선언후, 덮어쓰기 하는 방법 이용
    - 코드가 복잡해짐

```kotlin
// 메모 삭제기능. deleteList 값이 true인 값으로 새로운 array 만든뒤, 덮어쓰기

fun deleteMemos(deleteList : Array<Boolean>){
    var newMemos : Array<MemoData> = arrayOf()
    for(index in deleteList.indices){
        if(!deleteList[index]){
            newMemos = newMemos.plus(memos[index])
        }
    }
    memos = newMemos
}
```

**→ 특징**

- 배열 크기 정해져있음. 크기 변경 불가 → 따라서 메소드들은 새로운 배열을 반환하는 형태임
- 배열에서 값을 삭제하더라도 배열의 크기가 줄어들지 않아 메모리가 낭비될 수 있다.
- 인덱스를 통한 검색이 용이하다.

**→ 기타 메소드**

- slice

```kotlin
array = array.slice(0..1).toTypedArray()
```

- sort

```kotlin
array = array.sortedArray()
```

## List

**→ 선언**

```python
var list = listOf(1,2,3,4)
```

**→ 삽입 / 삭제 / 수정**

- 읽기전용 이므로 모두 불가능!!!

**→ 특징**

- 읽기전용
- 수정 불가능
- **해서, 주로 List를 MutableList 또는 ArrayList 로 변환하여 사용한다**

## ArrayList

**MutableList를 상속받은 구현체!!**

- MutableList 나 ArrayList 나 List로 인식되므로, 둘중 아무거나 사용해도 상관 없다

**→ 선언**

```kotlin
arrlist = arrayListOf()
```

**→ 삽입 / 삭제 / 수정**

- 삽입 : add(index, data)

```kotlin
arrayList.add(4) // 맨뒤에 4 추가
arrayList.add(0,5) // 0 index 에 5 추가
```

- 수정 : 인덱싱

```kotlin
arrayList = arrayListOf(1,2,3,4)
arrayList[2] = 5

// print(arrayList) -> 1,2,5,4
```

- 삭제 : removeAt(index)

```kotlin
arrayList.removeAt(0)  

// 0 index 값 삭제
// 한칸씩 땡기기
```

- 완전삭제 : clear()

```kotlin
arrayList.clear()
```

**→ 특징**

- 크기 자유로움
- 수정 가능
- 삽입, 삭제 가능