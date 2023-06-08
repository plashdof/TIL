# AsyncStorage

# AsyncStorage 사용

- 패키지명이 최근에 변경된것 같다
    - @react-native-community/async-storage
    - → @react-native-async-storage/async-storage 로 변경

- React.js 에 LocalStorage와 같은 기능을 한다!!

- setItem은 LoaclStorage와 사용법이 동일하다. 하지만 getItem할 경우, 원래 하던대로 반환값만 출력하게 되면, Promise 객체가 나온다
    
    
    - 두번째인자에 꼭 함수를 넣어줘야, 넣었던 자료형 그대로 이용할 수 있다
    
    ```jsx
    AsyncStorage.getItem('uuid', (err, result) => { //user_id에 담긴 아이디 불러오기
      console.log(result); // result에 담김 //불러온거 출력
    });
    ```
    
    - result 에 uuid 값이 담기게 되므로, 바깥스코프에서 사용하려면, useState를 사용해야한다