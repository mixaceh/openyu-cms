package org.openyu.cms.tag.vo;

import org.openyu.commons.enumz.IntEnum;

/**
 * 操作類別
 */
public enum ActionType implements IntEnum
{
	/**
	 * 未知
	 */
	UNKNOWN(-1),

	/**
	 * 新增
	 */
	INSERT(1),

	/**
	 * 修改
	 */
	UPDATE(2),

	/**
	 * 刪除
	 */
	DELETE(3),

	;
	private final int value;

	private ActionType(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}
}
