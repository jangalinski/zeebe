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
package io.zeebe.perftest;

import static io.zeebe.perftest.CommonProperties.DEFAULT_TOPIC_NAME;

import java.util.concurrent.Future;
import java.util.function.Supplier;

import io.zeebe.client.api.TasksClient;
import io.zeebe.client.api.ZeebeClient;
import io.zeebe.perftest.helper.MaxRateThroughputTest;


public class CreateTaskThroughputTest extends MaxRateThroughputTest
{
    private static final String TASK_TYPE = "example-task-type";

    public static void main(String[] args)
    {
        new CreateTaskThroughputTest().run();
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Supplier<Future> requestFn(ZeebeClient client)
    {
        final TasksClient tasksClient = client.tasks();

        return () -> tasksClient.create(DEFAULT_TOPIC_NAME, TASK_TYPE).executeAsync();
    }

}
