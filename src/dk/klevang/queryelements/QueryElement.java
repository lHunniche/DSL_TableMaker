package dk.klevang.queryelements;

import dk.klevang.Query;

public class QueryElement
{
    private QueryElement child;

    protected QueryElement(QueryElement child)
    {
        this.child = child;
    }

    public QueryElement getChild()
    {
        return child;
    }
}
