package org.just.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//����ר������������ݿ�,������Ϊ�̶��Ĺ�����ʹ��(����������)
public class DBManager {
	// ����һ����̬�����Ӷ�������������ݿ�
	// private static Connection conn = null;
	// ��һ����̬��������,����ִ��sql���
	// private static Statement stmt = null;
	// ����һ����̬�Ľ������������ִ��sql�����ѯ�õ��Ľ��
	// private static ResultSet rs = null;

	/**
	 * ������ݿ�ķ���
	 * 
	 * @return conn ����һ�����Ӷ���
	 */
	public static Connection mssql(String url, String user, String pass) {
		Connection conn = null;
		try {
			// 1������������
			// "jdbc:odbc:bookdemo"
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// 2�����ӵ���ݿ⣨������Ӷ���
			// ͨ�����ӹ�����(DriverManager)���һ��������������Ӷ�������Ĳ����ʾ�������ӵ����Դbookdemo
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// �Զ�ջ�ķ�ʽ��������Ϣ��ӡ����
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn; // �����Ӷ��󷵻�
	}

	/**
	 * ������ݿ�ķ���
	 * 
	 * @return conn ����һ�����Ӷ���
	 * @throws ClassNotFoundException
	 * @throws java.sql.SQLException
	 */
	public static Connection mysql(String url, String user, String pass)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;

		// 1������������
		// "jdbc:odbc:bookdemo"
		Class.forName("com.mysql.jdbc.Driver");
		// 2�����ӵ���ݿ⣨������Ӷ���
		// ͨ�����ӹ�����(DriverManager)���һ��������������Ӷ�������Ĳ����ʾ�������ӵ����Դbookdemo
		conn = DriverManager.getConnection(url, user, pass);

		return conn; // �����Ӷ��󷵻�
	}

	/**
	 * �������mysql��ݿ�����
	 * @throws java.sql.SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection mysql(String host, String database, String user,
			String pass) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://" + host + "/" + database
				+ "?useUnicode=true&amp;characterEncoding=UTF-8";
		return mysql(url, user, pass);
	}

	/**
	 * ����������ִ���û������sql���(������select���)
	 * 
	 * @param sql
	 *            �����sql��䣬�ȴ�ִ��
	 * @return ����ִ��sql����Ľ�����
	 */
	public static ResultSet query(Connection conn, String sql) {
		ResultSet rs = null;
		try {
			// 3��ͨ�����Ӷ��󴴽�һ��������stmt������ִ��sql���
			Statement stmt = conn.createStatement();
			// 4��ִ��sql��䣬�õ�һ��rs��������
			rs = stmt.executeQuery(sql);
		} catch (Exception e) { // �����?��ʱ�������
			e.printStackTrace();
		}
		return rs; // ����ѯ�õ��Ľ����󷵻�
	}

	/**
	 * ����������ִ�и�����䣬������Ӱ���˶����У�insert,update,delete��
	 * 
	 * @param sql
	 *            �����sql��䣬�ȴ�ִ��
	 * @return ����ִ��sql����Ľ�����
	 */
	public static int update(Connection conn, String sql) {
		// ִ��sql���ǰ�����ӵ���ݿ�
		Statement stmt = null;
		int i = 0;
		try {
			// ͨ�����Ӷ��󴴽�һ��������stmt������ִ��sql���
			stmt = conn.createStatement();
			// ִ�и�����䣬������Ӱ���˶�����
			i = stmt.executeUpdate(sql);
		} catch (Exception e) { // �����?��ʱ�������
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}