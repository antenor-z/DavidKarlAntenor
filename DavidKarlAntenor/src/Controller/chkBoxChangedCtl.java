package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class chkBoxChangedCtl implements ItemListener{
	ArrayList<JCheckBox> checkBoxes =  new ArrayList<JCheckBox>();
	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	JButton btnNext;
	
	public chkBoxChangedCtl (ArrayList<JCheckBox> checkBoxes, 
	ArrayList<JTextField> textFields, JButton btnNext)
	{
		this.checkBoxes = checkBoxes;
		this.textFields = textFields;
		this.btnNext = btnNext;
	}
	public void itemStateChanged(ItemEvent e) {
		MenuValidateTextCombo.validate(checkBoxes, textFields, btnNext);
	}
	
}
