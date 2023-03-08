# AlertDialog

**→ 코틀린에서 제공하는 기본 모달창이다.**

### 기본 AlertDialog

→ Step0. 모달 띄우기

- setMessage 안에 띄울 문구 작성
- setPositiveButton 안에 버튼글씨 작성
    - DialogInterface.OnClickListener 안에, 버튼클릭시 동작 작성

```kotlin
val builder = AlertDialog.Builder(this@LoginActivity)
builder.setTitle("")
    .setMessage("아이디를 입력하세요")
    .setPositiveButton("확인",
        DialogInterface.OnClickListener { dialog, id ->
        })
builder.show()
```

→ Step1. 테마 적용

- AlertDialog의 스타일 변경을 위해서는, AppCompat 을 상속받아야 한다.
- 작성한 모달용 테마를 모달을 띄울 Activity 에 적용시킨다
- colorAccent로 버튼글자색 지정 가능.

```xml
<style name="ModalTheme" parent="Theme.AppCompat.Light.NoActionBar" >
    <item name="colorAccent">@color/poscoblue</item>
    <item name="android:statusBarColor" tools:targetApi="l">@color/white</item>
    <!-- Customize your theme here. -->
    <item name="android:windowLightStatusBar">true</item>
</style>
```