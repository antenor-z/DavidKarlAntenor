package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import Model.GameException;
import Model.GameState;
import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;

@SuppressWarnings("serial")
public class PlayBtnCtl extends JComponent implements ActionListener {
	private ActionListener _controller;
	ArrayList<JCheckBox> checkBoxes =  new ArrayList<JCheckBox>();
	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	HashMap<Model.PlayerColor, String> playersName = new HashMap<Model.PlayerColor, String>();
	
	public PlayBtnCtl(ActionListener controller, ArrayList<JCheckBox> checkBoxes, 
			ArrayList<JTextField> textFields) {
		_controller = controller;
		this.checkBoxes = checkBoxes;
		this.textFields = textFields;
	}

	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 6; i++) {
			if(checkBoxes.get(i).isSelected()){
				Model.PlayerColor pc = Model.PlayerColor.valueOf(checkBoxes.get(i).getText());
				String name = textFields.get(i).getText();
				playersName.put(pc, name);
			}
		}
		GameState gameState = GameState.getInstance();
		for(Map.Entry<Model.PlayerColor, String> player : playersName.entrySet())
		{
			try {
				gameState.addPlayer(player.getValue(), player.getKey());
			} catch (GameException e1) {
				e1.printStackTrace();
			}
		}
		_controller.actionPerformed(new ChangeViewEvent(this, 200, "", ViewType.GAME));
	}
}
