package com.planon.mdb;

import java.sql.SQLException;

import com.planon.mdb.operations.read.Read;

public class ApplicationDB {

	public static void main(String[] args) throws SQLException {

		Read read = new Read();
//		read.readmdb();
//		read.traceFrom();
		read.traceTo();
	}
}
