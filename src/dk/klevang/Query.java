package dk.klevang;

import dk.klevang.builders.IBuilder;
import dk.klevang.builders.IFromBuilder;
import dk.klevang.builders.ISelectBuilder;
import dk.klevang.builders.IWhereBuilder;
import dk.klevang.queryelements.From;
import dk.klevang.queryelements.Select;
import dk.klevang.queryelements.Where;

public class Query
{
    private String queryString;
    private Select select;
    private From from;
    private Where where;

    public Query()
    {

    }

    private void generateQueryString()
    {

    }

    public ISelectBuilder init()
    {
        return new Builder();
    }


    private static class Builder implements ISelectBuilder, IFromBuilder, IWhereBuilder, IBuilder
    {
        private Select select;
        private From from;
        private Where where;

        private Builder()
        {
        }

        @Override
        public Query build()
        {
            //build query, clean up elements, and return query.
            return null;
        }

        @Override
        public ISelectBuilder inNestedQuery()
        {
            // appropriate clean up of selects/froms/wheres when nested query is initted.
            return null;
        }

        @Override
        public IFromBuilder select(Column... columns)
        {
            if (this.select == null)
            {
                this.select = new Select();
            }

            select.setColumns(columns);

            return this;
        }

        @Override
        public IWhereBuilder from(Table fromTable)
        {
            if (this.from == null)
            {
                this.from = new From();
            }

            this.from.setFromTable(fromTable);
            this.select.setFrom(this.from);

            return this;
        }

        @Override
        public IBuilder where(String columnValue, String operator, int value)
        {
            if (this.where == null)
            {
                this.where = new Where();
            }

            this.where.setColumnValue(columnValue);
            this.where.setOperator(operator);
            this.where.setValue(value);
            this.from.setWhere(this.where);

            return this;
        }

        @Override
        public IBuilder where(String columnValue, String operator)
        {
            if (this.where == null)
            {
                this.where = new Where();
            }

            this.where.setColumnValue(columnValue);
            this.where.setOperator(operator);
            this.from.setWhere(this.where);

            return this;
        }
    }
}
