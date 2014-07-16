package it.bncf.magazziniDigitali.gestionale.cruscotto;

import java.util.TreeMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CruscottoServiceAsync {

	void findStatus(String idIstituto,
			AsyncCallback<TreeMap<String, Integer>> callback);

	void checkIstituto(String idIstituto,
			AsyncCallback<TreeMap<String, Object>> callback);

}
