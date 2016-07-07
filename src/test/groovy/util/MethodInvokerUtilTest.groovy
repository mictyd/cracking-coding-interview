package util

import spock.lang.Specification
import spock.lang.Unroll

import static util.MethodInvokerUtil.getDeclaredPublicInstanceMethods
import static util.MethodInvokerUtil.invokeMethods

class MethodInvokerUtilTest extends Specification {

    def 'should return zero methods'() {
        expect:
        getDeclaredPublicInstanceMethods(ClassWithNoMethods).isEmpty()
    }

    @Unroll
    def 'should return non static public methods in #clazz'() {
        expect:
        getDeclaredPublicInstanceMethods(clazz).collect { it.getName() } == methods

        where:
        clazz                                  | methods
        ClassWithPublicMethods                 | ['publicMethod1', 'publicMethod2']
        ClassWithPublicAndPrivateMethods       | ['publicMethod1', 'publicMethod2']
        ClassWithPublicPrivateAndStaticMethods | ['publicMethod1', 'publicMethod2']
        ClassWithAbstractMethods               | []
    }

    def 'should execute void method and return type'() {
        given:
        def object = new TestMethods()
        def voidMethod = TestMethods.getMethod('getVoid')

        when:
        def returnValues = invokeMethods(object, [voidMethod], voidMethod.returnType)

        then:
        returnValues == []
    }

    def 'should execute int method and return type'() {
        given:
        def object = new TestMethods()
        def method = TestMethods.getMethod('getInt')

        when:
        println method.returnType
        def returnValues = invokeMethods(object, [method], method.returnType)

        then:
        returnValues == [1234]
    }
}
