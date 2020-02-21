package dk.klevang.queryelements;

import dk.klevang.Table;

public class From extends QueryElement
{
    private Table fromTable;
    private Where where;

    public From(QueryElement child)
    {
        super(child);
    }

    public Table getFromTable()
    {
        return fromTable;
    }

    public void setFromTable(Table fromTable)
    {
        this.fromTable = fromTable;
    }

    public Where getWhere()
    {
        return where;
    }

    public void setWhere(Where where)
    {
        this.where = where;
    }

}
