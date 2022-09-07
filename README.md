# multipart_speed

### 1. 빌드 방법
```
cd workspace/smp-semi-real-time-agent-sj
./gradlew build
cd build/libs
```
build/libs 위치에 생성된 jar 파일을 실행스크립트 위치로 이동
```
cp srt-agent-0.0.1-SNAPSHOT.jar ~/workspace/upload
```
스크립트 실행(환경 적용 - local, dev)
```
cd ~/workspace/upload
sh start.sh local
```
### 2. 클래스 파일 생성시 주의사항
Service > JNI에 있는 파일들 클래스 파일로 만들때, spring 관련한 것들 다 주석처리 후 javac 파일명


### 3. API 테스트 예시
client를 postman으로 요청할 경우

#### - stt 처리 요청
[POST] http://~ /stt/srtweb
```
semiRealTimeRequest: fileSendType(0 or 1), wavFilePath, serviceCode, callkey, txRxType, modelAuthKey, callbackUrl을 json 형태로 전송
```
- nas 처리(fileSendType = 0)
```
semiRealTimeRequest: {"fileSendType":"0",
                      "wavFilePath":"서버 음성파일 경로",
                      "serviceCode":"서비스코드",
                      "callkey":"콜 키",
                      "txRxType":"txRxType",
                      "modelAuthKey":"음성파일에 대한 md5값",
                      "callbackUrl":"callback 처리할 url"}
wavFile: X
```
- multipart file 처리(fileSendType = 1)
```
semiRealTimeRequest: {"fileSendType":"1",
                      "wavFilePath":X,
                      "serviceCode":"서비스코드",
                      "callkey":"콜 키",
                      "txRxType":"txRxType",
                      "modelAuthKey":"멀티파트로 전송한 음성 파일에 대한 md5값",
                      "callbackUrl":"callback 처리할 url"}
wavFile: 음성파일(fileSendType = 1 일때만 필수)
```
#### - stt 처리 상태 조회
[GET] http:// ~ /stt/srtweb/status?serviceCode=""&srtId=""



