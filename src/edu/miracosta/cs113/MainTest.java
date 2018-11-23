package edu.miracosta.cs113;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
                dataFromFile = test.readDataToString(fileName);

            }
            catch (Exception e)
            {

            }
            //count the number that each character occurs.
            aBinaryTree.getPrevalenceOfEachCharacter(dataFromFile);
            //put the values into a priority que.
            aBinaryTree.putPrevalenceIntoAPrioriQue();
            //construct the huffman tree.
            aBinaryTree.constructTheHuffmanTree();
            //The original file.
            String original = dataFromFile;
            // the encoded file.
            String encoded = aBinaryTree.encodeAString(dataFromFile);
            //Write to file
            try
            {
                whenWriteStringUsingBufferedWritter_thenCorrect(encoded, "EndcodedData");
            }
            catch (IOException e)
            {
                System.out.println("error");
            }

            //decode and write to file.
            String decoded = aBinaryTree.decode(encoded);
            try
            {
                whenWriteStringUsingBufferedWritter_thenCorrect(decoded, "DecodedData");
            }
            catch (IOException e)
            {
                System.out.println("error");
            }



            //compare the two.
            System.out.println(" The number of bits in the encoded file (compressed):" + test.getNumChars("EncodedData"));
            System.out.println(" The number of bits in the original file (16 bits per character):" + test.getNumChars(fileName)*16 );
            System.out.println(" The number of bits in the decoded file (16 bits per character):" + test.getNumChars("DecodedData")*16 );
            System.out.println(" This will return true if the files are the same :" + original.equals(decoded));

            //ratio of bits between encoded and decoded.
            float encodedFileBits = test.getNumChars("EncodedData");
            float decodedFileBits = (test.getNumChars(fileName)*16);
            float ratio = decodedFileBits/encodedFileBits;

            System.out.println("The original file is this much larger :" + (ratio) );


        }








    public static void whenWriteStringUsingBufferedWritter_thenCorrect(String str, String filePath) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(str);

        writer.close();
    }

}
