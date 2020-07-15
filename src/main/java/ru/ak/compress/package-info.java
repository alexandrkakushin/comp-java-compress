@XmlSchema(attributeFormDefault = XmlNsForm.QUALIFIED,
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace = "http://compress.ak.ru/",
        xmlns = {
                @XmlNs(namespaceURI = "http://compress.ak.ru/", prefix = ""),
                @XmlNs(namespaceURI = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"),
                @XmlNs(namespaceURI = "http://www.w3.org/2001/XMLSchema", prefix = "xs")})
package ru.ak.compress;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;