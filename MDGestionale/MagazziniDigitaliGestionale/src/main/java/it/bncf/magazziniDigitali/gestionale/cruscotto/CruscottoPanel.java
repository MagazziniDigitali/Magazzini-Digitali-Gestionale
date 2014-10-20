/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.cruscotto;

import java.util.TreeMap;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;

/**
 * @author massi
 *
 */
public class CruscottoPanel extends CaptionPanel {

	private CruscottoMessages messagi = null;
	private CruscottoConstants costanti = null;

	private IntegerItem initTrasf = null;
	private IntegerItem fineTrasf = null;
	private IntegerItem initValid = null;
	private IntegerItem fineValid = null;
	private IntegerItem initPublish = null;
	private IntegerItem finePublish = null;
	private IntegerItem errorTrasf = null;
	private IntegerItem errorVal = null;
	private IntegerItem errorDecomp = null;
	private IntegerItem errorCopy = null;
	private IntegerItem errorMove = null;
	private IntegerItem errorPub = null;
	private IntegerItem errorDelete = null;

	private CruscottoServiceAsync cruscottoService = null;

	/**
	 * 
	 */
	public CruscottoPanel(String idIstituto, String nomeIstituto, String ipClient) {
		super();

		messagi = GWT.create(CruscottoMessages.class);
		costanti = GWT.create(CruscottoConstants.class);
		cruscottoService = GWT.create(CruscottoService.class);
		this.setCaptionText(messagi.title());

		this.getElement().setClassName("cruscotto");
		init();
		this.getContentWidget().setStyleName("cwCruscotto",true);
		cruscottoService.findStatus(idIstituto, new CruscottoServiceResult(this));
		Scheduler.get().scheduleFixedPeriod(new CruscottoAggiorna(this, cruscottoService, idIstituto),60000);
	}

