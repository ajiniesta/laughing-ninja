package com.iniesta.ninja;

import static org.asciidoctor.Asciidoctor.Factory.create;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.asciidoctor.Asciidoctor;

public class First {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Asciidoctor asciidoctor = create();

//		String html = asciidoctor.convert("Writing AsciiDoc is _easy_!", Collections.EMPTY_MAP);
//		System.out.println(html);

//		URL resource = First.class.getClassLoader().getResource("first.adoc");
//		File file = new File("first.adoc");
//		List<File> fileList = Arrays.asList(file);
//		html = asciidoctor.renderFile(file, Collections.EMPTY_MAP);
//		
//		System.out.println(html);
		
		
//		String rendered = asciidoctor.render("*This* is it.", Collections.EMPTY_MAP);
//		System.out.println(rendered);
//		
//		rendered = asciidoctor.renderFile(new File("first.adoc"), new HashMap<String, Object>());
//		System.out.println(rendered);
//		
//		for (String html_chunk : result) {
//			System.out.println(html_chunk);
//		}
		
		FileReader reader = new FileReader(new File("first.adoc"));
		StringWriter writer = new StringWriter();

		asciidoctor.convert(reader, writer, Collections.EMPTY_MAP);

		StringBuffer htmlBuffer = writer.getBuffer();
		System.out.println(htmlBuffer.toString());
	}

}
