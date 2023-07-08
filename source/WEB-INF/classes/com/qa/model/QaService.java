package com.qa.model;

import java.util.List;

public class QaService {
	
	private QaDAO_interface dao;
	
	public QaService() {
		dao = new QaJNDIDAO();
	}
	
	public QaVO addQa(String q_content, String a_content) {
		QaVO qaVO = new QaVO();
		
		qaVO.setQ_content(q_content);
		qaVO.setA_content(a_content);
		dao.insert(qaVO);
		return qaVO;
	}
	
	public QaVO updateQa(String qa_no, String q_content, String a_content) {
		QaVO qaVO = new QaVO();
		qaVO.setQa_no(qa_no);
		qaVO.setQ_content(q_content);
		qaVO.setA_content(a_content);
		dao.update(qaVO);
		return qaVO;
	}
	
	public void deleteQa(String qa_no) {
		dao.delete(qa_no);
	}
	
	public QaVO getOneQa(String qa_no) {
		return dao.findByPrimaryKey(qa_no);
	}
	
	public List<QaVO> getAll(){
		return dao.getAll();
	}
}
