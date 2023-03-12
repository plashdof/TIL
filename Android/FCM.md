# FCM

# settings

## 1. firebase 셋팅

[https://minchanyoun.tistory.com/99](https://minchanyoun.tistory.com/99)  참고

## 2. service 셋팅

→ module 단위 gradle 파일에 코드 추가

```kotlin
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    // FCM
    id 'com.google.gms.google-services'
}

dependencies {
    // FCM
    implementation platform('com.google.firebase:firebase-bom:31.2.2')
    implementation 'com.google.firebase:firebase-messaging-ktx'

}
```

→ Manifest 파일에 추가

```kotlin
<service
    android:name=".src.main.MyFirebaseMessagingService"
    android:enabled="true"
    android:exported="true"
    android:stopWithTask="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

→ Service kotlin 파일 추가

```kotlin
class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FCM"

    // Token 생성

    override fun onNewToken(token: String) {
        Log.d(TAG, "new Token: $token")

        // 토큰 값을 따로 저장
        val pref = this.getSharedPreferences( "token", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("token", token).apply()
        editor.commit()

        Log.i(TAG, "성공적으로 토큰을 저장함")
    }

    // foreground 메세지 수신시 동작 설정

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d(TAG, "From: " + remoteMessage!!.from)

        // 받은 remoteMessage의 값 출력해보기. 데이터메세지 / 알림메세지
        Log.d(TAG, "Message data : ${remoteMessage.data}")
        Log.d(TAG, "Message noti : ${remoteMessage.notification}")

        if(remoteMessage.data.isNotEmpty()){

            // 알림생성
            sendNotification(remoteMessage)

						// 포그라운드 수신시 수신한 url 데이터로 화면 이동
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            for(key in remoteMessage.data.keys){
                intent.putExtra(key, remoteMessage.data.getValue(key))
            }
            startActivity(intent)

        }else {
            Log.e(TAG, "data가 비어있습니다. 메시지를 수신하지 못했습니다.")
        }
    }

    // 메세지 알림 생성

    private fun sendNotification(remoteMessage: RemoteMessage) {

        // RequestCode, Id를 고유값으로 지정하여 알림이 개별 표시
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()

        // 일회용 PendingIntent : Intent 의 실행 권한을 외부의 어플리케이션에게 위임
        val intent = Intent(this, MainActivity::class.java)

        // 각 key, value 추가
        for(key in remoteMessage.data.keys){
            intent.putExtra(key, remoteMessage.data.getValue(key))
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack 을 경로만 남김(A-B-C-D-B => A-B)
        val pendingIntent = PendingIntent.getActivity(this, uniId, intent, PendingIntent.FLAG_ONE_SHOT)

        // 알림 채널 이름
        val channelId = "my_channel"
        // 알림 소리
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // 알림에 대한 UI 정보, 작업
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // 아이콘 설정
            .setContentTitle(remoteMessage.data["title"].toString()) // 제목
            .setContentText(remoteMessage.data["body"].toString()) // 메시지 내용
            .setAutoCancel(true) // 알람클릭시 삭제여부
            .setSound(soundUri)  // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 이후에는 채널이 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // 알림 생성
        notificationManager.notify(uniId, notificationBuilder.build())
    }

    // Token 가져오기

    fun getFirebaseToken() {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d(TAG, "Fetching FCM registration token failed ${task.exception}")
                    return@OnCompleteListener
                }
                var deviceToken = task.result
                Log.d(TAG, "token=${deviceToken}")
            })
    }
}
```

# FCM 개념

# 테스트 해보기

## PostMan 으로 테스트

→ 토큰 발급 진행

- [https://developers.google.com/oauthplayground/](https://developers.google.com/oauthplayground/) 접속
- Firebase Cloud Messaging API v1 → *[https://www.googleapis.com/auth/cloud-platform](https://www.googleapis.com/auth/cloud-platform) 선택*
- exchange authorization code for token 클릭
- accessToken 사용하기

→ PostMan 설정

- Post Url 설정
    - [https://fcm.googleapis.com/v1/projects/{프로젝트명}/messages:send](https://fcm.googleapis.com/v1/projects/flarez-912c2/messages:send)
- headers 설정
    - Authorization : Bearer accessToken
    - Content-Type : application/json
- Body 설정
    
    ```kotlin
    {
        "message": {
            "token": "firebase 토큰",
            "notification": {
              "body": "Body of Your Notification in data",
              "title": "Title of Your Notification in data"
            },
            "data": {
                "type": "NORMAL",
               	"title": "알림 제목",
                "body": "알림 바디",
                "message": "www.google.com"
            }
    
        }
    }
    ```
    

## Firebase 콘솔 알람으로 테스트

## 잠금화면 알람 처리

```kotlin
object PushUtils {
    private var mWakeLock: PowerManager.WakeLock? = null

    @SuppressLint("InvalidWakeLockTag")
    fun acquireWakeLock(context: Context) {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        mWakeLock = pm.newWakeLock(
            PowerManager.FULL_WAKE_LOCK or
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
                    PowerManager.ON_AFTER_RELEASE, "WAKEUP"
        )
        mWakeLock!!.acquire(3000)
    }

    fun releaseWakeLock() {
        if (mWakeLock != null) {
            mWakeLock!!.release()
            mWakeLock = null
        }
    }
}

class MyFirebaseMessagingService : FirebaseMessagingService() {

		override fun onCreate() {
		    super.onCreate()
		    PushUtils.acquireWakeLock(this)
		}
		
		
		override fun onMessageReceived(remoteMessage: RemoteMessage) {
		        
		    PushUtils.releaseWakeLock()
		
		    ...
		}
		
}

```