package org.educa.service;

import org.educa.dao.AsientoDAO;
import org.educa.dao.AsientoDAOImpl;
import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AsientoService {

    private static final BigDecimal SUPLEMENTO_POR_DEFECTO = new BigDecimal("0.00");

    /**
     * Suplemento que se añade al precio del billete según la clase del asiento
     */
    private static final Map<String, BigDecimal> SUPLEMENTOS = Map.of(
            "TURISTA", new BigDecimal("0.00"),
            "PREMIUM_ECONOMY", new BigDecimal("35.00"),
            "BUSINESS", new BigDecimal("120.00"),
            "FIRST", new BigDecimal("250.00"));

    private final AsientoDAO asientoDAO = new AsientoDAOImpl();

    /**
     * Obtiene los asientos libres del avión del vuelo, con su suplemento según la clase
     *
     * @param vuelo el {@link VueloEntity}
     * @return los asientos que todavía no tienen billete vendido para ese vuelo
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException {
        List<AsientoEntity> asientos = asientoDAO.findDisponibles(vuelo);
        for (AsientoEntity asiento : asientos) {
            asiento.setSuplemento(SUPLEMENTOS.getOrDefault(asiento.getClase(), SUPLEMENTO_POR_DEFECTO));
        }
        return asientos;
    }
}