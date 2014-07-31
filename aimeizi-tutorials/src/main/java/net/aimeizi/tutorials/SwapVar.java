package net.aimeizi.tutorials;

/**
 * 交换两个数的值,不借助于第三个数
 * @author 冯靖
 *
 */
public class SwapVar {

	private static void swap1(int a,int b){
	      System.out.println("交换前a,b的值:"+a+","+b);
	      a=a+b-(b=a);
	      System.out.println("交换后a,b的值:"+a+","+b);
	  }
	  
	  private static void swap2(int a,int b){
	      System.out.println("交换前a,b的值:"+a+","+b);
	      b=a+(a=b)*0;
	      System.out.println("交换后a,b的值:"+a+","+b);
	  }
	  
	  private static void swap3(int a,int b){
	      System.out.println("交换前a,b的值:"+a+","+b);
	      a=a*b;
	      b=a/b;
	      a=a/b;
	      System.out.println("交换后a,b的值:"+a+","+b);
	  }
	  
	  private static void swap4(int a,int b){
	      System.out.println("交换前a,b的值:"+a+","+b);
	      a=a+b;
	      b=a-b;
	      a=a-b;
	      System.out.println("交换后a,b的值:"+a+","+b);
	  }
	  
	  private static void swap5(int a,int b){
	      System.out.println("交换前a,b的值:"+a+","+b);
	      a=a^b;
	      b=a^b;
	      a=a^b;
	      System.out.println("交换后a,b的值:"+a+","+b);
	  }
	  
	  public static void main(String[] args) {
	      swap1(10, 20);
	      System.out.println("------------------------------");
	      swap2(30, 40);
	      System.out.println("------------------------------");
	      swap3(50, 60);
	      System.out.println("------------------------------");
	      swap4(70, 80);
	      System.out.println("------------------------------");
	      swap5(90, 100);
	  }

}
