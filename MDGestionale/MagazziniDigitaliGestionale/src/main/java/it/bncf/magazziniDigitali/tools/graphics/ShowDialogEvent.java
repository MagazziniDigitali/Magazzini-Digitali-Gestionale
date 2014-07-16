/**
 * 
 */
package it.bncf.magazziniDigitali.tools.graphics;

import it.bncf.magazziniDigitali.gestionale.search.SearchPanel;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

/**
 * @author massi
 *
 */
@SuppressWarnings("rawtypes")
public abstract class ShowDialogEvent<ED extends EditDialog, P extends SearchPanel, ID extends Serializable> 
	implements RecordClickHandler, ClickHandler {

	public static String CLEAR="clear";
	public static String NEW="new";
	public static String CANCEL="cancel";
	public static String EDIT="edit";
	protected P parent;
//	protected UtentiInfo utentiInfo;
	protected Messaggi messaggi;
	protected Costanti costanti;
	protected String action;

	/**
	 * 
	 */
	public ShowDialogEvent(P parent, //UtentiInfo utentiInfo, 
			Messaggi messaggi, Costanti costanti, String action) {
		this.parent = parent;
//		this.utentiInfo=utentiInfo;
		this.messaggi = messaggi;
		this.costanti = costanti;
		this.action = action;
	}

	/**
	 * @see com.smartgwt.client.widgets.grid.events.RecordClickHandler#onRecordClick(com.smartgwt.client.widgets.grid.events.RecordClickEvent)
	 */
	@Override
	public void onRecordClick(RecordClickEvent event) {
		String descrizione = null;
		ID id = null;
		String myID = null;
		
		descrizione = getDescrizione(event);
		myID = event.getRecord().getAttributeAsString(costanti.id());
		id = getID(myID);
		if (action.equals(EDIT)){
			SC.confirm(costanti.msgModifica_title(),
					messaggi.msgModifica(descrizione),
					
					new BoolModifica<EditDialog<Messaggi,Costanti, SearchPanel>>(event));
		} else if (action.equals(CANCEL)){

			if (!isLicensed()){
				com.google.gwt.user.client.Window.alert(costanti.cancelNotAbilitata());
			}else {
			
				SC.confirm(costanti.msgCancel_title(),
						messaggi.cancel_message(descrizione),
						new BoolCancel(id, descrizione));
			}
		}
	}

	protected abstract ID getID(String id);

	protected String getDescrizione(RecordClickEvent event){
		return event.getRecord().getAttributeAsString(costanti.descrizione());
	}
	protected abstract boolean isLicensed();
	
	/**
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
	 */
	@Override
	public void onClick(ClickEvent event) {
		if (action.equals(NEW)){
			SC.confirm(costanti.msgNuovo_title(),
					messaggi.msgNuovo(),
					new BoolModifica<EditDialog<Messaggi,Costanti, SearchPanel>>());
		} else if (action.equals(CLEAR)){
			clear(parent);
//			parent.search();
		}
	}

	public abstract ED newInstance(Record record);

	public abstract void clear(P parent);

	public abstract void delete(ID id, AsyncCallback<Void> callback) throws Exception;

	class BoolCancel implements BooleanCallback{

		private ID id;
		private String descrizione;
		
		public BoolCancel(ID id, String descrizione){
			this.id = id;
			this.descrizione = descrizione;
		}

		@Override
		public void execute(Boolean value) {
			if (Boolean.TRUE.equals(value)) {
				try{
					delete(id , new ActionDelete(descrizione));
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}

	class ActionDelete implements AsyncCallback<Void>{

		private String descrizione;

		public ActionDelete(String descrizione){
			this.descrizione = descrizione;
		}

		@Override
		public void onFailure(Throwable caught) {
			com.google.gwt.user.client.Window.alert(messaggi.cancel_msgError(descrizione));
		}

		@Override
		public void onSuccess(Void result) {
			com.google.gwt.user.client.Window.alert(messaggi.cancel_msgOk(descrizione));
//			parent.search();
		}
		
	}
	@SuppressWarnings("hiding")
	class BoolModifica<ED extends EditDialog<Messaggi, Costanti, SearchPanel>>  implements BooleanCallback{
		private RecordClickEvent event;
		
		public BoolModifica(){
		}
	
		public BoolModifica(RecordClickEvent event){
			this.event = event;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public void execute(Boolean value) {
			ED edit = null;
			if (Boolean.TRUE.equals(value)) {
				edit = (ED) newInstance((event==null?null:event.getRecord()));
				edit.show();
				
			}
		}
	
	}
}