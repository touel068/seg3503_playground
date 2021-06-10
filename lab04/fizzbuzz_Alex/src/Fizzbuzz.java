public class Fizzbuzz {
    
    public String getFizzBuzz(int n){
        if(n % 3 == 0){
            return "fizz";
        }
        else if(n % 5 == 0){
            return "buzz";
        }
        else{
            return Integer.toString(n);
        }
    }
}
