# ViewPager2

### ViewPager2 개념정리

**ViewPager란 무엇이고, 어떤 기능을 할까요?**

- 페이지 간 전환을 위한 스와이프 동작이 내장되어 있으며 기본적으로 화면 슬라이드 애니메이션을 표시해주는 AdapterView

**ViewPager가 사용된 예시에는 무엇이 있을까요?**

- 에브리타임 어플 게시판

![KakaoTalk_20230513_110916269.jpg](ViewPager2%20496ed0fee717439f9283a8f568eac9d5/KakaoTalk_20230513_110916269.jpg)

**ViewPager와 ViewPager2의 차이는 무엇일까요?**

- ViewPager 의 개선된 버전이 ViewPager2이다
- **`android:orientation`** 설정으로 ****세로 페이징 지원
- **`android:layoutDirection`** 설정으로 오른쪽에서 왼쪽(RTL) 페이징 지원
- RecyclerView 기반으로 빌드되므로, DiffUtil 유틸리티 클래스에 접근 가능

**ViewPager2에서 사용할 수 있는 속성들은 무엇이 있을까요?**

```xml
<androidx.viewpager2.widget.ViewPager2
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent" 
		**android:orientation="vertical"
		android:layoutDirection="rtl"**/>
```

- **`android:orientation`** 페이징 방향(수직,수평) 설정
- **`android:layoutDirection`** RTL 페이징 수동설정 가능

### ViewPager2 초기셋팅 (Adapter 선택)

**라이브러리 추가**

```kotlin
implementation "androidx.viewpager2:viewpager2:1.0.0"
```

**ViewPager2를 사용할 Activity/Fragment에 배치**

```xml
<!-- ViewPager2를 이용하기 위해 어떤 라이브러리를 사용해야 할까요 -->
<androidx.viewpager2.widget.ViewPager2
	android:id="@+id/view_pager
	android:width="match_parent"
	android:height="500dp" />
```

**ViewPager2에서 사용할 Adapter 설정**

- 단순히 Fragment 간 교체만을 원할 때 FragmentStateAdapter 사용
    - rough 한 방법
    
    ```kotlin
    // 3개의 화면을 구성한다고 가정
    // OneFragment, TwoFragment, ThreeFragment
    class ViewPageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    
        // 페이지 갯수 설정
        override fun getItemCount(): Int = 3
    
        // 불러올 Fragment 정의
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> SecondFragment()
                2 -> ThirdFragment()
                else -> throw IllegalArgumentException("Invalid position $position")
            }
        }
    }
    
    // MainActivity
    class MainActivity : AppCompatActivity() {
    
        private lateinit var binding : ActivityMainBinding
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
    
            binding.viewpage.adapter = ViewPageAdapter(this)
        }
    
    }
    ```
    
    - 동적인 방법
    
    ```kotlin
    // 3개의 화면을 구성한다고 가정
    // OneFragment, TwoFragment, ThreeFragment
    class ViewPageAdapter2(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    
        var fragments : ArrayList<Fragment> = ArrayList()
    
        // 페이지 갯수 설정
        override fun getItemCount(): Int = fragments.size
    
        // 불러올 Fragment 정의
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    
        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
            notifyItemInserted(fragments.size-1)
        }
    
        fun removeFragment() {
            fragments.removeLast()
            notifyItemRemoved(fragments.size)
        }
    }
    
    // MainActivity
    class MainActivity : AppCompatActivity() {
    
        private lateinit var binding : ActivityMainBinding
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
    
            val adapter = ViewPageAdapter2(this)
            adapter.addFragment(HomeFragment())
            adapter.addFragment(SecondFragment())
            adapter.addFragment(ThirdFragment())
            binding.viewpage.adapter = adapter
    
        }
    
    }
    ```
    

- 같은 Layout에 일부 View만 교체하는 경우  RecyclerViewAdapter 사용
    
    ```kotlin
    class ViewpageAdapter(val datas : Array<Int>) : RecyclerView.Adapter<ViewpageAdapter.ViewHolder>() {
    
        inner class ViewHolder(private val binding : ViewpagePostBinding) : RecyclerView.ViewHolder(binding.root){
    
            fun bind(item : Int){
                binding.ivImage.setImageResource(item)
            }
        }
    
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(datas[position])
        }
    
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ViewpagePostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(binding)
        }
    
        override fun getItemCount(): Int {
            return datas.size
        }
    
    }
    
    // MainActivity
    class MainActivity : AppCompatActivity() {
    
        private lateinit var binding : ActivityMainBinding
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
    
            val adapter = ViewpageAdapter(item.imgList)
    				binding.viewpager.adapter = adapter
        }
    
    }
    
    ```
    

### TabLayout 개념정리

**TabLayout이란 무엇이고, 어떤 기능을 할까요?**

