# React Native 에러노트

## app:installdebug 무한로딩 / app:installdebug FAILED

- 어떠한 SDK가 중복되어서 발생하는 문제라고는 하나, 정확한 이유는 모르겠다
- Solution
    - 에뮬레이터를 삭제하고, 새롭게 생성하니 해결되었다
    

### Malformed calls from JS: field sizes are different.

- 생전 처음보는 에러다.
- useState 사용시, 최초랜더링시 default값을 전달하게 되는데 그것이 잘못되었을경우 나는 에러라고 한다.
- [https://reactnativeseoul.org/t/malformed-calls-from-js-field-sizes-are-different/1127](https://reactnativeseoul.org/t/malformed-calls-from-js-field-sizes-are-different/1127)

![캡처.PNG](React%20Native%20%E1%84%8B%E1%85%A6%E1%84%85%E1%85%A5%E1%84%82%E1%85%A9%E1%84%90%E1%85%B3%20fc26d4af6b9242969505ebd43266bb00/%25EC%25BA%25A1%25EC%25B2%2598.png)

- HomeChart.js 에서 DrawChart.js 로 props를 전달하는 과정에서, 빈값이 전달되어서 생긴 오류였다.

- Solution
    - HomeChart.js 에서 useEffect() 안에 조건문(endfetchdata)을 하나 더 달은후, fetch가 종료될경우만 값을 chartDataObj에 담게끔 설정하였다
    
    ![캡처.PNG](React%20Native%20%E1%84%8B%E1%85%A6%E1%84%85%E1%85%A5%E1%84%82%E1%85%A9%E1%84%90%E1%85%B3%20fc26d4af6b9242969505ebd43266bb00/%25EC%25BA%25A1%25EC%25B2%2598%201.png)
    
    - parsing() 함수안에, obj에 값을 담는 로직 담겨져있음. 조건문이 없어서 빈값을 obj에 담았서 전달했었던 것이었음