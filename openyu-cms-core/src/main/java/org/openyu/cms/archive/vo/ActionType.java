package org.openyu.cms.archive.vo;

import org.openyu.commons.enumz.IntEnum;

/**
 * 檔案操作類別
 */
public enum ActionType implements IntEnum
{
	/**
	 * 未知
	 */
	UNKNOWN(-1),

	/**
	 * 新建目錄
	 */
	CREATE_DIR(1),

	/**
	 * 新建檔案
	 */
	CREATE_FILE(2),

	/**
	 * 儲存檔案/儲存資源/儲存樣版
	 */
	SAVE_FILE(3),

	/**
	 * 更名檔案
	 */
	RENAME_FILE(4),

	/**
	 * 更名目錄
	 */
	RENAME_DIR(5),

	/**
	 * 刪除檔案
	 */
	DELETE_FILE(6),

	/**
	 * 刪除目錄
	 */
	DELETE_DIR(7),

	/**
	 * 上傳檔案
	 */
	UPLOAD_FILE(8),

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