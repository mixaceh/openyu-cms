package org.openyu.cms.vote.po.userType;

import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.impl.VoteItemImpl;
import org.openyu.commons.entity.usertype.NamesBeanUserType;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.hibernate.usertype.supporter.BaseUserTypeSupporter;
import org.openyu.commons.lang.ArrayHelper;

public class VoteItemsUserType extends BaseUserTypeSupporter {

	private static final long serialVersionUID = 9131955775496679530L;

	private static transient NamesBeanUserType namesBeanUserType = new NamesBeanUserType();

	public VoteItemsUserType() {
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
		@SuppressWarnings("rawtypes")
		Map<String, VoteItem> src = (Map<String, VoteItem>) value;
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
	public String assembleBy_1(Map<String, VoteItem> src) {
		StringBuilder result = new StringBuilder();
		//
		result.append(src.size());
		for (Map.Entry<String, VoteItem> entry : src.entrySet()) {
			String key = entry.getKey();
			VoteItem value = entry.getValue();
			//
			result.append(PARENT_OBJECT_SPLITTER);
			// key(id)
			result.append(toString(key));// e0
			result.append(PARENT_ENTRY_SPLITTER);
			// names
			result.append(namesBeanUserType.assembleBy_1(value.getNames()));// e1
			result.append(PARENT_ENTRY_SPLITTER);
			// vote count
			result.append(toString(value.getVoteCount()));// e2
			result.append(PARENT_ENTRY_SPLITTER);
			// sort
			result.append(toString(value.getSort()));// e3
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
		Map<String, VoteItem> result = new LinkedHashMap<String, VoteItem>();
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
	public Map<String, VoteItem> disassembleBy_1(StringBuilder src) {
		Map<String, VoteItem> result = new LinkedHashMap<String, VoteItem>();
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
		int size = toObject(values, idx++, int.class);// 0
		//
		for (int i = 0; i < size; i++)// 1
		{
			String eValue = ArrayHelper.get(values, idx++);
			String[] entryValues = StringUtils.splitPreserveAllTokens(eValue,
					PARENT_ENTRY_SPLITTER);
			if (ArrayHelper.isEmpty(entryValues)) {
				continue;
			}
			int edx = 0;
			VoteItem voteItem = new VoteItemImpl();
			String key = toObject(entryValues, edx++, String.class);
			voteItem.setId(key);
			//
			StringBuilder buff = new StringBuilder();
			buff.append(toObject(entryValues, edx++, String.class));
			voteItem.setNames(namesBeanUserType.disassembleBy_1(buff));// e1
			//
			voteItem.setVoteCount(toObject(entryValues, edx++, Integer.class));// e2
			voteItem.setSort(toObject(entryValues, edx++, Integer.class));// e3
			//
			result.put(voteItem.getId(), voteItem);
		}
		return result;
	}
}
