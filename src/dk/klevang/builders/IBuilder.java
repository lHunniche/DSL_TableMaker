package dk.klevang.builders;

import dk.klevang.Query;

public interface IBuilder
{
    Query build();
    ISelectBuilder enterNest();
    IWhereBuilder as(String alias);
    IWhereBuilder exitNest();
}
