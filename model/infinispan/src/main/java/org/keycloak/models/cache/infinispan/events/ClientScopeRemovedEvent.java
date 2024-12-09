/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.models.cache.infinispan.events;

import java.util.Set;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoTypeId;
import org.keycloak.marshalling.Marshalling;
import org.keycloak.models.cache.infinispan.RealmCacheManager;

@ProtoTypeId(Marshalling.CLIENT_SCOPE_REMOVED_EVENT)
public class ClientScopeRemovedEvent extends BaseClientScopeEvent {

    @ProtoFactory
    ClientScopeRemovedEvent(String id, String realmId) {
        super(id, realmId);
    }

    public static ClientScopeRemovedEvent create(String clientScopeId, String realmId) {
        return new ClientScopeRemovedEvent(clientScopeId, realmId);
    }

    @Override
    public void addInvalidations(RealmCacheManager realmCache, Set<String> invalidations) {
        realmCache.clientScopeRemoval(realmId, invalidations);
    }
}
