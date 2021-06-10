public class Fizzbuzz {
    
    public String getFizzBuzz(int n){
        String answer = "";
        if(n % 3 == 0){
            answer += "fizz";
        }
        if(n % 5 == 0){
            answer += "buzz";
        }
        if(n % 3 != 0 && n % 5 != 0){
            answer = Integer.toString(n);
        }
        return answer;
    }
}
