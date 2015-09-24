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
        schema << [null, null, "",   ""]
        args   << [null, [""], null, [null]]
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

    @Unroll("getString 's' and '#args' returns '#expectation'")
    def "get String test"(){
        when:
        def parser = new ArgumentParser("s*", args as String[])

        then:
        parser != null
        parser.getString("s" as char) == expectation

        where:
        args << [["-s", "bla"], ["-s", "bla", "-s", "bla2"]]
        expectation << ["bla", "bla"]
    }

    @Unroll("getInt 's' and '#args' returns '#expectation'")
    def "getInt test"(){
        when:
        def parser = new ArgumentParser("s#", args as String[])

        then:
        parser != null
        parser.getInt("s" as char) == expectation

        where:
        args << [["-s", "1"], ["-s", "0"]]
        expectation << [1, 0]
    }

    @Unroll("getInt 's' and '#args' throws ArgsException")
    def "getInt test exceptions"(){
        when:
        def parser = new ArgumentParser("s#", args as String[])
        parser.getInt("s" as char)

        then:
        thrown(ArgsException)

        where:
        args << [["-s", "dsadsa"], ["-s", ""], ["-s", null], ["-s", "1.2"]]
    }

    @Unroll("getDouble 's' and '#args' returns '#expectation'")
    def "getDouble test"(){
        when:
        def parser = new ArgumentParser("s##", args as String[])

        then:
        parser != null
        parser.getDouble("s" as char) == expectation

        where:
        args << [["-s", "1"], ["-s", "0.1"]]
        expectation << [1, 0.1]
    }

    @Unroll("getDouble 's' and '#args' throws ArgsException")
    def "getDouble test exceptions"(){
        when:
        def parser = new ArgumentParser("s##", args as String[])
        parser.getDouble("s" as char)

        then:
        thrown(ArgsException)

        where:
        args << [["-s", "dsadsa"], ["-s", ""], ["-s", null], ["-s", "1.2xd"]]
    }

    @Unroll("getStringArray 's' and '#args' returns #expectations")
    def "getStringArray test success"(){
        when:
        def parser = new ArgumentParser("s[*]", args as String[])
        def result = Arrays.asList(parser.getStringArray("s" as char));

        then:
        result == expectations

        where:
        args << [["-s", "first", "-s", "second"]]
        expectations << [["first", "second"]]
    }
}
