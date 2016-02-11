package org.openyu.cms.keyword.po.userType;

import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.hibernate.usertype.supporter.BaseUserTypeSupporter;
import org.openyu.commons.lang.ArrayHelper;

public class StringDictionaryUserType extends BaseUserTypeSupporter {

	private static final long serialVersionUID = -5580576101039934135L;

	public StringDictionaryUserType() {
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
		return Map.class;
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
		if (!(value instanceof Map)) {
			return result;
		}
		//
		StringBuilder dest = new StringBuilder();
		Map<String, Dictionary> src = (Map<String, Dictionary>) value;
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
	public String assembleBy_1(Map<String, Dictionary> src) {
		StringBuilder result = new StringBuilder();
		//
		result.append(src.size());
		for (Map.Entry<String, Dictionary> entry : src.entrySet()) {
			String key = entry.getKey();
			Dictionary value = entry.getValue();
			//
			result.append(OBJECT_SPLITTER);
			// 關鍵字
			result.append(toString(key));// e0
			result.append(ENTRY_SPLITTER);
			// 替換字
			result.append(toString(value.getValue()));// e1
			result.append(ENTRY_SPLITTER);
			// 啟用
			result.append(toString(value.getValid()));// e2
		}
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
		// 預設傳回空物件,非null
		Map<String, Dictionary> result = new LinkedHashMap<String, Dictionary>();
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
	public Map<String, Dictionary> disassembleBy_1(StringBuilder src) {
		Map<String, Dictionary> result = new LinkedHashMap<String, Dictionary>();
		if (src == null) {
			return result;
		}
		//
		String[] values = StringUtils.splitPreserveAllTokens(src.toString(),
				OBJECT_SPLITTER);
		if (ArrayHelper.isEmpty(values)) {
			return result;
		}
		//
		int idx = 0;
		int size = toObject(values, idx++, int.class);// 0
		//
		for (int i = 0; i < size; i++)// 1
		{
			String eValue = ArrayHelper.get(values, idx++);
			String[] entryValues = StringUtils.splitPreserveAllTokens(eValue,
					ENTRY_SPLITTER);
			if (ArrayHelper.isEmpty(entryValues)) {
				continue;
			}
			int edx = 0;
			Dictionary entry = new DictionaryImpl();
			// 關鍵字
			entry.setKey(toObject(entryValues, edx++, String.class));
			// 替換字
			entry.setValue(toObject(entryValues, edx++, String.class));
			// 啟用
			entry.setValid(toObject(entryValues, edx++, boolean.class));
			//
			result.put(entry.getKey(), entry);
		}
		return result;
	}
}
