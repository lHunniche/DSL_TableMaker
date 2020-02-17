package dk.klevang.builders;

import dk.klevang.Column;

public interface ISelectBuilder
{
    IFromBuilder select(Column... columns);
    IFromBuilder select (String... columns);
}
