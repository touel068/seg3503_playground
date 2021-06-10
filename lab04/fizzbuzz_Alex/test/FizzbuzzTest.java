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


}