package org.kie.kogito.examples;

import org.kie.kogito.examples.OrdersModel;
import org.kie.api.definition.process.Process;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.drools.core.util.KieFunctions;
import org.jbpm.process.core.datatype.impl.type.StringDataType;

@org.springframework.stereotype.Component("demo.orders")
public class OrdersProcess extends org.kie.kogito.process.impl.AbstractProcess<org.kie.kogito.examples.OrdersModel> {

    @org.springframework.beans.factory.annotation.Autowired()
    @org.springframework.beans.factory.annotation.Qualifier("demo.orderItems")
    org.kie.kogito.process.Process<OrderItemsModel> processorderItems;

    @org.springframework.beans.factory.annotation.Autowired()
    public OrdersProcess(org.kie.kogito.app.Application app, org.kie.kogito.correlation.CorrelationService correlations) {
        super(app, java.util.Arrays.asList(), correlations);
        activate();
    }

    public OrdersProcess() {
    }

    @Override()
    public org.kie.kogito.examples.OrdersProcessInstance createInstance(org.kie.kogito.examples.OrdersModel value) {
        return new org.kie.kogito.examples.OrdersProcessInstance(this, value, this.createProcessRuntime());
    }

    public org.kie.kogito.examples.OrdersProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.examples.OrdersModel value) {
        return new org.kie.kogito.examples.OrdersProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    public org.kie.kogito.examples.OrdersProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.correlation.CompositeCorrelation correlation, org.kie.kogito.examples.OrdersModel value) {
        return new org.kie.kogito.examples.OrdersProcessInstance(this, value, businessKey, this.createProcessRuntime(), correlation);
    }

    @Override()
    public org.kie.kogito.examples.OrdersModel createModel() {
        return new org.kie.kogito.examples.OrdersModel();
    }

    public org.kie.kogito.examples.OrdersProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.kie.kogito.examples.OrdersModel) value);
    }

    public org.kie.kogito.examples.OrdersProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.kie.kogito.examples.OrdersModel) value);
    }

    public org.kie.kogito.examples.OrdersProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.kie.kogito.examples.OrdersProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public org.kie.kogito.examples.OrdersProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.kie.kogito.examples.OrdersProcessInstance(this, this.createModel(), wpi);
    }

    protected org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("demo.orders", true);
        factory.variable("order", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(org.kie.kogito.examples.demo.Order.class), null, "customTags", null);
        factory.variable("approver", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), null, "customTags", null);
        factory.name("orders");
        factory.packageName("org.kie.kogito.examples");
        factory.dynamic(false);
        factory.version("1.0");
        factory.type("BPMN");
        factory.visibility("Public");
        factory.metaData("Documentation", "Deals with orders created by customer");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode1 = factory.actionNode(1);
        actionNode1.name("Dump order");
        actionNode1.action(kcontext -> {
            org.kie.kogito.examples.demo.Order order = (org.kie.kogito.examples.demo.Order) kcontext.getVariable("order");
            java.lang.String approver = (java.lang.String) kcontext.getVariable("approver");
            System.out.println("Order has been created " + order + " with assigned approver " + approver.toUpperCase());
        });
        actionNode1.metaData("UniqueId", "_0617D7DF-047A-4EC4-85E7-E201D640F4F5");
        actionNode1.metaData("elementname", "Dump order");
        actionNode1.metaData("NodeType", "ScriptTask");
        actionNode1.metaData("x", 236);
        actionNode1.metaData("width", 154);
        actionNode1.metaData("y", 77);
        actionNode1.metaData("height", 102);
        actionNode1.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNode2 = factory.endNode(2);
        endNode2.name("End");
        endNode2.terminate(false);
        endNode2.metaData("UniqueId", "_2D876EF2-93F4-4CBE-959A-04EF7BFA9CED");
        endNode2.metaData("x", 712);
        endNode2.metaData("width", 56);
        endNode2.metaData("y", 100);
        endNode2.metaData("height", 56);
        endNode2.done();
        org.jbpm.ruleflow.core.factory.SubProcessNodeFactory<?> subProcessNode3 = factory.subProcessNode(3);
        subProcessNode3.name("Add items");
        subProcessNode3.processId("demo.orderItems");
        subProcessNode3.processName("");
        subProcessNode3.waitForCompletion(true);
        subProcessNode3.independent(false);
        subProcessNode3.subProcessNode(new org.jbpm.workflow.core.node.SubProcessFactory<OrderItemsModel>() {

            public OrderItemsModel bind(org.kie.api.runtime.process.ProcessContext kcontext) {
                org.kie.kogito.examples.OrderItemsModel model = new org.kie.kogito.examples.OrderItemsModel();
                java.util.Map<java.lang.String, java.lang.Object> inputs = org.jbpm.workflow.core.impl.NodeIoHelper.processInputs((org.jbpm.workflow.instance.impl.NodeInstanceImpl) kcontext.getNodeInstance(), (java.lang.String name) -> {
                    return kcontext.getVariable(name);
                });
                model.update(inputs);
                return model;
            }

            public org.kie.kogito.process.ProcessInstance<OrderItemsModel> createInstance(OrderItemsModel model) {
                return processorderItems.createInstance(model);
            }

            public void unbind(org.kie.api.runtime.process.ProcessContext kcontext, OrderItemsModel model) {
                java.util.Map<java.lang.String, java.lang.Object> outputs = new java.util.HashMap<java.lang.String, java.lang.Object>();
                outputs.put("order", model.getOrder());
                org.jbpm.workflow.core.impl.NodeIoHelper.processOutputs((org.jbpm.workflow.instance.impl.NodeInstanceImpl) kcontext.getNodeInstance(), (java.lang.String name) -> {
                    return outputs.get(name);
                }, (java.lang.String name) -> {
                    return kcontext.getVariable(name);
                });
            }
        });
        subProcessNode3.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("order", "order", "java.lang.Object", null)), new org.jbpm.workflow.core.impl.DataDefinition("_9484CB12-FE52-434C-AE9F-3C3C267D1C96_orderInputX", "order", "org.kie.kogito.examples.demo.Order", null), null, null));
        subProcessNode3.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_9484CB12-FE52-434C-AE9F-3C3C267D1C96_orderOutputX", "order", "org.kie.kogito.examples.demo.Order", null)), new org.jbpm.workflow.core.impl.DataDefinition("order", "order", "java.lang.Object", null), null, null));
        subProcessNode3.metaData("UniqueId", "_9484CB12-FE52-434C-AE9F-3C3C267D1C96");
        subProcessNode3.metaData("elementname", "Add items");
        subProcessNode3.metaData("x", 458);
        subProcessNode3.metaData("width", 153);
        subProcessNode3.metaData("y", 78);
        subProcessNode3.metaData("height", 101);
        subProcessNode3.done();
        org.jbpm.ruleflow.core.factory.StartNodeFactory<?> startNode4 = factory.startNode(4);
        startNode4.name("Start");
        startNode4.interrupting(true);
        startNode4.metaData("UniqueId", "_B44545AB-8B78-4FE4-B6B9-1D467954C070");
        startNode4.metaData("x", 100);
        startNode4.metaData("width", 56);
        startNode4.metaData("y", 100);
        startNode4.metaData("height", 56);
        startNode4.done();
        factory.connection(4, 1, "_B7B4282B-F317-4BF9-95E9-962B046EE815");
        factory.connection(3, 2, "_8216C810-34D8-4BFA-B814-1AA01907810F");
        factory.connection(1, 3, "_58684613-0155-48B2-8746-7675AFF24439");
        factory.validate();
        return factory.getProcess();
    }

    protected void registerListeners() {
        services.getSignalManager().addEventListener("demo.orderItems", completionEventListener);
    }
}
