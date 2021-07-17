package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Producto;
import controllers.Facturacion;

public class Facturacion_nuevoProducto extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection con=null;
	private JTextField txt_cod;
	private JTextField txt_nomb;
	private JTextField txt_precio;
	
	Facturacion controlador;
	
	public Facturacion_nuevoProducto(Facturacion controlador) {
		this.controlador = controlador;
		this.interfaz();
	}
	
	public void load() {
		this.setVisible(true);
	}
	
	public void close() {
		this.setVisible(false);
		this.dispose();
	}
	
	public void interfaz() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevoProducto = new JLabel("Nuevo Vehiculo");
		lblNuevoProducto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoProducto.setBounds(92, 13, 285, 35);
		contentPane.add(lblNuevoProducto);
		
		JLabel lbl_codigo = new JLabel("Codigo:");
		lbl_codigo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_codigo.setBounds(59, 97, 67, 21);
		contentPane.add(lbl_codigo);
		
		JLabel lbl_nombre = new JLabel("Modelo:");
		lbl_nombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_nombre.setBounds(59, 149, 67, 21);
		contentPane.add(lbl_nombre);
		
		JLabel lbl_precio = new JLabel("Precio:");
		lbl_precio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_precio.setBounds(59, 199, 67, 21);
		contentPane.add(lbl_precio);
		
		JButton btnIngresarProducto = new JButton("Ingresar Producto");
		btnIngresarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txt_cod.getText().length()==0 || txt_nomb.getText().length()==0 || txt_precio.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Complete todos los campos","Error",JOptionPane.ERROR_MESSAGE);
				else{
					try{
						Facturacion_nuevoProducto.this.controlador.insertarProducto(new Producto(txt_cod.getText(),txt_nomb.getText(),Double.parseDouble(txt_precio.getText())));
						JOptionPane.showMessageDialog(null, "Registro correcto");
					}catch(Exception e){
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(null, "El vehiculo ya esta registrado","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnIngresarProducto.setBounds(160, 269, 149, 35);
		contentPane.add(btnIngresarProducto);
		
		txt_cod = new JTextField();
		txt_cod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				String temp=txt_cod.getText();
				if(!(t>='0'||t<='9') && !(t>='A'||t<='Z') && (t!=KeyEvent.VK_BACK_SPACE) && (t!='.') || temp.length()==9)
					e.consume();
			}
		});
		txt_cod.setBounds(138, 98, 116, 22);
		contentPane.add(txt_cod);
		txt_cod.setColumns(10);
		
		txt_nomb = new JTextField();
		txt_nomb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				String temp=txt_nomb.getText();
				if((!Character.isLetter(t) && t!=KeyEvent.VK_SPACE) || temp.length()==16)
					e.consume();
			}
		});
		txt_nomb.setColumns(16);
		txt_nomb.setBounds(138, 150, 116, 22);
		contentPane.add(txt_nomb);
		
		txt_precio = new JTextField();
		txt_precio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				String temp=txt_precio.getText();
				if((t<'0'||t>'9') && (t!=KeyEvent.VK_BACK_SPACE) && (t!='.') || temp.length()==6)
					e.consume();
			}
		});
		txt_precio.setColumns(10);
		txt_precio.setBounds(138, 200, 116, 22);
		this.setTitle("Registro de Productos");
		contentPane.add(txt_precio);
	}
}
