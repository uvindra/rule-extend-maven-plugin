package com.wso2.build.utils;

import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by uvindra on 2/22/14.
 */
public class Utility {

    private static final String mvnBundlePluginName = "maven-bundle-plugin";
    private static final String instructionsTag = "instructions";

    public static List<String> getBundlePluginInstructionValues(MavenProject mavenProject, String searchInstruction) {
        List<Plugin> plugins = mavenProject.getBuildPlugins();

        List<String> instructionValues = new LinkedList<String>();

        for (Plugin plugin : plugins) {
            if (true != plugin.getArtifactId().equals(mvnBundlePluginName)) {
                continue;
            }

            Xpp3Dom config = (Xpp3Dom) plugin.getConfiguration();

            Xpp3Dom[] instructions = config.getChildren(instructionsTag);

            for (Xpp3Dom instruction : instructions) {
                Xpp3Dom[] matchingInstructions = instruction.getChildren(searchInstruction);

                for (Xpp3Dom matchingInstruction : matchingInstructions) {
                    String exportPackageValue = matchingInstruction.getValue();

                    instructionValues.add(exportPackageValue);
                }
            }
        }

        return instructionValues;
    }

    public static boolean isBundlePluginInstructionExist(MavenProject mavenProject, String searchInstruction) {
        List<Plugin> plugins = mavenProject.getBuildPlugins();

        for (Plugin plugin : plugins) {
            if (true != plugin.getArtifactId().equals(mvnBundlePluginName)) {
                continue;
            }

            Xpp3Dom config = (Xpp3Dom) plugin.getConfiguration();

            Xpp3Dom[] instructions = config.getChildren(instructionsTag);

            for (Xpp3Dom instruction : instructions) {
                Xpp3Dom[] matchingInstructions = instruction.getChildren(searchInstruction);

                if (0 < matchingInstructions.length) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean isElementSpecified(MavenProject mavenProject, String element) {
        Model model = mavenProject.getModel();

        File pomFile = model.getPomFile();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(pomFile);

            NodeList nodeList = doc.getElementsByTagName(element);

            return (0 < nodeList.getLength());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static boolean hasChildParentElement(MavenProject mavenProject, String childElement, String parentElement) {
        Model model = mavenProject.getModel();

        File pomFile = model.getPomFile();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(pomFile);

            NodeList childNodes = doc.getElementsByTagName(childElement);

            for (int i = 0; i < childNodes.getLength(); ++i) {

                Node node = childNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Node parentNode = node.getParentNode();

                    // The child elements parent is not the specified parent
                    if (false == parentElement.equals(parentNode.getNodeName())) {
                        return false;
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


    public static boolean hasParentChildElement(MavenProject mavenProject, String parentElement, String childElement) {
        Model model = mavenProject.getModel();

        File pomFile = model.getPomFile();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(pomFile);

            NodeList parentNodes = doc.getElementsByTagName(parentElement);

            for (int i = 0; i < parentNodes.getLength(); ++i) {

                Node parentNode = parentNodes.item(i);

                if (parentNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                NodeList childNodes = parentNode.getChildNodes();

                boolean isChildPresent = false;

                for (int j = 0; j < childNodes.getLength(); ++j) {
                    Node childNode = childNodes.item(j);

                    if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    if (true == childElement.equals(childNode.getNodeName())) {
                        isChildPresent = true;
                    }
                }

                if (false == isChildPresent) { // Specified child not present in parent element
                    return false;
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }
}
