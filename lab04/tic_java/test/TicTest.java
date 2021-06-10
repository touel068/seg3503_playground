import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTest{
    Tic tic1 = new Tic();
    
    @Test
    void test1(){
        String[][] result = {{"_","_"},{"_","_"}};
        assertEquals(result, tic1.emptyboard(2,2));
    }


}