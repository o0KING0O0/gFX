package kr.co.giloh.model.dao.database;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import kr.co.giloh.persistence.DataTransferObject;
import kr.co.giloh.persistence.file.SimpleDAO;
import kr.co.giloh.persistence.db.annotation.DBColumn;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface SimpleDBDAO<T extends DataTransferObject<T>> extends SimpleDAO<T> {


	default Map<String, String> getDBColumnMap(T dto) throws IllegalAccessException {
		HashMap<String, String> dbColMap = new HashMap<>();

		for (Field field : dto.getClass().getDeclaredFields()) {
			if (field.getDeclaredAnnotation(DBColumn.class) != null) {
				String name = field.getDeclaredAnnotation(DBColumn.class).name();

				//https://stackoverflow.com/questions/13400075/reflection-generic-get-field-value
				field.setAccessible(true);
				Object value = field.get(dto);

				String strValue = "";
				if (value instanceof ObjectProperty
						|| value instanceof IntegerProperty
						|| value instanceof DoubleProperty
						|| value instanceof BooleanProperty
						) {
					
				}

				if (name.equals("##default")) {
					dbColMap.put(field.getName(), value.toString());
				} else {
					dbColMap.put(name, value.toString());
				}

			}

		}

//		Field field = object.getClass().getDeclaredField(fieldName);
//		field.setAccessible(true);
//		Object value = field.get(object);

		return dbColMap;
	}
}
