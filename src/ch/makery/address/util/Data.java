package ch.makery.address.util;

import java.sql.ResultSet;

public class Data {

	/**
	 * sqlStr初期化用
	 * @param string = sqlStr
	 */
	public Data(String string) {
		this.sqlStr = string;
	}
	public String selectedWhere = null;
	public String selectedOrderBy;
	ResultSet resultSet = null;

	public String sqlStr;
	public String selectedFirstName;
	public String selectedLastName;
	public String selectedStreet;
	public String selectedCity;
	public int selectedPostalCode;
	public String selectedSearchString;
}
