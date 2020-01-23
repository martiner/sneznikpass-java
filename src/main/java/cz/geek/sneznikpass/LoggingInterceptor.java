package cz.geek.sneznikpass;

import static org.springframework.util.StreamUtils.copyToString;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		log.debug("{} {} {}", request.getMethod(), request.getURI(), request.getHeaders());
		if (log.isTraceEnabled()) {
			log.trace(new String(body));
		}

		ClientHttpResponse response = execution.execute(request, body);

		log.debug("{} {} {}", response.getRawStatusCode(), response.getStatusText(), response.getHeaders());
		if (log.isTraceEnabled()) {
			log.trace(copyToString(response.getBody(), StandardCharsets.UTF_8));
		}
		return response;
	}
}
