package org.openyu.cms.module.po.userType;

import java.sql.Types;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.ModuleItem.ColumnType;
import org.openyu.cms.module.vo.impl.CatalogItemImpl;
import org.openyu.commons.entity.usertype.NamesBeanUserType;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.hibernate.usertype.supporter.BaseUserTypeSupporter;
import org.openyu.commons.lang.ArrayHelper;

public class CatalogItemsUserType extends BaseUserTypeSupporter {

	private static final long serialVersionUID = -2066924784420555409L;

	private static transient NamesBeanUserType namesBeanUserType = new NamesBeanUserType();

	public CatalogItemsUserType() {
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
		return Set.class;
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
		if (!(value instanceof Set)) {
			return result;
		}
		//
		StringBuilder dest = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Set<CatalogItem> src = (Set) value;
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
	public String assembleBy_1(Set<CatalogItem> src) {
		StringBuilder result = new StringBuilder();
		result.append((src != null ? src.size() : 0));// 0
		for (CatalogItem entry : src) {
			result.append(PARENT_OBJECT_SPLITTER);
			result.append(toString(entry.getId()));// e0
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(namesBeanUserType.assembleBy_1(entry.getNames()));// e1
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getSort()));// e2
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getDft()));// e3
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getOption()));// e4
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getLength()));// e5
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getRows()));// e6
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getCols()));// e7
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getHelp()));// e8
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getHelpPosition()));// e9
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getColumnType()));// e10
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getSingle()));// e11
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getCustom()));// e12
			result.append(PARENT_ENTRY_SPLITTER);
			result.append(toString(entry.getDisplay()));// e13
		}
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
		Set<CatalogItem> result = new LinkedHashSet<CatalogItem>();
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
	public Set<CatalogItem> disassembleBy_1(StringBuilder src) {
		Set<CatalogItem> result = new LinkedHashSet<CatalogItem>();
		if (src == null) {
			return result;
		}
		//
		String[] values = StringUtils.splitPreserveAllTokens(src.toString(),
				PARENT_OBJECT_SPLITTER);
		if (ArrayHelper.isEmpty(values)) {
			return result;
		}
		//
		int idx = 0;
		int size = toObject(values, idx++, int.class);
		//
		for (int i = 0; i < size; i++) {
			String eValue = ArrayHelper.get(values, idx++);
			String[] entryValues = StringUtils.splitPreserveAllTokens(eValue,
					PARENT_ENTRY_SPLITTER);
			if (ArrayHelper.isEmpty(entryValues)) {
				continue;
			}
			int edx = 0;
			CatalogItem entry = new CatalogItemImpl();
			//
			entry.setId(toObject(entryValues, edx++, String.class));// e0
			//
			StringBuilder buff = new StringBuilder();
			buff.append(toObject(entryValues, edx++, String.class));
			entry.setNames(namesBeanUserType.disassembleBy_1(buff));// e1

			entry.setSort(toObject(entryValues, edx++, Integer.class));// e2
			entry.setDft(toObject(entryValues, edx++, String.class));// e3
			entry.setOption(toObject(entryValues, edx++, String.class));// e4
			entry.setLength(toObject(entryValues, edx++, String.class));// e5
			entry.setRows(toObject(entryValues, edx++, String.class));// e6
			entry.setCols(toObject(entryValues, edx++, String.class));// e7
			entry.setHelp(toObject(entryValues, edx++, String.class));// e8
			entry.setHelpPosition(toObject(entryValues, edx++, String.class));// e9
			//
			ColumnType columnType = EnumHelper.valueOf(ColumnType.class,
					toObject(entryValues, edx++, int.class));
			entry.setColumnType(columnType);// e10
			//
			entry.setSingle(toObject(entryValues, edx++, boolean.class));// e11
			entry.setCustom(toObject(entryValues, edx++, boolean.class));// e12
			entry.setDisplay(toObject(entryValues, edx++, boolean.class));// e13
			//
			result.add(entry);
		}
		return result;
	}
}
