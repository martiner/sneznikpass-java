package cz.geek.sneznikpass;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.NonNull;

class HeaderSettingInterceptor implements ClientHttpRequestInterceptor {

	private final Map<String, String> headers;

	public HeaderSettingInterceptor(@NonNull Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		headers.forEach((name, value) -> request.getHeaders().set(name, value));
		return execution.execute(request, body);
	}
}
