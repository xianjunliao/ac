package com.ac;

import com.ac.util.baiduTrans.TransApi;




public class test {

	public static void main(String[] args) {
		
		
		TransApi api=new TransApi();
		String transResult = api.getTransResult("Stephen", "auto", "auto");
		System.out.println(transResult);
		
		
	}

}
