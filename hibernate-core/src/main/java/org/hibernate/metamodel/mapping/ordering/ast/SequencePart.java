/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.metamodel.mapping.ordering.ast;

import org.hibernate.metamodel.mapping.ordering.TranslationContext;

/**
 * Represents an individual identifier in a dot-identifier sequence
 *
 * @author Steve Ebersole
 */
public interface SequencePart {
	SequencePart resolvePathPart(
			String name,
			boolean isTerminal,
			TranslationContext translationContext);
}
