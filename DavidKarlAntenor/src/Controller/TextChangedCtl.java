package Controller;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextChangedCtl implements DocumentListener {
	ArrayList<JCheckBox> checkBoxes =  new ArrayList<JCheckBox>();
	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	JButton btnNext;
	
	public TextChangedCtl (ArrayList<JCheckBox> checkBoxes, 
	ArrayList<JTextField> textFields, JButton btnNext)
	{
		this.checkBoxes = checkBoxes;
		this.textFields = textFields;
		this.btnNext = btnNext;
	}
	
	public void insertUpdate(DocumentEvent e) {
		MenuValidateTextCombo.validate(checkBoxes, textFields, btnNext);
	}

	public void removeUpdate(DocumentEvent e) {
		MenuValidateTextCombo.validate(checkBoxes, textFields, btnNext);
	}

	public void changedUpdate(DocumentEvent e) {
		MenuValidateTextCombo.validate(checkBoxes, textFields, btnNext);
	}
}
