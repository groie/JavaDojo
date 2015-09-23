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
        new ArgumentParser(schema, args as String[]);

        then:
        thrown(ArgsException)

        where:
        schema << [null, null, "", ""  ]
        args   << [null, [""]  , null, [null]]
    }

    @Unroll("getBoolean 'f' and '#args' returns '#expectetations'")
    def "get boolean test"() {
        when:
        def parser = new ArgumentParser("f", args as String[]);

        then:
        parser != null
        parser.getBoolean('f' as char) == expectetations

        where:
        args   << [["-f"], [""]]
        expectetations << [true, false]
    }

    @Unroll("getString 's' and '#args' returns '#expectations'")
    def "get String test"(){
        when:
        def parser = new ArgumentParser("s", args as String[])

        then:
        parser != null
        parser.getString("s" as char) != null
        parser.getString("s" as char) == expectation

        where:
        args << [["-s bla"], []]
        expectation << ["bla", null]
    }
}
