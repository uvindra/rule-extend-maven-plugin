package com.wso2.build.rules.require_bundle;

import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.util.xml.Xpp3Dom;

import java.util.List;

/**
 * Created by uvindra on 2/19/14.
 */
@Component(role = ProjectRule.class, hint = "require_bundle")
public class RequireBundleRule implements ProjectRule {

    private static final String mvnBundlePluginName = "maven-bundle-plugin";
    private static final String instructionsTag = "instructions";
    private static final String requireBundleTag = "Require-Bundle";

    @Override
    public boolean validate(MavenProject mvnProject, MavenSession mvnSession, Settings settings) {
        List<Plugin> plugins = mvnProject.getBuildPlugins();

        for (Plugin plugin : plugins) {
            if (true == plugin.getArtifactId().equals(mvnBundlePluginName)) {
                Xpp3Dom config = (Xpp3Dom) plugin.getConfiguration();

                Xpp3Dom[] instructions = config.getChildren(instructionsTag);

                for (Xpp3Dom instruction : instructions) {
                    Xpp3Dom[] requireBundles = instruction.getChildren(requireBundleTag);

                    if (0 < requireBundles.length) {
                        System.out.println("Require-Bundle header specified");
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
