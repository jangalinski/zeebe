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
package io.zeebe.client.api;

import java.util.Properties;
import java.util.ServiceLoader;

import io.zeebe.client.api.clustering.TopologyResponse;
import io.zeebe.client.api.cmd.Request;

public interface ZeebeClient extends AutoCloseable
{

    /**
     * Provides APIs revolving around task events, such as creating a task.
     */
    TasksClient tasks();

    /**
     * Provides APIs revolving around workflow events, such as creating a workflow instance.
     */
    WorkflowsClient workflows();

    /**
     * Provides cross-cutting APIs related to any topic, such as subscribing to topic events.
     */
    TopicsClient topics();

    /**
     * Fetches the current cluster topology, i.e. which brokers are available at which endpoint
     * and which broker is the leader of which topic.
     */
    Request<TopologyResponse> requestTopology();

    /**
     * Gracefully closes all current connections to all brokers. This includes closing
     * all open task and topic subscriptions. The client establishes new connections as soon as
     * new requests are made.
     */
    void disconnect();

    @Override
    void close();

    static ZeebeClient create(Properties properties)
    {
        final ServiceLoader<ZeebeClientFactory> client = ServiceLoader.load(ZeebeClientFactory.class);
        return client.iterator().next().create(properties);
    }
}
