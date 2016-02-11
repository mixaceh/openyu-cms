package org.openyu.cms.keyword.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.keyword.vo.impl.KeywordImpl;
import org.openyu.cms.keyword.KeywordTestSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;

public class KeywordServiceImplTest extends KeywordTestSupporter
{
	/**
	 * 隨機產生群組資料
	 * 
	 * @return
	 */
	public static Keyword randomKeyword()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_KEYWORD" + UNIQUE;
		//
		Keyword result = new KeywordImpl();
		result.setId(ID);
		Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();
		int count = 500000;
		for (int i = 0; i < count; i++)
		{
			Dictionary dictionary = new DictionaryImpl();
			String key = randomAlphabet(5);
			dictionary.setKey(key);
			dictionary.setValue(randomAlphabet(5));
			dictionary.setValid(randomBoolean());
			dictionarys.put(key, dictionary);
		}
		result.setDictionarys(dictionarys);
		result.setLocale(Locale.TRADITIONAL_CHINESE);
		//		result.setLocale(Locale.SIMPLIFIED_CHINESE);
		//		result.setLocale(Locale.US);
		//
		return result;
	}

	/**
	 * 檢核群組資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertKeyword(Keyword expected, Keyword actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getDictionarys(), actual.getDictionarys());
		assertEquals(expected.getLocale(), actual.getLocale());
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void replaceKeyword()
	{

		String result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = keywordService.replaceKeyword("9FsTWFJNtPtY12c");
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//2XhX6XKyTLsuoa8
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	//1 times: 660 mills. 
	//2 times: 55 mills. 
	//3 times: 131 mills. 
	//verified: ok
	public void findKeyword()
	{

		List<Keyword> result = null;
		Inquiry inquiry = new InquiryImpl();
		Pagination pagination = inquiry.getPagination();
		//0
		pagination.setPageIndex(0);//跳到第幾頁

		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = keywordService.findKeyword(inquiry, Locale.TRADITIONAL_CHINESE);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println("size: " + result.size());
		System.out.println("dictionarys.size: " + result.get(0).getDictionarys().size());
		System.out.println(result);

		//		//1
		//		count = 2;
		//		pagination.setPageIndex(1);//跳到第幾頁
		//		beg = System.currentTimeMillis();
		//		for (int i = 0; i < count; i++)
		//		{
		//			result = keywordService.findKeyword(inquiry, Locale.TRADITIONAL_CHINESE);
		//		}
		//		end = System.currentTimeMillis();
		//		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		//		System.out.println("size: " + result.size());
		//		System.out.println("dictionarys.size: " + result.get(0).getDictionarys().size());
		//		System.out.println(result);
		//
		//		//555
		//		count = 3;
		//		pagination.setPageIndex(555);//跳到第幾頁
		//		beg = System.currentTimeMillis();
		//		for (int i = 0; i < count; i++)
		//		{
		//			result = keywordService.findKeyword(inquiry, Locale.TRADITIONAL_CHINESE);
		//		}
		//		end = System.currentTimeMillis();
		//		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		//		System.out.println("size: " + result.size());
		//		System.out.println("dictionarys.size: " + result.get(0).getDictionarys().size());
		//		System.out.println(result);
		//
		//		assertNotNull(result);
	}

	@Test
	//1 times: 660 mills. 
	//2 times: 55 mills. 
	//3 times: 131 mills. 
	//verified: ok
	public void orderBy()
	{
		//		LinkedHashMap < String, Person > newMap = getOrder(map);
		//隨機
		Keyword keyword = randomKeyword();
//		System.out.println("old :" + keyword);
		int count = 1;
		long beg = System.currentTimeMillis();
		keyword.setDictionarys(getOrderByKey(keyword.getDictionarys(),"ASC"));
		//		keyword.getDictionarys() = new TreeMap(hashmap);
		long end = System.currentTimeMillis();

		System.out.println(count + " times: " + (end - beg) + " mills. ");
		System.out.println("dictionarys.size: " + keyword.getDictionarys().size());
//		System.out.println("map :" + keyword);
		
		count = 2;
		beg = System.currentTimeMillis();
		keyword.setDictionarys(getOrderByKey(keyword.getDictionarys(),"DESC"));
		end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		System.out.println("dictionarys.size: " + keyword.getDictionarys().size());
//		System.out.println("map :" + keyword);
		
		//order by value
		count = 3;
		beg = System.currentTimeMillis();
		keyword.setDictionarys(getOrderByValue(keyword.getDictionarys(), ""));
		end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		System.out.println("dictionarys.size: " + keyword.getDictionarys().size());
//		System.out.println("map :" + keyword);
		
		count = 4;
		beg = System.currentTimeMillis();
		keyword.setDictionarys(getOrderByValue(keyword.getDictionarys(), "DESC"));
		end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		System.out.println("dictionarys.size: " + keyword.getDictionarys().size());
//		System.out.println("map :" + keyword);
	}

	//default order asc
	public static LinkedHashMap<String, Dictionary> getOrderByKey(Map<String, Dictionary> map, final String order)
	{
		List<Map.Entry<String, Dictionary>> infoIds = new ArrayList<Map.Entry<String, Dictionary>>(
			map.entrySet());

		// 排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Dictionary>>()
		{
			public int compare(Map.Entry<String, Dictionary> o1, Map.Entry<String, Dictionary> o2)
			{
				String p1 = (String) o1.getKey();
				String p2 = (String) o2.getKey();
//				System.out.println("def 1:"+p1.compareTo(p2));
//				System.out.println("2:"+p2.compareTo(p1));
				//return p1.compareTo(p2);
				if (order.equals("DESC"))
					return p2.compareTo(p1);
				else 
					return p1.compareTo(p2);
			}
		}
		);

		/* 轉換成新map輸出*/
		LinkedHashMap<String, Dictionary> newMap = new LinkedHashMap<String, Dictionary>();

		for (Map.Entry<String, Dictionary> entity : infoIds)
		{
			newMap.put(entity.getKey(), entity.getValue());
		}

		return newMap;
	}
	
	public static LinkedHashMap<String, Dictionary> getOrderByValue(Map<String, Dictionary> map, final String order)
	{
		List<Map.Entry<String, Dictionary>> infoIds = new ArrayList<Map.Entry<String, Dictionary>>(
			map.entrySet());

		// 排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Dictionary>>()
		{
			public int compare(Map.Entry<String, Dictionary> o1, Map.Entry<String, Dictionary> o2)
			{
				String p1 = (String) o1.getValue().getValue();
				String p2 = (String) o2.getValue().getValue();

				if (order.equals("DESC"))
					return p2.compareTo(p1);
				else 
					return p1.compareTo(p2);
//				return p1.compareTo(p2);
			}
		});

		/* 轉換成新map輸出*/
		LinkedHashMap<String, Dictionary> newMap = new LinkedHashMap<String, Dictionary>();

		for (Map.Entry<String, Dictionary> entity : infoIds)
		{
			newMap.put(entity.getKey(), entity.getValue());
		}

		return newMap;
	}
	
	@Test
	//1 times: 660 mills. 
	//2 times: 55 mills. 
	//3 times: 131 mills. 
	//verified: ok
	public void search()
	{
		Keyword keyword = randomKeyword();
		System.out.println("dictionarys.size: " + keyword.getDictionarys().size());
		int count = 1;
		long beg = System.currentTimeMillis();
		Dictionary searcher = new DictionaryImpl();
		searcher.setKey("aa");
		
		Map<String, Dictionary> buffs = new LinkedHashMap<String, Dictionary>();
		
		for (String key : keyword.getDictionarys().keySet())
		{
			if (key.toLowerCase().indexOf(searcher.getKey().toLowerCase()) > -1)
			{
				buffs.put(key, keyword.getDictionarys().get(key));
			}
		}
		
		long end = System.currentTimeMillis();

		System.out.println(count + " times: " + (end - beg) + " mills. ");
		System.out.println("buffs.size: " + buffs.size());
		System.out.println("map :" + buffs);
	}
}
