package io.javathought.springconfigclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javathought.springconfigclient.config.DbSettings;

@RestController
@RequestMapping("/config/client/hello")
public class HelloController {
	
	@Value("${my.greeting}")
	private String greetingMessage;
	
	@Value("${my.list.values}")
	private List<String> listValues;
	
	@Autowired
	private DbSettings dBSettings;

	
	@RequestMapping("/greeting")
	public String getGtreeing() {
		return "greetingMessage:-"+greetingMessage+"\ndb.connection:-"+dBSettings.toString()+"\nlistValues:-"+listValues; 
	}
}
