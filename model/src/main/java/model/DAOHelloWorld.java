package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Map;

/**
 * The Class DAOHelloWorld.
 *
 * @author Jean-Aymeric Diet
 */
class DAOHelloWorld extends DAOEntity<Map> {

	/**
	 * Instantiates a new DAO hello world.
	 *
	 * @param connection
	 *          the connection
	 * @throws SQLException
	 *           the SQL exception
	 */
	public DAOHelloWorld(final Connection connection) throws SQLException {
		super(connection);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#create(model.Entity)
	 */
	@Override
	public boolean create(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#delete(model.Entity)
	 */
	@Override
	public boolean delete(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#update(model.Entity)
	 */
	@Override
	public boolean update(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#find(int)
	 */
	@Override
	public Map find(final int id) {
		Map map = new Map();

		try {
			final String sql = "SELECT level, width, height FROM levels WHERE id=?";
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, id);
			call.execute();
			final ResultSet result = call.getResultSet();
			result.next();
			map = new Map(result.getObject(1).toString(), (int) result.getObject(2), (int) result.getObject(3));
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
	@Override
	public Map find(final String code) {
		return null;
	}
}
