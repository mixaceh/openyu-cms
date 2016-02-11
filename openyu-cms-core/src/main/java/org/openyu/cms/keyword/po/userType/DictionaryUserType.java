package org.openyu.cms.keyword.po.userType;

import java.sql.Types;

import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.hibernate.usertype.supporter.BaseUserTypeSupporter;
import org.openyu.commons.lang.ArrayHelper;

public class DictionaryUserType extends BaseUserTypeSupporter {

	private static final long serialVersionUID = -250596582570861562L;

	public DictionaryUserType() {
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
		return Dictionary.class;
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
		if (!(value instanceof Dictionary)) {
			return result;
		}
		//
		StringBuilder dest = new StringBuilder();
		Dictionary src = (Dictionary) value;
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
	 * @param src
	 * @return
	 */
	public String assembleBy_1(Dictionary src) {
		StringBuilder result = new StringBuilder();
		// 關鍵字
		result.append(toString(src.getKey()));// 0
		result.append(SPLITTER);
		// 替換字
		result.append(toString(src.getValue()));// 1
		result.append(SPLITTER);
		// 啟用
		result.append(toString(src.getValid()));// 2
		//
		return result.toString();
	}

	// --------------------------------------------------
	// disassemble
	// --------------------------------------------------
	/**
	 * 由欄位反組成物件
	 */
	@SuppressWarnings("unchecked")
	public <R, T, O> R unmarshal(T value, O owner, SessionImplementor session) {
		Dictionary result = null;
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

	/**
	 * v1 由欄位反組成物件
	 */
	public Dictionary disassembleBy_1(StringBuilder src) {
		Dictionary result = null;
		if (src == null) {
			return result;
		}
		//
		String[] values = StringUtils.splitPreserveAllTokens(src.toString(),
				SPLITTER);
		if (ArrayHelper.isEmpty(values)) {
			return result;
		}
		//
		int idx = 0;
		result = new DictionaryImpl();
		// 關鍵字
		result.setKey(toObject(values, idx++, String.class));
		// 替換字
		result.setValue(toObject(values, idx++, String.class));
		// 啟用
		result.setValid(toObject(values, idx++, boolean.class));
		//
		return result;
	}
}
