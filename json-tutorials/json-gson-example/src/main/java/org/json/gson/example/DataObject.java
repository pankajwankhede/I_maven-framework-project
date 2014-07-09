package org.json.gson.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class DataObject implements Serializable{

	private int data1 = 100;
	private String data2 = "hello";
	
	private List<String> list = new ArrayList<String>() {
		{
			add("String 1");
			add("String 2");
			add("String 3");
		}
	};

	@Override
	public String toString() {
		return "DataObject [data1=" + data1 + ", data2=" + data2 + ", list="
				+ list + "]";
	}

	public int getData1() {
		return data1;
	}

	public void setData1(int data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}
