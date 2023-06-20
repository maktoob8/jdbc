package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentInsertMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("::::::::::::::::: 학생 등록 메뉴 입니다. ::::::::::::::::::");
        System.out.println("학생번호 입력시 0000 입력은 종료입니다");

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "iclass";
        String password = "0419";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("연결 상태 = " + conn);
            if (conn != null)
                System.out.println("오라클 데이터베이스 연결 성공!!");

            String sql = "INSERT INTO tbl_student VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                while (true) {
                    System.out.print("학생 번호 입력: ");
                    String studentNo = sc.nextLine();
                    if (studentNo.equals("0000")) {
                        System.out.println("학생 등록 종료");
                        
                        break;
                    }
                    System.out.print("학생 이름 입력: ");
                    String name = sc.nextLine();
                    System.out.print("학생 나이 입력: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("학생 주소 입력: ");
                    String adress = sc.nextLine();

                    pstmt.setString(1, studentNo);
                    pstmt.setString(2, name);
                    pstmt.setInt(3, age);
                    pstmt.setString(4, adress);

                    pstmt.executeUpdate();
                    System.out.println("학생 정보가 등록되었습니다.");
                }
            }
        } catch (Exception e) {
            System.out.println("오류 메시지 = " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}