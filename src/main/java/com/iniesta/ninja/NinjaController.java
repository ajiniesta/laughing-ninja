package com.iniesta.ninja;

import static org.asciidoctor.Asciidoctor.Factory.create;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.Collections;

import org.asciidoctor.Asciidoctor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class NinjaController {

	@SuppressWarnings("unchecked")
	@RequestMapping("/")
	@ResponseBody
	String home() throws Exception{
		Asciidoctor asciidoctor = create();

		String html = "";
		FileReader reader = new FileReader(new File("first.adoc"));
		StringWriter writer = new StringWriter();

		asciidoctor.convert(reader, writer, Collections.EMPTY_MAP);

		StringBuffer htmlBuffer = writer.getBuffer();
		
		html = htmlBuffer.toString();
		
		return html;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NinjaController.class, args);
	}
}

