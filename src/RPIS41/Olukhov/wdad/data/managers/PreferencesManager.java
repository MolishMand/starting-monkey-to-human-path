package RPIS41.Olukhov.wdad.data.managers;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.omg.CORBA.MARSHAL;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by molish on 08.10.2016.
 */
public class PreferencesManager {
    private static PreferencesManager instance;
    private static String sourcePath = "src\\RPIS41\\Olukhov\\wdad\\resources\\configuration\\appconfig.xml";
    private Document appconfig;

    private PreferencesManager() throws ParserConfigurationException, SAXException, IOException{
        File xmlFile = new File(sourcePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        appconfig = documentBuilder.parse(xmlFile);
        appconfig.getDocumentElement().normalize();
    }

    public static PreferencesManager getInstance() throws ParserConfigurationException, SAXException, IOException{
        if(instance == null)
            instance = new PreferencesManager();
        return instance;
    }

    public boolean isCreateRegistry(){
        //TODO FUCK OFF BILLY!
        if(appconfig.getElementsByTagName("createregistry").item(0).getTextContent().equals("yes"))
            return true;
        else return false;
    }
    public void setCreateRegistry(boolean createRegistry){
        String createRegValue;
        if(createRegistry)
            createRegValue = "yes";
        else createRegValue = "no";
        appconfig.getElementsByTagName("createregistry").item(0).setTextContent(createRegValue);
        saveChanges();
    }

    public String getRegistryAddress(){
        return appconfig.getElementsByTagName("registryaddress").item(0).getTextContent();
    }
    public boolean setRegistryAddress(String registryAddress){
        Pattern patternURL = Pattern.compile("^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?$");
        Matcher matcherURL = patternURL.matcher(registryAddress);
        Pattern patternIP = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher matcherIP = patternIP.matcher(registryAddress);
        if(matcherIP.find() || matcherURL.find() || registryAddress.equals("localhost")) {
            appconfig.getElementsByTagName("registryaddress").item(0).setTextContent(registryAddress);
            saveChanges();
            return true;
        }else return false;
    }

    public int getRegistryPort(){
        return Integer.parseInt(appconfig.getElementsByTagName("registryport").item(0).getTextContent());
    }
    public void setRegistryPort(int registryPort){
        appconfig.getElementsByTagName("registryport").item(0).setTextContent(Integer.toString(registryPort));
        saveChanges();
    }

    public String getPolicyPath(){
        return appconfig.getElementsByTagName("policypath").item(0).getTextContent();
    }
    public boolean setPolicyPath(String policyPath){
        Pattern pattern = Pattern.compile("(^(.+):(\\\\.*))?(\\w*)\\.(policy)$");
        Matcher matcher = pattern.matcher(policyPath);
        if (matcher.find()) {
            appconfig.getElementsByTagName("policypath").item(0).setTextContent(policyPath);
            saveChanges();
            return true;
        }
        else return false;
    }

    public boolean isUseCodeBaseOnly(){
        if(appconfig.getElementsByTagName("usecodebaseonly").item(0).getTextContent().equals("yes"))
            return true;
        else return false;
    }
    public void setUseCodeBaseOnly(boolean useCodeBaseOnly){
        String useCodeBaseOnlyValue;
        if(useCodeBaseOnly)
            useCodeBaseOnlyValue = "yes";
        else useCodeBaseOnlyValue = "no";
        appconfig.getElementsByTagName("usecodebaseonly").item(0).setTextContent(useCodeBaseOnlyValue);
        saveChanges();
    }

    public String getClassProvider(){
        return appconfig.getElementsByTagName("classprovider").item(0).getTextContent();
    }
    public boolean setClassProvider(String classProvider) {
        Pattern patternURL = Pattern.compile("^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?(\\w)*(\\.jar)$");
        Matcher matcherURL = patternURL.matcher(classProvider);
        Pattern patternPath = Pattern.compile("(^(.+):(\\\\.*))?(\\w*)\\.(jar)$");
        Matcher matcherPath = patternPath.matcher(classProvider);
        if (matcherURL.find() || matcherPath.find()) {
            appconfig.getElementsByTagName("classprovider").item(0).setTextContent(classProvider);
            saveChanges();
            return true;
        }
        else return false;
    }

    private void saveChanges() {
        try {
            DOMImplementationLS domImplementationLS =
                    (DOMImplementationLS) appconfig.getImplementation().getFeature("LS", "3.0");
            LSOutput lsOutput = domImplementationLS.createLSOutput();
            FileOutputStream outputStream = new FileOutputStream(sourcePath);
            lsOutput.setByteStream(outputStream);
            LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
            lsSerializer.write(appconfig, lsOutput);
            outputStream.close();
        }catch (IOException e){e.printStackTrace();}
    }
}
