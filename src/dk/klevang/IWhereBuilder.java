package dk.klevang;

public interface IWhereBuilder
{
    String where(String columnValue, String operator, int value);
    ISelectBuilder where(String columnValue, String operator, ISelectBuilder nestedSelect);
}
