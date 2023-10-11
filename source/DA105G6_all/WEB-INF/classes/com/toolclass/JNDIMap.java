package com.toolclass;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JNDIMap {

	public static String getMemberJNDIDAO(String col, String value) {
		String aCondition = null;
		if ("member_points".equals(col) || "member_height".equals(col) || "member_weight".equals(col)
|| "member_review".equals(col) || "member_auth".equals(col) || "member_bodyfat".equals(col)  ) { // other
			aCondition = col + "=" + value;
		} else if ("member_no".equals(col) || "member_name".equals(col) || "member_sex".equals(col)
|| "member_password".equals(col) || "member_card".equals(col) || "member_cellphone".equals(col)
|| "member_account".equals(col) || "member_address".equals(col)) { // char
			aCondition = col + " like '%" + value + "%'";
		}
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = getMemberJNDIDAO(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		return whereCondition.toString();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getCoachJNDIDAO(String  col, String value) {
		String aCondition = null;
		if("coach_total_evaluation".equals(col) || "coach_total_people_evaluation".equals(col) || "coach_review".equals(col) ||
"coach_auth".equals(col) || "coach_average_evaluation".equals(col) 
|| "coach_income".equals(col) ) {
			aCondition = col + "=" + value;
		}else if("coach_no".equals(col) || "coach_name".equals(col) || "coach_sex".equals(col) 
|| "coach_cellphone".equals(col) || "coach_account".equals(col) || "coach_password".equals(col) 
|| "coach_address".equals(col) || "coach_introduction".equals(col) || "coach_bank_account".equals(col)) { //char
			aCondition = col + " like '%" + value +"%'";
		}
		return aCondition + " ";
	}
	
	public static String get_CoachCondition(Map<String , String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0 ;
		for(String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = getCoachJNDIDAO(key , value.trim());
				if(count==1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
				System.out.println("有送出查詢資料的欄位數 count = " + count);
			}
		}
		return whereCondition.toString();
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void main(String argv[]) {
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("member_no", new String[] { "M001"});
		map.put("member_name",new String[] {"Teddy"});
		String finalSQL = "select * from member "
		          + JNDIMap.get_WhereCondition(map)
		          + "order by member_no";
		System.out.println(finalSQL);
		
		Map<String, String[]> map1 = new TreeMap<String, String[]>();
		map1.put("coach_no", new String[] { "C001"});
		map1.put("coach_name",new String[] {"YEE"});
		String finalSQL1 = "select * from coach "
		          + JNDIMap.get_CoachCondition(map1)
		          + "order by coach_no";
		System.out.println(finalSQL1);
	}
}
