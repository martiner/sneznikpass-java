package cz.geek.sneznikpass;

import static cz.geek.sneznikpass.TestUtils.readJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class ResponseTest {

	@Test
	public void shouldDeserialize() throws Exception {
		Response response = readJson("/response.json", Response.class);
		assertThat(response.getStatus(), is("OK"));
		assertThat(response.getTime(), is("20191204120640"));
	}
}