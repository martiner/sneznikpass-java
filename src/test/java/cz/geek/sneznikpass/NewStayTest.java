package cz.geek.sneznikpass;

import static cz.geek.sneznikpass.TestUtils.writeString;

import org.junit.Test;

public class NewStayTest extends AbstractStayTest {

	@Test
	public void shouldSerialize() throws Exception {
		NewStay newStay = new NewStay("token", stay);
		String json = writeString(newStay);
		assertStayJson(json);
	}

}