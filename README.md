# log2uml

## Remote Tab

![](https://user-images.githubusercontent.com/32938245/123391614-2b88c280-d5d7-11eb-8b38-6a32a4340fce.png)

1. Host, User, Password : 원격접속할 Host, User, Password 정보
2. Command:  명령어 입력 후 Run을 누르면 원격 접속 후 명령을 실행한다.
   - 로그파일을 읽고, UML 문법에 맞춰서 수정하기 위해 사용된다
   - 명령 수행 결과는 UML_TEXT에 입력된다
3. Log : 원격 명령 수행 결과 및 로그를 출력한다


## UML_TEXT Tab

![](https://user-images.githubusercontent.com/32938245/123391785-5115cc00-d5d7-11eb-8667-3848388192f5.png)

- UML을 그리기 위한 텍스트를 입력하는 곳
- Remote Tab에서 원격 수행 결과가 출력된다
- UML을 그리기위한 규격은 다음 사이트에 정의되어 있다 (https://plantuml.com/ko/sequence-diagram)



## UML_VIEW Tab

![](https://user-images.githubusercontent.com/32938245/123391825-5d018e00-d5d7-11eb-8136-4abe00772583.png)

- 우측 하단의 Load버튼을 누르면 UML_TEXT의 내용을 읽어와 이미지로 그려준다
