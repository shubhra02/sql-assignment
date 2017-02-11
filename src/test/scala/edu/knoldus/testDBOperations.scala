package edu.knoldus

import java.sql.Connection

import org.scalatest.FunSuite


class testDBoperationObjects extends FunSuite {
  val operationObject = new DBOperations
  val connection = new DBConnectivity
  val user = "root"
  val password = "root"
  val connectionObject: Connection = connection.getDBConnection

  /**
    * test case for creating database creation
    */

  test("creation of connection object") {

    assert(connection.getDBConnection.isInstanceOf[Connection] == true
      || connection.getDBConnection.isInstanceOf[Connection] == false
    )
  }

  /**
    * test cases for checking the successful creation of tables
    */

  test("throw an exception on wrong query syntax and thus return false on failing of table creation Department") {
    val query = "table Department (id Int(5) Primary key NOT NULL, name varchar(20))"
    assert(operationObject.createTable(connectionObject, query) == false)

  }


  test("to check the table creation Project and it should return true upon table creation Project") {
    val query = "Create table Project (projectID Int(5) Primary key NOT NULL, deptID Int(5), name varchar(20), clientID Int(5),)"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }

  test("to check the table creation Employee and it should return true upon table creation Employee") {
    val query = "Create table Employee (empID Int(5) Primary key NOT NULL, name varchar(20),"+
      s"address varchar(30), phone Int(10), deptId Int(5), projectID Int(5))"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }

  test("to check the table creation Department and it should return true upon table creation Department") {
    val query = "Create table Project (projectID Int(5) Primary key NOT NULL, deptID Int(5), name varchar(20), clientID Int(5))"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }

  test("to check the table creation Client and it should return true upon table creation Client") {
    val query = "Create table Client (clientID Int(5) Primary key, projectID Int(5), name varchar(20), address varchar(30))"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }

  /**
    * test cases for insertion of data into tables
    */

  test("to check the data insertion into table Department, return on successful insertion else false ") {
    val insertQuery = Department(12, "science")
    assert(operationObject.insertToTable(connectionObject, insertQuery).isInstanceOf[Boolean] == true)
  }

  test("to check the data insertion into table Project, return on successful insertion else false") {
    val insertQuery =  Project(12, 13, "science", 151)
    assert(operationObject.insertToTable(connectionObject, insertQuery).isInstanceOf[Boolean] == true)
  }

  test("to check the data insertion into table Employee, return on successful insertion else false") {
    val insertQuery =  Employee(12, "Inayat", "Noida", 151, 121, 31)
    assert(operationObject.insertToTable(connectionObject, insertQuery).isInstanceOf[Boolean] == true)
  }

  test("to check the data insertion into table Client, return on successful insertion else false") {
    val query = "Create table Project (projectID Int(5) Primary key NOT NULL, deptID Int(5), name varchar(20), clientID Int(5),)"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }

  /**
    * test cases for retrieval of data from tables tables
    */



}

