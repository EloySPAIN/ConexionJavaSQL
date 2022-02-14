package Ej2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Ej2 {

	public static Connection conexion;

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL

		openConnection();
		createDB("dbEmpleados");
		createTable("dbEmpleados", "empleados", "departamentos");
		insertData("dbEmpleados", "empleados", "departamentos");
		getValuesEmpleados("dbEmpleados", "empleados");
		getValuesDepartamentos("dbEmpleados", "departamentos");
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
	public static void createTable(String db,String empleados,String departamentos) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+empleados+""
						+ "(DNI varchar(8) PRIMARY KEY, "
						+ "Nombre nvarchar(100),"
						+ "Apellidos nvarchar(255),"
						+ "Departamento int,"
						+ "CONSTRAINT FK_Departamentos FOREIGN KEY (Departamento) REFERENCES departamentos (Codigo));";
						
								
				String Query2 = "CREATE TABLE "+departamentos+""
						+ "(Codigo int PRIMARY KEY auto_increment,"
						+ "Nombre nvarchar(100),"
						+ "Presupuesto nvarchar(255));";
				
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
			public static void insertData(String db,String empleados,String departamentos) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
					//insertamos datos a la tabla empleados	
	
					String insert1E = "INSERT INTO " + empleados + " (DNI,Nombre, Apellidos, Departamento) VALUE(" 
							+ "\"" + "1234567A" + "\", "
							+ "\"" + "nombre1" + "\", "
							+ "\"" + "apellido1" + "\", "
							+ "\"" + "1" + "\"); ";
					
					String insert2E = "INSERT INTO " + empleados + " (DNI,Nombre, Apellidos, Departamento) VALUE(" 
							+ "\"" + "1234567B" + "\", "
							+ "\"" + "nombre2" + "\", "
							+ "\"" + "apellido2" + "\", "
							+ "\"" + "2" + "\"); ";
					
					String insert3E = "INSERT INTO " + empleados + " (DNI,Nombre, Apellidos, Departamento) VALUE(" 
							+ "\"" + "1234567C" + "\", "
							+ "\"" + "nombre3" + "\", "
							+ "\"" + "apellido3" + "\", "
							+ "\"" + "3" + "\"); ";
					
					String insert4E = "INSERT INTO " + empleados + " (DNI,Nombre, Apellidos, Departamento) VALUE(" 
							+ "\"" + "1234567D" + "\", "
							+ "\"" + "nombre4" + "\", "
							+ "\"" + "apellido4" + "\", "
							+ "\"" + "4" + "\"); ";
					
					String insert5E = "INSERT INTO " + empleados + " (DNI,Nombre, Apellidos, Departamento) VALUE(" 
							+ "\"" + "1234567E" + "\", "
							+ "\"" + "nombre5" + "\", "
							+ "\"" + "apellido5" + "\", "
							+ "\"" + "5" + "\"); ";
					
					//insertamos datos a la tabla departamentos	
					
					String insert1D = "INSERT INTO " + departamentos + " (Codigo,Nombre, Presupuesto) VALUE(" 
							+ "\"" + "1" + "\", "
							+ "\"" + "nombre1" + "\", "
							+ "\"" + "presupuesto1" + "\"); ";
					String insert2D = "INSERT INTO " + departamentos + " (Codigo,Nombre, Presupuesto) VALUE(" 
							+ "\"" + "2" + "\", "
							+ "\"" + "nombre2" + "\", "
							+ "\"" + "presupuesto2" + "\"); ";
					String insert3D = "INSERT INTO " + departamentos + " (Codigo,Nombre, Presupuesto) VALUE(" 
							+ "\"" + "3" + "\", "
							+ "\"" + "nombre3" + "\", "
							+ "\"" + "presupuesto3" + "\"); ";
					String insert4D = "INSERT INTO " + departamentos + " (Codigo,Nombre, Presupuesto) VALUE(" 
							+ "\"" + "4" + "\", "
							+ "\"" + "nombre4" + "\", "
							+ "\"" + "presupuesto4" + "\"); ";
					String insert5D = "INSERT INTO " + departamentos + " (Codigo,Nombre, Presupuesto) VALUE(" 
							+ "\"" + "5" + "\", "
							+ "\"" + "nombre5" + "\", "
							+ "\"" + "presupuesto5" + "\"); ";
					
					Statement st = conexion.createStatement();
					//ejecutamos los inserts de la tabla departamentos
					st.executeUpdate(insert1D);
					st.executeUpdate(insert2D);
					st.executeUpdate(insert3D);
					st.executeUpdate(insert4D);
					st.executeUpdate(insert5D);
					
					//ejecutamos los inserts de la tabla empleados
					st.executeUpdate(insert1E);
					st.executeUpdate(insert2E);
					st.executeUpdate(insert3E);
					st.executeUpdate(insert4E);
					st.executeUpdate(insert5E);
					
					
					
					System.out.println("Datos almacenados correctamente");;
					
				} catch (SQLException ex ) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
				}
							
			}
		
	//METODO QUE OBTIENE VALORES MYSQL
		public static void getValuesEmpleados(String db, String empleados) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM " + empleados;
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("DNI: " +  resultSet.getString("DNI") + " "
							+ "Nombre: " +  resultSet.getString("Nombre") + " "
							+ "Apellidos:" + resultSet.getString("Apellidos") +  " "
							+ "Departamento: " +  resultSet.getString("Departamento") + " "
							);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		
		}
		//METODO QUE OBTIENE VALORES MYSQL
				public static void getValuesDepartamentos(String db, String departamentos) {
					try {
						String Querydb = "USE "+db+";";
						Statement stdb= conexion.createStatement();
						stdb.executeUpdate(Querydb);
									
						String Query = "SELECT * FROM " + departamentos;
						Statement st = conexion.createStatement();
						java.sql.ResultSet resultSet;
						resultSet = st.executeQuery(Query);
						
						while (resultSet.next()) {
							System.out.println("Codigo: " +  resultSet.getString("Codigo") + " "
									+ "Nombre: " +  resultSet.getString("Nombre") + " "
									+ "Presupuesto:" + resultSet.getString("Presupuesto") +  " "
									);
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
