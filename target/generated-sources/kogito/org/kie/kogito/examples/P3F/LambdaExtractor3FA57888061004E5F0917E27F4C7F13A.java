package org.kie.kogito.examples.P3F;


import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355.*;
import org.kie.kogito.examples.*;
import org.kie.kogito.examples.demo.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaExtractor3FA57888061004E5F0917E27F4C7F13A implements org.drools.model.functions.Function1<org.kie.kogito.examples.demo.Person, Integer>, org.drools.model.functions.HashedExpression {

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
