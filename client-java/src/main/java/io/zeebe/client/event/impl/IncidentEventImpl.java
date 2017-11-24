/*
 * Copyright © 2017 camunda services GmbH (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.zeebe.client.event.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.zeebe.client.api.event.IncidentEvent;
import io.zeebe.client.api.event.TopicEventType;

public class IncidentEventImpl extends EventImpl implements IncidentEvent
{

    private String errorType;
    private String errorMessage;

    private String bpmnProcessId;
    private Long workflowInstanceKey;
    private String activityId;
    private Long activityInstanceKey;

    private Long taskKey;

    @JsonCreator
    public IncidentEventImpl(@JsonProperty("state") String state)
    {
        super(TopicEventType.INCIDENT, state);
    }

    @Override
    public String getErrorType()
    {
        return errorType;
    }

    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getBpmnProcessId()
    {
        return bpmnProcessId != null && !bpmnProcessId.isEmpty() ? bpmnProcessId : null;
    }

    public void setBpmnProcessId(String bpmnProcessId)
    {
        this.bpmnProcessId = bpmnProcessId;
    }

    @Override
    public String getActivityId()
    {
        return activityId != null && !activityId.isEmpty() ? activityId : null;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    @Override
    public Long getActivityInstanceKey()
    {
        return activityInstanceKey > 0 ? activityInstanceKey : null;
    }

    public void setActivityInstanceKey(long activityInstanceKey)
    {
        this.activityInstanceKey = activityInstanceKey;
    }

    @Override
    public Long getTaskKey()
    {
        return taskKey > 0 ? taskKey : null;
    }

    public void setTaskKey(long taskKey)
    {
        this.taskKey = taskKey;
    }

    @Override
    public Long getWorkflowInstanceKey()
    {
        return workflowInstanceKey > 0 ? workflowInstanceKey : null;
    }

    public void setWorkflowInstanceKey(long workflowInstanceKey)
    {
        this.workflowInstanceKey = workflowInstanceKey;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("IncidentEvent [state=");
        builder.append(state);
        builder.append(", errorType=");
        builder.append(errorType);
        builder.append(", errorMessage=");
        builder.append(errorMessage);
        builder.append(", bpmnProcessId=");
        builder.append(bpmnProcessId);
        builder.append(", workflowInstanceKey=");
        builder.append(workflowInstanceKey);
        builder.append(", activityId=");
        builder.append(activityId);
        builder.append(", activityInstanceKey=");
        builder.append(activityInstanceKey);
        builder.append(", taskKey=");
        builder.append(taskKey);
        builder.append("]");
        return builder.toString();
    }

}
