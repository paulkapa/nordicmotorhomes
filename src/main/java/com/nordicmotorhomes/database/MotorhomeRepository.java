package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Brand;
import com.nordicmotorhomes.model.Modela;
import com.nordicmotorhomes.model.Motorhome;
import com.nordicmotorhomes.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotorhomeRepository implements IObjectRepository<Motorhome> {

    private static Connection conn = DBConnection.getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet result;

    private static IObjectRepository typeRepository = new TypeRepository();
    private static IObjectRepository brandRepository = new BrandRepository();
    private static IObjectRepository modelRepository = new ModelRepository();

    public MotorhomeRepository() {
    }

    @Override
    public ArrayList<Motorhome> readAll(String tableName) {
        ArrayList<Motorhome> motorhomes = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms");
            result = preparedStatement.executeQuery();

            while (result.next()){

                Motorhome motorhome = new Motorhome();
                motorhome.setId(result.getInt("pKey_mtrhmId"));
                Type type = (Type) typeRepository.read("types", "id", String.valueOf(result.getInt("fKey_typeId")));
                motorhome.setType(type.getType());
                Brand brand = (Brand) brandRepository.read("brands", "id", String.valueOf(result.getInt("fKey_brandId")));
                motorhome.setBrand(brand.getBrand());
                Modela model = (Modela) modelRepository.read("models", "id", String.valueOf(result.getInt("fKey_modelId")));
                motorhome.setModel(model.getModel());
                motorhome.setIsAvailable(result.getInt("isAvailable"));
                motorhome.setMaxCapacity(model.getMaxCapacity());
                motorhome.setFuelTankVolume(model.getFuelTankVolume());
                motorhome.setPpd(model.getPpd());
                motorhomes.add(motorhome);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motorhomes;
    }

    @Override
    public Motorhome read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public Motorhome readId(int id) {
        Motorhome motorhome = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms WHERE pKey_mtrhmId = '" + id + "'");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                motorhome = new Motorhome(result.getInt("pKey_mtrhmId"), result.getInt("isAvailable"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motorhome;
    }

    @Override
    public void create(String tableName, Motorhome object) {

        try {

            preparedStatement = conn.prepareStatement("INSERT INTO mtrhms(fKey_typeId, fKey_brandId, fKey_modelId, isAvailable) VALUES(?, ?, ?, ?)") ;
            preparedStatement.setInt(1, readType(object.getType()).getId());
            preparedStatement.setInt(2, readBrand(object.getBrand()).getId());
            preparedStatement.setInt(3, readModel(object).getId());
            preparedStatement.setInt(4, object.getIsAvailable());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(String tableName, Motorhome object) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE mtrhms SET isAvailable = ? WHERE pKey_mtrhmId = ?");

            preparedStatement.setInt(1, object.getIsAvailable());
            preparedStatement.setInt(2, object.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void delete(String tableName, String columnName, String value) {


    }

    private Type readType(String string) {
        Type object = null;

        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM types WHERE type = '"+ string +"'");
            result = preparedStatement.executeQuery();

            if (result.next()){
                object = new Type(result.getInt("pKey_typeId"), string);
            }
            else {
                result = null;
                typeRepository.create("type", new Type(string));

                try {
                    preparedStatement = conn.prepareStatement("SELECT * FROM types WHERE type = '"+ string +"'");
                    result = preparedStatement.executeQuery();

                    if (result.next()){
                        object = new Type(result.getInt("pKey_typeId"), string);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    private Brand readBrand(String string) {
        Brand object = null;

        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM brands WHERE brand = '"+ string +"'");
            result = preparedStatement.executeQuery();

            if (result.next()){
                object = new Brand(result.getInt("pKey_brandId"), string);
            }
            else {
                result = null;
                brandRepository.create("brand", new Brand(string));

                try {
                    preparedStatement = conn.prepareStatement("SELECT * FROM brands WHERE brand = '"+ string +"'");
                    result = preparedStatement.executeQuery();

                    if (result.next()){
                        object = new Brand(result.getInt("pKey_brandId"), string);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    private Modela readModel(Motorhome motorhome) {
        Modela object = null;

        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM models WHERE model = '"+ motorhome.getModel() +"'");
            result = preparedStatement.executeQuery();

            if (result.next()){
                object = new Modela(motorhome.getModel(), motorhome.getMaxCapacity(), motorhome.getFuelTankVolume(), motorhome.getPpd());
            }
            else {
                result = null;

                modelRepository.create("models", new Modela(motorhome.getModel(), motorhome.getMaxCapacity(), motorhome.getFuelTankVolume(), motorhome.getPpd()));

                try {
                    preparedStatement = conn.prepareStatement("SELECT * FROM models WHERE model = '"+ motorhome.getModel() +"'");
                    result = preparedStatement.executeQuery();

                    if (result.next()){
                        object = new Modela(result.getInt("pKey_modelId"), motorhome.getModel(), motorhome.getMaxCapacity(), motorhome.getFuelTankVolume(), motorhome.getPpd());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }
}
