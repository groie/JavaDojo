package com.bbv.javadojo.optional;

import java.util.HashMap;
import java.util.Map;

/**
 * Schema:
 - char    - Boolean arg.
 - char*   - String arg.
 - char#   - Integer arg.
 - char##  - double arg.
 - char[*] - one element of a string array.
 *
 * Created by gro on 24/09/15.
 */
public class Schema {

    private final Map<Key, Type> parsedSchema;

    private enum Type {Boolean, String, Integer, Double, StringArray}

    private static Map<String, Type> schemaStringToType = new HashMap<>();

    static {
        schemaStringToType.put("", Type.Boolean);
        schemaStringToType.put("*", Type.String);
        schemaStringToType.put("#", Type.Integer);
        schemaStringToType.put("##", Type.Double);
        schemaStringToType.put("[*]", Type.StringArray);
    }

    public Schema(String schemaString) {
        parsedSchema = parseSchema(schemaString);
    }

    private static Map<Key, Type> parseSchema(String schemaString) {
        HashMap<Key, Type> result = new HashMap<>();
        String[] strings = schemaString.split(",");
        for (String string : strings) {
            result.put(new Key(string.charAt(0)), schemaStringToType.get(string.substring(1)));
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schema{");
        sb.append("parsedSchema=");
        for (Map.Entry<Key, Type> parsedSchemaItem : parsedSchema.entrySet()) {
            sb.append("{key: ").append(parsedSchemaItem.getKey())
              .append(", value:").append(parsedSchemaItem.getValue()).append("}");
        }
        sb.append('}');
        return sb.toString();
    }
}
