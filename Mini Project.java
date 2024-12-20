// 28/10/2024
// Version 3
// Word connection game, words are shown one by one, the user needs to guess␣
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random; //Allows for the topic choice to be randomized
import java.util.Scanner; //Allows for user input
class Topic {
    final String[] allTopics = {"Ronaldo","Phone", "Programming languages","Sherlock Holmes", "Backpack", "One Piece", "Naruto", "London", "Watch","Hiking", "Breaking Bad", "Chess", "Monopoly", "Death Note", "Dragonball Z","Albert Einstein", "Leonardo da Vinci", "Harry Potter", "Tree"};
    int noOfTopics = allTopics.length;
    String name;
    String[][] allTopicItems = {
            {"Footballer","Portugal","United","Madrid","Siu"},
            {"Communication", "Portable", "Displays", "Listens", "Camera"},
            {"Python", "Java", "Ruby", "C++", "Swift"},
            {"221B", "Detective", "Pipe", "Hat", "London"},
            {"Straps", "Portable", "Container", "Back", "Zips"},
            {"Scar", "Monkey", "Islands", "Stretch", "Pirates"},
            {"Tails", "Noodles", "Headband", "Chakra", "Jutsu"},
            {"Capital", "Crown", "Ben", "Historic", "Multicultural"},
            {"Moving", "Constant", "Hands", "Numbers", "Wrist"},
            {"Up", "Views", "Trail", "Mountains", "Risk"},
            {"Pink", "White", "Chemistry", "Crime", "Drugs"},
            {"Tactical", "Warfare", "Castle", "Board", "Checkmate"},
            {"GO", "Capitalism", "Property", "Bankruptcy", "Board"},
            {"Kira", "Power", "Shinigami", "L", "Notebook"},
            {"Fusion", "Cell", "Fighting", "Senzu", "Saiyan"},
            {"Relativity", "Atomic", "German", "Tongue", "E=mc^2"},
            {"Vitruvian", "Italian", "Mona", "Multifaceted", "Supper"},
            {"Magic", "Glasses", "Scar", "9 & 3/4", "Wand"},
            {"Tall", "Bark", "Oil", "Seasonal", "Wood"}};
    String[] topicItems = new String[5];
}
class WordConnect {
    public static void main(String[] a) {
        RunGame();
    }
    public static void PrintRules() {
        System.out.println(" Welcome to Word Connect");
                System.out.println("---------------------------------------------------------------------------------");
                        System.out.println(" RULES:");
        System.out.println(" Words will appear one at a time");
                System.out.println("After each word appears, you will get a chance to␣ guess what 'topic' the series of words will be referencing.");
        System.out.println("A topic can be any noun (Show, Book, Location, Object, Person) as well as any activity. All objects will be singular.");
        System.out.println("The fewer words you need to guess the correct topic, the more points you are awarded.");
                System.out.println("---------------------------------------------------------------------------------");
    }
    public static int ChooseGameLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" How many words do you want to guess? CHOOSE FROM: 3, 5, 10");
        int GameLength = Integer.parseInt(scanner.nextLine()); //Takes an integer input from the user, they choose their game length
        while (GameLength != 3 && GameLength != 5 && GameLength != 10) {
            System.out.println("Not a valid game length. CHOOSE FROM: 3, 5,10");
                    GameLength = Integer.parseInt(scanner.nextLine()); //Takes the input again, if the user's first input was invalid
        }
        return GameLength;
    }
    public static void DottedLineAndSpace() { //Creates a dotted line and a space beneath it
        System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
        System.out.println("");
    }
    public static void PrintWithSpaces(String string) { //Prints with spaces above and beneath
        System.out.println("");
        System.out.println(string);
        System.out.println("");
    }
    static void RandomizeTopic(Topic topic) { //Assigns a topic with a random topic and it's corresponding topic items
        Random random = new Random();
        int topicIndex = random.nextInt(topic.noOfTopics);
        topic.name = topic.allTopics[topicIndex];
        topic.topicItems = topic.allTopicItems[topicIndex];
    }
    static void Rounds() {
        int GameLength = ChooseGameLength(); //User chooses the game's length
        Scanner scanner = new Scanner(System.in); //Allows the scanner to be accessed by this method
        int highestScore = GameLength * 5;
        int Points = 0;
        for (int i = 1; i <= GameLength; i++) {
            Topic topic = new Topic(); //Creates a new topic record
            RandomizeTopic(topic); //Randomizes the topic
            DottedLineAndSpace();
            System.out.println(" ROUND " + i);
            for (int j = 1; j <= 5; j++) {
                System.out.println(topic.topicItems[j - 1]);
                System.out.println();
                String guess = scanner.nextLine();
                if (guess.equalsIgnoreCase(topic.name)) { //When the topic is guessed
                    if (j == 1) {
                        System.out.println();
                        System.out.println(" Nice, you got it on your first try! So you get " + (6 - j) + " points.");
                    }
                    else {
                        System.out.println(" Correct! You guessed after "+ j +" words, so you get " + (6 - j) + " points.");
                    }
                    Points += 6 - j;
                    j = 6;
                    PrintWithSpaces(" CURRENT POINTS:" + Points);
                }
                else { //When the topic isn't guessed
                    if (j == 5) { //All 5 words were printed
                        PrintWithSpaces("Wrong, the answer was: " + topic.name);
                        j = 6;
                    }
                    else {
                        System.out.println("Wrong. The next word is:");
                    }
                }
            }
        }
        DottedLineAndSpace();
        System.out.println(" All rounds have finished, you scored "+ Points + "/" + highestScore + "!");
        WriteTotalPointsToFile(Points); // Update total score in the file
    }
    public static void RunGame() {
        PrintRules(); //Prints the game's rules
        Rounds();
    }
    public static void WriteTotalPointsToFile(int points) {
        try {
            File totalScoreFile = new File("Total_Score.txt");
            int totalScore = 0;
            if (totalScoreFile.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(totalScoreFile));
                String line = bufferedReader.readLine();
                if (line != null) {
                    totalScore = Integer.parseInt(line.replace("Total Score: ","").trim());
                }
                bufferedReader.close();
            }
            totalScore += points;
            // Write the updated total score to the file
            FileWriter fileWriter = new FileWriter(totalScoreFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Total Score: " + totalScore);
            bufferedWriter.close();
            System.out.println("Your total score has been updated and written to 'Total_Score.txt'.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }
}
