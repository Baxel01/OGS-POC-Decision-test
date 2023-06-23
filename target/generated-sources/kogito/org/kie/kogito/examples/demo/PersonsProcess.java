package org.kie.kogito.examples.demo;

import org.kie.kogito.examples.demo.PersonsModel;
import org.kie.api.definition.process.Process;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.drools.core.util.KieFunctions;

@org.springframework.stereotype.Component("persons")
public class PersonsProcess extends org.kie.kogito.process.impl.AbstractProcess<org.kie.kogito.examples.demo.PersonsModel> {

    @org.springframework.beans.factory.annotation.Autowired()
    public PersonsProcess(org.kie.kogito.app.Application app, org.kie.kogito.correlation.CorrelationService correlations) {
        super(app, java.util.Arrays.asList(), correlations);
        activate();
    }

    public PersonsProcess() {
    }

    @Override()
    public org.kie.kogito.examples.demo.PersonsProcessInstance createInstance(org.kie.kogito.examples.demo.PersonsModel value) {
        return new org.kie.kogito.examples.demo.PersonsProcessInstance(this, value, this.createProcessRuntime());
    }

    public org.kie.kogito.examples.demo.PersonsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.examples.demo.PersonsModel value) {
        return new org.kie.kogito.examples.demo.PersonsProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    public org.kie.kogito.examples.demo.PersonsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.correlation.CompositeCorrelation correlation, org.kie.kogito.examples.demo.PersonsModel value) {
        return new org.kie.kogito.examples.demo.PersonsProcessInstance(this, value, businessKey, this.createProcessRuntime(), correlation);
    }

    @Override()
    public org.kie.kogito.examples.demo.PersonsModel createModel() {
        return new org.kie.kogito.examples.demo.PersonsModel();
    }

    public org.kie.kogito.examples.demo.PersonsProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.kie.kogito.examples.demo.PersonsModel) value);
    }

    public org.kie.kogito.examples.demo.PersonsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.kie.kogito.examples.demo.PersonsModel) value);
    }

    public org.kie.kogito.examples.demo.PersonsProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.kie.kogito.examples.demo.PersonsProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public org.kie.kogito.examples.demo.PersonsProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.kie.kogito.examples.demo.PersonsProcessInstance(this, this.createModel(), wpi);
    }

    protected org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("persons", true);
        factory.variable("person", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(org.kie.kogito.examples.demo.Person.class), null, "customTags", null);
        factory.name("Person Process");
        factory.packageName("org.kie.kogito.examples.demo");
        factory.dynamic(false);
        factory.version("1.0");
        factory.type("BPMN");
        factory.visibility("Public");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        org.jbpm.ruleflow.core.factory.SplitFactory<?> splitNode1 = factory.splitNode(1);
        splitNode1.name("Exclusive Gateway 2");
        splitNode1.type(2);
        splitNode1.metaData("UniqueId", "_3D5AE9D6-6086-46E1-BF17-00E578FA1934");
        splitNode1.metaData("elementname", "Exclusive Gateway 2");
        splitNode1.metaData("x", 452);
        splitNode1.metaData("width", 56);
        splitNode1.metaData("y", 215);
        splitNode1.metaData("Default", null);
        splitNode1.metaData("height", 56);
        splitNode1.constraint(4, "_6F2887C0-1CB5-4D87-AC1A-0CBCCCBE2B42", "DROOLS_DEFAULT", "java", kcontext -> {
            org.kie.kogito.examples.demo.Person person = (org.kie.kogito.examples.demo.Person) kcontext.getVariable("person");
            return person.isRetired() == false;
        }, 0, false);
        splitNode1.constraint(3, "_FAD1FFBC-3371-4D29-B91F-9FF4D6C0CBDC", "DROOLS_DEFAULT", "java", kcontext -> {
            org.kie.kogito.examples.demo.Person person = (org.kie.kogito.examples.demo.Person) kcontext.getVariable("person");
            return person.isRetired() == true;
        }, 0, false);
        splitNode1.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNode2 = factory.endNode(2);
        endNode2.name("End Event 3");
        endNode2.terminate(false);
        endNode2.metaData("UniqueId", "_A24BDC19-A266-45A1-8B02-477A649AA1D0");
        endNode2.metaData("elementname", "End Event 3");
        endNode2.metaData("x", 810);
        endNode2.metaData("width", 56);
        endNode2.metaData("y", 315);
        endNode2.metaData("height", 56);
        endNode2.done();
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory<?> humanTaskNode3 = factory.humanTaskNode(3);
        humanTaskNode3.name("handling for retired adults");
        humanTaskNode3.workParameter("NodeName", "handling for retired adults");
        humanTaskNode3.workParameter("TaskName", "RetiredHandling");
        humanTaskNode3.workParameter("Priority", "1");
        humanTaskNode3.workParameter("Skippable", "true");
        humanTaskNode3.workParameter("ActorId", "admin");
        humanTaskNode3.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_C108B805-B8F0-45E1-8877-98BE2FD65139_TaskNameInputX", "TaskName", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("d7193301-6aca-45f0-b462-93279819df76", "EXPRESSION (RetiredHandling)", "java.lang.Object", "RetiredHandling"), new org.jbpm.workflow.core.impl.DataDefinition("_C108B805-B8F0-45E1-8877-98BE2FD65139_TaskNameInputX", "TaskName", "Object", null))), null));
        humanTaskNode3.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("person", "person", "java.lang.Object", null)), new org.jbpm.workflow.core.impl.DataDefinition("_C108B805-B8F0-45E1-8877-98BE2FD65139_personInputX", "person", "org.kie.kogito.examples.demo.Person", null), null, null));
        humanTaskNode3.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_C108B805-B8F0-45E1-8877-98BE2FD65139_SkippableInputX", "Skippable", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("ca422a73-33e1-4073-a53a-3709ada4c844", "EXPRESSION (true)", "java.lang.Object", "true"), new org.jbpm.workflow.core.impl.DataDefinition("_C108B805-B8F0-45E1-8877-98BE2FD65139_SkippableInputX", "Skippable", "Object", null))), null));
        humanTaskNode3.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_C108B805-B8F0-45E1-8877-98BE2FD65139_PriorityInputX", "Priority", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("d07206c8-c5b1-4783-9612-91b7c0cc9ec0", "EXPRESSION (1)", "java.lang.Object", "1"), new org.jbpm.workflow.core.impl.DataDefinition("_C108B805-B8F0-45E1-8877-98BE2FD65139_PriorityInputX", "Priority", "Object", null))), null));
        humanTaskNode3.done();
        humanTaskNode3.metaData("UniqueId", "_C108B805-B8F0-45E1-8877-98BE2FD65139");
        humanTaskNode3.metaData("elementname", "handling for retired adults");
        humanTaskNode3.metaData("x", 561);
        humanTaskNode3.metaData("width", 160);
        humanTaskNode3.metaData("y", 300);
        humanTaskNode3.metaData("height", 83);
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory<?> humanTaskNode4 = factory.humanTaskNode(4);
        humanTaskNode4.name("handling for adults not retired");
        humanTaskNode4.workParameter("NodeName", "handling for adults not retired");
        humanTaskNode4.workParameter("TaskName", "AdultHandling");
        humanTaskNode4.workParameter("Priority", "1");
        humanTaskNode4.workParameter("Skippable", "true");
        humanTaskNode4.workParameter("ActorId", "admin");
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0_TaskNameInputX", "TaskName", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("8c83f22a-49f0-4f68-b869-2bb2e6956d85", "EXPRESSION (AdultHandling)", "java.lang.Object", "AdultHandling"), new org.jbpm.workflow.core.impl.DataDefinition("_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0_TaskNameInputX", "TaskName", "Object", null))), null));
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("person", "person", "java.lang.Object", null)), new org.jbpm.workflow.core.impl.DataDefinition("_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0_personInputX", "person", "org.kie.kogito.examples.demo.Person", null), null, null));
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0_SkippableInputX", "Skippable", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("fd078cfb-c525-46a8-acb3-64a7fa3b5e75", "EXPRESSION (true)", "java.lang.Object", "true"), new org.jbpm.workflow.core.impl.DataDefinition("_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0_SkippableInputX", "Skippable", "Object", null))), null));
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0_PriorityInputX", "Priority", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("477ab15e-2155-4e28-9e4f-10f3c511646d", "EXPRESSION (1)", "java.lang.Object", "1"), new org.jbpm.workflow.core.impl.DataDefinition("_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0_PriorityInputX", "Priority", "Object", null))), null));
        humanTaskNode4.done();
        humanTaskNode4.metaData("UniqueId", "_E9DB93CA-045D-47DE-A8A9-D52B28EA65B0");
        humanTaskNode4.metaData("elementname", "handling for adults not retired");
        humanTaskNode4.metaData("x", 561);
        humanTaskNode4.metaData("width", 160);
        humanTaskNode4.metaData("y", 201);
        humanTaskNode4.metaData("height", 83);
        org.jbpm.ruleflow.core.factory.StartNodeFactory<?> startNode5 = factory.startNode(5);
        startNode5.name("StartProcess");
        startNode5.interrupting(true);
        startNode5.metaData("UniqueId", "StartEvent_1");
        startNode5.metaData("elementname", "StartProcess");
        startNode5.metaData("x", 79);
        startNode5.metaData("width", 56);
        startNode5.metaData("y", 102);
        startNode5.metaData("height", 56);
        startNode5.done();
        org.jbpm.ruleflow.core.factory.RuleSetNodeFactory<?> ruleSetNode6 = factory.ruleSetNode(6);
        ruleSetNode6.name("Evaluate person");
        ruleSetNode6.ruleUnit("org.kie.kogito.examples.PersonValidationService", new org.jbpm.workflow.core.node.RuleUnitFactory<org.kie.kogito.examples.PersonValidationService>() {

            public org.kie.kogito.examples.PersonValidationService bind(org.kie.api.runtime.process.ProcessContext kcontext) {
                org.kie.kogito.examples.PersonValidationService unit = new org.kie.kogito.examples.PersonValidationService();
                org.kie.kogito.examples.demo.Person person = (org.kie.kogito.examples.demo.Person) (kcontext.getVariable("person"));
                {
                    org.drools.ruleunits.api.DataStore unit_persons = unit.getPersons();
                    unit_persons.subscribe(org.drools.ruleunits.api.DataObserver.ofUpdatable(o -> kcontext.setVariable("person", o)));
                }
                {
                    org.drools.ruleunits.api.DataStore unit_persons = unit.getPersons();
                    unit_persons.add((org.kie.kogito.examples.demo.Person) (kcontext.getVariable("person")));
                }
                return unit;
            }

            public org.drools.ruleunits.api.RuleUnit<org.kie.kogito.examples.PersonValidationService> unit() {
                return app.get(org.kie.kogito.rules.RuleUnits.class).create(org.kie.kogito.examples.PersonValidationService.class);
            }

            public void unbind(org.kie.api.runtime.process.ProcessContext kcontext, org.kie.kogito.examples.PersonValidationService unit) {
                org.kie.kogito.examples.demo.Person person = (org.kie.kogito.examples.demo.Person) (kcontext.getVariable("person"));
                {
                    org.drools.ruleunits.api.DataStore unit_persons = unit.getPersons();
                    unit_persons.subscribe(org.drools.ruleunits.api.DataObserver.ofUpdatable(o -> kcontext.setVariable("person", o)));
                }
            }
        });
        ruleSetNode6.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("person", "person", "java.lang.Object", null)), new org.jbpm.workflow.core.impl.DataDefinition("BusinessRuleTask_1_personsInputX", "persons", "org.kie.kogito.examples.demo.Person", null), null, null));
        ruleSetNode6.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("BusinessRuleTask_1_personsOutputX", "persons", "org.kie.kogito.examples.demo.Person", null)), new org.jbpm.workflow.core.impl.DataDefinition("person", "person", "java.lang.Object", null), null, null));
        ruleSetNode6.metaData("UniqueId", "BusinessRuleTask_1");
        ruleSetNode6.metaData("elementname", "Evaluate person");
        ruleSetNode6.metaData("x", 184);
        ruleSetNode6.metaData("width", 180);
        ruleSetNode6.metaData("y", 88);
        ruleSetNode6.metaData("height", 83);
        ruleSetNode6.done();
        org.jbpm.ruleflow.core.factory.SplitFactory<?> splitNode7 = factory.splitNode(7);
        splitNode7.name("Exclusive Gateway 1");
        splitNode7.type(2);
        splitNode7.metaData("UniqueId", "ExclusiveGateway_1");
        splitNode7.metaData("elementname", "Exclusive Gateway 1");
        splitNode7.metaData("x", 452);
        splitNode7.metaData("width", 56);
        splitNode7.metaData("y", 102);
        splitNode7.metaData("Default", null);
        splitNode7.metaData("height", 56);
        splitNode7.constraint(1, "SequenceFlow_5", "DROOLS_DEFAULT", "java", kcontext -> {
            org.kie.kogito.examples.demo.Person person = (org.kie.kogito.examples.demo.Person) kcontext.getVariable("person");
            return person.isAdult() == true;
        }, 0, false);
        splitNode7.constraint(8, "SequenceFlow_3", "DROOLS_DEFAULT", "java", kcontext -> {
            org.kie.kogito.examples.demo.Person person = (org.kie.kogito.examples.demo.Person) kcontext.getVariable("person");
            return person.isAdult() == false;
        }, 0, false);
        splitNode7.done();
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory<?> humanTaskNode8 = factory.humanTaskNode(8);
        humanTaskNode8.name("handling for children");
        humanTaskNode8.workParameter("NodeName", "handling for children");
        humanTaskNode8.workParameter("TaskName", "ChildrenHandling");
        humanTaskNode8.workParameter("Priority", "1");
        humanTaskNode8.workParameter("Skippable", "true");
        humanTaskNode8.workParameter("ActorId", "admin");
        humanTaskNode8.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("UserTask_1_TaskNameInputX", "TaskName", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("1e5dcd92-abd9-4111-a0e7-76e064b3285b", "EXPRESSION (ChildrenHandling)", "java.lang.Object", "ChildrenHandling"), new org.jbpm.workflow.core.impl.DataDefinition("UserTask_1_TaskNameInputX", "TaskName", "Object", null))), null));
        humanTaskNode8.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("person", "person", "java.lang.Object", null)), new org.jbpm.workflow.core.impl.DataDefinition("UserTask_1_personInputX", "person", "org.kie.kogito.examples.demo.Person", null), null, null));
        humanTaskNode8.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("UserTask_1_SkippableInputX", "Skippable", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("397b0098-cff5-4e4f-ae78-9902630faa7e", "EXPRESSION (true)", "java.lang.Object", "true"), new org.jbpm.workflow.core.impl.DataDefinition("UserTask_1_SkippableInputX", "Skippable", "Object", null))), null));
        humanTaskNode8.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("UserTask_1_PriorityInputX", "Priority", "Object", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("7a48c22d-feb5-4af9-8313-9f941a622277", "EXPRESSION (1)", "java.lang.Object", "1"), new org.jbpm.workflow.core.impl.DataDefinition("UserTask_1_PriorityInputX", "Priority", "Object", null))), null));
        humanTaskNode8.done();
        humanTaskNode8.metaData("UniqueId", "UserTask_1");
        humanTaskNode8.metaData("elementname", "handling for children");
        humanTaskNode8.metaData("x", 561);
        humanTaskNode8.metaData("width", 160);
        humanTaskNode8.metaData("y", 88);
        humanTaskNode8.metaData("height", 83);
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNode9 = factory.endNode(9);
        endNode9.name("End Event 1");
        endNode9.terminate(false);
        endNode9.metaData("UniqueId", "EndEvent_1");
        endNode9.metaData("elementname", "End Event 1");
        endNode9.metaData("x", 810);
        endNode9.metaData("width", 56);
        endNode9.metaData("y", 102);
        endNode9.metaData("height", 56);
        endNode9.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNode10 = factory.endNode(10);
        endNode10.name("End Event 2");
        endNode10.terminate(false);
        endNode10.metaData("UniqueId", "EndEvent_2");
        endNode10.metaData("elementname", "End Event 2");
        endNode10.metaData("x", 810);
        endNode10.metaData("width", 56);
        endNode10.metaData("y", 215);
        endNode10.metaData("height", 56);
        endNode10.done();
        factory.connection(7, 1, "SequenceFlow_5");
        factory.connection(3, 2, "_B1348055-244D-4CEB-B9AB-04AC012C51DC");
        factory.connection(1, 3, "_FAD1FFBC-3371-4D29-B91F-9FF4D6C0CBDC");
        factory.connection(1, 4, "_6F2887C0-1CB5-4D87-AC1A-0CBCCCBE2B42");
        factory.connection(5, 6, "SequenceFlow_1");
        factory.connection(6, 7, "SequenceFlow_2");
        factory.connection(7, 8, "SequenceFlow_3");
        factory.connection(8, 9, "SequenceFlow_4");
        factory.connection(4, 10, "_26275B0C-7259-4AA7-9BFA-B3E60A8FE161");
        factory.validate();
        return factory.getProcess();
    }
}
