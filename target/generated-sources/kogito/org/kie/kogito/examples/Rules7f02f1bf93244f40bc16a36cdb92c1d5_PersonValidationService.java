package org.kie.kogito.examples;

import org.drools.modelcompiler.dsl.pattern.D;
import org.drools.model.Index.ConstraintType;
import org.kie.kogito.examples.demo.Person;
import static org.kie.kogito.examples.Rules7f02f1bf93244f40bc16a36cdb92c1d5.*;

public class Rules7f02f1bf93244f40bc16a36cdb92c1d5_PersonValidationService extends Rules7f02f1bf93244f40bc16a36cdb92c1d5 {

    @Override
    public String getName() {
        return super.getName() + ".PersonValidationService";
    }

    @Override
    public String getPackageName() {
        return super.getName();
    }

    /**
     * With the following expression ID:
     * org.drools.model.codegen.execmodel.generator.DRLIdGenerator@1fc56958
     */
    @Override
    public java.util.List<org.drools.model.Rule> getRules() {
        return rules;
    }

    public java.util.List<org.drools.model.Rule> getRulesList() {
        return java.util.Arrays.asList(Rules7f02f1bf93244f40bc16a36cdb92c1d5_PersonValidationService_rule_Is_32adult.rule_Is_32adult(),
                                       Rules7f02f1bf93244f40bc16a36cdb92c1d5_PersonValidationService_rule_Is_32retired.rule_Is_32retired());
    }

    java.util.List<org.drools.model.Rule> rules = getRulesList();

    @Override
    public java.util.List<org.drools.model.EntryPoint> getEntryPoints() {
        return java.util.Arrays.asList(D.entryPoint("persons"));
    }

    public static final org.drools.model.Global<org.drools.ruleunits.api.DataStore<org.kie.kogito.examples.demo.Person>> var_persons = D.globalOf(new org.drools.model.TypeReference<org.drools.ruleunits.api.DataStore<org.kie.kogito.examples.demo.Person>>(org.drools.ruleunits.api.DataStore.class),
                                                                                                                                                  "org.kie.kogito.examples",
                                                                                                                                                  "persons");

    @Override
    public java.util.List<org.drools.model.Query> getQueries() {
        return java.util.Collections.emptyList();
    }

    {
        globals.add(var_persons);
    }
}
