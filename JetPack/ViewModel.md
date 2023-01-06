# ViewModel

### ViewModel 이란?

**→ 한마디로, UI (View) 에 띄우는 데이터를 분리해서 관리하기 위해 사용!!**

→ UI컨트롤러의 로직에서( Activity 와 Fragment 같은 ) 데이터 다루는 로직을 분리하기 위해 등장한, Android JetPack 라이브러리 

**→ 화면 전환 등, 특정 이벤트로 화면의 데이터가 날아가는 문제가 빈번히 발생** 

**(Fragment Activity 모두 동일)**

- 기존에는 saveInstanceState 이용
    - 담을수 있는 데이터 적다
    - 담을수 있는 데이터 형태 제한됨
    - 화면띄우는데 시간이 오래걸림

- ViewModel 사용
    - Activity 보다 생명주기가 길다! : 데이터 소멸 방지 가능
    
    ![캡처.JPG](ViewModel%20e3443dbe1a604bf68baff7ff51aeccd1/%25EC%25BA%25A1%25EC%25B2%2598.jpg)
    

→ 기존 Activity 나 Fragment 에서 사용했던 변수들 (데이터담는) 을 모두 ViewModel로 이동!

- 일반 변수로 설정하는것이 아님
- 거의 대부분 LiveData 사용

→ 안드로이드의 생명 주기를 관리하기 쉽다라는 말과 같은 말인데
상태(LifeCycle)가 변경될 때 마다 데이터를 관리해줘야 하는데
ViewModel을 사용하면 관리가 편하다

### 기본 사용법

**→ Step1. ViewModel 생성**

- ViewModel() 상속
- ViewModel 언제 실행되는지 알려면, init으로 Log 찍어보면 됨
- Activity 나 Fragment 에서 UI 데이터에 접근할 함수 생성.

```kotlin
class MainViewModel : ViewModel() {

    var countValue = 0

    init {
        Log.d("MainViewModel", "init")
    }

    fun plus(){
        countValue++
        Log.d("MainViewModel", countValue.toString())
    }

    fun minus(){
        countValue--
        Log.d("MainViewModel", countValue.toString())
    }

    fun getCount() : Int {
        return countValue
    }

}
```

**→ Step2. Activity (Fragment) 와의 연결**

- ViewModelProvider(this).get(뷰모델이름::class.java)  로 뷰모델 과 연결
- viewmodel 변수를 이용해,  데이터변수 접근하여 값 컨트롤
- Default로 Activity 가 Create 되었을때, UI값 viewmodel 데이터값으로 넣어줌

```kotlin
// ViewModel

// 1 - https://developer.android.com/topic/libraries/architecture/viewmodel#coroutines
// 2 - https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ko

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
		private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

				binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val plusBtn = binding.plus
        val minusBtn = binding.minus
        val resultArea = binding.result

        resultArea.text= viewModel.countValue.toString()

        plusBtn.setOnClickListener{
						viewModel.plus()
						resultArea.text= viewModel.countValue.toString()
				}
						
				minusBtn.setOnClickListener{
						viewModel.minus()
				    resultArea.text= viewModel.countValue.toString()
				}

}
```

### Fragment 에서의 ViewModel

### **Activity 와 Fragment의 ViewModel 공유**

- Fragment 는 Activity에 붙어있기 때문에, Activity와 ViewModel을 공유해야함!!
- Fragment 에 ViewModel을 따로 연결하면, 어차피 Activity 생명주기에 영향을 받아서 쓸모 없어짐.
- **Activity 와 Fragment 의 값 공유도 자연스레 이뤄지게 됨!!! (어렵게 데이터 서로 넘길필요 없음!!!)**

→ Step0. dependencies 추가

```xml
implementation 'androidx.fragment:fragment-ktx:1.4.1'
```

→ Step1. ViewModel 생성, Activity 와 연결

```kotlin
viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
```

→ Step2. Fragment 와 연결

```kotlin
private val viewModel : MainViewModel by activityViewModels()
```

### **Activity 와 공유하지 않고, Fragment 에서 따로 ViewModel을 사용하는 법**

```kotlin
// Activity

if(savedInstanceState == null) {

	// fragment 이동 코드

}
```

### ViewModelFactory

→ Activity 나 Fragment 에서 ViewModel 로 값을 넘겨주고 싶을때 사용!

→ 네트워크 통신 or LocalDB 사용시 이용한다!!

→ ViewModelFactory 사용하지않고, set함수 뚫어도 되지 않나 싶다

→ 예시

- MainActivity
    - ViewModelProvider 사용시 this 와 viewModelFactory 두개를 매개변수에 넣어준다

```kotlin
class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = MainViewModelFactory(5000)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    }
}
```

- ViewModel

