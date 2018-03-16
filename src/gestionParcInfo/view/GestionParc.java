package gestionParcInfo.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gestionParcInfo.view.tab.AlerteTab;
import gestionParcInfo.view.tab.EmployeTab;
import gestionParcInfo.view.tab.ImprimanteTab;
import gestionParcInfo.view.tab.OrdinateurTab;
import gestionParcInfo.view.tab.ServeurTab;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class GestionParc extends JFrame {

	private JPanel contentPane;
	private JTable TBL_ordinateur;
	private JTable TBL_imprimante;
	private JTable table3;
	private JTable table4;
	private JTable table5;
	
	OrdinateurTab ordiTab =new OrdinateurTab();
	ImprimanteTab imprTab =new ImprimanteTab();
	ServeurTab servTab =new ServeurTab();
	EmployeTab emplTab =new EmployeTab();
	AlerteTab alerteTab =new AlerteTab();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionParc frame = new GestionParc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionParc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 713, 450);
		contentPane.add(tabbedPane);
		
		
		tabbedPane.addTab("Ordinateurs", null,ordiTab, null);		
		tabbedPane.addTab("Imprimantes", null, imprTab, null);		
		tabbedPane.addTab("Serveurs", null, servTab, null);
		tabbedPane.addTab("Employes", null, emplTab, null);
		
		
		
		tabbedPane.addTab("Alertes", null, alerteTab, null);
		
		
	}

}
