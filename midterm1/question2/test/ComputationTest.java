
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

class ComputationTest {

    Computation comp = new Computation();

 

    @Test 
    public void test1() {
        assertEquals(7, comp.compute(7,8));
    }

    @Test 
    public void test2() {
        assertEquals(11, comp.compute(3,4));
    }


    @Test 
    public void test3() {
        assertEquals(-3, comp.compute(-1,2));
    }


    @Test 
    public void test4() {
        assertEquals(1, comp.compute(2,1));
    }

 

   
   

  
}