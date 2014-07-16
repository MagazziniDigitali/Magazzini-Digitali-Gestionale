package it.bncf.magazziniDigitali.gestionale.search;

import it.bncf.magazziniDigitali.utils.Record;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SearchServiceAsync {

	void find(String idIstituto, String nomeFile,
			AsyncCallback<Vector<Record>> callback);

}
