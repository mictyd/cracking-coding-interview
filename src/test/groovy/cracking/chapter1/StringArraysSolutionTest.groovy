package cracking.chapter1

import spock.lang.Specification
import spock.lang.Unroll

class StringArraysSolutionTest extends Specification {

    def underTest = new StringArraysSolution()

    def 'should fail on null'() {
        when:
        invokeAllMethods(null)
        underTest.hasOnlyUniqueCharacters_streams(null)

        then:
        thrown(IllegalArgumentException)
    }

    def 'should return true on empty string'() {
        expect:
        underTest.hasOnlyUniqueCharacters_streams('')
    }

    def 'should fail on a string longer than 255 characters'() {
        expect:
        !underTest.hasOnlyUniqueCharacters_streams('a' * 256)
    }

    @Unroll
    def "should #desc on string '#string'"() {
        expect:
        expected == underTest.hasOnlyUniqueCharacters_streams(string)

        where:
        desc   | string | expected
        'fail' | 'abcdefgha' | false
        'work' | 'abcdefgh' | true
        'work' | 'abcdefgh' | true
    }

    private invokeAllMethods(args) {
        def methods = StringArraysSolution.allPublicMethods
        println ('About to invoke all methods: ' + methods)

        methods.forEach { it.invoke(underTest, args)}
    }
}
