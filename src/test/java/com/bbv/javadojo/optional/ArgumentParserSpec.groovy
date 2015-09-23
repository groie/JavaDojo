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

    @Unroll("Constructor with '#schema' and '#args' succeeds")
    def "successful test constructor"() {
        when:
        def parser = new ArgumentParser(schema, args);

        then:
        parser != null

        where:
        schema << ["f,s*,n#,a##,p[*]"]
        args << ["-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3"]
    }
}
