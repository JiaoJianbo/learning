package com.bravo.mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.junit.Test;

public class TestList {
	@Test (expected = ClassCastException.class)
	public void testSubList() {
		// ArrayList的subList 结果不可强转成 ArrayList ，否则会抛出 ClassCastException异常，
		//即 java.util.RandomAccessSubList cannot be cast to java.util.ArrayList 
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		list.add("2");
		System.out.println(list);
		
		List<String> subList = list.subList(0, 2);
		System.out.println(subList);
		
		ArrayList<String> alist = (ArrayList<String>)subList;
		System.out.println(alist);
	}
	
	@Test (expected = ConcurrentModificationException.class)
	public void testSubListUpdate() {
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		list.add("2");
		System.out.println(list);
		
		List<String> subList = list.subList(0, 2);
		System.out.println(subList);
		
		//对于SubList子列表的所有操作最终会反映到元列表上
		subList.add("3");
		System.out.println(subList);
		System.out.println(list);
		
		//在 subList 场景中，高度注意对原集合元素的增加或删除，均会导致子列表的遍历 、增加、删除产生 ConcurrentModificationException 异常
		list.remove(1);
		System.out.println(subList); //got ConcurrentModificationException
		System.out.println(list);
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testArrays() {
		//使用工具类 Arrays . asList() 把数组转换成集合时，不能使用其修改集合相关的方法，
		//它的 add / remove / clear 方法会抛出 UnsupportedOperationException 异常
		String[] strArray = new String[] {"0", "1", "2"};
		List<String> list = Arrays.asList(strArray);
		
		strArray[0] = "00"; //那么 list.get(0) 也会随之修改
		System.out.println(list);
		
		list.add("3"); //got UnsupportedOperationException
		System.out.println(list);
	}
	
	@Test
	public void testRemove() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		
		for(String s : list) {
			if("2".equals(s)) {
				list.remove(s);
			}
		}
		System.out.println(list);
	}
}
