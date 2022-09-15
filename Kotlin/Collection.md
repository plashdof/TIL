# Collection

## **컬랙션**

→ 같은 type을 저장하는 자료구조

→ **immutable : read only) 생성후, 추가한값을 수정 못함.**

1. List
    - 순서있음
    - index 로 원소 접근 가능
    - 선언 : listOf<T>(1,2,2) / List(){}
    - 결과 : [1,2,2]
    - 메소드
        - .size : List 원소개수
        - .get(index) : 인덱싱
        - [index] : 인덱싱
        - .indexOf(element) : 인덱스반환
2. Set
    - 중복없는 유일원소
    - 순서없음
    - 선언 : setOf<T>(1,2,2) / Set(){}
    - 결과 : [1,2]
    - 메소드
        - .size : Set 원소개수
        - .contains(element) : 포함여부 boolean 반환
        - .isEmpty() : Set 비었는지 boolean 반환
        
3. Map
    - key-value 쌍으로 원소 관리
    - 선언 : mapOf<key T, val T>(”first” to 1, “second” to 2) / Map(){}
    - 결과 : [ { first:1 }, { second:2 } ]

→ **mutable) 생성후, 추가한 값을 수정가능**

1. MutableList
    - 선언 : mutableListOf() / MutableList(){}
2. MutableSet
    - 선언 : mutableSetOf() / MutableSet(){}
3. MutableMap
    - 선언 : mutableMapOf() / MutableMap(){}

→ **add, remove operations**

- MutableList
    - .remove(element) : 해당원소 삭제
    - .add(element) : 해당원소 추가
        - 맨뒤 인덱스에 추가됨
    - .addAll(List) : List뒤에 List 이어붙이기
    - .removeAt(index)
        - 해당 index 원소 제거

- MutableSet
    - .remove(element) : 해당원소 삭제
    - .add(element) : 해당원소 추가
    - .removeIf({ it 조건문 })
        - 해당 조건 만족하는 원소 제거

- MutableMap
    - .put(key, value) : 해당 key value 추가
    - [key] : 해당 key 의 value 반환
    - .remove(key) : 해당 key value 제거
    - .clear() : Map 초기화

**→ Retrieve operations**

1. position (List 만 사용 가능. 순서기반)
    - index
        - list.elementAt(index) : 해당 인덱스 원소반환
        - list.elementAtOrNull(index) : 해당 인덱스 원소 없으면, Null 반환
        - list.elementAtOrElse(index) {기본값} : 해당 인덱스 원소 없으면, 기본값 반환
    - 확장함수
        - list.first() : 0번째 데이터 반환
        - list.last() : 마지막 데이터 반환
        - list.firstOrNull() : 0번째 데이터 없으면 Null 반환
        - list.lastOrNull() : 마지막 데이터 없으면 Null 반환
2. condition
    - 함수의 argument 로 조건 전달
        - list.firstOrNull {조건} : 조건 만족하는 첫번째 원소 반환
        - list.find{조건} : 위와 같음
        
        - list.lastOrNull {조건} : 조건 만족하는 마지막 원소 반환
        - list.findLast {조건} : 위와 같음

**→ Transformation**

1. Set을 Map으로 변환
    - set.map { it 연산 } : set 각원소 에서 연산적용한 값들을 반환
    - set.mapIndexed { idx, value → 연산} : set 원소와 index 사이의 연산 적용한 set를 반환
    
2. List, Set을 string으로 변환출력 연산자
    
    
    ```kotlin
    
    arr = listOf(1,2,2,3)
    arr.joinToString(
    	separator = "&",
    	prefix = "start: ",
    	postfix = ": end"
    )
    
    // 결과
    "start: 1&2&2&3 :end"
    ```
    

**→ Filtering (Set, List 사용 가능)**

1. predicate
    - list.filter {조건} : 조건 부합하는 리스트 원소만 반환
    - list.any {조건} : 리스트 원소중, 조건만족 하나라도 하면 true 반환
    - list.none {조건} : 리스트 원소중, 조건만족 하나도 없으면 true 반환
    - list.all {조건} : 리스트 원소 모두 조건 만족하면 true 반환
    
2.  partition
    - list.partition {조건} : 조건 만족하는 그룹과, 만족하지 못하는 그룹 순서로 분리 (반환값 두가지로 됨)

**→ Grouping (Map 사용가능)**

1. map.groupBy { 변수 → 변수.key}

![캡처.PNG](https://user-images.githubusercontent.com/86242930/190387136-d939deff-91fd-4871-b22d-625c2c292f82.png)
