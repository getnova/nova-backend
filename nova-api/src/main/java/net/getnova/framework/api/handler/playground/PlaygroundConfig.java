package net.getnova.framework.api.handler.playground;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter(AccessLevel.PACKAGE)
public final class PlaygroundConfig {

  @Value("${PLAYGROUND_ENABLED:false}")
  private boolean enabled;

  @Value("${PLAYGROUND_PATH:playground}")
  private String path;
}
