package org.openyu.cms.module.vo.supporter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.module.vo.ModuleItem;
import org.openyu.commons.bean.supporter.IdNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "moduleItem")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class ModuleItemSupporter extends IdNamesBeanSupporter implements ModuleItem
{
	private static final long serialVersionUID = -1672100749295226085L;

	/**
	 * 排序
	 */
	private int sort;

	/**
	 * 預設值
	 */
	private String defaultz;

	/**
	 * 可選項
	 */
	private String option;

	/**
	 * 長度
	 */
	private String length;

	/**
	 * 文本行數
	 */
	private String rows;

	/**
	 * 文本列數
	 */
	private String cols;

	/**
	 * 説明資訊
	 */
	private String help;

	/**
	 * 幫助位置
	 */
	private String helpPosition;

	/**
	 * 欄位類型
	 */
	private ColumnType columnType;

	/**
	 * 是否獨佔一行
	 */
	private boolean single;

	/**
	 * 是否自訂
	 */
	private boolean custom;

	/**
	 * 是否顯示
	 */
	private boolean display;

	public ModuleItemSupporter()
	{}

	public int getSort()
	{
		return sort;
	}

	public void setSort(int priority)
	{
		this.sort = priority;
	}

	public String getDefault()
	{
		return defaultz;
	}

	public void setDefault(String defaultz)
	{
		this.defaultz = defaultz;
	}

	public String getOption()
	{
		return option;
	}

	public void setOption(String option)
	{
		this.option = option;
	}

	public String getLength()
	{
		return length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}

	public String getRows()
	{
		return rows;
	}

	public void setRows(String rows)
	{
		this.rows = rows;
	}

	public String getCols()
	{
		return cols;
	}

	public void setCols(String cols)
	{
		this.cols = cols;
	}

	public String getHelp()
	{
		return help;
	}

	public void setHelp(String help)
	{
		this.help = help;
	}

	public String getHelpPosition()
	{
		return helpPosition;
	}

	public void setHelpPosition(String helpPosition)
	{
		this.helpPosition = helpPosition;
	}

	public ColumnType getColumnType()
	{
		return columnType;
	}

	public void setColumnType(ColumnType columnType)
	{
		this.columnType = columnType;
	}

	public boolean getSingle()
	{
		return single;
	}

	public void setSingle(boolean single)
	{
		this.single = single;
	}

	public boolean getCustom()
	{
		return custom;
	}

	public void setCustom(boolean custom)
	{
		this.custom = custom;
	}

	public boolean getDisplay()
	{
		return display;
	}

	public void setDisplay(boolean display)
	{
		this.display = display;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("sort", sort);
		builder.append("defaultz", defaultz);
		builder.append("option", option);
		builder.append("length", length);
		builder.append("rows", rows);
		builder.append("cols", cols);
		builder.append("help", help);
		builder.append("helpPosition", helpPosition);
		builder.append("columnType", columnType);
		builder.append("single", single);
		builder.append("custom", custom);
		builder.append("display", display);
		return builder.toString();
	}

	public Object clone()
	{
		ModuleItemSupporter copy = null;
		copy = (ModuleItemSupporter) super.clone();
		return copy;
	}

}
