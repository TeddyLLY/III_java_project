package com.article.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.JSONFileUpload;
import com.entities.UploadHelper;
import com.google.gson.Gson;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
@MultipartConfig
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action=req.getParameter("action");
		if(action.equals("update")||action.equals("insert")) {
			doPost_Upload(req,res);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet_Home(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("back-end/addArticle.jsp").forward(req,res);
	}
	protected void doPost_Upload(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	UploadHelper.upload("/front-end/ckeditor/uploads", req);
	String fileName=UploadHelper.upload("front-end/ckeditor/uploads", req);
	Gson gson=new Gson();
	PrintWriter out=res.getWriter();
	
	out.print(gson.toJson(new JSONFileUpload("./front-end/ckeditor/uploads/"+fileName)));
	out.flush();
	out.close();
	}
	protected void doGet_BroeserFile(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   String application=req.getServletContext().getRealPath("");
   String basePath=application+File.separator+"front-end"+File.separator+"ckeditor"+File.separator+"uploads";
   File folder=new File(basePath);
    req.setAttribute("files", folder.listFiles());
    req.getRequestDispatcher("product/browserfile.jsp").forward(req, res);
	}
}
