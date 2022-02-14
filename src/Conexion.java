import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Conexion {

	public static Connection conexion;
	
	//METODO QUE ABRE LA CONEXION CON SERVER MYSQL
		public static void openConnection() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion=DriverManager.getConnection("jdbc:mysql://192.168.0.30:3306?useTimezone=true&serverTimezone=UTC","remote","adaw");//credenciales temporales
				System.out.print("Server Connected");
				
			}catch(SQLException | ClassNotFoundException ex  ){
				System.out.print("No se ha podido conectar con mi base de datos");
				System.out.println(ex.getMessage());
				
			}
			
		}
			
		//METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
		public static void closeConnection() {
			try {
		
				conexion.close();
				System.out.print("Server Disconnected");
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.print("Error cerrando conexion");
			}
		}
		
		//METODO QUE CREA LA BASE DE DATOS
		public static void createDB() {
			try {
				String Query="CREATE DATABASE if not exists tiendainformatica";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("DB creada con exito!");
				
			JOptionPane.showMessageDialog(null, "Se ha creado la DB tiendainformatica de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error creando la DB.");
			}	
		}
		
		//METODO QUE ELIMINA LA BASE DE DATOS
		public static void deleteDB() {
			try {
				String Query="DROP DATABASE tiendainformatica";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("\nDB eliminada con exito!");
				
			JOptionPane.showMessageDialog(null, "Se ha eliminado la DB tiendainformatica de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error eliminando la DB.");
			}	
		}
		
		//METODO QUE CREA TABLAS MYSQL
		public static void createTableFabricantes() {
			try {
				String Querydb = "USE tiendainformatica;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE fabricantes"
						+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(100))";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
		
		public static void createTableArticulos() {
			try {
				String Querydb = "USE tiendainformatica;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE articulos"
						+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(100), Precio int, Fabricante int,"
						+"constraint FK_farbricante_articulo foreign key (Fabricante) references fabricantes(Codigo))";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertDataFabricantes() {
			try {
				String Querydb = "USE tiendainformatica;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO fabricantes (Nombre) VALUES" 
						+"(\"nombre1\"),(\"nombre2\"),(\"nombre3\"),(\"nombre4\"),(\"nombre5\")";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
				public static void insertDataArticulos() {
					try {
						String Querydb = "USE tiendainformatica;";
						Statement stdb= conexion.createStatement();
						stdb.executeUpdate(Querydb);
									
						String Query = "INSERT INTO articulos (Nombre,Precio,Fabricante) VALUES" 
								+"(\"nombre1\",10,1),(\"nombre2\",20,2),(\"nombre3\",30,3),(\"nombre4\",40,4),(\"nombre5\",50,5)";
						Statement st = conexion.createStatement();
						st.executeUpdate(Query);
						
						System.out.println("Datos almacenados correctamente");
						
					} catch (SQLException ex ) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
					}			
				}
		
		//METODO QUE OBTIENE VALORES MYSQL
		public static void getValuesFabricantes() {
			try {
				String Querydb = "USE tiendainformatica;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM fabricantes";
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("Codigo: " +  resultSet.getString("Codigo") + " "
							+ "Nombre: " +  resultSet.getString("Nombre")
							);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		}
		
		public static void getValuesArticulos() {
			try {
				String Querydb = "USE tiendainformatica;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM articulos";
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("Codigo: " +  resultSet.getString("Codigo") + " "
							+ "Nombre: " +  resultSet.getString("Nombre") + " "
							+ "Precio: " +  resultSet.getString("Precio") + " "
							+ "Fabricante: " +  resultSet.getString("Fabricante")
							);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		}
		
		//METODO QUE LIMPIA TABLAS MYSQL
		public static void deleteRecord(String table_name, String ID) {
			try {
				String Querydb = "USE tiendainformatica;";
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
		public static void deleteTAbla(String table_name) {
			try {
				String Querydb = "USE tiendainformatica;";
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
		
	
}
