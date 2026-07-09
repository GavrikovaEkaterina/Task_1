package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты класса Bun")
class BunTest {

    @ParameterizedTest(name = "Булочка {0} должна стоить {1} руб.")
    @CsvSource({
            "Булочка с кунжутом, 50.0",
            "Булочка с маком, 75.5",
            "Булочка без глютена, 120.0"
    })
    @DisplayName("Конструктор должен сохранять имя и цену")
    void constructorShouldSetNameAndPrice(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice());
    }

    @Test
    @DisplayName("Метод getName должен возвращать правильное имя")
    void getNameShouldReturnCorrectName() {
        Bun bun = new Bun("Тестовая булочка", 100);
        assertEquals("Тестовая булочка", bun.getName());
    }

    @Test
    @DisplayName("Метод getPrice должен возвращать правильную цену")
    void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun("Булочка", 99.9f);
        assertEquals(99.9f, bun.getPrice());
    }

    @Test
    @DisplayName("Цена должна изменяться при прямой модификации поля")
    void priceShouldChangeWhenFieldModified() {
        Bun bun = new Bun("Тестовая булочка", 100);
        float initialPrice = bun.getPrice();

        bun.price = 200;

        assertEquals(200, bun.getPrice(), "Цена должна измениться после прямой модификации поля");
        assertNotEquals(initialPrice, bun.getPrice(), "Цена должна отличаться от начальной");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Конструктор должен корректно обрабатывать пустое и null имя")
    void constructorShouldHandleEmptyAndNullName(String name) {
        Bun bun = new Bun(name, 100);
        assertEquals(name, bun.getName());
    }

    @Test
    @DisplayName("Конструктор должен корректно обрабатывать имя со специальными символами")
    void constructorShouldHandleSpecialCharactersInName() {
        String nameWithSpecialChars = "Булочка!@#$%^&*()_+";
        Bun bun = new Bun(nameWithSpecialChars, 100);
        assertEquals(nameWithSpecialChars, bun.getName());
    }

    @Test
    @DisplayName("Конструктор должен корректно обрабатывать имя с пробелами")
    void constructorShouldHandleNameWithSpaces() {
        String nameWithSpaces = "  Булочка с пробелами  ";
        Bun bun = new Bun(nameWithSpaces, 100);
        assertEquals(nameWithSpaces, bun.getName());
    }

    @Test
    @DisplayName("Конструктор должен корректно обрабатывать очень длинное имя")
    void constructorShouldHandleVeryLongName() {
        String longName = "a".repeat(1000);
        Bun bun = new Bun(longName, 100);
        assertEquals(longName, bun.getName());
    }

    @ParameterizedTest
    @ValueSource(floats = {-100.0f, -1.0f, 0.0f, 0.5f, 100.5f, 1000.0f})
    @DisplayName("Конструктор должен корректно обрабатывать разные значения цены")
    void constructorShouldHandleVariousPrices(float price) {
        Bun bun = new Bun("Тест", price);
        assertEquals(price, bun.getPrice());
    }

    @Test
    @DisplayName("Метод getPrice должен возвращать актуальное значение после изменения поля")
    void getPriceShouldReturnUpdatedValueAfterFieldChange() {
        Bun bun = new Bun("Тест", 100);
        assertEquals(100, bun.getPrice());

        bun.price = 200;
        assertEquals(200, bun.getPrice());

        bun.price = 0;
        assertEquals(0, bun.getPrice());

        bun.price = -50;
        assertEquals(-50, bun.getPrice());
    }
}