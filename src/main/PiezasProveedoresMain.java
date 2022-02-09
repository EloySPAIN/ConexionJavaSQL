package main;

import clases.Conexion;

public class PiezasProveedoresMain {

	public static void main(String[] args) {
		Conexion BD = new Conexion();
		BD.enableConnection();
		BD.crearBD("PyP");
		BD.crearTabla("PyP", "Piezas", "Proveedores", "Suministra");
		BD.crearInserts("PyP", "Piezas", "(Codigo, Nombre)", "('1', 'Pieza1')");
		BD.crearInserts("PyP", "Proveedores", "(Id, Nombre)", "('324', 'Prov1')");
		BD.crearInserts("PyP", "Suministra", "(CodigoPieza, IdProveedor, Precio)", "('1', '324', '100')");
		BD.closeConnection();

	}

}
