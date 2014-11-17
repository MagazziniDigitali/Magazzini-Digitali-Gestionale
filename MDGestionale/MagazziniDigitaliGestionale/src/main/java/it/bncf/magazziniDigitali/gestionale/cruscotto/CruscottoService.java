/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.cruscotto;

import java.util.TreeMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author massi
 *
 */
@RemoteServiceRelativePath("servlet/cruscotto")
public interface CruscottoService extends RemoteService {

	TreeMap<String, Integer> findStatus(String idIstituto) throws Exception;
	
	TreeMap<String, Object> checkIstituto(String idIstituto) throws Exception;
}
