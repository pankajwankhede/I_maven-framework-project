package net.aimeizi.tutorials.guava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.LineReader;
import com.google.common.primitives.Ints;

/**
 * Google Guava工具类
 * 
 * @author 冯靖
 * 
 */
public class GuavaTutorial {

	@Test
	public void example1() {

		// Map<String, Map<Long, List<String>>> map1 = new HashMap<String,
		// Map<Long,List<String>>>();

		ImmutableList<String> list = ImmutableList.of("小米2", "小米2S", "小米2A",
				"小米3");

		// 声明Map<String, Map<Long, List<String>>>
		// Map<String, Map<Long, List<String>>> map2 = Maps.newHashMap();

		// 赋值
		ImmutableMap<Long, List<String>> mp = ImmutableMap.of(1999l,
				(List<String>) list);

		System.out
				.println("-----------Map<String, Map<Long, List<String>>>---------");

		Set<Long> keySet = mp.keySet();
		for (Long log : keySet) {
			System.out.println("key:" + log);
			List<String> lt = mp.get(log);
			for (String string : lt) {
				System.out.println(string);
			}
		}

		// 声明List<Map<Long, List<String>>>
		// List<Map<Long, List<String>>> list = Lists.newArrayList();

		// 赋值
		ImmutableList<Map<Long, List<String>>> lst = ImmutableList
				.of((Map<Long, List<String>>) mp);

		System.out.println("-----------List<Map<Long, List<String>>>---------");

		for (Map<Long, List<String>> map : lst) {
			Set<Long> key = map.keySet();
			for (Long log : key) {
				System.out.println("key:" + log);
				List<String> lt = map.get(log);
				for (String string : lt) {
					System.out.println(string);
				}
			}
		}

		// 声明Set<Map<Long, List<String>>>
		// Set<Map<Long, List<String>>> sets = Sets.newHashSet();

		// 赋值
		ImmutableSet<Map<Long, List<String>>> st = ImmutableSet
				.of((Map<Long, List<String>>) mp);

		System.out.println("-----------Set<Map<Long, List<String>>>---------");

		for (Map<Long, List<String>> map : st) {
			Set<Long> key = map.keySet();
			for (Long log : key) {
				System.out.println("key:" + log);
				List<String> lt = map.get(log);
				for (String string : lt) {
					System.out.println(string);
				}
			}
		}

	}

	@Test
	public void example2() {

		List<String> list = new ArrayList<String>();

		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");

		// 直接为List赋值
		ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");

		for (String string : of) {
			System.out.println(string);
		}

		// 直接为Map赋值
		ImmutableMap<String, String> map = ImmutableMap.of("key1", "value1",
				"key2", "value2");

		System.out.println("-----------------------------------");

		ImmutableSet<String> keySet = map.keySet();

		for (String string : keySet) {
			System.out.println(string + ":" + map.get(string));
		}

		// 直接为Set赋值
		ImmutableSet<String> set = ImmutableSet.of("a", "b", "c", "d");

		System.out.println("-----------------------------------");

		for (String string : set) {
			System.out.println(string);
		}
	}

	@Test
	public void example3() {
		File file = new File("src/main/resources/sample.txt");
		List<String> lines = null;
		try {
			lines = Files.readLines(file, Charsets.UTF_8);
			for (String string : lines) {
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void example4() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		List<String> lines = CharStreams.readLines(new FileReader(file));
		for (String string : lines) {
			System.out.println(string);
		}
	}
	
	
	@Test
	public void example5() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		//读取内容到字符串中
		String string = CharStreams.toString(new FileReader(file));
		System.out.println(string);
	}
	
	/**
	 * 使用LineReader
	 * @throws Exception
	 */
	@Test
	public void example6() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		LineReader lineReader = new LineReader(new FileReader(file));
		for(String line = lineReader.readLine();line!=null;line=lineReader.readLine()){
			System.out.println(line);
		}
	}
	
