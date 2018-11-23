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
     * Initializes the class with and array of prevalence for each character and  a priority que.
     * Also takes a string in and makes a tree.
     */
    public HuffmanTree(String messageToEncode)
    {
        arrayOfPrevalence = new CharacterAndWeight[80];
        queOfPrevalence = new PriorityQueue<Node<CharacterAndWeight>>(new PrevalenceComparator());
        huffmanTree = new BinaryTree<CharacterAndWeight>();

        getPrevalenceOfEachCharacter(messageToEncode);
        putPrevalenceIntoAPrioriQue();
        constructTheHuffmanTree();

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
        int timeWhenCreated = queOfPrevalence.size()+1;
        while (queOfPrevalence.size() > 1)
        {
            //Start with the two smallest nodes.
             Node<CharacterAndWeight> nodeToInsert = queOfPrevalence.poll();
                //Get second node.
                Node<CharacterAndWeight> nodeToInsertTwo = queOfPrevalence.poll();
                //get combined weight.
                int combinedWeight = nodeToInsert.data.prevalence + nodeToInsertTwo.data.prevalence;
                //make new node that will be a weight node.
                Node<CharacterAndWeight> nodeThatContainsCombinedWeight = new Node<CharacterAndWeight>(new CharacterAndWeight('*', combinedWeight));
                nodeThatContainsCombinedWeight.data.setTimeWhenCreated(timeWhenCreated);
                //connect nodes.
                nodeThatContainsCombinedWeight.right = nodeToInsert;
                nodeThatContainsCombinedWeight.left = nodeToInsertTwo;
                //insert.
                queOfPrevalence.add(nodeThatContainsCombinedWeight);
                //update the time stamp
            timeWhenCreated++;
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
                Node<CharacterAndWeight> nodeToInsert = addNodeWithLetterInfo(arrayOfPrevalence[i]);
                nodeToInsert.data.setTimeWhenCreated(i);
                queOfPrevalence.offer(nodeToInsert);
            }
            else
            {

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
        PriorityQueue<Node<CharacterAndWeight>> test = new PriorityQueue<Node<CharacterAndWeight>>(queOfPrevalence);
        while (!test.isEmpty())
        {
            System.out.println(test.remove());
        }

        System.out.println("queOfPrevalence is of this length " +  queOfPrevalence.size());
    }

    /**
     * decode a character
     */
    public String decodeACharacter(String number)
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
        return  Character.toString(tmpNode.data.character);
    }

    /**
     * decode a String
     */
    public String decodeAString(String message)
    {
        //tmp node
        Node<CharacterAndWeight> tmpNode = huffmanTree.root;
        //string to array.
        String[] messageToDecode = message.split("");
        //the number representing a character.
        String decodedString = "";

        for(int i = 0; i<messageToDecode.length;i++)
        {
            if (messageToDecode[i].equals("0"))
            {
                tmpNode = tmpNode.left;
            }
            else if (messageToDecode[i].equals("1"))
            {
                tmpNode = tmpNode.right;
            }

            if(tmpNode.data.character != '*')
            {
                decodedString += tmpNode.data.character;
                tmpNode = huffmanTree.root;
            }
        }

        return decodedString;
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
     * Encode a character recursive helper.
     */
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
     * Encode a string character by character. .
     */
     public String encodeAString(String message)
     {
         String encodedMessage = "";
         String[] messageArray = message.split("");
         for(int i = 0; i < messageArray.length; i++)
         {
             encodedMessage += encodeACharacter(messageArray[i].charAt(0));
         }

         return encodedMessage;
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
        return decodeAString(codedMessage);
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
        return encodeAString(message);
    }

}
