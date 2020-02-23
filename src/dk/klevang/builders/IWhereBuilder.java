package dk.klevang.builders;

public interface IWhereBuilder extends IBuilder
{
    IBuilder where(String columnValue, String operator, int value);
    IBuilder where(String columnValue, String operator);
    IBuilder where(String columnValue, String operator, String value);
}
