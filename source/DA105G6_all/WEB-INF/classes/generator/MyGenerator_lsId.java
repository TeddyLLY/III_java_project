package generator;

import java.io.*;
import java.sql.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator_lsId implements IdentifierGenerator {

	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {
		int num =3;
		String prefix = "HV";
		String prefix_subfix = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT HISTORY_VIDEO_NO_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			String nextval = rs.getString("nextval");
			String subfix_nextval = getSubfix_nextval(num, nextval);
			prefix_subfix = prefix+subfix_nextval;    //LV00001 (總長度為7，後面數字長度為5)
//			con.close();  //於Hibernate 5 不可關閉連線
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		return prefix_subfix;
	}
	
	protected String getSubfix_nextval(int num, String nextval) {
		String zero = "0";
		for (int i = 1; i < num; i++)
			zero += "0";
		nextval = zero + nextval;
		nextval = nextval.substring(nextval.length() - num);
		return nextval;
	}
}