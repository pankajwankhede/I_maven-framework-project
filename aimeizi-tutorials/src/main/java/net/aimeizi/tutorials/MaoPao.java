package net.aimeizi.tutorials;

import java.util.Arrays;

public class MaoPao {

	public static void main(String args[]) {
		int temp;
		int[] s = { 23, 5, 12, 59, 78, 21, 100, 79, 66 ,200,365,420,862};
		for(int j=1;j<=s.length;j++){
			for(int i=0;i<s.length-1;i++){
				if(s[i]>s[i+1]){
					temp=s[i];
					s[i]=s[i+1];
					s[i+1]=temp;
				}
			}
		}
		System.out.println("最终顺序:-------------------");
		System.out.println(Arrays.toString(s));
	}
}