	/**
	 * Disegno il contenuto del pannello
	 */
	private void init(){
		DynamicForm sf = null;
		FormItem[] fields = null;
		sf = new DynamicForm();
		sf.setStyleName("fCruscotto");
		sf.setWidth("95%");
		sf.setColWidths("*,105, *,105, *,105, *,105, *,105");
		sf.setNumCols(10);
		
		fields = new FormItem[13];
		
		fields[0] = getInitTrasf();
		fields[1] = getFineTrasf();
		fields[2] = getInitValid();
		fields[3] = getFineValid();
		fields[4] = getInitPublish();
		fields[5] = getFinePublish();
		fields[6] = getErrorTrasf();
		fields[7] = getErrorVal();
		fields[8] = getErrorDecomp();
		fields[9] = getErrorCopy();
		fields[10] = getErrorMove();
		fields[11] = getErrorPub();
		fields[12] = getErrorDelete();
		sf.setFields(fields);
		this.setContentWidget(sf);
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato initTrasf
	 * 
	 * @return
	 */
	public IntegerItem getInitTrasf(){
		if (initTrasf ==null){
			initTrasf = new IntegerItem(costanti.initTrasf(), costanti.initTrasf_title());
			initTrasf.setValue(0);
			initTrasf.setWidth(100);
			initTrasf.setDisabled(true);
			initTrasf.setRequired(true);
			initTrasf.setShowDisabled(false);
		}
		return initTrasf;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato fineTrasf
	 * 
	 * @return
	 */
	public IntegerItem getFineTrasf(){
		if (fineTrasf ==null){
			fineTrasf = new IntegerItem(costanti.fineTrasf(), costanti.fineTrasf_title());
			fineTrasf.setValue(0);
			fineTrasf.setWidth(100);
			fineTrasf.setDisabled(true);
			fineTrasf.setRequired(true);
			fineTrasf.setShowDisabled(false);
		}
		return fineTrasf;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato initValid
	 * 
	 * @return
	 */
	public IntegerItem getInitValid(){
		if (initValid ==null){
			initValid = new IntegerItem(costanti.initValid(), costanti.initValid_title());
			initValid.setValue(0);
			initValid.setWidth(100);
			initValid.setDisabled(true);
			initValid.setRequired(true);
			initValid.setShowDisabled(false);
		}
		return initValid;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato fineValid
	 * 
	 * @return
	 */
	public IntegerItem getFineValid(){
		if (fineValid ==null){
			fineValid = new IntegerItem(costanti.fineValid(), costanti.fineValid_title());
			fineValid.setValue(0);
			fineValid.setWidth(100);
			fineValid.setDisabled(true);
			fineValid.setRequired(true);
			fineValid.setShowDisabled(false);
		}
		return fineValid;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato initPublish
	 * 
	 * @return
	 */
	public IntegerItem getInitPublish(){
		if (initPublish ==null){
			initPublish = new IntegerItem(costanti.initPublish(), costanti.initPublish_title());
			initPublish.setValue(0);
			initPublish.setWidth(100);
			initPublish.setDisabled(true);
			initPublish.setRequired(true);
			initPublish.setShowDisabled(false);
		}
		return initPublish;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato finePublish
	 * 
	 * @return
	 */
	public IntegerItem getFinePublish(){
		if (finePublish ==null){
			finePublish = new IntegerItem(costanti.finePublish(), costanti.finePublish_title());
			finePublish.setValue(0);
			finePublish.setWidth(100);
			finePublish.setDisabled(true);
			finePublish.setRequired(true);
			finePublish.setShowDisabled(false);
		}
		return finePublish;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato errorTrasf
	 * 
	 * @return
	 */
	public IntegerItem getErrorTrasf(){
		if (errorTrasf ==null){
			errorTrasf = new IntegerItem(costanti.errorTrasf(), costanti.errorTrasf_title());
			errorTrasf.setValue(0);
			errorTrasf.setWidth(100);
			errorTrasf.setDisabled(true);
			errorTrasf.setRequired(true);
			errorTrasf.setShowDisabled(false);
		}
		return errorTrasf;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato errorVal
	 * 
	 * @return
	 */
	public IntegerItem getErrorVal(){
		if (errorVal ==null){
			errorVal = new IntegerItem(costanti.errorVal(), costanti.errorVal_title());
			errorVal.setValue(0);
			errorVal.setWidth(100);
			errorVal.setDisabled(true);
			errorVal.setRequired(true);
			errorVal.setShowDisabled(false);
		}
		return errorVal;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato errorDecomp
	 * 
	 * @return
	 */
	public IntegerItem getErrorDecomp(){
		if (errorDecomp ==null){
			errorDecomp = new IntegerItem(costanti.errorDecomp(), costanti.errorDecomp_title());
			errorDecomp.setValue(0);
			errorDecomp.setWidth(100);
			errorDecomp.setDisabled(true);
			errorDecomp.setRequired(true);
			errorDecomp.setShowDisabled(false);
		}
		return errorDecomp;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato errorCopy
	 * 
	 * @return
	 */
	public IntegerItem getErrorCopy(){
		if (errorCopy ==null){
			errorCopy = new IntegerItem(costanti.errorCopy(), costanti.errorCopy_title());
			errorCopy.setValue(0);
			errorCopy.setWidth(100);
			errorCopy.setDisabled(true);
			errorCopy.setRequired(true);
			errorCopy.setShowDisabled(false);
		}
		return errorCopy;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato errorMove
	 * 
	 * @return
	 */
	public IntegerItem getErrorMove(){
		if (errorMove ==null){
			errorMove = new IntegerItem(costanti.errorMove(), costanti.errorMove_title());
			errorMove.setValue(0);
			errorMove.setWidth(100);
			errorMove.setDisabled(true);
			errorMove.setRequired(true);
			errorMove.setShowDisabled(false);
		}
		return errorMove;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato errorPub
	 * 
	 * @return
	 */
	public IntegerItem getErrorPub(){
		if (errorPub ==null){
			errorPub = new IntegerItem(costanti.errorPub(), costanti.errorPub_title());
			errorPub.setValue(0);
			errorPub.setWidth(100);
			errorPub.setDisabled(true);
			errorPub.setRequired(true);
			errorPub.setShowDisabled(false);
		}
		return errorPub;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato errorDelete
	 * 
	 * @return
	 */
	public IntegerItem getErrorDelete(){
		if (errorDelete ==null){
			errorDelete = new IntegerItem(costanti.errorDelete(), costanti.errorDelete_title());
			errorDelete.setValue(0);
			errorDelete.setWidth(100);
			errorDelete.setDisabled(true);
			errorDelete.setRequired(true);
			errorDelete.setShowDisabled(false);
		}
		return errorDelete;
	}
}

class CruscottoAggiorna implements RepeatingCommand{
	private CruscottoPanel cruscottoPanel;
	private CruscottoServiceAsync cruscottoService;
	private String idIstituto;

	public CruscottoAggiorna(CruscottoPanel cruscottoPanel, 
			CruscottoServiceAsync cruscottoService, String idIstituto){
		this.cruscottoPanel = cruscottoPanel;
		this.cruscottoService = cruscottoService;
		this.idIstituto = idIstituto;
	}

	@Override
	public boolean execute() {
		
		cruscottoService.findStatus(idIstituto, new CruscottoServiceResult(cruscottoPanel));

//		Integer i = cruscottoPanel.getInitTrasf().getValueAsInteger();
//		cruscottoPanel.getInitTrasf().setValue(i+1);
		return true;
	}

	
}

class CruscottoServiceResult implements AsyncCallback<TreeMap<String, Integer>>{

	private CruscottoPanel parent = null;

	public CruscottoServiceResult(CruscottoPanel parent){
		this.parent = parent;
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}
	
	@Override
	public void onSuccess(TreeMap<String, Integer> result) {

		setIntValue(parent.getInitTrasf(),result.get("INITTRASF"),"tbArancione");
		setIntValue(parent.getFineTrasf(),result.get("FINETRASF"),"tbGiallo");
		setIntValue(parent.getInitValid(),result.get("INITVALID"),"tbArancione");
		setIntValue(parent.getFineValid(),result.get("FINEVALID"),"tbGiallo");
		setIntValue(parent.getInitPublish(),result.get("INITPUBLISH"),"tbArancione");
		setIntValue(parent.getFinePublish(),result.get("FINEPUBLISH"),"tbVerde");
		setIntValue(parent.getErrorTrasf(),result.get("ERRORTRASF"),"tbRosso");
		setIntValue(parent.getErrorVal(),result.get("ERRORVAL"),"tbRosso");
		setIntValue(parent.getErrorDecomp(),result.get("ERRORDECOMP"),"tbRosso");
		setIntValue(parent.getErrorCopy(),result.get("ERRORCOPY"),"tbRosso");
		setIntValue(parent.getErrorMove(),result.get("ERRORMOVE"),"tbRosso");
		setIntValue(parent.getErrorPub(),result.get("ERRORPUB"),"tbRosso");
		setIntValue(parent.getErrorDelete(),result.get("ERRORDELETE"),"tbRosso");
	}

	private void setIntValue(IntegerItem campo, Integer value, String style){
		if (value ==null){
			campo.setValue(0);
			campo.setTextBoxStyle("");
		} else {
			campo.setValue(value.intValue());
			campo.setTextBoxStyle(style);
		}
	}
}