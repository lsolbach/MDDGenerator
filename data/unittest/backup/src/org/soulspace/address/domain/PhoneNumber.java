/*
 * Interface PhoneNumber
 * Template:  domain/java/entity-interface
 * Imports:   lib,model/lib,java/lib,java/interface,common/java/lib,domain/model/lib,domain/java/lib
 * Timestamp: Wed Feb 02 21:35:29 CET 2011
 */

package org.soulspace.address.domain;


import java.util.*;
import org.soulspace.annotation.domain.*;


@Entity
public interface PhoneNumber  {

	// Accessors

	String getNumber();

	void setNumber(String number);


	// Operations

}

