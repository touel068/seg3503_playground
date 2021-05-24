
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateNextDateExceptionTest {

    private int year;
    private int month;
    private int day;

    public DateNextDateExceptionTest(int y, int m, int d) {
        this.year = y;
        this.month = m;
        this.day = d;

    }

    @Parameters
    public static List<Integer[]> data() {
        List<Integer[]> params = new LinkedList<Integer[]>();
        params.add(new Integer[] { 1500, 2, 31 });
        params.add(new Integer[] { 1500, 2, 29 });
        params.add(new Integer[] { -1, 10, 20 });
        return params;
    }

    @Test
    public void testNextDateException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Date(year, month, day));

    }

}