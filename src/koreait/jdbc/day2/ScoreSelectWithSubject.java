package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ScoreSelectWithSubject {

   public static void main(String[] args) {
      Connection conn = OracleUtility.getConnection();   
      //미리 작성한 OracleUtility 클래스의 getConnection()메소드를 통해 OracleDB에 연결하는
      //Connection 객체를 반환받아 참조변수 conn에 저장
      
      System.out.println(":::::::::::성적을 과목으로 조회하는 메뉴:::::::::::");
      selectScore(conn);
      // 과목으로 성적을 조회하는 메소드


      OracleUtility.close(conn);
      // 미리 작성한 OracleUtility 클래스의 getConnection()메소드를 통해 
      // conn가 참조하는 Connection 객체와 OracleDB의 연결을 해제하는 메소드
      System.out.println("프로그램 종료.");
   }

   private static void selectScore(Connection conn) {
      Scanner sc = new Scanner(System.in);
      int cnt = 0;
      String subject;
      String sql = "select * from tbl_score where subject = ?";
      // "select * from tbl_score where subject = subject";
      // 조건절에 사용하는 칼럼이 기본키와 유니크일 때는 0 또는 1개 행이 조회되고 -> rs.next()를 if에 사용
      //                 기본키와 유니크가ㄴ 아닐 때는 0 ~ n 개 행이 조회됩니다.->while문에 사용
      System.out.print("조회할 성적의 과목을 입력하세요(국어/영어/과학) >>> ");
      subject = sc.nextLine();

      try (PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, subject);
         ResultSet rs = ps.executeQuery();

         System.out.println("\n학  번\t\t과  목\t\t점  수\t\t교  사\t\t학  기");
         System.out.println("----------------------------------------------------------------------");
         while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.print("\t\t");
            System.out.print(rs.getString(2));
            System.out.print("\t\t");
            System.out.print(rs.getString(3));
            System.out.print("\t\t");
            System.out.print(rs.getString(4));
            System.out.print("\t\t");
            System.out.print(rs.getString(5));
            System.out.println();
            cnt++;
         }

      } catch (SQLException e) {
         System.out.println("데이터 조회에 문제가 생겼습니다. " + e.getMessage());
      }
      System.out.println(cnt+"건의 "+subject+" 과목정보를 조회 성공!");
      selectCount(conn,subject);

   }// selectScore() 메소드
   private static void selectCount(Connection conn,String subject) {
      String sql = "select count(*) \r\n"
               +"from TBL_SCORE \r\n"
               +"where subject = ?";
      // 2. 조건절에 사용하는 컬럼이 키본키와 유니크 일때는 0또는 1개행이 조회되고 -> rs.next() 를 if에 사용
      //			기본키와 유니크 아닐때는 0~n 개 행이 조회됩니다 ->			while 에 사용
      
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, subject);
         
         ResultSet rs = ps.executeQuery();
         int cnt=0;
         if(rs.next()) {   // 칼럼의 첫번째 커서(위치,행)로 이동 다른 조회문과 다르게 if 문 안써도 됩니다 rs.next() 만 단독으로
            cnt=rs.getInt(1);   // 현재 커서(위치,행)의 첫번째 인덱스에 있는 칼럼을 가져옴 = count(*) 값
         }
         // 참고 : 입력한 과목의 건(행) 수를 조회할수 있습니다.
         System.out.println("과목 << "+subject+" >> "+cnt+"건이 조회되었습니다.");
      } catch (SQLException e) {
         System.out.println("데이터 조회에 문제가 생겼습니다."+e.getMessage());

      } 
   }

}