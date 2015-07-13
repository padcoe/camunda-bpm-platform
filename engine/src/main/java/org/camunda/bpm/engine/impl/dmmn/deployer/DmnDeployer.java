/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.bpm.engine.impl.dmmn.deployer;

import org.camunda.bpm.engine.impl.AbstractDefinitionDeployer;
import org.camunda.bpm.engine.impl.dmmn.entity.repository.DecisionDefinitionEntity;
import org.camunda.bpm.engine.impl.dmmn.entity.repository.DecisionDefinitionManager;
import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;
import org.camunda.bpm.engine.impl.persistence.deploy.DeploymentCache;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ResourceEntity;

import java.util.Collections;
import java.util.List;

/**
 * {@link Deployer} responsible to parse CMMN 1.0 XML files and create the
 * proper {@link DecisionDefinitionEntity}s.
 *
 */
public class DmnDeployer extends AbstractDefinitionDeployer<DecisionDefinitionEntity> {

  public static final String[] DMN_RESOURCE_SUFFIXES = new String[] { "dmn10.xml", "dmn" };

  protected String[] getResourcesSuffixes() {
    return DMN_RESOURCE_SUFFIXES;
  }

  protected List<DecisionDefinitionEntity> transformDefinitions(DeploymentEntity deployment, ResourceEntity resource) {
    return Collections.emptyList();
  }

  protected DecisionDefinitionEntity findDefinitionByDeploymentAndKey(String deploymentId, String definitionKey) {
    return getDecisionDefinitionManager().findDecisionDefinitionByDeploymentAndKey(deploymentId, definitionKey);
  }

  protected DecisionDefinitionEntity findLatestDefinitionByKey(String definitionKey) {
    return getDecisionDefinitionManager().findLatestDecisionDefinitionByKey(definitionKey);
  }

  protected void persistDefinition(DecisionDefinitionEntity decisionDefinition) {
    getDecisionDefinitionManager().insertDecisionDefinition(decisionDefinition);
  }

  protected void addDefinitionToDeploymentCache(DeploymentCache deploymentCache, DecisionDefinitionEntity decisionDefinition) {
    deploymentCache.addDecisionDefinition(decisionDefinition);
  }

  // context ///////////////////////////////////////////////////////////////////////////////////////////

  protected DecisionDefinitionManager getDecisionDefinitionManager() {
    return getCommandContext().getDecisionDefinitionManager();
  }

  // getters/setters ///////////////////////////////////////////////////////////////////////////////////

}
