package contract;

import java.util.Observable;

import entity.Map;
import model.Player;

/**
 * The Interface IModel.
 *
 */
public interface IModel {

	/**
	 * Gets the map and player.
	 *
	 * @return the map and player entity
	 */
	Map getMap();
	
	Player getPlayer();

	/**
	 * Load the map.
	 *
	 * @param code
	 *          the code
	 */
	void loadMap(String code);

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();
}
