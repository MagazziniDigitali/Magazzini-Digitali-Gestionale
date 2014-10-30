/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.server;

import it.bncf.magazziniDigitali.database.dao.MDIstituzioneDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.utils.GenFileDest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 * 
 */
public class DownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3780474731825468209L;

	/**
	 * Dati relativi alla connessione Hibernate ricavato dallo Spring
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 
	 */
	public DownloadServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDownload(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDownload(req, resp);
	}

	private void doDownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File fObjNew = null;
		String file = null;
		int BUFFER = 1024 * 100;
		byte[] buffer = new byte[BUFFER];
		InputStream istream = null;
		ServletOutputStream ostream = null;
		int bytesRead = 0;

		try {
			if (checkIP(req)){
				file = req.getParameter("file");
	
				fObjNew = GenFileDest.genFileDest(Configuration.getValue("demoni.Publish.pathStorage"), file);
	
				if (fObjNew.exists()) {
					try {
						resp.setContentType("application/octet-stream");
						resp.setHeader("Content-Description", "File Transfer");
						resp.setHeader("Content-Transfer-Encoding","binary");
						resp.setHeader("Content-Disposition",
								"attachment; filename=" + "\"" + 
										(req.getParameter("fileOri") ==null?
												fObjNew.getName():
													req.getParameter("fileOri"))// Mettere il nome del file originale
										+ "\"");
						resp.setHeader("Expires","0");
						resp.setHeader("Cache-Control","must-revalidate");
						resp.setHeader("Pragma:","public");
						
						ostream = resp.getOutputStream();
						resp.setContentLength(Long.valueOf(fObjNew.length())
								.intValue());
						resp.setBufferSize(BUFFER);
						// Your IO code goes here to create a file and set to
						// outputStream//
						istream = new FileInputStream(fObjNew);
						while ((bytesRead = istream.read(buffer)) > 0) {
							ostream.write(buffer, 0, bytesRead);
						}
					} catch (IOException e) {
						throw e;
					} finally {
						try {
							if (istream != null){
								istream.close();
							}
							if (ostream != null) {
								ostream.flush();
								ostream.close();
							}
						} catch (IOException e) {
							throw e;
						}
					}
				} else {
					resp.getWriter()
							.print(printPage("<h1>Il file richiesto non &egrave presente</h1>"));
				}
			} else {
				resp.getWriter()
				.print(printPage("<h1>Non sei autorizzato a scaricare il file indicato</h1>"));
			}
		} catch (HibernateException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (IOException e) {
			throw e;
		}
	}

	private String printPage(String msg){
		String page = "";
		page += "<html>\n";
		page += "<head>\n";
		page += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
		page += "<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css\" />\n";
		page += "<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\n";
		page += "<script src=\"http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js\"></script>\n";
		page += "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n";
		page += "<meta name=\"gwt:property\" content=\"locale=it\">\n";
		page += "<link type=\"text/css\" rel=\"stylesheet\" href=\"index.css\">\n";
		page += "<title>Magazzini Digitale - Gestionale</title>\n";
		page += "<script type=\"text/javascript\" language=\"javascript\" src=\"./index/index.nocache.js\"></script>\n";
		page += "<link href=\"./style/mdGestionale.css\" type=\"text/css\" rel=\"stylesheet\"/>\n";
		page += "</head>\n";
		page += "<body>\n";
		page += "<iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" tabIndex='-1' style=\"position:absolute;width:0;height:0;border:0\">xxx</iframe>\n";
		page += "<noscript>\n";
		page += "<div style=\"width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif\">\n";
		page += "Your web browser must have JavaScript enabled\n";
		page += "in order for this application to display correctly.\n";
		page += "</div>\n";
		page += "</noscript>\n";
		page += "<div id=\"header\" class=\"header\">\n";
		page += "<a href=\"http://www.depositolegale.it/\" class=\"lMagazziniDigitali\">\n";
		page += "<img alt=\"Magazzini Digitali\" title=\"Magazzini Digitali\" src=\"https://upload.wikimedia.org/wikipedia/commons/3/35/Crystal_Clear_device_blockdevice.png\" class=\"iMagazziniDigitali\"/>\n";
		page += "</a>\n";
		page += "</div>\n";
		page += "<div id=\"center\" class=\"center\">";
		page += msg;
		page += "</div>\n";
		page += "<div id=\"footer\" class=\"footer\">&copy; Magazzini digitali 2014<br/>Ver. 1.0.3</div>\n";
		page += "</body>\n";
		page += "</html>\n";
		return page;
	}

	private boolean checkIP(HttpServletRequest request) throws ServletException{
		InetAddress address =  null;
		String ip = null;
		String[] stClient;
		String[] st;
		String[] stAuthor;
		boolean found = false;
		MDIstituzioneDAO mdIstituzioneDAO = null;
		MDIstituzione mdIstituzione = null;

		try {
			if (request.getHeader("x-forwarded-for") != null) {
				address = InetAddress.getByName(request.getHeader("x-forwarded-for"));
			} else {
				address = InetAddress.getByName(request.getRemoteAddr());
			}
			ip = address.getHostAddress();

			stClient = ip.split("\\.");
			mdIstituzioneDAO = new MDIstituzioneDAO(hibernateTemplate);
			mdIstituzione = mdIstituzioneDAO.findById(request.getParameter("Istituto"));
			if (mdIstituzione != null){
				if (mdIstituzione.getIpDownload()!= null && !mdIstituzione.getIpDownload().trim().equals("")){
					st = mdIstituzione.getIpDownload().trim().split(",");
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
				}
			}
		} catch (UnknownHostException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new ServletException(e.getMessage(), e);
		}
		return found;
	}
}
