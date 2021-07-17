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

public class formFacturacion {

	private JFrame frmFacturacionCircuito;
	public static JTextField txt_NFact;
	private JLabel txt_fecha;
	private JComboBox txt_cod;
	private JTextField txt_prod;
	private JTextField txt_cant;
	private JTextField txt_precio;
	private JTable tabla_fact;
	public JComboBox cmb_clientes;
	Connection con=null;
	//static final String Controlador="org.sqlite.JDBC";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formFacturacion window = new formFacturacion();
					window.frmFacturacionCircuito.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public formFacturacion() throws ClassNotFoundException {
		//String t="";
		initialize();
		
		con=Conexion.Conectar();
		llenar();
		llenar_NFactura();
		
	}

	private void initialize() {
		frmFacturacionCircuito = new JFrame();
		frmFacturacionCircuito.getContentPane().setBackground(Color.BLACK);
		frmFacturacionCircuito.setBounds(100, 100, 1033, 506);
		frmFacturacionCircuito.setLocationRelativeTo(null);
		frmFacturacionCircuito.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFacturacionCircuito.setTitle("FacturacionTestingFinal");
		frmFacturacionCircuito.setResizable(false);
		frmFacturacionCircuito.getContentPane().setLayout(null);
		
		JLabel lblNFactura = new JLabel("N\u00B0 Factura:");
		lblNFactura.setForeground(Color.WHITE);
		lblNFactura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblNFactura.setBounds(55, 40, 94, 16);
		frmFacturacionCircuito.getContentPane().add(lblNFactura);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(55, 74, 94, 16);
		frmFacturacionCircuito.getContentPane().add(lblFecha);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(55, 186, 116, 27);
		frmFacturacionCircuito.getContentPane().add(lblCodigo);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setForeground(Color.WHITE);
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProducto.setBounds(195, 186, 116, 27);
		frmFacturacionCircuito.getContentPane().add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(343, 186, 116, 27);
		frmFacturacionCircuito.getContentPane().add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio.setBounds(481, 186, 116, 27);
		frmFacturacionCircuito.getContentPane().add(lblPrecio);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(611, 186, 130, 27);
		frmFacturacionCircuito.getContentPane().add(lblTotal);
		
		txt_NFact = new JTextField();
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
		txt_NFact.setBounds(161, 38, 116, 22);
		frmFacturacionCircuito.getContentPane().add(txt_NFact);
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
		txt_fecha.setForeground(Color.WHITE);
		txt_fecha.setBounds(161, 72, 116, 22);
		txt_fecha.setText(fecha);
		frmFacturacionCircuito.getContentPane().add(txt_fecha);
		
		txt_cod = new JComboBox();
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
		txt_cod.setBounds(55, 224, 116, 22);
		frmFacturacionCircuito.getContentPane().add(txt_cod);
		
		txt_prod = new JTextField();
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
		txt_prod.setBounds(195, 224, 116, 22);
		frmFacturacionCircuito.getContentPane().add(txt_prod);
		
		txt_cant = new JTextField();
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
		txt_cant.setBounds(343, 224, 116, 22);
		frmFacturacionCircuito.getContentPane().add(txt_cant);
		
		txt_precio = new JTextField();
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
		txt_precio.setBounds(481, 224, 116, 22);
		frmFacturacionCircuito.getContentPane().add(txt_precio);
		
		JLabel lbl_total = new JLabel("0.00");
		lbl_total.setForeground(Color.WHITE);
		lbl_total.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_total.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_total.setBounds(621, 224, 116, 16);
		frmFacturacionCircuito.getContentPane().add(lbl_total);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 257, 714, 145);
		frmFacturacionCircuito.getContentPane().add(scrollPane);
		
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
				"Codigo", "Producto", "Cantidad", "Precio", "Total"
			}
		));
		
		JButton btn_agregar = new JButton("Agregar Producto");
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
						JOptionPane.showMessageDialog(null, "Articulo ingresado correctamente");
					}catch(SQLException e){
						JOptionPane.showMessageDialog(null, "El articulo ya existe en la factura","Error",JOptionPane.ERROR_MESSAGE);
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
		btn_agregar.setBounds(587, 26, 154, 25);
		frmFacturacionCircuito.getContentPane().add(btn_agregar);
		
		JButton btn_eliminar = new JButton("Eliminar Producto");
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
							JOptionPane.showMessageDialog(null, "Producto eliminado");
						}catch(SQLException e){
							System.out.println(e.getMessage());
							JOptionPane.showMessageDialog(null, "Error al eliminar producto","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			}
		});
		btn_eliminar.setBounds(587, 72, 154, 25);
		frmFacturacionCircuito.getContentPane().add(btn_eliminar);
		
		JButton btn_modificar = new JButton("Modificar Producto");
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
						JOptionPane.showMessageDialog(null, "Error al modificar producto","Error",JOptionPane.ERROR_MESSAGE);
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
		btn_modificar.setBounds(590, 116, 154, 25);
		frmFacturacionCircuito.getContentPane().add(btn_modificar);
		
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
		btnNewButton.setBounds(875, 312, 86, 75);
		frmFacturacionCircuito.getContentPane().add(btnNewButton);
		cmb_clientes = new JComboBox();
		DefaultComboBoxModel modelo=new DefaultComboBoxModel();
		cmb_clientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		cmb_clientes.setBounds(161, 117, 130, 22);
		frmFacturacionCircuito.getContentPane().add(cmb_clientes);
		JLabel lbl_cliente = new JLabel("Cliente:");
		lbl_cliente.setForeground(Color.WHITE);
		lbl_cliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_cliente.setBounds(55, 123, 94, 16);
		frmFacturacionCircuito.getContentPane().add(lbl_cliente);
		
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
		btnRegistro.setBounds(875, 60, 86, 75);
		frmFacturacionCircuito.getContentPane().add(btnRegistro);
		
		JButton btn_actualizar = new JButton("Actualizar Nuevos Datos Ingresados");
		btn_actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_cod.removeAllItems();
				cmb_clientes.removeAllItems();
				llenar();
			}
		});
		btn_actualizar.setBounds(327, 44, 225, 81);
		frmFacturacionCircuito.getContentPane().add(btn_actualizar);
		
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
		btnNuevoProducto.setBounds(875, 186, 86, 77);
		frmFacturacionCircuito.getContentPane().add(btnNuevoProducto);
		
		JButton btnNuevaFactura = new JButton("Crear Factura");
		btnNuevaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				llenar_NFactura();
				txt_cant.setText("");
				LimpiarTabla((DefaultTableModel) tabla_fact.getModel());
			}
		});
		btnNuevaFactura.setBounds(481, 433, 116, 25);
		frmFacturacionCircuito.getContentPane().add(btnNuevaFactura);
		
		JLabel lblIngresarNuevoCliente = new JLabel("Ingresar Nuevo Cliente");
		lblIngresarNuevoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresarNuevoCliente.setForeground(Color.WHITE);
		lblIngresarNuevoCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngresarNuevoCliente.setBounds(823, 146, 178, 27);
		frmFacturacionCircuito.getContentPane().add(lblIngresarNuevoCliente);
		
		JLabel lblAgregarNuevoProducto = new JLabel("Agregar Nuevo Producto");
		lblAgregarNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarNuevoProducto.setForeground(Color.WHITE);
		lblAgregarNuevoProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAgregarNuevoProducto.setBounds(835, 274, 166, 27);
		frmFacturacionCircuito.getContentPane().add(lblAgregarNuevoProducto);
		
		JLabel lblVistaPrevia = new JLabel("Vista Previa");
		lblVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
		lblVistaPrevia.setForeground(Color.WHITE);
		lblVistaPrevia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVistaPrevia.setBounds(854, 387, 130, 27);
		frmFacturacionCircuito.getContentPane().add(lblVistaPrevia);
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
