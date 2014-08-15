/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.search;

import it.bncf.magazziniDigitali.tools.graphics.EditDialog;

//import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateTimeItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

/**
 * @author massi
 * 
 */
public class SearchEditDialog extends
		EditDialog<SearchMessages, SearchConstants, SearchPanel> {

	private DynamicForm dynamicForm;

	private TextItem tNomeFile;
	private DateTimeItem dtNomeFileMod;
	private TextItem tStato;

	private DateTimeItem dtTrasfDataStart;
	private DateTimeItem dtTrasfDataEnd;
	private TextItem tTrasfEsito;

	private DateTimeItem dtValidateDataStart;
	private DateTimeItem dtValidateDataEnd;
	private TextItem tValidateEsito;

	private TextItem tXmlMimeType;

	private DateTimeItem dtDecompDataStart;
	private DateTimeItem dtDecompDataEnd;
	private TextItem tDecompEsito;

	private DateTimeItem dtPublishDataStart;
	private DateTimeItem dtPublishDataEnd;
	private TextItem tPublishEsito;

	private DateTimeItem dtCopyPremisDataStart;
	private DateTimeItem dtCopyPremisDataEnd;
	private TextItem tCopyPremisEsito;

	private DateTimeItem dtMoveFileDataStart;
	private DateTimeItem dtMoveFileDataEnd;
	private TextItem tMoveFileEsito;

	private DateTimeItem dtDeleteLocalDataStart;
	private DateTimeItem dtDeleteLocalDataEnd;
	private TextItem tDeleteLocalEsito;

	private TextItem tPremisFile;
	
	public SearchEditDialog(SearchPanel parent, Record record) {
		super(parent, record);
		setSize("850px", "328px");
		setTitle(costanti.editTitle());
//		if (bSave != null) {
//			bSave.setDisabled(true);
//		}

//		if (record !=null){
//			tabSet.setDisabled(true);
//		} else {
//			tabSet.setDisabled(false);
//			search();
//		}
	}

	@Override
	protected DynamicForm getDynamicForm() {
		if (dynamicForm == null) {
			dynamicForm = new DynamicForm();
			dynamicForm.setSize("830px", "300px");
			dynamicForm.setNumCols(6);
			dynamicForm.setColWidths(100,120,100,120,100,"*");
			dynamicForm.setLayoutAlign(Alignment.CENTER);

			dynamicForm.setFields(new FormItem[] { 
					getTNomeFile(), getDtNomeFileMod(), getTStato(), 
					getDtTrasfDataStart(), getDtTrasfDataEnd(), getTTrasfEsito(), 
					getDtValidateDataStart(), getDtValidateDataEnd(), getTValidateEsito(), 
					getDtDecompDataStart(), getDtDecompDataEnd(), getTDecompEsito(), 
					getDtCopyPremisDataStart(), getDtCopyPremisDataEnd(), getTCopyPremisEsito(), 
					getDtMoveFileDataStart(), getDtMoveFileDataEnd(), getTMoveFileEsito(), 
					getDtPublishDataStart(), getDtPublishDataEnd(), getTPublishEsito(), 
					getDtDeleteLocalDataStart(), getDtDeleteLocalDataEnd(), getTDeleteLocalEsito(), 
					getTXmlMimeType(), getTPremisFile()
					});

		}
		return dynamicForm;
	}

	private TextItem getTNomeFile() {
		if (tNomeFile == null) {
			tNomeFile = new TextItem(costanti.nomeFile(),
					costanti.nomeFile_title());
			tNomeFile.setDisabled(true);
			tNomeFile.setShowDisabled(false);
			if (record != null) {
				tNomeFile.setValue(record.getAttributeAsString(costanti
						.nomeFile()));
			}
			tNomeFile.setWidth("*");
		}
		return tNomeFile;
	}

	private DateTimeItem getDtNomeFileMod() {
		if (dtNomeFileMod == null) {
			dtNomeFileMod = new DateTimeItem(costanti.nomeFileMod(),
					costanti.nomeFileMod_title());
			dtNomeFileMod.setDisabled(true);
			dtNomeFileMod.setShowDisabled(false);
			dtNomeFileMod.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.nomeFileMod())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.nomeFileMod())));
				dtNomeFileMod.setValue(record.getAttributeAsString(costanti
						.nomeFileMod()));
			}
			dtNomeFileMod.setWidth("*");
		}
		return dtNomeFileMod;
	}

	private TextItem getTStato() {
		if (tStato == null) {
			tStato = new TextItem(costanti.stato(),
					costanti.stato_title());
			tStato.setDisabled(true);
			tStato.setShowDisabled(false);
			if (record != null) {
				String stato = null;
				stato = record.getAttributeAsString(costanti
						.stato());
				if (stato.equals("INITTRASF")){
					stato = costanti.statoINITTRASF();
				} else if (stato.equals("FINETRASF")){
					stato = costanti.statoFINETRASF();
				} else if (stato.equals("ERROR")){
					stato = costanti.statoERROR();
				} else if (stato.equals("ERRORTRASF")){
					stato = costanti.statoERRORTRASF();
				} else if (stato.equals("ERRORVAL")){
					stato = costanti.statoERRORVAL();
				} else if (stato.equals("ERRORDECOMP")){
					stato = costanti.statoERRORDECOMP();
				} else if (stato.equals("ERRORCOPY")){
					stato = costanti.statoERRORCOPY();
				} else if (stato.equals("ERRORMOVE")){
					stato = costanti.statoERRORMOVE();
				} else if (stato.equals("ERRORPUB")){
					stato = costanti.statoERRORPUB();
				} else if (stato.equals("ERRORDELETE")){
					stato = costanti.statoERRORDELETE();
				} else if (stato.equals("INITVALID")){
					stato = costanti.statoINITVALID();
				} else if (stato.equals("FINEVALID")){
					stato = costanti.statoFINEVALID();
				} else if (stato.equals("INITPUBLISH")){
					stato = costanti.statoINITPUBLISH();
				} else if (stato.equals("FINEPUBLISH")){
					stato = costanti.statoFINEPUBLISH();
				}
				tStato.setValue(stato);
			}
			tStato.setWidth("*");
		}
		return tStato;
	}

	private DateTimeItem getDtTrasfDataStart() {
		if (dtTrasfDataStart == null) {
			dtTrasfDataStart = new DateTimeItem(costanti.trasfDataStart(),
					costanti.trasfDataStart_title());
			dtTrasfDataStart.setDisabled(true);
			dtTrasfDataStart.setShowDisabled(false);
			dtTrasfDataStart.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.trasfDataStart())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.trasfDataStart())));
				dtTrasfDataStart.setValue(record.getAttributeAsString(costanti
						.trasfDataStart()));
			}
			dtTrasfDataStart.setWidth("*");
		}
		return dtTrasfDataStart;
	}

	private DateTimeItem getDtTrasfDataEnd() {
		if (dtTrasfDataEnd == null) {
			dtTrasfDataEnd = new DateTimeItem(costanti.trasfDataEnd(),
					costanti.trasfDataEnd_title());
			dtTrasfDataEnd.setDisabled(true);
			dtTrasfDataEnd.setShowDisabled(false);
			dtTrasfDataEnd.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.trasfDataEnd())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.trasfDataEnd())));
				dtTrasfDataEnd.setValue(record.getAttributeAsString(costanti
						.trasfDataEnd()));
			}
			dtTrasfDataEnd.setWidth("*");
		}
		return dtTrasfDataEnd;
	}

	private TextItem getTTrasfEsito() {
		if (tTrasfEsito == null) {
			tTrasfEsito = new TextItem(costanti.trasfEsito(),
					costanti.trasfEsito_title());
			tTrasfEsito.setDisabled(true);
			tTrasfEsito.setShowDisabled(false);
			if (record != null) {
				if(record.getAttributeAsBoolean(costanti.trasfEsito())){
					tTrasfEsito.setValue(costanti.esitoPositivo());
				}else {
					tTrasfEsito.setValue(costanti.esitoNegativo());
				}
			}
			tTrasfEsito.setWidth("*");
		}
		return tTrasfEsito;
	}

	private DateTimeItem getDtValidateDataStart() {
		if (dtValidateDataStart == null) {
			dtValidateDataStart = new DateTimeItem(costanti.validateDataStart(),
					costanti.validateDataStart_title());
			dtValidateDataStart.setDisabled(true);
			dtValidateDataStart.setShowDisabled(false);
			dtValidateDataStart.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.validateDataStart())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.validateDataStart())));
				dtValidateDataStart.setValue(record.getAttributeAsString(costanti
						.validateDataStart()));
			}
			dtValidateDataStart.setWidth("*");
		}
		return dtValidateDataStart;
	}

	private DateTimeItem getDtValidateDataEnd() {
		if (dtValidateDataEnd == null) {
			dtValidateDataEnd = new DateTimeItem(costanti.validateDataEnd(),
					costanti.validateDataEnd_title());
			dtValidateDataEnd.setDisabled(true);
			dtValidateDataEnd.setShowDisabled(false);
			dtValidateDataEnd.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.validateDataEnd())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.validateDataEnd())));
				dtValidateDataEnd.setValue(record.getAttributeAsString(costanti
						.validateDataEnd()));
			}
			dtValidateDataEnd.setWidth("*");
		}
		return dtValidateDataEnd;
	}

	private TextItem getTValidateEsito() {
		if (tValidateEsito == null) {
			tValidateEsito = new TextItem(costanti.validateEsito(),
					costanti.validateEsito_title());
			tValidateEsito.setDisabled(true);
			tValidateEsito.setShowDisabled(false);
			if (record != null) {
				if(record.getAttributeAsBoolean(costanti.validateEsito())){
					tValidateEsito.setValue(costanti.esitoPositivo());
				}else {
					tValidateEsito.setValue(costanti.esitoNegativo());
				}
			}
			tValidateEsito.setWidth("*");
		}
		return tValidateEsito;
	}

	private DateTimeItem getDtDecompDataStart() {
		if (dtDecompDataStart == null) {
			dtDecompDataStart = new DateTimeItem(costanti.decompDataStart(),
					costanti.decompDataStart_title());
			dtDecompDataStart.setDisabled(true);
			dtDecompDataStart.setShowDisabled(false);
			dtDecompDataStart.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.decompDataStart())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.decompDataStart())));
				dtDecompDataStart.setValue(record.getAttributeAsString(costanti
						.decompDataStart()));
			}
			dtDecompDataStart.setWidth("*");
		}
		return dtDecompDataStart;
	}

	private DateTimeItem getDtDecompDataEnd() {
		if (dtDecompDataEnd == null) {
			dtDecompDataEnd = new DateTimeItem(costanti.decompDataEnd(),
					costanti.decompDataEnd_title());
			dtDecompDataEnd.setDisabled(true);
			dtDecompDataEnd.setShowDisabled(false);
			dtDecompDataEnd.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.decompDataEnd())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.decompDataEnd())));
				dtDecompDataEnd.setValue(record.getAttributeAsString(costanti
						.decompDataEnd()));
			}
			dtDecompDataEnd.setWidth("*");
		}
		return dtDecompDataEnd;
	}

	private TextItem getTDecompEsito() {
		if (tDecompEsito == null) {
			tDecompEsito = new TextItem(costanti.decompEsito(),
					costanti.decompEsito_title());
			tDecompEsito.setDisabled(true);
			tDecompEsito.setShowDisabled(false);
			if (record != null) {
				if(record.getAttributeAsBoolean(costanti.decompEsito())){
					tDecompEsito.setValue(costanti.esitoPositivo());
				}else {
					tDecompEsito.setValue(costanti.esitoNegativo());
				}
			}
			tDecompEsito.setWidth("*");
		}
		return tDecompEsito;
	}

	private DateTimeItem getDtPublishDataStart() {
		if (dtPublishDataStart == null) {
			dtPublishDataStart = new DateTimeItem(costanti.publishDataStart(),
					costanti.publishDataStart_title());
			dtPublishDataStart.setDisabled(true);
			dtPublishDataStart.setShowDisabled(false);
			dtPublishDataStart.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.publishDataStart())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.publishDataStart())));
				dtPublishDataStart.setValue(record.getAttributeAsString(costanti
						.publishDataStart()));
			}
			dtPublishDataStart.setWidth("*");
		}
		return dtPublishDataStart;
	}

	private DateTimeItem getDtPublishDataEnd() {
		if (dtPublishDataEnd == null) {
			dtPublishDataEnd = new DateTimeItem(costanti.publishDataEnd(),
					costanti.publishDataEnd_title());
			dtPublishDataEnd.setDisabled(true);
			dtPublishDataEnd.setShowDisabled(false);
			dtPublishDataEnd.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.publishDataEnd())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.publishDataEnd())));
				dtPublishDataEnd.setValue(record.getAttributeAsString(costanti
						.publishDataEnd()));
			}
			dtPublishDataEnd.setWidth("*");
		}
		return dtPublishDataEnd;
	}

	private TextItem getTPublishEsito() {
		if (tPublishEsito == null) {
			tPublishEsito = new TextItem(costanti.publishEsito(),
					costanti.publishEsito_title());
			tPublishEsito.setDisabled(true);
			tPublishEsito.setShowDisabled(false);
			if (record != null) {
				if(record.getAttributeAsBoolean(costanti.publishEsito())){
					tPublishEsito.setValue(costanti.esitoPositivo());
				}else {
					tPublishEsito.setValue(costanti.esitoNegativo());
				}
			}
			tPublishEsito.setWidth("*");
		}
		return tPublishEsito;
	}

	private DateTimeItem getDtCopyPremisDataStart() {
		if (dtCopyPremisDataStart == null) {
			dtCopyPremisDataStart = new DateTimeItem(costanti.copyPremisDataStart(),
					costanti.copyPremisDataStart_title());
			dtCopyPremisDataStart.setDisabled(true);
			dtCopyPremisDataStart.setShowDisabled(false);
			dtCopyPremisDataStart.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.copyPremisDataStart())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.copyPremisDataStart())));
				dtCopyPremisDataStart.setValue(record.getAttributeAsString(costanti
						.copyPremisDataStart()));
			}
			dtCopyPremisDataStart.setWidth("*");
		}
		return dtCopyPremisDataStart;
	}

	private DateTimeItem getDtCopyPremisDataEnd() {
		if (dtCopyPremisDataEnd == null) {
			dtCopyPremisDataEnd = new DateTimeItem(costanti.copyPremisDataEnd(),
					costanti.copyPremisDataEnd_title());
			dtCopyPremisDataEnd.setDisabled(true);
			dtCopyPremisDataEnd.setShowDisabled(false);
			dtCopyPremisDataEnd.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.copyPremisDataEnd())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.copyPremisDataEnd())));
				dtCopyPremisDataEnd.setValue(record.getAttributeAsString(costanti
						.copyPremisDataEnd()));
			}
			dtCopyPremisDataEnd.setWidth("*");
		}
		return dtCopyPremisDataEnd;
	}

	private TextItem getTCopyPremisEsito() {
		if (tCopyPremisEsito == null) {
			tCopyPremisEsito = new TextItem(costanti.copyPremisEsito(),
					costanti.copyPremisEsito_title());
			tCopyPremisEsito.setDisabled(true);
			tCopyPremisEsito.setShowDisabled(false);
			if (record != null) {
				if(record.getAttributeAsBoolean(costanti.copyPremisEsito())){
					tCopyPremisEsito.setValue(costanti.esitoPositivo());
				}else {
					tCopyPremisEsito.setValue(costanti.esitoNegativo());
				}
			}
			tCopyPremisEsito.setWidth("*");
		}
		return tCopyPremisEsito;
	}

	private DateTimeItem getDtMoveFileDataStart() {
		if (dtMoveFileDataStart == null) {
			dtMoveFileDataStart = new DateTimeItem(costanti.moveFileDataStart(),
					costanti.moveFileDataStart_title());
			dtMoveFileDataStart.setDisabled(true);
			dtMoveFileDataStart.setShowDisabled(false);
			dtMoveFileDataStart.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.moveFileDataStart())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.moveFileDataStart())));
				dtMoveFileDataStart.setValue(record.getAttributeAsString(costanti
						.moveFileDataStart()));
			}
			dtMoveFileDataStart.setWidth("*");
		}
		return dtMoveFileDataStart;
	}

	private DateTimeItem getDtMoveFileDataEnd() {
		if (dtMoveFileDataEnd == null) {
			dtMoveFileDataEnd = new DateTimeItem(costanti.moveFileDataEnd(),
					costanti.moveFileDataEnd_title());
			dtMoveFileDataEnd.setDisabled(true);
			dtMoveFileDataEnd.setShowDisabled(false);
			dtMoveFileDataEnd.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.moveFileDataEnd())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.moveFileDataEnd())));
				dtMoveFileDataEnd.setValue(record.getAttributeAsString(costanti
						.moveFileDataEnd()));
			}
			dtMoveFileDataEnd.setWidth("*");
		}
		return dtMoveFileDataEnd;
	}

	private TextItem getTMoveFileEsito() {
		if (tMoveFileEsito == null) {
			tMoveFileEsito = new TextItem(costanti.moveFileEsito(),
					costanti.moveFileEsito_title());
			tMoveFileEsito.setDisabled(true);
			tMoveFileEsito.setShowDisabled(false);
			if (record != null) {
				if(record.getAttributeAsBoolean(costanti.moveFileEsito())){
					tMoveFileEsito.setValue(costanti.esitoPositivo());
				}else {
					tMoveFileEsito.setValue(costanti.esitoNegativo());
				}
			}
			tMoveFileEsito.setWidth("*");
		}
		return tMoveFileEsito;
	}

	private DateTimeItem getDtDeleteLocalDataStart() {
		if (dtDeleteLocalDataStart == null) {
			dtDeleteLocalDataStart = new DateTimeItem(costanti.deleteLocalDataStart(),
					costanti.deleteLocalDataStart_title());
			dtDeleteLocalDataStart.setDisabled(true);
			dtDeleteLocalDataStart.setShowDisabled(false);
			dtDeleteLocalDataStart.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.deleteLocalDataStart())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.deleteLocalDataStart())));
				dtDeleteLocalDataStart.setValue(record.getAttributeAsString(costanti
						.deleteLocalDataStart()));
			}
			dtDeleteLocalDataStart.setWidth("*");
		}
		return dtDeleteLocalDataStart;
	}

	private DateTimeItem getDtDeleteLocalDataEnd() {
		if (dtDeleteLocalDataEnd == null) {
			dtDeleteLocalDataEnd = new DateTimeItem(costanti.deleteLocalDataEnd(),
					costanti.deleteLocalDataEnd_title());
			dtDeleteLocalDataEnd.setDisabled(true);
			dtDeleteLocalDataEnd.setShowDisabled(false);
			dtDeleteLocalDataEnd.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
			if (record != null &&
					record.getAttributeAsString(costanti
							.deleteLocalDataEnd())!=null) {
//				Date date=null;
//				date = new Date(Long.parseLong(record.getAttributeAsString(costanti
//						.deleteLocalDataEnd())));
				dtDeleteLocalDataEnd.setValue(record.getAttributeAsString(costanti
						.deleteLocalDataEnd()));
			}
			dtDeleteLocalDataEnd.setWidth("*");
		}
		return dtDeleteLocalDataEnd;
	}

	private TextItem getTDeleteLocalEsito() {
		if (tDeleteLocalEsito == null) {
			tDeleteLocalEsito = new TextItem(costanti.deleteLocalEsito(),
					costanti.deleteLocalEsito_title());
			tDeleteLocalEsito.setDisabled(true);
			tDeleteLocalEsito.setShowDisabled(false);
			if (record != null) {
				if(record.getAttributeAsBoolean(costanti.deleteLocalEsito())){
					tDeleteLocalEsito.setValue(costanti.esitoPositivo());
				}else {
					tDeleteLocalEsito.setValue(costanti.esitoNegativo());
				}
			}
			tDeleteLocalEsito.setWidth("*");
		}
		return tDeleteLocalEsito;
	}

	private TextItem getTXmlMimeType() {
		if (tXmlMimeType == null) {
			tXmlMimeType = new TextItem(costanti.xmlMimeType(),
					costanti.xmlMimeType_title());
			tXmlMimeType.setDisabled(true);
			tXmlMimeType.setShowDisabled(false);
			if (record != null) {
				tXmlMimeType.setValue(record.getAttributeAsString(costanti
						.xmlMimeType()));
			}
			tXmlMimeType.setWidth("*");
		}
		return tXmlMimeType;
	}

	private TextItem getTPremisFile() {
		if (tPremisFile == null) {
			tPremisFile = new TextItem(costanti.premisFile(),
					costanti.premisFile_title());
			tPremisFile.setDisabled(true);
			tPremisFile.setShowDisabled(false);
			tPremisFile.setColSpan(3);
			if (record != null) {
				tPremisFile.setValue(record.getAttributeAsString(costanti
						.premisFile()));
			}
			tPremisFile.setWidth("*");
		}
		return tPremisFile;
	}

	@Override
	public void init() {
		if (costanti == null) {
			costanti = GWT.create(SearchConstants.class);
		}
		if (messaggi == null) {
			messaggi = GWT.create(SearchMessages.class);
		}

	}

	@Override
	protected void initEditDialog() {
	}

}
