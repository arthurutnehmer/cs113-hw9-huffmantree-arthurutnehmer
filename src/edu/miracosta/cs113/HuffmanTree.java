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
     * will get the root of the huffman tree.
     */
    public Node<CharacterAndWeight> getRoot()
    {
        return huffmanTree.root;
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
                //Get second node.
                Node<CharacterAndWeight> nodeToInsertTwo = queOfPrevalence.remove();
                //get combined weight.
                int combinedWeight = nodeToInsert.data.prevalence + nodeToInsertTwo.data.prevalence;
                //make new node that will be a weight node.
                Node<CharacterAndWeight> nodeThatContainsCombinedWeight = new Node<CharacterAndWeight>(new CharacterAndWeight('*', combinedWeight));
                //connect nodes.
                nodeThatContainsCombinedWeight.right = nodeToInsert;
                nodeThatContainsCombinedWeight.left = nodeToInsertTwo;
                //insert.
                queOfPrevalence.add(nodeThatContainsCombinedWeight);
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
        String message =  encodeACharacterRecursive(huffmanTree.root,Character);
        message = message.substring(0, message.length()-1);
        return message;
    }

    /**
     * decode a character
     */
    public void decodeACharacter(String number)
    {
        String[] numberArray = number.split("");
        Node<CharacterAndWeight> tmpNode = huffmanTree.root;
        for(int i = 0; i < numberArray.length;i++)
        {
            if (numberArray[i].equals("0"))
            {
                tmpNode = tmpNode.left;
            }
            else if (numberArray[i].equals("1"))
            {
                tmpNode = tmpNode.right;
            }
        }

        System.out.println(tmpNode);
    }
    public String encodeACharacterRecursive(Node<CharacterAndWeight> tmp, char character)
    {
        String toReturn = "";
       if(tmp.data.character == character)
       {
           return "*";
       }
       else if(tmp.left == null && tmp.right == null)
       {
           return "";
       }
       else if(tmp.left == null)
       {
           return "1" + encodeACharacterRecursive(tmp.right, character);
       }
       else if(tmp.right == null)
       {
           return "0" + encodeACharacterRecursive(tmp.left, character);
       }
       else
       {
           String left = "";
           String right = "";
           if(tmp.left != null)
           {
               left = "0" + encodeACharacterRecursive(tmp.left, character);
           }
           if(tmp.right != null)
           {
                right = "1" + encodeACharacterRecursive(tmp.right, character);
           }
           if(right.charAt(right.length()-1) == '*')
           {
               toReturn = right;
           }
           if(left.charAt(left.length()-1) == '*')
           {
               toReturn = left;
           }
       }

       return toReturn;
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
