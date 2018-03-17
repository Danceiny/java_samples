package com.itheima.listener;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class MyProgressListener implements ProgressListener {
	private double megaBytes = -1;//兆字节
	private HttpSession session;
	public MyProgressListener(HttpServletRequest request){
		session = request.getSession();
	}
	public void update(long pBytesRead, long pContentLength, int pItems) {
		double mBytes = pBytesRead/1000000;//已读取的字节数转换为M字节
		double total = pContentLength/1000000;//上传文件的大小转化为M字节
		if(megaBytes == mBytes)
			return;
		System.out.println("total===>"+total);
		System.out.println("mBytes===>"+mBytes);
		megaBytes = mBytes;
		System.out.println("megaBytes===>"+megaBytes);
		System.out.println("正在读取第几项："+pItems);
		if(pContentLength==-1){
			System.out.println("So far, " + pBytesRead + " bytes have been read.");  
		}else{
			System.out.println("So far, " + pBytesRead + " of " + pContentLength  
                    + " bytes have been read.");  
			double read = (mBytes/total);
			NumberFormat nf = NumberFormat.getPercentInstance();
			System.out.println("read===>"+nf.format(read));//打印百分比
			session.setAttribute("progress", nf.format(read));//把百分比存到session中
		}
	}

}
