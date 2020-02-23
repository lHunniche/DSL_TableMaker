package dk.klevang.queryelements;

import dk.klevang.Table;

public class From
{
    private Table fromTable;
    private Where where;
    private Select select;
    private String alias;

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

    public Select getSelect()
    {
        return select;
    }

    public void setSelect(Select select)
    {
        this.select = select;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }
}
