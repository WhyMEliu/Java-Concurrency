package com.peng.concurrency.example.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {
	
	@RequestMapping("/test")
	@ResponseBody
	public Long test(){
		return RequestHolder.getId();
	}
}
