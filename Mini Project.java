// Zakariya Yusuf
// 05/10/2024
// Version 1
// Word connection game, words are shown one by one, the user needs to guess the related topic

import java.util.Random; // Needed to make randomisation
import java.util.Scanner; //Needed to make Scanner available

class WordConnect
{
    public static void main (String [] args)
    {
        GameController();
    } // END main

    public static void PrintRules() {
        System.out.println("                            Welcome to Word Connect                              ");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("                                    RULES:");
        System.out.println("                       Words will appear one at a time");
        System.out.println("After each word appears, you will get a chance to guess what 'topic' the series of words will be referencing.");
        System.out.println("A topic can be any noun (Show, Book, Location, Object, Person) as well as any activity. All objects will be singular.");
        System.out.println("The fewer words you need to guess the correct topic, the more points you are awarded.");

        System.out.println("---------------------------------------------------------------------------------");
    }
    
    public static int ChooseGameLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("           How many words do you want to guess? CHOOSE FROM: 3, 5, 10");
        int GameLength = Integer.parseInt(scanner.nextLine()); //Takes an integer input from the user, they choose their game length
        while (GameLength != 3 ^ GameLength != 5 ^ GameLength != 10) {
            System.out.println("Not a valid game length. CHOOSE FROM: 3, 5, 10");
            GameLength = Integer.parseInt(scanner.nextLine()); //Takes the input again, if the user's first input was invalid
        }
        return GameLength;
    }
    
    public static void DottedLineAndSpace() { //Creates a dotted line and a space beneath it
        System.out.println("--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --");
        System.out.println();
    }

    public static void PrintWithSpaces(String string) { //Prints with spaces above and beneath
        System.out.println();
        System.out.println(string);
        System.out.println();
    }

    public static String[] TopicRandomizer() {
        Random random = new Random(); //Allows for the random library to be accessed by this method
        String[][] topicList = { {"Phone","Communication","Portable","Displays","Listens","Camera"}, {"Manchester City", "Blue","Champions","England","Pep","Haaland"}, {"Sherlock Holmes","221B","Detective","Pipe","Hat","London"}, {"Backpack","Straps","Portable","Container","Back","Zips"}, {"One Piece","Scar","Monkey","Islands","Stretch","Pirates"}, {"Naruto","Tails","Noodles","Headband","Chakra","Jutsu"}, {"London", "Capital","Crown","Ben","Historic","Multicultural"}, {"Watch","Moving","Constant","Hands","Numbers","Wrist"}, {"Hiking","Up","Views","Trail","Mountains","Risk"}, {"Singapore","Asia","Green","Modern","Wealthy","Pioneer"}, {"Breaking Bad","Pink","White","Chemistry","Crime","Drugs"}, {"Chess","Tactical","Warfare","Castle","Board","Checkmate"}, {"Monopoly","GO","Capitalism","Property","Bankruptcy","Board"}, {"Death Note","Kira","Power","Shinigami","L","Notebook"}, {"Dragonball Z","Fusion","Cell","Fighting","Senzu","Saiyan"}, {"Paper","Scratch","Flexible","Tear","Draw","A4..."}, {"Onion","Layered","Painful","Bhaji","Soup","Vegetable"}, {"Gym","Sweaty","Metal","Locker","Barbell","Facility"}, {"Matrix","Pills","Time","Action","Morpheus","Neo"}, {"Albert Einstein","Relativity","Atomic","German","Tongue","E=mc^2"}, {"Leonardo da Vinci","Vitruvian","Italian","Mona","Multifaceted","Supper"}, {"Peppa Pig","George","Anthropomorphic","Muddy","Snort","Cartoon"}, {"Harry Potter","Magic","Glasses","Scar","9 & 3/4","Wand"}, {"Real Madrid","Galactico","White","Hala","Bernabeu","Spain"}, {"Arsenal","Red","Bottle","North","Highbury","Emirates"}};
        return topicList[random.nextInt(25)]; //Returns a random topic from the array
    }

    public static void GameController() {
        PrintRules(); //Prints the game's rules
        int GameLength = ChooseGameLength(); //User chooses the game's length
        Scanner scanner = new Scanner(System.in); //Allows the scanner to be accessed by this method
        int highestScore = GameLength * 5;
        int Points = 0;
        for (int i = 1; i <= GameLength; i++) {
            String[] ChosenTopic = TopicRandomizer();
            String Topic = ChosenTopic[0];
            DottedLineAndSpace();
            System.out.println("                                 ROUND "+i);
            for (int j = 1; j <= 5; j++) {
                System.out.println(ChosenTopic[j]);
                System.out.println();
                String guess = scanner.nextLine();
                if (guess.equalsIgnoreCase(Topic)) {
                    if (j == 1) { 
                        System.out.println();
                        System.out.println("         Nice, you got it on your first try! So you get "+(6-j)+" points."); 
                    }
                    else { System.out.println("      Correct! You guessed after "+j+" words, so you get "+(6-j)+" points."); } 
                    Points += 6-j;
                    j = 6;
                    PrintWithSpaces("                           CURRENT POINTS: "+Points);
                } else {
                    if (j == 5) {                   
                        PrintWithSpaces("Wrong, the answer was: "+ChosenTopic[0]);
                        j = 6;
                    } else { System.out.println("Wrong. The next word is:"); }
                }
            }
        }
        DottedLineAndSpace();
        System.out.println("            All rounds have finished, you scored "+Points+"/"+highestScore+"!");
    }
} // END class WordConnect