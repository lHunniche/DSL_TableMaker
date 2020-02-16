package dk.klevang.tablegenerator;

class Column_OLD
{
    private String name;
    private String type;
    private String[] property;

    Column_OLD(String name, String type)
    {
        this.name = name;
        this.type = type;
        this.property = null;
    }

    Column_OLD(String name, String type, String[] property)
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

    String[] getProperty()
    {
        return property;
    }

    String getPropertiesAsString()
    {
        if (this.property == null) return "";
        StringBuilder builder = new StringBuilder();
        for (String s : this.property)
        {
            builder.append(s);
            builder.append(" ");
        }

        return builder.toString().trim();
    }













}
