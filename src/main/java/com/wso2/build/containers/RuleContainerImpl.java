package com.wso2.build.containers;

import com.wso2.build.interfaces.rules.BuildRule;
import com.wso2.build.interfaces.containers.RuleContainer;
import com.wso2.build.interfaces.rules.ProjectRule;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import java.util.Map;

/**
 * Created by uvindra on 2/1/14.
 */
@Component( role = RuleContainer.class )
public class RuleContainerImpl implements RuleContainer{
    /**
     * Inject all implementations of BuildRule.  The container automatically add all the components defined with the role <code>BuildRule.class</code>.
     */
    @Requirement( role = BuildRule.class )
    private Map<String, BuildRule> buildRules;

    /**
     * Inject all implementations of ProjectRule.  The container automatically add all the components defined with the role <code>ProjectRule.class</code>.
     */
    @Requirement( role = ProjectRule.class )
    private Map<String, ProjectRule> mvnRules;



    @Override
    public BuildRule getBuildRule(String buildRuleHint) {
        return buildRules.get(buildRuleHint);
    }

    @Override
    public ProjectRule getProjectRule(String mvnRuleHint) {
        return mvnRules.get(mvnRuleHint);
    }
}
