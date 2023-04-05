package com.example.file.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder{

	private String path = "C:\\Users\\ruank\\Documents\\apache-camel\\";
	
	@Override
	public void configure() throws Exception {
		from("file://" + path + "input")
				.log("Arquivo: ${header:CamelFileName} - Path: ${header:CamelFilePath}")
				.log("${file:name}")
				.to("file://" + path + "output");
	}
	
}