package edu.miracosta.cs113;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
public class MainTest
{
        public static void main(String[]args)
        {
            //create object for the huffmanTree.
            HuffmanTree aBinaryTree = new HuffmanTree();
            //String that will hold the data from the file.
            String dataFromFile = "";
            //create the object to scrape.
            TextFileGenerator test = new TextFileGenerator();
            //website domain.
            String website = "https://www.ancient.eu/plato/";
            //file name.
            String fileName = "DataToEncode";
            try
            {
                test.makeCleanFile(website, fileName);
                dataFromFile = test.readDataToString("DataToTest");

            }
            catch (Exception e)
            {

            }

            //count the number that each character occurs.
            aBinaryTree.getPrevalenceOfEachCharacter(dataFromFile);
            //put the values into a priority que.
            aBinaryTree.putPrevalenceIntoAPrioriQue();
            //print the que.
            aBinaryTree.printQueOfPrevalence();
            //construct the huffman tree.
            aBinaryTree.constructTheHuffmanTree();
            //print the tree
            aBinaryTree.printHuffmanTree();

            System.out.println(aBinaryTree.encodeACharacter('a'));


        }


}
