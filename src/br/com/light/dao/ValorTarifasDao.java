/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.light.dao;

import br.com.light.model.ValorTarifas;
import br.com.light.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ValorTarifasDao {

    Conexao conect;

    public ValorTarifasDao() {

        conect = new Conexao();
    }

    public List<ValorTarifas> selectRecord() {

        PreparedStatement ps;
        ResultSet rs;

        List<ValorTarifas> valorTarifas = new LinkedList<>();

        String sql;

        sql = "SELECT * FROM VALOR_TARIFAS";

        try {

            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                ValorTarifas valores = new ValorTarifas();

                valores.setId(Integer.parseInt(rs.getString("ID")));
                valores.setTarifa(rs.getDouble("TARIFA"));
                valores.setTarifa_bVermelha(rs.getDouble("TARIFA_BVERMELHA"));
                valores.setTarifa_Iluminacao(rs.getDouble("TAXA_ILUMINACAO"));

                valorTarifas.add(valores);
            }

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return valorTarifas;

    }
    
    public ValorTarifas buscaTarifaPorId(int id) {
        
        ValorTarifas objValorTarifas = new ValorTarifas();
        PreparedStatement ps;
        ResultSet rs;
 
        String sql;

        sql = "SELECT * FROM VALOR_TARIFAS where id = ?";

        try {

            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                objValorTarifas.setId(Integer.parseInt(rs.getString("ID")));
                objValorTarifas.setTarifa(rs.getDouble("TARIFA"));
                objValorTarifas.setTarifa_bVermelha(rs.getDouble("TARIFA_BVERMELHA"));
                objValorTarifas.setTarifa_Iluminacao(rs.getDouble("TAXA_ILUMINACAO"));

               
            }

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return objValorTarifas;

    }


    public int insertRecord(ValorTarifas valor) {

        PreparedStatement ps;
        int retorno = 0;
        String sql;

        sql = "insert into VALOR_TARIFAS(TARIFA, TARIFA_BVERMELHA, TAXA_ILUMINACAO)"
                + " values(?, ?, ?)";
        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setDouble(1, valor.getTarifa());
            ps.setDouble(2, valor.getTarifa_bVermelha());
            ps.setDouble(3, valor.getTarifa_Iluminacao());

            retorno = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        conect.closeAll();

        return retorno;
    }

    public int updateRecord(ValorTarifas valor) {
        PreparedStatement ps;
        int retorno = 0;
        String sql;

        sql = "update VALOR_TARIFAS set TARIFA = ?, TARIFA_BVERMELHA = ?, TAXA_ILUMINACAO = ? where ID = ?";
        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);

            ps.setDouble(1, valor.getTarifa());
            ps.setDouble(2, valor.getTarifa_bVermelha());
            ps.setDouble(3, valor.getTarifa_Iluminacao());
            ps.setInt(4, valor.getId());

            retorno = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        conect.closeAll();

        return retorno;
    }

    public int deletRecord(int valor_id) {
        PreparedStatement ps;
        int retorno = 0;
        String sql;

        sql = "delete from VALOR_TARIFAS where ID = ?";
        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setInt(1, valor_id);

            retorno = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        conect.closeAll();

        return retorno;


    }

    public int last_IdTarifa_Select() {

        //ValorTarifas valores = new ValorTarifas();
        PreparedStatement ps;
        ResultSet rs;
        int id_tarifa = 0;

        //String sql = "select * from VALOR_TARIFAS where ID = (select MAX(id) from VALOR_TARIFAS)";
        String sql = "select MAX(id) as max from VALOR_TARIFAS ";

        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            rs = ps.executeQuery();
           
            while (rs.next()) {

                //valores.setId(rs.getInt("max"));
                //valores.setTarifa(rs.getDouble("TARIFA"));
                //valores.setTarifa_bVermelha(rs.getDouble("TARIFA_BVERMELHA"));
                //valores.setTarifa_Iluminacao(rs.getDouble("TAXA_ILUMINACAO"));

                id_tarifa = rs.getInt("max");
            }

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return id_tarifa;
    }

    public Double account_calculation(float kwh, int id) {

        PreparedStatement ps;
        ResultSet rs;
        double total_conta = 0;
        String sql = "select * from VALOR_TARIFAS where id = ?";

        try {
            ValorTarifas valores = new ValorTarifas();

            int id_tarifa;
            double tarifa = 0;
            double tarifa_bVermelha = 0;
            double taxa_iluminacao = 0;

            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                valores.setId(Integer.parseInt(rs.getString("ID")));
                valores.setTarifa(rs.getDouble("TARIFA"));
                valores.setTarifa_bVermelha(rs.getDouble("TARIFA_BVERMELHA"));
                valores.setTarifa_Iluminacao(rs.getDouble("TAXA_ILUMINACAO"));

                id_tarifa = rs.getInt("ID");
                tarifa = rs.getDouble("TARIFA");
                tarifa_bVermelha = rs.getDouble("TARIFA_BVERMELHA");
                taxa_iluminacao = rs.getDouble("TAXA_ILUMINACAO");
            }

            /* Calcula o valor da conta de luz */
            total_conta = (kwh * tarifa) + (kwh * tarifa_bVermelha) + taxa_iluminacao;

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução" + e.getLocalizedMessage());
        }

        conect.closeAll();

        return total_conta;

    }
}
