package org.kie.kogito.examples;

import org.kie.kogito.examples.OrderItemsModel;
import org.kie.api.definition.process.Process;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.drools.core.util.KieFunctions;
import org.jbpm.process.core.datatype.impl.type.StringDataType;

@org.springframework.stereotype.Component("demo.orderItems")
public class OrderItemsProcess extends org.kie.kogito.process.impl.AbstractProcess<org.kie.kogito.examples.OrderItemsModel> {

    public OrderItemsProcess(org.kie.kogito.app.Application app, org.kie.kogito.correlation.CorrelationService correlations) {
        this(app, correlations, new org.kie.kogito.handlers.CalculationService_calculateTotal_3_Handler());
    }

    @org.springframework.beans.factory.annotation.Autowired()
    public OrderItemsProcess(org.kie.kogito.app.Application app, org.kie.kogito.correlation.CorrelationService correlations, org.kie.kogito.handlers.CalculationService_calculateTotal_3_Handler calculationService_calculateTotal_3_Handler) {
        super(app, java.util.Arrays.asList(calculationService_calculateTotal_3_Handler), correlations);
        activate();
    }

    public OrderItemsProcess() {
    }

    @Override()
    public org.kie.kogito.examples.OrderItemsProcessInstance createInstance(org.kie.kogito.examples.OrderItemsModel value) {
        return new org.kie.kogito.examples.OrderItemsProcessInstance(this, value, this.createProcessRuntime());
    }

