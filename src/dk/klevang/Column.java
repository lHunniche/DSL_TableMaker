package dk.klevang;

class Column
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

    String getName()
    {
        return name;
    }

    String getType()
    {
        return this.type;
    }

    String getProperty()
    {
        return property;
    }













}
