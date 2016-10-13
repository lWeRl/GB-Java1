import java.util.Scanner;

/**
 * Created by lWeRl on 11.10.2016.
 * Java1 Lesson 3 Nickolay Bobrov.
 */
class HomeWork3_2 {

    final char PLAYER_DOT = 'x';
    final char AI_DOT = 'o';
    final char EMPTY_DOT = '.';
    final int FIELD_SIZE = 3;                              // выставляется любое значение
    final int WIN_SIZE = 3;                                // выставляется любое значение
    char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new HomeWork3_2().go();
    }

    void go() {
        initField();
        printField();
        while (true) {
            turnPlayer();
            printField();
            if (checkWin(PLAYER_DOT, WIN_SIZE)) {
                printField();
                System.out.println("You Win!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw.");
                printField();
                break;
            }
            turnAI();
            if (checkWin(AI_DOT, WIN_SIZE)) {
                printField();
                System.out.println("You Lose!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw.");
                printField();
                break;
            }
            printField();
        }
    }

    void turnPlayer() {
        int x, y;
        do {
            System.out.println("Enter coordinates(x,y) 1-" + FIELD_SIZE);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellEmpty(x, y));
        field[x][y] = PLAYER_DOT;
    }

    void turnAI() {
        int[] aiXY = aiCheckIsNextTurnWin(AI_DOT, WIN_SIZE); // Ищем выйгрышное поле для ИИ
        if (aiXY[0] != -1) {
            field[aiXY[0]][aiXY[1]] = AI_DOT;
            return;
        }
        for (int i = WIN_SIZE; i > 1; i--) {
            aiXY = aiCheckIsNextTurnWin(PLAYER_DOT, i); // Ищем выйгрышное поле для игрока
            if (aiXY[0] != -1) {
                field[aiXY[0]][aiXY[1]] = AI_DOT;
                return;
            }
        }
        int x, y;
        do {
            System.out.println("Enter coordinates(x,y) 1-" + FIELD_SIZE);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellEmpty(x, y));
        field[x][y] = AI_DOT;
    }

    int[] aiCheckIsNextTurnWin(char dot, int size) {
        int[] result = {-1, -1};
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    field[i][j] = dot;
                    if (checkWin(dot, size)) {
                        result[0] = i;
                        result[1] = j;
                        field[i][j] = EMPTY_DOT;
                        return result;
                    }
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return result;
    }

    boolean isFieldFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    boolean checkWin(char dot, int size) {
        //Проверка линий
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j <= FIELD_SIZE - size; j++) {
                if (field[i][j] == dot) {
                    int k;
                    for (k = 1; k < size; k++) {
                        if (field[i][j + k] != dot) break;
                    }
                    if (k == size) return true;
                }
                if (field[j][i] == dot) {
                    int k;
                    for (k = 1; k < size; k++) {
                        if (field[j + k][i] != dot) break;
                    }
                    if (k == size) return true;
                }
            }
        }
        // Проверка диагонали
        for (int i = 0; i <= FIELD_SIZE - size; i++) {
            for (int j = 0; j <= FIELD_SIZE - size; j++) {
                if (field[i][j] == dot) {
                    int k;
                    for (k = 1; k < size; k++) {
                        if (field[i + k][j + k] != dot) break;
                    }
                    if (k == size) return true;
                }
                if (field[i][FIELD_SIZE - j - 1] == dot) {
                    int k;
                    for (k = 1; k < size; k++) {
                        if (field[i + k][FIELD_SIZE - j - 1 - k] != dot) break;
                    }
                    if (k == size) return true;
                }
            }
        }
        return false;
    }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
        if (field[x][y] == EMPTY_DOT) return true;
        return false;
    }

    void initField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    void printField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) System.out.print(field[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}