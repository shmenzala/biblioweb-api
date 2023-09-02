/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 *
 * @author shmen
 */
public class StringKeyGenerator implements IdentifierGenerator, Configurable{

    private String sqcName = "";
    private String identificator_id = "";
    
    @Override
    public Object generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        Connection connection = null;
        try {
            connection = ssci.getJdbcConnectionAccess().obtainConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StringKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement ps = null;
        String result = "";

        try {
            // Oracle-specific code to query a sequence
            ps = connection.prepareStatement("SELECT " + sqcName + ".nextval AS VAL_PK FROM dual");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int pk = rs.getInt("VAL_PK");

                // Convert to a String
                result = identificator_id + Integer.toString(pk);
            }
        } catch (SQLException e) {
            throw new HibernateException("No se puede generar la Primary Key.");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new HibernateException("incapaz de cerrar Prepared Statement.");
                }
            }
        }
        return result;
    }
    
    @Override
    public void configure(Type type, Properties prprts, ServiceRegistry sr) throws MappingException {
        sqcName = prprts.getProperty("sqcName");
        identificator_id = prprts.getProperty("identificator_id");
    }

}
