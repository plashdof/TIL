# Toast Message

### Default Toast Message

→ 기본 토스트 메세지

- 앱아이콘과 회색 바탕화면을 가진다
- LENGTH_SHORT / LENGTH_LONG 으로 띄워지는 시간설정 가능

```kotlin
Toast.makeText(this@MainActivity, "토스트 메세지 띄우기 입니다.", Toast.LENGTH_SHORT)
	.show()
```

### Custom Toast Message

→ Step0. Toast layout 생성

- CardView 로 text를 감싸는 형태!
- CardView의 id를 이용할것임

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.cardview.widget.CardView
        android:id="@+id/layoutContainer"
        android:layout_width="350dp"
        android:layout_gravity="center_horizontal"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_height="50dp"
        app:cardBackgroundColor="#313441"
        app:cardCornerRadius="30dp"
        app:cardElevation="3dp">

        <TextView
            android:id="@+id/tvToast"
            android:layout_gravity="center"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="이메일을 다시 발송했어요."
            android:textColor="@android:color/white"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.cardview.widget.CardView>

</FrameLayout>
```

→ Step1. Toast object 생성

- 위치설정 : this.apply 내부 setGravity
- 시간설정 : this.apply 내부 duration
- 띄우기 : view = layout / show()

```kotlin
object CustomToast {

    fun Toast.showCustomToast(message: String, activity: Activity)
    {
        val layout = activity.layoutInflater.inflate (
            R.layout.toast_custom,
            activity.findViewById(R.id.layoutContainer)
        )

        // set the text of the TextView of the message
        val textView = layout.findViewById<TextView>(R.id.tvToast)
        textView.text = message

        // use the application extension function
        this.apply {
            setGravity(Gravity.BOTTOM, 0, 40)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }
}
```

→ Step2. Toast 메세지 호출

```kotlin
Toast(this@FindpwSuccessActivity)
	.showCustomToast ("이메일을 다시 발송했어요.", this@FindpwSuccessActivity)
```