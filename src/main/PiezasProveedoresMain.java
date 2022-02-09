package main;

import clases.Conexion;

public class PiezasProveedoresMain {

	public static void main(String[] args) {
		Conexion BD = new Conexion();
		BD.enableConnection();
		BD.crearBD("Almacen");
		BD.crearTabla("Almacen", "Almacenes", "Cajas");
		BD.crearInserts("Almacen");
		BD.getValuesAlmacen("Almacen", "Almacenes");
		BD.getValuesCaja("Almacen", "Cajas");
		BD.deleteRecord("Almacen", "Cajas", "1");
		BD.delTab("Almacen", "Cajas");
		BD.closeConnection();

	}

}
