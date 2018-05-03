package kr.co.giloh.fx.view.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import kr.co.giloh.model.name.ImmutableName;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


public class BasicListTableCellFactory<S, T extends List<? extends ImmutableName>> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@Override
	public TableCell<S, T> call(TableColumn<S, T> param) {
		return new TableCell<S, T>() {
			@Override
			protected void updateItem(T itemList, boolean empty) {
				super.updateItem(itemList, empty);

				if (itemList == null || empty) {
					setText(null);
				} else {
					setText(StringUtils.join(itemList.stream().map(item -> item.getName()).collect(Collectors.toList()), ','));
				}
			}
		};
	}
}
