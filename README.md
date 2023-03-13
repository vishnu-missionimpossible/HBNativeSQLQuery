# HBNativeSQLQuery

NativeSQL Query
----------------------
=> It is given to execute plain SQL queries that are supported by underlying DB
S/w.
=> We need to use these operations only when it is not possible through HQL

eg: inserting a single record.
=> It supports both select and non select operation
=> These queries performance is bit good compared to HQL because they go to sql
directly without any conversion.
=> We write a query using table name and column names.
working with select operation
---------------------------------------
1. working with single record.
2. working with all record.
Working with Nonselect operation
1. using NamedNativeQuery
2. Directly writing the query inside DAOImpl class.
refer: HBNativeSQLQuery
