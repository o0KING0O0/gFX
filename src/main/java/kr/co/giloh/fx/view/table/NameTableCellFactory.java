package kr.co.giloh.fx.view.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import kr.co.giloh.model.name.ImmutableName;

public class NameTableCellFactory<S, T extends ImmutableName> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	@Override
	public TableCell<S, T> call(TableColumn<S, T> param) {

		return new TableCell<S, T>() {
			@Override
			protected void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty) {
					setText(null);
					setGraphic(null);
				} else {
					setText(item.getName());
					setGraphic(null);
				}
			}
		};
	}
}
