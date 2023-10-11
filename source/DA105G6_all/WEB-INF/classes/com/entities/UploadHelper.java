package com.entities;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class UploadHelper {
	public static String upload(String upload_dir, HttpServletRequest req) {
		String fileName = null;
		try {
			Part filePart = req.getPart("upload");
			fileName = getFileName(filePart);
			String applicationPath = req.getServletContext().getRealPath("");
			String basePath = applicationPath + File.separator + upload_dir + File.separator;
			InputStream in = null;
			OutputStream out = null;
			try {
				File outputFilePath = new File(basePath + fileName);
				in = filePart.getInputStream();
				out = new FileOutputStream(outputFilePath);
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = in.read(bytes)) != -1) {
					out.write(bytes);
				}
			} catch (Exception e) {
				fileName = null;
			} finally {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			}
		} catch (Exception ex) {
			fileName = null;
		}
		return fileName;
	}

	private static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
