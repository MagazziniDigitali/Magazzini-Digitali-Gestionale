package it.bncf.magazziniDigitali.tools.graphics;

import com.google.gwt.i18n.client.Messages;

public interface Messaggi extends Messages {

	String save_message(String id);
	
	String save_msgErro(String msg);
	
	String msgNuovo();
	
	String msgModifica(String descrizione);
	
	String cancel_message(String descrizione);
	
	String cancel_msgError(String descrizione);
	
	String cancel_msgOk(String descrizione);
}
