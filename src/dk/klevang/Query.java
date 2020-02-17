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
        StringBuilder stringBuilder = new StringBuilder();

        /* SELECT  */
        stringBuilder.append("SELECT ");
        for (Column column : this.select.getColumns())
        {
            stringBuilder.append(column.getName());
            stringBuilder.append(", ");
        }

        String checkPoint = stringBuilder.toString();
        checkPoint = checkPoint.trim();
        if (checkPoint.charAt(checkPoint.length()-1) == ',')
        {
            checkPoint = checkPoint.substring(0, checkPoint.length() - 1);
        }

        stringBuilder = new StringBuilder();
        stringBuilder.append(checkPoint);
        stringBuilder.append("\n");

        /* FROM  */

        /* WHERE  */

    }

    public String getQueryString()
    {
        return this.queryString;
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
        private boolean containsNestedQuery = false;

        private Builder()
        {
        }

        @Override
        public Query build()
        {
            //build query, clean up elements, and return query.
            Query query = new Query();
            query.select = this.select;
            query.from = this.from;
            query.where = this.where;
            query.generateQueryString();

            return query;
        }

        @Override
        public ISelectBuilder inNestedQuery()
        {
            // appropriate clean up of selects/froms/wheres when nested query is initted.
            this.containsNestedQuery = true;
            this.select = null;
            this.from = null;

            return this;
        }

        @Override
        public IFromBuilder select(Column... columns)
        {
            if (this.select == null)
            {
                this.select = new Select();
            }

            this.select.setColumns(columns);
            if (this.containsNestedQuery)
            {
                this.where.setNestedSelect(this.select);
            }
            this.containsNestedQuery = false;

            return this;
        }

        @Override
        public IFromBuilder select(String... columns)
        {
            if (this.select == null)
            {
                this.select = new Select();
            }

            this.select.setColumns(createColumnsFromStringNames(columns));
            if (this.containsNestedQuery)
            {
                this.where.setNestedSelect(this.select);
            }
            this.containsNestedQuery = false;

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
        public IWhereBuilder from(String tableName)
        {
            if (this.from == null)
            {
                this.from = new From();
            }

            this.from.setFromTable(new Table(tableName));
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

        private Column[] createColumnsFromStringNames(String[] columnNames)
        {
            Column[] columns = new Column[columnNames.length];
            for (int i = 0; i < columns.length; i++)
            {
                columns[i] = new Column(columnNames[i]);
            }

            return columns;
        }
    }
}
