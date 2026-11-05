package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.sql.SQLException;
import java.util.List;

public interface VueloDAO {

    /**
     * Obtiene los vuelos no cancelados que salen de un aeropuerto, con su aeropuerto de destino
     *
     * @param origen el {@link AeropuertoEntity} de origen
     * @return los vuelos ordenados por fecha de salida
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException;
}