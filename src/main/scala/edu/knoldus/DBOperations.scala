package edu.knoldus

import java.sql._

abstract class Operations

class DBOperations extends DBConnectivity {

  def createTable(connectObject: Connection, query: String): Boolean = {

    val statement = connectObject.prepareStatement(query)
    try {
      val queryAnswer = statement.executeUpdate
      if (queryAnswer == 0)
        true
      else
        false
    }

    catch {
      case ex: Exception => false
    }
  }

  def insertToTable(connectObject: Connection, query: Operations): Boolean = {
    val statement = connectObject.createStatement
    try {
      query match {
        case Employee(empID, name, address, phone, deptID, projectID) => statement.executeUpdate(s"(Insert into" +
          s"Employee Values $empID, $name, $address, $phone, $deptID, $projectID);");
          true
        case Department(deptID, name) => statement.executeUpdate(s"(Insert into" +
          s"Department Values $deptID, $name);");
          true
        case Client(clientID, projectID, name, address) => statement.executeUpdate(s"(Insert into" +
          s"Clent Values $clientID, $projectID, $name, $address);");
          true
        case Project(projectID, deptID, name, clientID) => statement.executeUpdate(s"(Insert into" +
          s"Project Values $projectID, $deptID, $name, $clientID);");
          true
        case _ => false
      }
    }
    catch {
      case ex: Exception => false
    }


  }

  /*def updateTables(connectObject: Connection, query: String, db_ID: Int): Boolean = {
    val newStatement = connectObject.createStatement
    try {
      query match {
        case Employee(empID, name, address, phone, deptID, projectID) => newStatement.executeUpdate(s"(update Employee set ID = $empID," +
          s"name = $name, address = $address, phone = $phone, deptID = $deptID, projectID = $projectID where ID = $db_ID);");
          true
        case Department(deptID, name) => newStatement.executeUpdate(s"(update Department set deptID = $deptID" +
          s"name = $name where deptID = $db_ID);");
          true
        case Client(clientID, projectID, name, address) => newStatement.executeUpdate(s"(update Client set clientID = $clientID," +
          s"projectID = $projectID, name = $name, address = $address where clientID = $db_ID);");
          true
        case Project(projectID, deptID, name, clientID) => newStatement.executeUpdate(s"(update Project set projectID = $projectID," +
          s"deptID = $deptID, name = $name, clientID = $clientID where projectID = $db_ID);");
          true
        case _ => false
      }
    }
    catch {
      case ex: Exception => false
    }

  }*/

  /*def deleteTable(connectObject: Connection, query: String): Boolean={

  }*/

  def retrieveFromTable(connectObject: Connection, table: String): Boolean = {
    val sql = s"SELECT * FROM $table";

    val statement = connectObject.createStatement();
    val resultSet = statement.executeQuery(sql);

    val count = 0;

    while (resultSet.next()) {
      try {
        table match {
          case "Employee" => ???
          case "Department" => ???
          case "Client" => ???
          case "Project" => ???
          case _ => false

        }
      }
      catch {
        case ex: Exception => false
      }

    }
  }

}

