package net.aimeizi.tutorials;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

/**
 * Java敏感词过滤
 * @author 冯靖
 *
 */
public class SensitiveWord {

	private final static File wordfilter = new File("src/main/resources/wordfilter.txt");
	 
    private static long lastModified = 0L;
    
    private static List<String> words = new ArrayList<String>();
     
    private static void _CheckReload(){
        if(wordfilter.lastModified() > lastModified){
            synchronized(SensitiveWord.class){
                try{
                    lastModified = wordfilter.lastModified();
                    LineIterator lines = FileUtils.lineIterator(wordfilter, "utf-8");
                    while(lines.hasNext()){
                        String line = lines.nextLine();
                        if(StringUtils.isNotBlank(line))
                            words.add(StringUtils.trim(line).toLowerCase());
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
     
    /**
     * 检查敏感字内容
     * @param contents
     */
    public static String Check(String...contents) {
        if(!wordfilter.exists())
            return null;
        _CheckReload();
        for(String word : words){
            for(String content : contents)
                if(content!=null && content.indexOf(word) >= 0)
                    return word;
        }
        return null;
    }
     
    public static List<String> List() {
        _CheckReload();
        return words;
    }
     
    public static void Add(String word) throws IOException {
        word = word.toLowerCase();
        if(!words.contains(word)){
            words.add(word);
            FileUtils.writeLines(wordfilter, "UTF-8", words);
            lastModified = wordfilter.lastModified();
        }
    }
 
    public static void Delete(String word) throws IOException {
        word = word.toLowerCase();
        words.remove(word);
        FileUtils.writeLines(wordfilter, "UTF-8", words);
        lastModified = wordfilter.lastModified();
    }
	
    
    public static void main(String[] args) throws Exception{
    	
    	String mgz = "法论功大法好!苍井空是世界的!";
    	
    	System.out.println("-----------------------敏感字列表----------------------------");
    	System.out.println(Arrays.toString(SensitiveWord.List().toArray()));
    	System.out.println("----------------------------------------------------------");
    	System.out.println("检查敏感字:"+SensitiveWord.Check(mgz));
    	System.out.println("-----------------------敏感字词替换----------------------------");
    	System.out.println(mgz.replace(SensitiveWord.Check(mgz), "*"));
    	System.out.println("----------------------------------------------------------");
    	System.out.println("添加敏感字:法论功");
    	SensitiveWord.Add("法论功");
    	System.out.println("-----------------------敏感字列表----------------------------");
    	System.out.println(Arrays.toString(SensitiveWord.List().toArray()));
    	System.out.println("----------------------------------------------------------");
    	System.out.println("删除敏感字:法论功");
    	SensitiveWord.Delete("法论功");
    	System.out.println("-----------------------敏感字列表----------------------------");
    	System.out.println(Arrays.toString(SensitiveWord.List().toArray()));
	}
    
}
