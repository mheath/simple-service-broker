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

import cf.spring.CfComponent;
import cf.spring.servicebroker.EnableServiceBroker;
import nats.client.Nats;
import nats.client.NatsConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Spring Configuration
@Configuration
@EnableAutoConfiguration
@ComponentScan

// Enable /healthz and /varz
@CfComponent(type="ServiceBroker")
// Service Broker Configuration
@EnableServiceBroker(username = "servicebroker", password = "password")
public class Main {

	@Bean
	Nats nats() { return new NatsConnector().addHost("nats://nats:password@10.1.0.1").connect(); }

	public static void main(String[] args) {
		new SpringApplication(Main.class).run(args);
	}

}
