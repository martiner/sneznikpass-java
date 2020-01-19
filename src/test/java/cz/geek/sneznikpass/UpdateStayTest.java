package cz.geek.sneznikpass;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static cz.geek.sneznikpass.TestUtils.writeString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class UpdateStayTest extends AbstractStayTest {

	@Test
	public void shouldSerialize() throws Exception {
		UpdateStay updateStay = new UpdateStay("token", "stayid", stay);
		String json = writeString(updateStay);
		assertThat(json, hasJsonPath("ID", equalTo("stayid")));
		assertStayJson(json);
	}

}