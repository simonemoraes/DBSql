package br.com.light.view;

import br.com.light.dao.ConsumoMesDao;
import br.com.light.model.ConsumoMes;
import br.com.light.model.ValorTarifas;
import br.com.light.services.ConsumoMesSRV;
import br.com.light.util.Conexao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class DBSql {
    
    private Conexao conect;
//ConsumoMes{id=299, data=2015-09-07, medida=1635.0, medida_anterior=1629.0, kwh_mes=6.0}
    public DBSql() {
        
        conect = new Conexao();
        
        selecionar( conect );
        //selecionarPorData( conect );
        //selecionarUltimoRegistro();
        //atualizarRegistro();
        //inserirRegistro();
        
        
    }

    public static void main(String[] args) throws SQLException {

        //opcaoAlteracao();
        
        new DBSql();

    }


    //Método para fazer consulta de registro na base de dados
    public  void selecionar( Conexao conect ) {

        //ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMesSRV consumoMesSrv = new ConsumoMesSRV();

        List<ConsumoMes> lista = consumoMesSrv.selecionaRegistros( conect );

        for (ConsumoMes consumo : lista) {

            System.out.println(consumo.toString());
        }

    }

    public  void selecionarPorData( Conexao conect ) {

        ConsumoMes registroRetornado = new ConsumoMes();
        ConsumoMesSRV consumoMesSRV = new ConsumoMesSRV();
        
        registroRetornado = consumoMesSRV.selecionaRegistrosPorData( conect );
        
        if (registroRetornado != null) {
            System.out.println(registroRetornado);
            System.out.println("Operação efetuada com sucesso!");
        } else {
            System.out.println("Operação não concluida!");
        }
        
       
    }

    public void selecionarUltimoRegistro() {

        ConsumoMesSRV consumoSRV = new ConsumoMesSRV();
        //ConsumoMes consumo = new ConsumoMes();

        ConsumoMes recebeUltimoRegistro = consumoSRV.selecionaUltimoRegistroInserido( conect );

        if (recebeUltimoRegistro != null) {
            System.out.println(recebeUltimoRegistro);
            System.out.println("Operação efetuada com sucesso!");
        } else {
            System.out.println("Operação não concluida!");
        }

    }

    
    //Método para alterar registro na base de dados
    public  void atualizarRegistro() {

        ConsumoMesSRV consumoSRV = new ConsumoMesSRV();
        
        ConsumoMes retorno = consumoSRV.atualizaRegistro( conect );

        if (retorno != null) {
            System.out.println(retorno);
            System.out.println("Atualização feita com sucesso!");
        } else {
            System.out.println("Atualização não foi concluida!");
        }
    }
   

    //Método para inserir registro na base de dados
    public  void inserirRegistro () {

        ConsumoMesSRV consumoSRV = new ConsumoMesSRV();
        ConsumoMes consumoRetornado = new ConsumoMes();

        consumoRetornado = consumoSRV.insereRegistro( conect );

        System.out.println(consumoRetornado.toString());

    }
/*
    //Método para deletar registro na base de dados
    public static void deletarRegistro () {

        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMes consumo = new ConsumoMes();

        int retorno = consumoDao.deleteRecord(Integer.parseInt(JOptionPane.showInputDialog("Entre com o id")));

        if (retorno != 0) {
            System.out.println("Registro deletado com sucesso!");
        } else {
            System.out.println("Registro não foi deletado!");

        }
    }

    public static void selecionarMediaKwh() {

        ConsumoMesDao consumoDao = new ConsumoMesDao();

        ConsumoMes data1 = new ConsumoMes();
        ConsumoMes data2 = new ConsumoMes();

        data1.setData(JOptionPane.showInputDialog("Digite a data inicial"));
        data2.setData(JOptionPane.showInputDialog("Digite a data final"));

        Float media = consumoDao.selectMediaKwh(data1, data2);

        String dt1 = data1.getData();
        String dt2 = data2.getData();

        JOptionPane.showMessageDialog(null, "A media no Periodo de "
                + dt1 + " até " + dt2 + " é " + media + " Kwh diário.");

    }

    public static void selecionarTotalKwh() {

        ConsumoMesDao consumoDao = new ConsumoMesDao();

        ConsumoMes data1 = new ConsumoMes();
        ConsumoMes data2 = new ConsumoMes();

        data1.setData(JOptionPane.showInputDialog("Digite a data inicial"));
        data2.setData(JOptionPane.showInputDialog("Digite a data final"));

        Float total_kwh = consumoDao.selectTotalKwh(data1, data2);

        String dt1 = data1.getData();
        String dt2 = data2.getData();


        JOptionPane.showMessageDialog(null, "O total de Kwh no periodo de "
                + dt1 + " até " + dt2 + " é " + total_kwh);

    }
*/
}
