package resources.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import reflection.ReflectionHelper;

public class SaxHandler extends DefaultHandler {

    private static final String CLASSNAME = "class";
    private String element;
    private Object object;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (!qName.equalsIgnoreCase(CLASSNAME)) {
            element = qName;
        } else {
            String className = attributes.getValue(0);
            //System.out.println("class name: " + className);
            object = ReflectionHelper.createInstance(className);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        element = null;

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (element == null) {
            return;
        }

        String value = new String(ch, start, length);
        System.out.println(element + ":" + value);
        ReflectionHelper.setFieldValue(object, element, value);

    }

    public Object getObject() {
        return object;
    }
}
