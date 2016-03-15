package org.just.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * ����������mysql�ı�ֱ������Bean
 * 
 * @author childlikeman@gmail.com
 */
public class MySQLToBean extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox checkBox;
	Properties p = new Properties();
	String configFile = "config.ini";
	private JLabel lblNewLabel_4;

	public MySQLToBean() {

		setResizable(false);

		setTitle("MySQL ----> javabean");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 484, 324);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtLocalhost = new JTextField();
		txtLocalhost.setText("192.168.60.76");
		txtLocalhost.setBounds(146, 10, 147, 21);
		panel.add(txtLocalhost);
		txtLocalhost.setColumns(10);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(80, 13, 30, 15);
		panel.add(lblIp);

		JLabel label = new JLabel("database");
		label.setBounds(80, 42, 54, 15);
		panel.add(label);

		textField = new JTextField();
		textField.setBounds(146, 39, 147, 21);
                textField.setText("workorder");
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("table:");
		label_1.setBounds(80, 127, 54, 15);
		panel.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(146, 124, 147, 21);
                 textField_1.setText("erp_order");
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_2 = new JLabel("package:");
		label_2.setBounds(79, 156, 54, 15);
		panel.add(label_2);

		txtComyourcom = new JTextField();
		txtComyourcom.setText("com.honghailt.workorder.order.bo");
		txtComyourcom.setBounds(146, 155, 147, 21);
		panel.add(txtComyourcom);
		txtComyourcom.setColumns(10);

		JLabel lblNewLabel = new JLabel("outpath:");
		lblNewLabel.setBounds(80, 190, 65, 15);
		panel.add(lblNewLabel);

		textField_3 = new JTextField();
		textField_3.setBounds(146, 186, 147, 21);
                textField_3.setText("d:\\xx");
		panel.add(textField_3);
		textField_3.setColumns(10);

		checkBox = new JCheckBox("auto create");
		checkBox.setSelected(true);
		checkBox.setBounds(145, 213, 147, 23);
		panel.add(checkBox);

