
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
    
}
