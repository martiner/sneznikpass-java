package cz.geek.sneznikpass;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = NON_NULL)
public class Guest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("DateOfBirth")
    private String dateOfBirth;

    @JsonProperty("IDCardNumber")
    private String idCardNumber;

    @JsonProperty("IDCardType")
    private IdCardType idCardType;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("State")
    private String state;

    @JsonProperty("NoFeeReason")
    private NoFeeReason noFeeReason;

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(@NonNull LocalDate date) {
        this.dateOfBirth = JodaSupport.format(date);
    }
}
