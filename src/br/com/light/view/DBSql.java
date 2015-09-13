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

    public DBSql() {
        
        conect = new Conexao();
        
        selecionar( conect );
    }

    public static void main(String[] args) throws SQLException {

        //opcaoAlteracao();
        
        new DBSql();

    }
/*
    public static void opcaoAlteracao() {

        Tarifas tarifa = new Tarifas();
        int selecao = 0;

        do {

            selecao = Integer.parseInt(JOptionPane.showInputDialog("Escolhar a opção desejada:"
                    + "\n\n\n***** CONSUMO *****\n\n"
                    + "\n1 - Selecionar "
                    + "\n2 - Selecionar por data "
                    + "\n3 - Selecionar ultimo registro"
                    + "\n4 - Atualizar"
                    + "\n5 - Inserir"
                    + "\n6 - Deletar"
                    + "\n7 - Media diaria de Kwh por periodo"
                    + "\n8 - Total de Kwh por periodo"
                    + "\n\n\n***** TARIFAS *****\n\n"
                    + "\n9 - Seleciona todas as tarifas"
                    + "\n10- Insere uma nova tarifa"
                    + "\n11- Atualiza tarifa"
                    + "\n12- Deleta tarifa"
                    + "\n13- Seleciona id da ultima tarifa inserida"
                    + "\n14- Calcula conta"
                    + "\n15- sair\n\n"));
            ValorTarifas valorTarifas = new ValorTarifas();

            switch (selecao) {

                case 1:
                    
                    selecionar();
                    break;

                case 2:
                    selecionarPorData();
                    break;

                case 3:
                    selecionarUltimo();
                    break;

                case 4:
                    atualizar();
                    break;

                case 5:
                    inserir();
                    break;

                case 6:
                    deletar();
                    break;

                case 7:
                    selecionarMediaKwh();
                    break;

                case 8:
                    selecionarTotalKwh();
                    break;

                case 9:
                    tarifa.selecinaTarifas();
                    break;

                case 10:
                    valorTarifas.setTarifa(Double.parseDouble(JOptionPane.showInputDialog("Insira um valor para tarifa")));
                    valorTarifas.setTarifa_bVermelha(Double.parseDouble(JOptionPane.showInputDialog("Insira um valor para tarifa BVermelha")));
                    valorTarifas.setTarifa_Iluminacao(Double.parseDouble(JOptionPane.showInputDialog("Insira um valor para taxa de iluminação")));

                    tarifa.insereTarifas(valorTarifas);
                    break;

                case 11:
                    valorTarifas.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira o id do registro a ser atualizado")));

                    ValorTarifas objetoRetornado = tarifa.buscaTarifaPorIdService(valorTarifas.getId());

                    if (objetoRetornado.getTarifa() > 0) {
                        System.out.println("id: " + objetoRetornado.getId());
                        System.out.println("Tarifa: " + objetoRetornado.getTarifa());
                        System.out.println("Vermelha: " + objetoRetornado.getTarifa_bVermelha());
                        System.out.println("Iluminação: " + objetoRetornado.getTarifa_Iluminacao());

                        valorTarifas.setTarifa(Double.parseDouble(JOptionPane.showInputDialog("Insira um valor para"
                                + " atualização da tarifa")));
                        valorTarifas.setTarifa_bVermelha(Double.parseDouble(JOptionPane.showInputDialog("Insira um valor para"
                                + " atualização da tarifa BVermelha")));
                        valorTarifas.setTarifa_Iluminacao(Double.parseDouble(JOptionPane.showInputDialog("Insira um valor para"
                                + " atualização da taxa de iluminação")));
                        tarifa.atualizaTarifas(valorTarifas);
                    } else {

                        JOptionPane.showMessageDialog(null, "Tarifa não encontrada");
                    }
                    break;

                case 12:
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite um id valido"));
                    tarifa.deletaTarifas(id);
                    break;

                case 13:
                    tarifa.selecionaUltimo_idTarifa();
                    break;

                case 14:
                    tarifa.calculoConta();
                    break;

                case 15:
                    System.exit(0);
            }
        } while (selecao != 15);
    }
    * */

    //Método para fazer consulta de registro na base de dados
    public  void selecionar( Conexao conect ) {

        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMesSRV consumoMesSrv = new ConsumoMesSRV();

        List<ConsumoMes> lista = consumoMesSrv.selecionaRegistros( conect );

        for (ConsumoMes consumo : lista) {

            System.out.println(consumo.toString());
        }

    }
/*
    public static void selecionarPorData() {

        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMes consumo = new ConsumoMes();
        //String data = null;

        consumo.setData(JOptionPane.showInputDialog("Digite uma data a ser consultada"));

        ConsumoMes retorno = consumoDao.selectRecordDate(consumo);

        if (retorno != null) {
            System.out.println(retorno);
            System.out.println("Operação efetuada com sucesso!");
        } else {
            System.out.println("Operação não concluida!");
        }

    }

    public static void selecionarUltimo() {

        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMes consumo = new ConsumoMes();

        ConsumoMes retorno = consumoDao.lastInsertSelect(consumo);

        if (retorno != null) {
            System.out.println(retorno);
            System.out.println("Operação efetuada com sucesso!");
        } else {
            System.out.println("Operação não concluida!");
        }

    }

    //Método para alterar registro na base de dados
    public static void atualizar() {

        ConsumoMesDao consumoDao = new ConsumoMesDao();
        ConsumoMes consumo = new ConsumoMes();

        consumo.setData(JOptionPane.showInputDialog("Entre com a data\n"));
        consumo.setMedida(Integer.parseInt(JOptionPane.showInputDialog("Entre com a medida")));
        consumo.setId(Integer.parseInt(JOptionPane.showInputDialog("Entre com o id")));

        ConsumoMes retorno = consumoDao.updateRecord(consumo);

        if (retorno != null) {
            System.out.println(retorno);
            System.out.println("Atualização feita com sucesso!");
        } else {
            System.out.println("Atualização não foi concluida!");
        }
    }

    //Método para inserir registro na base de dados
    public static void inserir() {

        //  Criando um objeto da class ConsumoDao para acessar suas propriedades e metodos.
        ConsumoMesDao consumoDao = new ConsumoMesDao();

        // Criando um objeto da class ConsumoMes para acessar suas propriedades e metodos.
        ConsumoMes consumo = new ConsumoMes();
        ConsumoMes consumoRetornado = new ConsumoMes();

        consumo.setData(JOptionPane.showInputDialog("Entre com a data"));
        consumo.setMedida(Integer.parseInt(JOptionPane.showInputDialog("Entre com a medida")));


        consumoRetornado = consumoDao.insertRecord(consumo);

        System.out.println(consumoRetornado.toString());

    }

    //Método para deletar registro na base de dados
    public static void deletar() {

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
