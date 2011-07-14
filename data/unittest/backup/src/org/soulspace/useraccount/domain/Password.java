/*
 * Interface of value Password
 * Template:  domain/java/value-interface
 * Imports:   lib,model/lib,java/lib,java/interface,common/lib,common/java/lib,domain/model/lib,domain/java/lib
 * Timestamp: Wed Apr 13 09:49:39 CEST 2011
 */

package org.soulspace.useraccount.domain;


import java.util.*;
import org.soulspace.annotation.domain.*;


@Value
public interface Password  {
// Accessors

	String getValue();

	void setValue(String value);

	boolean getInitial();

	void setInitial(boolean initial);


// Operations

}
