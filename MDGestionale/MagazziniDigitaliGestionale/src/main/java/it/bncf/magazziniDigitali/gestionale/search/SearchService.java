/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.search;

import it.bncf.magazziniDigitali.utils.Record;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author massi
 *
 */
@RemoteServiceRelativePath("servlet/search")
public interface SearchService extends RemoteService {

	Vector<Record> find(String idIstituto, String nomeFile) throws Exception;
}
