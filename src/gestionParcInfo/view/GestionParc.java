package gestionParcInfo.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

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
	
	private OrdinateurTab ordiTab;
	private ImprimanteTab imprTab;
	private ServeurTab servTab;
	private EmployeTab emplTab;
	private AlerteTab alerteTab;
	
	
	public GestionParc(OrdinateurTab ordiTab, ImprimanteTab imprTab, ServeurTab servTab, EmployeTab emplTab, AlerteTab alerteTab) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 713, 450);
		contentPane.add(tabbedPane);
		
		this.ordiTab = ordiTab;
		this.imprTab = imprTab;
		this.servTab = servTab;
		this.emplTab = emplTab;
		this.alerteTab = alerteTab;
		
		tabbedPane.addTab("Ordinateurs", null,ordiTab, null);		
		tabbedPane.addTab("Imprimantes", null, imprTab, null);		
		tabbedPane.addTab("Serveurs", null, servTab, null);
		tabbedPane.addTab("Employes", null, emplTab, null);
		tabbedPane.addTab("Alertes", null, alerteTab, null);
	}
	
}
