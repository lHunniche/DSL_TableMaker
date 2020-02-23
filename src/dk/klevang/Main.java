package dk.klevang;

public class Main
{

    public static void main(String[] args)
    {
        Query query = new Query().begin().
                select("name", "age").
                from("customers").
                where("credit", ">", 10).
                build();

        System.out.println(query.getQueryString() + "\n\n");


        Query nestedQuery = new Query().begin().
                select("name", "age").
                from("customers").
                where("credit", ">")
                    .enterNest().
                        select("grade").
                        from("requirements").
                        where("subject", "=", "science").
                        build();

        System.out.println(nestedQuery.getQueryString() + "\n\n");


        Query doubleNestedQuery = new Query().begin().
                select("name", "age").
                from("students").
                where("credit", ">")
                .enterNest().
                    select("grade").
                    from("requirements").
                    where("subject", "=")
                    .enterNest().
                        select("favourite_subject").
                        from("students").
                        where("name", "=", "nestedName").
                        build();

        System.out.println(doubleNestedQuery.getQueryString() + "\n\n");



        Query nestedFrom = new Query().begin()
                .select("name")
                .from()
                .enterNest()
                    .select("MAX(price)", "ProductName")
                    .from("products")
                    .as("MyAlias")
                .where("discount", ">", 10)
                .build();

        System.out.println(nestedFrom.getQueryString());


        //TODO: when value is given in where statement, don't allow for any more nested queries.

        //TODO: allow reference to higher up queries (alias, as).

        //TODO: exit out of nested query

        //TODO: select from (select from....)

        //TODO: support more SQL versions (MySQL, SQL Server...)











//        // Creates a table with a column property (NOT NULL) and Primary key
//        Table table = new Table.Builder("Persons")
//                .withColumn("PersonID", ColumnType.INT()).asPrimaryKey()
//                .withColumn("FirstName", ColumnType.VAR_CHAR(255), ColumnProperty.NOT_NULL)
//                .withColumn("LastName", ColumnType.VAR_CHAR(255))
//                .withColumn("City", ColumnType.VAR_CHAR(255))
//                .withColumn("Address", ColumnType.VAR_CHAR(255))
//                .build();
//
//        System.out.println(table.getCreateString() + "\n");
//
//        // creates a table with a Primary and Foreign key
//        Table orderTable = new Table.Builder("Orders")
//                .withColumn("Name", ColumnType.VAR_CHAR(255))
//                .withColumn("OrderID", ColumnType.INT()).asPrimaryKey()
//                .withColumn("PersonID", ColumnType.INT()).asForeignKeyWith("Persons", "PersonID")
//                .build();
//
//        System.out.println(orderTable.getCreateString() + "\n");
//
//        //creates a table with only a Foreign key
//        Table orderAmounts = new Table.Builder("OrderAmounts")
//                .withColumn("Amount", ColumnType.FLO.AT(10))
//                .withColumn("OrderID", ColumnType.INT()).asForeignKeyWith("Orders", "OrderID")
//                .build();
//
//        System.out.println(orderAmounts.getCreateString() + "\n");
//
//        // creates a table with no keys and no column properties
//        Table weirdTable = new Table.Builder("Weird")
//                .withColumn("Monkey", ColumnType.BLOB(255))
//                .build();
//
//        System.out.println(weirdTable.getCreateString() + "\n");
    }
}
