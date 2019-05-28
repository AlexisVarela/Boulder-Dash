package contract;

import java.util.Observable;

import entity.Map;
import model.Player;

/**
 * The Interface IModel.
 *
 * @author Jean-Aymeric Diet
 */
public interface IModel {

	/**
	 * Gets the hello world.
	 *
	 * @return the helloworld entity
	 */
	Map getMap();
	
	Player getPlayer();

	/**
	 * Load the message.
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
