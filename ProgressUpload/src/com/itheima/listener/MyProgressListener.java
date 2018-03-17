package com.itheima.listener;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class MyProgressListener implements ProgressListener {
	private double megaBytes = -1;//���ֽ�
	private HttpSession session;
	public MyProgressListener(HttpServletRequest request){
		session = request.getSession();
	}
	public void update(long pBytesRead, long pContentLength, int pItems) {
		double mBytes = pBytesRead/1000000;//�Ѷ�ȡ���ֽ���ת��ΪM�ֽ�
		double total = pContentLength/1000000;//�ϴ��ļ��Ĵ�Сת��ΪM�ֽ�
		if(megaBytes == mBytes)
			return;
		System.out.println("total===>"+total);
		System.out.println("mBytes===>"+mBytes);
		megaBytes = mBytes;
		System.out.println("megaBytes===>"+megaBytes);
		System.out.println("���ڶ�ȡ�ڼ��"+pItems);
		if(pContentLength==-1){
			System.out.println("So far, " + pBytesRead + " bytes have been read.");  
		}else{
			System.out.println("So far, " + pBytesRead + " of " + pContentLength  
                    + " bytes have been read.");  
			double read = (mBytes/total);
			NumberFormat nf = NumberFormat.getPercentInstance();
			System.out.println("read===>"+nf.format(read));//��ӡ�ٷֱ�
			session.setAttribute("progress", nf.format(read));//�Ѱٷֱȴ浽session��
		}
	}

}
