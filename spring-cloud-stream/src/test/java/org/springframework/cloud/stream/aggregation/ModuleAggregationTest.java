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

package org.springframework.cloud.stream.aggregation;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.aggregate.AggregateApplication;
import org.springframework.cloud.stream.aggregate.SharedChannelRegistry;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BindableChannelFactory;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Marius Bogoevici
 */
@Ignore("https://github.com/spring-cloud/spring-cloud-stream/issues/241")
public class ModuleAggregationTest {

	@Test
	public void testModuleAggregation() {
		ConfigurableApplicationContext aggregatedApplicationContext = AggregateApplication.run(TestSource.class,
				TestProcessor.class);
		SharedChannelRegistry sharedChannelRegistry = aggregatedApplicationContext.getBean(SharedChannelRegistry.class);
		BindableChannelFactory channelFactory = aggregatedApplicationContext.getBean(BindableChannelFactory.class);
		assertNotNull(channelFactory);
		assertThat(sharedChannelRegistry.getAll().keySet(), hasSize(2));
	}


	@EnableBinding(Source.class)
	@EnableAutoConfiguration
	public static class TestSource {

	}

	@EnableBinding(Processor.class)
	@EnableAutoConfiguration
	public static class TestProcessor {

	}

}
