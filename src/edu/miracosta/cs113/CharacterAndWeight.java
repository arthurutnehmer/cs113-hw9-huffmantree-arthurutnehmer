package edu.miracosta.cs113;
import java.sql.Timestamp;
/**
 * Character  : Class that is within a node. It will contain the weight and the character.
 */
public class CharacterAndWeight
{
    char character;
    int prevalence;
    private int timeWhenCreated;

    public CharacterAndWeight(char Character, int Prevalence)
    {
        character = Character;
        prevalence = Prevalence;
        timeWhenCreated = 0;
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

    /*gets the time when the item was created.
     */
    public int getTimeWhenCreated()
    {
        return timeWhenCreated;
    }
    /*sets the order in which the items were created.
     */
    public void setTimeWhenCreated(int timeWhenCreated)
    {
        this.timeWhenCreated = timeWhenCreated;
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

    /*Converts to string.
     */
    public String toString()
    {
        String toReturn = "";
        toReturn += "The character :(" + character + ") the prevalence:" + prevalence ;
        return toReturn;
    }

}
