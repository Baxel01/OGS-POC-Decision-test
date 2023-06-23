/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.examples;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import java.util.Map;
import java.util.HashMap;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;

@org.kie.kogito.codegen.Generated(value = "kogito-codegen", reference = "demo.orders", name = "Orders", hidden = false)
public class OrdersModel implements org.kie.kogito.Model, MapInput, MapInputId, MapOutput, MappableToModel<OrdersModelOutput> {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "approver")
    @javax.validation.Valid()
    private java.lang.String approver;

    public java.lang.String getApprover() {
        return approver;
    }

    public void setApprover(java.lang.String approver) {
        this.approver = approver;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "order")
    @javax.validation.Valid()
    private org.kie.kogito.examples.demo.Order order;

    public org.kie.kogito.examples.demo.Order getOrder() {
        return order;
    }

    public void setOrder(org.kie.kogito.examples.demo.Order order) {
        this.order = order;
    }

    @Override()
    public OrdersModelOutput toModel() {
        OrdersModelOutput result = new OrdersModelOutput();
        result.setId(getId());
        result.setApprover(getApprover());
        result.setOrder(getOrder());
        return result;
    }
}
