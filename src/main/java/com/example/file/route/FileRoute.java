package com.example.file.route;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder{

	private String path = "C:\\Users\\ruank\\Documents\\apache-camel\\";
	
	@Override
	public void configure() throws Exception {
		from("file://" + path + "input")
				.choice()
					.when(simple("${header.CamelFileLength} < 422"))
						.to("bean:fileComponent")
					.otherwise()
						.process(new FileProcessor());
	}
	
}

@Component
class FileComponent {
	public void log(File file) {
		System.out.println("FileComponent: " + file.getName());
	}
}

class FileProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("Processor: " + exchange.getIn().getBody());
	}

}