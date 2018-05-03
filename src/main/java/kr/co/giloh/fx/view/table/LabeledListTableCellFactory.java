package kr.co.giloh.fx.view.table;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import kr.co.giloh.Colorable;
import kr.co.giloh.model.name.ImmutableName;

import java.util.List;

public class LabeledListTableCellFactory<S, T extends List<E>, E extends ImmutableName & Colorable> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	@Override
	public TableCell<S, T> call(TableColumn<S, T> param) {
		return new TableCell<S, T>() {
			@Override
			protected void updateItem(T itemList, boolean empty) {
				super.updateItem(itemList, empty);

				if (itemList == null || empty) {
					setText(null);
					setGraphic(null);
				} else {
					setText(null);
					if (itemList.size() == 0) {
						setGraphic(null);
						return;
					}

					HBox hbox = new HBox();
					hbox.setAlignment(Pos.BOTTOM_CENTER);

					for (int i = 0; i < itemList.size() - 1; i++) {
						String itemName = itemList.get(i).getName();
						Label lb_item = new Label();
						lb_item.setText(itemName);
						lb_item.setFont(Font.font(11));
						lb_item.setTextFill(itemList.get(i).getColor());


						Label lb_comma = new Label(", ");
						hbox.getChildren().addAll(lb_item, lb_comma);
					}

					Label lb_lastItem = new Label(itemList.get(itemList.size() - 1).getName());
					lb_lastItem.setFont(Font.font(11));
					lb_lastItem.setTextFill(itemList.get(itemList.size() - 1).getColor());


					hbox.getChildren().add(lb_lastItem);

					setGraphic(hbox);
				}
			}
		};
	}
}
