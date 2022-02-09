package clases;

/**
 * 
 * @author Eloy
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

	public void crearTabla(String db, String tab1, String tab2) {
		try {
			String querydb = "USE " + db + ";";
			Statement cndb = conexion.createStatement();
			cndb.executeUpdate(querydb);
			String qtb1 = "CREATE TABLE if not exists " + tab1 + ""
					+ "(Codigo int primary key auto_increment, Lugar nvarchar(100), Capacidad int);";
			Statement st = conexion.createStatement();
			st.executeUpdate(qtb1);
			String qtb2 = "CREATE TABLE if not exists " + tab2 + ""
					+ "(NumReferencia char(5) primary key, Contenido nvarchar(100), Valor int, Almacen int, "
					+ "CONSTRAINT fk_codigo FOREIGN KEY (Almacen) REFERENCES Almacenes (Codigo));";
			st.executeUpdate(qtb2);
			System.out.println("Tablas creadas");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error al crear la tabla");
		}
	}

	public void crearInserts(String db) {
		PreparedStatement ptst = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Escribe la tabla para inserir los datos:");
		String tabla = scan.nextLine();
		try {
			String querydb = "USE " + db + ";";
			Statement cndb = conexion.createStatement();
			cndb.executeUpdate(querydb);
			if (tabla.equals("Almacenes")) {
				// Insertamos en diferentes variables los values
				Scanner scan2 = new Scanner(System.in);
				System.out.println("Escribe el lugar: ");
				String Lugar = scan2.nextLine();
				System.out.println("Escribe la capacidad: ");
				String Capacidad = scan2.nextLine();
				int cap = Integer.parseInt(Capacidad);

				// Ponemos las variables como values
				String ins = "INSERT INTO Almacen (Lugar, Capacidad) VALUES (?,?);";
				ptst = conexion.prepareStatement(ins);
				ptst.setString(1, Lugar);
				ptst.setInt(2, cap);
				ptst.executeUpdate();
				ptst.close();
				System.out.println("Datos inseridos");
			} else if (tabla.equals("Caja")) {
				// Insertamos en diferentes variables los values
				Scanner scan2 = new Scanner(System.in);
				System.out.println("Escribe el numero de referencia: ");
				String NumReferencia = scan2.nextLine();
				System.out.println("Escribe el contenido: ");
				String Contenido = scan2.nextLine();
				System.out.println("Escribe el valor: ");
				String Valor = scan2.nextLine();
				int Val = Integer.parseInt(Valor);
				System.out.println("Escribe el almacen: ");
				String Almacen = scan2.nextLine();
				int Alm = Integer.parseInt(Almacen);

				// Ponemos las variables como values
				String ins = "INSERT INTO Caja (NumReferencia, Contenido, Valor, Almacen) VALUES (?,?,?,?);";
				ptst = conexion.prepareStatement(ins);
				ptst.setString(1, NumReferencia);
				ptst.setString(2, Contenido);
				ptst.setInt(3, Val);
				ptst.setInt(4, Alm);
				ptst.executeUpdate();
				ptst.close();
				System.out.println("Datos inseridos");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error al crear el insert");
		}
	}

	// Metodo para obtener los registros de la tabla
	public static void getValuesAlmacen(String db, String table_name) {
		try {
			System.out.println("Tabla Almacenes:");
			String Querydb = "USE " + db + ";";
			Statement cndb = conexion.createStatement();
			cndb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + table_name;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			
			while (resultSet.next()) {
				System.out.println("- Codigo: " + resultSet.getString("Codigo") + " " + "- Lugar: "
						+ resultSet.getString("Lugar") + " " + "- Capacidad: " + resultSet.getString("Capacidad")+ " ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

	}

	// Metodo para obtener los registros de la tabla
	public static void getValuesCaja(String db, String table_name) {
		try {
			System.out.println("Tabla Cajas");
			String Querydb = "USE " + db + ";";
			Statement cndb = conexion.createStatement();
			cndb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + table_name;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			
			/**
			 * String qtb2 = "CREATE TABLE if not exists " + tab2 + ""
					+ "(NumReferencia char(5) primary key, Contenido nvarchar(100), Valor int, Almacen int, "
					+ "CONSTRAINT fk_id_nickname FOREIGN KEY (Almacen) REFERENCES Almacenes (Codigo));";
			 */
			while (resultSet.next()) {
				System.out.println("- NumReferencia: " + resultSet.getString("NumReferencia") + " " + "- Contenido: "
						+ resultSet.getString("Contenido") + " " + "- Valor: " + resultSet.getString("Valor") + " "
						+ "- Almacen: " + resultSet.getString("Almacen") + " ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

	}

	// Metodo para borrar registros
	public static void deleteRecord(String db, String table_name, String ID) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "DELETE FROM " + table_name + " WHERE Almacen = \"" + ID + "\";";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Registro eliminado!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al borrar el registro");
		}
	}

	// Metodo para borrar las tablas
	public static void delTab(String db, String table_name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement cndb = conexion.createStatement();
			cndb.executeUpdate(Querydb);

			String Query = "DROP TABLE " + table_name + ";";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Tabla Eliminada!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al borrar la tabla");
		}
	}

}
