package RPIS41.Olukhov.wdad.data.managers;

import RPIS41.Olukhov.wdad.utils.PreferencesConstantManager;
import jdk.nashorn.internal.runtime.PropertyHashMap;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by molish on 09.10.2016.
 */
public class TestPreferencesManager {
    public static void test(){
        try {
            PreferencesManager preferencesManager = PreferencesManager.getInstance();

            System.out.println(preferencesManager.getProperty(PreferencesConstantManager.POLICY_PATH));
            Properties pro = preferencesManager.getProperties();
            pro.list(System.out);
            //preferencesManager.addBindedObject("keke", "kek");
            //preferencesManager.removeBindedObject("keke");
        }catch (IOException | ParserConfigurationException | SAXException e){e.printStackTrace();}
    }
}
