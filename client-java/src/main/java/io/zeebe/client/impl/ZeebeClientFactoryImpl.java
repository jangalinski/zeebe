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
package io.zeebe.client.impl;

import java.util.Properties;

import io.zeebe.client.api.ZeebeClient;
import io.zeebe.client.api.ZeebeClientFactory;

public class ZeebeClientFactoryImpl implements ZeebeClientFactory
{

    @Override
    public ZeebeClient create(Properties properties)
    {
        return new ZeebeClientImpl(properties);
    }
}
