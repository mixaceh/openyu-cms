package org.openyu.cms.vote.web.struts2;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.vote.service.VoteService;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.VoteCollector;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.cms.vote.vo.impl.VoteItemImpl;
import org.openyu.commons.lang.StringHelper;
import org.openyu.commons.util.CollectionHelper;
import org.openyu.commons.util.DateHelper;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 * 投票控制器
 */
@ParentPackage("default")
@Namespace("/back/service/vote")
@Results({ @Result(name = "success", type = "freemarker", location = "voteList.ftl") })
public class VoteAction extends AppListActionSupporter {

	private static final long serialVersionUID = -8184746975973063374L;

	private static transient final Logger log = LogManager
			.getLogger(VoteAction.class);

	/**
	 * 投票服務
	 */
	@Autowired
	@Qualifier("voteService")
	protected transient VoteService voteService;

	/**
	 * 靜態收集器
	 */
	protected transient VoteCollector voteCollector = VoteCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Vote searcher = new VoteImpl();

	/**
	 * 單筆資料
	 */
	private Vote vote = new VoteImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Vote> votes = new LinkedList<Vote>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String voteName;

	/**
	 * 暫存的描述,因應i8n處理
	 */
	private String voteDescription;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	/**
	 * 投票開始時間
	 */
	private Date voteStartDate;

	private Date voteStartTime;

	/**
	 * 投票結束時間
	 */
	private Date voteEndDate;

	private Date voteEndTime;

	/**
	 * 投票選項
	 */
	private List<String> ids = new LinkedList<String>();

	private List<String> voteNames = new LinkedList<String>();

	private List<Integer> voteCounts = new LinkedList<Integer>();

	private List<Integer> sorts = new LinkedList<Integer>();

