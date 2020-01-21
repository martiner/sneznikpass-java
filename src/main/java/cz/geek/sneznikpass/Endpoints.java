package cz.geek.sneznikpass;

import java.net.URI;

import lombok.NonNull;

public enum Endpoints implements Endpoint {

	TESTING("https://cma.odp.cz/dolnimorava"),
	PRODUCTION("https://cloud.odp.cz/dolnimorava/DolniMorava.svc"),
	;

	private final URI uri;

	Endpoints(@NonNull String uri) {
		this.uri = URI.create(uri);
	}

	@Override
	public URI getUri() {
		return uri;
	}
}
