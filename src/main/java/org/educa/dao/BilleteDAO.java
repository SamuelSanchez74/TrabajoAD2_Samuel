package org.educa.dao;

import org.educa.entity.BilleteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface BilleteDAO {

    /**
     * Inserta un billete y actualiza su identificador generado
     *
     * @param connection la conexión (parte de la transacción en curso)
     * @param billete    el {@link BilleteEntity} a insertar (con el pasajero ya guardado)
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    void save(Connection connection, BilleteEntity billete) throws SQLException;
}
