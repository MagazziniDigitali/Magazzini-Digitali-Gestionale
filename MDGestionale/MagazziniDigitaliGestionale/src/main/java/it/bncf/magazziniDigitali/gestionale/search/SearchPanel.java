/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.search;

import it.bncf.magazziniDigitali.gestionale.search.tools.SearchShowDialogEvent;
import it.bncf.magazziniDigitali.tools.Navigator;
import it.bncf.magazziniDigitali.tools.graphics.ButtonClear;
import it.bncf.magazziniDigitali.tools.graphics.ButtonSearch;
import it.bncf.magazziniDigitali.tools.graphics.CellEdit;
import it.bncf.magazziniDigitali.tools.graphics.ShowDialogEvent;
import it.bncf.magazziniDigitali.utils.Record;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

/**
 * @author massi
 *
 */
public class SearchPanel extends CaptionPanel {

	private SearchMessages messaggi = null;
	private SearchConstants costanti = null;
	
	protected Navigator pFooter;

	private TextItem tNomeFile = null;
	private ButtonSearch bSearch = null;
	private ButtonClear bClear = null;

	protected SearchForm searchForm;

	protected ListGrid gCenter;
	
	private ListGridField nomeFile;
	private ListGridField stato;

	private CellEdit modifica;
	protected RecordClickHandler rchModifica;

	private SearchServiceAsync searchService = null;

	private String idIstituto=null;
//	private CruscottoServiceAsync cruscottoService = null;

	/**
	 * 
	 */
	public SearchPanel(String idIstituto, String nomeIstituto, String ipClient) {
		super();

		this.idIstituto = idIstituto;
		messaggi = GWT.create(SearchMessages.class);
		costanti = GWT.create(SearchConstants.class);
//		cruscottoService = GWT.create(CruscottoService.class);
		this.setCaptionText(messaggi.title());

		this.getElement().setClassName("search");
		init();
		this.getContentWidget().setStyleName("cwSearch",true);
		searchService = GWT.create(SearchService.class);
		searchForm.enable();
//		cruscottoService.findStatus(idIstituto, new CruscottoServiceResult(this));
//		Scheduler.get().scheduleFixedPeriod(new CruscottoAggiorna(this, cruscottoService, idIstituto),60000);
	}

	/**
	 * Disegno il contenuto del pannello
	 */
	private void init(){
		FlowPanel fp = null;
		
		rchModifica = new SearchShowDialogEvent(this, 
				//utentiInfo, 
				messaggi, costanti, 
//				sociService, 
				ShowDialogEvent.EDIT);
		
		fp = new FlowPanel();
		fp.add(getSearchForm());
		fp.add(getGCenter());
		fp.add(getPFooter());
		add(fp);
//		search();
	}

	private Navigator getPFooter() {
		if (pFooter == null) {
			pFooter = new Navigator(gCenter);
			pFooter.setSize("100%", "24px");
		}
		return pFooter;
	}
//
//	private void init_old(){
//		SearchForm sf = null;
//		FormItem[] fields = null;
//		sf = new SearchForm();
//		sf.setStyleName("fSearch");
//		sf.setWidth("500 px");
//		sf.setColWidths("150,105, 150,105");		
//		sf.setNumCols(4);
//		
//		fields = new FormItem[3];
//		
//		fields[0] = getTNomeFile();
//		fields[1] = getBSearch();
//		fields[2] = getBClear();
//		sf.setFields(fields);
//		this.setContentWidget(sf);
//	}

	protected SearchForm getSearchForm() {
		FormItem[] lFields;
		if (searchForm == null) {
			searchForm = new SearchForm();
			searchForm.setSize("550px", "30px");
			searchForm.setNumCols(6);
			lFields = new FormItem[3];
			lFields[0] =getTNomeFile();
			lFields[1]=getBSearch();
			lFields[2]=getBClear();
			searchForm.setFields(lFields);
		}
		return searchForm;
	}

	protected ListGrid getGCenter() {
		Vector<ListGridField> fields;
		ListGridField[] lFields;
		int x=0;

		if (gCenter == null) {
			gCenter = new ListGrid();
			gCenter.setWidth("97%");
			gCenter.setHeight("300px");
			gCenter.setAlign(Alignment.CENTER);
			fields = new Vector<ListGridField>();
			if (rchModifica != null){
				fields.add(getModifica());
			}
//			if (rchCancel != null){
//				fields.add(getCancel());
//			}
			fields.addAll(initResult());
			lFields = new ListGridField[fields.size()];
			for(ListGridField field: fields){
				lFields[x]=field;
				x++;
			}
			gCenter.setFields(lFields);
//			if (sort != null){
//				gCenter.setSort(sort);
//			}
//			
//			if (groupBy != null){
//				gCenter.setGroupStartOpen (GroupStartOpen.ALL);
//				gCenter.setCanGroupBy (true);
//				gCenter.setGroupByField(groupBy);
//			}
		}
		return gCenter;
	}

