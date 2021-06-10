import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FizzbuzzTest {

    @Test
    void getFizzBuzz_3() {
        Fizzbuzz fb = new Fizzbuzz();
        assertEquals("fizz", fb.getFizzBuzz(3));
    }

    @Test
    void getFizzBuzz_5() {
        Fizzbuzz fb = new Fizzbuzz();
        assertEquals("buzz", fb.getFizzBuzz(5));
    }

    @Test
    void getFizzBuzz_7() {
        Fizzbuzz fb = new Fizzbuzz();
        assertEquals("7", fb.getFizzBuzz(7));
    }

    @Test
    void getFizzBuzz_10() {
        Fizzbuzz fb = new Fizzbuzz();
        assertEquals("buzz", fb.getFizzBuzz(10));
    }

    @Test
    void getFizzBuzz_15() {
        Fizzbuzz fb = new Fizzbuzz();
        assertEquals("fizzbuzz", fb.getFizzBuzz(15));
    }

}