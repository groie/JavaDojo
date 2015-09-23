package com.bbv.javadojo.optional;

import java.util.List;
import java.util.Map;

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
        return args.contains(keyOf(arg));
    }

    //  - char*   - String arg.
    public String getString(final char arg) {
        // TODO: we could still have multiple matches
        int idx = args.indexOf(keyOf(arg));
        return idx == -1 ? null : args.get(idx +1);
    }

    public int getInt(final char arg) {
        return 0;
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

    private static String keyOf(final char arg) {
        return "-" + arg;
    }
}
