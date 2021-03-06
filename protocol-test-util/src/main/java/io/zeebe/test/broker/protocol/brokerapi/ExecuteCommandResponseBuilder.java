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
package io.zeebe.test.broker.protocol.brokerapi;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import io.zeebe.test.broker.protocol.MsgPackHelper;
import io.zeebe.test.util.collection.MapFactoryBuilder;

public class ExecuteCommandResponseBuilder
{

    protected final Consumer<MessageBuilder<ExecuteCommandRequest>> registrationFunction;
    protected final ExecuteCommandResponseWriter commandResponseWriter;

    public ExecuteCommandResponseBuilder(
            Consumer<MessageBuilder<ExecuteCommandRequest>> registrationFunction,
            MsgPackHelper msgPackConverter)
    {
        this.registrationFunction = registrationFunction;
        this.commandResponseWriter = new ExecuteCommandResponseWriter(msgPackConverter);
        partitionId(r -> r.partitionId()); // default
    }

    public ExecuteCommandResponseBuilder partitionId(final int partitionId)
    {
        return partitionId((r) -> partitionId);
    }

    public ExecuteCommandResponseBuilder partitionId(Function<ExecuteCommandRequest, Integer> partitionIdFunction)
    {
        commandResponseWriter.setPartitionIdFunction(partitionIdFunction);
        return this;
    }

    public ExecuteCommandResponseBuilder key(long l)
    {
        return key((r) -> l);
    }

    public ExecuteCommandResponseBuilder key(Function<ExecuteCommandRequest, Long> keyFunction)
    {
        commandResponseWriter.setKeyFunction(keyFunction);
        return this;
    }

    public ExecuteCommandResponseBuilder position(long position)
    {
        return position((r) -> position);
    }

    public ExecuteCommandResponseBuilder position(Function<ExecuteCommandRequest, Long> positionFunction)
    {
        commandResponseWriter.setPositionFunction(positionFunction);
        return this;
    }

    public ExecuteCommandResponseBuilder event(Map<String, Object> map)
    {
        commandResponseWriter.setEventFunction((re) -> map);
        return this;
    }

    public MapFactoryBuilder<ExecuteCommandRequest, ExecuteCommandResponseBuilder> event()
    {
        return new MapFactoryBuilder<>(this, commandResponseWriter::setEventFunction);
    }

    public void register()
    {
        registrationFunction.accept(commandResponseWriter);
    }

    /**
     * Blocks before responding; continues sending the response only when {@link ResponseController#unblockNextResponse()} is called.
     */
    public ResponseController registerControlled()
    {
        final ResponseController controller = new ResponseController();
        final NotifyingMessageBuilder<ExecuteCommandRequest> notifyingBuilder = new NotifyingMessageBuilder<>(commandResponseWriter, controller::waitForNextJoin);
        registrationFunction.accept(notifyingBuilder);
        return controller;
    }
}
