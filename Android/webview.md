# webview

## 초기 설정

- webview 자체를 바인딩후 선언
- webViewClient 선언후 setting 값 적용

```kotlin
binding.webview.loadUrl("https://dev.flarez.im")

binding.webview.apply {
    webViewClient = WebViewClient()
    settings.javaScriptEnabled = true
    settings.domStorageEnabled = true
    settings.useWideViewPort = true
    settings.displayZoomControls = false

    addJavascriptInterface(WebAppInterface(this@MainActivity, RoomToWebview()), "Android")
}

// javaScriptEnabled : 웹앱 패키징에 필요. 앱에서 웹 자바스크립트 함수 호출시 필요
// domStorageEnabled : 웹앱 패키징에 필요. 앱에서 웹 자바스크립트 DOM객체 호출시 필요
// useWideViewPort : true 면 웹에 지정된 너비값이 사용됨 
// displayZoomControls : 웹뷰 줌기능 플러스마이너스 버튼 활성화/비활성화

//  builtInZoomControls = false  :  웹뷰 줌기능 활성화/비활성화
//  setSupportZoom(false) : 화면 확대 활성화/비활성화
```

## 웹앱 패키징

### 웹 → 앱

- addJavascriptInterface 로, 인터페이스와, **프로토콜 명** 지정
- @JavascriptInterface 어노테이션 하위에, **함수 작성**,
    - 프로토콜명 + 함수명 으로 웹애서 앱 함수 호출 가능

```kotlin
// MainActivity

binding.webview.apply {
    webViewClient = WebViewClient()
    
    addJavascriptInterface(WebAppInterface(this@MainActivity),"Android")
}

// WebAppInterface

class WebAppInterface(activity: Activity) {

    private var act = activity
    private val TAG = "GooglepayUtil"

    @JavascriptInterface
    fun buyTicket(count:String){
        Log.d(TAG,"웹앱패키징 성공")
        Log.d(TAG,count)
        InappUtil.getPay(act, count)
    }
    @JavascriptInterface
    fun getToken(token:String){
        Log.d(TAG,"accessToken 받아오기 성공")
        Singleton.setJwt(token)
    }

    @JavascriptInterface
    fun showToast(test:String){
        Log.d(TAG,"테스트 성공")
        Toast.makeText(act,"웹으로부터 넘겨받은 값 : $test",Toast.LENGTH_SHORT)
            .show()
    }

}
```

### 앱 → 웹

- webview.loadUrl 안에 “javascript:scriptname.funcName()인자”
    - 이걸로 앱에서 웹 함수 호출 가능.
    - 함수 안에 인자넣을때 작은따옴표 필수!!!!

```kotlin
webview.loadUrl("javascript:scriptName.funcName('$purchaseResult')")
```

- 액티비티 이외의 곳에서 view 호출하는법??
    - 아래 코드로 Activity 아닌곳 (다른 class) 에서 view를 호출할수 있음.
        
        ```kotlin
        private val inflater = LayoutInflater.from(App.context())
        private val view = inflater.inflate(R.layout.activity_main, null)
        private val webview = view.findViewById<WebView>(R.id.webview)
        ```
        
    - webview 호출 Activity 에 innerclass 작성해서 사용하는 방법도 있음 (해당 방법 권장)
        
        ```kotlin
        inner class RoomToWebview {
          
            fun zoomControl(temp: Boolean) {
        
        				runOnUiThread{
        					try {
        	            if (temp) {
        	                binding.webview.settings.builtInZoomControls = true
        	                binding.webview.settings.setSupportZoom(true)
        	            } else {
        	                binding.webview.settings.builtInZoomControls = false
        	                binding.webview.settings.setSupportZoom(false)
        	            }
        	        } catch (e: Exception) {
        	            Log.d(TAG, "$e")
        	        }
        				}
                
            }
        
            fun postPurchase(data: PurchaseData) {
                Log.d(TAG, "postPurchase")
                Log.d(TAG, data.toString())
        
                try {
                    binding.webview.loadUrl("javascript:window.paymentResultOnAndroid('$data')")
                } catch (e: Exception) {
                    Log.d(TAG, "$e")
                }
        
            }
        }
        ```
        

**→ Webview 에대한 Client 는, 대표적으로 두가지가 있다**

- WebViewClient
- WebChromeClient

이 두가지중 하나만 사용해야 되는게 아니라, 각기 기능이 다르기 때문에, 두가지 다 적용시켜 사용할 수 있다!!! 매우 유용한 override 함수가 많이 있다. 한번 알아보자

## WebViewClient

### 웹뷰 줌인 줌아웃 시점 감지

```kotlin
// 최초확대 시점 감지 메소드
override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
    super.onScaleChanged(view, oldScale, newScale)

    if (newScale > oldScale) {
        // 확대
        
        if(sendState){
            Log.d(TAG,"최초확대")
            link.detectZoom()
            sendState = false
            defaultScale = oldScale
        }
        
    } else {
        // 축소
        
        if(newScale == defaultScale){ // 최초스케일과 스케일 같아지면, scaleState 값 허용
            Log.d(TAG,"줌아웃 끝")
            link.detectZoom()
            sendState = true
        }
    }
}
```

