package resources.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ResourceReader {

    public static Object readViaSax(String path) {

        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            SaxHandler handler = new SaxHandler();
            saxParser.parse(path, handler);
            return handler.getObject();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
