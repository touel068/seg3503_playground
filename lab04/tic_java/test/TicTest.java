import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTest{
    Tic tic = new Tic();

    @Test
    void test1(){
        String[][] result = {{"_","_"},{"_","_"}};
        assertEquals("_", tic.emptyboard(2,2));
    }


}