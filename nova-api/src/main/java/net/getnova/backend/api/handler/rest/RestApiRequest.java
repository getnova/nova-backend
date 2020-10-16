package net.getnova.backend.api.handler.rest;

import com.google.gson.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.getnova.backend.api.data.ApiRequest;
import reactor.netty.http.server.HttpServerRequest;

@Getter
@ToString
@EqualsAndHashCode
public class RestApiRequest extends ApiRequest {

  private final HttpServerRequest httpRequest;

  public RestApiRequest(final String endpoint, final JsonObject data, final HttpServerRequest httpRequest) {
    super(endpoint, data);
    this.httpRequest = httpRequest;
  }
}
