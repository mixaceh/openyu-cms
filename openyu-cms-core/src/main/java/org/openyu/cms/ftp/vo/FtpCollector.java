package org.openyu.cms.ftp.vo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.ftp.vo.adapter.StringFtpXmlAdapter;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.io.IoHelper;
import org.openyu.commons.lang.StringHelper;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "ftpCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class FtpCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = -1442802114270746511L;

	private static FtpCollector ftpCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設FTP
	 */
	private String defaultFtp = "DEFAULT";

	/**
	 * 所有FTP
	 * 
	 * <id,ftp>
	 */
	@XmlJavaTypeAdapter(StringFtpXmlAdapter.class)
	private Map<String, Ftp> ftps = new LinkedHashMap<String, Ftp>();

	/**
	 * 編碼選項
	 */
	private List<String> encodingOptions = new LinkedList<String>();

	public FtpCollector() {
		setId(FtpCollector.class.getName());
	}

	public synchronized static FtpCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static FtpCollector getInstance(boolean initial) {
		if (ftpCollector == null) {
			ftpCollector = new FtpCollector();
			if (initial) {
				ftpCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return ftpCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!ftpCollector.isInitialized()) {
			ftpCollector = readFromSer(FtpCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (ftpCollector == null) {
				ftpCollector = new FtpCollector();
			}
			//
			ftpCollector.setInitialized(true);
		}
	}

	public void clear() {
		ftps.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getDefaultFtp() {
		return defaultFtp;
	}

	public void setDefaultFtp(String defaultFtp) {
		this.defaultFtp = defaultFtp;
	}

	public Map<String, Ftp> getFtps() {
		if (ftps == null) {
			ftps = new LinkedHashMap<String, Ftp>();
		}
		return ftps;
	}

	public void setFtps(Map<String, Ftp> ftps) {
		this.ftps = ftps;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------

	/**
	 * 編碼選項
	 * 
	 * @return
	 */
	public List<String> getEncodingOptions() {
		if (encodingOptions == null) {
			encodingOptions = new LinkedList<String>();
		}
		return encodingOptions;
	}

	public void setEncodingOptions(List<String> encodingOptions) {
		this.encodingOptions = encodingOptions;
	}

	// --------------------------------------------------
	/**
	 * 建構查詢條件
	 * 
	 * @return
	 */
	public Inquiry createInquiry() {
		return (inquiry != null ? (Inquiry) inquiry.clone() : null);
	}

	/**
	 * 取得cloneFTP
	 * 
	 * @param id
	 * @return
	 */
	public Ftp getFtp(String id) {
		Ftp result = null;
		if (id != null) {
			result = ftps.get(id);
		}
		return (result != null ? (Ftp) result.clone() : null);
	}

	public Ftp createFtp() {
		return createFtp(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Ftp createFtp(String id) {
		Ftp result = null;
		// 來自靜態資料的clone副本
		result = getFtp(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new FtpImpl(StringHelper.randomUnique());// 1361579JmbDESVea
		} else {
			// xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());// DEFAULT_1361579JmbDESVea
		}
		return result;
	}

}