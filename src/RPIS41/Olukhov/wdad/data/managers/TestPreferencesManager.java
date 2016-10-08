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

            System.out.println(preferencesManager.isCreateRegistry());
            System.out.println(preferencesManager.isUseCodeBaseOnly());
            System.out.println(preferencesManager.getClassProvider());
            System.out.println(preferencesManager.getPolicyPath());
            System.out.println(preferencesManager.getRegistryAddress());
            System.out.println(preferencesManager.getRegistryPort());

            System.out.println(preferencesManager.setPolicyPath("checkSum"));//false
            System.out.println(preferencesManager.setPolicyPath("efe\\ewfr\\wf\\checkSum.policy"));//true
            System.out.println(preferencesManager.setPolicyPath("D:\\wew\\wedw\\wedw\\checkSum.policy"));//true
            System.out.println(preferencesManager.setPolicyPath("C://wce/cew/wce/wce/checkSum.policy"));//true

            System.out.println(preferencesManager.setClassProvider("C://wce/cew/wce/wce/checkSum"));//false
            System.out.println(preferencesManager.setClassProvider("C:\\edw\\wed\\wed\\client.jar"));//true
            System.out.println(preferencesManager.setClassProvider("http://erfe/eve/efr/erfclient"));//false
            System.out.println(preferencesManager.setClassProvider("https://werfewrf.wefr.efr/werferf/ewferf.jar"));//true

            System.out.println(preferencesManager.setRegistryAddress("233.45.123.243"));//true
            System.out.println(preferencesManager.setRegistryAddress("localhost"));//true
            System.out.println(preferencesManager.setRegistryAddress("jecer.dfsgdf.sfdgwe.wdew"));//true
            System.out.println(preferencesManager.setRegistryAddress("325.453.23.132"));//false
            System.out.println(preferencesManager.setRegistryAddress("http://wercc.cer.cercer"));//true

            /*System.out.println(preferencesManager.setPolicyPath("client.policy"));
            System.out.println(preferencesManager.setRegistryAddress("localhost"));
            System.out.println(preferencesManager.setClassProvider("http://www.yourhost.free.ru/cp/cp.jar"));*/

        }catch (IOException | ParserConfigurationException | SAXException e){e.printStackTrace();}
    }
}
