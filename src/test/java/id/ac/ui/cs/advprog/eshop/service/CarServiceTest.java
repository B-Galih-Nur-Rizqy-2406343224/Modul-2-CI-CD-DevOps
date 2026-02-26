package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarServiceImpl carService;

    @Mock
    CarRepository carRepository;

    @Test
    void testCreateCar() {
        Car car = new Car();
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);

        when(carRepository.create(car)).thenReturn(car);
        Car result = carService.create(car);

        verify(carRepository, times(1)).create(car);
        assertEquals(car, result);
    }

    @Test
    void testFindAllCars() {
        Car car1 = new Car();
        car1.setCarName("Avanza");
        car1.setCarColor("Red");
        car1.setCarQuantity(10);

        Car car2 = new Car();
        car2.setCarName("Innova");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);

        Iterator<Car> iterator = List.of(car1, car2).iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();

        verify(carRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);

        when(carRepository.findById("1")).thenReturn(car);
        Car result = carService.findById("1");

        verify(carRepository, times(1)).findById("1");
        assertEquals(car, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(carRepository.findById("non-existent-id")).thenReturn(null);
        Car result = carService.findById("non-existent-id");

        verify(carRepository, times(1)).findById("non-existent-id");
        assertNull(result);
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarName("Innova");
        car.setCarColor("Blue");
        car.setCarQuantity(5);

        carService.update("1", car);

        verify(carRepository, times(1)).update("1", car);
    }

    @Test
    void testUpdateNonExistentCar() {
        Car car = new Car();
        car.setCarName("Innova");
        car.setCarColor("Blue");
        car.setCarQuantity(5);

        carService.update("non-existent-id", car);

        verify(carRepository, times(1)).update("non-existent-id", car);
    }

    @Test
    void testDeleteCar() {
        doNothing().when(carRepository).delete("1");
        carService.deleteCarById("1");

        verify(carRepository, times(1)).delete("1");
    }
}
