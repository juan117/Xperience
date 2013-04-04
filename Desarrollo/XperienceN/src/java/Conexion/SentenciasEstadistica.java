package Conexion;

public class SentenciasEstadistica {
    private static String insertRegistroAcceso ="INSERT INTO mestadisticas_acceso (id_usu) values(";
    private static String insertRegistroSalida ="INSERT INTO mestadisticas_salida (id_usu) values (";
    public static String getSentenciaRegistroAcceso(int idusu){
        return insertRegistroAcceso+String.valueOf(idusu) +")";
    }
    public static String getSentenciaRegistroSalida(int idusu){
        return insertRegistroSalida+String.valueOf(idusu) +")";
    }
}
