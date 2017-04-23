package com.caden.cognitionTraining2.dip;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dip")
public class DIPServiceProperties {

	/**
	 * Folder location for storing files
	 */
	private String rootLocation = "src/main/resources/static/upload-dir";
	private String tempLocation = "src/main/resources/static/upload-dir-temp";

	public String getRootLocation() {
		return rootLocation;
	}

	public void setRootLocation(String rootLocation) {
		this.rootLocation = rootLocation;
	}

	public String getTempLocation() {
		return tempLocation;
	}

	public void setTempLocation(String tempLocation) {
		this.tempLocation = tempLocation;
	}

}
