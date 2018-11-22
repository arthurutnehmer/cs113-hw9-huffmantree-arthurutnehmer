package edu.miracosta.cs113;
import java.util.PriorityQueue;

public class HuffmanTree extends BinaryTree<CharacterAndWeight> implements HuffmanInterface
{
    //Array that is used while scanning the file for frequency of each character.
    CharacterAndWeight[] arrayOfPrevalence;
    //A sorted queue that contains the objects characterAndWeight.
    PriorityQueue<Node<CharacterAndWeight>> queOfPrevalence;
    //The completed huffman tree.
    BinaryTree<CharacterAndWeight> huffmanTree;
    /**
     * Initializes the class with and array of prevalence for each character.
     */
    public HuffmanTree()
    {
        arrayOfPrevalence = new CharacterAndWeight[80];
        queOfPrevalence = new PriorityQueue<Node<CharacterAndWeight>>(new PrevalenceComparator());
        huffmanTree = new BinaryTree<CharacterAndWeight>();

    }

    /**
     * Construct the huffman tree.
     */

    public void constructTheHuffmanTree()
    {
        while (queOfPrevalence.size() > 1)
        {
            //Start with the two smallest nodes.
            Node<CharacterAndWeight> nodeToInsert = queOfPrevalence.remove();
            if (!queOfPrevalence.isEmpty())
            {
                //Get second node.
                Node<CharacterAndWeight> nodeToInsertTwo = queOfPrevalence.remove();
                //get combined weight.
                int combinedWeight = nodeToInsert.data.prevalence + nodeToInsertTwo.data.prevalence;
                //make new node that will be a weight node.
                Node<CharacterAndWeight> nodeThatContainsCombinedWeight = new Node<CharacterAndWeight>(new CharacterAndWeight('*', combinedWeight));
                //connect nodes.
                nodeThatContainsCombinedWeight.left = nodeToInsert;
                nodeThatContainsCombinedWeight.right = nodeToInsertTwo;
                //insert.
                queOfPrevalence.add(nodeThatContainsCombinedWeight);
            }
        }
        huffmanTree.root = queOfPrevalence.remove();
    }

    /**
     * Places the characters and the weights into a priority que.
     */
    public void putPrevalenceIntoAPrioriQue()
    {
        for(int i = 0; i<arrayOfPrevalence.length; i++)
        {
            if(arrayOfPrevalence[i] != null)
            {
                //make new node to put into the que.
                Node<CharacterAndWeight> nodeToInsert = addNodeWithLetterInfo(arrayOfPrevalence[i].character, arrayOfPrevalence[i].prevalence);
                queOfPrevalence.add(nodeToInsert);
            }
            else
            {
                i = arrayOfPrevalence.length;
            }
        }
    }
    /**
     * Prints the tree
     */
    public void printHuffmanTree()
    {
        System.out.println(huffmanTree.toString());
    }
    /**
     * Prints the queOfPrevalence
     */
    public void printQueOfPrevalence()
    {
        PriorityQueue<BinaryTree.Node> test = new PriorityQueue<BinaryTree.Node>(queOfPrevalence);
        while (!test.isEmpty())
        {
            System.out.println(test.remove());
        }

        System.out.println("queOfPrevalence is of this length " +  queOfPrevalence.size());
    }

    /**
     * Encode a character.
     */
    public String encodeACharacter(char Character)
    {
       return encodeACharacterRecursive(huffmanTree.root,Character);
    }

    public String encodeACharacterRecursive(Node<CharacterAndWeight> tmp, char character)
    {
        if(tmp.left != null)
        {
            Node<CharacterAndWeight> tmpLeft = tmp.left;
            if(tmp.data.character == character)
            {
                return "0";
            }
            else
            {
                return ("0" + encodeACharacterRecursive(tmpLeft, character));
            }
        }

        if(tmp.right != null)
        {
            Node<CharacterAndWeight> tmpRight = tmp.right;
            if(tmp.data.character == character)
            {
                return "1";
            }
            else
            {
                return ("1" + encodeACharacterRecursive(tmpRight, character));
            }
        }
        return null;
    }
    /**
     * Finds the amount of times that each character may occur and stores the result as an object.
     */
    public void getPrevalenceOfEachCharacter(String message)
    {
        String [] charsToCount = message.split("");

        //loop for each character in the message.
        for(int i = 0; i<charsToCount.length; i++)
        {
            for(int c = 0; c< arrayOfPrevalence.length;c++)
            {
                //if the array has reached an empty spot without finding the letter.
                if(arrayOfPrevalence[c] == null)
                {
                    arrayOfPrevalence[c] = new CharacterAndWeight(charsToCount[i].charAt(0), 1);
                    c = arrayOfPrevalence.length;
                }
                //if the array finds a matching character.
                else if (arrayOfPrevalence[c].character == charsToCount[i].charAt(0))
                {
                    arrayOfPrevalence[c].addOneToPrevalence();
                    c = arrayOfPrevalence.length;
                }
            }
        }
    }

    /**
     * Finds the amount of times that each character may occur.
     */
    public void printPrevalenceOfEachCharacter()
    {
        for(int i = 0; i < arrayOfPrevalence.length; i++)
        {
            int c = i;
            System.out.println(arrayOfPrevalence[i] + " Number: " + i);
        }

        System.out.println("arrayOfPrevalence is of this length " +arrayOfPrevalence.length);
    }
    /**
     * Decodes a message using the generated Huffman tree, where each character in the given message ('1's and '0's)
     * corresponds to traversals through said tree.
     *
     * @param codedMessage The compressed message based on this Huffman tree's encoding
     * @return The given message in its decoded form
     */
    public String decode(String codedMessage)
    {
        return null;
    }

    /**
     * Outputs the message encoded from the generated Huffman tree.
     * pre: the Huffman tree has been built using characters by which the message is only comprised.
     *
     * @param message The message to be decoded
     * @return The given message in its specific Huffman encoding of '1's and '0's
     */
    public String encode(String message)
    {
        return null;
    }

}