	private CellEdit getModifica() {
		if (modifica == null) {
			modifica = new CellEdit();
			modifica.addRecordClickHandler(rchModifica);
		}
		return modifica;
	}

	protected Collection<ListGridField> initResult() {
		Vector<ListGridField> result;
		
		result = new Vector<ListGridField>();
		result.add(getNomeFile());
		result.add(getStato());
		return result;
	}

	private ListGridField getNomeFile() {
		if (nomeFile == null){
			nomeFile = new ListGridField(costanti.nomeFile(), costanti.nomeFile_title());
		}
		return nomeFile;
	}

	private ListGridField getStato() {
		Map<String, String> valueMap= null;
		if (this.stato == null){
			this.stato = new ListGridField(costanti.stato(), costanti.stato_title());

			valueMap= new TreeMap<String, String>();
			valueMap.put("INITTRASF",costanti.statoINITTRASF());
			valueMap.put("FINETRASF",costanti.statoFINETRASF());
			valueMap.put("ERROR",costanti.statoERROR());
			valueMap.put("ERRORTRASF",costanti.statoERRORTRASF());
			valueMap.put("ERRORVAL",costanti.statoERRORVAL());
			valueMap.put("ERRORDECOMP",costanti.statoERRORDECOMP());
			valueMap.put("ERRORCOPY",costanti.statoERRORCOPY());
			valueMap.put("ERRORMOVE",costanti.statoERRORMOVE());
			valueMap.put("ERRORPUB",costanti.statoERRORPUB());
			valueMap.put("ERRORDELETE",costanti.statoERRORDELETE());
			valueMap.put("INITVALID",costanti.statoINITVALID());
			valueMap.put("FINEVALID",costanti.statoFINEVALID());
			valueMap.put("INITPUBLISH",costanti.statoINITPUBLISH());
			valueMap.put("FINEPUBLISH",costanti.statoFINEPUBLISH());
			this.stato.setValueMap(valueMap);
		}
		return stato;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato initTrasf
	 * 
	 * @return
	 */
	public TextItem getTNomeFile(){
		if (tNomeFile ==null){
			tNomeFile = new TextItem(costanti.nomeFile(), costanti.nomeFile_title());
			tNomeFile.setValue("");
			tNomeFile.setRequired(true);
			tNomeFile.addKeyPressHandler(new KeyPressHandler() {
				
				@Override
				public void onKeyPress(KeyPressEvent event) {
					if (event.getCharacterValue() != null && 
							(event.getCharacterValue()==13 ||
							event.getCharacterValue()==10)){
						event.cancel();
						search();
					}
				}
			});
		}
		return tNomeFile;
	}

	private ButtonSearch getBSearch(){
		if (bSearch==null){
			bSearch = new ButtonSearch();
			bSearch.setColSpan(1);
			bSearch.setAlign(Alignment.CENTER);
			bSearch.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					search();
				}
			});
			System.out.println("bSearch: "+bSearch);
		}
		return bSearch;
	}

	private ButtonClear getBClear(){
		if (bClear==null){
			bClear =new ButtonClear();
			bClear.setColSpan(1);
			bClear.setAlign(Alignment.CENTER);
			bClear.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
				}
			});
		}
		return bClear;
	}
	
	private void search(){
		try {
			bSearch.setDisabled(true);
			bClear.setDisabled(true);
			searchService.find(idIstituto, tNomeFile.getValueAsString(),
					new AsyncCallback<Vector<Record>>() {
				
				@Override
				public void onSuccess(Vector<Record> result) {
					pFooter.init(result, null);
					bSearch.setDisabled(false);
					bClear.setDisabled(false);
//					bNew.setDisabled(false);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					com.google.gwt.user.client.Window.alert(caught.getMessage());
					bSearch.setDisabled(false);
					bClear.setDisabled(false);
//					bNew.setDisabled(false);
				}
			});
		} catch (Exception e) {
			com.google.gwt.user.client.Window.alert(e.getMessage());
		}
	}
}
