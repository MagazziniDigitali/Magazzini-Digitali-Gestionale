/**
 * 
 */
package it.bncf.magazziniDigitali.gestionale.search;

import it.bncf.magazziniDigitali.tools.graphics.Costanti;

/**
 * @author massi
 *
 */
public interface SearchConstants extends Costanti {

	@DefaultStringValue("idMDFilesTmp")
	String id();
	
	@DefaultStringValue("nomeFile")
	String descrizione();

	@DefaultStringValue("nomeFile")
	String nomeFile();
	String nomeFile_title();
	
	String editTitle();

	@DefaultStringValue("nomeFileMod")
	String nomeFileMod();
	String nomeFileMod_title();

	@DefaultStringValue("stato")
	String stato();
	String stato_title();

	@DefaultStringValue("trasfDataStart")
	String trasfDataStart();
	String trasfDataStart_title();

	@DefaultStringValue("trasfDataEnd")
	String trasfDataEnd();
	String trasfDataEnd_title();

	@DefaultStringValue("trasfEsito")
	String trasfEsito();
	String trasfEsito_title();

	@DefaultStringValue("validDataStart")
	String validateDataStart();
	String validateDataStart_title();

	@DefaultStringValue("validDataEnd")
	String validateDataEnd();
	String validateDataEnd_title();

	@DefaultStringValue("validEsito")
	String validateEsito();
	String validateEsito_title();

	@DefaultStringValue("decompDataStart")
	String decompDataStart();
	String decompDataStart_title();

	@DefaultStringValue("decompDataEnd")
	String decompDataEnd();
	String decompDataEnd_title();

	@DefaultStringValue("decompEsito")
	String decompEsito();
	String decompEsito_title();

	@DefaultStringValue("publishDataStart")
	String publishDataStart();
	String publishDataStart_title();

	@DefaultStringValue("publishDataEnd")
	String publishDataEnd();
	String publishDataEnd_title();

	@DefaultStringValue("publishEsito")
	String publishEsito();
	String publishEsito_title();

	@DefaultStringValue("copyPremisDataStart")
	String copyPremisDataStart();
	String copyPremisDataStart_title();

	@DefaultStringValue("copyPremisDataEnd")
	String copyPremisDataEnd();
	String copyPremisDataEnd_title();

	@DefaultStringValue("copyPremisEsito")
	String copyPremisEsito();
	String copyPremisEsito_title();

	@DefaultStringValue("moveFileDataStart")
	String moveFileDataStart();
	String moveFileDataStart_title();

	@DefaultStringValue("moveFileDataEnd")
	String moveFileDataEnd();
	String moveFileDataEnd_title();

	@DefaultStringValue("moveFileEsito")
	String moveFileEsito();
	String moveFileEsito_title();

	@DefaultStringValue("deleteLocalDataStart")
	String deleteLocalDataStart();
	String deleteLocalDataStart_title();

	@DefaultStringValue("deleteLocalDataEnd")
	String deleteLocalDataEnd();
	String deleteLocalDataEnd_title();

	@DefaultStringValue("deleteLocalEsito")
	String deleteLocalEsito();
	String deleteLocalEsito_title();

	String esitoPositivo();
	String esitoNegativo();

	@DefaultStringValue("xmlMimeType")
	String xmlMimeType();
	String xmlMimeType_title();

	@DefaultStringValue("premisFile")
	String premisFile();
	String premisFile_title();

	@DefaultStringValue("bDownloadFile")
	String bDownloadFile();
	String bDownloadFile_title();

	String statoINITTRASF();

	String statoFINETRASF();

	String statoERROR();

	String statoERRORTRASF();

	String statoERRORVAL();

	String statoERRORDECOMP();

	String statoERRORCOPY();

	String statoERRORMOVE();

	String statoERRORPUB();

	String statoERRORDELETE();

	String statoINITVALID();

	String statoFINEVALID();

	String statoINITPUBLISH();

	String statoFINEPUBLISH();

	String modifica_title();

}
