package org.kie.kogito.examples.P5B;


import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355.*;
import org.kie.kogito.examples.*;
import org.kie.kogito.examples.demo.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaPredicate5B3302E4C9A7A332E74517F6FF7948D5 implements org.drools.model.functions.Predicate1<org.kie.kogito.examples.demo.Person>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "A24655EFC6B7BA9A0D8D6862A0CD19A6";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public boolean test(org.kie.kogito.examples.demo.Person _this) throws java.lang.Exception {
        return org.drools.modelcompiler.util.EvaluationUtil.greaterThanNumbers(_this.getAge(), 18);
    }

    @Override()
    public org.drools.model.functions.PredicateInformation predicateInformation() {
        org.drools.model.functions.PredicateInformation info = new org.drools.model.functions.PredicateInformation("age > 18");
        info.addRuleNames("Is adult", "/home/oem/kogito-projects/kogito-examples/kogito-springboot-examples/process-springboot-example/src/main/resources/org/kie/kogito/examples/PersonValidationService.drl");
        return info;
    }
}
