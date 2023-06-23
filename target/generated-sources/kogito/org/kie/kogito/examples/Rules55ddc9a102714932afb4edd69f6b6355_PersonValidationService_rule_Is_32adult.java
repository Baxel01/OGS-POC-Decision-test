package org.kie.kogito.examples;

import org.drools.modelcompiler.dsl.pattern.D;
import org.drools.model.Index.ConstraintType;
import org.kie.kogito.examples.demo.Person;
import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355.*;
import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355_PersonValidationService.*;

public class Rules55ddc9a102714932afb4edd69f6b6355_PersonValidationService_rule_Is_32adult {

    /**
     * Rule name: Is adult
     */
    public static org.drools.model.Rule rule_Is_32adult() {
        final org.drools.model.Variable<org.kie.kogito.examples.demo.Person> var_$person = D.declarationOf(org.kie.kogito.examples.demo.Person.class,
                                                                                                           DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE,
                                                                                                           "$person",
                                                                                                           D.entryPoint("persons"));
        final org.drools.model.BitMask mask_$person = org.drools.model.BitMask.getPatternMask(DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE,
                                                                                              "adult");
        org.drools.model.Rule rule = D.rule("org.kie.kogito.examples",
                                            "Is adult")
                                      .unit(org.kie.kogito.examples.PersonValidationService.class)
                                      .build(D.pattern(var_$person).expr("GENERATED_195829F82016EE8C591CA3D133BB1BFA",
                                                                         org.kie.kogito.examples.P5B.LambdaPredicate5B3302E4C9A7A332E74517F6FF7948D5.INSTANCE,
                                                                         D.alphaIndexedBy(int.class,
                                                                                          org.drools.model.Index.ConstraintType.GREATER_THAN,
                                                                                          DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE.getPropertyIndex("age"),
                                                                                          org.kie.kogito.examples.P3F.LambdaExtractor3FA57888061004E5F0917E27F4C7F13A.INSTANCE,
                                                                                          18),
                                                                         D.reactOn("age")),
                                             D.on(var_$person).execute(org.kie.kogito.examples.PDA.LambdaConsequenceDA5E1AAD6EB64041932094F4DA7DEA84.INSTANCE));
        return rule;
    }
}
