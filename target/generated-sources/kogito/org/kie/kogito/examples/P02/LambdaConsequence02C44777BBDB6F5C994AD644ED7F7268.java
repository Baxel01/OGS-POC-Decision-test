package org.kie.kogito.examples.P02;


import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355.*;
import org.kie.kogito.examples.*;
import org.kie.kogito.examples.demo.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaConsequence02C44777BBDB6F5C994AD644ED7F7268 implements org.drools.model.functions.Block2<org.drools.model.Drools, org.kie.kogito.examples.demo.Person>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "53074C3E050E2F43213AA39D6F1ADB5B";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    private final org.drools.model.BitMask mask_$person = org.drools.model.BitMask.getPatternMask(DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE, "retired");

    @Override()
    public void execute(org.drools.model.Drools drools, org.kie.kogito.examples.demo.Person $person) throws java.lang.Exception {
        {
            {
                ($person).setRetired(true);
            }
            drools.update($person, mask_$person);
        }
    }
}
