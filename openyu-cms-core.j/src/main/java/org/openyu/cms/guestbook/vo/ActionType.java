package org.openyu.cms.guestbook.vo;

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
	 * 新增留言
	 */
	INSERT(1),

	/**
	 * 修改留言
	 */
	UPDATE(2),

	/**
	 * 刪除留言
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
