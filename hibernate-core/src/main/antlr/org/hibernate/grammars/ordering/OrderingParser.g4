parser grammar OrderingParser;

options {
	tokenVocab=OrderingLexer;
}

@header {
/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.grammars.ordering;

/**
 * Grammar for parsing order-by fragments.
 *
 * @implNote While we could re-use the HQL lexer/parser for order fragment parsing, both the HQL lexer and parser
 * are way "heavier" than needed here.  So we use a simplified lexer and parser that defione just what is needed
 * to parse the order fragment
 */
}

// todo (6.0) : add hooks for keyword-as-identifier logging like we do for HQL?

orderByFragment
	: sortSpecification (COMMA sortSpecification)*
	;

sortSpecification
	: expression collationSpecification? direction? nullsPrecedence?
	;

expression
	: function
	| identifier
	| dotIdentifier
	;

function
	: simpleFunction
	| packagedFunction
	;

simpleFunction
	: identifier functionArguments
	;

packagedFunction
	: dotIdentifier functionArguments
	;

functionArguments
	: OPEN_PAREN expression* CLOSE_PAREN
	;

collationSpecification
	: COLLATE identifier
	;

direction
	: ASC | DESC
	;

nullsPrecedence
	: NULLS (FIRST | LAST)
	;

identifier
	: IDENTIFIER
	// keyword-as-identifier
	| FIRST
	| LAST
	| ASC
	| DESC
	| COLLATE
	;

dotIdentifier
	: IDENTIFIER (DOT IDENTIFIER)+
	;
