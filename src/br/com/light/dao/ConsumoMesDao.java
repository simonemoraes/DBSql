package br.com.light.dao;

import br.com.light.model.ConsumoMes;
import br.com.light.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class ConsumoMesDao {

    /* Método para selecionar registro no banco.*/
    public List<ConsumoMes> selectRecord( Conexao conect ) {

        ResultSet rs;
        PreparedStatement ps;
        String sql;

        List<ConsumoMes> listaConsumo = new LinkedList<>();

        sql = "select * from CONSUMO_MES";


        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                ConsumoMes consumoRetornado = new ConsumoMes();

                consumoRetornado.setId(rs.getInt("ID"));
                consumoRetornado.setData(rs.getString("DATA"));
                consumoRetornado.setMedida(rs.getFloat("MEDIDA"));
                consumoRetornado.setMedida_anterior(rs.getFloat("MEDIDA_ANTERIOR"));
                consumoRetornado.setKwh_mes(rs.getFloat("TOTAL_KWH_MES"));

                listaConsumo.add(consumoRetornado);
            }
        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return listaConsumo;
    }

    /* Metodo para selecionar o registro no banco atraves da data.*/
    public ConsumoMes selectRecordDate( ConsumoMes consumo, Conexao conect ) {

        PreparedStatement ps;
        ResultSet rs;
        String sql;
       
        ConsumoMes consumoRetornado = new ConsumoMes();
        
        sql = "select * from CONSUMO_MES where DATA = ?;";

        try {

            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setString(1, consumo.getData());
            rs = ps.executeQuery();

            if (rs.next()) {
                consumoRetornado.setId(rs.getInt("id"));
                consumoRetornado.setData(rs.getString("DATA"));
                consumoRetornado.setMedida(rs.getFloat("MEDIDA"));
                consumoRetornado.setMedida_anterior(rs.getFloat("MEDIDA_ANTERIOR"));
                consumoRetornado.setKwh_mes(rs.getFloat("TOTAL_KWH_MES"));
            }

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return consumoRetornado;
    }

    public ConsumoMes lastInsertSelect( Conexao conect ) {

        PreparedStatement ps;
        ResultSet rs;
        String sql;
        ConsumoMes ultimoRegistro = new ConsumoMes();

        sql = "select top 1 * from CONSUMO_MES order by medida desc";

        try {

            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            //ps.setString(1, consumo.getData());
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoRegistro.setId(rs.getInt("ID"));
                ultimoRegistro.setData(rs.getString("DATA"));
                ultimoRegistro.setMedida(rs.getFloat("MEDIDA"));
                ultimoRegistro.setMedida_anterior(rs.getFloat("MEDIDA_ANTERIOR"));
                ultimoRegistro.setKwh_mes(rs.getFloat("TOTAL_KWH_MES"));
            }

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return ultimoRegistro;
    }

    /* Método para atualizar registro no banco.*/
    public ConsumoMes updateRecord(ConsumoMes consumo, Conexao conect ) {

        PreparedStatement ps = null;
        String sql;
        ResultSet retorno = null;
        ConsumoMes consumoRetornado = new ConsumoMes();

        sql = "update CONSUMO_MES set DATA=?, MEDIDA=? where id=?";

        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setString(1, consumo.getData());
            ps.setFloat(2, consumo.getMedida());
            ps.setInt(3, consumo.getId());

            retorno = ps.executeQuery();

            if (retorno.next()) {

                consumoRetornado.setId(retorno.getInt("id"));
                consumoRetornado.setData(retorno.getString("DATA"));
                consumoRetornado.setMedida(retorno.getFloat("MEDIDA"));
                consumoRetornado.setMedida_anterior(retorno.getFloat("MEDIDA_ANTERIOR"));
                consumoRetornado.setKwh_mes(retorno.getFloat("TOTAL_KWH_MES"));

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        conect.closeAll();
        return consumoRetornado;
    }

    /* Método para deletar registro no banco.*/
    public int deleteRecord(int id_consumo, Conexao conect ) {

        PreparedStatement ps;
        String sql;
        sql = "delete from CONSUMO_MES where id=?";

        int retorno = 0;

        try {

            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setInt(1, id_consumo);

            retorno = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return retorno;
    }

    /* Método para inserir registro no banco atraves de procedure.*/
    public ConsumoMes insertRecord(ConsumoMes consumo, Conexao conect ) {

        PreparedStatement ps = null;
        String sql;
        ResultSet rs = null;
        ConsumoMes consumoRetornado = new ConsumoMes();

        sql = "exec INSERE_KWH ?,?";

        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setString(1, consumo.getData());
            ps.setFloat(2, consumo.getMedida());

            rs = ps.executeQuery();

            if (rs.next()) {

                consumoRetornado.setId(rs.getInt("id"));
                consumoRetornado.setData(rs.getString("DATA"));
                consumoRetornado.setMedida(rs.getFloat("MEDIDA"));
                consumoRetornado.setMedida_anterior(rs.getFloat("MEDIDA_ANTERIOR"));
                consumoRetornado.setKwh_mes(rs.getFloat("TOTAL_KWH_MES"));

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        conect.closeAll();

        return consumoRetornado;
    }

    public float selectMediaKwh(ConsumoMes data1, ConsumoMes data2, Conexao conect ) {

        ResultSet rs;
        PreparedStatement ps;
        String sql;
        float media = 0, kwh;
        int tam = 0;

        List<Float> listaConsumo = new LinkedList<>();

        sql = "select * from CONSUMO_MES where DATA between ? and ?";


        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setString(1, data1.getData());
            ps.setString(2, data2.getData());
            rs = ps.executeQuery();

            while (rs.next()) {

                ConsumoMes consumoRetornado = new ConsumoMes();

                consumoRetornado.setId(rs.getInt("ID"));
                consumoRetornado.setData(rs.getString("DATA"));
                consumoRetornado.setMedida(rs.getFloat("MEDIDA"));
                consumoRetornado.setMedida_anterior(rs.getFloat("MEDIDA_ANTERIOR"));
                consumoRetornado.setKwh_mes(rs.getFloat("TOTAL_KWH_MES"));

                kwh = rs.getFloat("TOTAL_KWH_MES");

                listaConsumo.add(kwh);
            }

            for (Float k : listaConsumo) {
                media = media + k;
            }

            tam = listaConsumo.size();

            media = media / tam;

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return media;
    }
    
    public float selectTotalKwh(ConsumoMes data1, ConsumoMes data2, Conexao conect ) {

        ResultSet rs;
        PreparedStatement ps;
        String sql;
        float total_kwh = 0, kwh;

        List<Float> listaConsumo = new LinkedList<>();

        sql = "select * from CONSUMO_MES where DATA between ? and ?";


        try {
            ps = (PreparedStatement) conect.getPreparedStatement(sql);
            ps.setString(1, data1.getData());
            ps.setString(2, data2.getData());
            rs = ps.executeQuery();

            while (rs.next()) {

                ConsumoMes consumoRetornado = new ConsumoMes();

                consumoRetornado.setId(rs.getInt("ID"));
                consumoRetornado.setData(rs.getString("DATA"));
                consumoRetornado.setMedida(rs.getFloat("MEDIDA"));
                consumoRetornado.setMedida_anterior(rs.getFloat("MEDIDA_ANTERIOR"));
                consumoRetornado.setKwh_mes(rs.getFloat("TOTAL_KWH_MES"));

                kwh = rs.getFloat("TOTAL_KWH_MES");

                listaConsumo.add(kwh);
            }

            for (Float k : listaConsumo) {
                total_kwh = total_kwh + k;
            }

            

        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível encontrar a instrução");
        }

        conect.closeAll();

        return total_kwh;
    }
}
