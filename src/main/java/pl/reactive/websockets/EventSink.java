package pl.reactive.websockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventSink {

	private static final Logger logger = LoggerFactory.getLogger(EventSink.class);

	public void save(String event) {
		logger.info(event);
	}

}
