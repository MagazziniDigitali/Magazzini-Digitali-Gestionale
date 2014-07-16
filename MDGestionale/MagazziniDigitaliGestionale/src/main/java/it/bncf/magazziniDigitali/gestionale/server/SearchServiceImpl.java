/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.server;

import java.util.Vector;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.gestionale.search.SearchService;
import it.bncf.magazziniDigitali.utils.Record;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author massi
 *
 */
@SuppressWarnings("serial")
@Service("search")
@Transactional
public class SearchServiceImpl extends RemoteServiceServlet implements
		SearchService {

	/**
	 * 
	 */
	public SearchServiceImpl() {
	}

	/**
	 * @param delegate
	 */
	public SearchServiceImpl(Object delegate) {
		super(delegate);
	}

	/* (non-Javadoc)
	 * @see it.bncf.magazziniDigitali.gestionale.search.SearchService#find(java.lang.String, java.lang.String)
	 */
	@Override
	public Vector<Record> find(String idIstituto, String nomeFile)
			throws Exception {
		OggettoDigitaleBusiness opere = null;
		Vector<Record> result = null;
		
		opere = new OggettoDigitaleBusiness();
		result = opere.findByNomeFile(idIstituto, nomeFile);

		return result;
	}

}