```kotlin
class MainViewModel(num : Int) : ViewModel() {

    init {
        Log.d("MainViewModel", num.toString())
    }

    // Repository
    // 네트워크 통신을 하거나

    // LocalDB
    // Room SQLite

}
```

- ViewModelFactory

```kotlin
class MainViewModelFactory(private val num : Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(num) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
```

### ViewModel + LiveData 사용법

### LiveData 란??

→ 관찰 가능한 데이터 클래스

→ 데이터를 관찰해줄 수 있는 친구 (LifeCycle 과 결합해서)

### MutableLiveData 기본예제

- observe 안의 내용 → Mutablelivedata 값이 변할때마다, 변화를 관찰해서 observe 안의 구문이 실행이 된다!!
- Mutablelivedata의 값을 변경시키려면, Mutablelivedata.value를 이용
    - Mutablelivedata.value = Mutablelivedata.value!!.plus(1)
    - Mutablelivedata 값을 +1 처리해준다
- Mutablelivedata를 viewmodel 로 옮겨서 사용해주면 됨!

```kotlin
// LiveData
// https://developer.android.com/topic/libraries/architecture/livedata

class MainActivity : AppCompatActivity() {

    private var testMutableLiveData = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnArea).setOnClickListener {
            testMutableLiveData.value = testMutableLiveData.value!!.plus(1)
        }

        testMutableLiveData.observe(this, Observer {
            Log.d("testMutableLiveData", testMutableLiveData.value.toString())
            findViewById<TextView>(R.id.textArea).text = testMutableLiveData.value.toString()
        })
    }
}
```

### MutableLiveData + LiveData 사용 예제

→ MutableLiveData vs LiveData

- LiveData : 변경 불가능한 데이터
    - ViewModel 에서 변경 가능 / 접근 가능
    - 외부 (Activity/Fragment) 에서 변경 불가 / 접근 가능
- MutableLiveData : 변경 가능한 데이터
    - ViewModel 에서 변경 가능 / 접근 가능
    - 외부 (Activity/Fragment) 에서 변경 가능 / 접근 가능

→ 그냥 MutableLiveData 쓰면 되지 않는가??

- 외부(Activity/Fragment) 에서 ViewModel의 데이터를 변경하지 못하게 막는 역할
- 외부(Activity/Fragment) 에서 접근은 가능하게, 변경은 불가능하게 해야함!
- **따라서 MutableLiveData + LiveData 로 사용하는게 좋음!!**

```kotlin
private var _testMutableLiveData = MutableLiveData(0)
val testLiveData : LiveData<Int>
    get() = _testMutableLiveData
```

→ ViewModel 에서 MutableLiveData로 값 변경

```kotlin
// https://developer.android.com/codelabs/kotlin-android-training-live-data?index=..%2F..android-kotlin-fundamentals&hl=fi_FI#5

class MainViewModel : ViewModel() {

    private var _testMutableLiveData = MutableLiveData(0)
    val testLiveData : LiveData<Int>
        get() = _testMutableLiveData

    fun plusLiveDataValue(){
        _testMutableLiveData.value = _testMutableLiveData.value!!.plus(1)
    }

}
```

→ Activity 에서 MutableLiveData 가 아닌 LiveData 를 관찰

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        findViewById<Button>(R.id.btnArea).setOnClickListener {
            viewModel.plusLiveDataValue()
        }

        viewModel.testLiveData.observe(this, Observer {
            findViewById<TextView>(R.id.textArea).text = it.toString()
        })

				
        // viewModel.testLiveData.value = 10
				// 이렇게 하면 오류!!!

    }
}
```

### ViewModelScope

→ ViewModel 에서 CoroutineScope 사용시, ViewModel을 없애도(Activity를 벗어나도) 계속 진행이 된다!

- ViewModel을 벗어났을때 멈춰주는작업 별도로 해줘야함.
- **ViewModel 없어졌을때, 자동으로 멈춰지는것이 ViewModelScope**

→ Activity

```kotlin
class SecondActivity : AppCompatActivity() {

    lateinit var viewModel : SecondViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        viewModel.a()
        viewModel.b()

    }
}
```

→ ViewModel

```kotlin
// https://developer.android.com/topic/libraries/architecture/coroutines?hl=ko

class SecondViewModel : ViewModel() {

	
    fun a() {
				
				// Activity 벗어나도 계속 Log 찍힘

        CoroutineScope(Dispatchers.IO).launch {
            for(i in 0..10) {
                delay(1000)
                Log.d("SecondViewModel A : ", i.toString())
            }
        }

    }

    fun b(){

				// Activity 벗어나면 멈춤

        viewModelScope.launch {
            for(i in 0..10) {
                delay(1000)
                Log.d("SecondViewModel B : ", i.toString())
            }
        }
    }

}
```