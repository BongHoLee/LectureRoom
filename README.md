# LectureRoom
JDBC, 소켓 네트워킹을 활용한 강의실 관리 프로그램

-Server Side(강사), Client Side(수강생)를 구분한 강의실 관리 프로그램
-ORACLE DATA BASE와 접목한 JDBC 활용
-Socket Networking을 활용한 서버와 클라이언트간 통신

-클라이언트의 요청 목록을 객체 직렬화를 통해 소켓 전송 -> 서버에서 요청확인 및 데이터베이스 갱신
-클라이언트와 서버간 양방향 채팅
-다중 클라이언트를 지원 -> 멀티 스레드 구축
