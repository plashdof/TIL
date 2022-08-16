# 카카오로그인 API (Native)

# 카카오 로그인 API (@react-native-seoul)

- react native는, 공식적으로 카카오에서 API를 지원하지 않는다고 한다. 따라서, 모듈을 직접 만들어 써야하는데, 이미 만들어져있는 모듈중 @react-native-seoul/kakao-login 을 이용하였다.

### 1단계 설치하기, 키해시, 패키지명 등록

- 설치 : npm install @react-native-seoul/kakao-login

- 키해시 :
    - keytool -exportcert -alias androiddebugkey -keystore ./android/app/debug.keystore -storepass android -keypass android | openssl sha1 -binary | openssl base64
    - 해당 명령어를 cmd에 입력하였는데, 안됐다!!!
        - 이유 : openssl 자리에 openssl.exe의 위치를 넣어야한다.
        - 해결 : 내 컴퓨터에 openssl이 안깔려있어서, 직접 다운받은뒤, 해당 파일내부 openssl.exe의 경로를  위의 명령어 openssl 자리에 넣었다 (/ / / /openssl.exe 여기까지 넣어야함).  키해시 나온다!
- 패키지명:
    - android/src/main/AndroidManifest.xml 에 들어가면, 상단에 보인다
    - package=”” 여기에 있다.
    
    ![캡처.PNG]![%EC%BA%A1%EC%B2%98 1](https://user-images.githubusercontent.com/86242930/184852827-9022d654-f5e4-4bda-a9cf-3072df7976be.png)
    

- 이제 키해시와 패키지명을, 카카오 개발자 사이트 내 어플리케이션에서, ANDROID 플랫폼에 등록한다
    - 여기에 등록하면 된다.
    
    ![캡처.PNG]![%EC%BA%A1%EC%B2%98 3](https://user-images.githubusercontent.com/86242930/184852854-9bdea9c2-3e0b-458f-ba54-829716f84edd.png)
    

### 2단계 REDIRECT URI 설정

- 자, 이제 android/src/main/AndroidManifest.xml 이 파일에 코드를 추가및 변경해줘야한다.

- 1 . android:allowBackup=”true” 로 변경
    - 빨간색부분을 flase 에서 true로 변경
    
    ![캡처.PNG]![%EC%BA%A1%EC%B2%98 4](https://user-images.githubusercontent.com/86242930/184852863-70b0344d-a5b5-425b-804e-6d59eee40e7f.png)
    

- 2 . 새로운 코드 추가
    - <activity> <activity/> 부분을 통채로 복붙하면 된다. native 키 부분에 알아서 넣자
    - 구글링중 대부분 빨간색부분을 뺴놓고 복붙하라는 글이 많은데, 무조건 넣자.  이것때문에 안되서 개고생 했던것 같다.
        
        ![캡처.PNG]![%EC%BA%A1%EC%B2%98 5](https://user-images.githubusercontent.com/86242930/184852891-5d17be1d-826c-491b-a775-f6de7a913af7.png)
        
    
    - 복붙용 코드
    
    ```jsx
    <activity android:name="com.kakao.sdk.auth.AuthCodeHandlerActivity"
                android:exported="true">
      <intent-filter>
          <action android:name="android.intent.action.VIEW" />
          <category android:name="android.intent.category.DEFAULT" />
          <category android:name="android.intent.category.BROWSABLE" />
    
          <!-- Redirect URI: "kakao{NATIVE_APP_KEY}://oauth“ -->
          <data android:host="oauth"
              android:scheme="kakao네이티브키" />
      </intent-filter>
    </activity>
    ```
    

### 3단계 Kakao SDK  적용

- android/build.gradle 파일에 들어가서, 코드를 추가하자

- 이 세가지 코드를 추가하면 되는데, 위치는 아래와 같다

```jsx
kotlinVersion = '1.3.41'

classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

maven { url 'https://devrepo.kakao.com/nexus/content/groups/public/' }
```

![캡처.PNG]![%EC%BA%A1%EC%B2%98](https://user-images.githubusercontent.com/86242930/184852903-f28ea964-ce72-48cf-b619-e260a0867629.png)

![캡처2.PNG]![%EC%BA%A1%EC%B2%982](https://user-images.githubusercontent.com/86242930/184852926-c9b245fa-8750-4dba-a049-b9ff6c76e0e0.png)

![캡처3.PNG]![%EC%BA%A1%EC%B2%983](https://user-images.githubusercontent.com/86242930/184852942-fcda6550-48a0-4f45-9f8e-6749102e7487.png)

### 4단계 string.XML 에 key값 추가

- android/app/src/main/res/values/string.xml 에 코드를 추가하자

```jsx
<string name="kakao_app_key">네이티브키</string>
```

![캡처.PNG](%E1%84%8F%E1%85%A1%E1%84%8F%E1%85%A1%E1%84%8B%E1%85%A9%E1%84%85%E1%85%A9%E1%84%80%E1%85%B3%E1%84%8B%E1%85%B5%E1%86%AB%20API%20(Native)%2058ec7cf3c5614a779eff47f4f769a44c/%25EC%25BA%25A1%25EC%25B2%2598%205.png)

### 5단계 실행전, gradle 캐시 지우기

- 이 과정또한 절대로 빼먹지 말자. 빌드전 gradlew clean을 진행한다

```jsx
// android 폴더로 들어가서 gradle 캐시를 지운다.
cd android
gradlew clean

// root 폴더로 돌아가서 android 실행한다!!!!!!!!!!
cd ..
npm run android
```

### 6단계 실행코드

- 메소드는 react-native-seoul github 가보면 설명이 되어있다.

- 가장 간단한 login() 함수로 설정하면된다

# 카카오 로그인 프로세스

- react 에서는, REST API로 구현을했었다.
    - 로그인성공시 인가코드 받음.
    - 프론트→서버 인가코드 전송.
    - 서버→프론트 uuid 전송.
    - 서버→카카오 인가코드로 토큰 발급

- 하지만 react native에서는, token을 프론트로 넘겨받는다.
    - 백엔드 팀원과 상의해보니, 그냥 인가코드 대신 token을 백엔드로 넘기면 똑같았다.