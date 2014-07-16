/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.server;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.gestionale.cruscotto.CruscottoService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.randalf.configuration.Configuration;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;

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
		
		odb = new OggettoDigitaleBusiness();
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

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	public String processCall(String payload) throws SerializationException {
		// TODO Auto-generated method stub
		return super.processCall(payload);
	}

	@Override
	protected void checkPermutationStrongName() throws SecurityException {
		// TODO Auto-generated method stub
		super.checkPermutationStrongName();
	}

	@Override
	protected SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		// TODO Auto-generated method stub
		return super.doGetSerializationPolicy(request, moduleBaseURL, strongName);
	}

	@Override
	protected String getCodeServerPolicyUrl(String strongName) {
		// TODO Auto-generated method stub
		return super.getCodeServerPolicyUrl(strongName);
	}

	@Override
	protected SerializationPolicy loadPolicyFromCodeServer(String url) {
		// TODO Auto-generated method stub
		return super.loadPolicyFromCodeServer(url);
	}

	@Override
	protected void onAfterResponseSerialized(String serializedResponse) {
		// TODO Auto-generated method stub
		super.onAfterResponseSerialized(serializedResponse);
	}

	@Override
	protected void onBeforeRequestDeserialized(String serializedRequest) {
		// TODO Auto-generated method stub
		super.onBeforeRequestDeserialized(serializedRequest);
	}

	@Override
	protected boolean shouldCompressResponse(HttpServletRequest request,
			HttpServletResponse response, String responsePayload) {
		// TODO Auto-generated method stub
		return super.shouldCompressResponse(request, response, responsePayload);
	}

	@Override
	protected void doUnexpectedFailure(Throwable e) {
		// TODO Auto-generated method stub
		super.doUnexpectedFailure(e);
	}

	@Override
	protected void onAfterRequestDeserialized(RPCRequest rpcRequest) {
		// TODO Auto-generated method stub
		super.onAfterRequestDeserialized(rpcRequest);
	}

	@Override
	protected String readContent(HttpServletRequest request)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return super.readContent(request);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(req, resp);
	}

	@Override
	protected void doOptions(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(arg0, arg1);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
	}

	@Override
	protected long getLastModified(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return super.getLastModified(req);
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return super.getInitParameter(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		return super.getInitParameterNames();
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return super.getServletConfig();
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return super.getServletContext();
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return super.getServletInfo();
	}

	@Override
	public String getServletName() {
		// TODO Auto-generated method stub
		return super.getServletName();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
		super.log(msg);
	}

	@Override
	public void log(String message, Throwable t) {
		// TODO Auto-generated method stub
		super.log(message, t);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
}
