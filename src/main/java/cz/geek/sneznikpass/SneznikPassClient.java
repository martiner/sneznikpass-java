package cz.geek.sneznikpass;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

public class SneznikPassClient {

	static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private final String endpoint;

	private RestTemplate restTemplate;

	SneznikPassClient(@NonNull String endpoint) {
		this.endpoint = endpoint;
		this.restTemplate = new RestTemplate();
	}

	public SneznikPassClient() {
		this("https://cma.odp.cz/");
	}

	public Response ping() throws SneznikPassClientException {
		return exchange(GET, "ping", Response.class, null);
	}

	public Authentication authenticateUser(@NonNull Credentials credentials)
			throws SneznikPassClientException {
		return exchange(POST, "authenticateUser", Authentication.class, credentials);
	}

	public CreatedStay newStay(@NonNull Authentication authentication, @NonNull Stay stay)
			throws SneznikPassClientException {
		NewStay request = new NewStay(authentication.getToken(), stay);
		return exchange(POST, "newStay", CreatedStay.class, request);
	}

	public void updateStay(@NonNull Authentication authentication, @NonNull String stayId, @NonNull Stay stay)
			throws SneznikPassClientException {
		UpdateStay request = new UpdateStay(authentication.getToken(), stayId, stay);
		exchange(POST, "updateStay", Response.class, request);
	}

	public ListGuests listGuests(@NonNull Authentication authentication)
			throws SneznikPassClientException {
		ListGuestsRequest request = new ListGuestsRequest(authentication.getToken(), authentication.getOrganizationId());
		return exchange(POST, "listGuests", ListGuests.class, request);
	}

	private <T extends Response> T exchange(HttpMethod method, String uri, Class<T> responseType, Object requestBody) {
		String url = createUrl(uri);
		ResponseEntity<T> response = execute(method, url, responseType, requestBody);
		validateResponse(response);
		return response.getBody();
	}

	private <T extends Response> ResponseEntity<T> execute(HttpMethod method, String url, Class<T> responseType,
			Object requestBody) {
		HttpEntity<Object> requestEntity = createRequest(requestBody);
		ResponseEntity<T> response;
		try {
			response = restTemplate.exchange(url, method, requestEntity, responseType);
		} catch (RestClientException e) {
			throw new SneznikPassClientException(e);
		}
		return response;
	}

	private <T extends Response> void validateResponse(ResponseEntity<T> response) {
		T responseBody = response.getBody();
		String status = responseBody.getStatus();
		if (!"OK".equals(status)) {
			throw new SneznikPassClientException(status);
		}
	}

	private HttpEntity<Object> createRequest(Object requestBody) {
		if (requestBody == null) {
			return null;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(requestBody, headers);
	}

	private String createUrl(String uri) {
		return UriComponentsBuilder.fromHttpUrl(endpoint)
				.pathSegment("dolnimorava", uri)
				.toUriString();
	}
}
