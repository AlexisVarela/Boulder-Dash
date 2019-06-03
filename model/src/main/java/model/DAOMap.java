package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The Class DAOMap.
 *
 */
class DAOMap {

	
	private Connection connection;

	/**
	 * Instantiates a new DAO map.
	 *
	 * @param connection
	 *          the connection
	 * @throws SQLException
	 *           the SQL exception
	 */
	public DAOMap(final Connection connection) throws SQLException {
		this.connection = connection;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#create(model.Entity)
	 */
	public boolean create(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#delete(model.Entity)
	 */
	public boolean delete(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#update(model.Entity)
	 */
	public boolean update(final Map entity) {
		// Not implemented
		return false;
	}

	/**
	 * Finds the map
	 * @param id Map ID from DB
	 * @return Map
	 */
	public Map find(final int id) {
		Map map = new Map();

		try {
			final String sql = "CALL getMap(?)";
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, id);
			call.execute();
			final ResultSet result = call.getResultSet();
			result.next();
			map = new Map(result.getObject(1).toString(), (int) result.getObject(2), (int) result.getObject(3), (int) result.getObject(4));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#find(java.lang.String)
	 */
	public Map find(final String code) {
		return null;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
}
