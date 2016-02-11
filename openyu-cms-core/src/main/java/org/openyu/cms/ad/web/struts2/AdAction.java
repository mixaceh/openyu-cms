package org.openyu.cms.ad.web.struts2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.annotations.common.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.ad.service.AdService;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.AdCollector;
import org.openyu.cms.ad.vo.AdTypeOption;
import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.cms.ad.vo.impl.AdImpl;
import org.openyu.cms.adSpace.service.AdSpaceService;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.util.CollectionHelper;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 廣告控制器
 */
@ParentPackage("default")
@Namespace("/back/service/ad")
@Results({ @Result(name = "success", type = "freemarker", location = "adList.ftl") })
public class AdAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger LOGGER = LoggerFactory
			.getLogger(AdAction.class);

	/**
	 * 廣告服務
	 */
	@Autowired
	@Qualifier("adService")
	protected transient AdService adService;

	/**
	 * 廣告版位服務
	 */
	@Autowired
	@Qualifier("adSpaceService")
	protected transient AdSpaceService adSpaceService;

	/**
	 * 靜態收集器
	 */
	protected transient AdCollector adCollector = AdCollector.getInstance();

	/**
	 * 搜尋者
	 */
	private Ad searcher = new AdImpl();

	/**
	 * 單筆資料
	 */
	private Ad ad = new AdImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Ad> ads = new LinkedList<Ad>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String adName;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	/**
	 * 廣告版位選項 seq
	 */
	private long adSpaceOptionSeq;

	public AdAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(adCollector.getInquiry());
	}

	public Ad getSearcher() {
		return searcher;
	}

	public void setSearcher(Ad searcher) {
		this.searcher = searcher;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
	}

	/**
	 * 廣告版位選項 seq
	 * 
	 * @return
	 */
	public long getAdSpaceOptionSeq() {
		return adSpaceOptionSeq;
	}

	public void setAdSpaceOptionSeq(long adSpaceOptionSeq) {
		this.adSpaceOptionSeq = adSpaceOptionSeq;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------
	/**
	 * 廣告版位選項
	 * 
	 * @return
	 */
	public List<AdSpace> getAdSpaceOptions() {
		return adSpaceService.getAdSpaces(userSession.getSiteId());
	}

	/**
	 * 廣告類型選項
	 * 
	 * @return
	 */
	public List<AdTypeOption> getAdTypeOptions() {
		return adCollector.getAdTypeOptions();
	}

	/**
	 * 廣告類型(1/2/3/4)選項名稱
	 * 
	 * @param adType
	 * @return
	 */
	public String getAdTypeName(AdType adType) {
		return getAdTypeName(adType, getLocale());
	}

	/**
	 * 廣告類型(1/2/3/4)選項名稱
	 * 
	 * @param adType
	 * @param locale
	 * @return
	 */
	public String getAdTypeName(AdType adType, Locale locale) {
		return adCollector.getAdTypeName(adType, locale);
	}

	/**
	 * 連結目標選項
	 * 
	 * @return
	 */
	public List<String> getTargetOptions() {
		return adCollector.getTargetOptions();
	}

	@Action(value = "index")
	public String execute() {
		// 返回列表
		return list();
	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	@Action(value = "list")
	public String list() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return SUCCESS;
			}

			// 讀取列表
			retrieveList(siteSeq);
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取列表
	 * 
	 * @return
	 */
	protected void retrieveList(long siteSeq) {
		ads = adService.findAd(inquiry, getLocale(), siteSeq, searcher);
	}

	/**
	 * 查詢, sql
	 * 
	 * @return
	 */
	@Action(value = "find")
	public String find() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return SUCCESS;
			}

			// 讀取列表
			retrieveList(siteSeq);
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 搜尋, lql
	 * 
	 * @return
	 */
	@Action(value = "search")
	public String search() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 顯示單筆,網站別
	 * 
	 * @return
	 */
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "adView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(ad.getSeq());
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取單筆
	 */
	protected void retriveSingle(long seq) {
		ad = adService.find(AdImpl.class, seq);
		if (ad != null) {
			// 名稱
			adName = ad.getName(getLocale());
			// 廣告版位
			AdSpace adSpace = ad.getAdSpace();
			if (adSpace != null) {
				adSpaceOptionSeq = adSpace.getSeq();
			}
		} else {
			addActionMessage(getText("global.data.not.exist"));
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = {
			@Result(name = "success", type = "freemarker", location = "adAdd.ftl"),
			@Result(name = "list", type = "freemarker", location = "adList.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設廣告
			ad = adService.createAd(adCollector.getDefaultAd());
			// session網站傳給網頁
			this.siteSeq = siteSeq;
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新增存檔,網站別
	 * 
	 * @return
	 */
	@Action(value = "addSave", results = {
			@Result(name = "input", type = "freemarker", location = "adAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "adList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "adName", key = "ad.names.required"),//
			@RequiredStringValidator(fieldName = "ad.title", key = "ad.adTitle.required"),//
			@RequiredStringValidator(fieldName = "ad.url", key = "ad.url.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			Site site = siteService.find(SiteImpl.class, siteSeq);
			if (site == null) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return INPUT;
			}

			// 網站
			ad.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(ad.getId())) {
				addActionError(getText("ad.id.required"));
				return INPUT;
			}
			// 預設廣告版位
			Ad defalutAd = adCollector.createAd(adCollector.getDefaultAd());

			// 名稱
			ad.setName(getLocale(), adName);
			// 廣告版位
			if (adSpaceOptionSeq > 0) {
				AdSpace adSpace = adSpaceService.find(AdSpaceImpl.class,
						adSpaceOptionSeq);
				if (adSpace != null) {
					ad.setAdSpace(adSpace);
				}
			}
			//
			result = adService.insert(ad, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] { String.valueOf(ad.getSeq()),
						ad.getName(getLocale()) };
				addActionMessage(getText("global.add.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		if (result == null) {
			String[] msgArgs = new String[] { String.valueOf(-1),
					ad.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = {
			@Result(name = "success", type = "freemarker", location = "adEdit.ftl"),
			@Result(name = "list", type = "freemarker", location = "adList.ftl") })
	public String edit() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 讀取單筆
			retriveSingle(ad.getSeq());
			// session網站傳給網頁
			this.siteSeq = siteSeq;
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 編輯存檔
	 * 
	 * @return
	 */
	@Action(value = "editSave", results = {
			@Result(name = "input", type = "freemarker", location = "adEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "adList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "adName", key = "ad.names.required"),//
			@RequiredStringValidator(fieldName = "ad.title", key = "ad.adTitle.required"),//
			@RequiredStringValidator(fieldName = "ad.url", key = "ad.url.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			Site site = siteService.find(SiteImpl.class, siteSeq);
			if (site == null) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return INPUT;
			}

			// 網站
			ad.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(ad.getId())) {
				addActionError(getText("ad.id.required"));
				return INPUT;
			}

			// 存在的廣告
			Ad existAd = adService.find(AdImpl.class, ad.getSeq());
			if (existAd == null) {
				String[] msgArgs = new String[] { String.valueOf(ad.getSeq()),
						ad.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			ad.setNames(existAd.getNames());
			ad.setName(getLocale(), adName);
			// 廣告版位
			if (adSpaceOptionSeq > 0) {
				AdSpace adSpace = adSpaceService.find(AdSpaceImpl.class,
						adSpaceOptionSeq);
				if (adSpace != null) {
					ad.setAdSpace(adSpace);
				}
			}
			//
			result = adService.update(ad, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] { String.valueOf(ad.getSeq()),
						ad.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(ad.getSeq()),
					ad.getName(getLocale()) };
			addActionError(getText("global.save.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 刪除, 多筆刪除,by seqs
	 * 
	 * @return
	 */
	@Action(value = "delete")
	public String delete() {
		List<Ad> result = new LinkedList<Ad>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = adService.delete(AdImpl.class, buffs,
					userSession.getUserId());
			size = result.size();
			if (size > 0) {
				addActionMessage(getText("global.delete.success",
						new String[] { String.valueOf(size) }));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			LOGGER.warn(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		//
		if (!seqs.isEmpty() && size < 1) {
			addActionMessage(getText("global.delete.no.data"));
		}
		return SUCCESS;
	}

	/**
	 * 存檔
	 * 
	 * @return
	 */
	@Action(value = "save")
	public String save() {
		return SUCCESS;
	}
}
