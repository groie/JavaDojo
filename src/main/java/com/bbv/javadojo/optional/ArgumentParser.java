package com.bbv.javadojo.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class ArgumentParser {

    private final String schema;
    private final List<String> args;

    public ArgumentParser(final String schema, final String[] args) {
        if (schema == null || args == null || asList(args).contains(null))
            throw new ArgsException("constructor parameters may not be null");
        this.schema = schema;
        this.args = asList(args);
    }

    public boolean getBoolean(final char arg) {
        return keyExists(arg);
    }

    public String getString(final char arg) {
        return getTypedValue(arg, String::valueOf);
    }

    public int getInt(final char arg) {
        return getTypedValue(arg, Integer::valueOf);
    }

    public double getDouble(final char arg) {
        return getTypedValue(arg, Double::valueOf);
    }

    private <T> T getTypedValue(final char arg, Function<String, T> converter) {
        try {
            Optional<String> valueForKey = getValueForKey(arg);
            return valueForKey.map(converter).orElse(null);
        } catch (NumberFormatException e) {
            throw new ArgsException(format("%s could not be parsed with the given converter", getValueForKey(arg)), e);
        }
    }

    public String[] getStringArray(final char arg) {
        List<String> valuesForKey = getValuesForKey(arg);
        return valuesForKey.toArray(new String[valuesForKey.size()]);
    }

    public Map<String, String> getMap(final char arg) {
        return null;
    }

    public int nextArgument() {
        // TODO Auto-generated method stub
        return 0;
    }

    private boolean keyExists(char key) {
        return args.contains(keyOf(key));
    }

    private List<String> getValuesForKey(char arg) {
        String key = keyOf(arg);
        List<String> result = new ArrayList<>();
        for(int i = 0 ; i < args.size() ; i++) {
            String param = args.get(i);
            if (param.equals(key)) {
                result.add(args.get(i+1));
            }
        }
        return result;
    }


    private Optional<String> getValueForKey(char key) {
        int idx = args.indexOf(keyOf(key));
        return idx == -1 ? Optional.empty() : Optional.ofNullable(args.get(idx+1));
    }

    private static String keyOf(final char arg) {
        return "-" + arg;
    }
}
