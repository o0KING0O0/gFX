package kr.co.giloh.util.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MariaDBHelper {


	public static LocalDate readCurrentDate(Statement stmt) throws SQLException {
//		Date currentDate = null;

		stmt.execute("SELECT CURRENT_DATE()");
		ResultSet rs = stmt.getResultSet();
		rs.next();
//		currentDate = rs.getDate("CURRENT_DATE()");
//		rs.getDate("CURRENT_DATE()").toLocalDate();
		

		return rs.getDate("CURRENT_DATE()").toLocalDate();
	}

	public static int readLastIndex(Statement stmt, String tableName, String indexColumnName) {
		int lastIndex = 0;
		try {
			stmt.execute(String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1", indexColumnName, tableName, indexColumnName));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (ResultSet rs = stmt.getResultSet();) {
			if (rs.next()) {
				lastIndex = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastIndex;
	}

	public static String toSQLDatetimeType(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String getSQLDateTimePattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}

	public static String getSQLDatePattern() {
		return "yyyy-MM-dd";
	}


}
