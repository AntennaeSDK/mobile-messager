/*
 * Copyright 2015 the original author or authors.
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

package com.github.antennaesdk.messageserver.gcm.xmpp;

import org.jivesoftware.smack.packet.DefaultExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public class GcmPacketExtension extends DefaultExtensionElement {
	
	private String json;

	public GcmPacketExtension(String json) {
		super(GcmXmppClient.GCM_ELEMENT_NAME, GcmXmppClient.GCM_NAMESPACE);
		this.json = json;
	}

	public String getJson() {
		return json;
	}

	@Override
	public String toXML() {
		return String.format("<%s xmlns=\"%s\">%s</%s>", 
							GcmXmppClient.GCM_ELEMENT_NAME, 
							GcmXmppClient.GCM_NAMESPACE, 
							json, 
							GcmXmppClient.GCM_ELEMENT_NAME);
	}

	public Stanza toPacket() {
        Message message = new Message();
        message.addExtension(this);
        return message;
    }
}
