package com.bbv.javadojo.optional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    //  - char*   - String arg.
    public String getString(final char arg) {
        return getValueForKey(arg).orElse(null);
    }

    public int getInt(final char arg) {
        try {
            return getValueForKey(arg).map(Integer::valueOf).orElse(null);
        } catch (NumberFormatException e) {
            throw new ArgsException(format("%s could not be parsed as an integer", getString(arg)), e);
        }
    }

    public double getDouble(final char arg) {
        return 0.0;
    }

    public String[] getStringArray(final char arg) {
        return null;
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

    private Optional<String> getValueForKey(char key) {
        int idx = args.indexOf(keyOf(key));
        return idx == -1 ? Optional.empty() : Optional.ofNullable(args.get(idx+1));
    }

    private static String keyOf(final char arg) {
        return "-" + arg;
    }
}
