/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.light.model;

/**
 *
 * @author Simone
 */
public class ConsumoMes extends javax.swing.JPanel{

    private int id;
    private String data;
    private float medida;
    private float medida_anterior;
    private float kwh_mes;
    private float media;
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
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the medida
     */
    public float getMedida() {
        return medida;
    }

    /**
     * @param medida the medida to set
     */
    public void setMedida(float medida) {
        this.medida = medida;
    }

    /**
     * @return the medida_anterior
     */
    public float getMedida_anterior() {
        return medida_anterior;
    }

    /**
     * @param medida_anterior the medida_anterior to set
     */
    public void setMedida_anterior(float medida_anterior) {
        this.medida_anterior = medida_anterior;
    }

    /**
     * @return the kwh_mes
     */
    public float getKwh_mes() {
        return kwh_mes;
    }

    /**
     * @param kwh_mes the kwh_mes to set
     */
    public void setKwh_mes(float kwh_mes) {
        this.kwh_mes = kwh_mes;
    }

    @Override
    public String toString() {
        return "ConsumoMes{" + "id=" + id + ", data=" + data + ", medida=" + medida + ", medida_anterior=" + medida_anterior + ", kwh_mes=" + kwh_mes + '}';
    }

    /**
     * @return the media
     */
    public float getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(float media) {
        this.media = media;
    }

    
}
