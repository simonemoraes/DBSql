
package br.com.light.services;

import br.com.light.dao.ConsumoMesDao;
import br.com.light.model.ConsumoMes;
import br.com.light.util.Conexao;
import java.util.List;

public class ConsumoMesSRV {
    
    public List<ConsumoMes> selecionaRegistros ( Conexao con ) {
       
        ConsumoMesDao consumoDao = new ConsumoMesDao();
        List<ConsumoMes> registroRetornado = consumoDao.selectRecord( con );
        
         return registroRetornado;
    }
    
}
