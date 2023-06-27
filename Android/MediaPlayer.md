# MediaPlayer

# 초기세팅

### 음악파일 셋팅

**→ 직접 프로젝트에 넣는경우**

- res 폴더 하위 raw 폴더 생성
- mp3파일 그대로 삽입

**→ 외부에서 받아오는 경우**

### MediaPlayer 선언

- mediaplayer 선언

```kotlin
private lateinit var mediaPlayer: MediaPlayer
```

- mp3 파일을 mediaplayer 에 초기화하며 삽입

```kotlin
mediaPlayer = MediaPlayer.create(this@MusicActivity, music.music)
```

- 음악 시작 / 정지 / 일시정지

```kotlin
mediaPlayer.start()
mediaPlayer.stop()
mediaPlayer.pause()
```

# 재생바 만들기

### SeekBar 생성

- 레이아웃 파일에 SeekBar를 생성해준다

```kotlin
<SeekBar
    android:id="@+id/seekBar"
    android:layout_width="match_parent"
    android:layout_height="5dp"
    android:layout_marginBottom="10dp"
    app:layout_constraintBottom_toTopOf="@id/tv_running_time"/>
```

### SeekBar 초기 셋팅

- mediaPlayer 가 생성되고 난뒤,  seekBar의 총길이와 현재값을 설정해준다

```kotlin
private lateinit var mediaPlayer : MediaPlayer
private var mediaFileLength : Int = 0

mediaPlayer = MediaPlayer.create(this,item.music)
mediaPlayer.setOnPreparedListener {

    // mediaPlayer의 총 길이 불러오기 (노래의 총 길이)
    // seekBar의 최댓값으로 설정
    // seekBar 현재값 0으로 설정
    mediaFileLength = mediaPlayer.duration
    binding.seekBar.max = mediaFileLength
    binding.seekBar.progress = 0
}
```

### SeekBar 진행 Handler OR Thread 생성

- mediaPlayer.isPlaying 을 통해, 재생중인지 아닌지 확인 가능
- Handler 를 이용하여, seekBar 업데이트

```kotlin
// play 버튼 누르면 음악 재생
binding.btnPlay.setOnClickListener {
    state = if(state){
        mediaPlayer.pause()
        binding.btnPlay.setImageResource(R.drawable.play)
        false
    }else{
        mediaPlayer.start()
        binding.btnPlay.setImageResource(R.drawable.pause)
        updateSeekBar()
        true
    }
}

// SeekBar 의 progress 와 position 을 0.01초마다 업데이트
private fun updateSeekBar() {
    binding.seekBar.progress = mediaPlayer.currentPosition
    if (mediaPlayer.isPlaying) {
        handler.postDelayed({ updateSeekBar() }, 10)
    }
}
```

- Thread 를 이용하여 ,seekBar 업데이트

```kotlin

private fun updateSeekBar(){
    Thread{
        while(mediaPlayer.isPlaying){
            binding.seekBar.progress = mediaPlayer.currentPosition
            Thread.sleep(10)
        }
    }.start()
}
```

### SeekBar 드래그 기능 추가

- setOnSeekBarChangeListener 를 통해, SeekBar의 변화를 감지.

```kotlin
binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if(fromUser){
            // Seekbar 손으로 드래그할경우 로직 처리
            mediaPlayer.seekTo(progress)
        }
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {

        // 드래그 시작했을 경우 음악 일시정지 하기
        mediaPlayer.pause()
        binding.btnPlay.setImageResource(R.drawable.play)
        state = false
    }
    override fun onStopTrackingTouch(seekBar: SeekBar?) {

        // 드래그 멈췄을 경우 음악 다시 시작하기
        mediaPlayer.start()
        binding.btnPlay.setImageResource(R.drawable.pause)
        updateSeekBar()
        state = true
    }
})
```

# 시간 나타내기

### 시간값 설정

- minute 과 seconds를 나타내는 UI 설정
- mediaPlayer 초기화될때, 전체시간을 Longtype으로 저장

```kotlin
private var duration : Long = 0
private var minutes = 0
private var seconds = 0

mediaPlayer = MediaPlayer.create(this,item.music)
mediaPlayer.setOnPreparedListener {

		...

    duration = mediaPlayer.duration.toLong()
}
```

### 시간값 출력함수 설정

- android os 내장함수를 통해, Long type의 시간값 duration을 활요하여, minute 과 seconds 를 구함
- 해당값을 UI에 출력하는 동작까지 설정

```kotlin
private fun setTimeText(){
    runOnUiThread {
        minutes = TimeUnit.MILLISECONDS.toMinutes(duration).toInt()
        seconds = TimeUnit.MILLISECONDS.toSeconds(duration).toInt() % 60
        var secondsString = ""
        var minutesString = ""
        if(seconds < 10) secondsString = "0$seconds"
        else secondsString = "$seconds"
        if(minutes < 10) minutesString = "0$minutes:"
        else minutesString = "$minutes"
        binding.tvRunningTime.text = minutesString + secondsString
    }
}
```

### 상황별 시간값 업데이트

1. **스레드를 통해, 진행시 업데이트**
- 재생, 일시정지 버튼을 통해 스레드시작/종료 컨트롤
- 스레드에서는, 전역변수인 duration 시간값을 seekBar로부터 10milsec 마다 업데이트 받고,
- 그 duration 값을 통해 setTimeText를 다시 검사한다.

```kotlin
private var threadState = true

binding.btnPlay.setOnClickListener {
    state = if(state){
        mediaPlayer.pause()
        threadState = false
        binding.btnPlay.setImageResource(R.drawable.play)
        false
    }else{
        mediaPlayer.start()
        threadState = true
        startThread()
        binding.btnPlay.setImageResource(R.drawable.pause)
        updateSeekBar()
        true
    }
}

private fun startThread(){
    Thread(){
        while(threadState){
            duration = binding.seekBar.progress.toLong()
            setTimeText()
            Thread.sleep(10)
        }
    }.start()
}
```

1. **SeekBar 강제 컨트롤시 업데이트**
- SeekBar를 드래그할 경우, duration 값 업데이트와 함께 setTimeText를 실행시켜준다
- SeekBar 드래그를 시작했을 경우 음악 일시정지와 함께, 시간출력 Thread도 정지시킨다
- SeekBar 드래그 멈췄을 경우, 음악시작과 함꼐, 시간출력 Thread도 실행시킨다

```kotlin
binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
  override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
      if(fromUser){
          // Seekbar 손으로 드래그할경우 로직 처리
          mediaPlayer.seekTo(progress)
          duration = progress.toLong()
          setTimeText()
      }
  }
  override fun onStartTrackingTouch(seekBar: SeekBar?) {

      // 드래그 시작했을 경우 음악 일시정지 하기
      mediaPlayer.pause()
      threadState = false
      binding.btnPlay.setImageResource(R.drawable.play)
      state = false
  }
  override fun onStopTrackingTouch(seekBar: SeekBar?) {

      // 드래그 멈췄을 경우 음악 다시 시작하기

      mediaPlayer.start()
      threadState = true
      binding.btnPlay.setImageResource(R.drawable.pause)
      updateSeekBar()
      state = true
  }
```