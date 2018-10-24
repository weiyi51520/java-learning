package com.wey.tx;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Yale.Wei
 * @date 2018/10/8 16:15
 */
public class SpringTransaction {
	private static String url = "jdbc:mysql://192.168.112.130:3306/test";
	private static String username = "root";
	private static String password = "515253";

	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	@Test
	public void templateTransaction() {
		final DataSource ds = new DriverManagerDataSource(url, username, password);
		final TransactionTemplate template = new TransactionTemplate();
		template.setTransactionManager(new DataSourceTransactionManager(ds));

		template.execute(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Connection conn = DataSourceUtils.getConnection(ds);
				Object savePoint = null;
				try {
					{
						// 插入
						PreparedStatement prepare = conn.
								prepareStatement("insert INTO salary (userid,salary) VALUES (?,?)");
						prepare.setInt(1, 1);
						prepare.setDouble(2, 1000d);
						prepare.executeUpdate();
					}

					// 设置保存点
					savePoint = status.createSavepoint();

					{
						// 插入
						PreparedStatement prepare = conn.
								prepareStatement("insert INTO salary (userid,salary) VALUES (?,?)");
						prepare.setInt(1, 2);
						prepare.setDouble(2, 2000d);
						prepare.executeUpdate();
					}

					{
						// 更新
						PreparedStatement prepare = conn.
								prepareStatement("UPDATE salary SET salary= salary+100d where userid=?");
						prepare.setInt(1, 1);

						int i=1/0;

					}
				} catch (Exception e) {
					System.out.println("更新失败");
					if (savePoint != null) {
						status.rollbackToSavepoint(savePoint);
					} else {
						status.setRollbackOnly();
					}
				}
				return null;
			}
		});
	}

	/**
	 * Propagation.SUPPORTED 当前没有事务就对该方法新建事务,当前有事务就加入当前事务
	 * Propagation.NOT_SUPPORTED 非事务执行该方法,当前已有事务挂起
	 * Propagation.REQUIRES_NEW 对该方法新建事务,当前已有事务则挂起
	 */
	@Test
	public void springPropagation(){
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("spring-tx.xml");
		UserService userService = context.getBean(UserService.class);
		userService.createUser(1,"Yale.Wei");
	}

	@Test
	public void proxyTest(){
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("spring-tx.xml");
		UserService userService = context.getBean(UserService.class);
		UserService proxyInstance = (UserService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[]{UserService.class}, (proxy, method, args) -> {
					System.out.println("开启事务:" + method.getName());
					try {
						return method.invoke(userService, args);
					} finally {
						System.out.println("关闭事务:"+method.getName());
					}
				}
		);

		proxyInstance.createUser(1,"Yale.Wei");
	}
}
