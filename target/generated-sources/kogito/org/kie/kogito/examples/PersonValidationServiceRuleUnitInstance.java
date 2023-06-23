package org.kie.kogito.examples;

import org.drools.core.common.ReteEvaluator;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.drools.ruleunits.impl.ReteEvaluatorBasedRuleUnitInstance;

public class PersonValidationServiceRuleUnitInstance extends ReteEvaluatorBasedRuleUnitInstance<org.kie.kogito.examples.PersonValidationService> {

    public PersonValidationServiceRuleUnitInstance(RuleUnit<org.kie.kogito.examples.PersonValidationService> unit, org.kie.kogito.examples.PersonValidationService workingMemory, ReteEvaluator reteEvaluator) {
        super(unit,
              workingMemory,
              reteEvaluator);
    }

    public PersonValidationServiceRuleUnitInstance(RuleUnit<org.kie.kogito.examples.PersonValidationService> unit, org.kie.kogito.examples.PersonValidationService workingMemory, ReteEvaluator reteEvaluator, RuleConfig ruleConfig) {
        super(unit,
              workingMemory,
              reteEvaluator,
              ruleConfig);
    }

    @Override
    protected void bind(ReteEvaluator evaluator, org.kie.kogito.examples.PersonValidationService ruleUnit) {
        ruleUnit.getPersons()
                .subscribe(new org.drools.ruleunits.impl.EntryPointDataProcessor(evaluator.getEntryPoint("persons")));
        evaluator.setGlobal("persons",
                            ruleUnit.getPersons());
    }
}
