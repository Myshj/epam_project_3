import models.City;
import models.Country;
import orm.repository.Repository;
import utils.ConnectionServiceProvider;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Repository<Country> countryRepository = new Repository<>(Country.class, ConnectionServiceProvider.INSTANCE.get());
        Repository<City> cityRepository = new Repository<>(City.class, ConnectionServiceProvider.INSTANCE.get());
        countryRepository.save(new Country("myshandi"));
        City newCity = new City("myshgorod", countryRepository.getById(3).orElseThrow(SQLException::new));
        cityRepository.save(
                newCity
        );
        cityRepository.delete(newCity);
        cityRepository.getById(7).ifPresent(
                c -> System.out.println("City[7].country.id: " + c.getCountry().get().map(Country::getId))
        );

        countryRepository.getById(3).ifPresent(
                c -> {
                    c.getName().setValue("tatatandia");
                    countryRepository.save(c);
                }
        );

        System.out.println("All countries: " + countryRepository.getAll());
    }

    enum TestEnum {
        test_1,
        test_2
    }
}
