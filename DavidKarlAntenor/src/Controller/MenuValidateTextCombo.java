package Controller;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

abstract public class MenuValidateTextCombo {
	static void validate(ArrayList<JCheckBox> checkBoxes, ArrayList<JTextField> textFields,
			JButton btnNext) {
		int count = 0;
		boolean textFieldsSizeOK = true;
		boolean textFieldsAllUnique = true;
		
		for(int i = 0; i < 6; i++) {
			if(checkBoxes.get(i).isSelected()){
				count++;
				textFields.get(i).setEnabled(true);
				String text = textFields.get(i).getText();
				
				if(text.length() == 0 || text.length() > 8) {
					textFieldsSizeOK = false;
				}
				else {
					for(int j = 0; j < 6; j++) {
						String text2 = textFields.get(j).getText();
						if(text2.equals(text) && i != j) {
							textFieldsAllUnique = false;	
							break;
						}
					}
				}
			}
			else {
				textFields.get(i).setEnabled(false);
			}
			if(count >= 3 && textFieldsSizeOK && textFieldsAllUnique)
				btnNext.setEnabled(true);
			else
				btnNext.setEnabled(false);
		}
	}
}