    public org.kie.kogito.examples.OrderItemsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.examples.OrderItemsModel value) {
        return new org.kie.kogito.examples.OrderItemsProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    public org.kie.kogito.examples.OrderItemsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.correlation.CompositeCorrelation correlation, org.kie.kogito.examples.OrderItemsModel value) {
        return new org.kie.kogito.examples.OrderItemsProcessInstance(this, value, businessKey, this.createProcessRuntime(), correlation);
    }

    @Override()
    public org.kie.kogito.examples.OrderItemsModel createModel() {
        return new org.kie.kogito.examples.OrderItemsModel();
    }

    public org.kie.kogito.examples.OrderItemsProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.kie.kogito.examples.OrderItemsModel) value);
    }

    public org.kie.kogito.examples.OrderItemsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.kie.kogito.examples.OrderItemsModel) value);
    }

    public org.kie.kogito.examples.OrderItemsProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.kie.kogito.examples.OrderItemsProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public org.kie.kogito.examples.OrderItemsProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.kie.kogito.examples.OrderItemsProcessInstance(this, this.createModel(), wpi);
    }

    protected org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("demo.orderItems", true);
        factory.variable("order", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(org.kie.kogito.examples.demo.Order.class), null, "customTags", null);
        factory.variable("item", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), null, "customTags", null);
        factory.name("orderItems");
        factory.packageName("org.kie.kogito.examples");
        factory.dynamic(false);
        factory.version("1.0");
        factory.type("BPMN");
        factory.visibility("Private");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        factory.imports("org.kie.kogito.examples.demo.Order");
        org.jbpm.ruleflow.core.factory.StartNodeFactory<?> startNode1 = factory.startNode(1);
        startNode1.name("Start");
        startNode1.interrupting(true);
        startNode1.metaData("UniqueId", "_6AB17406-9386-43F2-BC8B-C987B93CA9F2");
        startNode1.metaData("elementname", null);
        startNode1.metaData("x", 100);
        startNode1.metaData("width", 56);
        startNode1.metaData("y", 100);
        startNode1.metaData("height", 56);
        startNode1.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNode2 = factory.endNode(2);
        endNode2.name("End");
        endNode2.terminate(false);
        endNode2.metaData("UniqueId", "_ACB2B473-2FCB-4E52-B96A-C0926782126F");
        endNode2.metaData("elementname", null);
        endNode2.metaData("x", 704);
        endNode2.metaData("width", 56);
        endNode2.metaData("y", 100);
        endNode2.metaData("height", 56);
        endNode2.done();
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNode3 = factory.workItemNode(3);
        workItemNode3.name("Calculate total");
        workItemNode3.workName("org.kie.kogito.examples.CalculationService_calculateTotal_3_Handler");
        workItemNode3.workParameter("NodeName", "Calculate total");
        workItemNode3.workParameter("Interface", "org.kie.kogito.examples.CalculationService");
        workItemNode3.workParameter("Operation", "calculateTotal");
        workItemNode3.workParameter("ParameterType", "org.kie.kogito.examples.demo.Order");
        workItemNode3.workParameter("interfaceImplementationRef", "org.kie.kogito.examples.CalculationService");
        workItemNode3.workParameter("implementation", "java");
        workItemNode3.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("order", "order", "java.lang.Object", null)), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_1", "Parameter", "org.kie.kogito.examples.demo.Order", null), null, null));
        workItemNode3.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("DataOutput_1", "Result", "org.kie.kogito.examples.demo.Order", null)), new org.jbpm.workflow.core.impl.DataDefinition("order", "order", "java.lang.Object", null), null, null));
        workItemNode3.done();
        workItemNode3.metaData("UniqueId", "ServiceTask_1");
        workItemNode3.metaData("Implementation", "java");
        workItemNode3.metaData("elementname", "Calculate total");
        workItemNode3.metaData("Type", "Service Task");
        workItemNode3.metaData("OperationRef", "Operation_1");
        workItemNode3.metaData("x", 220);
        workItemNode3.metaData("width", 161);
        workItemNode3.metaData("y", 77);
        workItemNode3.metaData("height", 102);
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory<?> humanTaskNode4 = factory.humanTaskNode(4);
        humanTaskNode4.name("Verify order");
        humanTaskNode4.workParameter("NodeName", "Verify order");
        humanTaskNode4.workParameter("TaskName", "Verify order");
        humanTaskNode4.workParameter("Priority", "1");
        humanTaskNode4.workParameter("Skippable", "true");
        humanTaskNode4.workParameter("ActorId", "john");
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_14", "TaskName", "java.lang.String", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("ccedaac5-6cc7-4833-8d5a-7d490ea88d5c", "EXPRESSION (Verify order)", "java.lang.Object", "Verify order"), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_14", "TaskName", "java.lang.String", null))), null));
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_15", "Priority", "java.lang.Integer", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("9f9ef87f-7e38-4684-a913-a6d48590d9b1", "EXPRESSION (1)", "java.lang.Object", "1"), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_15", "Priority", "java.lang.Integer", null))), null));
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_19", "Skippable", "java.lang.Boolean", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("07121ce3-98f8-4c90-b215-ae6159b4dc61", "EXPRESSION (true)", "java.lang.Object", "true"), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_19", "Skippable", "java.lang.Boolean", null))), null));
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_21", "Locale", "java.lang.String", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("03a1c164-c5d9-43e0-9b8c-37845542381a", "EXPRESSION (en-UK)", "java.lang.Object", "en-UK"), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_21", "Locale", "java.lang.String", null))), null));
        humanTaskNode4.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("order", "order", "java.lang.Object", null)), new org.jbpm.workflow.core.impl.DataDefinition("DataInput_23", "input1", "org.kie.kogito.examples.demo.Order", null), null, null));
        humanTaskNode4.done();
        humanTaskNode4.metaData("UniqueId", "UserTask_1");
        humanTaskNode4.metaData("elementname", "Verify order");
        humanTaskNode4.metaData("x", 490);
        humanTaskNode4.metaData("width", 131);
        humanTaskNode4.metaData("y", 77);
        humanTaskNode4.metaData("height", 102);
        factory.connection(4, 2, "SequenceFlow_4");
        factory.connection(1, 3, "SequenceFlow_1");
        factory.connection(3, 4, "SequenceFlow_3");
        factory.validate();
        return factory.getProcess();
    }
}
