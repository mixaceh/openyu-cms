package org.openyu.cms.keyword.vo;

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
	 * 新增關鍵字
	 */
	INSERT(1),

	/**
	 * 修改關鍵字
	 */
	UPDATE(2),

	/**
	 * 刪除關鍵字
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
