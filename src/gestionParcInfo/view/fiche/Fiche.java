package gestionParcInfo.view.fiche;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

/**
 * Classe abstraite de fiche.
 * @author Sebastien Claudel
 *
 */
public class Fiche extends JFrame implements ActionListener, Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum State {
			CREATION("Mode création"), 
			MODIFICATION("Mode modification"),
			VISUALISATION("Mode visualisation");
		
		private String name = "";
		
		State(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
	};
	
	protected JButton btnAnnuler, btnSauver;
	protected JToggleButton tglbtnMode;
	
	private final State initialState;
	private State currentState;
	protected JPanel contentPane;
	
	public Fiche(State initialState) {
		this.initComponents();
		
		this.initialState = initialState;
	}
	
	protected void changeState(State newState) {
		this.currentState = newState;
		this.tglbtnMode.setText(newState.toString());
		this.btnAnnuler.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnSauver.setEnabled(newState != Fiche.State.VISUALISATION);
	}
	
	public JButton getBtnSauver() {
		return btnSauver;
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	private void initComponents() {
		//Configuration fenetre
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);
		
		this.btnAnnuler = new JButton("Annuler");
		this.btnAnnuler.setBounds(0, 0, 89, 23);
		this.contentPane.add(this.btnAnnuler);
		
		this.tglbtnMode = new JToggleButton();
		this.tglbtnMode.addActionListener(this);
		this.tglbtnMode.setBounds(0, 0, 145, 23);
		this.contentPane.add(this.tglbtnMode);
		
		this.btnSauver = new JButton("Sauvegarder");
		this.btnAnnuler.addActionListener(this);
		this.btnSauver.setBounds(0, 0, 110, 23);
		this.contentPane.add(this.btnSauver);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == this.tglbtnMode) {
			switch(this.currentState) {
			case CREATION:
				changeState(Fiche.State.VISUALISATION);
				break;
			case MODIFICATION:
				changeState(Fiche.State.VISUALISATION);
				break;
			case VISUALISATION:
				switch(this.initialState) {
				case CREATION:
					changeState(Fiche.State.CREATION);
					break;
				case MODIFICATION:
					changeState(Fiche.State.MODIFICATION);
					break;
				case VISUALISATION:
					changeState(Fiche.State.MODIFICATION);
					break;
				}
				break;
				
			}
		}
		if(arg0.getSource() == this.btnAnnuler) {
			super.dispose();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
