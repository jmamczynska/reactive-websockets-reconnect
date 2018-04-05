package pl.reactive.websockets;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

@SpringBootApplication
public class WebsocketsReconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketsReconnectApplication.class, args);
	}

	@Bean
	public WebSocketClient configureClient() {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		container.setDefaultMaxSessionIdleTimeout(1000);
		return new TomcatWebSocketClient(container);
	}
}
