package com.coding.mindtree.exceptions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param con
	 * @throws ConnectionFailedException
	 */
	public static void closeResource(Connection con) throws ConnectionFailedException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new ConnectionFailedException();
			}
		}
	}

	/**
	 * @param st
	 * @throws ConnectionFailedException
	 */
	public static void closeResource(Statement st) throws ConnectionFailedException {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new ConnectionFailedException();
			}
		}
	}

	/**
	 * @param res
	 * @throws ConnectionFailedException
	 */
	public static void closeResource(ResultSet res) throws ConnectionFailedException {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new ConnectionFailedException();
			}
		}
	}

}
