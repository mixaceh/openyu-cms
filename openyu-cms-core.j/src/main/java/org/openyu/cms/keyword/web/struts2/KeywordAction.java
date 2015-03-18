package org.openyu.cms.keyword.web.struts2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.keyword.vo.KeywordCollector;
import org.openyu.cms.keyword.service.KeywordService;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.vo.impl.KeywordImpl;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 關鍵字控制器
 */
@ParentPackage("default")
@Namespace("/back/service/keyword")
@Results({ @Result(name = "success", type = "freemarker", location = "keywordList.ftl") })
public class KeywordAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(KeywordAction.class);

	/**
	 * 關鍵字服務
	 */
	@Autowired
	@Qualifier("keywordService")
	protected transient KeywordService keywordService;

	/**
	 * 網站服務
	 */
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	/**
	 * 靜態收集器
	 */
	protected transient KeywordCollector keywordCollector = KeywordCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Dictionary searcher = new DictionaryImpl();

	/**
	 * 單筆資料
	 */
	private Keyword keyword = new KeywordImpl();

	/**
	 * 新字典資料
	 */
	private Dictionary dictionary = new DictionaryImpl();

	/**
	 * 舊字典資料
	 */
	private Dictionary origDictionary = new DictionaryImpl();

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	/**
	 * 選擇多筆關鍵字
	 */
	private List<String> keys = new LinkedList<String>();

	public KeywordAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();
		// 初始化查詢條件
		initializeInquiry(keywordCollector.createInquiry());
	}

	public Dictionary getSearcher() {
		return searcher;
	}

	public void setSearcher(Dictionary searcher) {
		this.searcher = searcher;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public Dictionary getOrigDictionary() {
		return origDictionary;
	}

	public void setOrigDictionary(Dictionary origDictionary) {
		this.origDictionary = origDictionary;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
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
			log.warn(ex);
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
		List<Keyword> list = keywordService.findKeyword(inquiry, getLocale(),
				siteSeq, searcher);
		if (list.size() > 0) {
			keyword = list.get(0);
		}
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
			log.warn(ex);
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
	 * 顯示單筆
	 * 
	 * @return
	 */
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "keywordView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取單筆
	 */
	protected void retriveSingle() {
		keyword = keywordService.find(KeywordImpl.class, keyword.getSeq());
		if (keyword != null) {
			dictionary.setValue(keyword.getDictionarys()
					.get(dictionary.getKey()).getValue());
			dictionary.setValid(keyword.getDictionarys()
					.get(dictionary.getKey()).getValid());
			origDictionary = (Dictionary) dictionary.clone();
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "keywordAdd.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			if (keyword.getSeq() > 0) {
				keyword = keywordService.find(KeywordImpl.class,
						keyword.getSeq());
				if (keyword == null) {
					// 預設關鍵字
					keyword = keywordService.createKeyword(
							keywordCollector.getDefaultKeyword(), getLocale());
				}
			} else {
				// 預設關鍵字
				keyword = keywordService.createKeyword(
						keywordCollector.getDefaultKeyword(), getLocale());
			}
			// 預設字典
			dictionary = keywordService.createDictionary(keywordCollector
					.getDefaultDictionary());

			// session網站傳給網頁
			this.siteSeq = siteSeq;
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新增存檔
	 * 
	 * @return
	 */
	@Action(value = "addSave", results = {
			@Result(name = "input", type = "freemarker", location = "keywordAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "keywordList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "dictionary.key", key = "keyword.dictionarys.key.required"),//
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
			keyword.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(keyword.getId())) {
				addActionError(getText("keyword.id.required"));
				return INPUT;
			}

			// 存在的關鍵字
			Keyword existKeyword = keywordService.find(KeywordImpl.class,
					keyword.getSeq());

			if (existKeyword != null) {
				// dictionarys
				if (existKeyword.getDictionarys().containsKey(
						dictionary.getKey())) {
					// 關鍵字存在
					String[] msgArgs = new String[] { dictionary.getKey(),
							dictionary.getValue() };
					addActionError(getText("global.data.exist", msgArgs));
					return INPUT;
				}
			}
			//
			result = keywordService.insertDictionary(userSession.getUser(),
					keyword, dictionary);
			if (result != null) {
				String[] msgArgs = new String[] { dictionary.getKey(),
						dictionary.getValue() };
				addActionMessage(getText("global.add.success", msgArgs));
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result == null) {
			String[] msgArgs = new String[] { dictionary.getKey(),
					dictionary.getValue() };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "keywordEdit.ftl") })
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
			retriveSingle();
			// session網站傳給網頁
			this.siteSeq = siteSeq;
		} catch (Exception ex) {
			log.warn(ex);
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
			@Result(name = "input", type = "freemarker", location = "keywordEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "keywordList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "dictionary.key", key = "keyword.dictionarys.key.required"),//
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
			keyword.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(keyword.getId())) {
				addActionError(getText("keyword.id.required"));
				return INPUT;
			}

			// 存在的關鍵字
			Keyword existKeyword = keywordService.find(KeywordImpl.class,
					keyword.getSeq());
			if (existKeyword == null) {
				String[] msgArgs = new String[] {
						String.valueOf(keyword.getSeq()), "" };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			result = keywordService.updateDictionary(userSession.getUser(),
					existKeyword, origDictionary, dictionary);

			if (result > 0) {
				String[] msgArgs = new String[] { dictionary.getKey(),
						dictionary.getValue() };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}

		if (result < 1) {
			String[] msgArgs = new String[] { dictionary.getKey(),
					dictionary.getValue() };
			addActionError(getText("global.save.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 刪除, 多筆刪除,by seqs
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "input", type = "freemarker", location = "keywordList.ftl") })
	public String delete() {
		List<Dictionary> result = null;
		int size = 0;
		try {
			// 檢查id
			if (StringHelper.isEmpty(keyword.getId())) {
				addActionError(getText("keyword.id.required"));
				return INPUT;
			}

			// 存在的關鍵字
			Keyword existKeyword = keywordService.find(KeywordImpl.class,
					keyword.getSeq());
			if (existKeyword == null) {
				String[] msgArgs = new String[] {
						String.valueOf(keyword.getSeq()), "" };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}
			//
			result = keywordService.deleteDictionary(userSession.getUser(),
					existKeyword, keys);
			size = result.size();
			if (size > 0) {
				addActionMessage(getText("global.delete.success",
						new String[] { String.valueOf(size) }));
				// 返回列表
				return list();
			}

		} catch (Exception ex) {
			log.warn(ex);
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
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return SUCCESS;
			}
			// TODO 修改啟用欄位
			// 讀取列表
			retrieveList(siteSeq);
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}
}
