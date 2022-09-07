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
[POST] http://125.159.61.195:55242/stt/srtweb



