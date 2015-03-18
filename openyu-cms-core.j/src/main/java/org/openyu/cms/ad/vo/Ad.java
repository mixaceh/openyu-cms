package org.openyu.cms.ad.vo;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.enumz.IntEnum;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 廣告
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Ad extends SeqIdAuditNamesBean
{
	String KEY = Ad.class.getName();
	
	/**
	 * 廣告類型
	 */
	public enum AdType implements IntEnum
	{

		/**
		 * 圖片
		 */
		PICTURE(1),

		/**
		 * Flash
		 */
		FLASH(2),

		/**
		 * 文字
		 */
		TEXT(3),

		/**
		 * 代碼
		 */
		CODE(4),

		//
		;
		private final int intValue;

		private AdType(int intValue)
		{
			this.intValue = intValue;
		}

		public int getValue()
		{
			return intValue;
		}
	}

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);

	/**
	 * 廣告類型
	 * 
	 * @return
	 */
	AdType getAdType();

	void setAdType(AdType adType);

	/**
	 * 標題
	 * 
	 * @return
	 */
	String getTitle();

	void setTitle(String title);
	
	/**
	 * 網址
	 * 
	 * @return
	 */
	String getUrl();

	void setUrl(String url);

	/**
	 * 圖示
	 * 
	 * @return
	 */
	String getLogo();

	void setLogo(String logo);

	/**
	 * 連結目標,_blank,_self
	 * 
	 * @return
	 */
	String getTarget();

	void setTarget(String target);

	/**
	 * 圖片寬度
	 */
	int getImgWidth();

	void setImgWidth(int imgWidth);

	/**
	 * 圖片高度
	 */
	int getImgHeight();

	void setImgHeight(int imgHeight);

	/**
	 * 點擊次數
	 * 
	 * @return
	 */
	int getClick();

	void setClick(int click);

	/**
	 * 顯示次數
	 * 
	 * @return
	 */
	int getDisplay();

	void setDisplay(int display);

	/**
	 * 權重
	 * 
	 * @return
	 */
	int getWeight();

	void setWeight(int weight);

	/**
	 * 開始時間
	 * 
	 * @return
	 */
	Date getBegDate();

	void setBegDate(Date begDate);

	/**
	 * 結束時間
	 * 
	 * @return
	 */
	Date getEndDate();

	void setEndDate(Date endDate);

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);

	/**
	 * 廣告版位
	 * 
	 * @return
	 */
	AdSpace getAdSpace();

	void setAdSpace(AdSpace adSpace);

	/**
	 * 搜尋用,是否啟用選項
	 * 
	 * @return
	 */
	WhetherOption getValidOption();

	void setValidOption(WhetherOption validOption);
}
