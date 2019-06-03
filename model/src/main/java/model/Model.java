package model;

import java.sql.SQLException;
import java.util.Observable;

import contract.IModel;

/**
 * The Class Model.
 *
 */
public final class Model extends Observable implements IModel {

	private Map map;
	private Player player;

	/**
	 * Instantiates a new model.
	 */
	public Model() {
		this.map = new Map();
		this.player = new Player();
	}


	/**
     * Gets the map.
     *
     * @return the map
     */
	public Map getMap() {
		return this.map;
	}

	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	/**
     * Sets the hello world.
     *
     * @param map
     *            The map
     */
	private void setMap(final Map map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}

	/**
     * Load the map.
     *
     * @param id
     *            Map ID from DB
     */
	public void loadMap(final int id) {
		try {
			final DAOMap daoMap = new DAOMap(DBConnection.getInstance().getConnection());
			this.setMap(daoMap.find(id));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	/**
     * Gets the observable.
     *
     * @return the observable
     */
	public Observable getObservable() {
		return this;
	}


	@Override
	public void loadMap(String code) {
	}
}
