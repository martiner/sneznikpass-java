package cz.geek.sneznikpass;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpHeaders.USER_AGENT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.NonNull;

public class SneznikPassClient {

	static final String CLIENT_USER_AGENT = "GeekSneznikPass/0.1.0 (+https://github.com/martiner/sneznikpass-java)";

	static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);

	private final Endpoint endpoint;

	private RestTemplate restTemplate;

	public SneznikPassClient(@NonNull Endpoint endpoint) {
		this.endpoint = endpoint;
		this.restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new HeaderSettingInterceptor(singletonMap(USER_AGENT, CLIENT_USER_AGENT)));
		restTemplate.getInterceptors().add(new LoggingInterceptor());
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		restTemplate.setMessageConverters(singletonList(new MappingJackson2HttpMessageConverter(OBJECT_MAPPER)));
	}

	public SneznikPassClient() {
		this(Endpoints.PRODUCTION);
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

	public ListStays listStays(@NonNull Authentication authentication)
			throws SneznikPassClientException {
		ListStaysRequest request = new ListStaysRequest(authentication.getToken(), authentication.getOrganizationId());
		return exchange(POST, "listStays", ListStays.class, request);
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
		} catch (RestClientResponseException e) {
			String msg = String.join(" ", url, Integer.toString(e.getRawStatusCode()), e.getResponseBodyAsString());
			throw new SneznikPassClientException(msg, e);
		} catch (RestClientException e) {
			throw new SneznikPassClientException(url, e);
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
		return UriComponentsBuilder.fromUri(endpoint.getUri())
				.pathSegment(uri)
				.toUriString();
	}
}
