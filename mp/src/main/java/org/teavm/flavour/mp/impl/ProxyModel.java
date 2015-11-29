/*
 *  Copyright 2015 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.flavour.mp.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.teavm.dependency.DependencyAgent;
import org.teavm.dependency.DependencyNode;
import org.teavm.model.MethodReference;

/**
 *
 * @author Alexey Andreev
 */
public class ProxyModel {
    private MethodReference method;
    private MethodReference proxyMethod;
    private List<ProxyParameter> parameters;
    private List<ProxyUsage> usages = new ArrayList<>();
    private List<DependencyNode> nodes;

    ProxyModel(MethodReference method, MethodReference proxyMethod, List<ProxyParameter> parameters,
            DependencyAgent agent) {
        this.method = method;
        this.proxyMethod = proxyMethod;
        this.parameters = Collections.unmodifiableList(new ArrayList<>(parameters));
        List<DependencyNode> nodes = new ArrayList<>();
        for (int i = 0; i < parameters.size(); ++i) {
            if (parameters.get(i).getKind() == ParameterKind.REFLECT_VALUE) {
                nodes.add(agent.createNode());
            } else {
                nodes.add(null);
            }
        }
        this.nodes = Collections.unmodifiableList(nodes);
    }

    public MethodReference getMethod() {
        return method;
    }

    public MethodReference getProxyMethod() {
        return proxyMethod;
    }

    public List<ProxyParameter> getParameters() {
        return parameters;
    }

    public List<ProxyUsage> getUsages() {
        return usages;
    }

    public List<DependencyNode> getNodes() {
        return nodes;
    }
}
