import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ScoreDeterminer scoreDeterminer = new ScoreDeterminer();


        scoreDeterminer.score(scanner.nextLine());
        System.out.println(scoreDeterminer.getScore());

    }
}
