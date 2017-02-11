package edu.knoldus

import java.sql._

import scala.annotation.tailrec

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

  def updateTables(connectObject: Connection, query: Operations, db_ID: Int): Boolean = {
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

  }

  def deleteTable(connectObject: Connection, table: String, id: Int): Boolean = {
    val statement = connectObject.createStatement
    try {
      if (statement.executeUpdate(s"DELETE FROM $table Where id=$id") > 0)
        true
      else
        false
    }
    catch {
      case ex: Exception => false
    }
  }


    def retrieveFromTable(connectObject: Connection, table: String): List[Operations] = {
      val sql = s"SELECT * FROM $table"
      val statement = connectObject.createStatement
      val resultSet: ResultSet = statement.executeQuery(sql)

      @tailrec
      def readRecords(resultSet: ResultSet,
                      table: String,
                      rawData: List[Operations] = Nil): List[Operations] = {
        if (!resultSet.next()) {
          rawData
        } else {
          table match {
            case "Employee" => readRecords(resultSet, table, rawData :+ getEmployeeData(resultSet))
            case "Department" => readRecords(resultSet, table, rawData :+ getDepartmentData(resultSet))
            case "Client" => readRecords(resultSet, table, rawData :+ getClientData(resultSet))
            case "Project" => readRecords(resultSet, table, rawData :+ getProjectData(resultSet))
            case _ => List()
          }
        }
      }

      readRecords(resultSet, table)
    }

    def getEmployeeData(resultSet: ResultSet): Operations = {
      Employee(resultSet
        .getInt("empID"),
        resultSet.getString("name"), resultSet.getString("address"),
        resultSet.getInt("phone"), resultSet.getInt("deptID"),
        resultSet.getInt("projectID"))
    }

    def getDepartmentData(resultSet: ResultSet): Operations = {
      Department(resultSet.getInt("deptID"),
        resultSet.getString("name"))
    }

    def getClientData(resultSet: ResultSet): Operations = {
      Client(resultSet.getInt("clientID"),
        resultSet.getInt("projectID"), resultSet.getString("name"),
        resultSet.getString("address"))
    }

    def getProjectData(resultSet: ResultSet): Operations = {
      Project(resultSet.getInt("projectID"),
        resultSet.getInt("deptID"), resultSet.getString("name"),
        resultSet.getInt("clientID"))
    }
}