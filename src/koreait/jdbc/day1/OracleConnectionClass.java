package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;

// 다른 DBMS 클라이언트 프로그램과 같이 db를 사용할 수 있는 동작을 구현. 
// 제일 먼저 해야할 것 : 데이터베이스 연결 작업
public class OracleConnectionClass {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      // 0.Connection은 인터페이스로 직접 객체를 생서앟지 않고
      // 구현할 필요가 있다. db에서는 db드라이버가 접속하려는 db의 종류에 따라
      // 알아서 프록시 구현 클래스와 구현 개겣를 만든다.
      
      Connection conn = null;
      
      // 접속하고자하는 서버의 주소 (ex: https://www.naver.com)
      // 1. 아래 4개의 필수 연결 저보를 설정
      String url ="jdbc:oracle:thin:@localhost:1521:xe";
      
      // oracle.jdbc.driver.OracleDriver는 ojdbc6에 포함된 패키지 일부
      // OracleDriver 은(는) 오라클 드라이버 클래스 이름
      String driver ="oracle.jdbc.driver.OracleDriver";
      String user ="iclass";
      String password ="0419";
      
      try {
         // 2. 드라이버 클래스 객체를 메모리에 로드(올리기)      
         //       ㄴ 연결 객체를 만들어주는 역할

         // 3. DriverManager 클래스는 연결객체를 만듭니다. -2번의 객체를 동작시킨다.
         // 이 때 2번에서 만든 객체 즉 dbms 에 따라 구현객체가 만들어진다.
         Class.forName(driver);
         conn = DriverManager.getConnection(url,user,password);
         
         // 4. 3번의 결과로 오라클 db에 맞는 연결객체가 생성됩니다.
         System.out.println("연결 상태 = "+ conn);
         if(conn!=null) 
            System.out.println("오라클 데이터베이스 연결 성공!!");
         else
            System.out.println("오라클 데이터베이스 연결 실패!!");
         
      }catch (Exception e) {
         System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
         System.out.println("SQLException = url 또는 user 또는 password가 잘못됐습니다.");
         System.out.println("오류 메시지 = "+e);
         e.printStackTrace();      //Exception 발생의 모든 원인을 cascade 형식으로 출력
      }finally {
         try {
            if(conn!=null)
               conn.close();
         }catch(Exception e){
            
         }
      }

   }   // main

}



/*
 * API : Application Programming interface 
 *     : 서로 다른 software 시스템 간의 연결을 위한 방식 (JAVA 와 ORACLE) 인터페이스는 소통
 *     
 * 라이브러리 : 자바 라이브러리와 같이 특정 기능을 제공하는 클래스드르이 집합. 확장자는 압축형태 .jar
 * 
 * jdbc : 자바와 dbms를 연결하는 api (오라클 jdbc는 ojdbcX.jar 이고 'X'는 오라클 버전 )
 * 
 */
