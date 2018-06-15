package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection conn = null;

	public UserDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://210.123.254.113:3306/test", "lee", "1234");
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("생성자 에러");
		}
	}

	public int join(User user) throws Exception {

		PreparedStatement pstmt = null;
		String SQL = "insert into user values(?,?,?,?,?,?)";
		try {
			if(checkKakao(user.getUserID()))//카카오 테이블 아이디 체크
			{
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, user.getUserID());
				pstmt.setString(2, user.getUserPassword());
				pstmt.setString(3, user.getUserName());
				pstmt.setString(4, user.getUserAddress());
				pstmt.setString(5, user.getUserPhone());
				pstmt.setString(6, user.getUserEmail());
				pstmt.execute();
				return 1;//두 개다 존재하지 않을 때  1: 성공
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return -1;//존재하는 아이디
	}

	public void insertMember(KakaoMember member) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into kakaotable values (?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getKaccount_email());
			pstmt.setString(3, member.getNickname());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
	}

	public int login(String userID, String userPassword) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where userID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("userPassword").equals(userPassword)) {
					return 1;// 로그인 성공
				} else
					return 0;// 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("login() error");
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return -2;
	}

	public boolean checkUser(String userID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from user where userID = ?";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	// 카카오 중복 체크부분
	public boolean checkKakao(String userID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from kakaotable where id = ?";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				return false;

			} else {
				return checkUser(userID);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
