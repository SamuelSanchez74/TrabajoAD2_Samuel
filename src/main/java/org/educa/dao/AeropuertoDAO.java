package org.educa.dao;

import org.educa.entity.AeropuertoEntity;

import java.sql.SQLException;
import java.util.List;

public interface AeropuertoDAO {

    /**
     * Obtiene todos los aeropuertos del sistema
     *
     * @return la lista de {@link AeropuertoEntity}
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    List<AeropuertoEntity> findAll() throws SQLException;
}