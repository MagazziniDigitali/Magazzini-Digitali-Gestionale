/**
 * 
 */
package it.bncf.magazziniDigitali.configuration;

import java.io.File;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

/**
 * @author massi
 *
 */
public class MDConfiguration {

	private String fileConfiguration = null;

	/**
	 * 
	 */
	public MDConfiguration() {
	}

	public String getFileConfiguration() {
		return fileConfiguration;
	}

	public void setFileConfiguration(String fileConfiguration) {
		String pathProperties = null;
		String[] st = null;
		File path = null;
		try {
			this.fileConfiguration = fileConfiguration;
			
			if (!Configuration.isInizialize()) {
				st = fileConfiguration.split("\\|");
				for (int x=0; x<st.length; x++){
					fileConfiguration = st[x];

					pathProperties = null;
					if (fileConfiguration != null && fileConfiguration.startsWith("file://"))
						pathProperties = fileConfiguration.replace("file:///", "");
					else {
						if (System.getProperty("catalina.base") != null){
							pathProperties = System.getProperty("catalina.base")
									+ File.separator;
							if (fileConfiguration == null)
								pathProperties += "conf/teca_digitale";
							else
								pathProperties += fileConfiguration;
						}
					}
	
					if (pathProperties != null){
						path = new File(pathProperties);
						if (path.exists()){
							Configuration.init(path.getAbsolutePath());
							break;
						}
					}
				}
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

}
