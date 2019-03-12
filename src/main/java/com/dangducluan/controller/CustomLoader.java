package com.dangducluan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class CustomLoader implements ResourceLoaderAware{

	@Autowired
	private ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		this.resourceLoader = resourceLoader;
	}
	public Resource getResource(String path)
	{
		return this.resourceLoader.getResource(path);
	}

}
