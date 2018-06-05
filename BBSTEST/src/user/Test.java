package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
	public static void main(String[] args) {
		connect();
	}

	public static void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 드라이버 이름 대소문자 주의
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test",  "lee", "1234");
			System.out.println("접속");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
