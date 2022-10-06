# multipart_speed

### 1. 빌드 방법
```
cd workspace/smp-semi-real-time-agent-sj
./gradlew build
cd build/libs
```
build/libs 위치에 생성된 jar 파일을 실행스크립트 위치로 이동
```
cp srt-agent-0.0.1-SNAPSHOT.jar ~/workspace/test
```
스크립트 실행(환경 적용 - local, dev)
```
cd ~/workspace/upload
sh start.sh local
```
### 2. API 테스트 예시
client를 postman으로 요청할 경우

#### - stt 처리 요청
[POST] http://125.159.61.195:55242/stt/srtweb

### 3. Sequence Diagram 
#### - 준실시간 Agent-scdp 초기화
![stt처리요청-초기화-v4 drawio](https://user-images.githubusercontent.com/70994710/194189063-6ca29f64-6ec9-4d75-be91-df95c288baf5.png)
#### - 준실시간 처리요청
![stt처리요청_ver3 drawio](https://user-images.githubusercontent.com/70994710/193760788-7d131734-9f60-469e-8876-22906ae53ba0.png)
#### - 준실시간 처리상태 조회
![stt처리상태조회3 drawio](https://user-images.githubusercontent.com/70994710/191181432-b7622671-a3d6-4d2e-82ce-cf9296628d76.png)






