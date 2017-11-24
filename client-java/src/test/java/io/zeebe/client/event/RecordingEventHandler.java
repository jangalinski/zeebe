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
package io.zeebe.client.event;

import java.util.ArrayList;
import java.util.List;

import io.zeebe.client.api.event.GeneralEvent;
import io.zeebe.client.api.event.UniversalEventHandler;

public class RecordingEventHandler implements UniversalEventHandler
{

    protected List<GeneralEvent> events = new ArrayList<>();

    @Override
    public void handle(GeneralEvent event)
    {
        this.events.add(event);
    }

    public int numRecordedEvents()
    {
        return events.size();
    }

    public List<GeneralEvent> getRecordedEvents()
    {
        return events;
    }

    public void reset()
    {
        this.events.clear();
    }
}
