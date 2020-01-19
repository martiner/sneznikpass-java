package cz.geek.sneznikpass;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static cz.geek.sneznikpass.TestUtils.writeString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class CredentialsTest {

	@Test
	public void shouldSerialize() throws Exception {
		Credentials credentials = new Credentials("em@i.l", "pwd");
		String json = writeString(credentials);
		assertThat(json, hasJsonPath("Email", equalTo("em@i.l")));
		assertThat(json, hasJsonPath("Password", equalTo("pwd")));
	}
}