package pl.reactive.websockets;

import java.net.URI;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.Disposable;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;

@Component
public class ReconnectionService {

	private ApplicationEventPublisher publisher;

	private WebSocketClient client;

	private final EventSink eventSink;

	public ReconnectionService(ApplicationEventPublisher publisher, WebSocketClient client, EventSink eventSink) {
		this.publisher = publisher;
		this.client = client;
		this.eventSink = eventSink;
	}

	public Disposable connect(String url) {

		EmitterProcessor<String> output = EmitterProcessor.create(false);

		Mono<Void> w = client.execute(URI.create(url),
				session -> session.receive()
						.map(WebSocketMessage::getPayloadAsText)
						.take(3)
						.subscribeWith(output)
						.doOnTerminate(() -> publisher.publishEvent(new ReconnectionEvent(url)))
						.then());

		return output
				.doOnSubscribe(s -> w.subscribe())
				.subscribe(next -> eventSink.save(next));
	}

}
