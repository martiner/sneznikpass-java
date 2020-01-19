package cz.geek.sneznikpass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
class UpdateStay extends Request {

    @JsonProperty("ID")
    private final String id;

    @JsonProperty("Data")
    private final Stay data;

    public UpdateStay(@NonNull String authToken, @NonNull String id, @NonNull Stay data) {
        super(authToken);
        this.id = id;
        this.data = data;
    }
}
