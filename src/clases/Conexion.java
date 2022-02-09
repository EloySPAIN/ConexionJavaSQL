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
	
	//Metodo para conectarnos a la base de datos
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
	//Metodo para cerrar la sesion de la base de datos
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
					+ "(id_nickname varchar(20) primary key, imagen_perfil varchar(50), nombre varchar(20) NOT NULL, correo varchar(30) not null);";
			Statement st = conexion.createStatement();
			st.executeUpdate(qtb1);
			String qtb2 = "CREATE TABLE if not exists " + tab2 + ""
					+ "(id_mensajes int primary key auto_increment, texto varchar(100) not null, imagenes varchar(50), videos varchar(50), "
					+ "id_nickname varchar(20), CONSTRAINT fk_id_nickname FOREIGN KEY (id_nickname) REFERENCES Usuarios (id_nickname));";
			st.executeUpdate(qtb2);
			System.out.println("Tablas creadas");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error al crear la tabla");
		}
	}

	public void crearInserts(String db) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escribe la tabla para inserir los datos:");
		String tabla = scan.nextLine();
		try {
			String querydb = "USE " + db + ";";
			Statement cndb = conexion.createStatement();
			cndb.executeUpdate(querydb);
			if (tabla.equals("Usuarios")) {
				//Insertamos en diferentes variables los values
				Scanner scan2 = new Scanner(System.in);
				System.out.println("Escribe el id_nickname: ");
				String idnick = scan2.nextLine();
				System.out.println("Pon la imagen: ");
				String img = scan2.nextLine();
				System.out.println("Escribe el nombre: ");
				String nombre = scan2.nextLine();
				System.out.println("Escribe el correo: ");
				String correo = scan2.nextLine();
				Statement st = conexion.createStatement();
				//Ponemos las variables como values
				String ins = "INSERT INTO Usuarios (id_nickname, imagen_perfil, nombre, correo) VALUES (" + "\""
						+ idnick + "\"" + ",\"" + img + "\",\"" + nombre + "\",\"" + correo + "\");";
				st.executeUpdate(ins);
				System.out.println("Datos inseridos");
			} else if (tabla.equals("Tweet")) {
				//Pedimos los datos
				Scanner scan2 = new Scanner(System.in);
				System.out.println("Escribe el texto: ");
				String texto = scan2.nextLine();
				System.out.println("Pon la imagen: ");
				String img = scan2.nextLine();
				System.out.println("Pon video: ");
				String video = scan2.nextLine();
				System.out.println("Escribe el nickname: ");
				String id_nickname = scan2.nextLine();
				PreparedStatement ptst = null;
				Statement st = conexion.createStatement();
				// String ins="INSERT INTO Tweet (texto, imagenes, videos, id_nickname) VALUES
				// ("+"\""+texto+"\""+",\""+img+"\",\""+video+"\",\""+id_nickname+"\");";
				
				String ins = "INSERT INTO Tweet (texto, imagenes, videos, id_nickname) VALUES (?,?,?,?);";
				//Utilizamos el preprareStatement para comprobar si img y video son nulos
				ptst = conexion.prepareStatement(ins);
				ptst.setString(1, texto);
				if (img.equals("")) {
					ptst.setNull(2, java.sql.Types.VARCHAR, img);
				} else {
					ptst.setString(2, img);
				}
				if (video.equals("")) {
					ptst.setNull(3, java.sql.Types.VARCHAR, img);
				} else {
					ptst.setString(3, img);
				}
				ptst.setString(4, id_nickname);
				ptst.executeUpdate();
				ptst.close();
				// st.executeUpdate(ins);
				System.out.println("Datos inseridos");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error al crear el insert");
		}
	}
	
	public static void getValuesUsuarios(String db, String table_name) {
		try {
			System.out.println("Tabla Usuarios:");
			String Querydb = "USE "+db+";";
			Statement cndb= conexion.createStatement();
			cndb.executeUpdate(Querydb);
						
			String Query = "SELECT * FROM " + table_name;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			
			while (resultSet.next()) {
				System.out.println("- ID: " +  resultSet.getString("id_nickname") + " "
						+ "- Imagen de perfil: " +  resultSet.getString("imagen_perfil") + " "
						+ "- Nombre: " +  resultSet.getString("nombre") + " "
						+ "- Correo: " +  resultSet.getString("correo") + " "
						);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}
	
	}
	
	public static void getValuesTweet(String db, String table_name) {
		try {
			System.out.println("Tabla Tweet");
			String Querydb = "USE "+db+";";
			Statement cndb= conexion.createStatement();
			cndb.executeUpdate(Querydb);
						
			String Query = "SELECT * FROM " + table_name;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			
			while (resultSet.next()) {
				System.out.println("- ID: " +  resultSet.getString("id_mensajes") + " "
						+ "- Mensaje: " +  resultSet.getString("texto") + " "
						+ "- Imagenes: " +  resultSet.getString("imagenes") + " "
						+ "- Videos: " +  resultSet.getString("videos") + " "
						+ "- Usuario: " +  resultSet.getString("id_nickname") + " "
						);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}
	
	}
	
	public static void deleteRecord(String db, String table_name, String ID) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "DELETE FROM " + table_name + " WHERE id_nickname = \"" + ID + "\";";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			
			System.out.println("Registro eliminado!");
						
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al borrar el registro");
		}
	}
	
	public static void delTab(String db, String table_name) {
		try {
			String Querydb = "USE "+db+";";
			Statement cndb= conexion.createStatement();
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
