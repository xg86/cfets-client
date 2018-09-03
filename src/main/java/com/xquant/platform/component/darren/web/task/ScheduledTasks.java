package com.xquant.platform.component.darren.web.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.util.HtmlUtils;

import com.xquant.platform.component.darren.vo.Greeting;

//@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Scheduled(fixedRate = 20000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		System.out.println(dateFormat.format(new Date()));
		Greeting greeting =  new Greeting("Hello, " + HtmlUtils.htmlEscape(dateFormat.format(new Date())) + "!");
		simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
	}
}