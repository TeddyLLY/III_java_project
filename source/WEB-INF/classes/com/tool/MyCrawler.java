package com.tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;


@WebServlet("/MyCrawler")
public class MyCrawler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		Crawler crawler = new Crawler();
				JSONArray ja = crawler.gymPeople();					
		System.out.println("======爬蟲開始========");	
		System.out.println("jsonArray1：" +  ja);			
		System.out.println("======爬蟲結束========");	
	    out.println(ja);
	}
}
