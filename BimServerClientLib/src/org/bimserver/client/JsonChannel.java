package org.bimserver.client;

/******************************************************************************
 * Copyright (C) 2009-2013  BIMserver.org
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

import org.bimserver.client.channels.Channel;
import org.bimserver.shared.TokenHolder;
import org.bimserver.shared.json.JsonReflector;
import org.bimserver.shared.reflector.ReflectorFactory;

public class JsonChannel extends Channel {

	private final ReflectorFactory reflectorFactory;
	private final JsonReflectorFactory jsonReflectorFactory;
	private JsonReflector reflector;
	private String address;

	public JsonChannel(ReflectorFactory reflectorFactory, JsonReflectorFactory jsonReflectorFactory, String address) {
		this.reflectorFactory = reflectorFactory;
		this.jsonReflectorFactory = jsonReflectorFactory;
		this.address = address;
	}

	@Override
	public void disconnect() {
	}

	public void connect(TokenHolder tokenHolder) throws ChannelConnectionException {
		reflector = jsonReflectorFactory.create(address, tokenHolder);
		finish(reflector, reflectorFactory);
	}
}