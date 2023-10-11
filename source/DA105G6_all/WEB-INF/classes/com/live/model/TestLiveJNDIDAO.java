package com.live.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestLiveJNDIDAO
 */
@WebServlet("/TestLiveJNDIDAO")
public class TestLiveJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestLiveJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		LiveJNDIDAO dao = new LiveJNDIDAO();
		
//		// 新增
//		LiveVO liveVO = new LiveVO();
//		liveVO.setLive_notice(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_title("123");
//		liveVO.setlive_imformation("123");
//		liveVO.setCoach_no("C003");
//		
//		dao.insert(liveVO);
//
//		// 修改
//		LiveVO liveVO = new LiveVO();
//		liveVO.setLive_no("L002");
//		liveVO.setLive_notice(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_title("234");
//		liveVO.setlive_imformation("234");
//		liveVO.setCoach_no("C001");
//		
//		dao.update(liveVO);
//
//		// 刪除
//		dao.delete("L002");
//
//		// 查詢
//		LiveVO liveVO = dao.findByPrimaryKey("L001");
//		out.print(liveVO.getLive_no() + ",");
//		out.print(liveVO.getLive_notice() + ",");
//		out.print(liveVO.getLive_time() + ",");
//		out.print(liveVO.getLive_title() + ",");
//		out.print(liveVO.getlive_imformation() + ",");
//		out.print(liveVO.getCoach_no() + ",");
//		
//		System.out.println("---------------------");
//
////		 查詢
		List<LiveVO> list = dao.getAll();
		for (LiveVO aliveVO : list) {
			out.print(aliveVO.getLive_no() + ",");
			out.print(aliveVO.getLive_notice() + ",");
			out.print(aliveVO.getLive_time() + ",");
			out.print(aliveVO.getLive_title() + ",");
			out.print(aliveVO.getLive_imformation() + ",");
			out.print(aliveVO.getCoach_no() + ",");
			out.println();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
