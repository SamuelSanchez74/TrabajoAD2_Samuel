package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.SQLException;

public interface ClienteDAO {

    /**
     * Busca un cliente por su DNI
     *
     * @param dni el DNI del cliente
     * @return el {@link ClienteEntity} o {@code null} si no existe
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    ClienteEntity findByDNI(String dni) throws SQLException;

    /**
     * Inserta un cliente y actualiza su identificador generado
     *
     * @param cliente el {@link ClienteEntity} a insertar
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    void save(ClienteEntity cliente) throws SQLException;
}
