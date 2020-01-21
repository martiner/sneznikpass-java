package cz.geek.sneznikpass;

import static cz.geek.sneznikpass.IdCardType.OBCANSKY_PRUKAZ;
import static cz.geek.sneznikpass.NoFeeReason.ZTP;
import static cz.geek.sneznikpass.Status.CONFIRMED;
import static cz.geek.sneznikpass.TestUtils.readJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class ListStaysTest {

	@Test
	public void shouldDeserialize() throws Exception {
		ListStays listStays = readJson("/listStays.json", ListStays.class);

		assertThat(listStays.getItems(), hasSize(1));
		Stay item = listStays.getItems().get(0);
		assertThat(item.getId(), is("123427c3-1f84-4a2b-9867-95f8d7fbc086"));
		assertThat(item.getDiscountCardId(), is("ffff27c3-1f84-4a2b-9867-95f8d7fbc086"));
		assertThat(item.getDiscountCardNum(), is(37));
		assertThat(item.getArrival(), is("25112019"));
		assertThat(item.getCreatedWhen(), is("241120191327"));
		assertThat(item.getDeparture(), is("29112019"));
		assertThat(item.getEmail(), is("test@test.test"));
		assertThat(item.getPhoneNumber(), is("605 134 565"));
		assertThat(item.isNewsletterAgreement(), is(true));
		assertThat(item.getStatus(), is(CONFIRMED));

		assertThat(item.getGuests(), hasSize(1));
		Guest guest = item.getGuests().get(0);
		assertThat(guest.getName(), is("Karel Novák"));
		assertThat(guest.getIdCardNumber(), is("123456"));
		assertThat(guest.getIdCardType(), is(OBCANSKY_PRUKAZ));
		assertThat(guest.getNoFeeReason(), is(ZTP));
		assertThat(guest.getDateOfBirth(), is("20091980"));
		assertThat(guest.getAddress(), is("Karlova 34, Karlovy Vary"));
		assertThat(guest.getState(), is("ČR"));
	}
}