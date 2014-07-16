/**
 * 
 */
package it.bncf.magazziniDigitali.tools.graphics;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author massi
 *
 */
public class CellEdit extends ListGridField {

	private Costanti constants = GWT
			.create(Costanti.class);

	/**
	 * 
	 */
	public CellEdit() {
		init(constants.modifica(), constants.modifica_title());
	}

	/**
	 * @param jsObj
	 */
	public CellEdit(JavaScriptObject jsObj) {
		super(jsObj);
		init(constants.modifica(), constants.modifica_title());
	}

	/**
	 * @param name
	 */
	public CellEdit(String name) {
		super(name);
		init(name, constants.modifica_title());
	}

	/**
	 * @param name
	 * @param width
	 */
	public CellEdit(String name, int width) {
		super(name, width);
		init(name, constants.modifica_title());
	}

	/**
	 * @param name
	 * @param title
	 */
	public CellEdit(String name, String title) {
		super(name, title);
		init(name, title);
	}

	/**
	 * @param name
	 * @param title
	 * @param width
	 */
	public CellEdit(String name, String title, int width) {
		super(name, title, width);
		init(name, title);
	}

	private void init(String name, String title){
		setName(name);
		setTitle(title);
		setType(ListGridFieldType.ICON);
		setCellIcon(constants.modifica_icon());
		setCanEdit(false);
		setCanFilter(true);
		setCanGroupBy(false);
		setCanSort(false);
	}
}
