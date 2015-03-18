package org.openyu.cms.module.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.IdNamesBean;
import org.openyu.commons.enumz.IntEnum;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 模組項目
 * 
 * ModuleItem
 * 
 * +-CatalogItem 目錄項目
 * 
 * +-ContextItem 本文項目
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface ModuleItem extends IdNamesBean
{
	String KEY = ModuleItem.class.getName();

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
	 * 排序
	 */
	int getSort();

	void setSort(int sort);

	/**
	 * 預設值
	 */
	String getDefault();

	void setDefault(String defaultz);

	/**
	 * 可選項
	 */
	String getOption();

	void setOption(String option);

	/**
	 * 長度
	 */
	String getLength();

	void setLength(String length);

	/**
	 * 文本行數
	 */
	String getRows();

	void setRows(String rows);

	/**
	 * 文本列數
	 */
	String getCols();

	void setCols(String cols);

	/**
	 * 説明資訊
	 */
	String getHelp();

	void setHelp(String help);

	/**
	 * 幫助位置
	 */
	String getHelpPosition();

	void setHelpPosition(String helpPosition);

	/**
	 * 欄位類型
	 */
	ColumnType getColumnType();

	void setColumnType(ColumnType columnType);

	/**
	 * 是否獨佔一行
	 */
	boolean getSingle();

	void setSingle(boolean single);

	/**
	 * 是否自訂
	 */
	boolean getCustom();

	void setCustom(boolean custom);

	/**
	 * 是否顯示
	 */
	boolean getDisplay();

	void setDisplay(boolean display);

}