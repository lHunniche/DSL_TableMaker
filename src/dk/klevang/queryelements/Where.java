package dk.klevang.queryelements;

public class Where
{
    private String columnValue;
    private String operator;
    private int value;
    private String stringValue;

    private Select nestedSelect;

    public String getColumnValue()
    {
        return columnValue;
    }

    public void setColumnValue(String columnValue)
    {
        this.columnValue = columnValue;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void setValue(String value)
    {
        this.stringValue = value;
    }

    public String getValue()
    {
        return (stringValue == null ? value + "" : stringValue);
    }

    public Select getNestedSelect()
    {
        return nestedSelect;
    }

    public void setNestedSelect(Select nestedSelect)
    {
        this.nestedSelect = nestedSelect;
    }

}
