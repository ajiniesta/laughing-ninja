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
import java.util.Map;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;

public class First {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Asciidoctor asciidoctor = create();
		String s = "= Hello, from One! \n"
//				+ ":linkcss: \n"
				+ ":stylesheet: global.css \n"
				+ ":toc: left \n"
				+ "Doc Writer <doc@example.com> \n"
				+ "\n"
				+ "An introduction to http://asciidoc.org[AsciiDoc].\n"
				+ "\n"
				+ "== First Section\n"
				+ "\n"
				+ "* item 1\n"
				+ "* item 2";
		Map<String, Object> options = OptionsBuilder.options()
        .compact(true)
        .headerFooter(true)
        .safe(SafeMode.UNSAFE)
        .backend("html")        
        .asMap();
		
		String html = asciidoctor.convert(s, options);
		System.out.println(html);

	}

}
