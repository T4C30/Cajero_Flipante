package me.t4c30;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.sqlite.SQLiteConnection;

public class GestorBD {
    private static final String URL = "base_dato.sqlite3";

    public static void añadirSaldo(Float dinero){
        try (Connection conexion =  DriverManager.getConnection(URL)) {
            Statement stament = conexion.createStatement();
            dinero =+ consultarSaldo();
            stament.executeUpdate("UPDATE Cuentas SET" + dinero);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public static void retirarSaldo(){

    }

    public static Float consultarSaldo(){
        try (Connection conexion =  DriverManager.getConnection(URL)) {
            Statement stament = conexion.createStatement();
            ResultSet resultado = stament.executeQuery("SELECT * FROM Cuentas");
            while (resultado.next()) {
                return resultado.getFloat(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0f;
    }

}
