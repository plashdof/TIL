# SingleTon

## 싱글톤 객체

### 싱글톤이란?

<aside>
💡 단 하나의 객체만 필요하고, 해당 객체를 프로젝트 전역에서 사용할때 SingleTon 객체 사용한다!

</aside>

- 객체를 하나만 만들어서, 계속 재사용 하는 패턴
    - 프로젝트 전체에서 단일객체를 생성
    - 생성자가 여러차례 호출되어도, 실제 생성 객체는 하나

- 언제사용??
    - 프로젝트 전역에서, 언제든 같은값 반환하기를 기대하는 경우 사용
    - ex) 앱 사용중 저장하는 데이터
    

![캡처](https://github.com/plashdof/TIL/assets/86242930/ee32bb5f-5650-47d4-b791-26cf9ebfcdda)


### 싱글톤 객체 생성법

- **Object 키워드 사용**
- class 이지만, class 전체가 싱글톤객체로 선언됨
- 프로젝트 전역에서 사용할 수 있는, 하나의 “저장소” 느낌!

```kotlin
object Numbers {

	private val Nums = mutableListOf<Int>()
	val nums: List<Int> = Nums

	fun addnum(x : Int){
		Nums.add(x)
	}

}
```

## **무명 객체**

- **변수에 담긴 싱글톤 패턴임!!**
- 객체를 변수에 저장하여, 재사용

1. 무명객체 생성
    
    ```kotlin
    val cartItems = object{
    	val products = mutableListOf(Product("전자기기", "핸드폰"))
    	override fun toString() = products.toString()
    }
    
    print(cartItems)
    
    // 출력
    
    [Product(전자기기, 핸드폰)]
    ```
    
2. 클래스/인터페이스 상속받아 생성
    
    ```kotlin
    interface ClickListener{
    	fun onClick(view: View)
    }
    
    val clickListener = object : ClickListener{
    	override fun onClick(view: View){
    
    	}
    }
    
    ```
    

## C**ompanion object**

- **클래스 일부분이 싱글톤객체로 선언됨**
- **객체생성 없이 클래스 내부에 접근하고싶을때 사용!!**

- 클래스당 하나만 선언 가능
- 이름 붙여도 되고, 안붙여도 됨!!

- 해당클래스가 속한 클래스가 load 될때 초기화 이뤄짐
- Class 생성자는 private으로 변경해, 외부에서 생성자 사용하여 객체 변경하는것 막기

```kotlin
class Store private constructor(private val products: List<Product>){

	companion object{
		fun create(storeId: String): Store{
			return Store(fetchProducts(storeId))
		}

		private fun fetchProducts(storeId: String): List<Product>{
			// 데이터요청
			return listOf()
		}

	}

	fun showProducts(){
		products.forEach{ product ->
			// product 리스트 출력해주기
		}
	}
}

// 외부에서 클래스 접근

val store = Store.create("electronics")
store.showProducts()

```

### Companion object 활용예시!!

<aside>
💡 기존 외부에서 Activity 에 접근하고 싶을때, 
inner class 를 활용하여 RoomToAcitivity 를 선언했었다.
이를 companion class 로 대체해보았다!

</aside>

→ 기존코드

```kotlin

// MainActivity.kt

private lateinit var binding : ActivityMainBinding

var deleteList = arrayListOf<Int>()

// adapter 에서 Activity 접근할 수 있는 Room
inner class RoomToAcitivity(){

    // 메모 길게 클릭시, 삭제모드 돌입
    fun deleteStart(position : Int){
        binding.cvDelete.visibility = View.VISIBLE
        Data.deleteMode()
        makeRecycler()
    }

    // adapter 의 삭제리스트 클릭할때마다, Activity의 삭제리스트도 update
    fun setDeleteList(position: Int, state : Boolean){
        if(state){
            deleteList.add(position)
        }else{
            deleteList.remove(position)
        }
    }
}

// MainAdapter.kt

class MainAdapter(val context: Context, private val datas: ArrayList<MemoData>, 
		private val link : MainActivity.RoomToActivity){

			...

		if(item.mode == 1){
          viewBinding.btnCheck.visibility = View.VISIBLE
          viewBinding.cv.setOnClickListener {
              if(checkarr[adapterPosition]){
                  viewBinding.btnCheck.setImageResource(R.drawable.check_off)
                  checkarr[adapterPosition] = false
                  link.setDeleteList(adapterPosition, false)
              }else{
                  viewBinding.btnCheck.setImageResource(R.drawable.check_on)
                  checkarr[adapterPosition] = true
                  link.setDeleteList(adapterPosition, true)
              }
          }

      }

			...
}
```

→ 수정코드

```kotlin

// MainActivity.kt

companion object{
  @SuppressLint("StaticFieldLeak")
  private lateinit var binding : ActivityMainBinding

  // 메모 길게 클릭시, 삭제모드 돌입
  var deleteList = arrayListOf<Int>()
  fun deleteStart(position : Int){
      binding.cvDelete.visibility = View.VISIBLE
      Data.deleteMode()
      val adapter = MainAdapter( App.context(),Data.memos)
      binding.recycler.adapter = adapter
      binding.recycler.layoutManager = GridLayoutManager(App.context(),3)
  }

  // adapter 의 삭제리스트 클릭할때마다, Activity의 삭제리스트도 update
  fun setDeleteList(position: Int, state : Boolean){
      if(state){
          deleteList.add(position)
      }else{
          deleteList.remove(position)
      }
  }
}

// MainAdapter.kt

class MainAdapter(val context: Context, private val datas: ArrayList<MemoData>){

			...

		if(item.mode == 1){
          viewBinding.btnCheck.visibility = View.VISIBLE
          viewBinding.cv.setOnClickListener {
              if(checkarr[adapterPosition]){
                  viewBinding.btnCheck.setImageResource(R.drawable.check_off)
                  checkarr[adapterPosition] = false
                  MainActivity.setDeleteList(adapterPosition, false)
              }else{
                  viewBinding.btnCheck.setImageResource(R.drawable.check_on)
                  checkarr[adapterPosition] = true
                  MainActivity.setDeleteList(adapterPosition, true)
              }
          }

      }

			...
}
```