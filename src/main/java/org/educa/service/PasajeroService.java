package org.educa.service;

import org.educa.dao.PasajeroDAO;
import org.educa.dao.PasajeroDAOImpl;
import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class PasajeroService {

    private final PasajeroDAO pasajeroDAO = new PasajeroDAOImpl();

    /**
     * Guarda el pasajero dentro de la transacción en curso
     *
     * @param connection la conexión de la transacción
     * @param pasajero   el {@link PasajeroEntity} a guardar
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        pasajeroDAO.save(connection, pasajero);
    }
}
