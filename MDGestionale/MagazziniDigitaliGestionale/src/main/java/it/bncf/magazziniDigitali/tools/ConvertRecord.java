/**
 * 
 */
package it.bncf.magazziniDigitali.tools;

import it.bncf.magazziniDigitali.utils.Record;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author massi
 *
 */
public class ConvertRecord {

	private static ConvertRecordMessages messages = GWT.create(ConvertRecordMessages.class);
	
	public static ListGridRecord[] convert(Vector<Record> dati, int start, int stop){
		ListGridRecord[] data = null;
		Record record;
		int x=0;

		if (dati != null){
			data = new ListGridRecord[(stop-(start-1))];
			for (int y=(start-1);y<stop; y++){
				record = dati.get(y);
				data[x]= new ListGridRecord();
				data[x] = convert(record, data[x]);
				x++;
			}
		} else {
			data = new ListGridRecord[0];
		}
		return data;
	}

	private static ListGridRecord convert(Record record, ListGridRecord value){
		Record[] records = null;
		for(String key :record.key()){
//			String key = record.key().nextElement();

			if (!record.isEmpty(key)){
				if (record.isString(key)){
					if (record.getStrings(key) != null){
						value.setAttribute(key, record.getStrings(key));
					} else {
						Window.alert(messages.formatNotFound(key));
					}
				}else if (record.isInteger(key)){
					if (record.getInteger(key) != null){
						value.setAttribute(key, record.getInteger(key).toString());
					} else {
						Window.alert(messages.formatNotFound(key));
					}
				}else if (record.isDate(key)){
					if (record.getDate(key) != null){
						value.setAttribute(key, record.getDate(key));
					} else {
						Window.alert(messages.formatNotFound(key));
					}
				}else if (record.isBoolean(key)){
					if (record.getBoolean(key) != null){
						value.setAttribute(key, record.getBoolean(key));
					} else {
						Window.alert(messages.formatNotFound(key));
					}
				} else {
					records = record.getRecords(key);
					for (int x=0; x<records.length; x++){
						convert(records[x], value);
					}
				}
			}
		}
		return value;
	}
}
