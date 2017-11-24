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
package io.zeebe.client.workflow.impl;

import java.io.InputStream;

import com.fasterxml.jackson.annotation.*;
import io.zeebe.client.api.event.TopicEventType;
import io.zeebe.client.api.event.WorkflowInstanceEvent;
import io.zeebe.client.event.impl.EventImpl;
import io.zeebe.client.impl.data.MsgPackConverter;
import io.zeebe.client.task.impl.subscription.MsgPackField;

/**
 * Represents a event, which is used to create a workflow instance on the broker.
 */
public class WorkflowInstanceEventImpl extends EventImpl implements WorkflowInstanceEvent
{

    protected String bpmnProcessId;
    protected int version = -1;
    protected long workflowKey = -1L;
    protected long workflowInstanceKey = -1L;
    protected String activityId;
    protected final MsgPackField payload;

    @JsonCreator
    public WorkflowInstanceEventImpl(@JsonProperty("state") String state, @JacksonInject MsgPackConverter converter)
    {
        super(TopicEventType.WORKFLOW_INSTANCE, state);
        this.payload = new MsgPackField(converter);
    }

    public WorkflowInstanceEventImpl(WorkflowInstanceEventImpl baseEvent, String state)
    {
        super(baseEvent, state);
        this.bpmnProcessId = baseEvent.bpmnProcessId;
        this.version = baseEvent.version;
        this.workflowKey = baseEvent.workflowKey;
        this.workflowInstanceKey = baseEvent.workflowInstanceKey;
        this.activityId = baseEvent.activityId;
        this.payload = new MsgPackField(baseEvent.payload);
    }

    @Override
    public String getBpmnProcessId()
    {
        return bpmnProcessId;
    }

    public void setBpmnProcessId(String bpmnProcessId)
    {
        this.bpmnProcessId = bpmnProcessId;
    }

    @Override
    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    @Override
    public long getWorkflowInstanceKey()
    {
        return workflowInstanceKey;
    }

    public void setWorkflowInstanceKey(long workflowInstanceKey)
    {
        this.workflowInstanceKey = workflowInstanceKey;
    }

    @Override
    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    @Override
    @JsonIgnore
    public String getPayload()
    {
        return payload.getAsJson();
    }

    @JsonProperty("payload")
    public byte[] getPayloadMsgPack()
    {
        return this.payload.getMsgPack();
    }

    @JsonProperty("payload")
    public void setPayload(byte[] msgpack)
    {
        this.payload.setMsgPack(msgpack);
    }

    public void setPayloadAsJson(String json)
    {
        this.payload.setJson(json);
    }

    public void setPayloadAsJson(InputStream json)
    {
        this.payload.setJson(json);
    }

    @Override
    public long getWorkflowKey()
    {
        return workflowKey;
    }

    public void setWorkflowKey(long workflowKey)
    {
        this.workflowKey = workflowKey;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("WorkflowInstanceEvent [state=");
        builder.append(state);
        builder.append(", workflowInstanceKey=");
        builder.append(workflowInstanceKey);
        builder.append(", workflowKey=");
        builder.append(workflowKey);
        builder.append(", bpmnProcessId=");
        builder.append(bpmnProcessId);
        builder.append(", version=");
        builder.append(version);
        builder.append(", activityId=");
        builder.append(activityId);
        builder.append(", payload=");
        builder.append(payload.getAsJson());
        builder.append("]");
        return builder.toString();
    }
}
