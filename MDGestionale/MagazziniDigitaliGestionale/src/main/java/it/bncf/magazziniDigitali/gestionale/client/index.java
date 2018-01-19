package it.bncf.magazziniDigitali.gestionale.client;


import it.bncf.magazziniDigitali.gestionale.cruscotto.CruscottoPanel;
import it.bncf.magazziniDigitali.gestionale.cruscotto.CruscottoService;
import it.bncf.magazziniDigitali.gestionale.cruscotto.CruscottoServiceAsync;
import it.bncf.magazziniDigitali.gestionale.search.SearchPanel;

import java.util.TreeMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class index implements EntryPoint {
	
	private RootPanel center;
	
	private RootPanel header;

	private CruscottoServiceAsync cruscottoService = null;

	protected index index = null;

	private IndexConstants costanti = null;

	private String idIstituto = null;

	private IndexMessages messaggi = null;

	/**
	 * 
	 */
	public index() {
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Window.alert("Ciao");
	}
	public void onModuleLoad_old() {

		
		center = RootPanel.get("center");
		header = RootPanel.get("header");
		cruscottoService = GWT.create(CruscottoService.class);
		costanti = GWT.create(IndexConstants.class);
		messaggi = GWT.create(IndexMessages.class);
		
		idIstituto = Window.Location.getParameter("idIstituto");
		Window.alert("Ciao");
		if (idIstituto != null && !idIstituto.equals("")){
			cruscottoService.checkIstituto(idIstituto, new AsyncCallback<TreeMap<String,Object>>() {
				
				@Override
				public void onSuccess(TreeMap<String, Object> result) {
					CruscottoPanel cruscottoPanel = null;
					CaptionPanel cpIstituto = null;
					CaptionPanel cpUrlIstituto = null;
					SearchPanel searchPanel = null;
					String msg = null;

					if (result.get("ERROR")==null){
						cpIstituto = new CaptionPanel();
						cpIstituto.setStyleName("titleIstituto");
						cpIstituto.setCaptionHTML(messaggi.titleIstituto((String) result.get("Nome")));;
						header.add(cpIstituto);

						cpUrlIstituto = new CaptionPanel();
						cpUrlIstituto.setStylePrimaryName("urlIstituto");
						cpUrlIstituto.setCaptionHTML("<a href=\""+
								(String) result.get("Url")+"\">"
										+ "<img class=\"urlIstituto\" src=\""+
								(String) result.get("Logo")+"\"/></a>");
						header.add(cpUrlIstituto);

						cruscottoPanel = new CruscottoPanel(idIstituto, 
								(String) result.get("Nome"),
								(String)result.get("IpClient"));
						center.add(cruscottoPanel);
						searchPanel = new SearchPanel(idIstituto, 
								(String) result.get("Nome"),
								(String)result.get("IpClient"));
						center.add(searchPanel);
					} else {
						if (result.get("ERROR").equals("ipUnAuthorized")){
							msg= messaggi.ipUnAuthorized((String)result.get("IpClient"), (String)result.get("Nome"));
						} else if (result.get("ERROR").equals("ipNotFound")){
							msg= messaggi.ipNotFound((String)result.get("Nome"));
						} else if (result.get("ERROR").equals("istitutoNotFound")){
							msg= messaggi.istitutoNotFound(idIstituto);
						} 
						Window.alert(msg);
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}
			});
		} else {
			Window.alert(costanti.istitutoNotFound());
		}

		index = this;
	}
}

