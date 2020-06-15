package net.getnova.backend.api.types;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class ApiZonedDateTimeMapping extends ApiTypeMapping implements Coercing<ZonedDateTime, Long> {

    public ApiZonedDateTimeMapping() {
        super("Timestamp", "a timestamp", ZonedDateTime.class);
    }

    private static boolean isNumberIsh(final Object input) {
        return input instanceof Number || input instanceof String;
    }

    private static String typeName(Object input) {
        if (input == null) {
            return "null";
        }

        return input.getClass().getSimpleName();
    }

    private static Long convertImpl(final Object input) {
        if (input instanceof ZonedDateTime) {
            return ((ZonedDateTime) input).toInstant().toEpochMilli();
        } else if (input instanceof Long) {
            return (Long) input;
        } else if (isNumberIsh(input)) {
            BigDecimal value;
            try {
                value = new BigDecimal(input.toString());
            } catch (NumberFormatException e) {
                return null;
            }
            try {
                return value.longValueExact();
            } catch (ArithmeticException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Long serialize(final Object dataFetcherResult) throws CoercingSerializeException {
        final Long result = convertImpl(dataFetcherResult);
        if (result == null) {
            throw new CoercingSerializeException(
                    "Expected type 'Int' but was '" + typeName(dataFetcherResult) + "'."
            );
        }
        return result;
    }

    @Override
    public ZonedDateTime parseValue(final Object input) throws CoercingParseValueException {
        return null;
    }

    @Override
    public ZonedDateTime parseLiteral(final Object input) throws CoercingParseLiteralException {
        return null;
    }
}
