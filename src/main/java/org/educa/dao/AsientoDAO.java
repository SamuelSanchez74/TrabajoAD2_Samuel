package org.educa.dao;

import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.SQLException;
import java.util.List;

public interface AsientoDAO {

    /**
     * Obtiene los asientos del avión de un vuelo que todavía no tienen billete vendido para ese vuelo.
     * El suplemento no se rellena: es una regla de negocio que calcula el servicio.
     *
     * @param vuelo el {@link VueloEntity}
     * @return los asientos libres
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException;
}