## WebChromeClient

## 웹뷰 디버깅하는 법

## 웹뷰에서 갤러리 접근하기

→ WebChromeClient 를 커스텀해서, onShowFileChooser 함수를 오버라이드한다

- 웹에서 input type=”file” 하게되면 호출됨

```kotlin
class MyWebChromeClient(private val act: Activity, private val link: MainActivity.RoomToWebview) : WebChromeClient(){

    private val TAG = "debugging"

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        Log.d(TAG,"앨범에서 사진선택")
        link.gotoGallery(filePathCallback)
        Log.d(TAG,"종료")
        return true
    }
}

```

→ 가장 먼저 filePathCallback 을 초기화 해준다!!! **필수**

- 참고로 mFilePathCallback 은 싱글톤에 선언된 ValueCallback<Array<Uri>>? 형태의 변수이다. 초기화는 null 로 되어있는 상태

```kotlin
// 프로필 사진 변경 갤러리 접근
fun gotoGallery(filePathCallback : ValueCallback<Array<Uri>>?) {
    mFilePathCallback = filePathCallback
    onCheckProfilePermission()
}
```

→ 그후 파일미디어 접근권한에 대한 체크 및 요청을 해준다

- 권한 있으면 → 바로 openGallery() 함수 실행
- 권한 없으면
    - 권한요청 모달 막혀있는 경우 → 설정탭으로 이동시킴 (gotoSettings)
    - 권한요청 모달 안막혀있는 경우 → 권한요청 모달 등장

```kotlin
// 프로필 사진 변경 권한 체크 & 로직

private fun onCheckProfilePermission(){
    // 권한 없을경우, 있을경우 분기처리
    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
        Log.d(TAG,"권한없음. 권한요청")

        if(!ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.READ_EXTERNAL_STORAGE)){
            showCustomToast("권한을 허용해주세요")
            gotoSettings("image")
        }else{
            ActivityCompat.requestPermissions(this@MainActivity,
arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                RC_PROFILE_PERMISSION)
        }
    } else {
        Log.d(TAG,"권한있음. 갤러리 열기")
        openGallery()
    }
}
```

- 권한요청에 대한 콜백
    - 권한 허용하기 누른경우 → openGallery() 호출
    - 권한 거부하기 누른경우 → **반드시!!!! mFilePathCallback?.onReceiveValue(null) 처리!!!!**

- **mFilePathCallback?.onReceiveValue 를** null 값으로 주지 않으면, 다음번에 onShowFileChooser 가 진행중 상태로 남게되어 호출되지 않는다

```kotlin
override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    
    if(requestCode == RC_PROFILE_PERMISSION){

        // 프로필이미지 권한설정 결과
        Log.d(TAG,"프로필 이미지 권한설정 결과")

        for(result in grantResults){
            if (result == PackageManager.PERMISSION_GRANTED){
                showCustomToast("권한이 허용되었습니다")
                openGallery()
                break
            }else{
                showCustomToast("권한이 허용되지 않았습니다")
                mFilePathCallback?.onReceiveValue(null)
                break
            }
        }
    }

}
```

→ openGallery() 함수로 갤러리 열기

- 초기에  `val galleryIntent = Intent(Intent.*ACTION_PICK*, MediaStore.Images.Media.*EXTERNAL_CONTENT_URI*)` 가 아닌 `val galleryIntent = Intent(Intent.*ACTION_PICK)`*  으로 하였더니, 웹에서 반응하지 못하였다…. chat gpt 도움으로 해당 코드 수정함
- Intent.ACTION_PICK : 갤러리로 바로 이동
    - 다른종류의 action intent 도 많이 있다. 알아보자

```kotlin
private fun openGallery(){
    // 갤러리 오픈 인텐트 지정
    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    // 갤러리 오픈. 반환코드 RC_GALLERY
    startActivityForResult(galleryIntent, RC_GALLERY)
}
```

→ 갤러리 intent 의 콜백을 받아줄 onActivityResult 선언

- 데이터 안에 있는 uri를 꺼내서,
- 배열형태로 바꾼뒤 onReceiveValue 안에 넣어주면 끝!!
- 당연히 else 일 경우 예외처리로 mFilePathCallback?.onReceiveValue(null) 수행

```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if(requestCode == RC_GALLERY){
        // 갤러리 사진선택 결과
        Log.d(TAG,"$resultCode  $data")
        if (resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                mFilePathCallback?.onReceiveValue(arrayOf(uri))
            }
        } else {
            mFilePathCallback?.onReceiveValue(null)
        }
        mFilePathCallback = null
    } else if(requestCode == RC_SETTINGS){
        mFilePathCallback?.onReceiveValue(null)
    }
}
```