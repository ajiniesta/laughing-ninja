package com.iniesta.ninja;

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import java.io.File;
import java.util.Map;

public class DocumentTemplate {

	private Template template;

	public DocumentTemplate(File file)throws Exception{
		template = new SimpleTemplateEngine().createTemplate(file);
	}
	
	public String process(Map<String, Object> bindings){
		Writable make = template.make(bindings);
		return make.toString();
	}
	
}
