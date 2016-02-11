package org.openyu.cms.friend.web.struts2;

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
import org.openyu.cms.friend.service.FriendService;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.FriendCollector;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.cms.friendType.service.FriendTypeService;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 友情連結控制器
 */
@ParentPackage("default")
@Namespace("/back/service/friend")
@Results({ @Result(name = "success", type = "freemarker", location = "friendList.ftl") })
public class FriendAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(FriendAction.class);

	/**
	 * 友情連結服務
	 */
	@Autowired
	@Qualifier("friendService")
	protected transient FriendService friendService;

	/**
	 * 友情類型服務
	 */
	@Autowired
	@Qualifier("friendTypeService")
	protected transient FriendTypeService friendTypeService;

	/**
	 * 靜態收集器
	 */
	protected transient FriendCollector friendCollector = FriendCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Friend searcher = new FriendImpl();

	/**
	 * 友情類型搜尋者
	 */
	private FriendType friendTypeSearcher = new FriendTypeImpl();

	/**
	 * 單筆資料
	 */
	private Friend friend = new FriendImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Friend> friends = new LinkedList<Friend>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String friendName;

	/**
	 * 暫存的描述,因應i8n處理
	 */
	private String friendDescription;

	/**
	 * 友情類型選項 seq
	 */
	private long friendTypeOptionSeq;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	public FriendAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(friendCollector.getInquiry());
	}

	public Friend getSearcher() {
		return searcher;
	}

	public void setSearcher(Friend searcher) {
		this.searcher = searcher;
	}

	public FriendType getFriendTypeSearcher() {
		return friendTypeSearcher;
	}

	public void setFriendTypeSearcher(FriendType friendTypeSearcher) {
		this.friendTypeSearcher = friendTypeSearcher;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendDescription() {
		return friendDescription;
	}

	public void setFriendDescription(String friendDescription) {
		this.friendDescription = friendDescription;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
	}

	/**
	 * 友情類型選項 seq
	 * 
	 * @return
	 */
	public long getFriendTypeOptionSeq() {
		return friendTypeOptionSeq;
	}

	public void setFriendTypeOptionSeq(long friendTypeOptionSeq) {
		this.friendTypeOptionSeq = friendTypeOptionSeq;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------
	/**
	 * 友情類型選項
	 * 
	 * @return
	 */
	public List<FriendType> getFriendTypeOptions() {
		return friendTypeService.getFriendTypes(userSession.getSiteId());
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
		friends = friendService.findFriend(inquiry, getLocale(), siteSeq,
				friendTypeSearcher, searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "friendView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(friend.getSeq());
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
		friend = friendService.find(FriendImpl.class, seq);

		if (friend != null) {
			// 暫存的名稱,因應i8n處理
			friendName = friend.getName(getLocale());
			// 暫存的描述,因應i8n處理
			friendDescription = friend.getDescription(getLocale());
			// 友情類型
			FriendType friendType = friend.getFriendType();
			if (friendType != null) {
				friendTypeOptionSeq = friendType.getSeq();
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
			@Result(name = "success", type = "freemarker", location = "friendAdd.ftl"),
			@Result(name = "list", type = "freemarker", location = "friendList.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設友情連結
			friend = friendService.createFriend(friendCollector
					.getDefaultFriend());
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
			@Result(name = "input", type = "freemarker", location = "friendAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "friendList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "friendName", key = "friend.names.required"),//
			@RequiredStringValidator(fieldName = "friend.url", key = "friend.url.required"),//
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
			friend.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(friend.getId())) {
				addActionError(getText("friend.id.required"));
				return INPUT;
			}

			// 預設友情連結
			Friend defalutFriend = friendCollector.createFriend(friendCollector
					.getDefaultFriend());

			// 名稱
			friend.setName(getLocale(), friendName);
			// 描述
			friend.setDescription(getLocale(), friendDescription);
			// 友情類型
			if (friendTypeOptionSeq > 0) {
				FriendType friendType = friendTypeService.find(
						FriendTypeImpl.class, friendTypeOptionSeq);
				if (friendType != null) {
					friend.setFriendType(friendType);
				}
			}
			//
			result = friendService.insert(friend, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(friend.getSeq()),
						friend.getName(getLocale()) };
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
					friend.getName(getLocale()) };
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
			@Result(name = "success", type = "freemarker", location = "friendEdit.ftl"),
			@Result(name = "list", type = "freemarker", location = "friendList.ftl") })
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
			retriveSingle(friend.getSeq());
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
			@Result(name = "input", type = "freemarker", location = "friendEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "friendList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "friendName", key = "friend.names.required"),//
			@RequiredStringValidator(fieldName = "friend.url", key = "friend.url.required"),//
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
			friend.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(friend.getId())) {
				addActionError(getText("friend.id.required"));
				return INPUT;
			}

			// 存在的友情連結
			Friend existFriend = friendService.find(FriendImpl.class,
					friend.getSeq());
			if (existFriend == null) {
				String[] msgArgs = new String[] {
						String.valueOf(friend.getSeq()),
						friend.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			friend.setNames(existFriend.getNames());
			friend.setName(getLocale(), friendName);
			// 描述
			friend.setDescriptions(existFriend.getDescriptions());
			friend.setDescription(getLocale(), friendDescription);
			// 友情類型
			if (friendTypeOptionSeq > 0) {
				FriendType friendType = friendTypeService.find(
						FriendTypeImpl.class, friendTypeOptionSeq);
				if (friendType != null) {
					friend.setFriendType(friendType);
				}
			}
			//
			result = friendService.update(friend, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(friend.getSeq()),
						friend.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(friend.getSeq()),
					friend.getName(getLocale()) };
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
		List<Friend> result = new LinkedList<Friend>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = friendService.delete(FriendImpl.class, buffs,
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
