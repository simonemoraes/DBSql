/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.light.model;

/**
 *
 * @author Simone
 */
public class ValorTarifas {
   
    private int id;
    private double tarifa;
    private double tarifa_bVermelha;
    private double tarifa_Iluminacao;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tarifa
     */
    public double getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return the tarifa_bVermelha
     */
    public double getTarifa_bVermelha() {
        return tarifa_bVermelha;
    }

    /**
     * @param tarifa_bVermelha the tarifa_bVermelha to set
     */
    public void setTarifa_bVermelha(double tarifa_bVermelha) {
        this.tarifa_bVermelha = tarifa_bVermelha;
    }

    /**
     * @return the tarifa_Iluminacao
     */
    public double getTarifa_Iluminacao() {
        return tarifa_Iluminacao;
    }

    /**
     * @param tarifa_Iluminacao the tarifa_Iluminacao to set
     */
    public void setTarifa_Iluminacao(double tarifa_Iluminacao) {
        this.tarifa_Iluminacao = tarifa_Iluminacao;
    }
    
    @Override
    public String toString() {
        return "ValorTarifas{" + "id=" + id + ", tarifa=" + tarifa + ", tarifa_bVermelha=" + tarifa_bVermelha + ", tarifa_Iluminacao=" + tarifa_Iluminacao + '}';
    }
    
}
