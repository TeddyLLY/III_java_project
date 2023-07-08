

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBImgReader extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
		
	}
		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		

		try {
			Statement stmt = con.createStatement();
			String lessonNo = req.getParameter("value").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT lesson_picture FROM LESSON WHERE LESSON_NO='"+lessonNo+"'");
			if (rs.next()) {
				
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("lesson_picture"));
				byte[] buf = new byte[8 * 1024]; // 8K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/images/none.jpg");
				byte[]b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		
		}
	}

	public void init() throws ServletException{
	try {
		Context ctx = new javax.naming.InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/DA105G6");
		con = ds.getConnection();
	} catch (NamingException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}


}