package com.lesson.model;

import java.util.*;

public class CompositeQuery_for_Lesson {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("CONCAT(LESSON_NAME,LESSON_CONTENT)".equals(columnName))// 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if("lesson_Class".equals(columnName))
			aCondition = columnName + "=" + value;

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append( aCondition);
				else
					whereCondition.append(" and " + aCondition);

			}
		}
		
		return whereCondition.toString();
	}

}
