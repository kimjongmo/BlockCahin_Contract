package bbs;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.app.chaincode.invocation.InvokeChaincode;
import org.app.chaincode.invocation.QueryChaincode;
import org.app.client.CAClient;
import org.app.client.ChannelClient;
import org.app.client.FabricClient;
import org.app.config.Config;
import org.app.user.UserContext;
import org.app.util.Util;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.ChaincodeResponse.Status;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.EventHub;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BbsDAO {
	private Connection conn = null;
	private ResultSet rs;
	private static final String SAVEFOLDER = "C:\\file"; // 실제 파일 저장 경로
	private static final String ENCTYPE = "utf-8";
	private static int MAXSIZE = 100 * 1024 * 1024; // 100MB

	public BbsDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://210.123.254.113:3306/test", "lee", "1234");
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

	//validation
	 public Vector<Bbs> getBoardList2(String keyField, String keyWord, int start, int end) {
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         String sql = null;
         Vector<Bbs> vlist = new Vector<Bbs>();
         try {
            if (keyWord.equals("null") || keyWord.equals("")) {
               sql = "select bbsID, bbsTitle, userID, fileName, filesize, bbsDate, pos from bbs order by bbsID desc, pos limit ?, ?";
               // 모든 게시물을 가져오기 위한 SQL문
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, start);
               pstmt.setInt(2, end);
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

	
	
	
	
	
	// 체인 등록하는 부분 작성하기
	public void insertBlock(String filepath) throws Exception {
		// 1. filepath에 있는 파일의 해쉬 값을 얻고
		String[] array = filepath.split("\\/");

		// 2. 파일이름@id , 일자, 해쉬값
		String blockName = array[3] + "@" + array[2];
		String hashValue = extractFileHashSHA256(filepath);
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String time = dayTime.format(new Date(System.currentTimeMillis()));

		// 3. 체인코드 호출.

		byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
		String EXPECTED_EVENT_NAME = "event";

		try {
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);

			FabricClient fabClient = new FabricClient(adminUserContext);
			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://210.123.254.152:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();

			TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
			ChaincodeID ccid = ChaincodeID.newBuilder().setName(Config.CHAINCODE_1_NAME).build();
			request.setChaincodeID(ccid);
			request.setFcn("inputFile");

			String[] arguments = { blockName, time, hashValue };
			request.setArgs(arguments);
			request.setProposalWaitTime(1000);

			Map<String, byte[]> tm2 = new HashMap<>();
			tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8)); // Just some extra junk
																								// in transient map
			tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8)); // ditto
			tm2.put("result", ":)".getBytes(UTF_8)); // This should be returned see chaincode why.
			tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA); // This should trigger an event see chaincode why.
			request.setTransientMap(tm2);
			Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
			for (ProposalResponse res: responses) {
				Status status = res.getStatus();
				Logger.getLogger(InvokeChaincode.class.getName()).log(Level.INFO,"Invoked createCar on "+Config.CHAINCODE_1_NAME + ". Status - " + status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 블록체인으로부터 값 가져오기
	public String[] getBlock(String fileName, String userID) {
		byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
		String EXPECTED_EVENT_NAME = "event";
		
		try {
			Util.cleanUp();
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);

			FabricClient fabClient = new FabricClient(adminUserContext);

			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://210.123.254.152:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();
			String args = fileName+"@"+userID;
			String[] args1 = {args};
			Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "Querying for a car - " + args1[0]);

			Collection<ProposalResponse> responses1Query = channelClient.queryByChainCode("fabcar", "searchFile",args1);
			String stringResponse="";
			for (ProposalResponse pres : responses1Query) {
				stringResponse = new String(pres.getChaincodeActionResponsePayload());
				System.out.println(stringResponse);
			}
			return stringResponse.split(",");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 파일의 해쉬값 가져오는 함수
	public static String extractFileHashSHA256(String filename) throws Exception {

		String SHA = "";
		int buff = 16384;
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");

			MessageDigest hashSum = MessageDigest.getInstance("SHA-256");

			byte[] buffer = new byte[buff];
			byte[] partialHash = null;

			long read = 0;

			// calculate the hash of the hole file for the test
			long offset = file.length();
			int unitsize;
			while (read < offset) {
				unitsize = (int) (((offset - read) >= buff) ? buff : (offset - read));
				file.read(buffer, 0, unitsize);

				hashSum.update(buffer, 0, unitsize);

				read += unitsize;
			}

			file.close();
			partialHash = new byte[hashSum.getDigestLength()];
			partialHash = hashSum.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < partialHash.length; i++) {
				sb.append(Integer.toString((partialHash[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return SHA;
	}

	public boolean check(String userID, String fileName) {
		String SQL = "select fileName from bbs where userID=? and fileName=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, fileName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return false;
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public int insert(HttpServletRequest request, String userID) {// return : Int
		MultipartRequest multi = null;
		int filesize = 0;
		String filename = null;
		String SQL = "insert into bbs value (?,?,?,?,?,?,0)";

		try {
			File file = new File(SAVEFOLDER);
			if (!file.exists())
				file.mkdirs();

			File userfile = new File(SAVEFOLDER + "/" + userID);
			if (!userfile.exists())
				userfile.mkdirs();

			// 여기 밑에 부터는 파일 바로 생성된다는 것 같음.
			multi = new MultipartRequest(request, SAVEFOLDER + "/" + userID, MAXSIZE, ENCTYPE,
					new DefaultFileRenamePolicy());

			if (multi.getFilesystemName("filename") != null) {
				filename = multi.getFilesystemName("filename");
				filesize = (int) multi.getFile("filename").length();
			}
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, multi.getParameter("bbsTitle"));
			pstmt.setString(3, userID);
			pstmt.setString(4, filename);
			pstmt.setInt(5, filesize);
			pstmt.setString(6, getDate());

			pstmt.execute();

			return 0;// 파일이 존재하지 않았고, db에 제대로 저장되었을 때
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2;
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
	
	
	//검증하기
	public String validate(HttpServletRequest request, String userID, String filename) {
		
		MultipartRequest multi = null;
		try {
			File file = new File(SAVEFOLDER);
			if (!file.exists())
				file.mkdirs();

			File userfile = new File(SAVEFOLDER + "/temp");
			if (!userfile.exists())
				userfile.mkdirs();
				//    c:/file/temp에 저장.
			multi = new MultipartRequest(request, SAVEFOLDER + "/temp", MAXSIZE, ENCTYPE);
			
			String comparedFile = multi.getFilesystemName("file");
			String[] str = getBlock(filename,userID);
			String[] str1 = str[2].split("\\\"");
			String block = str1[3];
			String comparedBlock = extractFileHashSHA256("c:/file/temp/"+comparedFile);
			
			System.out.println("block:"+block);
			System.out.println("comparedBlock:"+comparedBlock);
			if(block.equals(comparedBlock)) {
				return "같은 파일입니다";
			}else {
				return "동일한 파일이 아닙니다";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
