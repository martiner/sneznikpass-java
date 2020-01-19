package cz.geek.sneznikpass;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListGuests extends Response {

    @JsonProperty("Items")
    private List<Stay> items;

}
