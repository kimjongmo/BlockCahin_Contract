package bbs;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BbsDAO {
	private Connection conn = null;
	private ResultSet rs;
	private static final String SAVEFOLDER = "C:\\file"; // 실제 파일 저장 경로
	private static final String ENCTYPE = "utf-8";
	private static int MAXSIZE = 10 * 1024 * 1024; // 10MB

	public BbsDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "lee", "1234");
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("생성자 에러");
		}
	}

	public String getDate() {
		String SQL = "select now()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; // 데이터베이스오류
	}

	public int getNext() {
		String SQL = "select bbsID from bbs order by bbsID desc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // 첫번째 게시물일 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스오류
	}

	//체인 등록하는 부분 작성하기
	//org.app.Chaincode.invocation 패키지에 있는 소스
	
	public int insert(HttpServletRequest request) {
		
		
		MultipartRequest multi = null;
		int filesize = 0;
		String filename = null;
		String SQL = "insert into bbs value (?,?,?,?,?,?,0)";
		
		try {
			request.setAttribute(request.getParameter("filename")+"@"+request.getParameter("userID"), "filename");
			File file = new File(SAVEFOLDER);
			if (!file.exists())
				file.mkdirs();
			multi = new MultipartRequest(request, SAVEFOLDER, MAXSIZE, ENCTYPE, new DefaultFileRenamePolicy());
			if (multi.getFilesystemName(multi.getFilesystemName("filename")) != null) {
				filename = multi.getFilesystemName("filename");
				filesize = (int) multi.getFile("filename").length();
			}
			PreparedStatement pstmt = conn.prepareStatement(SQL); 
			pstmt.setInt(1, getNext());
			pstmt.setString(2, multi.getParameter("bbsTitle"));
			pstmt.setString(3, multi.getParameter("userID"));
			pstmt.setString(4, filename);
			pstmt.setInt(5, filesize);
			pstmt.setString(6, getDate());
			
			pstmt.execute();
			
			return 1;//파일이 존재하지 않았고, db에 제대로 저장되었을 때
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getTotalCount(String keyField, String keyWord) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			if (keyWord.equals("null") || keyWord.equals("")) {
				sql = "select count(bbsID) from bbs";
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "select count(bbsID) from bbs where " + keyField + " like ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return totalCount;
	}

	// 게시판 리스트
	public Vector<Bbs> getBoardList(String keyField, String keyWord, int start, int end, String userID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bbs> vlist = new Vector<Bbs>();
		try {
			if (keyWord.equals("null") || keyWord.equals("")) {
				sql = "select bbsID, bbsTitle, userID, fileName, filesize, bbsDate, pos from bbs where userID = ? order by bbsID desc, pos limit ?, ?";
				// 모든 게시물을 가져오기 위한 SQL문
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userID);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				sql = "select * from bbs where " + keyField + " like ? ";
				sql += "order by bbsID desc, pos limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bean = new Bbs();
				bean.setBbsID(rs.getInt("bbsID"));
				bean.setBbsTitle(rs.getString("bbsTitle"));
				bean.setUserID(rs.getString("userID"));
				bean.setFileName(rs.getString("fileName"));
				bean.setFilesize(rs.getInt("filesize"));
				bean.setBbsDate(rs.getString("bbsDate"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return vlist;
	}
	
	public int getBoardList(int bbsID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bean = new Bbs();
				bean.setBbsID(rs.getInt("bbsID"));
				bean.setBbsTitle(rs.getString("bbsTitle"));
				bean.setUserID(rs.getString("userID"));
				bean.setFileName(rs.getString("fileName"));
				bean.setFilesize(rs.getInt("filesize"));
				bean.setBbsDate(rs.getString("bbsDate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return bbsID;
	}
}
