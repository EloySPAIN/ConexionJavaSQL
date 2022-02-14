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
				String Query="CREATE DATABASE if not exists losCientificos";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("DB creada con exito!");
				
			JOptionPane.showMessageDialog(null, "Se ha creado la DB losCientificos de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error creando la DB.");
			}	
		}
		
		//METODO QUE ELIMINA LA BASE DE DATOS
		public static void deleteDB() {
			try {
				String Query="DROP DATABASE losCientificos";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("\nDB eliminada con exito!");
				
			JOptionPane.showMessageDialog(null, "Se ha eliminado la DB losCientificos de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error eliminando la DB.");
			}	
		}
		
		//METODO QUE CREA TABLAS MYSQL
		public static void createTableCientificos() {
			try {
				String Querydb = "USE losCientificos;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE Cientificos"
						+ "(DNI VARCHAR(8) PRIMARY KEY, Nombre VARCHAR(255))";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
		
		public static void createTableProyecto() {
			try {
				String Querydb = "USE losCientificos;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE Proyecto"
						+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(100), Horas int)";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
		
		public static void createTableAsignado() {
			try {
				String Querydb = "USE losCientificos;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE Asignado"
						+ "(Cientifico varchar(8), Proyecto int, "
						+ "primary key(Cientifico, Proyecto), "
						+ "constraint FK_Cientifico foreign key (Cientifico) references Cientificos(DNI),"
						+ "constraint FK_Proyecto foreign key (Proyecto) references Proyecto(Codigo))";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertDataCientificos() {
			try {
				String Querydb = "USE losCientificos;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Cientificos (DNI,Nombre) VALUES" 
						+"(\"1111111A\",\"nombre1\"),(\"1111112A\",\"nombre2\"),(\"1111113A\",\"nombre3\"),(\"1111114A\",\"nombre4\"),(\"1111115A\",\"nombre5\")";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
				public static void insertDataProyecto() {
					try {
						String Querydb = "USE losCientificos;";
						Statement stdb= conexion.createStatement();
						stdb.executeUpdate(Querydb);
									
						String Query = "INSERT INTO Proyecto (Nombre,Horas) VALUES" 
								+"(\"nombre1\",1),(\"nombre2\",2),(\"nombre3\",3),(\"nombre4\",4),(\"nombre5\",5)";
						Statement st = conexion.createStatement();
						st.executeUpdate(Query);
						
						System.out.println("Datos almacenados correctamente");
						
					} catch (SQLException ex ) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
					}			
				}
				
				public static void insertDataAsignado() {
					try {
						String Querydb = "USE losCientificos;";
						Statement stdb= conexion.createStatement();
						stdb.executeUpdate(Querydb);
									
						String Query = "INSERT INTO Asignado (Cientifico,Proyecto) VALUES" 
								+"(\"1111111A\",1),(\"1111112A\",2),(\"1111113A\",3),(\"1111114A\",4),(\"1111115A\",5)";
						Statement st = conexion.createStatement();
						st.executeUpdate(Query);
						
						System.out.println("Datos almacenados correctamente");
						
					} catch (SQLException ex ) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
					}			
				}
		
		//METODO QUE OBTIENE VALORES MYSQL
		public static void getValuesCientificos() {
			try {
				String Querydb = "USE losCientificos;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM Cientificos";
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("DNI: " +  resultSet.getString("DNI") + " "
							+ "Nombre: " +  resultSet.getString("Nombre")
							);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		}
		
		public static void getValuesProyecto() {
			try {
				String Querydb = "USE losCientificos;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM Proyecto";
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("Codigo: " +  resultSet.getString("Codigo") + " "
							+ "Nombre: " +  resultSet.getString("Nombre") + " "
							+ "Horas: " +  resultSet.getString("Horas")
							);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		}
		
		public static void getValuesAsignado() {
			try {
				String Querydb = "USE losCientificos;";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM Asignado";
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("Cientifico: " +  resultSet.getString("Cientifico") + " "
							+ "Proyecto: " +  resultSet.getString("Proyecto")
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
				String Querydb = "USE losCientificos;";
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
				String Querydb = "USE losCientificos;";
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
