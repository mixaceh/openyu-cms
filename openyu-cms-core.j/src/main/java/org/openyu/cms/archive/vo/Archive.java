package org.openyu.cms.archive.vo;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.BaseBean;
import org.openyu.commons.enumz.StringEnum;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 檔案
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Archive extends BaseBean
{
	String KEY = Archive.class.getName();

	/**
	 * 可編輯的尾碼名類別
	 * 
	 */
	public enum EditType implements StringEnum
	{
		/**
		 * html
		 */
		HTML("html"),

		/**
		 * htm
		 */
		HTM("htm"),

		/**
		 * css
		 */
		CSS("css"),

		/**
		 * js
		 */
		JS("js"),

		/**
		 * txt
		 */
		TXT("txt"),

		//
		;

		private final String value;

		private EditType(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}
	}

	/**
	 * 圖片的尾碼名類別
	 * 
	 */
	public enum ImageType implements StringEnum
	{
		/**
		 * jpg
		 */
		JPG("jpg"),

		/**
		 * jpeg
		 */
		JPEG("jpeg"),

		/**
		 * gif
		 */
		GIF("gif"),

		/**
		 * png
		 */
		PNG("png"),

		/**
		 * bmp
		 */
		BMP("bmp"),

		//
		;

		private final String value;

		private ImageType(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}

	}

	/**
	 * 檔案/目錄
	 * 
	 * @return
	 */
	File getFile();

	void setFile(File file);

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);

	/**
	 * 是否檔案
	 * 
	 * @return
	 */
	boolean isFile();

	/**
	 * 是否目錄
	 * 
	 * @return
	 */
	boolean isDirectory();

	/**
	 * 完整檔案名稱, 根據父層絕對路徑(parentAbsolutePath)
	 * 
	 * /custom/resource/s1/default/css/style.css
	 * 
	 * @return
	 */
	String getPath();

	/**
	 * 檔案路徑, 不包含檔案名稱的路徑
	 * 
	 * /custom/resource/s1/default/css
	 * 
	 * @return
	 */
	String getParent();

	/**
	 * 檔案名稱, 不包含路徑的檔案名稱
	 * 
	 * 如沒有指定名稱，則返回檔自身的名稱
	 * 
	 * style.css
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 副檔名
	 * 
	 * @return
	 */
	String getExtension();

	/**
	 * 最後修改時間
	 * 
	 * @return
	 */
	long getLastModified();

	/**
	 * 最後修改時間
	 * 
	 * @return
	 */
	Date getLastModifiedDate();

	/**
	 * 檔案大小, KB
	 * 
	 * @return
	 */
	long getLength();

	/**
	 * 檔案圖示名稱
	 * <ul>
	 * <li>directory = folder</li>
	 * <li>jpg,jpeg = jpg</li>
	 * <li>gif = gif</li>
	 * <li>html,htm = html</li>
	 * <li>swf = swf</li>
	 * <li>txt = txt</li>
	 * <li>其他 = unknow</li>
	 * </ul>
	 * 
	 * @return
	 */
	String getIcon();

	/**
	 * 下層子目錄
	 * 
	 * 如果沒有指定子目錄，則返回資料夾自身的子檔，並運用FileFilter。
	 * 
	 * @return
	 */
	List<Archive> getChild();

	void setChild(List<Archive> child);

	/**
	 * 是否可編輯的尾碼名類別
	 * 
	 * @return
	 */
	boolean isEditType();

	/**
	 * 是否圖片的尾碼名類別
	 * 
	 * @return
	 */
	boolean isImageType();

}