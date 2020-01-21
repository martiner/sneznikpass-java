package cz.geek.sneznikpass;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static cz.geek.sneznikpass.TestUtils.writeString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class ListStaysRequestTest {

	@Test
	public void shouldSerialize() throws Exception {
		ListStaysRequest request = new ListStaysRequest("token", "org123");
		String json = writeString(request);
		assertThat(json, hasJsonPath("AuthToken", equalTo("token")));
		assertThat(json, hasJsonPath("OrgID", equalTo("org123")));
	}
}