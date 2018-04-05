package pl.reactive.websockets;

import org.springframework.context.ApplicationEvent;

public class ReconnectionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -586277474166927373L;

	private String url;

	public ReconnectionEvent(String url) {
		super(url);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}