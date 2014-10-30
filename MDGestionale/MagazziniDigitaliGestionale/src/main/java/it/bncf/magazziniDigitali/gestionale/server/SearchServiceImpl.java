/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.server;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.database.dao.MDIstituzioneDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
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
	 * Dati relativi alla connessione Hibernate ricavato dallo Spring
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

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
		MDIstituzioneDAO mdIstituzioneDAO = null;
		MDIstituzione mdIstituzione = null;
		
		mdIstituzioneDAO = new MDIstituzioneDAO(hibernateTemplate);
		mdIstituzione = mdIstituzioneDAO.findById(idIstituto);
		opere = new OggettoDigitaleBusiness(hibernateTemplate);
		result = opere.findByNomeFile(mdIstituzione, nomeFile);
		return result;
	}

}
