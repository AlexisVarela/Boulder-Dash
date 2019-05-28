package controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

/**
 * The Class Controller.
 */
public final class Controller implements IController {

	/** The view. */
	private IView		view;

	/** The model. */
	private IModel	model;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
	}

	/**
     * Control.
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#control()
	 */
	public void control() {
		this.view.printMessage("Appuyez sur Z,Q,S,D pour vous déplacer.");
	}

	/**
     * Sets the view.
     *
     * @param pview
     *            the new view
     */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
     * Order perform.
     *
     * @param controllerOrder
     *            the controller order
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {
		if (canMove(controllerOrder)) {
			switch (controllerOrder) {
				case Top:
					this.model.getPlayer().setPosY(-16);
					break;
				case Right:
					this.model.getPlayer().setPosX(16);
					break;
				case Bottom:
					this.model.getPlayer().setPosY(16);
					break;
				case Left:
					this.model.getPlayer().setPosX(-16);
					break;
			}
		}
	}
	
	public boolean canMove(ControllerOrder controllerOrder) {
		int x = 0;
		int y = 0;
		String[] notMove = {"unbreakableWall", "breakableWall"};
		switch(controllerOrder) {
			case Top:
				y = -1;
				break;
			case Right:
				x = 1;
				break;
			case Bottom:
				y = 1;
				break;
			case Left:
				x = -1;
				break;
		}
		
		JSONObject map = new JSONObject(model.getMap().getDataMap());
		String block = map.getJSONObject(String.valueOf((model.getPlayer().getPosY()/16)+y)).getJSONObject(String.valueOf((model.getPlayer().getPosX()/16)+x)).getString("type");
		if (!Arrays.asList(notMove).contains(block)) {
			return true;
		}
		return false;
	}

}
