package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Cliente;
import controllers.Facturacion;

public class Facturacion_nuevoCliente extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_dni;
	private JTextField txt_telef;
	private JTextField txt_correo;
	
	Facturacion controlador;
	
	public Facturacion_nuevoCliente(Facturacion controlador) {
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
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngresoDeNuevo = new JLabel("Ingreso de Nuevo Cliente");
		lblIngresoDeNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresoDeNuevo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIngresoDeNuevo.setBounds(80, 13, 281, 46);
		contentPane.add(lblIngresoDeNuevo);
		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_nombre.setBounds(23, 72, 94, 16);
		contentPane.add(lbl_nombre);
		
		JLabel lbl_dni = new JLabel("DNI:");
		lbl_dni.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dni.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_dni.setBounds(23, 118, 94, 16);
		contentPane.add(lbl_dni);
		
		JLabel lbl_telef = new JLabel("Telefono:");
		lbl_telef.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_telef.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_telef.setBounds(23, 162, 94, 16);
		contentPane.add(lbl_telef);
		
		JLabel lbl_correo = new JLabel("Correo:");
		lbl_correo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_correo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_correo.setBounds(23, 202, 94, 16);
		contentPane.add(lbl_correo);
		
		txt_nombre = new JTextField();
		txt_nombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char t=arg0.getKeyChar();
				String temp=txt_nombre.getText();
				if(!Character.isLetter(t) && t!=KeyEvent.VK_SPACE || temp.length()==25)
					arg0.consume();
			}
		});
		txt_nombre.setBounds(110, 71, 116, 22);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_dni = new JTextField();
		txt_dni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar() ;
				String temp=txt_dni.getText();
				if(t<'0'|| t>'9' || temp.length()==8 )
					e.consume();
			}
		});
		txt_dni.setColumns(10);
		txt_dni.setBounds(110, 117, 116, 22);
		contentPane.add(txt_dni);
		
		txt_telef = new JTextField();
		txt_telef.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				String temp=txt_telef.getText();
				if(t<'0'|| t>'9' || temp.length()==9)
					e.consume();
			}
		});
		txt_telef.setColumns(10);
		txt_telef.setBounds(110, 161, 116, 22);
		contentPane.add(txt_telef);
		
		txt_correo = new JTextField();
		txt_correo.setColumns(10);
		txt_correo.setBounds(110, 201, 116, 22);
		contentPane.add(txt_correo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pattern patron=Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
				Matcher mather=patron.matcher(txt_correo.getText());
				if (txt_nombre.getText().length()==0 || txt_dni.getText().length()==0 || txt_telef.getText().length()==0 || txt_correo.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Complete todos los campos","Error",JOptionPane.ERROR_MESSAGE);
				else{
					if(mather.find()==false)
						JOptionPane.showMessageDialog(null,"Ingrese un correo que sea valido", "Error", JOptionPane.ERROR_MESSAGE);
					else{
						try{;
							Facturacion_nuevoCliente.this.controlador.insertarCliente(new Cliente(txt_dni.getText(),txt_nombre.getText(),txt_correo.getText(),txt_telef.getText()));
							JOptionPane.showMessageDialog(null, "Registro correcto");
						}catch(Exception e){
							System.out.println(e.getMessage());
							JOptionPane.showMessageDialog(null, "El cliente ya esta registrado","Error",JOptionPane.ERROR_MESSAGE);
						}
					}	
				}
		}
		});
		btnAgregar.setBounds(176, 251, 97, 25);
		this.setTitle("Registro de clientes");
		contentPane.add(btnAgregar);
	}
}
