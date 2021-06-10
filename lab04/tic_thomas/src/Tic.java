
public class Tic {

    public static String[][] emptyboard(int indexI, int indexJ) {

        String[][] board = new String[indexI][indexJ];
        for (int i = 0; i < indexI; i++) {
            for (int j = 0; j < indexJ; j++) {
                board[i][j] = "_";
            }
        }

        return board;

    }

    public static int boardSize(int indexI,int indexJ){

        return indexI*indexJ;
    }

    public static String[][] default_Board(){

        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "_";
            }
        }

        return board;

    }

}
