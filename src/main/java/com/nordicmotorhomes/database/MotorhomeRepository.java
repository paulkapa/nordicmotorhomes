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

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private IObjectRepository typeRepository;
    private IObjectRepository brandRepository;
    private IObjectRepository modelRepository;

    public MotorhomeRepository() {
        this.conn = DBConnection.getConnection();
        this.typeRepository = new TypeRepository();
        this.brandRepository = new BrandRepository();
        this.modelRepository = new ModelRepository();
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
                motorhome.setIsAvailable(1);
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
    public void create(String tableName, Motorhome object) {

    }

    @Override
    public void update(String tableName, Motorhome object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
