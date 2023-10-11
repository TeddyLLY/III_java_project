package com.coach.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class CoachLicenseReader extends HttpServlet {
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); 
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

			
		try {
			String coach_no = req.getParameter("coach_no").trim();
			Statement stmt = con.createStatement();
			String SQL ="SELECT COACH_LICENSE  FROM COACH WHERE COACH_NO = '" + coach_no + "' ";
				ResultSet rs = stmt.executeQuery( SQL ); 
				
/////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("COACH_LICENSE"));
					byte[] buf = new byte[8 * 10240]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					//res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in = getServletContext().getResourceAsStream("/tool/images/nottomcat.gif");
					byte[] b =  new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();stmt.close();
			
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		} catch (Exception e) {
			//System.out.println(e);
			
			InputStream in = getServletContext().getResourceAsStream("/tool/images/nottomcat.gif");
			byte[] b =  new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
			
		}
		
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}