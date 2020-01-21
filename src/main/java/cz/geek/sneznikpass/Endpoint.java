package cz.geek.sneznikpass;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.NonNull;

public interface Endpoint {

	URI getUri();

	static Endpoint customEndpoint(@NonNull URI uri) {
		return () -> uri;
	}

	static Endpoint customEndpoint(@NonNull String scheme, @NonNull String host, int port) {
		URI uri = UriComponentsBuilder.newInstance()
				.scheme(scheme)
				.host(host)
				.port(port)
				.build()
				.toUri();
		return customEndpoint(uri);
	}
}
