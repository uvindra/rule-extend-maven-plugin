package com.wso2.build.interfaces.containers;

import com.wso2.build.interfaces.rules.BuildRule;
import com.wso2.build.interfaces.rules.ProjectRule;

/**
 * Created by uvindra on 2/1/14.
 */
public interface RuleContainer {
    BuildRule getBuildRule(String buildRuleHint);
    ProjectRule getProjectRule(String mvnRuleHint);
}
