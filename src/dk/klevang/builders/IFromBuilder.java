package dk.klevang.builders;

import dk.klevang.Table;

public interface IFromBuilder
{
    IWhereBuilder from(String tableName);
    IBuilder from();
}
