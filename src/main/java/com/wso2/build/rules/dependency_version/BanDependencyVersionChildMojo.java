package com.wso2.build.rules.dependency_version;

import com.wso2.build.utils.Utility;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;


/**
 * Created by uvindra on 2/22/14.
 */

/**
 *
 * @goal dependency_version_ban_child
 */
public class BanDependencyVersionChildMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String versionTag = "version";
    private static final String dependencyTag = "dependency";

    private static final String groupIdTag = "groupId";
    private static final String carbonGroup = "org.wso2.carbon";

    @Override
    public void execute() throws MojoExecutionException {
        MavenProject parentProject = mavenProject.getParent();

        if (null == parentProject) {  // Ignore parent projects
            return;
        }

        List<NodeList> nodeLists = Utility.getChildrenOfParent(mavenProject, dependencyTag);

        for (NodeList nodeList : nodeLists) {

            boolean isExclude = false;
            boolean isVersionPresent = false;

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);

                // Carbon group dependencies should be excluded from this check
                if (true == groupIdTag.equals(node.getNodeName()) && true == carbonGroup.equals(node.getNodeValue())) {
                    isExclude = true;
                }

                if (true == versionTag.equals(node.getNodeName())) {
                    isVersionPresent = true;
                }
            }

            if (false == isExclude && true == isVersionPresent) {
                throw new MojoExecutionException("Child pom contains version tag in dependency section");
            }
        }
    }
}
