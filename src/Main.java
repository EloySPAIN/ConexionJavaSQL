public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conexion.openConnection();
		Conexion.deleteDB();
		Conexion.createDB();
		Conexion.createTablePeliculas();
		Conexion.createTableSalas();
		Conexion.insertDataPeliculas();
		Conexion.insertDataSalas();
		Conexion.getValuesPeliculas();
		Conexion.getValuesSalas();
		Conexion.closeConnection();
	}

}
