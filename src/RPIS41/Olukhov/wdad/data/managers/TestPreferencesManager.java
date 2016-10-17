package RPIS41.Olukhov.wdad.data.managers;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by molish on 09.10.2016.
 */
public class TestPreferencesManager {
    public static void test(){
        try {
            PreferencesManager preferencesManager = PreferencesManager.getInstance();

            System.out.println(preferencesManager.getCreateRegistry());
            System.out.println(preferencesManager.isUseCodeBaseOnly());
            System.out.println(preferencesManager.getClassProvider());
            System.out.println(preferencesManager.getPolicyPath());
            System.out.println(preferencesManager.getRegistryAddress());
            System.out.println(preferencesManager.getRegistryPort());

            preferencesManager.setPolicyPath("efe\\ewfr\\wf\\checkSum.policy");
            preferencesManager.setClassProvider("C:\\edw\\wed\\wed\\client.jar");
            preferencesManager.setRegistryAddress("233.45.123.243");

            /*preferencesManager.setPolicyPath("client.policy");
            preferencesManager.setRegistryAddress("localhost");
            preferencesManager.setClassProvider("http://www.yourhost.free.ru/cp/cp.jar");*/

        }catch (IOException | ParserConfigurationException | SAXException e){e.printStackTrace();}
    }
}
