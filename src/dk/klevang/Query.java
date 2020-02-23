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
    private Select root;

    public Query()
    {

    }

    private String generateQueryString(Select select)
    {
        StringBuilder stringBuilder = new StringBuilder();

        /* SELECT  */
        stringBuilder.append("SELECT ");

        for (Column column : select.getColumns())
        {
            stringBuilder.append(column.getName());
            stringBuilder.append(", ");
        }

        String checkPoint = stringBuilder.toString();
        checkPoint = checkPoint.trim();
        if (checkPoint.charAt(checkPoint.length() - 1) == ',')
        {
            checkPoint = checkPoint.substring(0, checkPoint.length() - 1);
        }

        stringBuilder = new StringBuilder();
        stringBuilder.append(checkPoint);
        stringBuilder.append(" ");

        /* FROM  */
        From currentFrom = select.getFrom();
        stringBuilder.append("FROM ");
        if (currentFrom.getFromTable() != null)
        {
            stringBuilder.append(currentFrom.getFromTable().getName());
            stringBuilder.append(" ");
        }
        else
        {
            stringBuilder.append("\n\t(").append(this.generateQueryString(currentFrom.getSelect())).append(") as ").append(currentFrom.getAlias()).append("\n");
        }


        /* WHERE  */
        if (currentFrom.getWhere() != null)
        {
            Where currentWhere = currentFrom.getWhere();
            stringBuilder.append("WHERE ");
            stringBuilder.append(currentWhere.getColumnValue());
            stringBuilder.append(" ").append(currentWhere.getOperator());
            stringBuilder.append(" ");

            if (currentWhere.getNestedSelect() == null)
            {
                stringBuilder.append(currentWhere.getValue());
            }
            else
            {
                // make this work for nested queries
                stringBuilder.append("\n\t(").append(this.generateQueryString(currentWhere.getNestedSelect())).append(")");
            }
        }

        /* FINISHING  */
        return stringBuilder.toString().trim();
    }

    public String getQueryString()
    {
        return this.queryString;
    }

    public ISelectBuilder begin()
    {
        return new Builder();
    }


    private static class Builder implements ISelectBuilder, IFromBuilder, IWhereBuilder, IBuilder
    {
        private Select root;
        private Select select;
        private From from;
        private Where where;
        private boolean containsNestedWhereQuery = false;
        private boolean containsNestedFromQuery = false;

        private Builder()
        {
        }

        @Override
        public Query build()
        {
            //build query, clean up elements, and return query.
            Query query = new Query();
            this.root = (this.root == null ? this.select : this.root);
            query.root = this.root;
            query.queryString = query.generateQueryString(query.root);

            return query;
        }

        @Override
        public ISelectBuilder enterNest()
        {
            // appropriate clean up of selects/froms/wheres when nested query is inited.
            if (this.from.getFromTable() == null)
            {
                this.containsNestedFromQuery = true;
            }
            else
            {
                this.containsNestedWhereQuery = true;
            }

            this.root = (this.root == null ? this.select : this.root);
            this.select = null;
            this.from = null;

            return this;
        }

        @Override
        public IWhereBuilder as(String alias)
        {
            this.from = root.getFrom();
            this.from.setAlias(alias);
            return this;
        }

        @Override
        public IWhereBuilder exitNest()
        {
            this.from = root.getFrom();
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
            if (this.containsNestedWhereQuery)
            {
                this.where.setNestedSelect(this.select);
                this.where = null;
                this.containsNestedWhereQuery = false;
            }
            else if (this.containsNestedFromQuery)
            {
                this.root.getFrom().setSelect(this.select);
                this.containsNestedFromQuery = false;
            }

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
        public IBuilder from()
        {
            // we come in here when the SELECT "name" FROM ... is followed by a nested select statement.
            if (this.from == null)
            {
                this.from = new From();
            }
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

        @Override
        public IBuilder where(String columnValue, String operator, String value)
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
