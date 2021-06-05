package Formulario;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Factura_modelo extends JFrame {

	private JPanel contentPane;
	private JTable table;

	
	public Factura_modelo(String n,String d2,String d3, JTable d4) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Factura");
		this.setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 72, 273, 101);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_tit_cli = new JLabel("Cliente:");
		lbl_tit_cli.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_tit_cli.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tit_cli.setBounds(24, 13, 60, 22);
		panel.add(lbl_tit_cli);
		
		JLabel lbl_tit_fec = new JLabel("Fecha:");
		lbl_tit_fec.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tit_fec.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_tit_fec.setBounds(24, 52, 60, 22);
		panel.add(lbl_tit_fec);
		
		JLabel lbl_cli = new JLabel("");
		lbl_cli.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cli.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_cli.setBounds(109, 13, 120, 22);
		lbl_cli.setText(d3);
		panel.add(lbl_cli);
		
		JLabel lbl_fecha = new JLabel("");
		lbl_fecha.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fecha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_fecha.setBounds(109, 52, 120, 22);
		lbl_fecha.setText(d2);
		panel.add(lbl_fecha);
		
		JLabel lblBoletaDeVenta = new JLabel("Boleta de Venta");
		lblBoletaDeVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoletaDeVenta.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblBoletaDeVenta.setBounds(136, 13, 283, 38);
		contentPane.add(lblBoletaDeVenta);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(327, 72, 185, 101);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbl_ti_num = new JLabel("N\u00B0 Factura");
		lbl_ti_num.setBounds(41, 13, 101, 26);
		lbl_ti_num.setFont(new Font("Tahoma", Font.PLAIN, 21));
		panel_1.add(lbl_ti_num);
		
		JLabel lbl_num = new JLabel("");
		lbl_num.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_num.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_num.setBounds(12, 53, 161, 16);
		lbl_num.setText(n);
		panel_1.add(lbl_num);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 217, 500, 146);
		contentPane.add(scrollPane);
		
		table = new JTable();
		TableModel model=d4.getModel();
		table.setModel(model);
		scrollPane.setViewportView(table);
		Precio_T();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(327, 376, 185, 78);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblIgv = new JLabel("IGV:");
		lblIgv.setHorizontalAlignment(SwingConstants.CENTER);
		lblIgv.setBounds(12, 0, 68, 16);
		panel_2.add(lblIgv);
		
		JLabel lblTotal = new JLabel("Subtotal:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(12, 28, 68, 16);
		panel_2.add(lblTotal);
		
		JLabel lbl_total = new JLabel("Total:");
		lbl_total.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_total.setBounds(12, 58, 68, 16);
		panel_2.add(lbl_total);
		
		JLabel lbl_igv = new JLabel("");
		Double igv=IGV();
		String temp2=String.valueOf(igv);
		lbl_igv.setText(temp2);
		lbl_igv.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_igv.setBounds(92, 0, 68, 16);
		panel_2.add(lbl_igv);
		
		
		JLabel lbl_subt = new JLabel("");
		//Double temp3=Double.parseDouble(igv);
		Double subt=Subtotal(igv);
		String temp3=String.valueOf(subt);
		lbl_subt.setText(temp3);
		lbl_subt.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_subt.setBounds(92, 28, 68, 16);
		panel_2.add(lbl_subt);
		
		JLabel lbl_tot = new JLabel("");
		lbl_tot.setHorizontalAlignment(SwingConstants.CENTER);
		Double total=Precio_T();
		String temp=String.valueOf(total);
		lbl_tot.setText(temp);
		lbl_tot.setBounds(92, 58, 68, 16);
		this.setTitle("Vista previa de la factura");
		panel_2.add(lbl_tot);
	}
	
	public double Precio_T(){
		double cont=0;
		String temp="";
		if(table.getRowCount()>0){
			for(int i=0;i<table.getRowCount();i++){
				temp=table.getValueAt(i, 4).toString();
				cont=cont+Double.parseDouble(temp);
			}
		}
		return cont;
	}
	
	public Double IGV(){
		double total=Precio_T();
		Double igv=total/1.18;
		igv=total-igv;
		System.out.println(igv);
		igv=Math.round(igv*100)/100d;
		return igv;
	}
	
	public Double Subtotal(double igv){
		double total=Precio_T();
		double subt=total-igv;
		subt=Math.round(subt*100)/100d;
		return subt;
	}
}
