package trunova.model;

import trunova.build.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FactorySQLDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/ecology";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection;

    public FactorySQLDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Factory> getFactoryesForModel(int  modelId){
        List<Factory> cities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM factory WHERE model_id = ?;");
            preparedStatement.setInt(1, modelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Factory factory = new Factory();
                factory.setId(resultSet.getInt("id"));
                factory.setPosX(resultSet.getInt("PosX"));
                factory.setPosY(resultSet.getInt("PosY"));
                factory.setMaxRelease(resultSet.getInt("maxRelease"));
                factory.setTax(resultSet.getInt("tax"));
                cities.add(factory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public void addNewFactory(Factory factory){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("""
                            INSERT INTO public.factory(id,
                                                    PosX,
                                                    PosY,
                                                    maxRelease,
                                                    tax,
                                                    model_id)
                            VALUES (nextval('factory_id_seq'), ?, ?, ?, ?, ?);""");

            setStatement(factory, preparedStatement);

            PreparedStatement seqStat = connection.prepareStatement("SELECT last_value FROM model_id_seq;");
            ResultSet resultSet = seqStat.executeQuery();
            resultSet.next();
            int nextVal = resultSet.getInt("last_value");
            preparedStatement.setInt(5, nextVal);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateFactory(Factory factory){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    UPDATE public.factory SET
                    PosX=?,
                    PosY=?,
                    maxRelease=?,
                    tax=? WHERE id=?;""");
            setStatement(factory, preparedStatement);
            preparedStatement.setInt(5, factory.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStatement(Factory factory, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, factory.getPosX());
        preparedStatement.setInt(2, factory.getPosY());
        preparedStatement.setInt(3, factory.getMaxRelease());
        preparedStatement.setInt(4, factory.getTax());

    }
}
