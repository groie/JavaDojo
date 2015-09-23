package com.bbv.javadojo.optional;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

public class ArgumentParser {


    private final String schema;
    private final String[] args;

    public ArgumentParser(final String schema, final String[] args) {
        if (schema == null || args == null || asList(args).contains(null))
            throw new ArgsException("constructor parameters may not be null");
        this.schema = schema;
        this.args = args;
    }

    public boolean getBoolean(final char arg) {
        return asList(args).contains(valueOf("-" + arg));
    }

    //  - char*   - String arg.
    public String getString(final char arg) {
        final String key = "-" + arg;
        Stream<String> potentialMatches = Arrays.asList(args).stream().filter(s -> s.startsWith(key));
        Stream<String> stringStream = potentialMatches.map(s -> s.substring(key.length()+1));
        return stringStream.findFirst().orElse(null);
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
}
