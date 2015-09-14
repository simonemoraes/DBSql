
package br.com.light.services;

import br.com.light.dao.ConsumoMesDao;
import br.com.light.model.ConsumoMes;
import br.com.light.util.Conexao;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsumoMesSRV {
    
    public List<ConsumoMes> selecionaRegistros ( Conexao con ) {
       
        ConsumoMesDao consumoDao = new ConsumoMesDao();
        List<ConsumoMes> registroRetornado = consumoDao.selectRecord( con );
        
         return registroRetornado;
    }
    
    public ConsumoMes selecionaRegistrosPorData ( Conexao conect ){
        
        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMes consumo = new ConsumoMes();
        
        consumo.setData(JOptionPane.showInputDialog("Digite uma data a ser consultada"));

        consumo = consumoDao.selectRecordDate( consumo, conect );
       
        return consumo;
    }
    
    public ConsumoMes selecionaUltimoRegistroInserido ( Conexao conect ){
        
        ConsumoMes recebeUltimoRegistro = new ConsumoMes();
        ConsumoMesDao consumoDao = new ConsumoMesDao();
        
        recebeUltimoRegistro = consumoDao.lastInsertSelect( conect);
        
        return recebeUltimoRegistro;
    }
    
    public ConsumoMes atualizaRegistro ( Conexao conect ){
        
        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMes consumo = new ConsumoMes();
        
        consumo.setData(JOptionPane.showInputDialog("Entre com a data\n"));
        consumo.setMedida(Integer.parseInt(JOptionPane.showInputDialog("Entre com a medida")));
        consumo.setId(Integer.parseInt(JOptionPane.showInputDialog("Entre com o id")));
        
        consumo = consumoDao.updateRecord( consumo, conect );
        
        return consumo;
    }
    
    public ConsumoMes insereRegistro ( Conexao conect ) {
        
        //  Criando um objeto da class ConsumoDao para acessar suas propriedades e metodos.
        ConsumoMesDao consumoDao = new ConsumoMesDao();
        // Criando um objeto da class ConsumoMes para acessar suas propriedades e metodos.
        ConsumoMes consumo = new ConsumoMes();

        consumo.setData(JOptionPane.showInputDialog("Entre com a data"));
        consumo.setMedida(Integer.parseInt(JOptionPane.showInputDialog("Entre com a medida")));
        
        consumo = consumoDao.insertRecord( consumo, conect );
        
        return consumo;
    }
    
    public int deletaRegistro ( Conexao conect ) {
        
        return 0;
    }
    
    public int selecionaMediaKwh ( Conexao conect ) {
        
        return 0;
    }
    
    public float selecionaTotalKwh ( Conexao conect ) {
        
        return 0;
    } 
    
}
