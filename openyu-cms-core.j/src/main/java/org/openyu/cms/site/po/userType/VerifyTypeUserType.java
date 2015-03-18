package org.openyu.cms.site.po.userType;

import java.sql.Types;

import org.hibernate.engine.spi.SessionImplementor;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.hibernate.userType.supporter.BaseUserTypeSupporter;

/**
 * 終審類別
 */
public class VerifyTypeUserType extends BaseUserTypeSupporter {

	private static final long serialVersionUID = -2698866489373512215L;

	public VerifyTypeUserType() {
		// --------------------------------------------------
		// 最新版本,目前用1,若將來有新版本
		// 可用其他版號,如:VolType._2
		// --------------------------------------------------
		setVolType(VolType._1);
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Class<?> returnedClass() {
		return VerifyType.class;
	}

	// --------------------------------------------------
	// assemble
	// --------------------------------------------------
	/**
	 * 由物件組成欄位
	 */
	@SuppressWarnings("unchecked")
	public <R, T> R marshal(T value, SessionImplementor session) {
		R result = null;
		if (!(value instanceof VerifyType)) {
			return result;
		}
		//
		VerifyType src = (VerifyType) value;
		StringBuilder dest = new StringBuilder();
		// vol
		dest.append(assembleVol(getVolType()));
		// v1
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
	public String assembleBy_1(VerifyType src) {
		StringBuilder result = new StringBuilder();
		//
		result.append(src.getValue());
		//
		return result.toString();
	}

	// --------------------------------------------------
	// disassemble
	// --------------------------------------------------
	/**
	 * 反欄位組成物件
	 */
	@SuppressWarnings("unchecked")
	public <R, T, O> R unmarshal(T value, O owner, SessionImplementor session) {
		VerifyType result = null;
		//
		if (!(value instanceof String)) {
			return (R) result;
		}
		//
		StringBuilder src = new StringBuilder((String) value);
		int vol = disassembleVol(src);
		VolType volType = EnumHelper.valueOf(VolType.class, vol);
		//
		if (volType == null) {
			return (R) result;
		}
		// v1
		if (volType.getValue() >= 1) {
			result = disassembleBy_1(src);
		}

		// v2,有新增的欄位,則繼續塞
		if (volType.getValue() >= 2) {

		}
		return (R) result;
	}

	public VerifyType disassembleBy_1(StringBuilder src) {
		VerifyType result = null;
		if (src == null) {
			return result;
		}
		//
		result = EnumHelper.valueOf(VerifyType.class,
				toObject(src.toString(), int.class));
		return result;
	}
}
