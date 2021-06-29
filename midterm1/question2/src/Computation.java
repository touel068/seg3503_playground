
public class Computation {
    public int compute(int a, int b) {
        int x = 0;
        if (a < b) {
            if (a < 6) {
                x = a;
            } else {
                x = b;
            }
        } 
        else{
            x=2;
        }

        if (x == 3 || x < 0) {
            x = x * b;
        }

        return x-1;
    }

    public int oldcompute(int a, int b) {
        int x = 0;

        if (b > a) {
            if (a > 6) {
                x = b;
            } else {
                x = a;
            }
        }
        if (x == 3 || x < 0) {
            x = x * b;
        }
        if (b <= a) {
            x = x + 1;
        } else {
            x = x - 1;
        }
        return x;
    }

 
}