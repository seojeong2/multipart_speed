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

- ScdpReqCallbackData.java 
  - lombok 어노테이션, import 부분 주석처리 
  - @Nullable 어노테이션, 관련 import 부분 주석처리 후
```
javac ScdpReqCallbackData.java
```
- ScdpReqData.java
  - 주석처리 없이 할 경우에는
  ex) javac -cp 라이브러리경로 ScdpReqData.java 
  - 라이브러리 여러개는 : 로 이어붙임
  ex) javac -cp 라이브러리1경로:라이브러리2경로 ScdpReqData.java
```
javac -cp 롬복라이브러리 풀경로(ex. /Users/kimseojeong/.gradle/caches/modules-2/files-2.1/org.projectlombok/lombok/1.18.24/13a394eed5c4f9efb2a6d956e2086f1d81e857d9/lombok-1.18.24.jar) ScdpReqCallbackData.java
```
- ScdpReqService.java
  - @Service @Slf4j @PostConstruct 스프링 어노테이션, 관련 import 부분 주석처리
  - init() 메서드 주석처리
  - ScdpReqService만 javac 할 경우 ScdpReqData를 못찾는 문제 발생 -> ScdpReqData, ScdpReqService 동시에 컴파일
```
javac ScdpReqData.java ScdpReqService.java
```
- ScdpResService.java
  - @Slf4j @Service 어노테이션 주석처리
  - import 부분 전체 주석처리
  - 필드 부분 ScdpResService 부분 제외하고 전체 주석처리
  - 생성자 주석처리
  - getInstace() 메서드 안의 log 출력 부분 주석처리
  - srtCallback() 메서드 안의 내용 전체 주석처리
  - ScdpResService.java 파일 내에 ScdpReqCallbackData 사용 부분이 있어 컴파일 시 같이 처리해줘야함
 ```
 javac ScdpReqCallbackData.java ScdpResService.java
 ```
 


### 3. API 테스트 예시
client를 postman으로 요청할 경우

#### - stt 처리 요청
[POST] http://125.159.61.195:55242/stt/srtweb

### 4. Sequence Diagram 
#### - 준실시간 처리요청
![STT처리요청 drawio](https://user-images.githubusercontent.com/70994710/191179428-b7733b06-23fe-4586-8964-f75567f5ee8f.png)
#### - 준실시간 처리상태 조회
![stt처리상태조회3 drawio](https://user-images.githubusercontent.com/70994710/191181432-b7622671-a3d6-4d2e-82ce-cf9296628d76.png)






