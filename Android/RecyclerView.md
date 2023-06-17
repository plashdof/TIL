# RecyclerView

## RecyclerView 개념정리

→ **ListView vs RecyclerView**

ListView의 단점 (View 재활용 없음)

1. 스크롤시 버벅임. findViewById 메소드 사용으로 비용이 상당히 많이듬
2. 기본 애니메이션 지원이 없음. 커스터마이징 해야함
3. 오직 수직 스크롤만 가능

![캡처.JPG](https://user-images.githubusercontent.com/86242930/216821127-9ec93ff1-8028-46d9-b179-1a6147a7fdae.jpg)

**위의 단점들을 보완한것이 RecyclerView 이다**

**→ 4가지 주요 컴포넌트**

1. Adapter

앱의 데이터에서 RecyclerView에 표시되는 아이템뷰에 바인딩을 제공. 각 아이템 뷰의 위치를 데이터 소스의 특정 위치에 연결할 수 있다

1. LayoutManager

RecyclerView 내에 아이템을 어떻게 배치할지 설정 가능. Linear 또는 Grid 를 기본으로 사용 가능

1. ItemAnimator

기본 애니메이션이 제공되며, 이를 오버라이드하고 변경할 수 있다.

1. ViewHolder

화면에 그리고 싶은 개별적인 아이템의 UI를 그릴 수 있도록 도와줌

**→ 동작원리**

Adapter 가 데이터를 View에 바인딩 한후, 이를 LayoutManager 에게 제공한다

ViewHolder에 화면에 맞는 아이템 뷰의 수만 할당하고, 스크롤할때 화면에서 사라지는 View 객체는, 사라지는게 아닌 위치만 이동하여 재사용 된다. **View 객체만 재사용되는 것이지, View 객체 안에 담긴 데이터는 갱신된다 (ViewHolder 의 bind 함수를 통하여 갱신됨)**

onCreatViewHolder 를 호출하여 View를 생성, 그후 onBindViewHolder 를 호출하여 View를 바인딩하고, 최종적으로 리턴

**→ 레이아웃**

총 2가지 레이아웃이 필요하다!

우선, RecyclerView 자체가 View로서 존재하기 떄문에, 이를 삽입할 layout이 필요하다

두번째로, 데이터를 RecyclerView에 띄우는 것이므로, 데이터 하나의 형태를정의하는 layout이 필요하다

## 초기 세팅

### Step1. 레이아웃 생성

→ item 레이아웃과  recyclerview 자체가 들어갈 layout 두가지 필요

- RecyclerView 가 있는 레이아웃

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".view.SelectActivity">

		...

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/coinListRV"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="120dp"
        android:layout_marginBottom="80dp"
        app:layout_constraintBottom_toTopOf="@+id/laterTextArea"
        app:layout_constraintTop_toBottomOf="@+id/topTextArea" />

    ...

</androidx.constraintlayout.widget.ConstraintLayout>
```

- RecyclerView 의 아이템 하나에 대한 레이아웃

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="80dp"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="10dp">

        ...

    </androidx.cardview.widget.CardView>

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Step2. Adapter 생성

- Adapter 의 매개변수 : Context, Data  두가지

- ViewHolder :
- onCreateViewHolder :
- onBindViewHolder :
- getItemCount :

**→ view 사용**

```kotlin
class SelectRVAdapter(val context : Context, val coinPriceList : List<CurrentPriceResult>)
:RecyclerView.Adapter<SelectRVAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) 
		: RecyclerView.ViewHolder(view){
      
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
		: ViewHolder {
        val view = LayoutInflater.from(parent.context)
					.inflate(R.layout.intro_coin_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return coinPriceList.size
    }

}
```

**→ viewBinding 사용**

```kotlin
class SelectRVAdapter(val context : Context, val coinPriceList : List<CurrentPriceResult>)
:RecyclerView.Adapter<SelectRVAdapter.ViewHolder>(){

    inner class ViewHolder(private val viewBinding: IntroCoinItemBinding) 
		: RecyclerView.ViewHolder(viewBinding.root){
      
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
		: ViewHolder {
        val viewBinding = IntroCoinItemBinding
					.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return coinPriceList.size
    }

}
```

### Step3. Adapter 에서 각 데이터 삽입

**→ onBindViewHolder 에서 삽입(공식홈페이지 내용)**

```kotlin
class SelectRVAdapter(val context : Context, val coinPriceList : List<CurrentPriceResult>)
:RecyclerView.Adapter<SelectRVAdapter.ViewHolder>(){

    val likedCoinList = ArrayList<String>()

    inner class ViewHolder(private val viewBinding: IntroCoinItemBinding) 
		: RecyclerView.ViewHolder(viewBinding.root){

				//  레이아웃의 view 를 viewBinding 으로 연결
        val coinName : TextView = viewBinding.coinName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = IntroCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
				// 데이터 (coinPriceList) 를 view에 삽입
        holder.coinName.text = coinPriceList[position].coinName
    }

    override fun getItemCount(): Int {
        return coinPriceList.size
    }

}
```

**→ ViewHolder 에서 삽입 (bind 함수 사용. 더 편리함)**

```kotlin
class SelectRVAdapter(val context : Context, val coinPriceList : List<CurrentPriceResult>)
:RecyclerView.Adapter<SelectRVAdapter.ViewHolder>(){

    val likedCoinList = ArrayList<String>()

    inner class ViewHolder(private val viewBinding: IntroCoinItemBinding) 
		: RecyclerView.ViewHolder(viewBinding.root){

				//  레이아웃의 view 를 viewBinding 으로 연결
        val coinName : TextView = viewBinding.coinName

				// 데이터 (coinPriceList) 를 view에 삽입
				fun bind(item : CurrentPriceResult){
						coinName.text = item.coinName
				}

				
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = IntroCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
				holder.bind(coinPriceList[position])
    }

    override fun getItemCount(): Int {
        return coinPriceList.size
    }

}
```

### Step4. recyclerview 와 adapter 연결

```kotlin

val adapter = SelectRVAdapter(context, 데이터)
binding.coinListRV.adapter = adapter
binding.coinListRV.layoutManager = LinearLayoutManager(context)
```

- adapter 선언
    - adapter에 context 와 데이터를 매개변수에 넣은뒤 선언
- Recyclerview 에 adapter를 연결
- Recyclerview 의 layoutManager 설정

## checked 에러 해결

- **왜 발생하는가?**

RecyclerView 의 Adapter는, 전체 아이템 개수에 기반하여,  

화면표시 아이템개수 + 여분 아이템 개수  의 ViewHolder를 미리 생성하고,

화면에 표시되지 않는 View를 재사용 하는 구조이다.

따라서, 재사용되는 View에 체크표시가 되어있다면, 그것이 **초기화가 안된상태로 새롭게 추가**되는 것이다! 따라서, 새롭게 추가되는 View에 대해, 조건을 걸어줘야 해결이 가능하다.

- **해결방법**

별도의 checked list 리스트를 만든 뒤,  item을 화면에 띄울때,

checked list에 들어있는 item인지 판별하여 띄운다!!

## Item Click 이벤트 처리

### Activity나 Fragment 에서 이벤트 처리 시키기

→ Step1. Adapter 에 itemClick interface를 생성한다. 

- position 과 view 를 매개변수로 받는 onClick 함수 선언
- onBindViewHolder 에, item의 클릭이벤트 처리시 onClick 함수 실행되게끔 이벤트처리

```kotlin
class CoinListRVAdapter(val context : Context, val dataSet : List<InterestCoinEntity>)
    : RecyclerView.Adapter<CoinListRVAdapter.ViewHolder>() {

    interface ItemClick{
        fun onClick(view : View, position: Int)
    }

    var itemClick : ItemClick? = null

   ...

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.likeBtn).setOnClickListener{ v->
            itemClick?.onClick(v,position)
        }

        ...
    }

    ...
}
```

→ Step2. Activity 나 Fragment 에서 onClick 함수 구현하기

- 클릭 이벤트 동작을 viewModel까지 반영 가능

```kotlin
selectedRVAdapter.itemClick = object : CoinListRVAdapter.ItemClick{
    override fun onClick(view: View, position: Int) {

        viewModel.updateInterestCoinData(selectedList[position])

    }
}
```