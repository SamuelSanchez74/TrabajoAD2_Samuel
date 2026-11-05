package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReservaDAO {

    /**
     * Obtiene todas las reservas de un cliente
     *
     * @param cliente el {@link ClienteEntity}
     * @return las reservas del cliente ordenadas por fecha
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException;

    /**
     * Comprueba si ya existe una reserva con ese código
     *
     * @param connection la conexión (parte de la transacción en curso)
     * @param codReserva el código a comprobar
     * @return {@code true} si el código ya está en uso
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    boolean existsByCodigo(Connection connection, String codReserva) throws SQLException;

    /**
     * Inserta una reserva y actualiza su identificador generado.
     * La fecha y el estado se rellenan con los valores por defecto de la tabla.
     *
     * @param connection la conexión (parte de la transacción en curso)
     * @param reserva    la {@link ReservaEntity} a insertar
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    void save(Connection connection, ReservaEntity reserva) throws SQLException;
}