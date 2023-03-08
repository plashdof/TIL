# ViewBinding

→ 의존성 추가

```xml
// android studio 4.0 미만

viewBinding{
	enabled=true
}

// android studio 4.0 이상

buildFeatures{
	viewBinding=true
}
```

### 방법1. lateinit으로 선언  (불안정함)

### Activity 에서 주로 사용

→ Fragment

```kotlin
class IntroFragment1 : Fragment() {

    private lateinit var binding : FragmentIntro1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }

}
```

→ Activity

```kotlin
class LoginActivity : AppCompatActivity(){

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
```

### 방법2. var ? = null  &  get() 사용 (안정적)

### 왜 굳이 이렇게 하는지 정확한 이유는????

### Fragment 에서 주로 사용

→ Fragment

```kotlin
class IntroFragment1 : Fragment() {

		// 전역 변수로 바인딩 객체 선언
    private var _binding : FragmentIntro1Binding? = null

		// 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

				// 자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해서
        // 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        _binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
```

→ Activity

```kotlin
class MainActivity : AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private var _binding: ActivityMainBinding? = null

    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해서
        // 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        _binding = ActivityMainBinding.inflate(layoutInflater)

        // getRoot 메서드로 레이아웃 내부의 최상위 위치 뷰의
        // 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시 합니다.
        setContentView(binding.root)
    }

    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroy()
    }
}
```