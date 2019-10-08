import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener{

	private JButton[] bttnBrowser = new JButton[2];
	private JButton donebttn= new JButton("Done");
	private JTextField[] textField= new JTextField[2];
	private JLabel[] titleLabel = new JLabel[2];
	private JLabel[] messageLabel= new JLabel[4];
	private JRadioButton[] rationbttn= new JRadioButton[2];
	private Sort sortClassObject;
	public GUI(){
		this.setTitle("File Manager");
		this.setSize(700,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.cyan);
		this.setLayout(null);
		this.declear();
		this.setOnPanel();

	}

	private void setOnPanel(){
		titleLabel[0].setText("Quick Files Manipulater");

		donebttn.setBounds(400,390 , 100, 30);
		titleLabel[0].setBounds(250,25,200,40);
		bttnBrowser[0].setBounds(450, 320, 100, 20);
		bttnBrowser[1].setBounds(450, 350, 100, 20);
		textField[0].setBounds(150, 320, 290, 20);
		textField[1].setBounds(150, 350, 290, 20);
		messageLabel[0].setText("From:");
		messageLabel[1].setText("To:");
		messageLabel[2].setText("Copy");
		messageLabel[3].setText("Move");

		messageLabel[0].setBounds(100,315,40,30);
		messageLabel[1].setBounds(100,345,40,30);
		messageLabel[2].setBounds(168,391,40,15);
		messageLabel[3].setBounds(258,391,40,15);


		rationbttn[0].setBounds(200,390,20,20);
		rationbttn[1].setBounds(295,390,20,20);

		for(int l=0; l<2;l++){
			bttnBrowser[l].setText("Browse");
			bttnBrowser[l].addActionListener(this);
			rationbttn[l].addActionListener(this);
			rationbttn[l].setOpaque(false);
			this.add(rationbttn[l]);
			this.add(textField[l]);
			this.add(bttnBrowser[l]);
			this.add(messageLabel[l]);
		}
		donebttn.setEnabled(false);
		this.add(donebttn);
		this.add(messageLabel[2]);
		this.add(messageLabel[3]);

		this.add(titleLabel[0]);
	}


	private void declear(){
		for(int k=0; k<2; k++){
			bttnBrowser[k]= new JButton();
			textField[k]= new JTextField();
			titleLabel[k]= new JLabel();
			messageLabel[k]= new JLabel();
			rationbttn[k]=  new JRadioButton();
		}
		messageLabel[2]= new JLabel();
		messageLabel[3]= new JLabel();
		donebttn.addActionListener(this);
	}
	int counter=0;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(counter==2)
			donebttn.setEnabled(true);

		if(arg0.getSource()==rationbttn[0])
			rationbttn[1].setSelected(false);
		else if(arg0.getSource()==rationbttn[1])
			rationbttn[0].setSelected(false);
		else if(arg0.getSource()==donebttn){
			sortClassObject= new Sort(textField[0].getText(),textField[1].getText());
			if(rationbttn[0].isSelected()){
				sortClassObject.copy();
				System.out.println("Copied");
			}
			else if(rationbttn[1].isSelected()){
				sortClassObject.move();
				System.out.println("moved");
			}

		}
		else{
			try{
				JFileChooser fileChoose= new JFileChooser();
				fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChoose.showOpenDialog(null);
				fileChoose.setAcceptAllFileFilterUsed(false);
				File select=fileChoose.getSelectedFile();


				if(select.getName()!=null){

					if(arg0.getSource()==bttnBrowser[0]){
						counter++;
						textField[0].setText(select.getPath());
					}
					else {
						counter++;
						textField[1].setText(select.getPath());
					}

				}
			}catch(Exception a){
				
			}
		}
	}

}

