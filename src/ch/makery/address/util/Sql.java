package ch.makery.address.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ch.makery.address.model.Person;

public class Sql {
	static final String serverName = "localhost";
	static final String databaseName = "db";
	public static final String user = "root";
	public static final String password = "password";
	static final String serverEncoding = "sjis";
	public static final String url =  "jdbc:mysql://localhost/" + databaseName;
	static final String tableName = "addressApp";

	/**
	 * SELECT文を構築する。
	 * @param data
	 * @return SELECT文
	 */
	public static String createQuerrySELECT(Data data){
		data.sqlStr = "SELECT * FROM"+tableName+"WHERE "+data.selectedWhere+" = \""+data.selectedSearchString+"\" ORDER BY "+data.selectedOrderBy+" asc";
		return data.sqlStr;
	}

	/**
	 * INSERT文を構築し、返す。
	 * @param person
	 * @return INSERT文
	 */
	public static String createQuerryINSERT(Person person) {
		String sqlStr = "INSERT INTO addressApp (firstName,lastName,street,city,postalCode) VALUES(\""+person.getFirstName()+"\",\""+person.getLastName()+"\",\""+person.getStreet()+"\",\""+person.getCity()+"\","+person.getPostalCode()+");";
		return sqlStr;
	}

	/**
	 * DELETE文を構築し、返す。
	 * @param person
	 * @return DELETE文
	 */
	public static String createQuerryDELETE(Person person) {
		String sqlStr = "DELETE FROM addressApp WHERE firstName = \""+person.getFirstName()+"\" and lastName = \""+person.getLastName()+"\";";
		return sqlStr;
	}

	/**
	 * 文字列のクエリを受け取り、mysqlで実行する。
	 * @param sqlStr
	 */
	public static void action(String sqlStr) {
		Connection connection;
		try {
			connection = DriverManager.getConnection(Sql.url,Sql.user,Sql.password);
			java.sql.Statement statement =connection.createStatement();
			System.out.println(sqlStr);
			statement.executeUpdate(sqlStr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
