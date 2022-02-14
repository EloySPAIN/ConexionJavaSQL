package Ej8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Ej8 {

	public static Connection conexion;

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL

		openConnection();
		createDB("dbAlmacenes");
		createTable("dbAlmacenes", "cajeros", "productos","venta","maquinas");
		insertData("dbAlmacenes", "cajeros", "productos","venta","maquinas");
		getValuesCajeros("dbAlmacenes", "cajeros");
		getValuesProductos("dbAlmacenes", "productos");
		getValuesVenta("dbAlmacenes", "venta");
		getValuesMaquinas("dbAlmacenes", "maquinas");
		closeConnection();
	}

	// METODO QUE ABRE LA CONEXION CON SERVER MYSQL
	public static void openConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.39:3306?useTimezone=true&serverTimezone=UTC",
					"remote", "Java_2022");// credenciales temporales
			System.out.print("Server Conectado");

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.print("No se ha podido conectar con mi base de datos");
			System.out.println(ex.getMessage());

		}

	}

	// METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
	public static void closeConnection() {
		try {

			conexion.close();
			System.out.print("Server Disconnected");
			fecha();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.print("Error cerrando conexion");
			fecha();
		}
	}

	// METODO QUE CREA LA BASE DE DATOS
	public static void createDB(String db) {
		try {
			String Query = "CREATE DATABASE " + db;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("DB creada con exito!");
			JOptionPane.showMessageDialog(null, "Se ha creado la DB " + db + " de forma exitosa.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la DB.");
		}
	}
	
	// METODO QUE CREA LA TABLA EMPLEADOS MYSQL
	public static void createTable(String db, String cajeros, String productos, String venta, String maquinas) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + cajeros + "" 
			+ "(Codigo int PRIMARY KEY auto_increment, " 
			+ "Nomapels nvarchar(255));";

			String Query2 = "CREATE TABLE " + productos + ""
			+ "(Codigo int PRIMARY KEY auto_increment,"
			+ "Nombre nvarchar(100)," + "Precio int);";
			
			String Query3 = "CREATE TABLE " + venta + "" 
			+ "(Cajero int,"
			+ "Maquina int," 
			+ "Producto int, PRIMARY KEY(Cajero,Maquina,Producto),"
			+ "CONSTRAINT FK_Cajero FOREIGN KEY (Cajero) REFERENCES cajeros (Codigo),"
			+ "CONSTRAINT FK_Producto FOREIGN KEY (Producto) REFERENCES productos (Codigo),"
			+ "CONSTRAINT FK_Maquina FOREIGN KEY (Maquina) REFERENCES maquinas (Codigo));";
			
			String Query4 = "CREATE TABLE " + maquinas + "" 
			+ "(Codigo int PRIMARY KEY auto_increment,"
			+ "Piso int);";

			Statement st = conexion.createStatement();
		
			
			st.executeUpdate(Query);
			st.executeUpdate(Query2);
			st.executeUpdate(Query4);
			st.executeUpdate(Query3);

			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando tabla.");

		}
	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public static void insertData(String db, String cajeros, String productos, String venta, String maquinas) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			// insertamos datos a la tabla cajeros

			String insert1Cajeros = "INSERT INTO " + cajeros + " (Codigo,NomApels) VALUE(" 
					+ "\""+ "1" + "\", " + "\"" + "NomApels1" + "\");";

			String insert2Cajeros = "INSERT INTO " + cajeros + " (Codigo,NomApels) VALUE(" 
					+ "\""+ "2" + "\", " + "\"" + "NomApels2" + "\");";

			String insert3Cajeros = "INSERT INTO " + cajeros + " (Codigo,NomApels) VALUE(" 
					+ "\""+ "3" + "\", " + "\"" + "NomApels3" + "\");";
			
			String insert4Cajeros ="INSERT INTO " + cajeros + " (Codigo,NomApels) VALUE(" 
					+ "\""+ "4" + "\", " + "\"" + "NomApels4" + "\");";

			String insert5Cajeros = "INSERT INTO " + cajeros + " (Codigo,NomApels) VALUE(" 
					+ "\""+ "5" + "\", " + "\"" + "NomApels5" + "\");";

			// insertamos datos a la tabla productos

			String insert1Productos = "INSERT INTO " + productos + " (Codigo,Nombre, Precio) VALUE("
			+ "\"" + "1" + "\", " + "\"" + "nombre1" + "\", " + "\"" + "1000" + "\"); ";
			
			String insert2Productos = "INSERT INTO " + productos + " (Codigo,Nombre, Precio) VALUE("
			+ "\"" + "2" + "\", " + "\"" + "nombre2" + "\", " + "\"" + "2000" + "\"); ";
			
			String insert3Productos = "INSERT INTO " + productos + " (Codigo,Nombre, Precio) VALUE("
			+ "\"" + "3" + "\", " + "\"" + "nombre3" + "\", " + "\"" + "3000" + "\"); ";
			
			String insert4Productos = "INSERT INTO " + productos + " (Codigo,Nombre, Precio) VALUE("
			+ "\"" + "4"+ "\", " + "\"" + "nombre4" + "\", " + "\"" + "4000" + "\"); ";
			
			String insert5Productos = "INSERT INTO " + productos + " (Codigo,Nombre, Precio) VALUE("
			+ "\"" + "5"+ "\", " + "\"" + "nombre5" + "\", " + "\"" + "5000" + "\"); ";
			
			// insertamos datos a la tabla venta
			
			String insert1venta = "INSERT INTO " + venta + " (Cajero,Maquina, Producto) VALUE("
			+ "\"" + "1" + "\", " + "\"" + "1" + "\", " + "\"" + "1" + "\"); ";
			
			String insert2venta = "INSERT INTO " + venta + " (Cajero,Maquina, Producto) VALUE("
			+ "\"" + "2"+ "\", " + "\"" + "2" + "\", " + "\"" + "2" + "\"); ";
			
			String insert3venta = "INSERT INTO " + venta + " (Cajero,Maquina, Producto) VALUE("
			+ "\"" + "3"+ "\", " + "\"" + "3" + "\", " + "\"" + "3" + "\"); ";
			
			String insert4venta = "INSERT INTO " + venta + " (Cajero,Maquina, Producto) VALUE(" 
			+ "\"" + "4"+ "\", " + "\"" + "4" + "\", " + "\"" + "4" + "\"); ";
			
			String insert5venta = "INSERT INTO " + venta + " (Cajero,Maquina, Producto) VALUE("
			+ "\"" + "5"+ "\", " + "\"" + "5" + "\", " + "\"" + "5" + "\"); ";
			
			// insertamos datos a la tabla maquinas
			
			String insert1maquinas = "INSERT INTO " + maquinas + " (Codigo, Piso) VALUE("
					+ "\"" + "1" + "\", " + "\"" + "1" + "\"); ";
					
			String insert2maquinas = "INSERT INTO " + maquinas + " (Codigo, Piso) VALUE("
					+ "\"" + "2"+ "\", " + "\"" + "2" + "\"); ";
					
			String insert3maquinas = "INSERT INTO " + maquinas + " (Codigo, Piso) VALUE("
					+ "\"" + "3"+ "\", " + "\"" + "3" + "\"); ";
					
			String insert4maquinas = "INSERT INTO " + maquinas + " (Codigo, Piso) VALUE(" 
					+ "\"" + "4"+ "\", " + "\"" + "4" + "\"); ";
					
			String insert5maquinas = "INSERT INTO " + maquinas + " (Codigo, Piso) VALUE("
					+ "\"" + "5"+ "\", " + "\"" + "5" + "\"); ";
			
			
			Statement st = conexion.createStatement();
			// ejecutamos los inserts de la tabla cajeros
			st.executeUpdate(insert1Cajeros);
			st.executeUpdate(insert2Cajeros);
			st.executeUpdate(insert3Cajeros);
			st.executeUpdate(insert4Cajeros);
			st.executeUpdate(insert5Cajeros);

			// ejecutamos los inserts de la tabla productos
			st.executeUpdate(insert1Productos);
			st.executeUpdate(insert2Productos);
			st.executeUpdate(insert3Productos);
			st.executeUpdate(insert4Productos);
			st.executeUpdate(insert5Productos);
			
						
			// ejecutamos los inserts de la tabla maquinas
			st.executeUpdate(insert1maquinas);
			st.executeUpdate(insert2maquinas);
			st.executeUpdate(insert3maquinas);
			st.executeUpdate(insert4maquinas);
			st.executeUpdate(insert5maquinas);
			
			
			// ejecutamos los inserts de la tabla venta
			st.executeUpdate(insert1venta);
			st.executeUpdate(insert2venta);
			st.executeUpdate(insert3venta);
			st.executeUpdate(insert4venta);
			st.executeUpdate(insert5venta);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}

	}

	// METODO QUE OBTIENE VALORES MYSQL
	public static void getValuesCajeros(String db, String cajeros) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + cajeros;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			
			while (resultSet.next()) {
				System.out.println("Codigo: " + resultSet.getString("Codigo") + " " 
						+ "NomApels: "+ resultSet.getString("NomApels") + " ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

	}

	// METODO QUE OBTIENE VALORES MYSQL
	public static void getValuesProductos(String db, String productos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + productos;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {
				System.out.println(
						"Codigo: " + resultSet.getString("Codigo") + " " + "Nombre: " + resultSet.getString("Nombre")
								+ " " + "Precio:" + resultSet.getString("Precio") + " ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

	}
	
	public static void getValuesVenta(String db, String venta) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + venta;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {
				System.out.println(
						"Cajero: " + resultSet.getString("Cajero") + " " + "Maquina: " + resultSet.getString("Maquina")
								+ " " + "Producto:" + resultSet.getString("Producto") + " ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

	}
	
	public static void getValuesMaquinas(String db, String maquinas) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + maquinas;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {
				System.out.println(
						"Codigo: " + resultSet.getString("Codigo") + " " + "Piso: " + resultSet.getString("Piso")+ " ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

	}

	// METODO QUE LIMPIA TABLAS MYSQL
	public static void deleteRecord(String db, String table_name, String ID) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Registros de tabla ELIMINADOS con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
		}
	}

	// METODO QUE ELIMINA TABLAS MYSQL
	public static void deleteTAbla(String db, String table_name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "DROP TABLE " + table_name + ";";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("TABLA ELIMINADA con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando la tabla");
		}
	}

	// METODO QUE MUESTRA FECHA
	public static void fecha() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		System.out.println(" - " + hourdateFormat.format(date));
	}

}
