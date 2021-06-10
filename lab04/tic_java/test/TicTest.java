
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TicTest {

    Tic tic = new Tic();

    @Test
    public void test_emptyboard() {
        String[][] result = {{"_","_","_"},{"_","_","_"}};
        assertTrue(Arrays.deepEquals(result,tic.emptyboard(2,3)));
    }
   

}