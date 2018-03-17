package com.itheima.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.itheima.listener.MyProgressListener;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String status = (String)session.getAttribute("progress");
		out.println(status);
		session.removeAttribute("progress");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setProgressListener(new MyProgressListener(request));
		try{
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item:items){
				if(!item.isFormField()){
					String fileName = getFileName(item);
					String storePath = getServletContext().getRealPath("/files");
					item.write(new File(storePath,fileName));
				}
			}
			out.println("<script type='text/javascript'>alert('上传成功！');history.back();</script>");
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//获取上传文件的文件名
	private String getFileName(FileItem item){
		String fileName = item.getName();
		System.out.println("上传的文件名是："+fileName);
		int lastIndex = fileName.lastIndexOf("\\");
		fileName = fileName.substring(lastIndex+1);
		System.out.println("截取的文件名是："+fileName);
		return fileName;
	}
}
