/**
 * 
 */
package it.bncf.magazziniDigitali.tools.graphics;


import it.bncf.magazziniDigitali.gestionale.search.SearchPanel;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

/**
 * @author massi
 *
 */
public abstract class EditDialog<M extends Messaggi, C extends Costanti, S extends SearchPanel> 
	extends Finestra<M, C> {

	protected EditDialog<M, C, S> window;

	private TextItem tId;

	protected S parent;

	protected Record record;

//	protected ButtonSave bSave;
	protected ClickHandler chSave;

	/**
	 * @param utentiInfo
	 */
	public EditDialog(S parent, Record record) {
		super();
		this.parent=parent;
		setShowFooter(false);
		window = this;
		this.record = record;
		initEditDialog();
		addItem(getDynamicForm());
	}

	protected abstract void initEditDialog();

	protected abstract DynamicForm getDynamicForm();

//	protected ButtonSave getBSave(){
//		if (bSave == null){
//			bSave = new ButtonSave();
//			bSave.setColSpan(2);
//			bSave.addClickHandler(chSave);
//		}
//		return bSave;
//	}

	public TextItem getTId(){
		if (tId==null){
			tId = new TextItem(costanti.id(), costanti.id_title());
			tId.setDisabled(true);
			tId.setShowDisabled(false);
			tId.setColSpan(3);
			if (record!= null){
				tId.setValue(record.getAttributeAsString(costanti.id()));
			}
			tId.setWidth(300);
			tId.setLength(36);
		}
		return tId;
	}
}
