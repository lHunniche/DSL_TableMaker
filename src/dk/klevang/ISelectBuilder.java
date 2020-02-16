package dk.klevang;

public interface ISelectBuilder
{
    IFromBuilder select(String... columns);
}
