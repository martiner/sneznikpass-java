package cz.geek.sneznikpass;

import static cz.geek.sneznikpass.TestUtils.readJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CreatedStayTest {

	@Test
	public void shouldDeserialize() throws Exception {
		CreatedStay response = readJson("/createdStay.json", CreatedStay.class);
		assertThat(response.getStatus(), is("OK"));
		assertThat(response.getTime(), is("20191204120640"));
		assertThat(response.getId(), is("ba0a27c3-1f84-4a2b-9867-95f8d7fbc086"));
	}

}