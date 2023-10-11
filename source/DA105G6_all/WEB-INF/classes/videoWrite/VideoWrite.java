package videoWrite;

import java.sql.*;
import java.io.*;

class VideoWrite {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA105G6";
		String passwd = "123456";
		String photos = "WebContent/resources/DB_video1"; //測試用圖片已置於【專案錄徑】底下的【resources/DB_video1】目錄內
		String update = "UPDATE History_video set history_video_content=? where history_video_no = ?";

		int count = 1;
		String isId = "HV0000";
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);
			File[] photoFiles = new File(photos).listFiles();
			for (File f : photoFiles) {
				fin = new FileInputStream(f);
				pstmt = con.prepareStatement(update);
				pstmt.setString(2, isId+count);
				pstmt.setBinaryStream(1, fin);
				pstmt.executeUpdate();
				count++;
				System.out.print(" update the database...");
				System.out.println(f.toString());
			}

			fin.close();
			pstmt.close();
			System.out.println("加入Video-更新成功.........");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
