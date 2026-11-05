package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;

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
}