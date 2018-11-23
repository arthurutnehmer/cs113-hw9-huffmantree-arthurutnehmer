package edu.miracosta.cs113;

import java.util.Comparator;
/**
 * PrevalenceComparator : Compares the prevalence.
 *
 * @author Arthur
 * @version 1.0
 */
public class PrevalenceComparator implements Comparator<BinaryTree.Node<CharacterAndWeight>>
{
    @Override
    public int compare(BinaryTree.Node<CharacterAndWeight> o1, BinaryTree.Node<CharacterAndWeight> o2)
    {
        if(o1.data.prevalence != o2.data.prevalence)
        {
            return ((o1.data.prevalence) - (o2.data.prevalence));
        }
        else
            {
            return ((o1.data.getTimeWhenCreated()) - (o2.data.getTimeWhenCreated()));
        }
    }
}
