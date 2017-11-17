package kr.co.giloh.fx.dao;


import com.google.gson.Gson;
import javafx.collections.ObservableList;
import kr.co.giloh.fx.util.JAXBTool;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicDAO<T extends DataTransferObject<T>> implements SimpleDAO<T> {


	@Override
	public boolean create(T itemToAdd) {
		boolean flag = read().add(itemToAdd.newInstance());
		rearrange();
		return flag;
	}

	public boolean create(List<T> itemToAddList) {
		boolean flag = read().addAll(itemToAddList);
		rearrange();
		return flag;
	}

	public boolean create(BasicDAO<T> dao) {
		return create(dao.read());
	}


	@Override
	public boolean put(T itemToAdd) {
		boolean flag = read().add(itemToAdd);
		rearrange();
		return flag;
	}


	public abstract ObservableList<T> read();

	@Override
	public T read(int index) {
		return read().get(index);
	}

	public int indexOf(T item) {
		return read().indexOf(item);
	}


	@Override
	public boolean update(int index, T newItem) {
		boolean flag = read().set(index, newItem.newInstance()) != null;

		rearrange();

		return flag;
	}

	@Override
	public boolean set(int index, T newItem) {
		boolean flag = read().set(index, newItem) != null;

		rearrange();

		return flag;
	}


	@Override
	public boolean delete(int index) {
		boolean flag = (read().remove(index) != null);
		rearrange();
		return flag;
	}

	public boolean delete(List<Integer> indexList) {
		boolean flag = false;
		indexList.sort((Integer o1, Integer o2) -> {
			int c = 0;
			if (o1 > o2) {
				c = -1;
			} else if (o1 < o2) {
				c = 1;
			} else {
				c = 0;
			}
			return c;
		});


		for (Integer index : indexList) {
			flag &= (read().remove(index.intValue()) != null);
		}

		rearrange();

		return flag;
	}

	@Override
	public boolean deleteAll() {
		return read().removeAll(read());
	}

	@Override
	public boolean moveUp(int index) {
		if (index < 1) {
			return false;
		}

		T itemToReplace = read().get(index - 1);
		T itemToMoveUp = read().set(index, itemToReplace);
		read().set(index - 1, itemToMoveUp);


		rearrange();

		return true;
	}

	@Override
	public boolean moveDown(int index) {
		if (index > (read().size() - 2)) {
			return false;
		}

		T itemToReplace = read().get(index + 1);
		T itemToMoveDown = read().set(index, itemToReplace);
		read().set(index + 1, itemToMoveDown);

		rearrange();

		return true;
	}

	/**
	 * @param objList
	 */
	public void copy(ObservableList<T> objList) {
		deleteAll();
		read().addAll(objList);
		rearrange();
	}

	/**
	 * @param obj
	 * @return
	 */
	public T find(T obj) {
		T ret = null;
		for (T t : read()) {
			if (t.compareTo(obj) == 0) {
				ret = t;
				break;
			}
		}

		return ret;
	}

	/**
	 * @param objList
	 */
	public void update(ObservableList<T> objList) {
		// if target obj list is not matched, delete

		ArrayList<Integer> delList = new ArrayList<>();
		skip:
		for (int i = 0; i < read().size(); i++) {
			T originObj = read(i);

			for (T targetObj : objList) {
				if (originObj.compareTo(targetObj) == 0) {
					originObj.copy(targetObj);
					continue skip;
				}
			}
			// if not mathed, will be skipped
			delList.add(i);
		}

		delete(delList);

		for (T targetObj : objList) {
			if (find(targetObj) == null) {
				create(targetObj);
			}
		}
	}

	/**
	 * marshal
	 */
	public String marshal() {
		return JAXBTool.marshal(this);
	}


	/**
	 * @param xml
	 */
	public void marshal(File xml) {
		JAXBTool.marshal(this, xml);
	}

	/**
	 * @return
	 */
	public String encodeJson() {
		Gson gson = FxGson.create();
		return gson.toJson(this, this.getClass());
	}

	/**
	 * @param jsonFile
	 * @throws IOException
	 */
	public void encodeJson(File jsonFile) throws IOException {
		FileWriter writer = new FileWriter(jsonFile);
		writer.write(encodeJson());
		writer.flush();
		writer.close();
	}


	/**
	 * @param xml
	 */
	public void unmarshal(File xml) {
		JAXBTool.unmarshalDAO(this, xml);
	}

	/**
	 * @param marshaledText
	 */
	public void unmarshal(String marshaledText) {
		JAXBTool.unmarshalDAO(this, marshaledText);
	}

	/**
	 * @param jsonText
	 */
	public void decodeJson(String jsonText) {
		Gson gson = FxGson.create();
		copy(gson.fromJson(jsonText, this.getClass()).read());
	}

	/**
	 * @param jsonFile
	 * @throws IOException
	 */
	public void decodeJson(File jsonFile) throws IOException {
		if (!jsonFile.exists()) {
			return;
		}
		FileReader reader = new FileReader(jsonFile);

		Gson gson = FxGson.create();
		copy(gson.fromJson(reader, this.getClass()).read());

//		int c;
//		StringBuffer buf = new StringBuffer();
//		while((c = reader.read()) != -1){
//			buf.append((char)c);
//		}
//
//		decodeJson(buf.toString());
	}


}
