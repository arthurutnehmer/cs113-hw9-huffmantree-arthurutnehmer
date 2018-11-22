package edu.miracosta.cs113;
/**
 * Character  : Class that is within a node. It will contain the weight and the character.
 */
public class CharacterAndWeight implements Comparable
{
    char character;
    int prevalence;

    public CharacterAndWeight(char Character, int Prevalence)
    {
        character = Character;
        prevalence = Prevalence;
    }

    /*sets the character in the node.
     */
    public void setCharacter(char character)
    {
        this.character = character;
    }

    /*gets the character in the node.
     */
    public char getCharacter()
    {
        return character;
    }

    /*gets the prevalence.
     */
    public int getPrevalence()
    {
        return prevalence;
    }

    /*sets the prevalence.
     */
    public void setPrevalence(int prevalence)
    {
        this.prevalence = prevalence;
    }

    /*Adds one to the prevelance
     */
    public void addOneToPrevalence()
    {
        prevalence = (prevalence+1);
    }

    /*Compares the weight of each object.
     */
    @Override
    public int compareTo(Object o)
    {

        if(((CharacterAndWeight) o).prevalence > this.prevalence)
        {
            return -1;
        }
        else if(((CharacterAndWeight) o).prevalence < this.prevalence)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /*Converts to string.
     */
    public String toString()
    {
        String toReturn = "";
        toReturn += "The character :(" + character + ") the prevalence:" + prevalence ;
        return toReturn;
    }

}
