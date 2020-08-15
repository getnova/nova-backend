package net.getnova.backend.api.parser;

import lombok.extern.slf4j.Slf4j;
import net.getnova.backend.api.annotations.ApiParameter;
import net.getnova.backend.api.data.ApiParameterData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
final class ApiParameterParser {

  private ApiParameterParser() {
    throw new UnsupportedOperationException();
  }

  @Nullable
  static Set<ApiParameterData> parseParameters(@NotNull final Class<?> clazz, @NotNull final Method method) {
    final Set<ApiParameterData> parameterData = new LinkedHashSet<>();
    for (final Parameter parameter : method.getParameters()) {

      final ApiParameterData currentParameterData = parseParameter(parameter);
      if (currentParameterData == null) {
        log.error("Parameter {}.{}.{} missing Annotation {}.",
          clazz.getName(), method.getName(), parameter.getName(), ApiParameter.class.getName());
        return null;
      } else parameterData.add(currentParameterData);

    }
    return parameterData;
  }

  @Nullable
  private static ApiParameterData parseParameter(@NotNull final Parameter parameter) {
    if (!parameter.isAnnotationPresent(ApiParameter.class)) return null;
    final ApiParameter parameterAnnotation = parameter.getAnnotation(ApiParameter.class);

    return new ApiParameterData(parameterAnnotation.id(),
      parameterAnnotation.required(),
      parameterAnnotation.type(),
      String.join("\n", parameterAnnotation.description()),
      parameter.getType());
  }
}
