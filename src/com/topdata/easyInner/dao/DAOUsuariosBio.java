/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.dao;

import com.topdata.easyInner.entity.UsuarioBio;
import com.topdata.easyInner.entity.UsuarioSemDigital;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonatas.silva
 */
public class DAOUsuariosBio {
    
    public UsuarioBio ConsultarUsuarioBio(String Cartao) throws IOException, SQLException, FileNotFoundException, ClassNotFoundException
    {
        Statement stm = DAOConexao.ConectarBase().createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM UsuariosBio WHERE Cartao = '" + Cartao + "'");
        UsuarioBio usuario = null;
        if (rs.next())
        {
            usuario = new UsuarioBio();
            usuario.setCodigo(rs.getInt("Codigo"));
            usuario.setCartao(rs.getString("Cartao"));
            usuario.setTemplate1(rs.getString("Template1"));
            usuario.setTemplate2(rs.getString("Template2"));
        }
        return usuario;
    }
            
    public List<UsuarioBio> ConsultarUsuariosBio() throws SQLException
    {
        try
        {
            List<UsuarioBio> ListUsers;
            Statement stm = DAOConexao.ConectarBase().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM UsuariosBio ORDER BY CDbl(Cartao)");
            ListUsers = new ArrayList();
            while (rs.next())
            {
                UsuarioBio user = new UsuarioBio();
                user.setCartao(rs.getString("Cartao"));
                user.setTemplate1(rs.getString("Template1"));
                user.setTemplate2(rs.getString("Template2"));
                ListUsers.add(user);
            }   
            DAOConexao.getConn().close();
            return ListUsers;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return null;
        }
    }
    
    public boolean ExisteUsuarioBio(String Usuario) throws SQLException, IOException, FileNotFoundException, ClassNotFoundException
    {
        Statement stmt = DAOConexao.ConectarBase().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM UsuariosBio WHERE Cartao = '" + Usuario + "'");
        if (rs.next())
        {
            stmt.close();
            DAOConexao.getConn().close();
            return true;
        }
        stmt.close();
        DAOConexao.getConn().close();
        return false;
    }
    
    public boolean InserirUsuarioBio(UsuarioBio user) throws SQLException
    {
        try
        {
            String sql = "INSERT INTO UsuariosBio (Cartao, Template1, Template2) VALUES (?, ?, ?)";
            PreparedStatement stmt = DAOConexao.ConectarBase().prepareStatement(sql);
            stmt.setString(1, user.getCartao());
            stmt.setString(2, user.getTemplate1());
            stmt.setString(3, user.getTemplate2());
            stmt.executeUpdate();
            stmt.close();
            DAOConexao.getConn().close();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return false;
        }
    }
    
    public boolean ExcluirUsuarioBio(String Usuario) throws SQLException
    {
        try
        {
            String Del = "DELETE FROM UsuariosBio Where Cartao = '" + Usuario + "'";
            PreparedStatement stmt = DAOConexao.ConectarBase().prepareStatement(Del);
            stmt.executeUpdate();
            DAOConexao.getConn().close();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return false;
        }
    }
    
    public List<UsuarioSemDigital> ConsultarUsuarioSemDigital() throws SQLException
    {
        try
        {
            List<UsuarioSemDigital> ListUsers;
            Statement stm = DAOConexao.ConectarBase().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ListaSemDigital ORDER BY CDbl(Cartao)");
            ListUsers = new ArrayList();
            while (rs.next())
            {
                UsuarioSemDigital user = new UsuarioSemDigital();
                user.setCartao(rs.getString("Cartao"));
                ListUsers.add(user);
            }   
            stm.close();
            DAOConexao.getConn().close();
            return ListUsers;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return null;
        }
    }
}
