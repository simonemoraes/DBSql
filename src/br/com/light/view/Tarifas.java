/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.light.view;

import br.com.light.dao.ConsumoMesDao;
import br.com.light.dao.ValorTarifasDao;
import br.com.light.model.ConsumoMes;
import br.com.light.model.ValorTarifas;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Simone
 */
public class Tarifas {

    public Tarifas() {
    }

    public static void selecinaTarifas() {

        ValorTarifasDao tarifaDao = new ValorTarifasDao();

        List<ValorTarifas> lista = tarifaDao.selectRecord();

        for (ValorTarifas tarifa : lista) {

            System.out.println(tarifa.toString());
        }
    }

    public static void insereTarifas(ValorTarifas obj) {

        ValorTarifasDao valoresDao = new ValorTarifasDao();
      
        int retorno = 0;

        retorno = valoresDao.insertRecord(obj);

        if (retorno != 0) {
            System.out.println("Nova tarifa inserida com sucesso!!");
        } else {
            System.out.println("Valor não inserido!!");
        }
    }
    
    public static void atualizaTarifas(ValorTarifas obj) {

        ValorTarifasDao valoresDao = new ValorTarifasDao();
       

        int retorno = 0;

        retorno = valoresDao.updateRecord(obj);

        if (retorno != 0) {
            System.out.println("Atualização feita com sucesso!!");
        } else {
            System.out.println("Não houve atualização!!");
        }
    }
    
    public  ValorTarifas buscaTarifaPorIdService(int id) {

        ValorTarifasDao valoresDao = new ValorTarifasDao();
       
        ValorTarifas retorno = null;

        retorno = valoresDao.buscaTarifaPorId(id);

       
        return retorno;
    }
    
    public static void deletaTarifas(int id) {

        ValorTarifasDao valoresDao = new ValorTarifasDao();
       
                
        int retorno = 0;
               

        retorno = valoresDao.deletRecord(id);

        if (retorno != 0) {
            System.out.println("Deleção feita com sucesso!!");
        } else {
            System.out.println("Não houve deleção!!");
        }
    }
    
    public static void selecionaUltimo_idTarifa(){
        
        ValorTarifasDao id_tarifaDao = new ValorTarifasDao();
       
        int id = id_tarifaDao.last_IdTarifa_Select();
        
        if(id != 0){
            System.out.println("O Id do ultimo registro é: " + id);
        }else{
            System.out.println("Não houve retorno de valores!");
        }
    }
    /*
    public static void calculoConta(){
        
        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ValorTarifasDao tarifas = new ValorTarifasDao();
        ConsumoMes data1 = new ConsumoMes();
        ConsumoMes data2 = new ConsumoMes();
        
        data1.setData(JOptionPane.showInputDialog("Digite a data inicial"));
        data2.setData(JOptionPane.showInputDialog("Digite a data final"));
        
        float total_kwh = consumoDao.selectTotalKwh(data1, data2);
        int id = tarifas.last_IdTarifa_Select();
        
        
        double valor_conta = tarifas.account_calculation(total_kwh, id);
        
        if(valor_conta != 0){
            System.out.println("O total da conta é: " + valor_conta);
        }else{
            System.out.println("Não houve retorno de valores.");
        }
        
    }
    * */
}
