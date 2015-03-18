package org.openyu.cms.config.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.BaseBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.enumz.IntEnum;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 浮水印位置
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface MarkPosOption extends BaseBean, NamesBean
{
	String KEY = MarkPosType.class.getName();

	/**
	 * 浮水印位置類別
	 */
	public enum MarkPosType implements IntEnum
	{
		/**
		 * 隨機
		 */
		RANDOM(1),

		/**
		 * 左上
		 */
		LEFT_TOP(2),

		/**
		 * 右上
		 */
		RIGHT_TOP(3),

		/**
		 * 左下
		 */
		LEFT_DOWN(4),

		/**
		 * 右下
		 */
		RIGHT_DOWN(5),

		/**
		 * 中央
		 */
		CENTER(6),

		//
		;
		private final int value;

		private MarkPosType(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	/**
	 * 浮水印位置類別,key
	 * 
	 * @return
	 */
	MarkPosType getId();

	void setId(MarkPosType id);

}
