
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.coach.model.CoachService;
import com.coach.model.CoachVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/Session_Set")
public class Session_Set extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		 MemberService memberhSvc=new MemberService();
	        MemberVO memberVO=memberhSvc.findOneMember("M001");
	  HttpSession session = req.getSession();
	  session.setAttribute("memberVO",memberVO);
        
        String ID = session.getId();
        out.println("ID="+ID);
	
	}
}
