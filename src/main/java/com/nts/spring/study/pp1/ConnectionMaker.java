package com.nts.spring.study.pp1;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	Connection makeConnection() throws ClassNotFoundException, SQLException;
}
