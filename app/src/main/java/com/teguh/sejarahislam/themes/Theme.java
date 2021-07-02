package com.teguh.sejarahislam.themes;

import java.util.List;

public class Theme {

	public int id;
	public String name;
	public String backgroundImageUrl;
	public List<String> tileImageUrls;
	public List<String> adKeywords;
	public String backgroundSoundUrl;
	public String clickSoundUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
