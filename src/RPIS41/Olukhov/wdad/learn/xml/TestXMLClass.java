package RPIS41.Olukhov.wdad.learn.xml;

import org.xml.sax.SAXException;

import javax.security.sasl.SaslException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by molish on 08.10.2016.
 */
public class TestXMLClass {
    public static void test(){
        Calendar calendarEarnings = Calendar.getInstance();
        calendarEarnings.set(2016, Calendar.DECEMBER, 28);//дата для проверки прибыли за день
        Calendar calendarRemove = Calendar.getInstance();
        calendarRemove.set(2015, Calendar.APRIL, 25);//дата для удаления


        try {
            XmlTask testClass = new XmlTask("src\\RPIS41\\Olukhov\\wdad\\learn\\xml\\testRightFirst");
            System.out.println("Прибыль Сидорова за 8 января 2016 года: " + testClass.earningsTotal("sidorov", calendarEarnings));
            testClass.removeDay(calendarRemove);
            testClass.changeOfficiantName("alexander", "petrov", "sashka", "ivanov");
            System.out.println(testClass.getLuckyGuy(calendarEarnings));
        }catch (ParserConfigurationException | SAXException | IOException e){e.printStackTrace();}
    }
}