- HorizontalScrollView를 상속받아서, 수평으로 Tab을 보여주는 Layout이다
- 주로 ViewPager2와 연결하여 사용하며, 현재페이지를 나타내는 역할을 한다

**TabLayout이 사용된 예시에는 무엇이 있을까요?**

- 인스타그램의 게시물이 2개 이상일때, Viewpager2 와 결합된 TabLayout을 볼 수 있다
    
    ![KakaoTalk_20230513_102736503.jpg](ViewPager2%20496ed0fee717439f9283a8f568eac9d5/KakaoTalk_20230513_102736503.jpg)
    

**TabLayout에서 사용할 수 있는 속성들은 무엇이 있을까요?**

```xml

<com.google.android.material.tabs.TabLayout
    android:id="@+id/tabLayout"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
		app:tabIndicatorColor="색상"
		app:tabIndicatorHeight="3dp"
		app:tabIndicatorGravity="top | center | bottom | stretch"
		app:tabGravity="fill"
		app:tabRippleColor="@android:color/transparent"
		>

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Monday" 
				android:icon="@drawable/아이콘"/>

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Tuesday" />

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Wednesday" />

</com.google.android.material.tabs.TabLayout>
```

→ TabLayout 전체 설정

- app:tabIndicatorColor="색상”
    - 밑줄 색상
- app:tabIndicatorHeight="3dp”
    - 밑줄 두께
- app:tabIndicatorGravity="top | center | bottom | stretch”
    - 밑줄 위치
- app:tabGravity="fill”
    - Tab 전체 너비 설정
- app:tabRippleColor="@android:color/transparent”
    - 탭 클릭시 색변화 설정

→ TabItem 설정

- android:icon="@drawable/아이콘”
    - TabItem 의 아이콘을 설정할 수 있다

### indicator 과 연결

**Indicator란 무엇이고, 어떤 역할을 할까요?**

- ViewPager2 의 페이지 상태를 나타내는 역할을 한다
- 현재 페이지 및, 페이지들의 위치파악에 이용된다

**ViewPager2에서 Indicator를 설정하려면 어떻게 해야할까요?**

- TabLayout 을 통해 TabLayoutMediator 로 ViewPager와 연결
- Custom Indicator 을 통해 registerOnPageChangeCallback 로 ViewPager와 연결

**Indicator과 ViewPager2 연동하기**

1. TabLayout과 ViewPager가 들어가는 XML 파일에 View 추가하기
    
    ```xml
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabIndicatorFullWidth="false"
        app:tabSelectedTextColor="#3f3fff"
        app:tabIndicatorColor="#3f3fff"
        app:tabRippleColor="#00ff0000"/>
    
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="0dp"/>
    ```
    
2. ViewPager Adapter 구현하기
    
    ```kotlin
    class ViewPageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    
        // 페이지 갯수 설정
        override fun getItemCount(): Int = 3
    
        // 불러올 Fragment 정의
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> SecondFragment()
                2 -> ThirdFragment()
                else -> throw IllegalArgumentException("Invalid position $position")
            }
        }
    }
    ```
    

1. Indicator 과 ViewPager2 연결

- By TabLayoutMediator (TabLayout)

```kotlin
val viewPagerAdapter = ViewPagerAdapter(this)
binding.view_pager.adapter = viewPagerAdapter

TabLayoutMediator(binding.tab_layout, binding.view_pager){
		// tab, position -> 적용하고 싶은 이벤트 or 명령들 작성
		tab, position -> tab.text = tabTextList[position]
}.attach()
```

- By registerOnPageChangeCallback (Custom Indicator)
    - Custom 한 indicator 을 연결할때 onPageSelected 에 연결시킬수 있음

```kotlin
binding.viewpage.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
    }
})
```

### ViewPager2 자동스크롤

- 아래와 같이 Handler와 registerOnPageChangeCallback 을 통해 구현한다

```kotlin
private val sliderImageHandler: Handler = Handler()
private val sliderImageRunnable = Runnable { binding.viewpage.currentItem = binding.viewpage.currentItem + 1 }

binding.viewpage.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        sliderImageHandler.removeCallbacks(sliderImageRunnable)
        //자동 스크롤은 3초 당 한 번 넘어가도록
        sliderImageHandler.postDelayed(sliderImageRunnable, 3000)
    }
})
```

### ViewPager2 무한스크롤

- 무한스크롤을 지원하는 기능은 없다.
- getITem 을 Int.MAX_VALUE 로 설정하고,
- binding 되는 것을 datas[position % datas.size] 로 설정하여, 맨 뒤 다음 맨앞으로 오게끔 설정!!

```kotlin
class ViewPageRecyclerAdapter(val datas : Array<Int>) : RecyclerView.Adapter<ViewPageRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ViewpageItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Int){
            binding.iv.setImageResource(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position % datas.size])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewpageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}
```