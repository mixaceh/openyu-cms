package org.openyu.cms.app.vo.supporter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openyu.cms.app.vo.AppResult;
import org.openyu.commons.bean.supporter.BaseBeanSupporter;

@XmlRootElement(name = "appResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppResultSupporter extends BaseBeanSupporter implements AppResult
{

	private static final long serialVersionUID = -387142316817761028L;

	public AppResultSupporter()
	{

	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		builder.appendSuper(super.toString());
		return builder.toString();
	}

	public Object clone()
	{
		AppResultSupporter copy = null;
		copy = (AppResultSupporter) super.clone();
		return copy;
	}

}
