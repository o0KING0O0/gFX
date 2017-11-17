package kr.co.giloh.fx;

import javafx.beans.property.BooleanProperty;

public interface Visibilitable {
	public BooleanProperty visibleProperty();

	public boolean isVisible();

	public void setVisible(boolean visible);
}
