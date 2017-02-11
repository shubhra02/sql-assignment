package edu.knoldus

import java.sql._

class GeneralOperations {

  val dbOperationsObject = new DBOperations

  def getEmployeeByDept(connectObject: Connection, dID: Int): List[Operations] = {
    val table: String = "Employee"
    val result = dbOperationsObject.retrieveFromTable (connectObject, table)
    result.filter (_.asInstanceOf[Employee].deptID == dID)

  }

  def getEmployeeByProject(connectObject: Connection, pID: Int): List[Operations] = {
    val table = "Employee"
    val result = dbOperationsObject.retrieveFromTable (connectObject, table)
    result.filter(_.asInstanceOf[Employee].projectID == pID)

  }

  def getClientByProject(connectObject: Connection, pID: Int): List[Operations] = {
    val table = "Client"
    val result = dbOperationsObject.retrieveFromTable (connectObject, table)
    result.filter(_.asInstanceOf[Client].projectID == pID)

  }

}
