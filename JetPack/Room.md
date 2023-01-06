# Room

ex) 카카오톡 네트워크 끊어져도, 채팅목록이 보임!!

→ 아마 LocalDB에 저장해놓고 불러오는것!

→ dependencies 추가

```xml
plugins {
    ...
    id 'kotlin-kapt'
}

// ROOM
def roomVersion = "2.4.3"

implementation("androidx.room:room-runtime:$roomVersion")
annotationProcessor("androidx.room:room-compiler:$roomVersion")

// To use Kotlin annotation processing tool (kapt)
kapt("androidx.room:room-compiler:$roomVersion")

// Coroutine
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
```

### 사용법 + 구조

참고1 : https://developer.android.com/training/data-storage/room?hl=ko
참고2 :  https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=ko#5

### Entity data class

- 데이터 구조를 정의한다
- tableName / PrimaryKey / ColumnInfo / Value 설정 가능

```kotlin
@Entity(tableName = "text_table")
data class TextEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,
    @ColumnInfo(name = "text")
    var text : String

)
```

### Dao interface

- DB에 적용시킬 Query문을 작성한다
- 데이터 삽입 / 불러오기 / 삭제  세가지 구현

```kotlin
@Dao
interface TextDao {

    @Query("SELECT * FROM text_table")
    fun getAllData() : List<TextEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(text : TextEntity)

    @Query("DELETE FROM text_table")
    fun deleteAllData()

}
```

### Database abstract class

- DB를 생성한다
- 나머지내용은 문법적인 요소들… (외우거나 복붙?)

```kotlin
@Database(entities = [TextEntity::class], version = 1)
abstract class TextDatabase : RoomDatabase() {

    abstract fun textDao() : TextDao

    companion object {
        @Volatile
        private var INSTANCE : TextDatabase? = null

        fun getDatabase(
            context : Context
        ) : TextDatabase {
            return INSTANCE ?:synchronized(this){
val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDatabase::class.java,
                    "text_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
						}
						}
    }
}
```

### Activity

- DB class 의 getDatabase 실행하여 DB 가져옴
- 메인스레드에서 접근할수 없음!! CoroutineScope을 사용하여 외부 스레드에서 사용해야함

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = TextDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            db.textDao().insert(TextEntity(0, "HELLO"))
            Log.d("MAINACTIVITY", db.textDao().getAllData().toString())

        }
    }
}
```

### Multi Table 만들기

→ 두개의 Dao interface 

- TextDao
- WordDao

→ 두개의 Entity data class

- TextEntity
- WordEntity

### Database abstract class

```kotlin
@Database(entities = [TextEntity::class, WordEntity::class], version = 2)
abstract class TextDatabase : RoomDatabase() {

    abstract fun textDao() : TextDao
    abstract fun wordDao() : WordDao

    companion object {
        @Volatile
        private var INSTANCE : TextDatabase? = null

        fun getDatabase(
            context : Context
        ) : TextDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDatabase::class.java,
                    "text_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}
```

### ViewModel 에서 Room 접근 (Context 활용)

→ Activity 에서 Database 호출시, context 가 필요함!

- val db = TextDatabase.getDatabase(this)

→ ViewModel에서는 어떻게??

- 방법 1 . application 활용

```kotlin
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext
    val db = TextDatabase.getDatabase(context)

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("MainViewModel", db.textDao().getAllData().toString())
        Log.d("MainViewModel", db.wordDao().getAllData().toString())
    }

    fun insertData(text : String) = viewModelScope.launch(Dispatchers.IO) {
        db.textDao().insert(TextEntity(0, text))
        db.wordDao().insert(WordEntity(0, text))
    }

    fun removeData() = viewModelScope.launch(Dispatchers.IO) {
        db.textDao().deleteAllData()
        db.wordDao().deleteAllData()
    }
}
```

- 방법 2 : App class 따로 생성하여, 전역파일에서 context 호출할 수 있게 하기
    
    [앱 전역에서 Context 사용하기](https://www.notion.so/Context-0c03863a8b654f1f8f310355c17848a5)