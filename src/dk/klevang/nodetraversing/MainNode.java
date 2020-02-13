package dk.klevang.nodetraversing;

public class MainNode
{
    public static void main(String[] args)
    {
        Node master = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        Node node7 = new Node();

        master.addChild(node1);
        master.addChild(node2);

        node1.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node4.addChild(node6);
        node6.addChild(node7);

        master.traverseTree(master);
    }
}
