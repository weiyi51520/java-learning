import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author Yale.Wei
 * @date 2018/9/18 16:54
 */
public class Main {
	static final Logger logger = LogManager.getLogger(Main.class.getName());

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://192.168.112.130:3306/test";
	private static String username = "root";
	private static String password = "515253";

	/**
	 * DriverManager 驱动管理，调用驱动从数据库获取连接
	 * Connection 代表了一个数据库连接
	 * Statement 持有Sql语句，执行并返回执行后的结果
	 * ResultSet Sql执行后返回的数据载体
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection(url,username,password);

		Statement statement = connection.createStatement();
		logger.info("=== Statement ===");
		statement.execute("SELECT * FROM user limit 3");
		ResultSet rs = statement.getResultSet();
		printResult(rs);
		rs.close();
		statement.close();
		logger.info("=== PreparedStatement ===");
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ? limit 3");
		preparedStatement.setString(1,"1");
		preparedStatement.execute();
		rs = preparedStatement.getResultSet();
		printResult(rs);
		rs.close();
		preparedStatement.close();
		connection.close();

	}

	private static void printResult(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int cols = meta.getColumnCount();
		StringBuffer b = new StringBuffer();
		while (rs.next()){
			for (int i = 1; i <= cols; i++) {
				b.append(meta.getColumnName(i)+"=");
				b.append(rs.getString(i)+"\t");
			}
			b.append("\n");
			logger.info(b);
		}
	}
}
