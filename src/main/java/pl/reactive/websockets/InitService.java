package pl.reactive.websockets;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitService {

	private final ReconnectionService reconnectionService;

	public InitService(ReconnectionService reconnectionService) {
		this.reconnectionService = reconnectionService;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		reconnectionService.connect("wss://stream.binance.com:9443/ws/btcusdt@trade");
	}

	@EventListener(ReconnectionEvent.class)
	public void onApplicationEvent(ReconnectionEvent event) {
		reconnectionService.connect(event.getUrl());
	}

}
