package nguyen.connor.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

public interface State {

	public void init();
	public void enter();
	public void tick(StateManager stateManager);
	public void render(Graphics2D brush);
	public void exit();
	public String getName();
	
}
