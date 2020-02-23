package dk.klevang.builders;

import dk.klevang.Column;

public interface ISelectBuilder
{
    IFromBuilder select (String... columns);
}
