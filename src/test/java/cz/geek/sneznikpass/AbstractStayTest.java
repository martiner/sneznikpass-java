package cz.geek.sneznikpass;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static cz.geek.sneznikpass.IdCardType.OBCANSKY_PRUKAZ;
import static cz.geek.sneznikpass.NoFeeReason.ZTP;
import static cz.geek.sneznikpass.Status.CONFIRMED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.joda.time.LocalDate;
import org.junit.Before;

public class AbstractStayTest {
	protected Stay stay;

	@Before
	public void setUp() throws Exception {
		stay = new Stay();
		stay.setArrival(new LocalDate(2020, 1, 9));
		stay.setDeparture(new LocalDate(2020, 1, 21));
		stay.setEmail("em@i.l");
		stay.setPhoneNumber("+123 456");
		stay.setStatus(CONFIRMED);
		stay.setNewsletterAgreement(true);

		Guest guest = new Guest();
		guest.setName("Karel Novák");
		guest.setIdCardNumber("123456");
		guest.setIdCardType(OBCANSKY_PRUKAZ);
		guest.setNoFeeReason(ZTP);
		guest.setDateOfBirth(new LocalDate(1980, 9, 20));
		guest.setAddress("Karlova 34, Karlovy Vary");
		guest.setState("ČR");
		stay.add(guest);
	}

	protected void assertStayJson(String json) {
		assertThat(json, hasJsonPath("AuthToken", equalTo("token")));
		assertThat(json, hasJsonPath("Data.Arrival", equalTo("09012020")));
		assertThat(json, hasJsonPath("Data.Departure", equalTo("21012020")));
		assertThat(json, hasJsonPath("Data.Email", equalTo("em@i.l")));
		assertThat(json, hasJsonPath("Data.PhoneNumber", equalTo("+123 456")));
		assertThat(json, hasJsonPath("Data.Status", equalTo("CONFIRMED")));
		assertThat(json, hasJsonPath("Data.NewsletterAgreement", equalTo(true)));

		assertThat(json, hasJsonPath("Data.Guests", hasSize(1)));
		assertThat(json, hasJsonPath("Data.Guests[0].Name", equalTo("Karel Novák")));
		assertThat(json, hasJsonPath("Data.Guests[0].IDCardNumber", equalTo("123456")));
		assertThat(json, hasJsonPath("Data.Guests[0].IDCardType", equalTo("OBCANSKY_PRUKAZ")));
		assertThat(json, hasJsonPath("Data.Guests[0].NoFeeReason", equalTo("ZTP")));
		assertThat(json, hasJsonPath("Data.Guests[0].DateOfBirth", equalTo("20091980")));
		assertThat(json, hasJsonPath("Data.Guests[0].Address", equalTo("Karlova 34, Karlovy Vary")));
		assertThat(json, hasJsonPath("Data.Guests[0].State", equalTo("ČR")));
	}
}
