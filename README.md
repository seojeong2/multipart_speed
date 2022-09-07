# multipart_speed

### 1. 빌드 방법
```
./gradlew build
cd build/libs
```
build/libs 위치에 생성된 jar 파일을 실행스크립트 위치로 이동
스크립트 실행(환경 적용 - local, dev)
```
sh start.sh local
```
### 2. API 테스트 예시
client를 postman으로 요청할 경우

#### - stt 처리 요청
[POST] http://~ /stt/srtweb
```
semiRealTimeRequest: fileSendType(0 or 1), wavFilePath, serviceCode, callkey, txRxType, modelAuthKey, callbackUrl을 json 형태로 전송

semiRealTimeRequest: {"fileSendType":"",
                      "wavFilePath":"",
                      "serviceCode":"",
                      "callkey":"",
                      "txRxType":"",
                      "modelAuthKey":"",
                      "callbackUrl":""}
wavFile: 음성파일(fileSendType = 1 일때만 필수)
```

#### - stt 처리 상태 초기화
[GET] http:// ~ /stt/srtweb/status/reset?serviceCode=""&srtId=""

