public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conexion.openConnection();
		Conexion.deleteDB();
		Conexion.createDB();
		Conexion.createTableCientificos();
		Conexion.createTableProyecto();
		Conexion.createTableAsignado();
		Conexion.insertDataCientificos();
		Conexion.insertDataProyecto();
		Conexion.insertDataAsignado();
		Conexion.getValuesCientificos();
		Conexion.getValuesProyecto();
		Conexion.getValuesAsignado();
		Conexion.closeConnection();
	}
}
