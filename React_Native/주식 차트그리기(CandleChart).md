# 주식차트 그리기 (CandleChart)

# react-native-charts-wrapper 주식차트그리기

- react native는 google chart 가 지원이 안된다. (적용시킬수 있는 무수히 많은방법 찾아봄. 그중 WebView 사용이 현실적이었으나, 서버가 따로있는 상황에서 적용이 쉽지않아 보였음)

- 따라서 수많은 라이브러리중 거의 유일하게 캔들차트를 지원하는 라이브러리인 react-native-charts-wrapper을 이용하기로 하였다. (상승과 하강에 대한 색깔설정을 할 수 있었음)

- 라이브러리 설명이 매우 불친절했다.. 처음 접한건 한 한국블로그였다
    - [https://m.blog.naver.com/noisy2/222063240495](https://m.blog.naver.com/noisy2/222063240495)  이분도 꽤 많은 라이브러리를 떠돌다가 wrapper로 정하신듯 하였다. 하지만 파이차트를 이용한 사례이기 때문에, 캔들차트는 내가 직접 라이브러리를 공부해야 했다
    - [https://github.com/wuxudong/react-native-charts-wrapper/blob/master/docs.md](https://github.com/wuxudong/react-native-charts-wrapper/blob/master/docs.md) 컴포넌트의 명세. 이곳에서 도움을 많이 받았다
    
    - [https://github.com/wuxudong/react-native-charts-wrapper/tree/master/Example/app](https://github.com/wuxudong/react-native-charts-wrapper/tree/master/Example/app) 샘플코드. 구버전 클래스컴포넌트로 예시가 되어있어서, 적용이 쉽진 않았다

- 결론적으로, 적용에 성공하였다. x축에 원하는 배열을 띄우는것과, 원치않은 정보를 지우는것이 꽤나 골치였다.

```jsx
import * as React from 'react';
import {View, processColor} from 'react-native';
import {useState, useEffect} from 'react';
import { CandleStickChart } from 'react-native-charts-wrapper';

function DrawChart({props}){

    // HomeChart.js 에서 받아올 차트데이터 형태
    let [chartdata, setChartdata] = useState([{shadowH: 0, shadowL: 0, open: 0, close: 0}])

    useEffect(()=>{
        setChartdata(props.data);
    },[])

    

    /* 데이터 라이브러리에 적용 */
    // 컴포넌트 명세서 : https://github.com/wuxudong/react-native-charts-wrapper/blob/master/docs.md
    // 샘플코드 : https://github.com/wuxudong/react-native-charts-wrapper/tree/master/Example/app

    const data={

        dataSets:[{
            values: chartdata,          
            label: 'KOSPI',
            config: {
                highlightColor: processColor('darkgray'),
                drawValues:false,
                shadowColor: processColor('black'), 
                shadowWidth: 1,
                shadowColorSameAsCandle: true,
                increasingColor: processColor('red'),       // 상승차트 빨간색
                increasingPaintStyle: 'FILL',               // 차트내부 색 채우기
                decreasingColor: processColor('blue')       // 하강차트 파랑색
              }
        }]
        
    }

    // 캔들 눌렀을때 ShadowH 표시
    const marker={
        enabled: true,                          
        markerColor: processColor('#2c3e50'),
        textColor: processColor('white'),
    }

    // x축에 표시할 데이터 props.date로 지정. 하단에 위치시킴
    const xAxis={
        position:'BOTTOM',
        valueFormatter: props.date
    }
    

    // 범례 지우기
    const legend={
        enabled: false,
        textSize: 14,
        form: 'CIRCLE',
        wordWrapEnabled: true
    }
    

    // description 지우기
    const description={
        text:''
    }

    

    return (
        <View style={{height: 200}}>
            <CandleStickChart
                chartDescription={description}
                style={{flex:1, flexDirection:'row'}}
                data={data}
                marker={marker}
                xAxis={xAxis}
                legend={legend}
            />

        </View>
        
    );

}

export default DrawChart;
```