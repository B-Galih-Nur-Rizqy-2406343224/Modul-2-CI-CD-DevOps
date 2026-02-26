package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Avanza");
        car1.setCarColor("Red");
        car1.setCarQuantity(10);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Innova");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car createdCar = carRepository.create(car);
        String carId = createdCar.getCarId();

        Car foundCar = carRepository.findById(carId);

        assertNotNull(foundCar);
        assertEquals(carId, foundCar.getCarId());
        assertEquals("Avanza", foundCar.getCarName());
        assertEquals("Red", foundCar.getCarColor());
        assertEquals(10, foundCar.getCarQuantity());
    }

    @Test
    void testFindByIdNotFound() {
        Car result = carRepository.findById("non-existent-id");
        assertNull(result);
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car createdCar = carRepository.create(car);
        String carId = createdCar.getCarId();

        Car updatedCar = new Car();
        updatedCar.setCarName("Innova");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(5);

        Car result = carRepository.update(carId, updatedCar);

        assertNotNull(result);
        assertEquals(carId, result.getCarId());
        assertEquals("Innova", result.getCarName());
        assertEquals("Blue", result.getCarColor());
        assertEquals(5, result.getCarQuantity());

        Car foundCar = carRepository.findById(carId);
        assertNotNull(foundCar);
        assertEquals("Innova", foundCar.getCarName());
        assertEquals("Blue", foundCar.getCarColor());
        assertEquals(5, foundCar.getCarQuantity());
    }

    @Test
    void testUpdateNonExistentCar() {
        Car car = new Car();
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car createdCar = carRepository.create(car);
        String carId = createdCar.getCarId();

        Car updatedCar = new Car();
        updatedCar.setCarName("Innova");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(5);

        Car result = carRepository.update("non-existent-id", updatedCar);

        assertNull(result);

        Car originalCar = carRepository.findById(carId);
        assertNotNull(originalCar);
        assertEquals("Avanza", originalCar.getCarName());
        assertEquals("Red", originalCar.getCarColor());
        assertEquals(10, originalCar.getCarQuantity());
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car createdCar = carRepository.create(car);
        String carId = createdCar.getCarId();

        carRepository.delete(carId);

        Car result = carRepository.findById(carId);
        assertNull(result);

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testDeleteNonExistentCar() {
        Car car = new Car();
        car.setCarName("Avanza");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        carRepository.create(car);

        carRepository.delete("non-existent-id");

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
    }

    @Test
    void testDeleteCarWithMultipleCars() {
        Car car1 = new Car();
        car1.setCarName("Avanza");
        car1.setCarColor("Red");
        car1.setCarQuantity(10);
        Car createdCar1 = carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarName("Innova");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);
        Car createdCar2 = carRepository.create(car2);

        carRepository.delete(createdCar1.getCarId());

        Car deletedCar = carRepository.findById(createdCar1.getCarId());
        assertNull(deletedCar);

        Car remainingCar = carRepository.findById(createdCar2.getCarId());
        assertNotNull(remainingCar);
        assertEquals("Innova", remainingCar.getCarName());
    }
}
