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

import java.util.Map;
import org.kie.kogito.UserTask;
import org.kie.kogito.UserTaskParam.ParamType;
import org.kie.kogito.UserTaskParam;

@UserTask(taskName = "Verify order", processName = "demo.orderItems")
public class OrderItems_4_TaskInput {

    public static OrderItems_4_TaskInput fromMap(Map<String, Object> params) {
        OrderItems_4_TaskInput item = new OrderItems_4_TaskInput();
        item.input1 = (org.kie.kogito.examples.demo.Order) params.get("input1");
        return item;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private org.kie.kogito.examples.demo.Order input1;

    public org.kie.kogito.examples.demo.Order getInput1() {
        return input1;
    }

    public void setInput1(org.kie.kogito.examples.demo.Order input1) {
        this.input1 = input1;
    }
}
// Task input for user task 'Verify order' in process 'demo.orderItems'
