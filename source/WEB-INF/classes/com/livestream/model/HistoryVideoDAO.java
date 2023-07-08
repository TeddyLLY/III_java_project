package com.livestream.model;
import org.hibernate.*;
import org.hibernate.query.Query;



import hibernate.util.HibernateUtil;
import java.util.*;


public class HistoryVideoDAO implements HistoryVideoDAO_interface {
	
	private static final String GET_ALL_STMT = "from HistoryVideoVO order by history_video_no";

	@Override
	public void insert(HistoryVideoVO historyVideoVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(historyVideoVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(HistoryVideoVO historyVideoVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(historyVideoVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String lsId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			HistoryVideoVO historyVideoVO = (HistoryVideoVO) session.get(HistoryVideoVO.class, lsId);
			session.delete(historyVideoVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public HistoryVideoVO findByPrimaryKey(String history_video_no) {
		HistoryVideoVO historyVideoVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			historyVideoVO=(HistoryVideoVO)session.get(HistoryVideoVO.class, history_video_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return historyVideoVO;
	}

	@Override
	public List<HistoryVideoVO> getAll() {
		List<HistoryVideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<HistoryVideoVO> query = session.createQuery(GET_ALL_STMT, HistoryVideoVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	public static void main(String[] args) {

		HistoryVideoDAO dao = new HistoryVideoDAO();

		// 新增
//		HistoryVideoVO historyVideoVO1 = new HistoryVideoVO();
//		historyVideoVO1.setLsDate(java.sql.Timestamp.valueOf("2019-10-05 16:00:00"));
//		historyVideoVO1.setLsViewNum(100);
//		historyVideoVO1.setLsContent(null);
//		dao.insert(historyVideoVO1);

		// 修改
//		HistoryVideoVO historyVideoVO2 = new HistoryVideoVO();
//		historyVideoVO2.setLsId("LV00001");
//		historyVideoVO2.setLsDate(java.sql.Timestamp.valueOf("2019-10-06 16:00:00"));
//		historyVideoVO2.setLsViewNum(100);
//		historyVideoVO2.setLsContent(null);
//		dao.update(historyVideoVO2);

		// 刪除
//		dao.delete("LV00003");

		// 查詢
//		HistoryVideoVO historyVideoVO3 = dao.findByPrimaryKey("LV00001");
//		System.out.print(historyVideoVO3.getLsId() + ",");
//		System.out.print(historyVideoVO3.getLsDate() + ",");
//		System.out.print(historyVideoVO3.getLsViewNum() + ",");
//		System.out.println("---------------------");

//		// 查詢
		
		

	}


	




}
