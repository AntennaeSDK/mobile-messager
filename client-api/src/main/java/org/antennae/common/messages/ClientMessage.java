/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.antennae.common.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.UUID;

/**
 * <code>ClientMessage</code> is sent to a client.
 * A client is a mobile-app or IOT app.
 * OR it could be an user on several devices.
 *
 * <code>ClientMessage</code> can be sent by a client to another client or from server to a client.
 *
 * @see ClientAddress for more details.
 */
public class ClientMessage {

    /**
     * To where the message is sent.
     * it can be to an user or an app
     */
    private ClientAddress to;

    /** actual message */
    private String payLoad;

    /** The requestId is generated by the requester at the time of the original message.
     * it may be derived from a @See ServerMessage and passed on by the server in the ClientMessage
     */
    private String requestId;

    // TODO: use TypeAdapterFactory instead of passing the type.
    private String classType = ClientMessage.class.getName();

    /**
     * Broker decides the delivery of the message, based on the QOS type;
     */
    private ClientMessageQOSEnum messageQOS = ClientMessageQOSEnum.DIRECT_CONNECTION_ONLY;

    public ClientMessage(String requestId) {
        this.requestId = requestId;
    }
    public ClientMessage(){
        this.requestId = UUID.randomUUID().toString();
    }
    public ClientAddress getTo() {
        return to;
    }
    public void setTo(ClientAddress to) {
        this.to = to;
    }
    public String getPayLoad() {
        return payLoad;
    }
    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }
    public String getRequestId() {
        return requestId;
    }
    public ClientMessageQOSEnum getMessageQOS() {
        return messageQOS;
    }
    public void setMessageQOS(ClientMessageQOSEnum messageQOS) {
        this.messageQOS = messageQOS;
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
    public String toJsonPretty(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        return json;
    }

    public static ClientMessage fromJson(String json ){
        Gson gson = new Gson();
        ClientMessage result = gson.fromJson( json, ClientMessage.class);
        return result;
    }

}