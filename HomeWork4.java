import java.util.Scanner;

/**
 * Created by lWeRl on 12.10.2016.
 */
class HomeWork4 {
    public static void main(String[] args) {
        Person[] array = new Person[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Person("name" + i, "job" + i, "email" + i, "phone" + i, i * 10000, 38 + i);
        }
        for (Person p : array) {
            if (p.age >= 40) System.out.println(p.toString());
        }

        TicTacToe game = new TicTacToe();
        game.game();
    }
}

class Person {
    String name;
    String job;
    String email;
    String phone;
    int salary;
    int age;

    Person(String name, String job, String email, String phone, int salary, int age) {
        this.name = name;
        this.job = job;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
    @Override
    public String toString(){
        return "Name:"+name+" Job:"+job+" Email:"+email+" Phone:"+phone+" Salary:"+salary+" Age:"+age;
    }
}

/**
 * Created by lWeRl on 12.10.2016.
 */
class TicTacToe {
    public void game() {
        Field field = new Field(3, 3);
        Player player = new Player(field);
        Ai ai = new Ai(field);
        field.initField();
        field.printField();
        while (true) {
            player.turnPlayer();
            field.printField();
            if (field.checkWin(field.PLAYER_DOT, field.WIN_SIZE)) {
                System.out.println("You Win!");
                break;
            }
            if (field.isFieldFull()) {
                System.out.println("Draw.");
                break;
            }
            ai.turnAI();
            field.printField();
            if (field.checkWin(field.AI_DOT, field.WIN_SIZE)) {
                System.out.println("You Lose!");
                break;
            }
            if (field.isFieldFull()) {
                System.out.println("Draw.");
                break;
            }
        }
    }

    private class Field {  // Все дальнейшие классы вложены в основной класс и выставлен флаг private, просто проверял доступность из других классов
        final int FIELD_SIZE;
        final int WIN_SIZE;
        char[][] field;
        final char EMPTY_DOT = '.';
        final char PLAYER_DOT = 'x';
        final char AI_DOT = 'o';

        Field(int field_size, int win_size) {
            FIELD_SIZE = field_size;
            WIN_SIZE = win_size;
            this.field = new char[FIELD_SIZE][FIELD_SIZE];
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

        boolean isFieldFull() {
            for (int i = 0; i < FIELD_SIZE; i++) {
                for (int j = 0; j < FIELD_SIZE; j++) {
                    if (field[i][j] == EMPTY_DOT) return false;
                }
            }
            return true;
        }

        boolean isCellEmpty(int x, int y) {
            if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
            if (field[x][y] == EMPTY_DOT) return true;
            return false;
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
    }

    private class Player {
        Field field;
        Scanner sc = new Scanner(System.in);

        Player(Field field) {
            this.field = field;
        }

        void turnPlayer() {
            int x, y;
            do {
                System.out.println("Enter coordinates(x,y) 1-" + field.FIELD_SIZE);
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!field.isCellEmpty(x, y));
            field.field[x][y] = field.PLAYER_DOT;
        }
    }

    private class Ai {
        Field field;

        Ai(Field field) {
            this.field = field;
        }

        void turnAI() {
            int[] aiXY = aiCheckIsNextTurnWin(field.AI_DOT, field.WIN_SIZE); // Ищем выйгрышное поле для ИИ
            if (aiXY[0] != -1) {
                field.field[aiXY[0]][aiXY[1]] = field.AI_DOT;
                return;
            }
            for (int i = field.WIN_SIZE; i > 1; i--) {
                aiXY = aiCheckIsNextTurnWin(field.PLAYER_DOT, i); // Ищем выйгрышное поле для игрока
                if (aiXY[0] != -1) {
                    field.field[aiXY[0]][aiXY[1]] = field.AI_DOT;
                    return;
                }
            }
        }

        int[] aiCheckIsNextTurnWin(char dot, int size) {
            int[] result = {-1, -1};
            for (int i = 0; i < field.FIELD_SIZE; i++) {
                for (int j = 0; j < field.FIELD_SIZE; j++) {
                    if (field.isCellEmpty(i, j)) {
                        field.field[i][j] = dot;
                        if (field.checkWin(dot, size)) {
                            result[0] = i;
                            result[1] = j;
                            field.field[i][j] = field.EMPTY_DOT;
                            return result;
                        }
                        field.field[i][j] = field.EMPTY_DOT;
                    }
                }
            }
            return result;
        }
    }
}