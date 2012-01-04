/*
 * Copyright 2010 Proofpoint, Inc.
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
package com.proofpoint.cloudmanagement.service;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.proofpoint.configuration.ConfigurationModule;
import com.proofpoint.discovery.client.DiscoveryBinder;

public class MainModule
        implements Module
{
    public void configure(Binder binder)
    {
        binder.requireExplicitBindings();
        binder.disableCircularProxies();

        binder.bind(InstancesResource.class).in(Scopes.SINGLETON);
        binder.bind(InstanceResource.class).in(Scopes.SINGLETON);
        binder.bind(SizeResource.class).in(Scopes.SINGLETON);
        binder.bind(TagResource.class).in(Scopes.SINGLETON);
        binder.bind(InstanceConnector.class).to(NovaInstanceConnector.class).in(Scopes.SINGLETON);
        ConfigurationModule.bindConfig(binder).to(NovaConfig.class);

        DiscoveryBinder.discoveryBinder(binder).bindHttpAnnouncement("cloudmanagement");
    }
}
