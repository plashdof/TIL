# Box-shadow

# RN 에서의 box-shadow

- box-shadow 라는 CSS 속성이, RN에서는 부여될수 없다. 따라서, 다른방식을 사용해야하는데, 그것이 IOS 와 Android가 방식이 차이가 있었다

- Android같은경우, 오직 두가지 속성으로만 box shadow 를 부여할 수 있었다
    - shadowColor : 그림자 색상을 결정 (string)
    - elevation : 그림자의 너비 및 모양을 결정 (num)
    - 가장 중요한것 : shadow를 설정할 box 컨테이너의 backgroundColor 를 무조건 White로 해야한다!

![캡처.PNG](https://user-images.githubusercontent.com/86242930/185656711-9aae17c9-f0c4-4f01-9d35-a2cbda81e549.png)