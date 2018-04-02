package Tree;

import java.util.Scanner;

public class HuffmanTest {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = input.nextLine();

        int[] counts = HuffmanCode.getCharacterFrequency(text);

        System.out.printf("%-15s%-15s%-15s\n", "ASCII Code", "Character", "Frequency", "Code");

         HuffmanCode.Tree tree = HuffmanCode.getHuffmanTree(counts);
         String[] codes = HuffmanCode.getCode(tree.root);

         for (int i = 0; i < codes.length; i++){
             if (counts[i] != 0)
                 System.out.printf("%-15s%-15s%-15s\n", i, (char)i + "", counts[i], codes[i]);

         }

    }
}
