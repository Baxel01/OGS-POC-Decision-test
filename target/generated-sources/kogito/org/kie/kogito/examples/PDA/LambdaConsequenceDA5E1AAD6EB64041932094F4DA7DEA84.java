package org.kie.kogito.examples.PDA;


import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355.*;
import org.kie.kogito.examples.*;
import org.kie.kogito.examples.demo.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaConsequenceDA5E1AAD6EB64041932094F4DA7DEA84 implements org.drools.model.functions.Block2<org.drools.model.Drools, org.kie.kogito.examples.demo.Person>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "6A441FB5B83A8FD28E6D1070F70E73A9";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    private final org.drools.model.BitMask mask_$person = org.drools.model.BitMask.getPatternMask(DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE, "adult");

    @Override()
    public void execute(org.drools.model.Drools drools, org.kie.kogito.examples.demo.Person $person) throws java.lang.Exception {
        {
            {
                ($person).setAdult(true);
            }
            drools.update($person, mask_$person);
        }
    }
}
