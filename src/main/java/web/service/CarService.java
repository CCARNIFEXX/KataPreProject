package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.List;
@Service
public class CarService {
    private static final List<Car> cars = List.of(
            new Car("OPEL", "47236819", 2005),
            new Car("BMW", "84329048", 2009),
            new Car("TOYOTA", "657488756", 2001),
            new Car("HYUNDAI", "657283456", 2012),
            new Car("NISSAN", "903567439", 2003)
    );
    public List<Car> getCars(Integer count){
        if (count == null) {
            count = cars.size();
        } else if (count < 0) {
            count = 0;
        } else if (count > cars.size()) {
            count = cars.size();
        }
        return cars.subList(0, count);
    }

}
