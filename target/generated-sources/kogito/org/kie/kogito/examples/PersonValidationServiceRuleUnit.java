package org.kie.kogito.examples;

import java.util.function.Function;
import org.drools.core.SessionConfiguration;
import org.drools.core.common.ReteEvaluator;
import org.drools.core.impl.RuleBase;
import org.drools.modelcompiler.KieBaseBuilder;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.drools.ruleunits.api.RuleUnits;
import org.drools.ruleunits.impl.factory.AbstractRuleUnit;
import org.drools.ruleunits.impl.factory.AbstractRuleUnits;
import org.drools.ruleunits.impl.ReteEvaluatorBasedRuleUnitInstance;
import org.drools.ruleunits.impl.sessions.RuleUnitExecutorImpl;

@org.springframework.stereotype.Component
public class PersonValidationServiceRuleUnit extends AbstractRuleUnit<org.kie.kogito.examples.PersonValidationService> {

    private static final RuleBase ruleBase = KieBaseBuilder.createKieBaseFromModel(new org.kie.kogito.examples.Rules55ddc9a102714932afb4edd69f6b6355_PersonValidationService(),
                                                                                   new org.kie.api.conf.KieBaseOption[] { org.kie.api.conf.EventProcessingOption.CLOUD });

    private static final SessionConfiguration sessionConfiguration = ruleBase.getSessionConfiguration().as(SessionConfiguration.KEY);

    static {
        sessionConfiguration.setOption(org.kie.api.runtime.conf.ClockTypeOption.REALTIME);
    }

    public PersonValidationServiceRuleUnit() {
        this(null);
    }

    @org.springframework.beans.factory.annotation.Autowired(required = false)
    public PersonValidationServiceRuleUnit(RuleUnits ruleUnits) {
        super(org.kie.kogito.examples.PersonValidationService.class,
              ruleUnits);
        this.ruleUnits.register(this);
    }

    @Override
    public PersonValidationServiceRuleUnitInstance internalCreateInstance(org.kie.kogito.examples.PersonValidationService data, RuleConfig ruleConfig) {
        ReteEvaluator reteEvaluator = evaluatorConfigurator.apply(new RuleUnitExecutorImpl(ruleBase,
                                                                                           sessionConfiguration));
        return new PersonValidationServiceRuleUnitInstance(this,
                                                           data,
                                                           reteEvaluator,
                                                           ruleConfig);
    }
}
