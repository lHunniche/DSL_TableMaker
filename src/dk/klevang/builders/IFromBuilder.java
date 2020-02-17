package dk.klevang.builders;

import dk.klevang.Table;

public interface IFromBuilder
{
    IWhereBuilder from(Table fromTable);
    IWhereBuilder from(String tableName);
}
