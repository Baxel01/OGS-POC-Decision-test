package org.kie.kogito.examples;

import org.drools.modelcompiler.dsl.pattern.D;
import org.drools.model.Index.ConstraintType;
import org.kie.kogito.examples.demo.Person;
import static org.kie.kogito.examples.Rules7f02f1bf93244f40bc16a36cdb92c1d5.*;
import static org.kie.kogito.examples.Rules7f02f1bf93244f40bc16a36cdb92c1d5_PersonValidationService.*;

public class Rules7f02f1bf93244f40bc16a36cdb92c1d5_PersonValidationService_rule_Is_32retired {

    /**
     * Rule name: Is retired
     */
    public static org.drools.model.Rule rule_Is_32retired() {
        final org.drools.model.Variable<org.kie.kogito.examples.demo.Person> var_$person = D.declarationOf(org.kie.kogito.examples.demo.Person.class,
                                                                                                           DomainClassesMetadata7f02f1bf93244f40bc16a36cdb92c1d5.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE,
                                                                                                           "$person",
                                                                                                           D.entryPoint("persons"));
        final org.drools.model.BitMask mask_$person = org.drools.model.BitMask.getPatternMask(DomainClassesMetadata7f02f1bf93244f40bc16a36cdb92c1d5.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE,
                                                                                              "retired");
        org.drools.model.Rule rule = D.rule("org.kie.kogito.examples",
                                            "Is retired")
                                      .unit(org.kie.kogito.examples.PersonValidationService.class)
                                      .build(D.pattern(var_$person).expr("GENERATED_80F47FAC21CC85B845744C61057E66CB",
                                                                         org.kie.kogito.examples.P02.LambdaPredicate02CF67F8D5F404536987D5D7FD10EDA6.INSTANCE,
                                                                         D.alphaIndexedBy(int.class,
                                                                                          org.drools.model.Index.ConstraintType.GREATER_THAN,
                                                                                          DomainClassesMetadata7f02f1bf93244f40bc16a36cdb92c1d5.org_kie_kogito_examples_demo_Person_Metadata_INSTANCE.getPropertyIndex("age"),
                                                                                          org.kie.kogito.examples.PD6.LambdaExtractorD6EBB88D7B2104248D7A049CC1BA80BC.INSTANCE,
                                                                                          64),
                                                                         D.reactOn("age")),
                                             D.on(var_$person).execute(org.kie.kogito.examples.P39.LambdaConsequence39D5DB46B7308831C5DE8680BB7F19C5.INSTANCE));
        return rule;
    }
}
