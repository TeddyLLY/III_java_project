//package com.entities;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts2.ServletActionContext;
//
//
//public class Upload {
//	private File upload; // 檔案
//	private String uploadContentType; // 檔案型別
//	private String uploadFileName; // 檔名
// 
//	/**
//	 * 圖片上傳
//	 * 
//	 * @return
//	 * @throws IOException
//	 */
//	public String imgUpload() throws IOException {
// 
//		// 獲得response,request
//		HttpServletResponse response = ServletActionContext.getResponse();
//		HttpServletRequest request = ServletActionContext.getRequest();
// 
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter();
//		// CKEditor提交的很重要的一個引數
//		String callback = request.getParameter("CKEditorFuncNum");
//		String expandedName = ""; // 副檔名
//		if (uploadContentType.equals("image/pjpeg")
//				|| uploadContentType.equals("image/jpeg")) {
//			// IE6上傳jpg圖片的headimageContentType是image/pjpeg，而IE9以及火狐上傳的jpg圖片是image/jpeg
//			expandedName = ".jpg";
//		} else if (uploadContentType.equals("image/png")
//				|| uploadContentType.equals("image/x-png")) {
//			// IE6上傳的png圖片的headimageContentType是"image/x-png"
//			expandedName = ".png";
//		} else if (uploadContentType.equals("image/gif")) {
//			expandedName = ".gif";
//		} else if (uploadContentType.equals("image/bmp")) {
//			expandedName = ".bmp";
//		} else {
//			out.println("<script type=\"text/javascript\">");
//			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
//					+ ",''," + "'檔案格式不正確（必須為.jpg/.gif/.bmp/.png檔案）');");
//			out.println("</script>");
//			return null;
//		}
//		if (upload.length() > 600 * 1024) {
//			out.println("<script type=\"text/javascript\">");
//			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
//					+ ",''," + "'檔案大小不得大於600k');");
//			out.println("</script>");
//			return null;
//		}
// 
//		InputStream is = new FileInputStream(upload);
//		//圖片上傳路徑
//		String uploadPath = ServletActionContext.getServletContext().getRealPath("/img/uploadImg");
//		String fileName = java.util.UUID.randomUUID().toString(); // 採用時間+UUID的方式隨即命名
//		fileName += expandedName;
//		File file = new File(uploadPath);
//		if (!file.exists()) { // 如果路徑不存在，建立
//			file.mkdirs();
//		}
//		File toFile = new File(uploadPath, fileName);
//		OutputStream os = new FileOutputStream(toFile);
//		byte[] buffer = new byte[1024];
//		int length = 0;
//		while ((length = is.read(buffer)) > 0) {
//			os.write(buffer, 0, length);
//		}
//		is.close();
//		os.close();
// 
//		// 返回"影象"選項卡並顯示圖片  request.getContextPath()為web專案名 
//		out.println("<script type=\"text/javascript\">");
//		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
//				+ ",'" + request.getContextPath() + "/img/uploadImg/" + fileName + "','')");
//		out.println("</script>");
//		return null;
//	}
// 
//	public File getUpload() {
//		return upload;
//	}
// 
//	public void setUpload(File upload) {
//		this.upload = upload;
//	}
// 
//	public String getUploadContentType() {
//		return uploadContentType;
//	}
// 
//	public void setUploadContentType(String uploadContentType) {
//		this.uploadContentType = uploadContentType;
//	}
// 
//	public String getUploadFileName() {
//		return uploadFileName;
//	}
// 
//	public void setUploadFileName(String uploadFileName) {
//		this.uploadFileName = uploadFileName;
//	}
//}
