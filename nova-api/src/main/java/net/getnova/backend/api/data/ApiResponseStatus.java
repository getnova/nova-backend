package net.getnova.backend.api.data;

import com.google.gson.JsonElement;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.getnova.backend.json.JsonBuilder;
import net.getnova.backend.json.JsonSerializable;
import org.jetbrains.annotations.NotNull;

@Getter
@ToString
@RequiredArgsConstructor
public enum ApiResponseStatus implements JsonSerializable {

    // Information responses 1xx

    // Successful responses 2xx
    OK(HttpResponseStatus.OK, false),

    // Redirection messages 3xx

    // Client error responses 4xx
    BAD_REQUEST(HttpResponseStatus.BAD_REQUEST, true),
    UNAUTHORIZED(HttpResponseStatus.UNAUTHORIZED, true),
    FORBIDDEN(HttpResponseStatus.FORBIDDEN, true),
    NOT_FOUND(HttpResponseStatus.NOT_FOUND, true),

    // Server error responses 5xx
    INTERNAL_SERVER_ERROR(HttpResponseStatus.INTERNAL_SERVER_ERROR, true),
    NOT_IMPLEMENTED(HttpResponseStatus.NOT_IMPLEMENTED, true),
    SERVICE_UNAVAILABLE(HttpResponseStatus.SERVICE_UNAVAILABLE, true);

    // Self made 9xx

    private final int code;
    @NotNull
    private final String name;
    private final boolean error;

    ApiResponseStatus(@NotNull final HttpResponseStatus status, final boolean error) {
        this(status.code(), status.reasonPhrase(), error);
    }

    @NotNull
    @Override
    public JsonElement serialize() {
        return JsonBuilder.create("code", this.code)
                .add("name", this.name)
                .add("error", this.error)
                .build();
    }
}
