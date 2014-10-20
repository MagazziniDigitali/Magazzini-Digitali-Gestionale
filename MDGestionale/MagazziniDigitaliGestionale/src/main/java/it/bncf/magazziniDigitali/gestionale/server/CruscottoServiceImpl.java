/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.server;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.gestionale.cruscotto.CruscottoService;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import mx.randalf.configuration.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author massi
 *
 */
@SuppressWarnings("serial")
@Service("cruscotto")
@Transactional
public class CruscottoServiceImpl extends RemoteServiceServlet implements
		CruscottoService {

	/**
	 * Dati relativi alla connessione Hibernate ricavato dallo Spring
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 
	 */
	public CruscottoServiceImpl() {
	}

	/**
	 * @param delegate
	 */
	public CruscottoServiceImpl(Object delegate) {
		super(delegate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.gestionale.cruscotto.CruscottoService#findStatus(java.lang.String)
	 */
	@Override
	public TreeMap<String, Integer> findStatus(String idIstituto) throws Exception {
		OggettoDigitaleBusiness odb = null;
		
		odb = new OggettoDigitaleBusiness(hibernateTemplate);
		return odb.findStatus(idIstituto);
	}

	@Override
	public TreeMap<String, Object> checkIstituto(String idIstituto)
			throws Exception {
		TreeMap<String, Object> ris = null;
		InetAddress address = null;
		boolean found= false;
		String ip = null;
		String[] st= null;
		String[] stClient = null;
		String[] stAuthor = null;
		HttpServletRequest request = null;

		request = this.getThreadLocalRequest();
		//getThreadLocalRequest();
		if (request != null){
			if (request.getHeader("x-forwarded-for") != null) {
				address = InetAddress.getByName(request.getHeader("x-forwarded-for"));
			} else {
				address = InetAddress.getByName(request.getRemoteAddr());
			}
		} else {
			for (final Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
			    final NetworkInterface intf = en.nextElement();
			    for (final Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
			        final InetAddress ip2 = enumIpAddr.nextElement();
			        if (!ip2.isLoopbackAddress() && !ip2.isLinkLocalAddress() && !ip2.isAnyLocalAddress()) {
			                ip = ip2.getHostAddress().toString();
			        }
			    }
			}
			address = InetAddress.getLocalHost();
		}
        ip = address.getHostAddress();

        stClient = ip.split("\\.");

        ris = new TreeMap<String, Object>();
		ris.put("IpClient", ip);

		if (Configuration.getValue("istituto."+idIstituto+".nome")!= null){
			ris.put("Nome", Configuration.getValue("istituto."+idIstituto+".nome"));
			ris.put("Url", Configuration.getValue("istituto."+idIstituto+".url"));
			ris.put("Logo", Configuration.getValue("istituto."+idIstituto+".urlLogo"));
			if (Configuration.getValue("istituto."+idIstituto+".ipAuthentication")!= null){
				st = Configuration.getValue("istituto."+idIstituto+".ipAuthentication").split(",");
				for(int x=0; x<st.length; x++){
					stAuthor = st[x].split("\\.");
					if ((stAuthor[0].equals("*")||
							stAuthor[0].equals(stClient[0])) &&
						(stAuthor[1].equals("*")||
								stAuthor[1].equals(stClient[1])) &&
						(stAuthor[2].equals("*")||
								stAuthor[2].equals(stClient[2])) &&
						(stAuthor[3].equals("*")||
								stAuthor[3].equals(stClient[3]))){
						found = true;
						break;
					}
				}
				if (!found){
					ris.put("ERROR", "ipUnAuthorized");
				}
			} else {
				ris.put("ERROR", "ipNotFound");
			}
		} else {
			ris.put("ERROR", "istitutoNotFound");
		}
		return ris;
	}
}
