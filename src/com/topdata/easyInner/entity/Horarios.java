/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.entity;

/**
 *
 * @author eduardo.santana
 */

/* It class is kind of like an enum, it is used to work with list of horarios*/
public class Horarios 
{    
    
    //Kind of like an enum
    private int codigo;
    private int faixa;
    private int dia;
    private int hora;
    private int minuto;
    private String horario;
    
    
    //Setters and getters
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }
    
    public int getCodigo()
    {
        return this.codigo;
    }

    
    public void setFaixa(int faixa)
    {
        this.faixa = faixa;
    }
        
    public int getFaixa()
    {
        return this.faixa;
    }        
    
    
    public void setDia(int dia)
    {
        this.dia = dia;
    }

    public int getDia()
    {
        return this.dia;
    }
    
    
    public void setHora(int hora)
    {
        this.hora = hora;
    }
    
    public int getHora()
    {
        return this.hora;
    }
    
    
    public void setMinuto(int minuto)
    {
        this.minuto = minuto;
    }
     
    public int getMinuto()
    {
        return this.minuto;
    }
    
    
    public void setHorario(String horario)
    {
        this.horario = horario;
    }
    
    public String getHorario()
    {
        return this.horario;
    }
}
