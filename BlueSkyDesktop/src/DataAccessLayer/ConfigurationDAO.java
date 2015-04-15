/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 - 110001NP Development Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */
package DataAccessLayer;

import Core.Config;
import Core.Provider;
import DataTransferObject.Connection;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigurationDAO {
    private File file;
    
    public ConfigurationDAO() {
        this.file = null;
    }
    
    public ConfigurationDAO(File file) {
        this.file = file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public boolean isConnected(Connection connection) throws Exception {
        Provider provider = new Provider();
        
        return provider.isConnected(connection);
    }
    
    public void readConfiguration() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(this.file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("configuration");
        
        Node node = nodeList.item(0);
        
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            
            NodeList hostElementList = element.getElementsByTagName("host");
            Element hostElement = (Element) hostElementList.item(0);
            NodeList host = hostElement.getChildNodes();
            Config.host = ((Node) host.item(0)).getNodeValue();
            
            NodeList portElementList = element.getElementsByTagName("port");
            Element portElement = (Element) portElementList.item(0);
            NodeList port = portElement.getChildNodes();
            Config.port = ((Node) port.item(0)).getNodeValue();
            
            NodeList databaseElementList = element.getElementsByTagName("database");
            Element databaseElement = (Element) databaseElementList.item(0);
            NodeList database = databaseElement.getChildNodes();
            Config.database = ((Node) database.item(0)).getNodeValue();
            
            NodeList usernameElementList = element.getElementsByTagName("username");
            Element usernameElement = (Element) usernameElementList.item(0);
            NodeList username = usernameElement.getChildNodes();
            Config.username = ((Node) username.item(0)).getNodeValue();
            
            NodeList passwordElementList = element.getElementsByTagName("password");
            Element passwordElement = (Element) passwordElementList.item(0);
            NodeList password = passwordElement.getChildNodes();
            Config.password = ((Node) password.item(0)).getNodeValue();
            
            NodeList languageElementList = element.getElementsByTagName("language");
            Element languageElement = (Element) languageElementList.item(0);
            NodeList language = languageElement.getChildNodes();
            Config.language = ((Node) language.item(0)).getNodeValue();
            
            NodeList themeElementList = element.getElementsByTagName("theme");
            Element themeElement = (Element) themeElementList.item(0);
            NodeList theme = themeElement.getChildNodes();
            Config.theme = ((Node) theme.item(0)).getNodeValue();
        }
    }
    
    public void writeConfigurationToFile(Connection connection)
            throws ParserConfigurationException, TransformerException {
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("bluesky");
        doc.appendChild(rootElement);
        
        Element configuration = doc.createElement("configuration");
        rootElement.appendChild(configuration);
        
        Element hostElement = doc.createElement("host");
        hostElement.appendChild(doc.createTextNode(connection.getHost()));
        configuration.appendChild(hostElement);
        
        Element portElement = doc.createElement("port");
        portElement.appendChild(doc.createTextNode(connection.getPort()));
        configuration.appendChild(portElement);
        
        Element databaseElement = doc.createElement("database");
        databaseElement.appendChild(doc.createTextNode(connection.getDatabase()));
        configuration.appendChild(databaseElement);
        
        Element usernameElement = doc.createElement("username");
        usernameElement.appendChild(doc.createTextNode(connection.getUsername()));
        configuration.appendChild(usernameElement);
        
        Element passwordElement = doc.createElement("password");
        passwordElement.appendChild(doc.createTextNode(connection.getPassword()));
        configuration.appendChild(passwordElement);
        
        Element languageElement = doc.createElement("language");
        languageElement.appendChild(doc.createTextNode(connection.getLanguage()));
        configuration.appendChild(languageElement);
        
        Element themeElement = doc.createElement("theme");
        themeElement.appendChild(doc.createTextNode(connection.getTheme()));
        configuration.appendChild(themeElement);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("../config.xml"));
        transformer.transform(source, result);
    }
}
