package org.kie.kogito.examples.PD6;


import static org.kie.kogito.examples.Rules7f02f1bf93244f40bc16a36cdb92c1d5.*;
import org.kie.kogito.examples.*;
import org.kie.kogito.examples.demo.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaExtractorD6EBB88D7B2104248D7A049CC1BA80BC implements org.drools.model.functions.Function1<org.kie.kogito.examples.demo.Person, Integer>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "0FA51219735F8678BD367E298048C446";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public Integer apply(org.kie.kogito.examples.demo.Person _this) {
        return _this.getAge();
    }
}
