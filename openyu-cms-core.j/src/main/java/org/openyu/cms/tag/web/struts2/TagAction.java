package org.openyu.cms.tag.web.struts2;

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
import org.openyu.cms.tag.service.TagService;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.TagCollector;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 標籤控制器
 */
@ParentPackage("default")
@Namespace("/back/service/tag")
@Results({ @Result(name = "success", type = "freemarker", location = "tagList.ftl") })
public class TagAction extends AppListActionSupporter {

	private static final long serialVersionUID = -5762266176860929136L;

	private static transient final Logger log = LogManager
			.getLogger(TagAction.class);

	/**
	 * 標籤服務
	 */
	@Autowired
	@Qualifier("tagService")
	protected transient TagService tagService;

	/**
	 * 靜態收集器
	 */
	protected transient TagCollector tagCollector = TagCollector.getInstance();

	/**
	 * 搜尋者
	 */
	private Tag searcher = new TagImpl();

	/**
	 * 單筆資料
	 */
	private Tag tag = new TagImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Tag> tags = new LinkedList<Tag>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String tagName;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	public TagAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(tagCollector.getInquiry());
	}

	public Tag getSearcher() {
		return searcher;
	}

	public void setSearcher(Tag searcher) {
		this.searcher = searcher;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
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
		// tags = tagService.findTag(inquiry, getLocale(), siteSeq, searcher);
		tags = tagService.findTag(inquiry, getLocale(), searcher);
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
	 * 顯示單筆,網站別
	 * 
	 * @return
	 */
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "tagView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(tag.getSeq());
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取單筆
	 */
	protected void retriveSingle(long seq) {
		tag = tagService.find(TagImpl.class, seq);
		// 暫存的標籤名稱,因應i8n處理
		if (tag != null) {
			tagName = tag.getName(getLocale());
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
			@Result(name = "success", type = "freemarker", location = "tagAdd.ftl"),
			@Result(name = "list", type = "freemarker", location = "tagList.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設標籤
			tag = tagService.createTag(tagCollector.getDefaultTag());
			// session網站傳給網頁
			this.siteSeq = siteSeq;
		} catch (Exception ex) {
			log.warn(ex);
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
			@Result(name = "input", type = "freemarker", location = "tagAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "tagList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "tagName", key = "tag.names.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			// 檢查id
			if (StringHelper.isEmpty(tag.getId())) {
				addActionError(getText("tag.id.required"));
				return INPUT;
			}
			// 預設標籤
			Tag defalutTag = tagCollector.createTag(tagCollector
					.getDefaultTag());

			// 名稱
			tag.setName(getLocale(), tagName);
			//
			result = tagService.insert(tag, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] { String.valueOf(tag.getSeq()),
						tag.getName(getLocale()) };
				addActionMessage(getText("global.add.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result == null) {
			String[] msgArgs = new String[] { String.valueOf(-1),
					tag.getName(getLocale()) };
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
			@Result(name = "success", type = "freemarker", location = "tagEdit.ftl"),
			@Result(name = "list", type = "freemarker", location = "tagList.ftl") })
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
			retriveSingle(tag.getSeq());
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
			@Result(name = "input", type = "freemarker", location = "tagEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "tagList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "tagName", key = "tag.names.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isEmpty(tag.getId())) {
				addActionError(getText("tag.id.required"));
				return INPUT;
			}

			// 存在的標籤
			Tag existTag = tagService.find(TagImpl.class, tag.getSeq());
			if (existTag == null) {
				String[] msgArgs = new String[] { String.valueOf(tag.getSeq()),
						tag.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			tag.setNames(existTag.getNames());
			tag.setName(getLocale(), tagName);
			//
			result = tagService.update(tag, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] { String.valueOf(tag.getSeq()),
						tag.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(tag.getSeq()),
					tag.getName(getLocale()) };
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
		List<Tag> result = new LinkedList<Tag>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = tagService.delete(TagImpl.class, buffs,
					userSession.getUserId());
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
		return SUCCESS;
	}

	/**
	 * 重置
	 * 
	 * @return
	 */
	@Action(value = "reset")
	public String reset() {
		try {
			tagService.reset();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		// 返回列表
		return list();
	}
}
