package by.pvt.jdbc.service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.pvt.jdbc.model.Brand;
import by.pvt.jdbc.model.Car;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class CarService {

	private static final Logger LOG = Logger.getLogger(CarService.class);

	private static CarService service;

	private static final Object monitor = new Object();

	private static final String SELECT_ALL = "select * from Webapp.cars JOIN Webapp.brands ON cars.BrandId = brands.id;";
    public static final String INSERT = "INSERT INTO \"Webapp\".cars "
            + "(BrandId, name, nodel, color) VALUES (?,?,?)";

	private CarService() {
	}

	public static CarService getInstance() {

		synchronized (monitor) {
			if (service == null) {
				service = new CarService();
			}
		}

		return service;
	}

	public List<Car> getCarList() {

		List<Car> result = new ArrayList<Car>();

		try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {

			ResultSet executeQuery = stmt.executeQuery(SELECT_ALL);

			result = convertResult(executeQuery);

			executeQuery.close();

		} catch (SQLException e) {
			LOG.error("Something went wrong", e);
		}

		return result;
	}

	private List<Car> convertResult(ResultSet executeQuery) throws SQLException {
		List<Car> result = new ArrayList<Car>();
		while (executeQuery.next()) {
			mapRowToCar(executeQuery, result);
		}
		return result;
	}

	private void mapRowToCar(ResultSet executeQuery, List<Car> result) throws SQLException {
		Car car = new Car();
		car.setId(executeQuery.getInt(1));
		car.setModel(executeQuery.getString(2));
		car.setColor(executeQuery.getString(3));
		String brandName = executeQuery.getString(7);
		if (brandName != null && brandName.length() > 0) {
			Brand brand = new Brand();
			brand.setName(brandName);
			brand.setId(executeQuery.getInt(6));
			car.setBrand(brand);
		}
		car.setDateOfManufacturing(executeQuery.getTimestamp(5));
		result.add(car);
	}

//    public void addUser(Car car) {
//
//        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
//
//            ResultSet executeQuery = stmt.executeQuery(SELECT_ALL);
//
//            result = convertResult(executeQuery);
//
//            executeQuery.close();
//
//        } catch (SQLException e) {
//            LOG.error("Something went wrong", e);
//        }
//        try (Connection conn = DBUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(SQL.INSERT, Statement.RETURN_GENERATED_KEYS)) {
//
//            stmt.setString(1, car.getFirstName());
//            stmt.setString(2, car.getLastName());
//            stmt.setDouble(3, car.getSalary());
//            stmt.setTimestamp(4,
//                    Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(car.getBirthdate())));
//            stmt.setBoolean(5, car.isMale());
//
//            stmt.executeUpdate();
//
//            ResultSet generatedKeys = stmt.getGeneratedKeys();
//            generatedKeys.next();
//            LOGGER.info("User created with id: " + generatedKeys.getLong(1));
//
//        } catch (Exception e) {
//            LOGGER.error("Something went wrong...", e);
//        }
//    }



}
