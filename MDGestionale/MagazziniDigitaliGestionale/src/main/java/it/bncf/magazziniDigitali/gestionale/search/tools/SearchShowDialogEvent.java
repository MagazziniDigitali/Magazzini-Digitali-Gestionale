/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.search.tools;


import it.bncf.magazziniDigitali.gestionale.search.SearchEditDialog;
import it.bncf.magazziniDigitali.gestionale.search.SearchPanel;
import it.bncf.magazziniDigitali.tools.graphics.Costanti;
import it.bncf.magazziniDigitali.tools.graphics.Messaggi;
import it.bncf.magazziniDigitali.tools.graphics.ShowDialogEvent;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;

/**
 * @author massi
 *
 */
public class SearchShowDialogEvent extends ShowDialogEvent<SearchEditDialog, SearchPanel, String> {

//	private SociServiceAsync service;
	
	/**
	 * @param parent
	 * @param utentiInfo
	 * @param messaggi
	 * @param costanti
	 */
	public SearchShowDialogEvent(SearchPanel parent, 
			Messaggi messaggi, Costanti costanti, 
//			SociServiceAsync service, 
			String action) {
		super(parent, messaggi, costanti, action);
//		this.service = service;
	}

	/* (non-Javadoc)
	 * @see mx.teca2013.TecaGestionale.tools.ShowDialogEvent#newInstance(com.smartgwt.client.data.Record)
	 */
	@Override
	public SearchEditDialog newInstance(Record record) {
		return new SearchEditDialog(parent, record);
	}

	@Override
	public void clear(SearchPanel parent) {
//		parent.getTxCognome().setValue("");
//		parent.getTxNome().setValue("");
//		parent.getSClub().setValue("");
	}

	@Override
	protected boolean isLicensed() {
		return true;
	}

	@Override
	public void delete(String id, AsyncCallback<Void> callback) {
//		service.delete(id, callback);
	}

	@Override
	protected String getID(String id) {
		return id;
	}

}
