package com.iniesta.ninja;

import static org.asciidoctor.Asciidoctor.Factory.create;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.asciidoctor.AsciiDocDirectoryWalker;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.DirectoryWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class NinjaController {

	@RequestMapping("/")
	@ResponseBody
	String home() throws Exception{
		String html = "";
		Map<String, Object> bindings = new HashMap<String, Object>();
		bindings.putAll(defaultBindings);
		bindings.put("adoc", html);
		html = dt.process(bindings);
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/index.html");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		while((line = reader.readLine())!=null){
			html += line;
		}
		reader.close();
		return html;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	String processPage(@PathVariable("id") String id) throws Exception{
		logger.info("ID:" + id);
		long t1 = System.currentTimeMillis();
		String html = "";
		String resourceName = "adoc/"+id+".adoc";
		String file = getClass().getClassLoader().getResource(resourceName).getFile();
		FileReader reader = new FileReader(new File(file));
		StringWriter writer = new StringWriter();

		asciidoctor.convert(reader, writer, Collections.<String, Object> emptyMap());

		StringBuffer htmlBuffer = writer.getBuffer();
		
		html = htmlBuffer.toString();
		
		long time = System.currentTimeMillis() - t1;
		logger.info("Time in converting one small file: {} ms", time);

		Map<String, Object> bindings = new HashMap<String, Object>();
		bindings.putAll(defaultBindings);
		bindings.put("adoc", html);
		html = dt.process(bindings);
		
		return html;
	}
	
	static String content = "";

	private static Logger logger = LoggerFactory.getLogger(NinjaController.class);

	private static Asciidoctor asciidoctor;

	private static DocumentTemplate dt;

	private static HashMap<String, Object> defaultBindings;
	
	public static void main(String[] args) throws Exception {
		long t1 = System.currentTimeMillis();
		asciidoctor = create();
		File templateFile = new File(NinjaController.class.getClassLoader().getResource("template/default.html").getFile());
		dt = new DocumentTemplate(templateFile);
		defaultBindings = new HashMap<String, Object>();
		defaultBindings.put("css", "global.css");
		long time = System.currentTimeMillis() - t1;
		logger.debug("Time in startup of the app: {} ms", time);

		t1 = System.currentTimeMillis();
		Map<String, Object> options = new HashMap<String, Object>();
		String baseDir = "src/main/resources/static";
		DirectoryWalker dw = new AsciiDocDirectoryWalker(baseDir);
		asciidoctor.convertDirectory(dw, options );
		time = System.currentTimeMillis() - t1;
		logger.debug("Time in convert the directory {} to html: {} ms", baseDir, time);
		
		SpringApplication.run(NinjaController.class, args);
	}
}

