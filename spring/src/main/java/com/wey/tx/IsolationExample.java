package com.wey.tx;

import org.junit.Test;

import java.sql.*;


/**
 * @author Yale.Wei
 * @date 2018/9/27 10:29
 */
public class IsolationExample {


	static Connection getConn() throws SQLException {
		return   DriverManager.getConnection("jdbc:mysql://192.168.112.130:3306/test", "root", "515253");
	}


	static Object lock = new Object();

	/**
	 * 可串行化 此时相当于单线程
	 */
	@Test
	public void serializable(){
		Thread t1 = new Thread(() -> {
			try {
				synchronized (lock){
					lock.wait();
				}
				Connection conn = getConn();
				insertSalary(2,2000d,conn);
				insertSalary(1,1000d,conn);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				Connection conn = getConn();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				selectSalary(2,conn);
				selectSalary(1,conn);
				synchronized (lock){
					lock.notify();
				}
				Thread.sleep(1000);
				if (selectSalary(2,conn)==0){
				}
				if (selectSalary(1,conn)==0){
					insertSalary(1,1000d,conn);
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 可重复读
	 */
	@Test
	public void readRepeatable(){
		Thread t1 = new Thread(() -> {
			try {
				Connection conn = getConn();
				synchronized (lock){
					lock.wait();
				}
				insertSalary(2,2000d,conn);
				insertSalary(1,1000d,conn);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				Connection conn = getConn();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
				selectSalary(2,conn);
				selectSalary(1,conn);
				synchronized (lock){
					lock.notify();
				}
				Thread.sleep(1000);
				selectSalary(2,conn);
				selectSalary(1,conn);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 可提交读
	 */
	@Test
	public void  readCommitted(){
		Thread t1 = new Thread(() -> {
			try {
				Connection conn = getConn();
				synchronized (lock){
					lock.wait();
				}
//				conn.setAutoCommit(false);不自动提交事务t2是无法读到数据的
				insertSalary(2,2000d,conn);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				Connection conn = getConn();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				selectSalary(2,conn);//第一次没有读到数据
				synchronized (lock){
					lock.notify();
				}
				Thread.sleep(500);
				selectSalary(2,conn);//第二次读到了事务已经提交的数据
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * TRANSACTION_READ_UNCOMMITTED 未提交读 可以读到未提交事务的数据修改
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	@Test
	public void readUncommitted() throws SQLException, InterruptedException {
		Thread t1 = new Thread(() -> {
			try {
				Connection conn = getConn();
				conn.setAutoCommit(false);
				insertSalary(1,1000d,conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(500);
				Connection conn = getConn();
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				selectSalary(1,conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		t1.join();

	}

	public static int selectSalary(int userid,Connection conn) throws SQLException, InterruptedException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM salary WHERE userid=?");
		ps.setInt(1,userid);
		ResultSet rs = ps.executeQuery();

		System.out.println("select salary "+userid+" start...");
		int i =1;
		while (rs.next()){
			System.out.println("salary"+ i++ +": userid="+rs.getInt(1)+", salary="+rs.getDouble(2));
			return i;
		}
		return 0;
	}

	public static int insertSalary(int userid, double salary ,Connection conn) throws SQLException, InterruptedException {
		PreparedStatement ps = conn.prepareStatement("INSERT  INTO salary VALUES (?,?)");
		ps.setInt(1,userid);
		ps.setDouble(2,salary);
		int i = ps.executeUpdate();
		System.out.println("insert salary "+userid+" start...");
		Thread.sleep(3000);
		return i;
	}
}
