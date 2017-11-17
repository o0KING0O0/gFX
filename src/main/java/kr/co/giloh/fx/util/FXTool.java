package kr.co.giloh.fx.util;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

/**
 * Created by giloh on 2017. 6. 6..
 */
public class FXTool {
	public static void applyButtonEffect(Node node) {
		node.setOnMousePressed((MouseEvent ev) -> {
			node.setEffect(new DropShadow());
		});
		node.setOnMouseReleased((MouseEvent ev) -> {
			node.setEffect(null);
		});
	}
}
