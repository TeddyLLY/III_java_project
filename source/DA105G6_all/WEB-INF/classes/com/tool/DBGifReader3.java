package com.tool;


import java.io.*;
import java.sql.*;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import com.article.model.*;

public class DBGifReader3 extends HttpServlet {

	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
        req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		
        String value=req.getParameter("article_no");
        ServletOutputStream out=res.getOutputStream();
		try {
			
				ArticleService articleSvc=new ArticleService();
				byte[] pic=articleSvc.getOneArticle(value).getArticle_picture();
				out.write(pic);
				
				
			

			
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in=getServletContext().getResourceAsStream("/NoData/none.jpg");
			byte[]b=new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	

}