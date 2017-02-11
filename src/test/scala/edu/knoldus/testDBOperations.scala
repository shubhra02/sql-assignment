package edu.knoldus

import java.sql.Connection

import org.scalatest.FunSuite


class DBOperationsTest extends FunSuite {
  val operationObject = new DBOperations
  val connection = new DBConnectivity
  val eID = 111
  val pID = 311
  val cID = 411
  val dID = 211
  val connectionObject: Connection = connection.getDBConnection

  test("creation of connection object") {

    assert(connection.getDBConnection.isInstanceOf[Connection]
      || !connection.getDBConnection.isInstanceOf[Connection]
    )
  }

  /**
    * test cases for checking the successful creation of tables
    */

  test("throw an exception on wrong query syntax and thus return false on failing of table creation Department") {
    val query = "table Department (deptID Int(5) Primary key NOT NULL, name varchar(20))"
    assert(!operationObject.createTable(connectionObject, query))

  }


  test("to check the table creation Project and it should return true upon table creation Department") {
    val query = "Create table Department (deptID Int(5) Primary key NOT NULL, name varchar(20))"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean])

  }

  test("to check the table creation Employee and it should return true upon table creation Employee") {
    val query = "Create table Employee (empID Int(5) Primary key NOT NULL, name varchar(20)," +
      s"address varchar(30), phone Int(10), deptId Int(5), projectID Int(5))"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean])

  }

  test("to check the table creation Department and it should return true upon table creation Project") {
    val query = "Create table Project (projectID Int(5) Primary key NOT NULL, deptID Int(5), name varchar(20), clientID Int(5))"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean])

  }

  test("to check the table creation Client and it should return true upon table creation Client") {
    val query = "Create table Client (clientID Int(5) Primary key, projectID Int(5), name varchar(20), address varchar(30))"
    assert(operationObject.createTable(connectionObject, query).isInstanceOf[Boolean])

  }

  /**
    * test cases for insertion of data into tables
    */

  test("to check the data insertion into table Department, return true on successful insertion else false ") {
    val insertQuery = Department(211, "science")
    assert(operationObject.insertToTable(connectionObject, insertQuery).isInstanceOf[Boolean])
  }

  test("to check the data insertion into table Project, return true on successful insertion else false") {
    val insertQuery = Project(311, 13, "science", 151)
    assert(operationObject.insertToTable(connectionObject, insertQuery).isInstanceOf[Boolean])
  }

  test("to check the data insertion into table Employee, return true on successful insertion else false") {
    val insertQuery = Employee(111, "Inayat", "Noida", 9825151, 121, 31)
    assert(operationObject.insertToTable(connectionObject, insertQuery).isInstanceOf[Boolean])
  }

  test("to check the data insertion into table Client, return true on successful insertion else false") {
    val insertQuery = Client(411, 311, "aparna", "Pune")
    assert(operationObject.insertToTable(connectionObject, insertQuery).isInstanceOf[Boolean])

  }

  /**
    * test cases for retrieval of data from tables tables
    */
  test("to check the data fetched from the table Employee") {
    val dataLength = operationObject.retrieveFromTable(connectionObject, "Employee").size
    assert(dataLength >= 0)
  }
  test("to check the data fetched from the table Department") {
    val dataLength = operationObject.retrieveFromTable(connectionObject, "Department").size
    assert(dataLength >= 0)
  }
  test("to check the data fetched from the table Project") {
    val dataLength = operationObject.retrieveFromTable(connectionObject, "Project").size
    assert(dataLength >= 0)
  }
  test("to check the data fetched from the table Client") {
    val dataLength = operationObject.retrieveFromTable(connectionObject, "Client").size
    assert(dataLength >= 0)
  }

  /**
    * test cases for updation of data in tables
    */
  test("to check the data updation into table Employee, return true on successful updation else false") {
    val query = Employee(eID, "Pallavi", "Punjab", 985456151, 121, 31)
    assert(operationObject.updateTables(connectionObject, query, eID).isInstanceOf[Boolean])

  }
  test("to check the data updation into table Department, return true on successful updation else false") {
    val query = Department(dID, "Design")
    assert(operationObject.updateTables(connectionObject, query, dID).isInstanceOf[Boolean])

  }
  test("to check the data updation into table Project, return true on successful updation else false") {
    val query = Project(pID, 122, "Carbon Data", 231)
    assert(operationObject.updateTables(connectionObject, query, pID).isInstanceOf[Boolean])

  }
  test("to check the data updation into table Client, return true on successful updation else false") {
    val query = Client(pID, 121, "aparna", "Kanpur")
    assert(operationObject.updateTables(connectionObject, query, eID).isInstanceOf[Boolean])

  }
  /**
    * test cases for deletion of data from tables
    */

  test("to check if the data is not successfully deleted from the table"){
    assert(!operationObject.deleteTable(connectionObject,"Employee", eID))
  }
  test("to check if the data is successfully deleted from the tables"){
    assert(operationObject.deleteTable(connectionObject,"Employee", eID).isInstanceOf[Boolean])
  }


}


