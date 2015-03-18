package org.openyu.cms.topic.web.struts2;

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
import org.openyu.cms.topic.service.TopicService;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.vo.TopicCollector;
import org.openyu.cms.topic.vo.impl.TopicImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 專題控制器
 */
@ParentPackage("default")
@Namespace("/back/service/topic")
@Results({ @Result(name = "success", type = "freemarker", location = "topicList.ftl") })
public class TopicAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(TopicAction.class);

	/**
	 * 專題服務
	 */
	@Autowired
	@Qualifier("topicService")
	protected transient TopicService topicService;

	/**
	 * 靜態收集器
	 */
	protected transient TopicCollector topicCollector = TopicCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Topic searcher = new TopicImpl();

	/**
	 * 單筆資料
	 */
	private Topic topic = new TopicImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Topic> topics = new LinkedList<Topic>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String topicName;

	/**
	 * 暫存的簡稱,因應i8n處理
	 */
	private String shortName;

	/**
	 * 暫存的描述,因應i8n處理
	 */
	private String description;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	public TopicAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(topicCollector.getInquiry());
	}

	public Topic getSearcher() {
		return searcher;
	}

	public void setSearcher(Topic searcher) {
		this.searcher = searcher;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------
	/**
	 * 連結目標選項
	 * 
	 * @return
	 */
	public List<String> getTargetOptions() {
		return topicCollector.getTargetOptions();
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
		topics = topicService
				.findTopic(inquiry, getLocale(), siteSeq, searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "topicView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(topic.getSeq());
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
		topic = topicService.find(TopicImpl.class, seq);
		if (topic != null) {
			// 名稱
			topicName = topic.getName(getLocale());
			// 簡稱
			shortName = topic.getShortName(getLocale());
			// 描述
			description = topic.getDescription(getLocale());
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
			@Result(name = "success", type = "freemarker", location = "topicAdd.ftl"),
			@Result(name = "list", type = "freemarker", location = "topicList.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設專題
			topic = topicService.createTopic(topicCollector.getDefaultTopic());
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
			@Result(name = "input", type = "freemarker", location = "topicAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "topicList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "topicName", key = "topic.names.required"),//
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
			topic.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(topic.getId())) {
				addActionError(getText("topic.id.required"));
				return INPUT;
			}
			// 預設專題版位
			Topic defalutTopic = topicCollector.createTopic(topicCollector
					.getDefaultTopic());

			// 名稱
			topic.setName(getLocale(), topicName);
			// 簡稱
			topic.setShortName(getLocale(), shortName);
			// 描述
			topic.setDescription(getLocale(), description);
			//
			result = topicService.insert(topic, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(topic.getSeq()),
						topic.getName(getLocale()) };
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
					topic.getName(getLocale()) };
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
			@Result(name = "success", type = "freemarker", location = "topicEdit.ftl"),
			@Result(name = "list", type = "freemarker", location = "topicList.ftl") })
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
			retriveSingle(topic.getSeq());
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
			@Result(name = "input", type = "freemarker", location = "topicEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "topicList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "topicName", key = "topic.names.required"),//
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
			topic.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(topic.getId())) {
				addActionError(getText("topic.id.required"));
				return INPUT;
			}

			// 存在的專題
			Topic existTopic = topicService.find(TopicImpl.class,
					topic.getSeq());
			if (existTopic == null) {
				String[] msgArgs = new String[] {
						String.valueOf(topic.getSeq()),
						topic.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			topic.setNames(existTopic.getNames());
			topic.setName(getLocale(), topicName);
			// 簡稱
			topic.setShortNames(existTopic.getShortNames());
			topic.setShortName(getLocale(), shortName);
			// 描述
			topic.setDescriptions(existTopic.getDescriptions());
			topic.setDescription(getLocale(), description);

			result = topicService.update(topic, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(topic.getSeq()),
						topic.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(topic.getSeq()),
					topic.getName(getLocale()) };
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
		List<Topic> result = new LinkedList<Topic>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = topicService.delete(TopicImpl.class, buffs,
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
}
