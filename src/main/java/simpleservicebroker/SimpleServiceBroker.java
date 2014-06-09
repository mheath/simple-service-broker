/*
 *   Copyright (c) 2014 Intellectual Reserve, Inc.  All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package simpleservicebroker;

import cf.spring.servicebroker.Bind;
import cf.spring.servicebroker.BindRequest;
import cf.spring.servicebroker.BindResponse;
import cf.spring.servicebroker.Deprovision;
import cf.spring.servicebroker.DeprovisionRequest;
import cf.spring.servicebroker.Provision;
import cf.spring.servicebroker.ProvisionRequest;
import cf.spring.servicebroker.ProvisionResponse;
import cf.spring.servicebroker.Service;
import cf.spring.servicebroker.ServiceBroker;
import cf.spring.servicebroker.ServicePlan;
import cf.spring.servicebroker.Unbind;
import cf.spring.servicebroker.UnbindRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@ServiceBroker(
	@Service(id = "service", name = "simple-service", description = "A simple service. It doesn't do much.",
			plans = {
					@ServicePlan(id = "plan1", name = "service-plan", description = "It's just a service plan"),
					@ServicePlan(id = "plan2", name = "another-service-plan", description = "Just another service plan")

		}
	)
)
public class SimpleServiceBroker {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServiceBroker.class);

	@Provision
	public ProvisionResponse provision(ProvisionRequest request) {
		LOGGER.info("Provisioning service with id {}", request.getInstanceGuid());
		return new ProvisionResponse();
	}

	@Deprovision
	public void deprovision(DeprovisionRequest request) {
		LOGGER.info("Deprovisioning service with id {}", request.getInstanceGuid());
	}

	@Bind
	public BindResponse bind(BindRequest request) {
		LOGGER.info(
				"Binding service instance {} to application {}",
				request.getServiceInstanceGuid(),
				request.getApplicationGuid());
		Map<String, String> credentials = new HashMap<>();
		credentials.put("some_credential", "foo");
		credentials.put("another_credential", "bar");
		return new BindResponse(credentials);
	}

	@Unbind
	public void unbind(UnbindRequest request) {
		LOGGER.info("Unbind service instance {}",
				request.getServiceInstanceGuid());
	}
}
