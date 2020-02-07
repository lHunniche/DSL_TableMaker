package dk.klevang;

public class Column
{
    private String name;
    private String type;
    private String property;

    Column(String name, String type)
    {
        this.name = name;
        this.type = type;
        this.property = "";
    }

    Column(String name, String type, String property)
    {
        this.name = name;
        this.type = type;
        this.property = property;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return this.type;
    }

    public String getProperty()
    {
        return property;
    }













}
