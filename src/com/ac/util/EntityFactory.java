package com.ac.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 实体生成工具类
 * 
 * @author wy
 * 
 */
public class EntityFactory {
	private String outpath;// 文件生成的路径

	private String sentence;// 生成的格式outClass{Classname(int @@@,....)}

	private String tableName;

	private String packageName;
	public static Map<String, String> colunmNames = new HashMap<String, String>();

	public EntityFactory(String outpath, String sentence, String tableName) {
		super();
		this.outpath = outpath;
		this.sentence = sentence;
		this.tableName = tableName;
	}

	public EntityFactory() {
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getOUTPATH() {
		return outpath;
	}

	public void setOUTPATH(String oUTPATH) {
		outpath = oUTPATH;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public File makeFile(String name, String type) {
		String path = outpath + name + "." + type;
		File outf = new File(path);
		if (outf.exists()) {
			outf.delete();
		} else {
			try {
				outf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return outf;
	}

	/**
	 * 类名
	 * 
	 * @param str
	 * @return
	 */
	public String getClassName(String str) {
		String className = null;
		if (str.contains("outClass")) {
			int start = str.indexOf("{");
			int sp = str.indexOf("(");
			className = str.substring(start + 1, sp);
		}
		return className + "Entity";
	}

	public ArrayList<String[]> splitString(String str) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (str.contains("outClass")) {
			int sp = str.indexOf("(");
			int end = str.lastIndexOf(")");
			String content = str.substring(sp + 1, end);
			String[] info = content.split(",");

			for (String s : info) {
				String[] detail = s.split(" ");
				list.add(detail);
			}

		}
		return list;
	}

	public String initial(String str) {
		String s = str.substring(0, 1);
		return s.toUpperCase() + str.substring(1);
	}

	public void writeFile() {
		/**
		 * bw.write("**ʵ�����Զ����"); д���ļ�
		 */
		BufferedWriter bw = null;
		try {
			File file = makeFile(getClassName(sentence), "java");
			bw = new BufferedWriter(new FileWriter(file));
			ArrayList<String[]> list = splitString(sentence);
			bw.write("package " + packageName + ";");
			bw.newLine();
			bw.write("import java.io.Serializable;");
			bw.newLine();
			bw.write("import javax.persistence.Column;");
			bw.newLine();
			bw.write("import javax.persistence.Entity;");
			bw.newLine();
			bw.write("import javax.persistence.GeneratedValue;");
			bw.newLine();
			bw.write("import javax.persistence.Table;");
			bw.newLine();
			bw.write("import javax.persistence.Id;");
			bw.newLine();
			bw.write("import java.util.Date;");
			bw.newLine();
			bw.write("import org.hibernate.annotations.DynamicUpdate;");
			bw.newLine();
			bw.write("import org.hibernate.annotations.GenericGenerator;");
			bw.newLine();
			bw.write("import org.springframework.format.annotation.DateTimeFormat;");
			bw.newLine();
			bw.newLine();
			bw.newLine();
			bw.write("/**** @time"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date()));
			bw.newLine();
			bw.write("****/");
			bw.newLine();
			bw.newLine();
			bw.write("@SuppressWarnings(\"serial\")");
			bw.newLine();
			bw.write("@Entity");
			bw.newLine();
			bw.write("@Table(name =" + tableName + ")");
			bw.newLine();
			bw.write("@DynamicUpdate");
			bw.newLine();
			bw.write("public  class  " + getClassName(sentence)
					+ "  implements Serializable" + "{");
			bw.newLine();
			bw.newLine();
			bw.newLine();
			bw.newLine();

			bw.write("\t@Id");
			bw.newLine();
			for (int i = 0; i < list.size(); i++) {
				// 生成属性
				String[] detail = list.get(i);
				bw.write("\t@Column(name = " + colunmNames.get(detail[1]) + ")");
				if (detail[1].equals("id")) {
					bw.newLine();
					bw.write("\t@GeneratedValue(generator=\"increment\")");
					bw.newLine();
					bw.write("\t@GenericGenerator(name=\"increment\", strategy = \"increment\")");
				}
				if (detail[0].equals("Date")) {

					bw.write("\t@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
				}
				bw.newLine();
				bw.write("\tprivate\t" + detail[0] + "\t" + detail[1] + " ;");
				bw.newLine();
				bw.newLine();
			}

			bw.newLine();

			// 生成无参的构造函数
			// bw.write("public\t" + getClassName(sentence) + "() {}");
			//
			// bw.newLine();
			// bw.newLine();
			//
			// 生成有参的构造函数
			// bw.write("public\t" + getClassName(sentence) + "(");
			// bw.newLine();
			// for (int i = 0; i < list.size(); i++) {
			// String[] detail = list.get(i);
			// if (i != list.size() - 1) {
			// bw.write(detail[0] + " " + detail[1] + ",");
			// } else {
			// bw.write(detail[0] + " " + detail[1]);
			// }
			// }
			// bw.write(")");
			// bw.write("{");
			// for (int i = 0; i < list.size(); i++) {
			//
			// // get set方法
			// String[] detail = list.get(i);
			// bw.write("this." + detail[1] + "=" + detail[1] + ";");
			// bw.newLine();
			// }
			// bw.write("\t}");

			bw.newLine();
			// 生成get\set方法
			for (int i = 0; i < list.size(); i++) {
				String[] detail = list.get(i);
				bw.newLine();
				bw.write("\tpublic\t" + detail[0] + "\t" + "get"
						+ initial(detail[1]) + " () {");
				bw.newLine();
				bw.write("\t" + "return" + "\t" + detail[1] + ";");
				bw.newLine();
				bw.write("\t}");
				bw.newLine();
				bw.write("\tpublic\t" + "void\t" + "set" + initial(detail[1])
						+ "(" + detail[0] + "  " + detail[1] + ") {");
				bw.newLine();
				bw.write("\t" + "this." + detail[1] + "=" + detail[1] + ";");
				bw.newLine();
				bw.write("\t}");
				bw.newLine();
			}
			bw.newLine();
			bw.newLine();

			// 生成toString()
			bw.write("\tpublic\tString\ttoString () {");
			bw.newLine();
			bw.write("\t" + "return" + "\t" + "\"" + getClassName(sentence)
					+ "[");
			for (int i = 0; i < list.size(); i++) {
				String[] detail = list.get(i);
				if (i == list.size() - 1) {
					bw.write(detail[1] + "=" + "\"" + "+" + detail[1] + "+"
							+ "\"" + "]" + "\"" + ";");
				} else {
					bw.write(detail[1] + "=" + "\"" + "+" + detail[1] + "+"
							+ "\"" + ",");
				}
			}
			bw.newLine();
			bw.write("\t}");
			bw.newLine();
			bw.newLine();
			bw.write("}");

			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean checkClass() {
		boolean boo = true;
		if (getClassName(sentence) == null) {
			boo = false;
		}
		return boo;

	}

	public void produceClass(String tableName, String path, String className) {

		setOUTPATH(path);
		setSentence("outClass{" + className + "("
				+ getColunmComments(tableName) + ")");
		setTableName("\"" + tableName.toUpperCase() + "\"");
		setPackageName(getPackagePath(path));
		boolean boo = checkClass();
		if (boo) {
			writeFile();
			System.out.println("实体类生成成功！");
		} else {
			System.out.println("实体类生成失败！");
		}
	}

	public static String getColunmComments(String tableName) {
		Map<String, String> colunmCommentsMap = new HashMap<String, String>();
		try {
			PropertiesUtil pUtil = new PropertiesUtil(
					"config/dbconfig.properties");
			Properties p = pUtil.getProperties();
			String url = p.getProperty("url");
			String driverClassName = p.getProperty("driverClassName");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			Class.forName(driverClassName);
			Connection conn = (Connection) DriverManager.getConnection(url,
					username, password);
			String sql = "select  * from  " + tableName;
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			for (int i = 0; i < metaData.getColumnCount(); i++) {

				String columnName = metaData.getColumnName(i + 1);
				int type = metaData.getColumnType(i + 1);
				colunmCommentsMap.put(getColunmName(columnName),
						getDataType(type));
				colunmNames.put(getColunmName(columnName), "\"" + columnName
						+ "\"");
			}
			while (rs.next()) {
				colunmCommentsMap.put(rs.getString(1), rs.getString(2));
			}
			conn.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		int i = 1;
		for (Map.Entry<String, String> entry : colunmCommentsMap.entrySet()) {

			String type = entry.getValue();

			String key = entry.getKey();
			sb.append(type + " " + key);
			if (i < colunmCommentsMap.size()) {

				sb.append(",");
			}
			i++;
		}
		return sb.toString();

	}

	public static String getDataType(int i) {
		String dateTypeStr = "String";
		switch (i) {
		case 4:
			dateTypeStr = "Integer";
			break;
		case 6:
			dateTypeStr = "Float";
			break;
		case 8:
			dateTypeStr = "Double";
			break;
		case 12:
			dateTypeStr = "String";
			break;
		case 91:
			dateTypeStr = "Date";
			break;
		case 93:
			dateTypeStr = "Date";
			break;
		default:
			break;
		}
		return dateTypeStr;
	}

	public static String getColunmName(String name) {
		String getColunmName = name;
		String[] split = name.split("_");
		if (split.length > 0) {
			String str1;
			try {
				str1 = split[1];
			} catch (Exception e1) {
				return name;
			}
			String substring = str1.substring(0, 1);
			String upperCase = substring.toUpperCase();
			getColunmName = split[0] + upperCase + str1.substring(1);
			String strArray;
			try {
				StringBuffer sb = new StringBuffer();
				sb.append(getColunmName);
				for (int i = 2; i < split.length; i++) {
					strArray = split[i];
					String substring2 = strArray.substring(0, 1);
					sb.append(substring2.toUpperCase());
					sb.append(strArray.substring(1));
				}
				return sb.toString();
			} catch (Exception e) {
				getColunmName = name;
				substring = str1.substring(0, 1);
				upperCase = substring.toUpperCase();
				getColunmName = split[0] + upperCase + str1.substring(1);
				return getColunmName;
			}

		}

		return name;
	}

	public static String getPackagePath(String path) {
		String[] split = path.split("\\\\");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			sb.append(split[i]);
			if (i < split.length - 1) {
				sb.append(".");
			}
		}
		return sb.toString().substring(4);
	}

	public static void main(String[] args) {

		EntityFactory entityFactory = new EntityFactory();
		entityFactory.produceClass("ac_log", "src\\com\\ac\\entity\\", "AcLog");
	}
}
