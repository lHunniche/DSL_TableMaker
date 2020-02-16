package dk.klevang;

public class Query implements ISelectBuilder, IFromBuilder, IWhereBuilder
{
    private String queryString;
    public Query()
    {
        queryString = "";
    }

    @Override
    public IFromBuilder select(String... coloums)
    {
        return this;
    }

    @Override
    public String where(String columnValue, String operator, int value)
    {
        return this.queryString;
    }

    @Override
    public ISelectBuilder where(String columnValue, String operator, ISelectBuilder nestedSelect)
    {
        return this;
    }

    @Override
    public IWhereBuilder from(String tableName)
    {
        return null;
    }
}
