package kr.co.giloh.fx.util;

import kr.co.giloh.persistence.file.AbstractDAO;
import kr.co.giloh.model.dao.Copyable;
import kr.co.giloh.persistence.DataTransferObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by co on 2017. 5. 13..
 */
public class JAXBTool {

	public static <T> void marshal(T obj, File xml) {
		try {
			JAXBContext.newInstance(obj.getClass()).createMarshaller().marshal(obj, xml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static <T> String marshal(T obj) {
		StringWriter writer = new StringWriter();
		try {

			JAXBContext.newInstance(obj.getClass()).createMarshaller().marshal(obj, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return writer.toString();
	}

	public static <T extends Copyable<T>> void unmarshal(T obj, File xml) {

		if (xml.exists()) {
			try {
				T oldObj = (T) JAXBContext.newInstance(obj.getClass()).createUnmarshaller().unmarshal(xml);
				obj.copy(oldObj);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}

	public static <T extends DataTransferObject<T>, D extends AbstractDAO<T>> void unmarshalDAO(D obj, File xml) {
		if (xml.exists()) {
			try {
				D oldObj = (D) JAXBContext.newInstance(obj.getClass()).createUnmarshaller().unmarshal(xml);
				obj.copy(oldObj.read());
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}

	public static <T extends DataTransferObject<T>, D extends AbstractDAO<T>> void unmarshalDAO(D obj, String mashaledText) {
		try {
			D oldObj = (D) JAXBContext.newInstance(obj.getClass()).createUnmarshaller().unmarshal(new StringReader(mashaledText));
			obj.copy(oldObj.read());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}


}