	public VoteAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(voteCollector.createInquiry());
	}

	public Vote getSearcher() {
		return searcher;
	}

	public void setSearcher(Vote searcher) {
		this.searcher = searcher;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public String getVoteName() {
		return voteName;
	}

	public String getVoteDescription() {
		return voteDescription;
	}

	public void setVoteDescription(String voteDescription) {
		this.voteDescription = voteDescription;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
	}

	public Date getVoteStartDate() {
		return voteStartDate;
	}

	public void setVoteStartDate(Date voteStartDate) {
		this.voteStartDate = voteStartDate;
	}

	public Date getVoteStartTime() {
		return voteStartTime;
	}

	public void setVoteStartTime(Date voteStartTime) {
		this.voteStartTime = voteStartTime;
	}

	public Date getVoteEndDate() {
		return voteEndDate;
	}

	public void setVoteEndDate(Date voteEndDate) {
		this.voteEndDate = voteEndDate;
	}

	public Date getVoteEndTime() {
		return voteEndTime;
	}

	public void setVoteEndTime(Date voteEndTime) {
		this.voteEndTime = voteEndTime;
	}

	// public Set<VoteItemView> getVoteItemViews()
	// {
	// return voteItemViews;
	// }
	//
	// public void setVoteItemViews(Set<VoteItemView> voteItemViews)
	// {
	// this.voteItemViews = voteItemViews;
	// }

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<String> getVoteNames() {
		return voteNames;
	}

	public void setVoteNames(List<String> voteNames) {
		this.voteNames = voteNames;
	}

	public List<Integer> getVoteCounts() {
		return voteCounts;
	}

	public void setVoteCounts(List<Integer> voteCounts) {
		this.voteCounts = voteCounts;
	}

	public List<Integer> getSorts() {
		return sorts;
	}

	public void setSorts(List<Integer> sorts) {
		this.sorts = sorts;
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
		votes = voteService.findVote(inquiry, getLocale(), siteSeq, searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "voteView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(vote.getSeq());
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
		vote = voteService.find(VoteImpl.class, vote.getSeq());

		if (vote != null) {
			// 暫存的投票名稱,因應i8n處理
			voteName = vote.getName(getLocale());
			voteDescription = vote.getDescription(getLocale());
			// 日期 時間[分開]
			voteStartDate = vote.getStartDate();
			voteStartTime = vote.getStartDate();
			// 日期 時間[分開]
			voteEndDate = vote.getEndDate();
			voteEndTime = vote.getEndDate();
		} else {
			addActionMessage(getText("global.data.not.exist"));
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "voteAdd.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設投票
			vote = voteService.createVote(voteCollector.getDefaultVote());
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
			@Result(name = "input", type = "freemarker", location = "voteAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "voteList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "voteName", key = "vote.names.required"),//
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
			vote.setSite(site);

			// 檢查id
			if (StringHelper.isBlank(vote.getId())) {
				addActionError(getText("vote.id.required"));
				return INPUT;
			}

			// 預設投票
			Vote defaultVote = voteService.createVote(voteCollector
					.getDefaultVote());
			// 投票選項
			vote.setVoteItems(defaultVote.getVoteItems());

			// 名稱
			vote.setName(getLocale(), voteName);
			// 描述
			vote.setDescription(getLocale(), voteDescription);
			//
			// 日期 時間[合併]
			// 開始時間
			Date startDate = null;
			if (voteStartDate != null) {
				String sdate = DateHelper.toString(voteStartDate, "yyyy/MM/dd");
				String stime = DateHelper.toString(voteStartTime, "HH:mm:ss");
				startDate = DateHelper.toDate(sdate + " " + stime);
			}
			vote.setStartDate(startDate);
			// 結束時間
			Date endDate = null;
			if (voteEndDate != null) {
				String edate = DateHelper.toString(voteEndDate, "yyyy/MM/dd");
				String etime = DateHelper.toString(voteEndTime, "HH:mm:ss");
				endDate = DateHelper.toDate(edate + " " + etime);
			}
			vote.setEndDate(endDate);
			//
			// 投票選項
			// 暫存的投票項目名稱, 因應i8n處理
			// Map<String, VoteItem> origVoteItems = (Map<String, VoteItem>)
			// existVote.getVoteItems();
			// origVoteItems
			Map<String, VoteItem> newVoteItems = new LinkedHashMap<String, VoteItem>();
			//
			VoteItem voteItem = null;
			String id = null;
			String oid = "";
			for (int index = 0; index < voteNames.size(); index++) {
				voteItem = new VoteItemImpl();
				//
				voteItem.setVoteCount(voteCounts.get(index));
				voteItem.setSort(sorts.get(index));
				id = ids.get(index);
				if (!StringHelper.isBlank(id)) {
					voteItem.setId(id);
					oid = id;
					// voteItem.setNames(origVoteItems.get(id).getNames());
				} else {
					id = String.valueOf(Integer.valueOf(oid) + index);
					voteItem.setId(id);
				}
				voteItem.setName(getLocale(), voteNames.get(index));
				//
				newVoteItems.put(id, voteItem);
			}
			//
			vote.setVoteItems(newVoteItems);
			//
			result = voteService.insert(vote, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(vote.getSeq()),
						vote.getName(getLocale()) };
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
					vote.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "voteEdit.ftl") })
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
			retriveSingle(vote.getSeq());
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
			@Result(name = "input", type = "freemarker", location = "voteEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "voteList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "voteName", key = "vote.names.required"),//
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
			vote.setSite(site);

			// 檢查id
			if (StringHelper.isBlank(vote.getId())) {
				addActionError(getText("vote.id.required"));
				return INPUT;
			}

			// 存在的投票
			Vote existVote = voteService.find(VoteImpl.class, vote.getSeq());
			if (existVote == null) {
				String[] msgArgs = new String[] {
						String.valueOf(vote.getSeq()),
						vote.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			vote.setNames(existVote.getNames());
			vote.setName(getLocale(), voteName);
			// 描述
			vote.setDescriptions(existVote.getDescriptions());
			vote.setDescription(getLocale(), voteDescription);
			// 投票時間限制
			// 日期 時間[合併]
			// 開始時間
			Date startDate = null;
			if (voteStartDate != null) {
				String sdate = DateHelper.toString(voteStartDate, "yyyy/MM/dd");
				String stime = DateHelper.toString(voteStartTime, "HH:mm:ss");
				startDate = DateHelper.toDate(sdate + " " + stime);
			}
			vote.setStartDate(startDate);
			// 結束時間
			Date endDate = null;
			if (voteEndDate != null) {
				String edate = DateHelper.toString(voteEndDate, "yyyy/MM/dd");
				String etime = DateHelper.toString(voteEndTime, "HH:mm:ss");
				endDate = DateHelper.toDate(edate + " " + etime);
			}
			vote.setEndDate(endDate);

			// 投票選項
			// 暫存的投票項目名稱, 因應i8n處理
			Map<String, VoteItem> origVoteItems = (Map<String, VoteItem>) existVote
					.getVoteItems();
			// origVoteItems
			Map<String, VoteItem> newVoteItems = new LinkedHashMap<String, VoteItem>();
			//
			VoteItem voteItem = null;
			String id = null;
			String oid = "";
			int i = 1;
			for (int index = 0; index < voteNames.size(); index++) {
				voteItem = new VoteItemImpl();
				//
				voteItem.setVoteCount(voteCounts.get(index));
				voteItem.setSort(sorts.get(index));
				id = ids.get(index);
				if (!StringHelper.isBlank(id)) {
					voteItem.setId(id);
					oid = id;
					voteItem.setNames(origVoteItems.get(id).getNames());
				} else {
					id = String.valueOf(Integer.valueOf(oid) + i++);
					voteItem.setId(id);
				}
				voteItem.setName(getLocale(), voteNames.get(index));
				//
				newVoteItems.put(id, voteItem);
			}
			//
			vote.setVoteItems(newVoteItems);
			//
			result = voteService.update(vote, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(vote.getSeq()),
						vote.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(vote.getSeq()),
					vote.getName(getLocale()) };
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
		List<Vote> result = new LinkedList<Vote>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = voteService.delete(VoteImpl.class, buffs,
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
