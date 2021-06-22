package br.edu.utfpr.dv.bcb;

import java.awt.EventQueue;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JFormattedTextField textDataInicial;
	private JFormattedTextField textDataFinal;
	private JPanel panelChart;
	private JComboBox comboIndice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u00CDndices Banco Central");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblndice = new JLabel("\u00CDndice:");
		panel.add(lblndice);
		
		comboIndice = new JComboBox();
		comboIndice.setSize(new Dimension(200, comboIndice.getPreferredSize().height));
		comboIndice.setModel(new DefaultComboBoxModel(new String[] {"IGP-M"}));
		panel.add(comboIndice);
		
		JLabel lblDataInicial = new JLabel("Data Inicial:");
		panel.add(lblDataInicial);
		
		MaskFormatter mascara = new MaskFormatter();
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mascara.setPlaceholder("_");
		
		textDataInicial = new JFormattedTextField(mascara);
		textDataInicial.setColumns(10);
		
		panel.add(textDataInicial);
		
		JLabel lblDataFinal = new JLabel("Data Final:");
		panel.add(lblDataFinal);
		
		textDataFinal = new JFormattedTextField(mascara);
		textDataFinal.setColumns(10);
		panel.add(textDataFinal);
		
		JButton buttonGerar = new JButton("Gerar Gr\u00E1fico");
		buttonGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coletarDados();
			}
		});
		panel.add(buttonGerar);
		
		panelChart = new JPanel();
		frame.getContentPane().add(panelChart, BorderLayout.CENTER);
		panelChart.setLayout(new BorderLayout(0, 0));
		
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
	
	private long getIndice() {
		if(this.comboIndice.getSelectedItem().equals("IGP-M")) {
			return 189;	
		} else {
			return 0;
		}
	}
	
	private String getDataInicial() {
		return textDataInicial.getText();
	}
	
	private String getDataFinal() {
		return textDataFinal.getText();
	}
	
	private void coletarDados() {
		
	}
	
	private void gerarGrafico(String titulo, List<ItemBCB> valores) {
		TimeSeries serie = new TimeSeries(titulo);
		
		for(ItemBCB val : valores) {
			System.out.println(val.getData());
			serie.add(new Month(val.getData()), val.getValor());
		}
		
		TimeSeriesCollection dados = new TimeSeriesCollection(serie);
		JFreeChart grafico = ChartFactory.createTimeSeriesChart(titulo, "Mês", "%", dados);
		ChartPanel panel = new ChartPanel(grafico);
		
		this.panelChart.removeAll();
		this.panelChart.add(panel, BorderLayout.CENTER);
		this.frame.setSize((int)this.frame.getSize().getWidth() + 1, (int)this.frame.getSize().getHeight() + 1);
		
	}
	
}
