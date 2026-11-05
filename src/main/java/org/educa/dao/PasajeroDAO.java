package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface PasajeroDAO {

    /**
     * Inserta un pasajero y actualiza su identificador generado
     *
     * @param connection la conexión (parte de la transacción en curso)
     * @param pasajero   el {@link PasajeroEntity} a insertar (con su idReserva ya informado)
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    void save(Connection connection, PasajeroEntity pasajero) throws SQLException;
}
