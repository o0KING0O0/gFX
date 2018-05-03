package kr.co.giloh.fx.view.table;

import javafx.beans.NamedArg;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.text.DecimalFormat;


public class FormattedTableCellFactory<S, T extends Double> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	private final String property;
	private final String postfix;

	public FormattedTableCellFactory(@NamedArg("pattern") String property, @NamedArg("postfix") String postfix) {
		this.property = property;
		this.postfix = postfix;
	}

	@Override
	public TableCell<S, T> call(TableColumn<S, T> p) {
		TableCell<S, T> cell = new TableCell<S, T>() {
			@Override
			protected void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);
				//Set the CSS style on the cell and set the cell's text.

				if (item == null || empty) {
					setText(null);
					setGraphic(null);
				} else {
					Label lb_unit = new Label(" " + postfix);
					lb_unit.setFont(Font.font(11));
					Label lb_value = new Label(new DecimalFormat(property).format(item.doubleValue()));
					lb_value.setTextFill(Color.MAROON);


					HBox hbox = new HBox(lb_value, lb_unit
					);
					hbox.setAlignment(Pos.BOTTOM_RIGHT);

					setGraphic(hbox);

//					setText(new DecimalFormat(property).format(item));
					setText(null);
				}
			}
		};
		return cell;
	}
}
