package com.report_article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReportArticleJNDIDAO implements ReportArticleDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	private static final String INSERT_STMT = "INSERT INTO report_article (report_article_no,report_article_content,report_article_time,report_article_status,article_no,member_no,report_article_reason) VALUES (('RA'||LPAD(TO_CHAR( REPORT_ARTICLE_NO_SEQ.NEXTVAL),3,'0')), ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT report_article_no,report_article_content,report_article_time,report_article_status,article_no,member_no,report_article_reason FROM report_article order by report_article_no";
	private static final String GET_ONE_STMT = "SELECT report_article_no,report_article_content,report_article_time,report_article_status,article_no,member_no report_article_reason FROM report_article where report_article_no = ?";
	private static final String DELETE = "DELETE FROM report_article where report_article_no = ?";
	private static final String UPDATE = "UPDATE report_article set report_article_content=?, report_article_time=?, report_article_status=?,article_no=?,member_no=? ,report_article_reason=? where report_article_no = ?";

	@Override
	public void insert(ReportArticleVO reportArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reportArticleVO.getReport_article_content());
			pstmt.setTimestamp(2, reportArticleVO.getReport_article_time());
			pstmt.setInt(3, reportArticleVO.getReport_article_status());
			pstmt.setString(4, reportArticleVO.getArticle_no());
			pstmt.setString(5, reportArticleVO.getMember_no());
			pstmt.setString(6, reportArticleVO.getReport_article_reason());
			pstmt.executeUpdate();

			// Handle any SQL errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
						// Clean up JDBC resources
					} finally {
						if (pstmt != null) {
							try {
								pstmt.close();
							} catch (SQLException se) {
								se.printStackTrace(System.err);
							}
						}
						if (con != null) {
							try {
								con.close();
							} catch (Exception e) {
								e.printStackTrace(System.err);
							}
						}
					}

				}
	@Override
	public void update(ReportArticleVO reportArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reportArticleVO.getReport_article_content());
			pstmt.setTimestamp(2, reportArticleVO.getReport_article_time());
			pstmt.setInt(3, reportArticleVO.getReport_article_status());
			pstmt.setString(4, reportArticleVO.getArticle_no());
			pstmt.setString(5, reportArticleVO.getMember_no());
			pstmt.setString(6, reportArticleVO.getReport_article_reason());
			pstmt.setString(7, reportArticleVO.getReport_article_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
						// Clean up JDBC resources
					} finally {
						if (pstmt != null) {
							try {
								pstmt.close();
							} catch (SQLException se) {
								se.printStackTrace(System.err);
							}
						}
						if (con != null) {
							try {
								con.close();
							} catch (Exception e) {
								e.printStackTrace(System.err);
							}
						}
					}

				}

	@Override
	public void delete(String report_article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, report_article_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ReportArticleVO findByPrimaryKey(String report_article_no) {

		ReportArticleVO reportArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, report_article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reportArticleVO = new ReportArticleVO();
				reportArticleVO.setReport_article_no(rs.getString("report_article_no"));
				reportArticleVO.setReport_article_content(rs.getString("report_article_content"));
				reportArticleVO.setReport_article_time(rs.getTimestamp("report_article_time"));
				reportArticleVO.setReport_article_status(rs.getInt("report_article_status"));
				reportArticleVO.setArticle_no(rs.getString("article_no"));
				reportArticleVO.setMember_no(rs.getString("member_no"));
				reportArticleVO.setReport_article_reason(rs.getString("report_article_reason"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return reportArticleVO;
	}

	@Override
	public List<ReportArticleVO> getAll() {
		List<ReportArticleVO> list = new ArrayList<ReportArticleVO>();
		ReportArticleVO reportArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				reportArticleVO = new ReportArticleVO();
				reportArticleVO = new ReportArticleVO();
				reportArticleVO.setReport_article_no(rs.getString("report_article_no"));
				reportArticleVO.setReport_article_content(rs.getString("report_article_content"));
				reportArticleVO.setReport_article_time(rs.getTimestamp("report_article_time"));
				reportArticleVO.setReport_article_status(rs.getInt("report_article_status"));
				reportArticleVO.setArticle_no(rs.getString("article_no"));
				reportArticleVO.setMember_no(rs.getString("member_no"));
				reportArticleVO.setReport_article_reason(rs.getString("report_article_reason"));
				

				list.add(reportArticleVO); // Store the row in the list
			}

			// Handle any driver errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
						// Clean up JDBC resources
					} finally {
						if (rs != null) {
							try {
								rs.close();
							} catch (SQLException se) {
								se.printStackTrace(System.err);
							}
						}
						if (pstmt != null) {
							try {
								pstmt.close();
							} catch (SQLException se) {
								se.printStackTrace(System.err);
							}
						}
						if (con != null) {
							try {
								con.close();
							} catch (Exception e) {
								e.printStackTrace(System.err);
							}
						}
					}
					return list;
				}
			}