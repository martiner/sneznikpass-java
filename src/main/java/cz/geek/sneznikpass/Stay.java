package cz.geek.sneznikpass;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static cz.geek.sneznikpass.JodaSupport.format;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = NON_NULL)
public class Stay {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("Arrival")
    private String arrival;

    @JsonProperty("Departure")
    private String departure;

    @JsonProperty("CreatedWhen")
    private String createdWhen;

    @JsonProperty("DiscountCardID")
    private String discountCardId;

    @JsonProperty("DiscountCardNum")
    private Integer discountCardNum;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    @JsonProperty("Status")
    private Status status;

    @JsonProperty("NewsletterAgreement")
    private boolean newsletterAgreement;

    @JsonProperty("Guests")
    private List<Guest> guests = new ArrayList<>();

    public void add(Guest... guests) {
        this.guests.addAll(asList(guests));
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setArrival(@NonNull LocalDate date) {
        this.arrival = format(date);
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDeparture(@NonNull LocalDate date) {
        this.departure = format(date);
    }
}
