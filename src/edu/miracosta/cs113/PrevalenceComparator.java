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
        return (o1.data.prevalence - o2.data.prevalence);
    }
}
