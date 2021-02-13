package com.chitranshu.xmeme.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemeRequest {

	/*
	 * To accept the request from RequestBody.
	 */

	private String name;
	private String url;
	private String caption;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String imageUrl) {
		this.url = imageUrl;
	}

}