//		JLabel lblNewLabel_1 = new JLabel("����ָ��������Ҳ���Բ�ָ��");
//		lblNewLabel_1.setBounds(303, 127, 176, 15);
//		panel.add(lblNewLabel_1);
//
//		JLabel lblNewLabel_2 = new JLabel("* ���ݿ���");
//		lblNewLabel_2.setForeground(Color.RED);
//		lblNewLabel_2.setBounds(303, 42, 66, 15);
//		panel.add(lblNewLabel_2);
//
//		JLabel lblNewLabel_3 = new JLabel("* ���ṹ");
//		lblNewLabel_3.setForeground(Color.RED);
//		lblNewLabel_3.setBounds(303, 158, 79, 15);
//		panel.add(lblNewLabel_3);

		JButton button = new JButton("go");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				go();
			}
		});
		button.setBounds(145, 242, 93, 23);
		panel.add(button);

		textField_4 = new JTextField();
		textField_4.setText("honghailt");
		textField_4.setBounds(145, 93, 147, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);

		txtRoot = new JTextField();
		txtRoot.setText("root");
		txtRoot.setBounds(145, 66, 148, 21);
		panel.add(txtRoot);
		txtRoot.setColumns(10);

		JLabel label_3 = new JLabel("name");
		label_3.setBounds(80, 69, 54, 15);
		panel.add(label_3);

		JLabel label_4 = new JLabel("password");
		label_4.setBounds(80, 96, 54, 15);
		panel.add(label_4);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(248, 242, 204, 23);
		panel.add(lblNewLabel_4);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				export();
				System.exit(0);
			}

		});
                setUIVal();
		inport();
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private JTextField txtLocalhost;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtComyourcom;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtRoot;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySQLToBean frame = new MySQLToBean();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void inport() {
		File config = new File(configFile);
		if (config.exists()) {
			try {
				InputStream is = new FileInputStream(config);
				p.load(is);
				is.close();
				setUIVal();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void setUIVal() {
		txtLocalhost.setText(p.getProperty("host", "192.168.60.76"));
		textField.setText(p.getProperty("database", "workorder"));
		txtRoot.setText(p.getProperty("user", "root"));
		textField_4.setText(p.getProperty("pass", "honghailt"));
		txtComyourcom.setText(p.getProperty("packname", "com.honghailt.workorder.order.bo"));
		textField_3.setText(p.getProperty("dirstr", "d:\\xx"));
		textField_1.setText(p.getProperty("tablename", "erp_order"));
	}

	private void export() {
            setUIVal();
		String host = txtLocalhost.getText();
		String database = textField.getText();
		String user = txtRoot.getText();
		String pass = textField_4.getText();
		String packname = txtComyourcom.getText();
		String dirstr = textField_3.getText();// �ձ�ʾ��ǰĿ¼
		String tablename = textField_1.getText();

		p.setProperty("host", host);
		p.setProperty("database", database);
		p.setProperty("user", user);
		p.setProperty("pass", pass);
		p.setProperty("packname", packname);
		p.setProperty("dirstr", dirstr);
		p.setProperty("tablename", tablename);

		try {
			OutputStream out = new FileOutputStream(configFile);
			p.store(out, "�˳������ļ�," + sdf.format(new Date()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTips(String msg) {
		lblNewLabel_4.setText(msg);
	}

	public void go() {
		String host = txtLocalhost.getText();
		String database = textField.getText();

		if (database.length() == 0) {
			setTips("���ݿ�������");
			return;
		}

		String user = txtRoot.getText();
		String pass = textField_4.getText();
		String packname = txtComyourcom.getText();
		String dirstr = textField_3.getText();// �ձ�ʾ��ǰĿ¼
		String tablename = textField_1.getText();
		boolean createPackage = checkBox.getSelectedObjects() != null;
		System.out.println(createPackage);
		if (dirstr != null && !dirstr.isEmpty()) {
			if (!dirstr.endsWith("/")) {
				dirstr += "/";
			}
		}
		File dir = new File(dirstr);
		if (createPackage) {
			dir = new File(dirstr + packname.replaceAll("\\.", "/"));
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		String outputdir = dir.getAbsolutePath();// bean������Ŀ¼

		Connection conn = null;
		try {

			conn = DBManager.mysql(host, database, user, pass);
			if (tablename.length() > 0) {
				parseTableByShowCreate(conn, tablename, packname, outputdir);
			} else {
				parseAllTable(conn, packname, outputdir);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setTips("�Ҳ���MySQL��jar��");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ��ʼ�����������б� ����������������ʾ�����ݿ������б�����bean; ����ָ����������bean;
	 */
	public void parseAllTable(Connection conn, String packname, String outputdir) {

		String sql = "show tables";
		ResultSet rs = DBManager.query(conn, sql);
		try {
			while (rs.next()) {
				String tablename = rs.getString(1);
				parseTableByShowCreate(conn, tablename, packname, outputdir);
			}
			DBManager.close(conn, null, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ͨ�� mysql�� show create table TABLE_NAME��������Bean;
	 * 
	 * @param conn
	 * @param outputdir
	 * @param packname
	 */
	private void parseTableByShowCreate(Connection conn, String tablename,
			String packname, String outputdir) {
		StringBuilder classInfo = new StringBuilder("\t/**\r\n\t*");
		boolean shouldCloseConn = false;

		String sql = "show create table " + tablename;
		ResultSet rs = null;
		try {
			rs = DBManager.query(conn, sql);
			StringBuilder fields = new StringBuilder();
			StringBuilder methods = new StringBuilder();

			while (rs.next()) {
				String sqlstr = rs.getString(2);
				String lines[] = sqlstr.split("\r\n");
				for (int i = 0; i < lines.length; i++) {
					String line = lines[i];
					// System.out.println(line);
					// System.out.println("------------");
					String regex = "\\s*`([^`]*)`\\s*(\\w+[^ ]*)\\s*(NOT\\s+NULL\\s*)?(DEFAULT\\s*([^ ]*|NULL|'0'|''|CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)\\s*)?(COMMENT\\s*'([^']*)')?\\s*,\\s*";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(line);
					while (m.find()) {
						String field = m.group(1);
						String type = typeTrans(m.group(2));
						String cmt = m.group(7);
						fields.append(getFieldStr(field, type, cmt));
						methods.append(getMethodStr(field, type));
						// System.out.println(field);
						// System.out.println(type);
						// System.out.println(cmt);
					}
					if (i == lines.length - 1) {
						classInfo.append("������" + getClass().getSimpleName()
								+ "�����Զ�����\r\n");
						classInfo.append("\t*��ע(���ݱ��comment�ֶ�)��");
						int index = line.indexOf("COMMENT=");
						if (index != -1) {
							String tmp = line.substring(index + 8);
							classInfo.append(tmp.replace("'", ""));
						} else {
							classInfo.append("�ޱ�ע��Ϣ");
						}
						classInfo.append("\r\n");
						classInfo
								.append("\t*@author childlikeman@gmail.com,http://t.qq.com/lostpig\r\n");
						classInfo.append("\t*@since ");
						classInfo.append(sdf.format(new Date()));
						classInfo.append("\r\n\t*/\r\n\r\n");
					}

				}

			}
			classInfo.append("\tpublic class ")
					.append(upperFirestChar(tablename)).append("{\r\n");
			classInfo.append(fields);
			classInfo.append(methods);
			classInfo.append("\r\n");
			classInfo.append("}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			DBManager.close(shouldCloseConn ? conn : null, null, rs);
		}

		String packageinfo = "package " + packname + ";\r\n\r\n";
		File file = new File(outputdir, upperFirestChar(tablename) + ".java");
		System.out.println(file.getAbsolutePath());
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(packageinfo);
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	private String getMethodStr(String field, String type) {
		StringBuilder get = new StringBuilder("\tpublic ");
		get.append(type).append(" ");
		if (type.equals("boolean")) {
			get.append(field);
		} else {
			get.append("get");
			get.append(upperFirestChar(field));
		}
		get.append("(){").append("\r\n\t\treturn this.").append(field)
				.append(";\r\n\t}\r\n");
		StringBuilder set = new StringBuilder("\tpublic void ");

		if (type.equals("boolean")) {
			set.append(field);
		} else {
			set.append("set");
			set.append(upperFirestChar(field));
		}
		set.append("(").append(type).append(" ").append(field)
				.append("){\r\n\t\tthis.").append(field).append("=")
				.append(field).append(";\r\n\t}\r\n");
		get.append(set);
		return get.toString();
	}

	public String upperFirestChar(String src) {
		return src.substring(0, 1).toUpperCase().concat(src.substring(1));
	}

	private String getFieldStr(String field, String type, String cmt) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t").append("private ").append(type).append(" ")
				.append(field).append(";");
		if (cmt != null) {
			sb.append("//").append(cmt);
		}
		sb.append("\r\n");
		return sb.toString();
	}

	/**
	 * mysql������ת����java ���Ͳο�����
	 * http://hi.baidu.com/wwtvanessa/blog/item/9fe555945a07bd16d31b70cd.html
	 */
	public String typeTrans(String type) {
		if (type.contains("tinyint")) {
			return "boolean";
		} else if (type.contains("int")) {
			return "int";
		} else if (type.contains("varchar") || type.contains("date")
				|| type.contains("time") || type.contains("datetime")
				|| type.contains("timestamp") || type.contains("text")
				|| type.contains("enum") || type.contains("set")) {
			return "String";
		} else if (type.contains("binary") || type.contains("blob")) {
			return "byte[]";
		} else {
			return "String";
		}
	}
}