package cz.geek.sneznikpass;

import static cz.geek.sneznikpass.Endpoint.customEndpoint;
import static cz.geek.sneznikpass.SneznikPassClient.CLIENT_USER_AGENT;
import static cz.geek.sneznikpass.TestUtils.readString;
import static net.jadler.Jadler.onRequest;
import static net.jadler.Jadler.port;
import static net.jadler.Jadler.verifyThatRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

import net.jadler.junit.rule.JadlerRule;
import net.jadler.stubbing.server.jdk.JdkStubHttpServer;

public class SneznikPassClientTest {

	@Rule
	public JadlerRule defaultJadler = new JadlerRule(new JdkStubHttpServer())
			.withDefaultResponseContentType("application/json")
			.withDefaultResponseStatus(HttpStatus.NOT_FOUND.value());

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private SneznikPassClient client;

	private Authentication authentication;

	@Before
	public void setUp() throws Exception {
		client = new SneznikPassClient(customEndpoint("http", "localhost", port()));
		authentication = new Authentication("token", "org123");
	}

	@Test
	public void shouldReturnPing() throws Exception {
		onRequest()
				.havingMethodEqualTo("GET")
				.havingPathEqualTo("/ping")
			.respond()
				.withStatus(200)
				.withBody(readString("/response.json"));
		Response response = client.ping();
		assertThat(response.getStatus(), is("OK"));
		assertThat(response.getTime(), is("20191204120640"));
	}

	@Test
	public void shouldFailWithStatus() throws Exception {
		expectedException.expect(SneznikPassClientException.class);
		expectedException.expectMessage("FAIL");

		onRequest()
				.havingMethodEqualTo("GET")
				.havingPathEqualTo("/ping")
			.respond()
				.withStatus(200)
				.withBody(readString("/fail.json"));
		client.ping();
	}

	@Test
	public void shouldFail() throws Exception {
		expectedException.expect(SneznikPassClientException.class);
		expectedException.expectCause(instanceOf(RestClientException.class));

		onRequest()
			.respond()
				.withStatus(500);

		client.ping();
	}

	@Test
	public void shouldAuthenticate() throws Exception {
		onRequest()
				.havingMethodEqualTo("POST")
				.havingPathEqualTo("/authenticateUser")
			.respond()
				.withStatus(200)
				.withBody(readString("/authentication.json"));

		Authentication authentication = client.authenticateUser(new Credentials("em@i.l", "pwd"));
		assertThat(authentication.getToken(), is("abdfb1d3b4d..."));
	}

	@Test
	public void shouldNewStay() throws Exception {
		onRequest()
				.havingMethodEqualTo("POST")
				.havingPathEqualTo("/newStay")
			.respond()
				.withStatus(200)
				.withBody(readString("/createdStay.json"));

		CreatedStay createdStay = client.newStay(authentication, new Stay());
		assertThat(createdStay.getId(), is("ba0a27c3-1f84-4a2b-9867-95f8d7fbc086"));
	}

	@Test
	public void shouldUpdateStay() throws Exception {
		onRequest()
				.havingMethodEqualTo("POST")
				.havingPathEqualTo("/updateStay")
			.respond()
				.withStatus(200)
				.withBody(readString("/response.json"));

		client.updateStay(authentication, "stayid", new Stay());
	}

	@Test
	public void shouldListGuests() throws Exception {
		onRequest()
				.havingMethodEqualTo("POST")
				.havingPathEqualTo("/listGuests")
			.respond()
				.withStatus(200)
				.withBody(readString("/listGuests.json"));

		ListGuests listGuests = client.listGuests(authentication);
		assertThat(listGuests.getItems(), hasSize(1));
	}

	@Test
	public void shouldSendUserAgent() throws Exception {
		onRequest()
				.respond()
				.withStatus(200)
				.withBody(readString("/response.json"));

		client.ping();
		verifyThatRequest().havingHeaderEqualTo("User-Agent", CLIENT_USER_AGENT);
	}

}