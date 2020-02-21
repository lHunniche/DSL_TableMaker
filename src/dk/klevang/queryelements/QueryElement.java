package dk.klevang.queryelements;

public abstract class QueryElement
{
    private QueryElement child;

    public abstract QueryElement addChild(QueryElement child);

    public QueryElement getChild()
    {
        return this.child;
    }
}
