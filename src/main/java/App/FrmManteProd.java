package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtC?digo;
	JComboBox<String> cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	JComboBox<String> cboProveedores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtC?digo = new JTextField();
		txtC?digo.setBounds(122, 11, 86, 20);
		contentPane.add(txtC?digo);
		txtC?digo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);
		JButton btnBuscar = new JButton("Buscar");

		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				buscar();

			}

		});

		btnBuscar.setBounds(324, 60, 89, 23);

		contentPane.add(btnBuscar);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");

		lblProveedores.setBounds(230, 106, 102, 14);

		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox<String>();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		llenaCombo();
	}

	void llenaCombo() {

		List<Categoria> lstCategorias = em.createQuery("select a from Categoria a", Categoria.class).getResultList();

		cboCategorias.addItem("Seleccione...");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}

		List<Proveedor> proveedores = em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
		cboProveedores.addItem("Seleccione...");
		for (Proveedor p : proveedores) {
			cboProveedores.addItem(p.getNombre_rs());

		}

	}

	void listado() {

		TypedQuery<Producto> consulta = em.createQuery("select pro from Producto pro", Producto.class);
		// Para poder listar un TypedQuery

		// El metodo getResultList es el metodo que nos devuelve el listado de nuestra
		// consulta de tipo typequery

		List<Producto> listadoProductos = consulta.getResultList();

		txtSalida.setText("");

		for (Producto p : listadoProductos) {

			txtSalida.append("************************************************" + "\n");
			txtSalida.append("Id Producto : " + p.getCodigo() + "\n");
			txtSalida.append("Descripcicon: " + p.getDescripcion() + "\n");
			txtSalida.append("Stock    : " + p.getStock() + "\n");
			txtSalida.append("Precio   : " + Double.toString(p.getPrecio()) + "\n");
			txtSalida.append("Id Categoria: " + Integer.toString(p.getIdcategoria()) + "\n");
			txtSalida.append("Categoria: " + p.getCategoria().getDescripcion() + "\n");
			txtSalida.append("Estado   : " + Integer.toString(p.getEstado()) + "\n");
			txtSalida.append("Id Proveedor: " + p.getIdProvedor() + "\n");
			txtSalida.append("Proveedor: " + p.getProveedor().getNombre_rs() + "\n");
		}

		em.close();

	}

	void registrar() {

		// entradas
		String codigo = txtC?digo.getText();
		String descripcion = txtDescripcion.getText();
		int estado = 1;
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int categoria = cboCategorias.getSelectedIndex();
		int proveedor = cboProveedores.getSelectedIndex();

		// Proceso
		Producto p = new Producto();
		p.setIdcategoria(categoria);
		p.setCodigo(codigo);
		p.setDescripcion(descripcion);
		p.setEstado(estado);
		p.setIdProvedor(proveedor);
		p.setPrecio(precio);
		p.setStock(stock);

		em.getTransaction().begin();

		em.persist(p);
		em.getTransaction().commit();
		em.close();

	}

	void buscar() {

		// empezo la transaccion
		em.getTransaction().begin();
		// proceso -- buscar usuario
		Producto p = em.find(Producto.class, txtC?digo.getText());
		// Salida
		txtDescripcion.setText(p.getDescripcion());
		cboCategorias.setSelectedItem(p.getCategoria().getDescripcion());
		txtPrecio.setText(Double.toString(p.getPrecio()));
		txtStock.setText(Integer.toString(p.getStock()));
		cboProveedores.setSelectedItem(p.getProveedor().getNombre_rs());
		// confirmar la transaccion
		em.getTransaction().commit();
		em.close();

	}

}
