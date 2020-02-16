package dk.klevang.builders;

public interface IWhereBuilder
{
    IBuilder where(String columnValue, String operator, int value);
    IBuilder where(String columnValue, String operator);
}
