package trunova.model;

import trunova.build.Autowired;
import trunova.build.Component;

import java.sql.*;

@Component
public class ModelSQLDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/ecology";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection;
    @Autowired
    private FactorySQLDAO factorySQLDAO;

    public ModelSQLDAO() {
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

    public Model getModel(int id){
        Model model = new Model();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM model WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            model.setId(resultSet.getInt("id"));
            model.setMoney(resultSet.getInt("money"));
            model.setCarCount(resultSet.getInt("carCount"));
            model.setRealiseForCar(resultSet.getInt("realiseForCar"));
            model.setRelease(resultSet.getInt("release"));
            model.setSquare(resultSet.getInt("square"));
            model.setFilterCost(resultSet.getInt("filterCost"));
            model.setFilterCount(resultSet.getInt("filterCount"));

            model.setFactories(factorySQLDAO.getFactoryesForModel(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    public void addNewModel(Model model){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("""
                            INSERT INTO public.model(id,
                                                    carCount,
                                                    realiseForCar,
                                                    square,
                                                    money,
                                                    filterCount,
                                                    filterCost,
                                                    release)
                            VALUES (nextval('model_id_seq'), ?, ?, ?, ?, ?, ?, ?);""");

            setStatement(model, preparedStatement);
            for (Factory c: model.getFactories()) {
                factorySQLDAO.addNewFactory(c);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateModel(Model model){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    UPDATE public.model SET
                    carCount=?,
                    realiseForCar=?,
                    square=?,
                    money=?,
                    filterCount=?,
                    filterCost=?,
                    release=? WHERE id=?;""");
            setStatement(model, preparedStatement);
            preparedStatement.setInt(8, model.getId());
            preparedStatement.executeUpdate();
            for (Factory c: model.getFactories()) {
                factorySQLDAO.updateFactory(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStatement(Model model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, model.getFilterCost());
        preparedStatement.setInt(2, model.getRealiseForCar());
        preparedStatement.setInt(3, model.getSquare());
        preparedStatement.setInt(4, model.getMoney());
        preparedStatement.setInt(5, model.getFilterCount());
        preparedStatement.setInt(6, model.getFilterCost());
        preparedStatement.setInt(7, model.getRelease());
    }
}
