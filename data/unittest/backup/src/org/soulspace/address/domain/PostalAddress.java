/*
 * Interface PostalAddress
 * Template:  domain/java/entity-interface
 * Imports:   lib,model/lib,java/lib,java/interface,common/java/lib,domain/model/lib,domain/java/lib
 * Timestamp: Wed Feb 02 21:35:29 CET 2011
 */

package org.soulspace.address.domain;


import java.util.*;
import org.soulspace.annotation.domain.*;


@Entity
public interface PostalAddress  {

	// Accessors

	String getStreet();

	void setStreet(String street);

	String getHouseNumber();

	void setHouseNumber(String houseNumber);

	String getZip();

	void setZip(String zip);

	String getCity();

	void setCity(String city);


	// Operations

}

