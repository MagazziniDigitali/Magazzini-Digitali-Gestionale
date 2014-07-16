/**
 * 
 */
package it.bncf.magazziniDigitali.tools.graphics;

import it.bncf.magazziniDigitali.tools.Navigator;

import java.util.Collection;
import java.util.Vector;

import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

/**
 * @author massi
 *
 */
public abstract class Show<M extends Messaggi, C extends Costanti> 
	extends Finestra<M, C> {

	protected Show<M, C> show; 

	protected ListGrid gCenter;
	
	protected Navigator pFooter;

	protected SearchForm searchForm;

	protected ButtonSearch bSearch;

	protected ButtonClear bClear;
	protected ClickHandler chClear;

//	protected ButtonNew bNew;
	protected ClickHandler chNew;

	protected SortSpecifier[] sort;

	protected String[] groupBy;

	private CellEdit modifica;
	protected RecordClickHandler rchModifica;

//	private CellCancel cancella;
	protected RecordClickHandler rchCancel;

	/**
	 * @param utentiInfo
	 */
	public Show() {
		super();
		addItem(getSearchForm());
		addItem(getGCenter());
		addItem(getPFooter());
		search();
		this.show=this;
	}

	protected SearchForm getSearchForm() {
		Vector<FormItem> fields;
		FormItem[] lFields;
		int x=0;
		if (searchForm == null) {
			searchForm = new SearchForm();
			searchForm.setSize("100%", "30px");
			searchForm.setNumCols(6);
			fields = initSearchForm();
			fields.add(getBSearch());
			fields.add(getBClear());
//			fields.add(getBNew());
			lFields = new FormItem[fields.size()];
			for(FormItem field: fields){
				lFields[x]=field;
				x++;
			}
			searchForm.setFields(lFields);
		}
		return searchForm;
	}

	private ButtonSearch getBSearch(){
		if (bSearch==null){
			bSearch = new ButtonSearch();
			bSearch.setColSpan(2);
			bSearch.setAlign(Alignment.CENTER);
			bSearch.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					search();
				}
			});
		}
		return bSearch;
	}

	private ButtonClear getBClear(){
		if (bClear==null){
			bClear =new ButtonClear();
			bClear.setColSpan(2);
			bClear.setAlign(Alignment.CENTER);
			if (chClear != null){
				bClear.addClickHandler(chClear);
			} else {
				bClear.setDisabled(true);
			}
		}
		return bClear;
	}

//	private ButtonNew getBNew(){
//		if (bNew==null){
//			bNew = new ButtonNew();
//			bNew.setColSpan(2);
//			bNew.setAlign(Alignment.CENTER);
//			if (chNew !=null){
//				bNew.addClickHandler(chNew);
//			} else {
//				bNew.setDisabled(true);
//			}
//		}
//		return bNew;
//	}

	protected abstract Vector<FormItem> initSearchForm();

	protected ListGrid getGCenter() {
		Vector<ListGridField> fields;
		ListGridField[] lFields;
		int x=0;

		if (gCenter == null) {
			gCenter = new ListGrid();
			
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
			if (sort != null){
				gCenter.setSort(sort);
			}
			
			if (groupBy != null){
				gCenter.setGroupStartOpen (GroupStartOpen.ALL);
				gCenter.setCanGroupBy (true);
				gCenter.setGroupByField(groupBy);
			}
		}
		return gCenter;
	}

	protected abstract Collection<ListGridField> initResult();

	private CellEdit getModifica() {
		if (modifica == null) {
			modifica = new CellEdit();
			modifica.addRecordClickHandler(rchModifica);
		}
		return modifica;
	}


//	private CellCancel getCancel() {
//		if (cancella == null) {
//			cancella = new CellCancel();
//			cancella.addRecordClickHandler(rchCancel);
//		}
//		return cancella;
//	}

	private Navigator getPFooter() {
		if (pFooter == null) {
			pFooter = new Navigator(gCenter);
			pFooter.setSize("100%", "24px");
		}
		return pFooter;
	}
	
	public abstract void search();
}
