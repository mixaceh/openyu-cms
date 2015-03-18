package org.openyu.cms.site.po.userType;

import java.sql.Types;

import org.hibernate.engine.spi.SessionImplementor;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.hibernate.userType.supporter.BaseUserTypeSupporter;

/**
 * 審核後類別
 */
public class ModifyTypeUserType extends BaseUserTypeSupporter
{

	private static final long serialVersionUID = -4106267530655149467L;

	public ModifyTypeUserType()
	{
		//--------------------------------------------------
		//最新版本,目前用1,若將來有新版本
		//可用其他版號,如:VolType._2
		//--------------------------------------------------
		setVolType(VolType._1);
	}

	@Override
	public int[] sqlTypes()
	{
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Class<?> returnedClass()
	{
		return ModifyType.class;
	}

	//--------------------------------------------------
	//assemble
	//--------------------------------------------------
	/**
	 * 由物件組成欄位
	 */
	@SuppressWarnings("unchecked")
	public <R, T> R marshal(T value, SessionImplementor session) {
		R result = null;
		if (!(value instanceof ModifyType))
		{
			return result;
		}
		//
		ModifyType src = (ModifyType) value;
		StringBuilder dest = new StringBuilder();
		//vol
		dest.append(assembleVol(getVolType()));
		//v1
		dest.append(assembleBy_1(src));
		//
		result = (R) dest.toString();
		return result;
	}

	/**
	 * v1 由物件組成欄位
	 * 
	 * @param value
	 * @return
	 */
	public String assembleBy_1(ModifyType src)
	{
		StringBuilder result = new StringBuilder();
		//
		result.append(src.getValue());
		//
		return result.toString();
	}

	//--------------------------------------------------
	//disassemble
	//--------------------------------------------------
	/**
	 * 反欄位組成物件
	 */
	@SuppressWarnings("unchecked")
	public <R, T, O> R unmarshal(T value, O owner, SessionImplementor session) {
		ModifyType result = null;
		//
		if (!(value instanceof String))
		{
			return (R) result;
		}
		//
		StringBuilder src = new StringBuilder((String) value);
		int vol = disassembleVol(src);
		VolType volType = EnumHelper.valueOf(VolType.class, vol);
		//
		if (volType == null)
		{
			return (R) result;
		}
		//v1
		if (volType.getValue() >= 1)
		{
			result = disassembleBy_1(src);
		}

		//v2,有新增的欄位,則繼續塞
		if (volType.getValue() >= 2)
		{

		}
		return (R) result;
	}

	public ModifyType disassembleBy_1(StringBuilder src)
	{
		ModifyType result = null;
		if (src == null)
		{
			return result;
		}
		//
		result = EnumHelper.valueOf(ModifyType.class, toObject(src.toString(), int.class));
		return result;
	}
}
