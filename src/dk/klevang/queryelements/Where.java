package dk.klevang.queryelements;

public class Where
{
    private String columnValue;
    private String operator;
    private int value;

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

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
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
