package ch.makery.address.util;

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
	String createQuerrySELECT(Data data){
		data.sqlStr = "SELECT * FROM"+tableName+"WHERE "+data.selectedWhere+" = \""+data.selectedSearchString+"\" ORDER BY "+data.selectedOrderBy+" asc";
		return data.sqlStr;
	}

	/**
	 * INSERT文を構築し、data.sqlStrに格納する。
	 * @param data
	 */
	void createQuerryINSERT(Data data) {
	}
}
