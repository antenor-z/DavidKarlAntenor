package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.GameException;
import Model.GameState;
import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;

@SuppressWarnings("serial")
public class OpenBtnCtl extends JComponent implements ActionListener {
	private ActionListener _controller;
	HashMap<Model.PlayerColor, String> playersName = new HashMap<Model.PlayerColor, String>();
	public OpenBtnCtl(ActionListener controller) {
		_controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		GameState gameState = GameState.getInstance();
		for(Map.Entry<Model.PlayerColor, String> player : playersName.entrySet())
		{
			try {
				gameState.addPlayer(player.getValue(), player.getKey());
			} catch (GameException e1) {
				e1.printStackTrace();
			}
		}
		JFileChooser jFileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON file", "json");
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.addChoosableFileFilter(filter);
		int option = jFileChooser.showSaveDialog(null);
		if(option == JFileChooser.APPROVE_OPTION)
		{
			String selectedFile = jFileChooser.getSelectedFile().getAbsolutePath();
			gameState.openGame(selectedFile);
			_controller.actionPerformed(new ChangeViewEvent(this, 200, "", ViewType.GAME));
		}
	}
}
