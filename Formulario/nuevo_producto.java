package Formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class nuevo_producto extends JFrame {

	private JPanel contentPane;
	Connection con=null;
	private JTextField txt_cod;
	private JTextField txt_nomb;
	private JTextField txt_precio;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nuevo_producto frame = new nuevo_producto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public nuevo_producto() throws ClassNotFoundException {
		con=Conexion.Conectar();
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
					String cad="INSERT INTO Producto(codigo,nombre,precio) VALUES(?,?,?)";
					PreparedStatement st=null;
					try{
						st=con.prepareStatement(cad);
						st.setString(1, txt_cod.getText());
						st.setString(2, txt_nomb.getText());
						st.setDouble(3, Double.parseDouble(txt_precio.getText()));
						st.execute();
						JOptionPane.showMessageDialog(null, "Registro correcto");
					}catch(SQLException e){
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
				if((t<'0'||t>'9') && (t!=KeyEvent.VK_BACK_SPACE) && (t!='.') || temp.length()==6)
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
				if((!Character.isLetter(t) && t!=KeyEvent.VK_SPACE) || temp.length()==12)
					e.consume();
			}
		});
		txt_nomb.setColumns(10);
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
