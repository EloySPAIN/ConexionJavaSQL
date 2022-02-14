public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conexion.openConnection();
		Conexion.deleteDB();
		Conexion.createDB();
		Conexion.createTableFabricantes();
		Conexion.createTableArticulos();
		Conexion.insertDataFabricantes();
		Conexion.insertDataArticulos();
		Conexion.getValuesFabricantes();
		Conexion.getValuesArticulos();
		Conexion.closeConnection();
	}

}
