/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.metamodel.mapping.ordering.ast;

import org.hibernate.NullPrecedence;
import org.hibernate.SortOrder;

/**
 * An individual sort specification in an order-by fragment
 *
 * @author Steve Ebersole
 */
public class SortSpecification implements Node {
	private final SortExpression sortExpression;

	private String collation;
	private SortOrder sortOrder;
	private NullPrecedence nullPrecedence = NullPrecedence.NONE;

	public SortSpecification(SortExpression sortExpression) {
		this.sortExpression = sortExpression;
	}

	public SortExpression getSortExpression() {
		return sortExpression;
	}

	public String getCollation() {
		return collation;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public NullPrecedence getNullPrecedence() {
		return nullPrecedence;
	}

	public void setNullPrecedence(NullPrecedence nullPrecedence) {
		this.nullPrecedence = nullPrecedence;
	}
}
