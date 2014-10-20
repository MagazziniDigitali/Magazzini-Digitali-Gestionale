/**
 * 
 */
package it.bncf.magazziniDigitali.tools.graphics;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.ButtonItem;

/**
 * @author massi
 *
 */
public class ButtonSave extends ButtonItem {

	private Costanti constants = GWT
			.create(Costanti.class);

	/**
	 * 
	 */
	public ButtonSave() {
		init(constants.bSave(), constants.bSave_title());
	}

	/**
	 * @param jsObj
	 */
	public ButtonSave(JavaScriptObject jsObj) {
		super(jsObj);
		init(constants.bSave(), constants.bSave_title());
	}

	/**
	 * @param title
	 */
	public ButtonSave(String title) {
		super(title);
		init(constants.bSave(), title);
	}

	/**
	 * @param name
	 * @param title
	 */
	public ButtonSave(String name, String title) {
		super(name, title);
		init(name, title);
	}

	private void init(String name, String title){
		setName(name);
		setTitle(title);
		setIcon(constants.bSave_icon());
		setTitleColSpan(0);
		setAlign(Alignment.CENTER);
		setTextAlign(Alignment.CENTER);
		setTop(10);
	}
}
