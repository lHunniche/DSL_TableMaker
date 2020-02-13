package dk.klevang.nodetraversing;

import java.util.ArrayList;

public class Node
{
    private ArrayList<Node> children;
    private int value;
    private static int index = 0;

    public Node()
    {
        this.value = index++;
        children = new ArrayList<>();
    }

    public Node addChild(Node child)
    {
        children.add(child);
        return this;
    }

    public void traverseTree(Node fromNode)
    {
        System.out.println(fromNode.value);
        visitNode(fromNode);
    }

    private void visitNode(Node node)
    {
        for (Node n : node.children)
        {
            System.out.println(n.value);
            visitNode(n);
        }
    }
}
