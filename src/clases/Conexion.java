package clases;

/**
 * 
 * @author Eloy
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	public static Connection conexion;

	// Metodo para conectarnos a la base de datos
	public void enableConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.18:3306?useTimezone=true&serverTimezone=UTC",
					"remote", "eE12345678");
			System.out.println("Conexion establecida");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("No se puede conectar a la BD");
			System.out.println(e);
		}
	}

	// Metodo para cerrar la sesion de la base de datos
	public void closeConnection() {
		try {
			conexion.close();
			System.out.println("Servidor desconectado");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error al cerrar conexion");
		}
	}

	public void crearBD(String nombre) {
		try {
			String query = "CREATE DATABASE if not exists " + nombre + ";";
			Statement st = conexion.createStatement();
			st.executeUpdate(query);
			System.out.println("Base de datos creado");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error al crear la base de datos");
		}
	}

	public void crearTabla(String db, String tab1, String tab2, String tab3) {
		try {
			String querydb = "USE " + db + ";";
			Statement cndb = conexion.createStatement();
			cndb.executeUpdate(querydb);
			String qtb1 = "CREATE TABLE if not exists " + tab1 + ""
					+ "(Codigo int primary key auto_increment, Nombre nvarchar(100));";
			Statement st = conexion.createStatement();
			st.executeUpdate(qtb1);
			String qtb2 = "CREATE TABLE if not exists " + tab2 + ""
					+ "(ID char(4) primary key, Nombre nvarchar(100));";
			st.executeUpdate(qtb2);
			String qtb3 = "CREATE TABLE if not exists " + tab3 + ""
					+ "(CodigoPieza INT PRIMARY KEY, IdProveedor CHAR(4), Precio INT,"
					+ "KEY CodigoP_idx (CodigoPieza), KEY IdP_idx (IdProveedor),"
					+ "CONSTRAINT CodigoPieza FOREIGN KEY (CodigoPieza) REFERENCES Piezas (Codigo),"
					+ "CONSTRAINT IdProveedor FOREIGN KEY (IdProveedor) REFERENCES Proveedores (ID));";
			st.executeUpdate(qtb3);
			System.out.println("Tablas creadas");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error al crear la tabla");
		}
	}

	public void crearInserts(String db, String tb, String val, String campos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			String Query = "INSERT INTO " + tb + " " + val + " VALUE " + campos;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Insertado correctamente");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar.");
		}
	}
	
	

}
