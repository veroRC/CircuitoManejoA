package Formulario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Conexion;
import clases.Factura;

import java.awt.ScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.Icon;

public class Intercar {

	public JFrame frmcircuitoManejoA;
	public static JTextField txt_NFact;
	private JLabel txt_fecha;
	private JComboBox txt_cod;
	private JTextField txt_prod;
	private JTextField txt_cant;
	private JTextField txt_precio;
	private JTable tabla_fact;
	public JComboBox cmb_clientes;
	Connection con=null;

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public Intercar() throws ClassNotFoundException {
		initialize();
		con=Conexion.Conectar();
		llenar();
		llenar_NFactura();
		
	}

	private void initialize() {
		frmcircuitoManejoA = new JFrame();
		frmcircuitoManejoA.getContentPane().setBackground(new Color(189, 183, 107));
		frmcircuitoManejoA.setBounds(100, 100, 1033, 506);
		frmcircuitoManejoA.setLocationRelativeTo(null);
		frmcircuitoManejoA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmcircuitoManejoA.setTitle("CONTROL CIRCUITO MANEJO");
		frmcircuitoManejoA.setResizable(false);
		frmcircuitoManejoA.getContentPane().setLayout(null);
		
		JLabel lblNFactura = new JLabel("N\u00B0 Factura:");
		lblNFactura.setForeground(Color.WHITE);
		lblNFactura.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblNFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblNFactura.setBounds(55, 104, 94, 16);
		frmcircuitoManejoA.getContentPane().add(lblNFactura);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblFecha.setBounds(42, 155, 94, 16);
		frmcircuitoManejoA.getContentPane().add(lblFecha);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblCodigo.setBounds(10, 246, 116, 27);
		frmcircuitoManejoA.getContentPane().add(lblCodigo);
		
		JLabel lblProducto = new JLabel("Modelo");
		lblProducto.setForeground(Color.WHITE);
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducto.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblProducto.setBounds(161, 246, 116, 27);
		frmcircuitoManejoA.getContentPane().add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Horas");
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblCantidad.setBounds(306, 246, 116, 27);
		frmcircuitoManejoA.getContentPane().add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblPrecio.setBounds(467, 246, 116, 27);
		frmcircuitoManejoA.getContentPane().add(lblPrecio);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblTotal.setBounds(600, 246, 130, 27);
		frmcircuitoManejoA.getContentPane().add(lblTotal);
		
		txt_NFact = new JTextField();
		txt_NFact.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txt_NFact.setEnabled(false);
		txt_NFact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char t=arg0.getKeyChar();
				if(t<'0' || t>'9'){
					arg0.consume();
				}
			}
		});
		txt_NFact.setBounds(159, 103, 149, 22);
		frmcircuitoManejoA.getContentPane().add(txt_NFact);
		txt_NFact.setColumns(10);
		
		Calendar c=new GregorianCalendar();
		int y=c.get(Calendar.YEAR);
		int m=c.get(Calendar.MONTH);
		int d=c.get(Calendar.DAY_OF_MONTH);
		String a=String.valueOf(y);
		String b=String.valueOf(m+1);
		String day=String.valueOf(d);
		String fecha=""+day+"/"+b+"/"+a;
		txt_fecha = new JLabel();
		txt_fecha.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		txt_fecha.setForeground(Color.WHITE);
		txt_fecha.setBounds(161, 154, 116, 22);
		txt_fecha.setText(fecha);
		frmcircuitoManejoA.getContentPane().add(txt_fecha);
		
		txt_cod = new JComboBox();
		txt_cod.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txt_cod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent l) {
				try{
					java.sql.Statement instruccion=con.createStatement();
					ResultSet rs=instruccion.executeQuery("SELECT * FROM Producto where codigo='"+txt_cod.getSelectedItem()+"'");
					while(rs.next()){
						txt_prod.setText(rs.getString("nombre"));
						txt_precio.setText(String.valueOf(rs.getDouble("precio")));
					}
				}catch(Exception e){
					System.out.println(e);
				}
				
			}
		});
		txt_cod.setBounds(42, 272, 116, 22);
		frmcircuitoManejoA.getContentPane().add(txt_cod);
		
		txt_prod = new JTextField();
		txt_prod.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txt_prod.setEnabled(false);
		txt_prod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char t=arg0.getKeyChar();
				if(!Character.isLetter(t))
					arg0.consume();
			}
		});
		txt_prod.setColumns(10);
		txt_prod.setBounds(192, 272, 116, 22);
		frmcircuitoManejoA.getContentPane().add(txt_prod);
		
		txt_cant = new JTextField();
		txt_cant.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txt_cant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				String temp=txt_cant.getText();
				if(t<'0'|| t>'9' || temp.length()==3)
					e.consume();
			}
		});
		txt_cant.setColumns(10);
		txt_cant.setBounds(341, 272, 116, 22);
		frmcircuitoManejoA.getContentPane().add(txt_cant);
		
		txt_precio = new JTextField();
		txt_precio.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txt_precio.setEnabled(false);
		txt_precio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				String temp=txt_precio.getText();
				if((t<'0'||t>'9')&& (t!=KeyEvent.VK_BACK_SPACE) && (t!='.'))
					e.consume();
			}
		});
		txt_precio.setColumns(10);
		txt_precio.setBounds(497, 272, 116, 22);
		frmcircuitoManejoA.getContentPane().add(txt_precio);
		
		JLabel lbl_total = new JLabel("0.00");
		lbl_total.setForeground(Color.WHITE);
		lbl_total.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_total.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lbl_total.setBounds(625, 273, 116, 16);
		frmcircuitoManejoA.getContentPane().add(lbl_total);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 304, 757, 145);
		frmcircuitoManejoA.getContentPane().add(scrollPane);
		
		tabla_fact = new JTable();
		tabla_fact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tabla_fact.getSelectedRow()>=0)
				{
					int fila=tabla_fact.getSelectedRow();
					String[] datosL={String.valueOf(tabla_fact.getValueAt(fila, 0)),
							String.valueOf(tabla_fact.getValueAt(fila, 1)),
							String.valueOf(tabla_fact.getValueAt(fila, 2)),
							String.valueOf(tabla_fact.getValueAt(fila, 3)),
							String.valueOf(tabla_fact.getValueAt(fila, 4))};
					//txt_cod.setText(datosL[0]);
					txt_prod.setText(datosL[1]);
					txt_cant.setText(datosL[2]);
					txt_precio.setText(datosL[3]);
					lbl_total.setText(datosL[4]);
				}
			}
		});
		scrollPane.setViewportView(tabla_fact);
		tabla_fact.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Modelo", "Horas", "Precio", "Total"
			}
		));
		
		JButton btn_agregar = new JButton("Agregar Auto");
		btn_agregar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txt_NFact.getText().length()==0 || txt_prod.getText().length()==0 || txt_precio.getText().length()==0 || txt_cant.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Complete todos los campos","Error",JOptionPane.ERROR_MESSAGE);
				else{
					String cad="INSERT INTO Factura_det(n_fact,cantidad,cod_prod) VALUES(?,?,?)";
					PreparedStatement st=null;
					int afec=0;
					try{
						st=con.prepareStatement(cad);
						st.setInt(1,Integer.parseInt(txt_NFact.getText())-1);//era sin -1
						st.setInt(2,Integer.parseInt(txt_cant.getText()));
						st.setString(3,txt_cod.getSelectedItem().toString());
						afec=st.executeUpdate();
						JOptionPane.showMessageDialog(null, "Vehiculo ingresado correctamente");
					}catch(SQLException e){
						JOptionPane.showMessageDialog(null, "El vehiculo ya existe en la factura","Error",JOptionPane.ERROR_MESSAGE);
					}
					if (afec>0){
						DefaultTableModel m=(DefaultTableModel) tabla_fact.getModel();
						Object[] fila= new Object[6];
						fila[0]=txt_cod.getSelectedItem();
						fila[1]=txt_prod.getText();
						fila[2]=txt_cant.getText();
						fila[3]=txt_precio.getText();
						double temp = Double.parseDouble(txt_precio.getText())* Double.parseDouble(txt_cant.getText());    
						temp=Math.round(temp*100)/100d;
						fila[4]=String.valueOf(temp);
						m.addRow(fila);
						
					}
				}
			}
		});
		btn_agregar.setBounds(354, 97, 187, 35);
		frmcircuitoManejoA.getContentPane().add(btn_agregar);
		
		JButton btn_eliminar = new JButton("Eliminar Pedido");
		btn_eliminar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btn_eliminar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel m=(DefaultTableModel) tabla_fact.getModel();
				int fila=tabla_fact.getSelectedRow();
				if(fila<0){
					JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla",null,fila,null);
				}
				else{
					int resp=JOptionPane.showConfirmDialog(null, "¿Esta seguro?","Alerta",JOptionPane.YES_NO_OPTION);
					if(JOptionPane.OK_OPTION==resp){
						m.removeRow(fila);
						int n=Integer.parseInt(txt_NFact.getText());
						n=n-1;
						String cad="DELETE FROM Factura_det WHERE n_fact="+n+" and cod_prod='"+txt_cod.getSelectedItem().toString()+"'";
						PreparedStatement st=null;
						try{
							st=con.prepareStatement(cad);
							st.execute();
							JOptionPane.showMessageDialog(null, "vehiculo eliminado");
						}catch(SQLException e){
							System.out.println(e.getMessage());
							JOptionPane.showMessageDialog(null, "Error al eliminar vehiculo","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			}
		});
		btn_eliminar.setBounds(354, 186, 187, 35);
		frmcircuitoManejoA.getContentPane().add(btn_eliminar);
		
		JButton btn_modificar = new JButton("Modificar Tiempo");
		btn_modificar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tabla_fact.getSelectedRow()>=0){
					int n=Integer.parseInt(txt_NFact.getText());
					n=n-1;
					String cad="Update Factura_det set cantidad="+Integer.parseInt(txt_cant.getText())+" where cod_prod='"+txt_cod.getSelectedItem().toString()+"' and n_fact="+n;
					PreparedStatement st=null;
					try{
						st=con.prepareStatement(cad);
						st.execute();
					}catch(SQLException e){
						System.out.println(1);
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(null, "Error al modificar vehiculo","Error",JOptionPane.ERROR_MESSAGE);
					}
					int fil=tabla_fact.getSelectedRow();
					double tot=Integer.parseInt(txt_cant.getText())*
							Double.parseDouble(txt_precio.getText());
					tabla_fact.setValueAt(txt_cod.getSelectedItem(), fil, 0);//ojo modificado
					tabla_fact.setValueAt(txt_prod.getText(), fil, 1);
					tabla_fact.setValueAt(txt_cant.getText(), fil, 2);
					tabla_fact.setValueAt(txt_precio.getText(), fil, 3);
					tabla_fact.setValueAt(String.valueOf(tot), fil, 4);
				}
				
			}
		});
		btn_modificar.setBounds(354, 143, 187, 33);
		frmcircuitoManejoA.getContentPane().add(btn_modificar);
		
		/*ImageIcon icon=new ImageIcon("disco.png");
		Image img=icon.getImage();
		Image img2 =img.getScaledInstance(84, 64, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon2=new ImageIcon(img2);*/
		JButton btnNewButton = new JButton();
		btnNewButton.setIcon(new ImageIcon(formFacturacion.class.getResource("/Imagenes/Factura.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertar_fact();
				Factura_modelo fa=new Factura_modelo(txt_NFact.getText(),txt_fecha.getText(),(String)cmb_clientes.getSelectedItem(),tabla_fact);
				fa.setVisible(true);
			}
		});
		btnNewButton.setBounds(587, 143, 99, 97);
		frmcircuitoManejoA.getContentPane().add(btnNewButton);
		cmb_clientes = new JComboBox();
		cmb_clientes.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		DefaultComboBoxModel modelo=new DefaultComboBoxModel();
		cmb_clientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		cmb_clientes.setBounds(159, 199, 149, 22);
		frmcircuitoManejoA.getContentPane().add(cmb_clientes);
		JLabel lbl_cliente = new JLabel("Cliente:");
		lbl_cliente.setForeground(Color.WHITE);
		lbl_cliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cliente.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lbl_cliente.setBounds(42, 200, 94, 16);
		frmcircuitoManejoA.getContentPane().add(lbl_cliente);
		
		/*ImageIcon icon_cliente=new ImageIcon("nuevoCliente.jpg");
		Image img_cliente=icon_cliente.getImage();
		Image img_cliente2=img_cliente.getScaledInstance(1, 1, java.awt.Image.SCALE_SMOOTH);*/
		//ImageIcon icon2_cliente=new ImageIcon(img_cliente2);
		JButton btnRegistro = new JButton(new ImageIcon(formFacturacion.class.getResource("/Imagenes/agregarcliente.png")));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar_cliente nc;
				try {
					nc = new registrar_cliente();
					nc.setVisible(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnRegistro.setBounds(857, 97, 99, 99);
		frmcircuitoManejoA.getContentPane().add(btnRegistro);
		
		JButton btn_actualizar = new JButton("Actualizar Nuevos Datos Ingresados");
		btn_actualizar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btn_actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_cod.removeAllItems();
				cmb_clientes.removeAllItems();
				llenar();
			}
		});
		btn_actualizar.setBounds(587, 97, 232, 35);
		frmcircuitoManejoA.getContentPane().add(btn_actualizar);
		
		JButton btnNuevoProducto = new JButton("");
		btnNuevoProducto.setIcon(new ImageIcon(formFacturacion.class.getResource("/Imagenes/agregarproducto.png")));
		btnNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					nuevo_producto np=new nuevo_producto();
					np.setVisible(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNuevoProducto.setBounds(857, 258, 99, 97);
		frmcircuitoManejoA.getContentPane().add(btnNuevoProducto);
		
		JButton btnNuevaFactura = new JButton("Crear Factura");
		btnNuevaFactura.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnNuevaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				llenar_NFactura();
				txt_cant.setText("");
				LimpiarTabla((DefaultTableModel) tabla_fact.getModel());
			}
		});
		btnNuevaFactura.setBounds(830, 407, 161, 42);
		frmcircuitoManejoA.getContentPane().add(btnNuevaFactura);
		
		JLabel lblIngresarNuevoCliente = new JLabel("Nuevo Cliente");
		lblIngresarNuevoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresarNuevoCliente.setForeground(Color.WHITE);
		lblIngresarNuevoCliente.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblIngresarNuevoCliente.setBounds(846, 195, 116, 27);
		frmcircuitoManejoA.getContentPane().add(lblIngresarNuevoCliente);
		
		JLabel lblAgregarNuevoProducto = new JLabel("Nuevo Automovil");
		lblAgregarNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarNuevoProducto.setForeground(Color.WHITE);
		lblAgregarNuevoProducto.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblAgregarNuevoProducto.setBounds(825, 355, 166, 27);
		frmcircuitoManejoA.getContentPane().add(lblAgregarNuevoProducto);
		
		JLabel lblVistaPrevia = new JLabel("Vista Previa");
		lblVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
		lblVistaPrevia.setForeground(Color.WHITE);
		lblVistaPrevia.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblVistaPrevia.setBounds(690, 176, 116, 27);
		frmcircuitoManejoA.getContentPane().add(lblVistaPrevia);
		
		JLabel lblNewLabel = new JLabel("Circuito Vehicular AQP");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
		lblNewLabel.setBounds(204, 23, 570, 49);
		frmcircuitoManejoA.getContentPane().add(lblNewLabel);
	}
	
	@SuppressWarnings("unchecked")
	public void llenar(){
		DefaultComboBoxModel modelo=new DefaultComboBoxModel();
		try{
			java.sql.Statement instruccion=con.createStatement();
			ResultSet rs=instruccion.executeQuery("SELECT * FROM Cliente");
			while(rs.next()){
				modelo.addElement(rs.getString("nombre"));
				cmb_clientes.setModel(modelo);
			}
			ResultSet rs2=instruccion.executeQuery("SELECT * FROM Producto");
			while(rs2.next())
				txt_cod.addItem(rs2.getString("codigo"));
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void insertar_fact(){
		String t="";
		String dni=cmb_clientes.getSelectedItem().toString();
		try{
			java.sql.Statement instruccion=con.createStatement();
			ResultSet rs2=instruccion.executeQuery("SELECT dni FROM Cliente where nombre='"+dni+"'");
			while(rs2.next())
				t=(rs2.getString("dni"));
			rs2.close();
		}catch(Exception e){
			System.out.println("Error");
		}
		
		int num_fact=1;
		try{
			java.sql.Statement instruccion=con.createStatement();
			ResultSet rs2=instruccion.executeQuery("SELECT MAX(num_factura) from Factura");
			while(rs2.next())
				num_fact=rs2.getInt(1)+1;
			rs2.close();
		}catch(Exception e){
			System.out.println("Error1");
		}
		
		String cad="INSERT INTO Factura(num_factura,cod_cli,fecha) VALUES(?,?,?)";
		PreparedStatement st=null;
		try{
			st=con.prepareStatement(cad);
			st.setInt(1, num_fact);
			st.setString(2,t);
			st.setString(3,txt_fecha.getText());
			st.executeUpdate();
			System.out.println("Factura creada");
			//JOptionPane.showMessageDialog(null, "Articulo ingresado correctamente");
		}catch(SQLException e){
			System.out.println(1);
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al crear factura","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public void llenar_NFactura(){
		int num_fact=1;
		try{
			java.sql.Statement instruccion=con.createStatement();
			ResultSet rs2=instruccion.executeQuery("SELECT MAX(num_factura) from Factura");
			while(rs2.next())
				num_fact=rs2.getInt(1)+1;
			System.out.println(num_fact);
			rs2.close();
		}catch(Exception e){
			System.out.println("No se pudo obtener valor");
		}
		txt_NFact.setText(Integer.toString(num_fact));
	}
	
	public void verificarF(){
		int num_fact=1;
		try{
			java.sql.Statement instruccion=con.createStatement();
			ResultSet rs2=instruccion.executeQuery("SELECT MAX(num_factura) from Factura");
			while(rs2.next())
				num_fact=rs2.getInt(1);
			System.out.println(num_fact);
			rs2.close();
		}catch(Exception e){
			System.out.println("No se pudo obtener valor");
		}
		txt_NFact.setText(Integer.toString(num_fact));
		if(num_fact<Integer.parseInt(txt_NFact.getText())){
			insertar_fact();
		}
	}
	
	public void LimpiarTabla(DefaultTableModel modelo){
		int el=modelo.getRowCount()-1;
		for(int i=el; i>=0;i--){
			modelo.removeRow(i );
			}
	}
	
	public ImageIcon Imagen(String nombreI){
		ImageIcon icon=new ImageIcon("nombreI");
		Image img=icon.getImage();
		Image img2 =img.getScaledInstance(84, 64, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon2=new ImageIcon(img2);
		return icon2;
	}
}
