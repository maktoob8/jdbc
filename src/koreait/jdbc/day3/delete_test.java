package koreait.jdbc.day3;

import java.sql.SQLException;

public class delete_test {
	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		try {
			int count = dao.delete("202356");
			System.out.println("학생 등록" + count + "건 정보 삭제 성공!!");
		} catch (SQLException e) {
			System.out.println("예외 - "  + e.getMessage());
		}
	}
}
