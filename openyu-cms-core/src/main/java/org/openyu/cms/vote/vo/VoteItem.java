package org.openyu.cms.vote.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.IdNamesBean;
import org.openyu.commons.enumz.IntEnum;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 投票項目
 * 
 * VoteItem
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface VoteItem extends IdNamesBean
{
	String KEY = VoteItem.class.getName();

	/**
	 * 欄位類別
	 * 
	 */
	public enum ColumnType implements IntEnum
	{
		/**
		 * 字串文本
		 */
		STRING(1),

		/**
		 * 整數型文本
		 */
		INTEGER(2),

		/**
		 * 浮點型文本
		 */
		DOUBLE(3),

		/**
		 * 文本區
		 */
		TEXT_AREA(4),

		/**
		 * 日期
		 */
		DATE(5),

		/**
		 * 下拉清單
		 */
		SELECT(6),

		//
		;

		private final int value;

		private ColumnType(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	/**
	 * 投票數量
	 * @return
	 */
	int getVoteCount();

	void setVoteCount(int voteCount);

	/**
	 * 排序
	 */
	int getSort();

	void setSort(int sort);


}