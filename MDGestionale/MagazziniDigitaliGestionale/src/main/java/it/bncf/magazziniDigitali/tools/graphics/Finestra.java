/**
 * 
 */
package it.bncf.magazziniDigitali.tools.graphics;


import com.smartgwt.client.widgets.Window;

/**
 * @author massi
 *
 */
public abstract class Finestra<M extends Messaggi, C extends Costanti> 
	extends Window {


	protected M messaggi;

	protected C costanti;
	
	/**
	 * 
	 */
	public Finestra() {
		init();
		setAutoCenter(true);
		setAutoSize(false);
		setShowFooter(true);
		setShowMaximizeButton(false);
	}

	public abstract void init();
}
