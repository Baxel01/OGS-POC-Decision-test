package org.kie.kogito.examples;

import org.kie.kogito.examples.OrdersModel;

public class OrdersProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<OrdersModel> {

    public OrdersProcessInstance(org.kie.kogito.examples.OrdersProcess process, OrdersModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public OrdersProcessInstance(org.kie.kogito.examples.OrdersProcess process, OrdersModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    public OrdersProcessInstance(org.kie.kogito.examples.OrdersProcess process, OrdersModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, processRuntime, wpi);
    }

    public OrdersProcessInstance(org.kie.kogito.examples.OrdersProcess process, OrdersModel value, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, wpi);
    }

    public OrdersProcessInstance(org.kie.kogito.examples.OrdersProcess process, OrdersModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.kogito.correlation.CompositeCorrelation correlation) {
        super(process, value, businessKey, processRuntime, correlation);
    }

    protected java.util.Map<String, Object> bind(OrdersModel variables) {
        if (null != variables)
            return variables.toMap();
        else
            return new java.util.HashMap();
    }

    protected void unbind(OrdersModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
