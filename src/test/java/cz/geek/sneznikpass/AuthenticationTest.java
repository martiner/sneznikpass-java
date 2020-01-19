package cz.geek.sneznikpass;

import static cz.geek.sneznikpass.TestUtils.readJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class AuthenticationTest {

	@Test
	public void shouldDeserialize() throws Exception {
		Authentication authentication = readJson("/authentication.json", Authentication.class);
		assertThat(authentication.getStatus(), is("OK"));
		assertThat(authentication.getTime(), is("20191204120640"));
		assertThat(authentication.getToken(), is("abdfb1d3b4d..."));
		assertThat(authentication.getId(), is("ba0a27c3-1f84-4a2b-9867-95f8d7fbc086"));
		assertThat(authentication.getOrganizationId(), is("ffff27c3-1f84-4a2b-9867-95f8d7fbc086"));
		assertThat(authentication.getOrganizationType(), is("ACCOMMODATION"));
		assertThat(authentication.getName(), is("Jan Novák"));
		assertThat(authentication.getRole(), is("COMMON"));
	}
}