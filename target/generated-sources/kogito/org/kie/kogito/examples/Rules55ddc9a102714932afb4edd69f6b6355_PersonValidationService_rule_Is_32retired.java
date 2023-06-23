package org.kie.kogito.examples;

import org.drools.modelcompiler.dsl.pattern.D;
import org.drools.model.Index.ConstraintType;
import org.kie.kogito.examples.demo.Person;
import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355.*;
import static org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355_PersonValidationService.*;

public class Rules55ddc9a102714932afb4edd69f6b6355_PersonValidationService_rule_Is_32retired {

    /**
     * Rule name: Is retired
     */
    public static org.drools.model.Rule rule_Is_32retired() {
        final org.drools.model.Variable<org.kie.kogito.examples.demo.Person> var_$person = D.declarationOf(org.kie.kogito.examples.demo.Person.class,
                                                                                                           DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE,
                                                                                                           "$person",
                                                                                                           D.entryPoint("persons"));
        final org.drools.model.BitMask mask_$person = org.drools.model.BitMask.getPatternMask(DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE,
                                                                                              "retired");
        org.drools.model.Rule rule = D.rule("org.kie.kogito.examples",
                                            "Is retired")
                                      .unit(org.kie.kogito.examples.PersonValidationService.class)
                                      .build(D.pattern(var_$person).expr("GENERATED_80F47FAC21CC85B845744C61057E66CB",
                                                                         org.kie.kogito.examples.P4C.LambdaPredicate4CF00ABB494DC2AD4F7754E83CC19D75.INSTANCE,
                                                                         D.alphaIndexedBy(int.class,
                                                                                          org.drools.model.Index.ConstraintType.GREATER_THAN,
                                                                                          DomainClassesMetadata55ddc9a102714932afb4edd69f6b6355.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE.getPropertyIndex("age"),
                                                                                          org.kie.kogito.examples.P3F.LambdaExtractor3FA57888061004E5F0917E27F4C7F13A.INSTANCE,
                                                                                          64),
                                                                         D.reactOn("age")),
                                             D.on(var_$person).execute(org.kie.kogito.examples.P02.LambdaConsequence02C44777BBDB6F5C994AD644ED7F7268.INSTANCE));
        return rule;
    }
}
