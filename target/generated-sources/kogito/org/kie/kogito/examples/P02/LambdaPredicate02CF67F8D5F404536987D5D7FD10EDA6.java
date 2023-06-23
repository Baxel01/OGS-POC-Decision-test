package org.kie.kogito.examples.P02;


import static org.kie.kogito.examples.Rules7f02f1bf93244f40bc16a36cdb92c1d5.*;
import org.kie.kogito.examples.*;
import org.kie.kogito.examples.demo.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaPredicate02CF67F8D5F404536987D5D7FD10EDA6 implements org.drools.model.functions.Predicate1<org.kie.kogito.examples.demo.Person>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "C1312F3ED00B356DDF029589306AC97A";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public boolean test(org.kie.kogito.examples.demo.Person _this) throws java.lang.Exception {
        return org.drools.modelcompiler.util.EvaluationUtil.greaterThanNumbers(_this.getAge(), 64);
    }

    @Override()
    public org.drools.model.functions.PredicateInformation predicateInformation() {
        org.drools.model.functions.PredicateInformation info = new org.drools.model.functions.PredicateInformation("age > 64");
        info.addRuleNames("Is retired", "/home/oem/kogito-projects/kogito-examples/kogito-springboot-examples/process-springboot-example/src/main/resources/org/kie/kogito/examples/PersonValidationService.drl");
        return info;
    }
}
