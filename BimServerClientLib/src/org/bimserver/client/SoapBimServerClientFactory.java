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

import org.bimserver.client.channels.SoapChannel;
import org.bimserver.shared.AuthenticationInfo;
import org.bimserver.shared.exceptions.ServiceException;
import org.bimserver.shared.meta.ServicesMap;

public class SoapBimServerClientFactory extends AbstractBimServerClientFactory {

	private String address;

	public SoapBimServerClientFactory(String address, ServicesMap servicesMap) {
		super(servicesMap);
		this.address = address;
	}
	
	public SoapBimServerClientFactory(String address) {
		this.address = address;
	}

	@Override
	public BimServerClient create(AuthenticationInfo authenticationInfo) throws ServiceException, ChannelConnectionException {
		SoapChannel soapChannel = new SoapChannel(address + "/soap", false);
		BimServerClient bimServerClient = new BimServerClient(address, getServicesMap(), soapChannel);
		soapChannel.connect(bimServerClient);
		bimServerClient.setAuthentication(authenticationInfo);
		bimServerClient.connect();
		return bimServerClient;
	}
}