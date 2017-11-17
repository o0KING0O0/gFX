package kr.co.giloh.fx.ctrl;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

/**
 * Created by giloh on 2017. 4. 21..
 */
public class FXControllerGen {
	public static <C extends StageContainable> C genCtrl(URL fxml) {
		C ctrl = null;
		FXMLLoader loader = new FXMLLoader(fxml);
		try {
			Parent root = loader.load();
			final Scene scene = new Scene(root);
			final Stage stage = new Stage();
			stage.sizeToScene();
			stage.setScene(scene);
			ctrl = (C) loader.getController();
			ctrl.setStage(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ctrl;
	}

	/**
	 * @param fxml
	 * @param owner
	 * @param <C>
	 * @return
	 */
	public static <C extends StageContainable> C genModalCtrl(URL fxml, Window owner) {
		final C ctrl = genCtrl(fxml);

		ctrl.getStage().initModality(Modality.WINDOW_MODAL);
		ctrl.getStage().initOwner(owner);


		ctrl.getStage().setOnShowing((event) -> {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ctrl.getStage().setX(owner.getX() + owner.getWidth() / 2 - ctrl.getStage().getWidth() / 2); //dialog.getWidth() = NaN
					ctrl.getStage().setY(owner.getY() + owner.getHeight() / 2 - ctrl.getStage().getHeight() / 2); //dialog.getHeight() = NaN
				}
			});
		});


		return ctrl;
	}


//	public static <C extends StageContainable> C genModalCtrl(URL fxml, C ownerCtrl){
//		final C ctrl = genCtrl(fxml);
//
//		ctrl.getStage().initModality(Modality.WINDOW_MODAL);
//		ctrl.getStage().initOwner(ownerCtrl.getStage().getScene().getWindow());
//
//		return ctrl;
//	}

}
