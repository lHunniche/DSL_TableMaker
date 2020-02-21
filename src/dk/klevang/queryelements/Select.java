package dk.klevang.queryelements;

import dk.klevang.Column;

public class Select extends QueryElement
{
    private Column[] columns;
    private From from;

    public Select(QueryElement child)
    {
        super(child);
    }

    public Column[] getColumns()
    {
        return columns;
    }

    public void setColumns(Column[] columns)
    {
        this.columns = columns;
    }

    public From getFrom()
    {
        return from;
    }

    public void setFrom(From from)
    {
        this.from = from;
    }
}
