package com.bbv.javadojo.optional

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by gro on 23/09/15.
 */
class ArgumentParserSpec extends Specification {

    @Unroll("Constructor with '#schema' and '#args' fails")
    def "failed test constructor"() {
        when:
        new ArgumentParser(schema, args);

        then:
        thrown(ArgsException)

        where:
        schema << [null, null, ""  ]
        args   << [null, ""  , null]
    }

    @Unroll("getBoolean '#schema' and '#args' returns '#expectetations'")
    def "get boolean test"() {
        when:
        def parser = new ArgumentParser(schema, args);

        then:
        parser != null
        parser.getBoolean('f' as char) == expectetations

        where:
        schema << ["f", "f"]
        args   << ["-f", ""]
        expectetations << [true, false]
    }
}
