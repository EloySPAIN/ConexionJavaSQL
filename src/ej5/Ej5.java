package ej5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
public class Ej5 {
	public static Connection conexion;

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL

		openConnection();
		createDB("dbDirectores");
		createTable("dbDirectores", "directores", "despachos");
		insertData("dbDirectores", "directores", "despachos");
		getValuesEmpleados("dbDirectores", "directores");
		getValuesDepartamentos("dbDirectores", "despachos");
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
	public static void createTable(String db,String directores,String despachos) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+directores+""
						+ "(DNI varchar(8) PRIMARY KEY,"
						+ "NomApels nvarchar(255),"
						+ "DNIjefe varchar(8),"
						+ "Despacho int,"
						+ "CONSTRAINT FK_DNIJefe FOREIGN KEY (DNIjefe) REFERENCES directores(DNI),"
						+ "CONSTRAINT FK_Despacho FOREIGN KEY (Despacho) REFERENCES despachos(numero));";
						
								
				String Query2 = "CREATE TABLE "+despachos+""
						+ "(numero int PRIMARY KEY,"
						+ "capacidad int);";
				
				Statement st= conexion.createStatement();
				//Ejecutamos antes la 2 query, porque en la primera query tenemos la foreign key y si no petara
				st.executeUpdate(Query2);
				st.executeUpdate(Query);
				
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error creando tabla.");
				
			}
	}
			
			//METODO QUE INSERTA DATOS EN TABLAS MYSQL
			public static void insertData(String db,String directores,String despachos) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
					
						//insertamos datos a la tabla directores	
					
					String insert1Dir = "INSERT INTO " + directores + " (DNI,NomApels, DNIjefe, Despacho) VALUE(" 
							+ "\"" + "1234567D" + "\", "
							+ "\"" + "NomApel1" + "\", "
							+ "\"" + "1234567D" + "\", "
							+ "\"" + "1" + "\"); ";
					
					String insert2Dir = "INSERT INTO " + directores + " (DNI,NomApels, DNIjefe, Despacho) VALUE(" 
							+ "\"" + "1134567D" + "\", "
							+ "\"" + "NomApel2" + "\", "
							+ "\"" + "1234567D" + "\", "
							+ "\"" + "2" + "\"); ";
					
					String insert3Dir = "INSERT INTO " + directores + " (DNI,NomApels, DNIjefe, Despacho) VALUE(" 
							+ "\"" + "1114567D" + "\", "
							+ "\"" + "NomApel3" + "\", "
							+ "\"" + "1234567D" + "\", "
							+ "\"" + "3" + "\"); ";
					
					String insert4Dir = "INSERT INTO " + directores + " (DNI,NomApels, DNIjefe, Despacho) VALUE(" 
							+ "\"" + "1111567D" + "\", "
							+ "\"" + "NomApel4" + "\", "
							+ "\"" + "1234567D" + "\", "
							+ "\"" + "4" + "\"); ";
					
					String insert5Dir = "INSERT INTO " + directores + " (DNI,NomApels, DNIjefe, Despacho) VALUE(" 
							+ "\"" + "1111167D" + "\", "
							+ "\"" + "NomApel5" + "\", "
							+ "\"" + "1234567D" + "\", "
							+ "\"" + "5" + "\"); ";
					
					//insertamos datos a la tabla despachos	
					
					String insert1Des = "INSERT INTO " + despachos + " (numero,capacidad) VALUE(" 
							+ "\"" + "1" + "\", "
							+ "\"" + "100" + "\"); ";
					String insert2Des = "INSERT INTO " + despachos + " (numero,capacidad) VALUE(" 
							+ "\"" + "2" + "\", "
							+ "\"" + "200" + "\"); ";
					String insert3Des = "INSERT INTO " + despachos + " (numero,capacidad) VALUE(" 
							+ "\"" + "3" + "\", "
							+ "\"" + "300" + "\"); ";
					String insert4Des = "INSERT INTO " + despachos + " (numero,capacidad) VALUE(" 
							+ "\"" + "4" + "\", "
							+ "\"" + "400" + "\"); ";
					String insert5Des = "INSERT INTO " + despachos + " (numero,capacidad) VALUE(" 
							+ "\"" + "5" + "\", "
							+ "\"" + "500" + "\"); ";
					
					Statement st = conexion.createStatement();
					//ejecutamos los inserts de la tabla despachos
					
					st.executeUpdate(insert1Des);
					st.executeUpdate(insert2Des);
					st.executeUpdate(insert3Des);
					st.executeUpdate(insert4Des);
					st.executeUpdate(insert5Des);
					
					//ejecutamos los inserts de la tabla directores
					
					st.executeUpdate(insert1Dir);
					st.executeUpdate(insert2Dir);
					st.executeUpdate(insert3Dir);
					st.executeUpdate(insert4Dir);
					st.executeUpdate(insert5Dir);
					
					
					
					System.out.println("Datos almacenados correctamente");;
					
				} catch (SQLException ex ) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
				}
							
			}
		
	//METODO QUE OBTIENE VALORES MYSQL
		public static void getValuesEmpleados(String db, String directores) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM " + directores;
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("DNI: " +  resultSet.getString("DNI") + " "
							+ "NomApels: " +  resultSet.getString("NomApels") + " "
							+ "DNIjefe:" + resultSet.getString("DNIjefe") +  " "
							+ "Despacho: " +  resultSet.getString("Despacho") + " "
							);
				}
				
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		
		}
		//METODO QUE OBTIENE VALORES MYSQL
				public static void getValuesDepartamentos(String db, String despachos) {
					try {
						String Querydb = "USE "+db+";";
						Statement stdb= conexion.createStatement();
						stdb.executeUpdate(Querydb);
									
						String Query = "SELECT * FROM " + despachos;
						Statement st = conexion.createStatement();
						java.sql.ResultSet resultSet;
						resultSet = st.executeQuery(Query);
						
						while (resultSet.next()) {
							System.out.println("numero: " +  resultSet.getString("numero") + " "
									+ "capacidad: " +  resultSet.getString("capacidad") + " ");
						}
					} catch (SQLException ex) {
						System.out.println(ex.getMessage());
						System.out.println("Error en la adquisicion de datos");
					}
				
				}
		
		//METODO QUE LIMPIA TABLAS MYSQL
		public static void deleteRecord(String db, String table_name, String ID) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
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
		//METODO QUE ELIMINA TABLAS MYSQL
		public static void deleteTAbla(String db, String table_name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
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

