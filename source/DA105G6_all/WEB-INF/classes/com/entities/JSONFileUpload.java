package com.entities;

import java.io.Serializable;

public class JSONFileUpload implements Serializable {

	private static final long serialVersionUID = 1L;
	private String path;

	public JSONFileUpload() {
		super();
 
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public JSONFileUpload(String path) {
		super();
		this.path = path;
	}

}