	/**
	 * 将读取的内容拷贝到一个writer中去
	 * @throws Exception
	 */
	@Test
	public void example7() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		StringWriter writer = new StringWriter();
		FileWriter fileWriter = new FileWriter(new File("src/main/resources/sample-copy.txt"));
		CharStreams.copy(new FileReader(file), fileWriter);//写文件
		fileWriter.flush();
		fileWriter.close();
		System.out.println(writer.toString());
	}
	
	
	@Test
	public void example8() throws Exception{
		
		File file = new File("src/main/resources/sample.txt");
		
		String string = new String(ByteStreams.toByteArray(new FileInputStream(file)));
		
		System.out.println(string);
		
	}

	@Test
	public void example9() throws Exception{
		
		File file = new File("src/main/resources/sample.txt");
		
		//将文件输入流拷贝到PrintStream输出
		ByteStreams.copy(new FileInputStream(file), System.out);
		
	}
	
	
	/**
	 * 基本数据类型大小比较
	 */
	@Test
	public void example10(){
		int compare = Ints.compare(10, 59);// (a < b) ? -1 : ((a > b) ? 1 : 0)
		System.out.println(compare);
	}
	
	
	/**
	 * 将List<Integer>转化为int[]
	 */
	@Test
	public void example11(){
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
		int[] is = Ints.toArray(list);
		for (int i : is) {
			System.out.println(i);
		}
	}
	
	
	
	@Test
	public void example12() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		System.out.println(Files.toString(file, Charsets.UTF_8));
	}
	
	@Test
	public void example13() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		System.out.println(new String(Files.toByteArray(file)));
	}
	
	@Test
	public void example14() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		Files.write(Files.toString(file, Charsets.UTF_8), new File("src/main/resources/sample-copy.txt"), Charsets.UTF_8);
	}
	
	@Test
	public void example15() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		Files.copy(file, System.out);
	}
	
	@Test
	public void example16(){
		System.out.println(CharMatcher.DIGIT.retainFrom("some text 89983 and more"));//返回数字部分内容
		System.out.println(CharMatcher.DIGIT.removeFrom("some text 89983 and more"));//去除数字
		System.out.println(CharMatcher.DIGIT.replaceFrom("some text 89983 and more","#"));//将数字替换为#
	}
	
	/**
	 * 连接
	 */
	@Test
	public void example17(){
		int[] numbers = { 1, 2, 3, 4, 5 };
		String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
		System.out.println(numbersAsString);
		
		String numbersAsStringDirectly = Ints.join(";", numbers);
		System.out.println(numbersAsStringDirectly);
	}
	
	/**
	 * 字符串分割
	 */
	@Test
	public void example18(){
		String numbsAsString = "1,2,3,4,5";
		Iterable<String> split = Splitter.on(",").split(numbsAsString);
		for (String object : split) {
			System.out.println(object);
		}
	}
	
	/**
	 * 字符串分割
	 */
	@Test
	public void example19(){
		String testString = "foo , what,,,more,";  
		Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(testString);
		for (String object : split) {
			System.out.println(object);
		}
	}
	
	@Test
	public void example20(){
		
		int[] array1 = { 1, 2, 3, 4, 5 };
		int a = 4;
		
		boolean contains = Ints.contains(array1, a);//判断数组中是否存在a元素
		
		System.out.println(contains);
		
		int indexOf = Ints.indexOf(array1, a); //获取该元素在数组中的下标
		
		System.out.println(indexOf);
		
		int max = Ints.max(array1);//求数组中的最大值
		
		System.out.println(max);
		
		int min = Ints.min(array1);//求数组中的最小值
		
		System.out.println(min);
		
		int[] array2 = {6, 7, 8, 9, 10};
		
		int[] concat = Ints.concat(array1, array2 );//合并数组
		
		System.out.println(Arrays.toString(concat));
		
	}
	
	/**
	 * Set的交集, 并集, 差集的用法
	 */
	@Test
	public void example21(){
		HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
		HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);
		 
		SetView<Integer> union = Sets.union(setA, setB);//取并集
		System.out.println("并集union:");
		for (Integer integer : union){
			System.out.println(integer);
		}
		           
		 
		SetView<Integer> difference = Sets.difference(setA, setB);//取差集 (所有属于A不属于B的元素构成的集合)
		System.out.println("差集 difference:");
		for (Integer integer : difference){
			System.out.println(integer);
		}
		          
		 
		SetView<Integer> intersection = Sets.intersection(setA, setB);//取交集
		System.out.println("交集intersection:");
		for (Integer integer : intersection){
			System.out.println(integer);
		}
		    
	}
	
	@Test
	public void example22(){
		
		Map<Integer,String> mapA = ImmutableMap.of(123, "Jack Johnson", 456, "Cindy Lewis", 789, "Terry Peters", 912, "Ethan Nicks", 234, "Sarah Perry");
		Map<Integer,String> mapB = ImmutableMap.of(123, "Jack Johnson", 478, "Patrick Ewig", 789, "Cindy Peters", 937, "Jon Lund", 234, "Sarah Perry");
		
		MapDifference<Integer,String> differenceMap = Maps.difference(mapA, mapB); 
		
		System.out.println("判断两个Map是否相同:"+differenceMap.areEqual());
		
		System.out.println("-------------------------------------------------");
		
		Map<Integer,ValueDifference<String>> entriesDiffering = differenceMap.entriesDiffering(); 
		
		System.out.println("键相同,值不同:"+Arrays.toString(entriesDiffering.keySet().toArray()));
		
		Map<Integer,String> entriesOnlyOnLeft = differenceMap.entriesOnlyOnLeft();
		
		Set<Integer> keySet = entriesOnlyOnLeft.keySet();
		
		System.out.println("------------------------entriesOnlyOnLeft-------------------------");
		
		for (Integer integer : keySet) {
			System.out.println(integer+":"+entriesOnlyOnLeft.get(integer));
		}
		
		System.out.println("------------------------entriesOnlyOnRight-------------------------");
		
		Map<Integer,String> entriesOnlyOnRight = differenceMap.entriesOnlyOnRight(); 
		
		keySet = entriesOnlyOnRight.keySet();
		
		for (Integer integer : keySet) {
			System.out.println(integer+":"+entriesOnlyOnRight.get(integer));
		}
		
		System.out.println("-------------------------entriesInCommon------------------------");
		
		Map<Integer,String> entriesInCommon = differenceMap.entriesInCommon();//取两个map中键值都相同的元素
		
		keySet = entriesInCommon.keySet();
		
		for (Integer integer : keySet) {
			System.out.println(integer+":"+entriesInCommon.get(integer));
		}
		
	}
	
	@Test
	public void example23(){
		
	}
	
	@Test
	public void example24(){
		
	}
	
	@Test
	public void example25(){
		
	}
	
	@Test
	public void example26(){
		
	}
	@Test
	public void example27(){
		
	}
	
	@Test
	public void example28(){
		
	}
	
	@Test
	public void example29(){
		
	}
	
	@Test
	public void example30(){
		
	}
	
	@Test
	public void example31(){
		
	}
	
	@Test
	public void example32(){
		
	}
	
	@Test
	public void example33(){
		
	}
	
	@Test
	public void example34(){
		
	}
	
	@Test
	public void example35(){
		
	}
	
	@Test
	public void example36(){
		
	}
	
	@Test
	public void example37(){
		
	}
	
	@Test
	public void example38(){
		
	}
	
	@Test
	public void example39(){
		
	}
	
	@Test
	public void example40(){
		
	}
	
	@Test
	public void example41(){
		
	}
	
	
	
}
