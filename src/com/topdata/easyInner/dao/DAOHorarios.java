/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.dao;

import com.topdata.easyInner.entity.Horarios;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo.santana
 */
public class DAOHorarios 
{
    public static List<Horarios> consultarHorarios() throws IOException, FileNotFoundException, ClassNotFoundException, SQLException            
    {
        //List "Horarios"(schedules) from ListaOffLine Table
        List<Horarios> listHours = listHours = new ArrayList<>();;

        try
        {
            
            String query = "SELECT * FROM ListaHorarios";
            Statement stm = DAOConexao.ConectarBase().createStatement(); 
            
            //Executing query
            ResultSet rsReader = stm.executeQuery(query);
        
            //Making a list of hours
            while(rsReader.next())
            {
                Horarios hours = new Horarios();
                hours.setCodigo(rsReader.getInt("Codigo"));
                hours.setFaixa(rsReader.getInt("Faixa"));
                hours.setDia(rsReader.getInt("Dia"));   
                hours.setHora(rsReader.getInt("Hora"));
                hours.setMinuto(rsReader.getInt("Minuto"));
                hours.setHorario(rsReader.getString("Horario"));
                
                listHours.add(hours);
            }
            
            DAOConexao.getConn().close();
            return listHours;
        }
        catch(IOException | ClassNotFoundException | SQLException ex)
        {
            System.out.println(ex);
            return null;
        }        
    }
    
}
