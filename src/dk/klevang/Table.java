package dk.klevang;

public class Table
{
    private String name;
    private Column[] columns;

    public Table(String name)
    {
        this.name = name;
    }

    public Table(Column... columns)
    {
        this.columns = columns;
    }

    public String getName()
    {
        return name;
    }
}
