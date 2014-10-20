/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.client;

import com.google.gwt.i18n.client.Messages;

/**
 * @author massi
 *
 */
public interface IndexMessages extends Messages {

	String confermaLogout();

	String ipUnAuthorized(String ipClient, String nomeIstituto);

	String ipNotFound(String nomeIstituto);

	String istitutoNotFound(String idIstituto);
	
	String titleIstituto(String nomeIstituto);
}
