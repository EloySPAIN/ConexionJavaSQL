package main;

import clases.Conexion;

public class InvestigadoresMain {

	public static void main(String[] args) {
		Conexion BD = new Conexion();
		BD.enableConnection();
		BD.crearBD("Investigadores");
		BD.crearTabla("Investigadores", "Facultad", "Investigadores", "Equipos", "Reserva");
		BD.crearInserts("Investigadores", "Facultad", "(Codigo, Nombre)", "('1', 'Facultad1')");
		BD.crearInserts("Investigadores", "Investigadores", "(DNI, NomApels, Facultad)", "('12345678P', 'Eloy Altozano', '1')");
		BD.crearInserts("Investigadores", "Equipos", "(NumSerie, Nombre, Facultad)", "('1A1A', 'A1', '1')");
		BD.crearInserts("Investigadores", "Reserva", "(DNI, NumSerie, Comienzo, Fin)", "('12345678P', '1A1A', '2022-01-010', '2022-03-30')");
		BD.closeConnection();

	}

}
