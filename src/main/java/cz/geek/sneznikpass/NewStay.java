package cz.geek.sneznikpass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
class NewStay extends Request {

    @JsonProperty("Data")
    private final Stay data;

    public NewStay(@NonNull String authToken, @NonNull Stay data) {
        super(authToken);
        this.data = data;
    }
}
